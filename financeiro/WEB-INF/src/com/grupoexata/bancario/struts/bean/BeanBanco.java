package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;

public class BeanBanco implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String bcncodg;
	private String bccdesc;
	
	public String getBcncodg() {
		return bcncodg;
	}
	public void setBcncodg(String bcncodg) {
		this.bcncodg = bcncodg;
	}
	public String getBccdesc() {
		return bccdesc;
	}
	public void setBccdesc(String bccdesc) {
		this.bccdesc = bccdesc;
	}
	
	
}
