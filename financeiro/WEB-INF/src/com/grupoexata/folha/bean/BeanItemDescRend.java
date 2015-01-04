package com.grupoexata.folha.bean;
public class BeanItemDescRend{
	private String idrncodg;
	//private String idrncgder;
	//private String idrncgtdr;
	private String idrctipo;
	private BeanDescRend idrncgder;
	private BeanTipoDescRend idrncgtdr; 

	public String getIdrncodg() {
		return this.idrncodg;
	}

	public void setIdrncodg(String idrncodg) {
		this.idrncodg = idrncodg;
	}

/*	public String getIdrncgder() {
		return this.idrncgder;
	}

	public void setIdrncgder(String idrncgder) {
		this.idrncgder = idrncgder;
	}

	public String getIdrncgtdr() {
		return this.idrncgtdr;
	}

	public void setIdrncgtdr(String idrncgtdr) {
		this.idrncgtdr = idrncgtdr;
	}
*/
	public String getIdrctipo() {
		return this.idrctipo;
	}

	public void setIdrctipo(String idrctipo) {
		this.idrctipo = idrctipo;
	}

	public BeanDescRend getIdrncgder() {
		return idrncgder;
	}

	public void setIdrncgder(BeanDescRend idrncgder) {
		this.idrncgder = idrncgder;
	}

	public BeanTipoDescRend getIdrncgtdr() {
		return idrncgtdr;
	}

	public void setIdrncgtdr(BeanTipoDescRend idrncgtdr) {
		this.idrncgtdr = idrncgtdr;
	}

		
}
