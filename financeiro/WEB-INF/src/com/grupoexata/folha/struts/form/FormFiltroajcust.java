package com.grupoexata.folha.struts.form;

import org.apache.struts.action.ActionForm;

public class FormFiltroajcust  extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4433766093794554775L;
	private String facncodg;
	private String facncgfil;
	private String facncgajc;
	public String getFacncodg() {
		return facncodg;
	}
	public void setFacncodg(String facncodg) {
		this.facncodg = facncodg;
	}
	public String getFacncgfil() {
		return facncgfil;
	}
	public void setFacncgfil(String facncgfil) {
		this.facncgfil = facncgfil;
	}
	public String getFacncgajc() {
		return facncgajc;
	}
	public void setFacncgajc(String facncgajc) {
		this.facncgajc = facncgajc;
	}

}
