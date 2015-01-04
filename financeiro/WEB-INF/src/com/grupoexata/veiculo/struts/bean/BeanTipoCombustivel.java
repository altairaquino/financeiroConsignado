package com.grupoexata.veiculo.struts.bean;

import java.io.Serializable;

public class BeanTipoCombustivel implements Serializable {
	
	private static final long serialVersionUID = -3085142980179629994L;
	
	private String tconcodg;
	private String tcocdesc;
	private String tcolativ;
	
	public String getTconcodg() {
		return tconcodg;
	}
	public void setTconcodg(String tconcodg) {
		this.tconcodg = tconcodg;
	}
	public String getTcocdesc() {
		return tcocdesc;
	}
	public void setTcocdesc(String tcocdesc) {
		this.tcocdesc = tcocdesc;
	}
	public String getTcolativ() {
		return tcolativ;
	}
	public void setTcolativ(String tcolativ) {
		this.tcolativ = tcolativ;
	}
	
	

}
