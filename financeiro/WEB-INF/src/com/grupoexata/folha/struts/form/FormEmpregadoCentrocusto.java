package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;

public class FormEmpregadoCentrocusto extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String eccncodg;
	private String eccncgemp;
	private String eccncgcr;

	public String getEccncodg() {
		return this.eccncodg;
	}

	public void setEccncodg(String eccncodg) {
		this.eccncodg = eccncodg;
	}

	public String getEccncgemp() {
		return this.eccncgemp;
	}

	public void setEccncgemp(String eccncgemp) {
		this.eccncgemp = eccncgemp;
	}

	public String getEccncgcr() {
		return this.eccncgcr;
	}

	public void setEccncgcr(String eccncgcr) {
		this.eccncgcr = eccncgcr;
	}
}
