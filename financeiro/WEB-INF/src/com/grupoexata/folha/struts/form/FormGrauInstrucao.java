package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;

public class FormGrauInstrucao extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String ginncodg;
	private String gincdesc;

	public String getGinncodg() {
		return this.ginncodg;
	}

	public void setGinncodg(String ginncodg) {
		this.ginncodg = ginncodg;
	}

	public String getGincdesc() {
		return this.gincdesc;
	}

	public void setGincdesc(String gincdesc) {
		this.gincdesc = gincdesc;
	}
}
