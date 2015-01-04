package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;

public class BeanTipoAngariador implements Serializable {
	
	private static final long serialVersionUID = 6062353471415888956L;
	
	private String tnncodg;
	private String tncdesc;
	private String tnlativ;
	
	public String getTnncodg() {
		return tnncodg;
	}
	public void setTnncodg(String tnncodg) {
		this.tnncodg = tnncodg;
	}
	public String getTncdesc() {
		return tncdesc;
	}
	public void setTncdesc(String tncdesc) {
		this.tncdesc = tncdesc;
	}
	public String getTnlativ() {
		return tnlativ;
	}
	public void setTnlativ(String tnlativ) {
		this.tnlativ = tnlativ;
	}
	
	

}
