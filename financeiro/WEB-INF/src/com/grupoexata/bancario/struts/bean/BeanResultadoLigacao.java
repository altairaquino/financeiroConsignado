package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;

public class BeanResultadoLigacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String rlncodg;
	private String rlcdesc;
	
	public String getRlncodg() {
		return rlncodg;
	}
	public void setRlncodg(String rlncodg) {
		this.rlncodg = rlncodg;
	}
	public String getRlcdesc() {
		return rlcdesc;
	}
	public void setRlcdesc(String rlcdesc) {
		this.rlcdesc = rlcdesc;
	}
	
	

}
