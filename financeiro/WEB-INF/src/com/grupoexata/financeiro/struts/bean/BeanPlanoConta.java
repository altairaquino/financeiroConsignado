package com.grupoexata.financeiro.struts.bean;

import java.io.Serializable;

public class BeanPlanoConta implements Serializable {
	
	private static final long serialVersionUID = -6061371834485064553L;
	
	private String plcncodg;
	private String plcnempr;
	private String plcnpai;
	private String plccpai;
	private String plccdesc;
	private String plcntipo;
	private String plclativ;
	
	public String getPlcncodg() {
		return plcncodg;
	}
	public void setPlcncodg(String plcncodg) {
		this.plcncodg = plcncodg;
	}
	public String getPlcnempr() {
		return plcnempr;
	}
	public void setPlcnempr(String plcnempr) {
		this.plcnempr = plcnempr;
	}
	public String getPlcnpai() {
		return plcnpai;
	}
	public void setPlcnpai(String plcnpai) {
		this.plcnpai = plcnpai;
	}
	public String getPlccdesc() {
		return plccdesc;
	}
	public void setPlccdesc(String plccdesc) {
		this.plccdesc = plccdesc;
	}
	public String getPlcntipo() {
		return plcntipo;
	}
	public void setPlcntipo(String plcntipo) {
		this.plcntipo = plcntipo;
	}
	public String getPlclativ() {
		return plclativ;
	}
	public void setPlclativ(String plclativ) {
		this.plclativ = plclativ;
	}
	public String getPlccpai() {
		return plccpai;
	}
	public void setPlccpai(String plccpai) {
		this.plccpai = plccpai;
	}	
	

}
