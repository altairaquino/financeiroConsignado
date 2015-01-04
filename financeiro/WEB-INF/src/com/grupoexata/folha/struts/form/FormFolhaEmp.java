package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;

public class FormFolhaEmp extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String foencodg;
	private String foencgemp;
	private String foencgfol;
	private String foeyliqd;

	public String getFoencodg() {
		return this.foencodg;
	}

	public void setFoencodg(String foencodg) {
		this.foencodg = foencodg;
	}

	public String getFoencgemp() {
		return this.foencgemp;
	}

	public void setFoencgemp(String foencgemp) {
		this.foencgemp = foencgemp;
	}

	public String getFoencgfol() {
		return this.foencgfol;
	}

	public void setFoencgfol(String foencgfol) {
		this.foencgfol = foencgfol;
	}

	public String getFoeyliqd() {
		return foeyliqd;
	}

	public void setFoeyliqd(String foeyliqd) {
		this.foeyliqd = foeyliqd;
	}
}
