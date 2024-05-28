package com.example.Proyecto.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Proyecto.Dto.UsuarioRegistroDto;
import com.example.Proyecto.Service.UsuarioSevice;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioController {
	
	private UsuarioSevice usuarioService;

	public RegistroUsuarioController(UsuarioSevice usuarioService) {
		super();
		this.usuarioService = usuarioService;
	
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDto retornarNuevoUsuarioRegistroDto() {
		
		return new UsuarioRegistroDto();
	}
	
	@GetMapping
	public String mostrarFormularioDeResgistro() {
		
		return "registro";
	}
	
	@PostMapping 
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDto registroDto) {
	usuarioService.save(registroDto);
	
	return "redirect:/registro?exito";

}
}
