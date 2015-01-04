package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;

public class FormTipoferiado extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String tfencodg;
	private String tfecdesc;

	public String getTfencodg() {
		return this.tfencodg;
	}

	public void setTfencodg(String tfencodg) {
		this.tfencodg = tfencodg;
	}

	public String getTfecdesc() {
		return this.tfecdesc;
	}

	public void setTfecdesc(String tfecdesc) {
		this.tfecdesc = tfecdesc;
	}
}
