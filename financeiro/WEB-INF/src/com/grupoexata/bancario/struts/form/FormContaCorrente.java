package com.grupoexata.bancario.struts.form;

import org.apache.struts.action.ActionForm;

public class FormContaCorrente extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String concodg;
	private String concgen;
	private String cocnmen;
	private String cocdocm;
	private String contcon;
	private String coctcon;
	private String concgbc = "237";
	private String cocdcbc;
	private String cocagen;
	private String cocnumr;
	private String coctitu;
	private String cocdesc;
	private String colprin;
	
	
	public String getColprin() {
		return colprin;
	}
	public void setColprin(String colprin) {
		this.colprin = colprin;
	}
	public String getCocdesc() {
		return cocdesc;
	}
	public void setCocdesc(String cocdesc) {
		this.cocdesc = cocdesc;
	}
	public String getConcodg() {
		return concodg;
	}
	public void setConcodg(String concodg) {
		this.concodg = concodg;
	}
	public String getContcon() {
		return contcon;
	}
	public void setContcon(String contcon) {
		this.contcon = contcon;
	}
	public String getCoctcon() {
		return coctcon;
	}
	public void setCoctcon(String coctcon) {
		this.coctcon = coctcon;
	}
	public String getConcgen() {
		return concgen;
	}
	public void setConcgen(String concgen) {
		this.concgen = concgen;
	}
	public String getCocnmen() {
		return cocnmen;
	}
	public void setCocnmen(String cocnmen) {
		this.cocnmen = cocnmen;
	}
	public String getCocdocm() {
		return cocdocm;
	}
	public void setCocdocm(String cocdocm) {
		this.cocdocm = cocdocm;
	}
	public String getConcgbc() {
		return concgbc;
	}
	public void setConcgbc(String concgbc) {
		this.concgbc = concgbc;
	}
	public String getCocdcbc() {
		return cocdcbc;
	}
	public void setCocdcbc(String cocdcbc) {
		this.cocdcbc = cocdcbc;
	}
	public String getCocagen() {
		return cocagen;
	}
	public void setCocagen(String cocagen) {
		this.cocagen = cocagen;
	}
	public String getCocnumr() {
		return cocnumr;
	}
	public void setCocnumr(String cocnumr) {
		this.cocnumr = cocnumr;
	}
	public String getCoctitu() {
		return coctitu;
	}
	public void setCoctitu(String coctitu) {
		this.coctitu = coctitu;
	}
}
