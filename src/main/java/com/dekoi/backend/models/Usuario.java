package com.dekoi.backend.models;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nombre;

	private String apellido;

	@Column(unique = true, length = 50)
	private String email;

	private String direccion;

	private String ciudad;

	private long numeroTelefono;

	
	@JsonIgnoreProperties
	private String password;

	@Column(unique = true)
	private String rut;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Role> roles;

	@OneToMany(cascade = { (CascadeType.ALL) }, mappedBy = "usuario", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "usuario", "hibernateLazyInitializer", "handler" })
	private List<Carrito> carritos;

	@OneToMany(cascade = { (CascadeType.ALL) }, mappedBy = "usuario", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "usuario", "hibernateLazyInitializer", "handler" })
	private List<Compra> compras;

	@OneToMany(cascade = { (CascadeType.ALL) }, mappedBy = "usuario", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "usuario", "hibernateLazyInitializer", "handler" })
	private List<Reunion> reunion;

	public List<Carrito> getCarritos() {
		return carritos;
	}

	public void setCarritos(List<Carrito> carritos) {
		this.carritos = carritos;
	}

	public long getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(long numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
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

	public List<Reunion> getReunion() {
		return reunion;
	}

	public void setReunion(List<Reunion> reunion) {
		this.reunion = reunion;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5336873772147927052L;

}
