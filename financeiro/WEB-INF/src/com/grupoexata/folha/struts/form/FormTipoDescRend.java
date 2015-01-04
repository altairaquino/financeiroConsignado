package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;

public class FormTipoDescRend extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String tdrncodg;
	private String tdrcdesc;

	public String getTdrncodg() {
		return this.tdrncodg;
	}

	public void setTdrncodg(String tdrncodg) {
		this.tdrncodg = tdrncodg;
	}

	public String getTdrcdesc() {
		return this.tdrcdesc;
	}

	public void setTdrcdesc(String tdrcdesc) {
		this.tdrcdesc = tdrcdesc;
	}
}
