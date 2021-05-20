package co.empresa.Crud.modelo;

import java.io.Serializable;

import lombok.Data;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
public class Usuario implements Serializable {
	
	private Integer id;
	
	private String nombre;
	
	private String email;
	
	private String pais;
	
	public Usuario() {
		super();
	}
	
	
	public Usuario (String nombre, String email, String pais) {
		this.nombre = nombre;
		this.email = email;
		this.pais = pais;
	}
	
	public Usuario ( int id ,String nombre, String email, String pais) {
		this.id=id;
		this.nombre = nombre;
		this.email = email;
		this.pais = pais;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	
	
	
}

