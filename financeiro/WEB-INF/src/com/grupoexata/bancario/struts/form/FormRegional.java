package com.grupoexata.bancario.struts.form;

import org.apache.struts.action.ActionForm;

public class FormRegional extends ActionForm {
	
	private static final long serialVersionUID = 1835079520181799232L;
	
	private String rgncodg;
	private String rgcdesc;
	private String rglativ;	
	
	public String getRglativ() {
		return rglativ;
	}
	public void setRglativ(String rglativ) {
		this.rglativ = rglativ;
	}
	public String getRgncodg() {
		return rgncodg;
	}
	public void setRgncodg(String rgncodg) {
		this.rgncodg = rgncodg;
	}
	public String getRgcdesc() {
		return rgcdesc;
	}
	public void setRgcdesc(String rgcdesc) {
		this.rgcdesc = rgcdesc;
	}	

}
