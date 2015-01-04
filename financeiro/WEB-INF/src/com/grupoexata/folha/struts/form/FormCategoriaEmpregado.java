package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;

public class FormCategoriaEmpregado extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String ctencodg;
	private String ctecdesc;

	public String getCtencodg() {
		return this.ctencodg;
	}

	public void setCtencodg(String ctencodg) {
		this.ctencodg = ctencodg;
	}

	public String getCtecdesc() {
		return this.ctecdesc;
	}

	public void setCtecdesc(String ctecdesc) {
		this.ctecdesc = ctecdesc;
	}
}
