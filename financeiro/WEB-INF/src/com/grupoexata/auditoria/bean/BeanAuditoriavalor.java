package com.grupoexata.auditoria.bean;

import java.io.Serializable;

public class BeanAuditoriavalor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3483344623383966488L;
	private String avlncodg;
	private String avlyinic;
	private String avlyfinl;
	private String avlctipo;
	private String avlncggpa;

	public String getAvlncodg() {
		return avlncodg;
	}

	public void setAvlncodg(String avlncodg) {
		this.avlncodg = avlncodg;
	}

	public String getAvlyinic() {
		return avlyinic;
	}

	public void setAvlyinic(String avlyinic) {
		this.avlyinic = avlyinic;
	}

	public String getAvlyfinl() {
		return avlyfinl;
	}

	public void setAvlyfinl(String avlyfinl) {
		this.avlyfinl = avlyfinl;
	}

	public String getAvlctipo() {
		return avlctipo;
	}

	public void setAvlctipo(String avlctipo) {
		this.avlctipo = avlctipo;
	}

	public String getAvlncggpa() {
		return avlncggpa;
	}

	public void setAvlncggpa(String avlncggpa) {
		this.avlncggpa = avlncggpa;
	}

}
