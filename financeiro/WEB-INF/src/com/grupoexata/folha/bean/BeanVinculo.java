package com.grupoexata.folha.bean;
public class BeanVinculo{
	private String vinncodg;
	private String vinccodg;
	private String vincdesc;
	
	public String getVinncodg() {
		return this.vinncodg;
	}

	public void setVinncodg(String vinncodg) {
		this.vinncodg = vinncodg;
	}

	public String getVinccodg() {
		return this.vinccodg;
	}

	public void setVinccodg(String vinccodg) {
		this.vinccodg = vinccodg;
	}

	public String getVincdesc() {
		return this.vincdesc;
	}

	public void setVincdesc(String vincdesc) {
		this.vincdesc = vincdesc;
	}
	
	public String getVinccodg_vincdesc(){
		return this.vinccodg + " - " + this.vincdesc;
	}
}
