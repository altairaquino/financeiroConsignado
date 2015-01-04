package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;

public class BeanOperacaoGrupo implements Serializable {
	
	private static final long serialVersionUID = 5539853939424408814L;
	
	private String goncodg;
	private String goncggr;
	private String gocdcgr;
	private String goncgop;
	private String gocdcop;	
	
	public String getGoncodg() {
		return goncodg;
	}
	public void setGoncodg(String goncodg) {
		this.goncodg = goncodg;
	}
	public String getGoncggr() {
		return goncggr;
	}
	public void setGoncggr(String goncggr) {
		this.goncggr = goncggr;
	}
	public String getGocdcgr() {
		return gocdcgr;
	}
	public void setGocdcgr(String gocdcgr) {
		this.gocdcgr = gocdcgr;
	}
	public String getGoncgop() {
		return goncgop;
	}
	public void setGoncgop(String goncgop) {
		this.goncgop = goncgop;
	}
	public String getGocdcop() {
		return gocdcop;
	}
	public void setGocdcop(String gocdcop) {
		this.gocdcop = gocdcop;
	}
	
	

}
