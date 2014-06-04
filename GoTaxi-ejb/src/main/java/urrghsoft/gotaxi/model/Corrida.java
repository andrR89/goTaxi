package urrghsoft.gotaxi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "CORRIDA")
@XmlRootElement
public class Corrida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_SOLICITACAO")
	private Date horaSolicitacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_FINALIZACAO")
	private Date horaFinalizacao;

	@Column(name = "LAT_ORIGEM")
	private Double latOrigem;

	@Column(name = "LON_ORIGEM")
	private Double lonOrigem;

	private String qru;
	
	private String status;

	private String logradouro;

	@ManyToOne
	@JoinColumn(name = "PASSAGEIRO_EMAIL")
	private Passageiro passageiro;

	@ManyToOne
	@JoinColumn(name = "UNIDADE_ID")
	private Unidade unidade;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the horaSolicitacao
	 */
	public Date getHoraSolicitacao() {
		return horaSolicitacao;
	}

	/**
	 * @param horaSolicitacao
	 *            the horaSolicitacao to set
	 */
	public void setHoraSolicitacao(Date horaSolicitacao) {
		this.horaSolicitacao = horaSolicitacao;
	}

	/**
	 * @return the horaFinalizacao
	 */
	public Date getHoraFinalizacao() {
		return horaFinalizacao;
	}

	/**
	 * @param horaFinalizacao
	 *            the horaFinalizacao to set
	 */
	public void setHoraFinalizacao(Date horaFinalizacao) {
		this.horaFinalizacao = horaFinalizacao;
	}

	/**
	 * @return the latOrigem
	 */
	public Double getLatOrigem() {
		return latOrigem;
	}

	/**
	 * @param latOrigem
	 *            the latOrigem to set
	 */
	public void setLatOrigem(Double latOrigem) {
		this.latOrigem = latOrigem;
	}

	/**
	 * @return the lonOrigem
	 */
	public Double getLonOrigem() {
		return lonOrigem;
	}

	/**
	 * @param lonOrigem
	 *            the lonOrigem to set
	 */
	public void setLonOrigem(Double lonOrigem) {
		this.lonOrigem = lonOrigem;
	}

	/**
	 * @return the qru
	 */
	public String getQru() {
		return qru;
	}

	/**
	 * @param qru
	 *            the qru to set
	 */
	public void setQru(String qru) {
		this.qru = qru;
	}

	/**
	 * @return the logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/**
	 * @param logradouro
	 *            the logradouro to set
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * @return the passageiro
	 */
	public Passageiro getPassageiro() {
		return passageiro;
	}

	/**
	 * @param passageiro
	 *            the passageiro to set
	 */
	public void setPassageiro(Passageiro passageiro) {
		this.passageiro = passageiro;
	}

	/**
	 * @return the unidade
	 */
	public Unidade getUnidade() {
		return unidade;
	}

	/**
	 * @param unidade
	 *            the unidade to set
	 */
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
