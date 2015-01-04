package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;

public class BeanLog implements Serializable {
	
	private static final long serialVersionUID = -5395632489454744596L;
	
	private String lgncodg;
	private String lgncgen;
	private String lgcnmen;
	private String lgdentr;
	private String lgdsaid;
	private String lgcreqt;
	public String getLgncodg() {
		return lgncodg;
	}
	public void setLgncodg(String lgncodg) {
		this.lgncodg = lgncodg;
	}
	public String getLgncgen() {
		return lgncgen;
	}
	public void setLgncgen(String lgncgen) {
		this.lgncgen = lgncgen;
	}
	public String getLgcnmen() {
		return lgcnmen;
	}
	public void setLgcnmen(String lgcnmen) {
		this.lgcnmen = lgcnmen;
	}
	public String getLgdentr() {
		return lgdentr;
	}
	public void setLgdentr(String lgdentr) {
		this.lgdentr = lgdentr;
	}
	public String getLgdsaid() {
		return lgdsaid;
	}
	public void setLgdsaid(String lgdsaid) {
		this.lgdsaid = lgdsaid;
	}
	public String getLgcreqt() {
		return lgcreqt;
	}
	public void setLgcreqt(String lgcreqt) {
		this.lgcreqt = lgcreqt;
	}
	
	

}
