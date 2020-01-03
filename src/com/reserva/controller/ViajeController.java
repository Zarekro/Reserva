package com.reserva.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.reserva.dao.ViajeFacade;
import com.reserva.entity.Viaje;

@ManagedBean
@RequestScoped
public class ViajeController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	ViajeFacade cf;
	Viaje c;


	public List<Viaje> listaViajes;
	
	@PostConstruct
	public void init() {
		c = new Viaje();
		cf = new ViajeFacade();
		mostrarViajes();
	}
	
	public void mostrarViajes() {
		this.listaViajes = new ArrayList<Viaje>();
		this.listaViajes = cf.mostrar();
	}
	
	public Viaje getC() {
		return c;
	}

	public void setC(Viaje c) {
		this.c = c;
	}

	public List<Viaje> getListaViajes() {
		return listaViajes;
	}

	public void setListaViajes(List<Viaje> listaViajes) {
		this.listaViajes = listaViajes;
	}
	
	public void agregar(){
		try {
			cf.create(c);
			mensaje("Viaje Registrado");
		} catch (Exception e) {
			mensaje("Ocurrio un problema");
		}
	} 
	
	public void eliminar(int id) {
		System.out.println(id);
		try {
			c = cf.findId(id);
			cf.delete(this.c);
			mensaje("Registro Eliminado");
			System.out.println("Si entra");
		} catch (Exception e) {
			mensaje("Error al intentar Eliminar registro");

			System.out.println("No");
		}
	}
	
	public void mensaje(String respuesta) {
		FacesMessage mensaje = new FacesMessage(respuesta);
		FacesContext.getCurrentInstance().addMessage(null, mensaje);
	}
	
	public String modificar(int id) {
		c = cf.findId(id);
		c.setIdViaje(id);
		return "EditViaje.xhtml";

	}

	public void actualizar(int id) {
		try {
//			c.getIdViaje();
			cf.update(c);
			mensaje("Se actualizo correctamente");
		}

		catch (Exception e) {
			mensaje("Error al actualizar");
		}
	}
}
