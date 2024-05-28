package com.example.Proyecto.Service;

import com.example.Proyecto.Model.Usuario;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.Proyecto.Dto.UsuarioRegistroDto;

public interface UsuarioSevice extends UserDetailsService{
	
	public Usuario save(UsuarioRegistroDto registroDto);
	
	public List<Usuario> listarUsuarios();
	
	public Usuario actualizarUsuario(Usuario usuario);
	public void eliminarUsuario(Long id);

	public Usuario obtenerUsuarioPorId(Long id);
	
	

}
