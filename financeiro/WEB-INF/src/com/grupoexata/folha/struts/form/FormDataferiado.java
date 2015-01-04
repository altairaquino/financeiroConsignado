package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;

public class FormDataferiado extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String dfencodg;
	private String dfencgfer;
	private String dfeddata;
	private String dfenano;

	public String getDfencodg() {
		return this.dfencodg;
	}

	public void setDfencodg(String dfencodg) {
		this.dfencodg = dfencodg;
	}

	public String getDfencgfer() {
		return this.dfencgfer;
	}

	public void setDfencgfer(String dfencgfer) {
		this.dfencgfer = dfencgfer;
	}

	public String getDfeddata() {
		return this.dfeddata;
	}

	public void setDfeddata(String dfeddata) {
		this.dfeddata = dfeddata;
	}

	public String getDfenano() {
		return this.dfenano;
	}

	public void setDfenano(String dfenano) {
		this.dfenano = dfenano;
	}
}
