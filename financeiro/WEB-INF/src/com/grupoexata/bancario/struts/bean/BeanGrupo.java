package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;

public class BeanGrupo implements Serializable {
	
	
	private static final long serialVersionUID = -6381321763109216097L;
	
	private String grncodg;
	private String grcdesc;
	
	public String getGrncodg() {
		return grncodg;
	}
	public void setGrncodg(String grncodg) {
		this.grncodg = grncodg;
	}
	public String getGrcdesc() {
		return grcdesc;
	}
	public void setGrcdesc(String grcdesc) {
		this.grcdesc = grcdesc;
	}

}
