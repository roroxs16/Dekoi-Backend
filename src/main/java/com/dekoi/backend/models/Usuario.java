package com.dekoi.backend.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nombre;
	
	private String apellido;
	
	@Column(unique=true, length=50)
	private String email;
	
	private String direccion;
	
	private String ciudad;
	
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	private String password;
	
	private String rut;
	
	
	@ManyToMany(fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	private List<Role> roles;
	

	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name= "carrito_id", referencedColumnName = "id")
	private Carrito carrito;
	
	


	public Carrito getCarrito() {
		return carrito;
	}




	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}




	public String getDireccion() {
		return direccion;
	}




	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getRut() {
		return rut;
	}




	public void setRut(String rut) {
		this.rut = rut;
	}




	public List<Role> getRoles() {
		return roles;
	}




	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}




	public Usuario() {
		
	}




	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}






	public String getCiudad() {
		return ciudad;
	}



	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}



	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}



	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}





	/**
	 * 
	 */
	private static final long serialVersionUID = -5336873772147927052L;

}
