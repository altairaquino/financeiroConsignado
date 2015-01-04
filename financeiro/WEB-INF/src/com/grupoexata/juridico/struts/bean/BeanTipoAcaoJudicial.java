package com.grupoexata.juridico.struts.bean;

import java.io.Serializable;

public class BeanTipoAcaoJudicial implements Serializable {
	
	private static final long serialVersionUID = 7372120704547908746L;
	
	private String tacncodg;
	private String taccdesc;
	private String taclativ;
	
	public String getTacncodg() {
		return tacncodg;
	}
	public void setTacncodg(String tacncodg) {
		this.tacncodg = tacncodg;
	}
	public String getTaccdesc() {
		return taccdesc;
	}
	public void setTaccdesc(String taccdesc) {
		this.taccdesc = taccdesc;
	}
	public String getTaclativ() {
		return taclativ;
	}
	public void setTaclativ(String taclativ) {
		this.taclativ = taclativ;
	}
	
	

}
