package com.grupoexata.financeiro.struts.form;

import org.apache.struts.action.ActionForm;

public class FormFormaLiquidez extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String flqncodg;
	private String flqcdesc;
	private String flqlimed = "T";
	
	public String getFlqlimed() {
		return flqlimed;
	}
	public void setFlqlimed(String flqlimed) {
		this.flqlimed = flqlimed;
	}
	public String getFlqncodg() {
		return flqncodg;
	}
	public void setFlqncodg(String flqncodg) {
		this.flqncodg = flqncodg;
	}
	public String getFlqcdesc() {
		return flqcdesc;
	}
	public void setFlqcdesc(String flqcdesc) {
		this.flqcdesc = flqcdesc;
	}
	
		
		

}
