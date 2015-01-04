package com.grupoexata.auditoria.bean;

import java.io.Serializable;

public class BeanContratoauditoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5168989964284645787L;
	private String ctancodg;
	private String ctancgct;
	private String ctancgfau;
	private String ctdcadt;
	
	

	public BeanContratoauditoria() {
	}

	public BeanContratoauditoria(String ctancgct, String ctancgfau) {
		this.ctancgct = ctancgct;
		this.ctancgfau = ctancgfau;
	}

	public String getCtancodg() {
		return ctancodg;
	}

	public void setCtancodg(String ctancodg) {
		this.ctancodg = ctancodg;
	}

	public String getCtancgct() {
		return ctancgct;
	}

	public void setCtancgct(String ctancgct) {
		this.ctancgct = ctancgct;
	}

	public String getCtancgfau() {
		return ctancgfau;
	}

	public void setCtancgfau(String ctancgfau) {
		this.ctancgfau = ctancgfau;
	}

	public String getCtdcadt() {
		return ctdcadt;
	}

	public void setCtdcadt(String ctdcadt) {
		this.ctdcadt = ctdcadt;
	}

}
