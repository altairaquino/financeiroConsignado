package com.grupoexata.financeiro.struts.bean;

import java.io.Serializable;

public class BeanOrigemCentro implements Serializable{
	
	private static final long serialVersionUID = 3109313923166045354L;
	
	private String orcncgcr;
	private String orccdccr;
	private String orcncgore;
	private String orccdcore;
	private String orclativ;
	
	public String getOrcncgcr() {
		return orcncgcr;
	}
	public void setOrcncgcr(String orcncgcr) {
		this.orcncgcr = orcncgcr;
	}
	public String getOrccdccr() {
		return orccdccr;
	}
	public void setOrccdccr(String orccdccr) {
		this.orccdccr = orccdccr;
	}
	public String getOrcncgore() {
		return orcncgore;
	}
	public void setOrcncgore(String orcncgore) {
		this.orcncgore = orcncgore;
	}
	public String getOrccdcore() {
		return orccdcore;
	}
	public void setOrccdcore(String orccdcore) {
		this.orccdcore = orccdcore;
	}
	public String getOrclativ() {
		return orclativ;
	}
	public void setOrclativ(String orclativ) {
		this.orclativ = orclativ;
	}
	
	
}
