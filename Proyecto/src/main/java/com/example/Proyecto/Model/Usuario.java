package com.example.Proyecto.Model;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))

public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre")
	private String nombre;
	
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "password")
	private String password;
	
	@ManyToMany(fetch= FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable (
			
			name = "usuarios_roles",
			joinColumns =  @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
			
			)
	
	private Collection<Rol> roles;


	public Usuario() { }


	public Usuario(String nombre, String apellido, String email, String direccion, String password, Collection<Rol> roles) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
        this.password = password;
        this.roles = roles;
    }	


	public Usuario(Long id, String nombre, String email, String apellido, String direccion, String password, Collection<Rol> roles) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.apellido = apellido;
		this.password = password;
		this.roles = roles;
		this.direccion = direccion;
	}


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


	public Collection<Rol> getRoles() {
		return roles;
	}


	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	 
	
	
}

	