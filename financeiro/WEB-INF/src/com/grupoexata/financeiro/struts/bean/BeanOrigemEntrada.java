package com.grupoexata.financeiro.struts.bean;

import java.io.Serializable;

public class BeanOrigemEntrada implements Serializable {
	
	private static final long serialVersionUID = 5957881085404169714L;
	
	private String orencodg;
	private String orecdesc;
	private String orelativ;
	
	public String getOrencodg() {
		return orencodg;
	}
	public void setOrencodg(String orencodg) {
		this.orencodg = orencodg;
	}
	public String getOrecdesc() {
		return orecdesc;
	}
	public void setOrecdesc(String orecdesc) {
		this.orecdesc = orecdesc;
	}
	public String getOrelativ() {
		return orelativ;
	}
	public void setOrelativ(String orelativ) {
		this.orelativ = orelativ;
	}
	
	

}
