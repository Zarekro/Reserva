package com.reserva.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.reserva.dao.ClienteFacade;
import com.reserva.entity.Cliente;

@RequestScoped
@ManagedBean
public class ClienteController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente c;
	private Cliente d;
	private ClienteFacade cf;
	public List<Cliente> listaCliente;
	String ban;
	int Id;

	@PostConstruct
	public void init() {
		c = new Cliente();
		cf = new ClienteFacade();
		mostrarCliente();
	}

	public void mostrarCliente() {
		this.listaCliente = new ArrayList<>();
		this.listaCliente = cf.mostrar();
	}

	public Cliente getC() {
		return c;
	}

	public void setC(Cliente c) {
		this.c = c;
	}

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public void agregar() {
		try {
			cf.create(c);
			mensaje("Datos Guardados Correctamente");
		} catch (Exception e) {
			mensaje("Error al agregar un nuevo Conductor");
		}
	}

	public void mensaje(String respuesta) {
		FacesMessage mensaje = new FacesMessage(respuesta);
		FacesContext.getCurrentInstance().addMessage(null, mensaje);
	}

	public void eliminar(int id) {
		System.out.println(id);
		try {
			c = cf.findId(id);
			cf.delete(this.c);
			
			System.out.println("Llego");

			System.out.println("ID " + id);
			System.out.println("Llego2");
			mensaje("ERROR al intentar borrar registro.");

		} catch (Exception e) {
			mensaje("Registro Eliminado");
			System.out.println("cccc" + c);
		}
	}

	public String modificar(int id) {
		c = cf.findId(id);
		Id = id;
		System.out.println(c.getIdCliente());
		System.out.println("Este es el id normal " + id + "y el ID" + Id);
		c.setIdCliente(id);
		return "EditCliente.xhtml";

	}

	public void actualizar() {
		try {
			cf.update(c);
			mensaje("Se actualizo correctamente");
		}

		catch (Exception e) {
			mensaje("Error al actualizar");
		}
	}

}
