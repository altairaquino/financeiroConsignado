package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;

public class FormSubgrupo extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String sgrncodg;
	private String sgrccodg;
	private String sgrctitulo;
	private String sgrncgsgp;
	private String sgrccgsgp;

	public String getSgrncodg() {
		return this.sgrncodg;
	}

	public void setSgrncodg(String sgrncodg) {
		this.sgrncodg = sgrncodg;
	}

	public String getSgrccodg() {
		return this.sgrccodg;
	}

	public void setSgrccodg(String sgrccodg) {
		this.sgrccodg = sgrccodg;
	}

	public String getSgrctitulo() {
		return this.sgrctitulo;
	}

	public void setSgrctitulo(String sgrctitulo) {
		this.sgrctitulo = sgrctitulo;
	}

	public String getSgrncgsgp() {
		return this.sgrncgsgp;
	}

	public void setSgrncgsgp(String sgrncgsgp) {
		this.sgrncgsgp = sgrncgsgp;
	}

	public String getSgrccgsgp() {
		return this.sgrccgsgp;
	}

	public void setSgrccgsgp(String sgrccgsgp) {
		this.sgrccgsgp = sgrccgsgp;
	}
}
