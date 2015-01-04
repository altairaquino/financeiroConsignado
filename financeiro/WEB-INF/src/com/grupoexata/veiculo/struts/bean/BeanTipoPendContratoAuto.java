package com.grupoexata.veiculo.struts.bean;

import java.io.Serializable;

public class BeanTipoPendContratoAuto implements Serializable {
	
	private static final long serialVersionUID = 3116153261596387454L;
	
	private String tpdncodg;
	private String tpdcdesc;
	private String tpdlativ;
	
	public String getTpdncodg() {
		return tpdncodg;
	}
	public void setTpdncodg(String tpdncodg) {
		this.tpdncodg = tpdncodg;
	}
	public String getTpdcdesc() {
		return tpdcdesc;
	}
	public void setTpdcdesc(String tpdcdesc) {
		this.tpdcdesc = tpdcdesc;
	}
	public String getTpdlativ() {
		return tpdlativ;
	}
	public void setTpdlativ(String tpdlativ) {
		this.tpdlativ = tpdlativ;
	}
	
	

}
