package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;

public class FormGrandegrupo extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String ggrncodg;
	private String ggrccodg;
	private String ggrctitulo;

	public String getGgrncodg() {
		return this.ggrncodg;
	}

	public void setGgrncodg(String ggrncodg) {
		this.ggrncodg = ggrncodg;
	}

	public String getGgrccodg() {
		return this.ggrccodg;
	}

	public void setGgrccodg(String ggrccodg) {
		this.ggrccodg = ggrccodg;
	}

	public String getGgrctitulo() {
		return this.ggrctitulo;
	}

	public void setGgrctitulo(String ggrctitulo) {
		this.ggrctitulo = ggrctitulo;
	}
}
