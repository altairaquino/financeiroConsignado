package com.grupoexata.bancario.struts.form;

import org.apache.struts.action.ActionForm;

public class FormFormaPagamento extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	private String fpncodg;
	private String fpcdesc;
	
	public String getFpncodg() {
		return fpncodg;
	}
	public void setFpncodg(String fpncodg) {
		this.fpncodg = fpncodg;
	}
	public String getFpcdesc() {
		return fpcdesc;
	}
	public void setFpcdesc(String fpcdesc) {
		this.fpcdesc = fpcdesc;
	}
	
		
		

}
