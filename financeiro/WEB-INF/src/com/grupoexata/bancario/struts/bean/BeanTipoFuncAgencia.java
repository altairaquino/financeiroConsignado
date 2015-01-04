package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;

public class BeanTipoFuncAgencia implements Serializable {
	
	private static final long serialVersionUID = -121780759016148606L;
	private String tfancodg;
	private String tfacdesc;
	private String tfactipo;
	private String tfalativ;
	
	public String getTfancodg() {
		return tfancodg;
	}
	public void setTfancodg(String tfancodg) {
		this.tfancodg = tfancodg;
	}
	public String getTfacdesc() {
		return tfacdesc;
	}
	public void setTfacdesc(String tfacdesc) {
		this.tfacdesc = tfacdesc;
	}
	public String getTfalativ() {
		return tfalativ;
	}
	public void setTfalativ(String tfalativ) {
		this.tfalativ = tfalativ;
	}
	public String getTfactipo() {
		return tfactipo;
	}
	public void setTfactipo(String tfactipo) {
		this.tfactipo = tfactipo;
	}
	
	

}
