package com.grupoexata.financeiro.struts.bean;

import java.io.Serializable;

public class BeanCentroCusto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String crncodg;
	private String crcdesc;
	private String crncgore;
	private String crcdcore;
	private String crlativ;	
	
	public String getCrncgore() {
		return crncgore;
	}
	public void setCrncgore(String crncgore) {
		this.crncgore = crncgore;
	}
	public String getCrcdcore() {
		return crcdcore;
	}
	public void setCrcdcore(String crcdcore) {
		this.crcdcore = crcdcore;
	}
	public String getCrncodg() {
		return crncodg;
	}
	public void setCrncodg(String crncodg) {
		this.crncodg = crncodg;
	}
	public String getCrcdesc() {
		return crcdesc;
	}
	public void setCrcdesc(String crcdesc) {
		this.crcdesc = crcdesc;
	}
	public String getCrlativ() {
		return crlativ;
	}
	public void setCrlativ(String crlativ) {
		this.crlativ = crlativ;
	}	

}
