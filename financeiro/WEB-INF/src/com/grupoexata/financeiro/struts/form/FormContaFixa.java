package com.grupoexata.financeiro.struts.form;

import org.apache.struts.action.ActionForm;

public class FormContaFixa extends ActionForm {
	
	private static final long serialVersionUID = -6657613435898009178L;
	
	private String cofncodg;
	private String cofncggpc = "1";	
	private String cofcdcgpc;
	private String cofncont;
	private String cofccont;
	private String cofnfili;
	private String cofcfili;
	private String cofndia;
	private String cofntipo = "2";
	private String cofcdesc;
	private String cofcdocm;
	private String cofnforn;
	private String cofcforn;
	private String cofncoco;
	private String cofccoco;
	private String cofyvalr;
	private String coflativ;	
	
	public String getCofncggpc() {
		return cofncggpc;
	}
	public void setCofncggpc(String cofncggpc) {
		this.cofncggpc = cofncggpc;
	}
	public String getCofcdcgpc() {
		return cofcdcgpc;
	}
	public void setCofcdcgpc(String cofcdcgpc) {
		this.cofcdcgpc = cofcdcgpc;
	}
	public String getCofyvalr() {
		return cofyvalr;
	}
	public void setCofyvalr(String cofyvalr) {
		this.cofyvalr = cofyvalr;
	}
	public String getCofnfili() {
		return cofnfili;
	}
	public void setCofnfili(String cofnfili) {
		this.cofnfili = cofnfili;
	}
	public String getCofcfili() {
		return cofcfili;
	}
	public void setCofcfili(String cofcfili) {
		this.cofcfili = cofcfili;
	}
	public String getCofnforn() {
		return cofnforn;
	}
	public void setCofnforn(String cofnforn) {
		this.cofnforn = cofnforn;
	}
	public String getCofcforn() {
		return cofcforn;
	}
	public void setCofcforn(String cofcforn) {
		this.cofcforn = cofcforn;
	}
	public String getCofncoco() {
		return cofncoco;
	}
	public void setCofncoco(String cofncoco) {
		this.cofncoco = cofncoco;
	}
	public String getCofccoco() {
		return cofccoco;
	}
	public void setCofccoco(String cofccoco) {
		this.cofccoco = cofccoco;
	}
	public String getCofndia() {
		return cofndia;
	}
	public void setCofndia(String cofndia) {
		this.cofndia = cofndia;
	}
	public String getCofncodg() {
		return cofncodg;
	}
	public void setCofncodg(String cofncodg) {
		this.cofncodg = cofncodg;
	}
	public String getCofncont() {
		return cofncont;
	}
	public void setCofncont(String cofncont) {
		this.cofncont = cofncont;
	}
	public String getCofccont() {
		return cofccont;
	}
	public void setCofccont(String cofccont) {
		this.cofccont = cofccont;
	}
	public String getCofntipo() {
		return cofntipo;
	}
	public void setCofntipo(String cofntipo) {
		this.cofntipo = cofntipo;
	}
	public String getCofcdesc() {
		return cofcdesc;
	}
	public void setCofcdesc(String cofcdesc) {
		this.cofcdesc = cofcdesc;
	}
	public String getCofcdocm() {
		return cofcdocm;
	}
	public void setCofcdocm(String cofcdocm) {
		this.cofcdocm = cofcdocm;
	}
	public String getCoflativ() {
		return coflativ;
	}
	public void setCoflativ(String coflativ) {
		this.coflativ = coflativ;
	}

}
