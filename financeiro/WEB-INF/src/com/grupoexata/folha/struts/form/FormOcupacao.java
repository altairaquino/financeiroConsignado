package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;

public class FormOcupacao extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String ocpncodg;
	private String ocpccodg;
	private String ocpctitulo;
	private String ocpncgfam;
	private String ocpccgfam;

	public String getOcpncodg() {
		return this.ocpncodg;
	}

	public void setOcpncodg(String ocpncodg) {
		this.ocpncodg = ocpncodg;
	}

	public String getOcpccodg() {
		return this.ocpccodg;
	}

	public void setOcpccodg(String ocpccodg) {
		this.ocpccodg = ocpccodg;
	}

	public String getOcpctitulo() {
		return this.ocpctitulo;
	}

	public void setOcpctitulo(String ocpctitulo) {
		this.ocpctitulo = ocpctitulo;
	}

	public String getOcpncgfam() {
		return this.ocpncgfam;
	}

	public void setOcpncgfam(String ocpncgfam) {
		this.ocpncgfam = ocpncgfam;
	}

	public String getOcpccgfam() {
		return this.ocpccgfam;
	}

	public void setOcpccgfam(String ocpccgfam) {
		this.ocpccgfam = ocpccgfam;
	}
}
