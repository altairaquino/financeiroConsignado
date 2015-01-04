package com.grupoexata.financeiro.struts.bean;

import java.io.Serializable;

public class BeanGrupoConta implements Serializable {
	
	private static final long serialVersionUID = 3489046495684174349L;
	
	private String gpcncodg;
	private String gpccdesc;
	private String gpclativ;
	
	public String getGpcncodg() {
		return gpcncodg;
	}
	public void setGpcncodg(String gpcncodg) {
		this.gpcncodg = gpcncodg;
	}
	public String getGpccdesc() {
		return gpccdesc;
	}
	public void setGpccdesc(String gpccdesc) {
		this.gpccdesc = gpccdesc;
	}
	public String getGpclativ() {
		return gpclativ;
	}
	public void setGpclativ(String gpclativ) {
		this.gpclativ = gpclativ;
	}
	
	

}
