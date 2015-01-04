package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;

public class BeanListaEmail implements Serializable {

	private static final long serialVersionUID = 4766971798239980657L;
	
	private String ltencodg;
	private String ltencgen;
	private String ltecnome;
	private String ltecmail;
	private String ltencggem;
	private String ltecdcgem;
	private String ltelativ;	
	
	public String getLtencgen() {
		return ltencgen;
	}
	public void setLtencgen(String ltencgen) {
		this.ltencgen = ltencgen;
	}
	public String getLtencodg() {
		return ltencodg;
	}
	public void setLtencodg(String ltencodg) {
		this.ltencodg = ltencodg;
	}
	public String getLtecnome() {
		return ltecnome;
	}
	public void setLtecnome(String ltecnome) {
		this.ltecnome = ltecnome;
	}
	public String getLtecmail() {
		return ltecmail;
	}
	public void setLtecmail(String ltecmail) {
		this.ltecmail = ltecmail;
	}
	public String getLtencggem() {
		return ltencggem;
	}
	public void setLtencggem(String ltencggem) {
		this.ltencggem = ltencggem;
	}
	public String getLtecdcgem() {
		return ltecdcgem;
	}
	public void setLtecdcgem(String ltecdcgem) {
		this.ltecdcgem = ltecdcgem;
	}
	public String getLtelativ() {
		return ltelativ;
	}
	public void setLtelativ(String ltelativ) {
		this.ltelativ = ltelativ;
	}
	
	
}
