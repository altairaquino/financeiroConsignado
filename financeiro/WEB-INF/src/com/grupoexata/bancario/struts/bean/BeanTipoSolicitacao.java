package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;

public class BeanTipoSolicitacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String tisncodg;
	private String tiscdesc;
	private String tislativ;
	
	public String getTisncodg() {
		return tisncodg;
	}
	public void setTisncodg(String tisncodg) {
		this.tisncodg = tisncodg;
	}
	public String getTiscdesc() {
		return tiscdesc;
	}
	public void setTiscdesc(String tiscdesc) {
		this.tiscdesc = tiscdesc;
	}
	public String getTislativ() {
		return tislativ;
	}
	public void setTislativ(String tislativ) {
		this.tislativ = tislativ;
	}
	

	
}
