package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;

public class FormVinculo extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String vinncodg;
	private String vinccodg;
	private String vincdesc;

	public String getVinncodg() {
		return this.vinncodg;
	}

	public void setVinncodg(String vinncodg) {
		this.vinncodg = vinncodg;
	}

	public String getVinccodg() {
		return this.vinccodg;
	}

	public void setVinccodg(String vinccodg) {
		this.vinccodg = vinccodg;
	}

	public String getVincdesc() {
		return this.vincdesc;
	}

	public void setVincdesc(String vincdesc) {
		this.vincdesc = vincdesc;
	}
}
