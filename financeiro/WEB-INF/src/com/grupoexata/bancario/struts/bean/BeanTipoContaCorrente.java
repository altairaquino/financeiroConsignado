package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;

public class BeanTipoContaCorrente implements Serializable {
	
	private static final long serialVersionUID = -6910278196097537470L;
	
	private String tctncodg;
	private String tctcdesc;
	private String tctlativ;
	
	public String getTctncodg() {
		return tctncodg;
	}
	public void setTctncodg(String tctncodg) {
		this.tctncodg = tctncodg;
	}
	public String getTctcdesc() {
		return tctcdesc;
	}
	public void setTctcdesc(String tctcdesc) {
		this.tctcdesc = tctcdesc;
	}
	public String getTctlativ() {
		return tctlativ;
	}
	public void setTctlativ(String tctlativ) {
		this.tctlativ = tctlativ;
	}
	

}
