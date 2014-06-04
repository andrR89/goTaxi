package urrghsoft.gotaxi.model;

import java.io.Serializable;


/**
 * The persistent class for the Unidade database table.
 * 
 */
public class Unidade implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String cnhMotorista;

	private String modelo;

	private String nomeMotorista;
	
	private String numero;

	private String placa;

    public Unidade() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCnhMotorista() {
		return this.cnhMotorista;
	}

	public void setCnhMotorista(String cnhMotorista) {
		this.cnhMotorista = cnhMotorista;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNomeMotorista() {
		return this.nomeMotorista;
	}

	public void setNomeMotorista(String nomeMotorista) {
		this.nomeMotorista = nomeMotorista;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
}