package com.reserva.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractFacade<T> {
	private Class<T> entityClass;

	protected abstract EntityManager getEntityManager();

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void create(T entity) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() == null && em.isOpen()) {
				em.getTransaction().rollback();
			}
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}

	public void delete(T entity) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			if (em.getTransaction() == null && em.isOpen()) {
				em.close();
			}
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}

	public void update(T entity) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() == null && em.isOpen()) {
				em.getTransaction().rollback();
			}
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}

	public List<T> mostrar() {
		CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
		return getEntityManager().createQuery(cq).getResultList();
	}

	public T findId(Object id) {
		return getEntityManager().find(entityClass, id);
	}

	public T aux(Object id) {
		return getEntityManager().find(entityClass, id);
	}
}
