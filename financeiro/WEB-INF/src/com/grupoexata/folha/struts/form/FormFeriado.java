package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;

public class FormFeriado extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String ferncodg;
	private String fercdesc;
	private String ferndia;
	private String fernmes;
	private String ferlsem;
	private String ferndsem;
	private String ferncgtfe;

	public String getFerncodg() {
		return this.ferncodg;
	}

	public void setFerncodg(String ferncodg) {
		this.ferncodg = ferncodg;
	}

	public String getFercdesc() {
		return this.fercdesc;
	}

	public void setFercdesc(String fercdesc) {
		this.fercdesc = fercdesc;
	}

	public String getFerndia() {
		return this.ferndia;
	}

	public void setFerndia(String ferndia) {
		this.ferndia = ferndia;
	}

	public String getFernmes() {
		return this.fernmes;
	}

	public void setFernmes(String fernmes) {
		this.fernmes = fernmes;
	}

	public String getFerlsem() {
		return this.ferlsem;
	}

	public void setFerlsem(String ferlsem) {
		this.ferlsem = ferlsem;
	}

	public String getFerndsem() {
		return this.ferndsem;
	}

	public void setFerndsem(String ferndsem) {
		this.ferndsem = ferndsem;
	}

	public String getFerncgtfe() {
		return this.ferncgtfe;
	}

	public void setFerncgtfe(String ferncgtfe) {
		this.ferncgtfe = ferncgtfe;
	}
}
