package urrghsoft.gotaxi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "UNIDADE")
@XmlRootElement
public class Unidade {

	@Id
	private Integer id;

	@Column(name = "NUMERO")
	private String numero;

	@Column(name = "NOME_MOTORISTA")
	private String nomeMotorista;

	@Column(name = "CNH_MOTORISTA")
	private String cnhMotorista;

	@Column(name = "PLACA")
	private String placa;

	@Column(name = "MODELO")
	private String modelo;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the nomeMotorista
	 */
	public String getNomeMotorista() {
		return nomeMotorista;
	}

	/**
	 * @param nomeMotorista
	 *            the nomeMotorista to set
	 */
	public void setNomeMotorista(String nomeMotorista) {
		this.nomeMotorista = nomeMotorista;
	}

	/**
	 * @return the cnhMotorista
	 */
	public String getCnhMotorista() {
		return cnhMotorista;
	}

	/**
	 * @param cnhMotorista
	 *            the cnhMotorista to set
	 */
	public void setCnhMotorista(String cnhMotorista) {
		this.cnhMotorista = cnhMotorista;
	}

	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * @param placa
	 *            the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo
	 *            the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

}
