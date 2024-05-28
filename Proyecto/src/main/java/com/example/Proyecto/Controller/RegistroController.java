package com.example.Proyecto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Proyecto.Model.Usuario;
import com.example.Proyecto.Service.UsuarioSevice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@Lazy
public class RegistroController {

    @Autowired
    private UsuarioSevice service;

    @GetMapping("/login")
    public String iniciarsesion() {
        return "login";
    }

    @GetMapping("/")
    public String verPerfil(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String rol = auth.getAuthorities().iterator().next().getAuthority(); // Obtener el rol del usuario
        model.addAttribute("rol", rol);
        return "Inicio";
    }

    @GetMapping("/Productos")
    public String verproductos() {
        return "Productos";
    }

    @GetMapping("/usuariolist")
    public String verUsuarios(Model modelo) {
        modelo.addAttribute("usuarios", service.listarUsuarios());
        return "usuariolist";
    }
    
    @PostMapping("/usuario/actualizar")
    public String actualizarUsuario(@RequestParam("id") Long id,
                                    @RequestParam("nombre") String nombre,
                                    @RequestParam("apellido") String apellido,
                                    @RequestParam("direccion") String direccion,
                                    @RequestParam("email") String email) {
        Usuario usuario = service.obtenerUsuarioPorId(id);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDireccion(direccion);
        usuario.setEmail(email);
        service.actualizarUsuario(usuario);
        return "redirect:/usuariolist";
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout"; // Puedes redirigir a donde desees después de cerrar sesión
    }

}
