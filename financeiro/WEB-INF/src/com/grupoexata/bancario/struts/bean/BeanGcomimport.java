package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class BeanGcomimport implements Serializable{
	
	
	private static final long serialVersionUID = -984545986024566604L;
	
	private Integer gcincodg;
	private String gcictipo;
	private Date gciddata;
	private String gciccontr;
	private String gciccpf;
	private String gcicclien;
	private String gcicaten;
	private String gcicvend;
	private Integer gcinplan;
	private Integer gcinprod;
	private String gcictabl;
	private String gciccrdb;
	private String gcicagen;
	private String gcicestd;
	private Double gciyvalr;
	private Double gcinperc;
	private String gcinperc_i;
	private String gcinperc_d;
	private String gciyvalr_i;
	private String gciyvalr_d;
	private String gcictext;

	public void setGciyvalr() {
		if (gciyvalr_i != null && gciyvalr_d != null) {
			try {
				gciyvalr = new DecimalFormat("0.00", new DecimalFormatSymbols(
						new Locale("pt", "BR"))).parse(
						gciyvalr_i + "," + gciyvalr_d).doubleValue();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	public void setGcinperc() {

		if (gcinperc_i != null && gcinperc_d != null) {
			try {
				gcinperc = new DecimalFormat("0.00", new DecimalFormatSymbols(
						new Locale("pt", "BR"))).parse(
						gcinperc_i + "," + gcinperc_d).doubleValue();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	public Integer getGcincodg() {
		return gcincodg;
	}

	public void setGcincodg(Integer gcincodg) {
		this.gcincodg = gcincodg;
	}

	public String getGcictipo() {
		return gcictipo;
	}

	public void setGcictipo(String gcictipo) {
		this.gcictipo = gcictipo;
	}

	public Date getGciddata() {
		return gciddata;
	}

	public void setGciddata(Date gciddata) {
		this.gciddata = gciddata;
	}

	public String getGciccontr() {
		return gciccontr;
	}

	public void setGciccontr(String gciccontr) {
		this.gciccontr = gciccontr;
	}

	public String getGciccpf() {
		return gciccpf;
	}

	public void setGciccpf(String gciccpf) {
		this.gciccpf = gciccpf;
	}

	public String getGcicclien() {
		return gcicclien;
	}

	public void setGcicclien(String gcicclien) {
		this.gcicclien = gcicclien;
	}

	public String getGcicaten() {
		return gcicaten;
	}

	public void setGcicaten(String gcicaten) {
		this.gcicaten = gcicaten;
	}

	public String getGcicvend() {
		return gcicvend;
	}

	public void setGcicvend(String gcicvend) {
		this.gcicvend = gcicvend;
	}

	public Integer getGcinplan() {
		return gcinplan;
	}

	public void setGcinplan(Integer gcinplan) {
		this.gcinplan = gcinplan;
	}

	public Integer getGcinprod() {
		return gcinprod;
	}

	public void setGcinprod(Integer gcinprod) {
		this.gcinprod = gcinprod;
	}

	public String getGcictabl() {
		return gcictabl;
	}

	public void setGcictabl(String gcictabl) {
		this.gcictabl = gcictabl;
	}

	public String getGciccrdb() {
		return gciccrdb;
	}

	public void setGciccrdb(String gciccrdb) {
		this.gciccrdb = gciccrdb;
	}

	public String getGcicagen() {
		return gcicagen;
	}

	public void setGcicagen(String gcicagen) {
		this.gcicagen = gcicagen;
	}

	public String getGcicestd() {
		return gcicestd;
	}

	public void setGcicestd(String gcicestd) {
		this.gcicestd = gcicestd;
	}

	public Double getGciyvalr() {
		return gciyvalr;
	}

	public void setGciyvalr(Double gciyvalr) {
		this.gciyvalr = gciyvalr;
	}

	public Double getGcinperc() {
		return gcinperc;
	}

	public void setGcinperc(Double gcinperc) {
		this.gcinperc = gcinperc;
	}

	public String getGcinperc_i() {
		return gcinperc_i;
	}

	public void setGcinperc_i(String gcinperc_i) {
		this.gcinperc_i = gcinperc_i;
	}

	public String getGcinperc_d() {
		return gcinperc_d;
	}

	public void setGcinperc_d(String gcinperc_d) {
		this.gcinperc_d = gcinperc_d;
	}

	public String getGciyvalr_i() {
		return gciyvalr_i;
	}

	public void setGciyvalr_i(String gciyvalr_i) {
		this.gciyvalr_i = gciyvalr_i;
	}

	public String getGciyvalr_d() {
		return gciyvalr_d;
	}

	public void setGciyvalr_d(String gciyvalr_d) {
		this.gciyvalr_d = gciyvalr_d;
	}

	public String getGcictext() {
		return gcictext;
	}

	public void setGcictext(String gcictext) {
		this.gcictext = gcictext;
	}

	public void imprimir() {
		System.out.println();
		System.out.println("Contrato : " + gciccontr);
		System.out.println("CPF: " + gciccpf);
		System.out.println("Cliente: " + gcicclien);
		System.out.println("Data: " + gciddata);
		System.out.println("Tipo: " + gcictipo);
		System.out.println("valor_i: " + gciyvalr_i);
		System.out.println("valor_d: " + gciyvalr_d);
		System.out.println("valor: " + gciyvalr);
		System.out.println("credito_debito: " + gciccrdb);
		System.out.println("estado: " + gcicestd);
		System.out.println("perc_exata_d: " + gcinperc_d);
		System.out.println("perc_exata_i: " + gcinperc_i);
		System.out.println("perc_exata: " + gcinperc);
		System.out.println("plano: " + gcinplan);
		System.out.println("produto: " + gcinprod);
		System.out.println("tabela: " + gcictabl);
		System.out.println("texto: " + gcictext);
	}
}
