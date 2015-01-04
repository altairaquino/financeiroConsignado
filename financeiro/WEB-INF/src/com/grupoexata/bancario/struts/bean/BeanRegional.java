package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;

public class BeanRegional implements Serializable {
	
	private static final long serialVersionUID = 1835079520181799272L;
	
	private String rgncodg;
	private String rgcdesc;
	private String rglativ;
	
	public String getRgncodg() {
		return rgncodg;
	}
	public void setRgncodg(String rgncodg) {
		this.rgncodg = rgncodg;
	}
	public String getRgcdesc() {
		return rgcdesc;
	}
	public void setRgcdesc(String rgcdesc) {
		this.rgcdesc = rgcdesc;
	}
	public String getRglativ() {
		return rglativ;
	}
	public void setRglativ(String rglativ) {
		this.rglativ = rglativ;
	}
	
	

}
