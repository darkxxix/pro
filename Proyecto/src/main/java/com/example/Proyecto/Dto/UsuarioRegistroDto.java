package com.example.Proyecto.Dto;

public class UsuarioRegistroDto {

	private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public UsuarioRegistroDto(Long id, String nombre, String email, String apellido, String direccion, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.apellido = apellido;
		this.password = password;
		this.direccion = direccion;
	}

	public UsuarioRegistroDto(String nombre, String email, String apellido, String direccion, String password) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.apellido = apellido;
		this.password = password;
		this.direccion = direccion;
	}

	public UsuarioRegistroDto(String email) {
		super();
		this.email = email;
	}

	public UsuarioRegistroDto() {
		super();
	}

}
