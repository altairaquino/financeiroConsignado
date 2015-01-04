package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;

public class BeanSituacaoContrato implements Serializable {
	
	private static final long serialVersionUID = -3031192046013222629L;
	
	private String scncodg;
	private String sccdesc;
	private String sclpgcm;
	
	public String getScncodg() {
		return scncodg;
	}
	public void setScncodg(String scncodg) {
		this.scncodg = scncodg;
	}
	public String getSccdesc() {
		return sccdesc;
	}
	public void setSccdesc(String sccdesc) {
		this.sccdesc = sccdesc;
	}
	public String getSclpgcm() {
		return sclpgcm;
	}
	public void setSclpgcm(String sclpgcm) {
		this.sclpgcm = sclpgcm;
	}
	
	

}
