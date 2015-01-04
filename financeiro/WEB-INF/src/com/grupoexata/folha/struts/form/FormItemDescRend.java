package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;

public class FormItemDescRend extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idrncodg;
	private String idrncgder;
	private String idrncgtdr;
	private String idrctipo;

	public String getIdrncodg() {
		return this.idrncodg;
	}

	public void setIdrncodg(String idrncodg) {
		this.idrncodg = idrncodg;
	}

	public String getIdrncgder() {
		return this.idrncgder;
	}

	public void setIdrncgder(String idrncgder) {
		this.idrncgder = idrncgder;
	}

	public String getIdrncgtdr() {
		return this.idrncgtdr;
	}

	public void setIdrncgtdr(String idrncgtdr) {
		this.idrncgtdr = idrncgtdr;
	}

	public String getIdrctipo() {
		return this.idrctipo;
	}

	public void setIdrctipo(String idrctipo) {
		this.idrctipo = idrctipo;
	}
}
