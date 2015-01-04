package com.grupoexata.bancario.struts.form;

import org.apache.struts.action.ActionForm;

public class FormBordero extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	private String borncodg;
	private String borcnumr;
	private String borcnrpt;
	private String bordbanc;
	private String borncgen;
	private String borcnmen;
	private String bordcadt;
	private String borcobsv;	
	
	public String getBorcobsv() {
		return borcobsv;
	}
	public void setBorcobsv(String borcobsv) {
		this.borcobsv = borcobsv;
	}
	public String getBorncodg() {
		return borncodg;
	}
	public void setBorncodg(String borncodg) {
		this.borncodg = borncodg;
	}
	public String getBorcnumr() {
		return borcnumr;
	}
	public void setBorcnumr(String borcnumr) {
		this.borcnumr = borcnumr;
	}
	public String getBorcnrpt() {
		return borcnrpt;
	}
	public void setBorcnrpt(String borcnrpt) {
		this.borcnrpt = borcnrpt;
	}
	public String getBordbanc() {
		return bordbanc;
	}
	public void setBordbanc(String bordbanc) {
		this.bordbanc = bordbanc;
	}
	public String getBorncgen() {
		return borncgen;
	}
	public void setBorncgen(String borncgen) {
		this.borncgen = borncgen;
	}
	public String getBorcnmen() {
		return borcnmen;
	}
	public void setBorcnmen(String borcnmen) {
		this.borcnmen = borcnmen;
	}
	public String getBordcadt() {
		return bordcadt;
	}
	public void setBordcadt(String bordcadt) {
		this.bordcadt = bordcadt;
	}
	
	

}
