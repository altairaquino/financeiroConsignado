package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;

public class FormTipoAdmissao extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String tadncodg;
	private String tadcdesc;

	public String getTadncodg() {
		return this.tadncodg;
	}

	public void setTadncodg(String tadncodg) {
		this.tadncodg = tadncodg;
	}

	public String getTadcdesc() {
		return this.tadcdesc;
	}

	public void setTadcdesc(String tadcdesc) {
		this.tadcdesc = tadcdesc;
	}
}
