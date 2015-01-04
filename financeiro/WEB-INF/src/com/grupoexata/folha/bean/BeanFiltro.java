package com.grupoexata.folha.bean;

import java.io.Serializable;

public class BeanFiltro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6654281500323884938L;
	private String filncodg;
	private String filcdesc;
	public String getFilncodg() {
		return filncodg;
	}
	public void setFilncodg(String filncodg) {
		this.filncodg = filncodg;
	}
	public String getFilcdesc() {
		return filcdesc;
	}
	public void setFilcdesc(String filcdesc) {
		this.filcdesc = filcdesc;
	}
	
}
