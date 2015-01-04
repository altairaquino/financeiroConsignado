package com.grupoexata.veiculo.struts.bean;

import java.io.Serializable;

public class BeanPgtoContratoAuto implements Serializable {
	
	private static final long serialVersionUID = 5281532758624280822L;
	
	private String pgtncodg;
	private String pgtcdesc;
	private String pgtlativ;
	
	public String getPgtncodg() {
		return pgtncodg;
	}
	public void setPgtncodg(String pgtncodg) {
		this.pgtncodg = pgtncodg;
	}
	public String getPgtcdesc() {
		return pgtcdesc;
	}
	public void setPgtcdesc(String pgtcdesc) {
		this.pgtcdesc = pgtcdesc;
	}
	public String getPgtlativ() {
		return pgtlativ;
	}
	public void setPgtlativ(String pgtlativ) {
		this.pgtlativ = pgtlativ;
	}
	
	

}
