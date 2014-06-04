package urrghsoft.gotaxi.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import urrghsoft.gotaxi.model.Corrida;

@Stateless
public class CorridaEJB {

	@PersistenceContext(name = "primary")
	private EntityManager em;

	public Corrida find(Integer pk) {
		try {
			Corrida corrida = (Corrida) em.find(Corrida.class, pk);
			em.refresh(corrida);
			return corrida;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Corrida> findAll()
	{
		String query = "Select c from Corrida c";
		List<Corrida> corridas = em.createQuery(query).getResultList();
		return corridas;
	}

	public void persist(Corrida corrida) {
		try {
			em.persist(corrida);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void merge(Corrida corrida) {
		try {
			em.merge(corrida);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Corrida> getByStatus(String status) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Corrida> cq = cb.createQuery(Corrida.class);
		Root<Corrida> root = cq.from(Corrida.class);
		Predicate byStatus = cb.equal(root.get("status"), status);
		cq.where(byStatus);
		List<Corrida> corridas = em.createQuery(cq).getResultList();
		return corridas;
	}

}
