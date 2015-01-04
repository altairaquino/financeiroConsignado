package com.grupoexata.folha.bean;

import java.io.Serializable;

public class BeanTipoajcust implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5015573990087515610L;
	private String tacncodg;
	private String taccdesc;
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
}
