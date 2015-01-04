package com.grupoexata.folha.bean;
public class BeanSituacaoEmpregado{
	private String stencodg;
	private String steccodg;
	private String stecdesc;

	public String getStencodg() {
		return this.stencodg;
	}

	public void setStencodg(String stencodg) {
		this.stencodg = stencodg;
	}

	public String getSteccodg() {
		return this.steccodg;
	}

	public void setSteccodg(String steccodg) {
		this.steccodg = steccodg;
	}

	public String getStecdesc() {
		return this.stecdesc;
	}

	public void setStecdesc(String stecdesc) {
		this.stecdesc = stecdesc;
	}
	
	public String getSteccodg_stecdesc(){
		return this.steccodg + " - " + this.stecdesc;
	}
}
