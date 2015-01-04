package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.grupoexata.bancario.utils.ValidaObjeto;

public class FormEmpregado extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String empncodg;
	private String empncgen;
	private String empcnmen;
	private String empccpf;
	private String empdnasc;
	private String empclogn;
	private String empncgste;
	private String empccgste;
	private String empcdcste;
	private String empncgcte;
	private String empcdccte;
	private String empncgvin;
	private String empccgvin;
	private String empcdcvin;
	private String empncgocp;
	private String empccgocp;
	private String empcdcocp;
	private String empncgtad;
	private String empcdctad;
	private String empncggin;
	private String empcdcgin;
	private String empctitl;
	private String empcrg;
	private String empcorg;
	private String empcufrg;
	private String empcncat;
	private String empcscat;
	private String empcufct;
	private String empcfgts;
	private String empdfgts;
	private String empcmatr;
	private String empccarg;
	private String empcpis;
	private String empnhrsm;
	private String empybase;
	private String empdadms;
	private String empdterm;
	private String empnma13;
	private String empdcadt;
	private String empdaltr;
	private String emplnir = "F";
	private String empycpsg;
	private String emplsreg = "F";
	private String empdcart;
	private String empddems;
	private String empdinat;
	private String empncgset;
	private String empcdcset;
	private String empncgfl;
	private String empcdcfl;
	private String emplcoms = "F";
	private String emplvale = "F";
	private String empyajud;
	private String emplativ = "T";
	
	public String getEmpncgfl() {
		return empncgfl;
	}
	public void setEmpncgfl(String empncgfl) {
		this.empncgfl = empncgfl;
	}
	public String getEmpcdcfl() {
		return empcdcfl;
	}
	public void setEmpcdcfl(String empcdcfl) {
		this.empcdcfl = empcdcfl;
	}
	public String getEmplcoms() {
		return emplcoms;
	}
	public void setEmplcoms(String emplcoms) {
		this.emplcoms = emplcoms;
	}
	public String getEmplvale() {
		return emplvale;
	}
	public void setEmplvale(String emplvale) {
		this.emplvale = emplvale;
	}
	public String getEmpyajud() {
		return empyajud;
	}
	public void setEmpyajud(String empyajud) {
		this.empyajud = empyajud;
	}
	public String getEmpclogn() {
		return empclogn;
	}
	public void setEmpclogn(String empclogn) {
		this.empclogn = empclogn;
	}
	public String getEmpncodg() {
		return empncodg;
	}
	public void setEmpncodg(String empncodg) {
		this.empncodg = empncodg;
	}
	public String getEmpncgen() {
		return empncgen;
	}
	public void setEmpncgen(String empncgen) {
		this.empncgen = empncgen;
	}
	public String getEmpcnmen() {
		return empcnmen;
	}
	public void setEmpcnmen(String empcnmen) {
		this.empcnmen = empcnmen;
	}
	public String getEmpccpf() {
		return empccpf;
	}
	public void setEmpccpf(String empccpf) {
		this.empccpf = empccpf;
	}
	public String getEmpdnasc() {
		return empdnasc;
	}
	public void setEmpdnasc(String empdnasc) {
		this.empdnasc = empdnasc;
	}
	public String getEmpncgste() {
		return empncgste;
	}
	public void setEmpncgste(String empncgste) {
		this.empncgste = empncgste;
	}
	public String getEmpccgste() {
		return empccgste;
	}
	public void setEmpccgste(String empccgste) {
		this.empccgste = empccgste;
	}
	public String getEmpcdcste() {
		return empcdcste;
	}
	public void setEmpcdcste(String empcdcste) {
		this.empcdcste = empcdcste;
	}
	public String getEmpncgcte() {
		return empncgcte;
	}
	public void setEmpncgcte(String empncgcte) {
		this.empncgcte = empncgcte;
	}
	public String getEmpcdccte() {
		return empcdccte;
	}
	public void setEmpcdccte(String empcdccte) {
		this.empcdccte = empcdccte;
	}
	public String getEmpncgvin() {
		return empncgvin;
	}
	public void setEmpncgvin(String empncgvin) {
		this.empncgvin = empncgvin;
	}
	public String getEmpccgvin() {
		return empccgvin;
	}
	public void setEmpccgvin(String empccgvin) {
		this.empccgvin = empccgvin;
	}
	public String getEmpcdcvin() {
		return empcdcvin;
	}
	public void setEmpcdcvin(String empcdcvin) {
		this.empcdcvin = empcdcvin;
	}
	public String getEmpncgocp() {
		return empncgocp;
	}
	public void setEmpncgocp(String empncgocp) {
		this.empncgocp = empncgocp;
	}
	public String getEmpccgocp() {
		return empccgocp;
	}
	public void setEmpccgocp(String empccgocp) {
		this.empccgocp = empccgocp;
	}
	public String getEmpcdcocp() {
		return empcdcocp;
	}
	public void setEmpcdcocp(String empcdcocp) {
		this.empcdcocp = empcdcocp;
	}
	public String getEmpncgtad() {
		return empncgtad;
	}
	public void setEmpncgtad(String empncgtad) {
		this.empncgtad = empncgtad;
	}
	public String getEmpcdctad() {
		return empcdctad;
	}
	public void setEmpcdctad(String empcdctad) {
		this.empcdctad = empcdctad;
	}
	public String getEmpncggin() {
		return empncggin;
	}
	public void setEmpncggin(String empncggin) {
		this.empncggin = empncggin;
	}
	public String getEmpcdcgin() {
		return empcdcgin;
	}
	public void setEmpcdcgin(String empcdcgin) {
		this.empcdcgin = empcdcgin;
	}
	public String getEmpctitl() {
		return empctitl;
	}
	public void setEmpctitl(String empctitl) {
		this.empctitl = empctitl;
	}
	public String getEmpcrg() {
		return empcrg;
	}
	public void setEmpcrg(String empcrg) {
		this.empcrg = empcrg;
	}
	public String getEmpcorg() {
		return empcorg;
	}
	public void setEmpcorg(String empcorg) {
		this.empcorg = empcorg;
	}
	public String getEmpcufrg() {
		return empcufrg;
	}
	public void setEmpcufrg(String empcufrg) {
		this.empcufrg = empcufrg;
	}
	public String getEmpcncat() {
		return empcncat;
	}
	public void setEmpcncat(String empcncat) {
		this.empcncat = empcncat;
	}
	public String getEmpcscat() {
		return empcscat;
	}
	public void setEmpcscat(String empcscat) {
		this.empcscat = empcscat;
	}
	public String getEmpcufct() {
		return empcufct;
	}
	public void setEmpcufct(String empcufct) {
		this.empcufct = empcufct;
	}
	public String getEmpcfgts() {
		return empcfgts;
	}
	public void setEmpcfgts(String empcfgts) {
		this.empcfgts = empcfgts;
	}
	public String getEmpdfgts() {
		return empdfgts;
	}
	public void setEmpdfgts(String empdfgts) {
		this.empdfgts = empdfgts;
	}
	public String getEmpcmatr() {
		return empcmatr;
	}
	public void setEmpcmatr(String empcmatr) {
		this.empcmatr = empcmatr;
	}
	public String getEmpccarg() {
		return empccarg;
	}
	public void setEmpccarg(String empccarg) {
		this.empccarg = empccarg;
	}
	public String getEmpcpis() {
		return empcpis;
	}
	public void setEmpcpis(String empcpis) {
		this.empcpis = empcpis;
	}
	public String getEmpnhrsm() {
		return empnhrsm;
	}
	public void setEmpnhrsm(String empnhrsm) {
		this.empnhrsm = empnhrsm;
	}
	public String getEmpybase() {
		return empybase;
	}
	public void setEmpybase(String empybase) {
		this.empybase = empybase;
	}
	public String getEmpdadms() {
		return empdadms;
	}
	public void setEmpdadms(String empdadms) {
		this.empdadms = empdadms;
	}
	public String getEmpdterm() {
		return empdterm;
	}
	public void setEmpdterm(String empdterm) {
		this.empdterm = empdterm;
	}
	public String getEmpnma13() {
		return empnma13;
	}
	public void setEmpnma13(String empnma13) {
		this.empnma13 = empnma13;
	}
	public String getEmpdcadt() {
		return empdcadt;
	}
	public void setEmpdcadt(String empdcadt) {
		this.empdcadt = empdcadt;
	}
	public String getEmpdaltr() {
		return empdaltr;
	}
	public void setEmpdaltr(String empdaltr) {
		this.empdaltr = empdaltr;
	}
	public String getEmplnir() {
		return emplnir;
	}
	public void setEmplnir(String emplnir) {
		this.emplnir = emplnir;
	}
	public String getEmpycpsg() {
		return empycpsg;
	}
	public void setEmpycpsg(String empycpsg) {
		this.empycpsg = empycpsg;
	}
	public String getEmplsreg() {
		return emplsreg;
	}
	public void setEmplsreg(String emplsreg) {
		this.emplsreg = emplsreg;
	}
	public String getEmpdcart() {
		return empdcart;
	}
	public void setEmpdcart(String empdcart) {
		this.empdcart = empdcart;
	}
	public String getEmpddems() {
		return empddems;
	}
	public void setEmpddems(String empddems) {
		this.empddems = empddems;
	}
	public String getEmpdinat() {
		return empdinat;
	}
	public void setEmpdinat(String empdinat) {
		this.empdinat = empdinat;
	}
	public String getEmpncgset() {
		return empncgset;
	}
	public void setEmpncgset(String empncgset) {
		this.empncgset = empncgset;
	}
	public String getEmpcdcset() {
		return empcdcset;
	}
	public void setEmpcdcset(String empcdcset) {
		this.empcdcset = empcdcset;
	}
	public String getEmplativ() {
		return emplativ;
	}
	public void setEmplativ(String emplativ) {
		this.emplativ = emplativ;
	}	

	public void validarForm(ActionMessages erros){
		if(ValidaObjeto.isNullId(getEmpncgste())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Situação"));
		}
		if(ValidaObjeto.isNullId(getEmpncgcte())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Categoria"));
		}
		if(ValidaObjeto.isNullId(getEmpncgvin())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Vínculo"));
		}
		if(ValidaObjeto.isNullId(getEmpncgocp())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Ocupação"));
		}
		if(ValidaObjeto.isNullId(getEmpncgtad())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Tipo de admissão"));
		}
		if(ValidaObjeto.isNullId(getEmpncggin())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Grau de instrução"));
		}
		String smes13 = getEmpnma13();
		int imes13 = -1;
		try {
			imes13 = Integer.parseInt(smes13);
		} catch (Exception e) {}
		if(!ValidaObjeto.isNullId(smes13) && !(imes13>=1 &&imes13 <=12 )){
			setEmpnma13("");
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.default", "Mês de Antecipação do 13º salário entre 1 e 12"));
		}
		if(!ValidaObjeto.isNullId(getEmpdadms()) && !ValidaObjeto.validaData(getEmpdadms())){
			setEmpdadms("");
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.date","e", "admissão"));
		}
		if(!ValidaObjeto.isNullId(getEmpdterm()) && !ValidaObjeto.validaData(getEmpdterm())){
			setEmpdterm("");
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.date","o", "término do contrato"));
		}
		if(!ValidaObjeto.isNullId(getEmpdfgts()) && !ValidaObjeto.validaData(getEmpdfgts())){
			setEmpdfgts("");
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.date","o", "FGTS"));
		}
		if(ValidaObjeto.isNullId(getEmpnhrsm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Horas semanais"));
		}
		if(ValidaObjeto.isNullId(getEmpybase())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Salário base"));
		}
		if(ValidaObjeto.isNullId(getEmpccarg())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Cargo/Função"));
		}
	}
}
