package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;

public class BeanProduto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String pdncodg;
	private String pdncgbc;
	private String pdcdcbc;
	private String pdnnumr;
	private String pdcdesc;
	private String pdcabrv;
	private String pddcadt;
	
	public String getPdnnumr() {
		return pdnnumr;
	}
	public void setPdnnumr(String pdnnumr) {
		this.pdnnumr = pdnnumr;
	}
	public String getPdncodg() {
		return pdncodg;
	}
	public void setPdncodg(String pdncodg) {
		this.pdncodg = pdncodg;
	}
	public String getPdncgbc() {
		return pdncgbc;
	}
	public void setPdncgbc(String pdncgbc) {
		this.pdncgbc = pdncgbc;
	}
	public String getPdcdcbc() {
		return pdcdcbc;
	}
	public void setPdcdcbc(String pdcdcbc) {
		this.pdcdcbc = pdcdcbc;
	}
	public String getPdcdesc() {
		return pdcdesc;
	}
	public void setPdcdesc(String pdcdesc) {
		this.pdcdesc = pdcdesc;
	}
	public String getPdcabrv() {
		return pdcabrv;
	}
	public void setPdcabrv(String pdcabrv) {
		this.pdcabrv = pdcabrv;
	}
	public String getPddcadt() {
		return pddcadt;
	}
	public void setPddcadt(String pddcadt) {
		this.pddcadt = pddcadt;
	}
	
	

}
