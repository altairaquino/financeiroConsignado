package com.grupoexata.bancario.struts.form;

import org.apache.struts.action.ActionForm;

public class FormGrupo extends ActionForm {
	
	
	private static final long serialVersionUID = 8625863243526367933L;
	
	private String grncodg;
	private String grcdesc;
	
	public String getGrncodg() {
		return grncodg;
	}
	public void setGrncodg(String grncodg) {
		this.grncodg = grncodg;
	}
	public String getGrcdesc() {
		return grcdesc;
	}
	public void setGrcdesc(String grcdesc) {
		this.grcdesc = grcdesc;
	}

}
