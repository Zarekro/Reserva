package com.reserva.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import com.reserva.entity.Conductor;
import com.reserva.utils.JPAUtils;

public class ConductorFacade extends AbstractFacade<Conductor> implements Serializable {

	private static final long serialVersionUID = 1L;
	private EntityManager em;
	
	public ConductorFacade() {
		super(Conductor.class);
		getEntityManager();
	}

	@Override
	protected EntityManager getEntityManager() {
		if (em == null) {
			em = JPAUtils.getEntityManagerFactory().createEntityManager();
		}
		return em;
	}
	

}
