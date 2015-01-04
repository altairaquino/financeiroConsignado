package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;

public class BeanGrupoEmail implements Serializable {
	
	private static final long serialVersionUID = 232419338284054031L;
	
	private String gemncodg;
	private String gemcdesc;
	private String gemlativ;
	
	public String getGemncodg() {
		return gemncodg;
	}
	public void setGemncodg(String gemncodg) {
		this.gemncodg = gemncodg;
	}
	public String getGemcdesc() {
		return gemcdesc;
	}
	public void setGemcdesc(String gemcdesc) {
		this.gemcdesc = gemcdesc;
	}
	public String getGemlativ() {
		return gemlativ;
	}
	public void setGemlativ(String gemlativ) {
		this.gemlativ = gemlativ;
	}
	
	

}
