package com.grupoexata.financeiro.struts.bean;

import java.io.Serializable;

public class BeanContaGrupo implements Serializable {
	
	private static final long serialVersionUID = 1959299799601375698L;
	
	private String cogncodg;
	private String cogncggpc;
	private String cogcdcgpc;
	private String cogcdesc;
	private String coglativ;
	
	public String getCogncodg() {
		return cogncodg;
	}
	public void setCogncodg(String cogncodg) {
		this.cogncodg = cogncodg;
	}
	public String getCogncggpc() {
		return cogncggpc;
	}
	public void setCogncggpc(String cogncggpc) {
		this.cogncggpc = cogncggpc;
	}
	public String getCogcdcgpc() {
		return cogcdcgpc;
	}
	public void setCogcdcgpc(String cogcdcgpc) {
		this.cogcdcgpc = cogcdcgpc;
	}
	public String getCogcdesc() {
		return cogcdesc;
	}
	public void setCogcdesc(String cogcdesc) {
		this.cogcdesc = cogcdesc;
	}
	public String getCoglativ() {
		return coglativ;
	}
	public void setCoglativ(String coglativ) {
		this.coglativ = coglativ;
	}
	
	

}
