package com.grupoexata.financeiro.struts.bean;

import java.io.Serializable;

public class BeanFormaLiquidez implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String flqncodg;
	private String flqcdesc;
	private String flqlimed;
	
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
