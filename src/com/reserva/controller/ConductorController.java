package com.reserva.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.reserva.dao.ConductorFacade;
import com.reserva.entity.Conductor;

@ManagedBean
@RequestScoped
public class ConductorController implements Serializable {

	private static final long serialVersionUID = 1L;
	int Id;

	ConductorFacade cf;
	Conductor c;
	public List<Conductor> listaConductores;

	@PostConstruct
	public void init() {
		c = new Conductor();
		cf = new ConductorFacade();
		mostrarConductores();
	}

	public Conductor getC() {
		return c;
	}

	public void setC(Conductor c) {
		this.c = c;
	}

	public List<Conductor> getListaConductores() {
		return listaConductores;
	}

	public void setListaConductores(List<Conductor> listaConductores) {
		this.listaConductores = listaConductores;
	}

	public void mostrarConductores() {
		this.listaConductores = new ArrayList<Conductor>();
		this.listaConductores = cf.mostrar();
	}

	public void agregar() {
		try {
			cf.create(c);
			mensaje("Datos Guardados Correctamente");
		} catch (Exception e) {
			mensaje("Error al agregar un nuevo Conductor");
		}
	}

	public String modificar(int id) {
		c = cf.findId(id);
		Id = id;
		System.out.println(c.getIdConductor());
		System.out.println("Este es el id normal "+ id + "y el ID" + Id);
		c.setIdConductor(id);
		return "EditConductor.xhtml";

	}

	public void actualizar(int id) {
		try {			
			System.out.println("ID " + id);
			c.setIdConductor(id);
			cf.update(c);
			mensaje("Se actualizo correctamente");
		}
		catch (Exception e) {
			mensaje("Error al actualizar");
		}
	}
	public void eliminar(int id) {
		System.out.println(id);
		try {
			c = cf.findId(id);
			cf.delete(this.c);
			c = cf.findId(id);
			mensaje("Registro Eliminado");
		} catch (Exception e) {
			mensaje("ERROR: No se puede eliminar el registro ya que esta vinculado a un registro de Viaje.");
		}
	}

	public void mensaje(String respuesta) {
		FacesMessage mensaje = new FacesMessage(respuesta);
		FacesContext.getCurrentInstance().addMessage(null, mensaje);
	}
}
