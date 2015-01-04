package com.grupoexata.veiculo.struts.bean;

import java.io.Serializable;

public class BeanMarcaVeiculo implements Serializable{
	
	private static final long serialVersionUID = -3350322773120131699L;
	
	private String mvencodg;
	private String mvecdesc;
	private String mvelativ;
	
	public String getMvencodg() {
		return mvencodg;
	}
	public void setMvencodg(String mvencodg) {
		this.mvencodg = mvencodg;
	}
	public String getMvecdesc() {
		return mvecdesc;
	}
	public void setMvecdesc(String mvecdesc) {
		this.mvecdesc = mvecdesc;
	}
	public String getMvelativ() {
		return mvelativ;
	}
	public void setMvelativ(String mvelativ) {
		this.mvelativ = mvelativ;
	}

}
