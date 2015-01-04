package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;

public class BeanSetor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String setncodg;
	private String setcdesc;
	private String setlativ;
	
	public String getSetncodg() {
		return setncodg;
	}
	public void setSetncodg(String setncodg) {
		this.setncodg = setncodg;
	}
	public String getSetcdesc() {
		return setcdesc;
	}
	public void setSetcdesc(String setcdesc) {
		this.setcdesc = setcdesc;
	}
	public String getSetlativ() {
		return setlativ;
	}
	public void setSetlativ(String setlativ) {
		this.setlativ = setlativ;
	}
	
	

}
