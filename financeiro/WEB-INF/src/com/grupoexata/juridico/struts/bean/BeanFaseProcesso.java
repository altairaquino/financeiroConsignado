package com.grupoexata.juridico.struts.bean;

import java.io.Serializable;

public class BeanFaseProcesso implements Serializable {
	
	private static final long serialVersionUID = -1587991031100626015L;
	
	private String fapncodg;
	private String fapcdesc;
	private String faplativ;
	
	public String getFapncodg() {
		return fapncodg;
	}
	public void setFapncodg(String fapncodg) {
		this.fapncodg = fapncodg;
	}
	public String getFapcdesc() {
		return fapcdesc;
	}
	public void setFapcdesc(String fapcdesc) {
		this.fapcdesc = fapcdesc;
	}
	public String getFaplativ() {
		return faplativ;
	}
	public void setFaplativ(String faplativ) {
		this.faplativ = faplativ;
	}
	
	

}
