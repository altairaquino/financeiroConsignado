package com.grupoexata.auditoria.bean;

import java.io.Serializable;

public class BeanGrupoauditoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -184351131807444026L;
	private String gpancodg;
	private String gpacoper;
	private String gpancggpa;
	private String gpacdesc;

	public String getGpancodg() {
		return gpancodg;
	}

	public void setGpancodg(String gpancodg) {
		this.gpancodg = gpancodg;
	}

	public String getGpacoper() {
		return gpacoper;
	}

	public void setGpacoper(String gpacoper) {
		this.gpacoper = gpacoper;
	}

	public String getGpancggpa() {
		return gpancggpa;
	}

	public void setGpancggpa(String gpancggpa) {
		this.gpancggpa = gpancggpa;
	}

	public String getGpacdesc() {
		return gpacdesc;
	}

	public void setGpacdesc(String gpacdesc) {
		this.gpacdesc = gpacdesc;
	}

}
