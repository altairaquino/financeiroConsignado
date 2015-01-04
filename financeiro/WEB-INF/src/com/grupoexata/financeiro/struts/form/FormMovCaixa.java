package com.grupoexata.financeiro.struts.form;

import org.apache.struts.action.ActionForm;

public class FormMovCaixa extends ActionForm{
	
	private static final long serialVersionUID = -2002495908147396501L;
	
	private String movncodg;
	private String movccodg;
	private String movddata;
	private String movdvenc;
	private String movncggpc = "1";
	private String movcdcgpc;
	private String movctpgpc;
	private String movncgcog;
	private String movcdccog;
	private String movncgore;
	private String movcdcore;
	private String movnflqu;
	private String movcflqu;
	private String movnqtpc;
	private String movnnrpc;
	private String movnfili;
	private String movcfili;
	private String movcdesc;
	private String movcdocm;
	private String movyvalr;
	private String movncadt;
	private String movccadt;
	private String movdcadt;
	private String movnforn;
	private String movcforn;
	private String movncoco;
	private String movccoco;
	private String movlpend = "F";
	private String movltipo;
	private String movlativ;
	private String [] movncecu;
	
	public String getMovccodg() {
		return movccodg;
	}
	public void setMovccodg(String movccodg) {
		this.movccodg = movccodg;
	}
	public String getMovlpend() {
		return movlpend;
	}
	public void setMovlpend(String movlpend) {
		this.movlpend = movlpend;
	}
	public String getMovctpgpc() {
		return movctpgpc;
	}
	public void setMovctpgpc(String movctpgpc) {
		this.movctpgpc = movctpgpc;
	}
	public String getMovnflqu() {
		return movnflqu;
	}
	public void setMovnflqu(String movnflqu) {
		this.movnflqu = movnflqu;
	}
	public String getMovcflqu() {
		return movcflqu;
	}
	public void setMovcflqu(String movcflqu) {
		this.movcflqu = movcflqu;
	}
	public String getMovncggpc() {
		return movncggpc;
	}
	public void setMovncggpc(String movncggpc) {
		this.movncggpc = movncggpc;
	}
	public String getMovcdcgpc() {
		return movcdcgpc;
	}
	public void setMovcdcgpc(String movcdcgpc) {
		this.movcdcgpc = movcdcgpc;
	}
	public String getMovncgcog() {
		return movncgcog;
	}
	public void setMovncgcog(String movncgcog) {
		this.movncgcog = movncgcog;
	}
	public String getMovcdccog() {
		return movcdccog;
	}
	public void setMovcdccog(String movcdccog) {
		this.movcdccog = movcdccog;
	}
	public String getMovncgore() {
		return movncgore;
	}
	public void setMovncgore(String movncgore) {
		this.movncgore = movncgore;
	}
	public String getMovcdcore() {
		return movcdcore;
	}
	public void setMovcdcore(String movcdcore) {
		this.movcdcore = movcdcore;
	}
	public String getMovnqtpc() {
		return movnqtpc;
	}
	public void setMovnqtpc(String movnqtpc) {
		this.movnqtpc = movnqtpc;
	}
	public String getMovnnrpc() {
		return movnnrpc;
	}
	public void setMovnnrpc(String movnnrpc) {
		this.movnnrpc = movnnrpc;
	}
	public String getMovnfili() {
		return movnfili;
	}
	public void setMovnfili(String movnfili) {
		this.movnfili = movnfili;
	}
	public String getMovcfili() {
		return movcfili;
	}
	public void setMovcfili(String movcfili) {
		this.movcfili = movcfili;
	}
	public String getMovncadt() {
		return movncadt;
	}
	public void setMovncadt(String movncadt) {
		this.movncadt = movncadt;
	}
	public String getMovccadt() {
		return movccadt;
	}
	public void setMovccadt(String movccadt) {
		this.movccadt = movccadt;
	}
	public String getMovdcadt() {
		return movdcadt;
	}
	public void setMovdcadt(String movdcadt) {
		this.movdcadt = movdcadt;
	}
	public String getMovltipo() {
		return movltipo;
	}
	public void setMovltipo(String movltipo) {
		this.movltipo = movltipo;
	}
	public String getMovnforn() {
		return movnforn;
	}
	public void setMovnforn(String movnforn) {
		this.movnforn = movnforn;
	}
	public String getMovcforn() {
		return movcforn;
	}
	public void setMovcforn(String movcforn) {
		this.movcforn = movcforn;
	}
	public String getMovncoco() {
		return movncoco;
	}
	public void setMovncoco(String movncoco) {
		this.movncoco = movncoco;
	}
	public String getMovccoco() {
		return movccoco;
	}
	public void setMovccoco(String movccoco) {
		this.movccoco = movccoco;
	}
	public String getMovdvenc() {
		return movdvenc;
	}
	public void setMovdvenc(String movdvenc) {
		this.movdvenc = movdvenc;
	}
	
	
	
	public String[] getMovncecu() {
		return movncecu;
	}
	public void setMovncecu(String[] movncecu) {
		this.movncecu = movncecu;
	}
	public String getMovncodg() {
		return movncodg;
	}
	public void setMovncodg(String movncodg) {
		this.movncodg = movncodg;
	}
	
	public String getMovddata() {
		return movddata;
	}
	public void setMovddata(String movddata) {
		this.movddata = movddata;
	}
	
	public String getMovcdesc() {
		return movcdesc;
	}
	public void setMovcdesc(String movcdesc) {
		this.movcdesc = movcdesc;
	}
	public String getMovcdocm() {
		return movcdocm;
	}
	public void setMovcdocm(String movcdocm) {
		this.movcdocm = movcdocm;
	}
	public String getMovyvalr() {
		return movyvalr;
	}
	public void setMovyvalr(String movyvalr) {
		this.movyvalr = movyvalr;
	}	
	public String getMovlativ() {
		return movlativ;
	}
	public void setMovlativ(String movlativ) {
		this.movlativ = movlativ;
	}	
	
	

}
