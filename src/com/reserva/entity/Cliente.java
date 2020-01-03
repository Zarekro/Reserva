package com.reserva.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idCliente;
	private String apellido;
	private String dirección;
	private String dui;
	private String email;
	private String genero;
	private String nombre;
	private String telefono;
	private List<Viaje> viajes;

	public Cliente() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cliente")
	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}


	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getDirección() {
		return this.dirección;
	}

	public void setDirección(String dirección) {
		this.dirección = dirección;
	}


	public String getDui() {
		return this.dui;
	}

	public void setDui(String dui) {
		this.dui = dui;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	//bi-directional many-to-one association to Viaje
	@OneToMany(mappedBy="cliente")
	public List<Viaje> getViajes() {
		return this.viajes;
	}

	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}

	public Viaje addViaje(Viaje viaje) {
		getViajes().add(viaje);
		viaje.setCliente(this);

		return viaje;
	}

	public Viaje removeViaje(Viaje viaje) {
		getViajes().remove(viaje);
		viaje.setCliente(null);

		return viaje;
	}

}