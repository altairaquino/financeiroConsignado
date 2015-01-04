package com.grupoexata.auditoria.bean;

import java.io.Serializable;

public class BeanOpcao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7446065031600079223L;
	private String opcncodg;
	private String opcncggpo;
	private String opccdesc;
	private String opclatv;

	public String getOpcncodg() {
		return opcncodg;
	}

	public void setOpcncodg(String opcncodg) {
		this.opcncodg = opcncodg;
	}

	public String getOpcncggpo() {
		return opcncggpo;
	}

	public void setOpcncggpo(String opcncggpo) {
		this.opcncggpo = opcncggpo;
	}

	public String getOpccdesc() {
		return opccdesc;
	}

	public void setOpccdesc(String opccdesc) {
		this.opccdesc = opccdesc;
	}

	public String getOpclatv() {
		return opclatv;
	}

	public void setOpclatv(String opclatv) {
		this.opclatv = opclatv;
	}

}
