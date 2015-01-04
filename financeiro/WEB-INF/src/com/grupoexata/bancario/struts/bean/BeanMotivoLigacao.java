package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;

public class BeanMotivoLigacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String mlncodg;
	private String mlcdesc;
	
	public String getMlncodg() {
		return mlncodg;
	}
	public void setMlncodg(String mlncodg) {
		this.mlncodg = mlncodg;
	}
	public String getMlcdesc() {
		return mlcdesc;
	}
	public void setMlcdesc(String mlcdesc) {
		this.mlcdesc = mlcdesc;
	}
	
			

}
