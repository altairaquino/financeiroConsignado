package com.grupoexata.bancario.struts.form;

import org.apache.struts.action.ActionForm;

public class FormUsuarioGrupo extends ActionForm {
	
	private static final long serialVersionUID = 1339409700590163543L;
	
	private String ugncodg;
	private String ugncgus;
	private String ugcnmen;
	private String ugncggr;
	private String ugcdcgr;
	
	public String getUgncodg() {
		return ugncodg;
	}
	public void setUgncodg(String ugncodg) {
		this.ugncodg = ugncodg;
	}
	public String getUgncgus() {
		return ugncgus;
	}
	public void setUgncgus(String ugncgus) {
		this.ugncgus = ugncgus;
	}
	public String getUgcnmen() {
		return ugcnmen;
	}
	public void setUgcnmen(String ugcnmen) {
		this.ugcnmen = ugcnmen;
	}
	public String getUgncggr() {
		return ugncggr;
	}
	public void setUgncggr(String ugncggr) {
		this.ugncggr = ugncggr;
	}
	public String getUgcdcgr() {
		return ugcdcgr;
	}
	public void setUgcdcgr(String ugcdcgr) {
		this.ugcdcgr = ugcdcgr;
	}
	
	

}
