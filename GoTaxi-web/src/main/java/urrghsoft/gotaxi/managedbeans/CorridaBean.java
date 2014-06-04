package urrghsoft.gotaxi.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import urrghsoft.gotaxi.controller.CorridaEJB;
import urrghsoft.gotaxi.model.Corrida;

@ManagedBean
public class CorridaBean {

	private List<Corrida> corridasList;

	@Inject
	private CorridaEJB corridaEJB;

	public void atualizaCorridas() {
		getCorridasList();
	}

	/**
	 * @return the corridasList
	 */
	public List<Corrida> getCorridasList() {
		corridasList = corridaEJB.getByStatus("Pendente");
		return corridasList;
	}

	/**
	 * @param corridasList
	 *            the corridasList to set
	 */
	public void setCorridasList(List<Corrida> corridasList) {
		this.corridasList = corridasList;
	}

}
