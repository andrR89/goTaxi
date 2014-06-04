package urrghsoft.gotaxi.model;

import java.io.Serializable;
import java.util.Calendar;


/**
 * The persistent class for the Corrida database table.
 * 
 */
public class Corrida implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private Calendar horaSolicitacao;

	private Calendar horaFinalizacao;
    
	private double latOrigem;

	private double lonOrigem;

	private String qru;

	private String status;

	private Passageiro passageiro;

	private Unidade unidade;
	
	private String logradouro;

    public Corrida() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getHoraSolicitacao() {
		return this.horaSolicitacao;
	}

	public void setHoraSolicitacao(Calendar horaSolicitacao) {
		this.horaSolicitacao = horaSolicitacao;
	}
	

	public Calendar getHoraFinalizacao() {
		return horaFinalizacao;
	}

	public void setHoraFinalizacao(Calendar horaFinalizacao) {
		this.horaFinalizacao = horaFinalizacao;
	}

	public double getLatOrigem() {
		return this.latOrigem;
	}

	public void setLatOrigem(double latOrigem) {
		this.latOrigem = latOrigem;
	}

	public double getLonOrigem() {
		return this.lonOrigem;
	}

	public void setLonOrigem(double lonOrigem) {
		this.lonOrigem = lonOrigem;
	}

	public String getQru() {
		return this.qru;
	}

	public void setQru(String qru) {
		this.qru = qru;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Passageiro getPassageiro() {
		return this.passageiro;
	}

	public void setPassageiro(Passageiro passageiro) {
		this.passageiro = passageiro;
	}
	
	public Unidade getUnidade() {
		return this.unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	
	
}