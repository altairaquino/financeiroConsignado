package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;

public class BeanTipoContrato implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String tcncodg;
	private String tccdesc;
	
	public String getTcncodg() {
		return tcncodg;
	}
	public void setTcncodg(String tcncodg) {
		this.tcncodg = tcncodg;
	}
	public String getTccdesc() {
		return tccdesc;
	}
	public void setTccdesc(String tccdesc) {
		this.tccdesc = tccdesc;
	}
	
		

}
