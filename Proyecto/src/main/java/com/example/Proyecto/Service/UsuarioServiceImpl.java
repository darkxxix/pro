package com.example.Proyecto.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.Proyecto.Dto.UsuarioRegistroDto;
import com.example.Proyecto.Model.Rol;
import com.example.Proyecto.Model.Usuario;
import com.example.Proyecto.Repository.UsuarioRepository;

@Service
public class   UsuarioServiceImpl implements UsuarioSevice{
	
	@Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
	
	public Usuario obtenerUsuarioActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof Usuario) {
            return (Usuario) auth.getPrincipal();
        } else {
            return null; // Usuario no autenticado
        }
	}
	
	
	@Autowired
	@Lazy
	private BCryptPasswordEncoder passwordEncoder;
	
	private UsuarioRepository usuarioRepository; 
	
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override 
	public Usuario save(UsuarioRegistroDto registroDto) {
	    Usuario usuario = new Usuario(
	        registroDto.getNombre(), 
	        registroDto.getApellido(), 
	        registroDto.getEmail(), 
	        registroDto.getDireccion(),
	        passwordEncoder.encode(registroDto.getPassword()),
	        Arrays.asList(new Rol("ROLE_USER"))
	    );
	    return usuarioRepository.save(usuario);
	}

	
	//obtener nombre de rol y Mapear roles a la autoridad simplegrandautority//

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password invalidos");
		}
		return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol>roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}
	
	@Override
	public Usuario actualizarUsuario(Usuario usuario) {
	    return usuarioRepository.save(usuario);
	}


	@Override
	public void eliminarUsuario(Long id) {
	    usuarioRepository.deleteById(id);
	}
	
}
