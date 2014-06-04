package urrghsoft.gotaxi.taxigoweb.entidades;

import java.io.Serializable;

public class Passageiro implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;

	private String celular;

    public Passageiro() {
    }

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return getEmail() + " (" + getCelular() + ")";
	}

}