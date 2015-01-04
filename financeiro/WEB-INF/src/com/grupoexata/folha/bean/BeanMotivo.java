package com.grupoexata.folha.bean;

import java.io.Serializable;

public class BeanMotivo implements Serializable {
	
	private static final long serialVersionUID = 901404330853088800L;
	
	private String motncodg;
	private String motcdesc;
	private String motlcanc;

	public String getMotncodg() {
		return motncodg;
	}

	public void setMotncodg(String motncodg) {
		this.motncodg = motncodg;
	}

	public String getMotcdesc() {
		return motcdesc;
	}

	public void setMotcdesc(String motcdesc) {
		this.motcdesc = motcdesc;
	}

	public String getMotlcanc() {
		return motlcanc;
	}

	public void setMotlcanc(String motlcanc) {
		this.motlcanc = motlcanc;
	}

}
