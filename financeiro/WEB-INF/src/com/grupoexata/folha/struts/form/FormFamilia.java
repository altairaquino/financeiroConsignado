package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;

public class FormFamilia extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String famncodg;
	private String famccodg;
	private String famctitulo;
	private String famncgsgr;
	private String famccgsgr;

	public String getFamncodg() {
		return this.famncodg;
	}

	public void setFamncodg(String famncodg) {
		this.famncodg = famncodg;
	}

	public String getFamccodg() {
		return this.famccodg;
	}

	public void setFamccodg(String famccodg) {
		this.famccodg = famccodg;
	}

	public String getFamctitulo() {
		return this.famctitulo;
	}

	public void setFamctitulo(String famctitulo) {
		this.famctitulo = famctitulo;
	}

	public String getFamncgsgr() {
		return this.famncgsgr;
	}

	public void setFamncgsgr(String famncgsgr) {
		this.famncgsgr = famncgsgr;
	}

	public String getFamccgsgr() {
		return this.famccgsgr;
	}

	public void setFamccgsgr(String famccgsgr) {
		this.famccgsgr = famccgsgr;
	}
}
