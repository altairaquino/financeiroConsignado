package com.grupoexata.bancario.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class FormContrato extends ActionForm {
	
	private static final long serialVersionUID = 8787157910472252405L;
	
	private String ctncodg;
	private String ctnnumr;
	private String ctnc2en;
	private String ctcnmcl;
	private String ctccpf;
	private String ctncgen;
	private String ctcnman;
	private String ctyvalr;
	private String ctdverb;
	private String ctdpgto;
	private String ctncgfp;
	private String ctcdcfp;
	private String ctncgsc;
	private String ctcdcsc;
	private String ctncgpd;
	private String ctcdcpd;
	private String ctncgtc;
	private String ctcdctc;
	private String ctncgtp;
	private String ctcdctp;
	private String ctcmrtp;
	private String ctnqtpc;
	private String ctnpraz;
	private String ctlfisi;
	private String ctdbxfi;
	private String ctdextn;
	private String ctncadt;
	private String ctccadt;
	private String cttcadt;
	private String ctdcadt;
	private String ctdalte;
	private String ctnalte;
	private String ctlaudi;
	private String ctncoms;
	private String ctycoms;
	private String ctnexat;
	private String ctyexat;
	private String ctyspre;
	private String ctncgbc;
	private String ctcdcbc;
	private String ctcagen;
	private FormFile arquivo;
	private String ctytari;
	private String ctdsqop;
	private String ctnsqop;
	private String ctcsqop;
	private String ctlpgcm;
	private String ctlativ;
	
	private String encnome;
	private String encdocm;
	private String encmae;
	private String encpai;
	private String endnasc;
	private String endexrg;
	private String encfone;
	private String encrg;
	private String encoerg;
	private String encdorg;
	private String enlendr;
	private String enncgcd;
	private String enccep;
	private String encbair;
	private String encsexo = "M";
	private String encufrg;
	private String enncgtl;
	private String encufcd;	
	
	public String getEncmae() {
		return encmae;
	}
	public void setEncmae(String encmae) {
		this.encmae = encmae;
	}
	public String getEncpai() {
		return encpai;
	}
	public void setEncpai(String encpai) {
		this.encpai = encpai;
	}
	public String getCttcadt() {
		return cttcadt;
	}
	public void setCttcadt(String cttcadt) {
		this.cttcadt = cttcadt;
	}
	public String getCtdalte() {
		return ctdalte;
	}
	public void setCtdalte(String ctdalte) {
		this.ctdalte = ctdalte;
	}
	public String getCtnalte() {
		return ctnalte;
	}
	public void setCtnalte(String ctnalte) {
		this.ctnalte = ctnalte;
	}
	public String getCtdpgto() {
		return ctdpgto;
	}
	public void setCtdpgto(String ctdpgto) {
		this.ctdpgto = ctdpgto;
	}	
	public String getCtlpgcm() {
		return ctlpgcm;
	}
	public void setCtlpgcm(String ctlpgcm) {
		this.ctlpgcm = ctlpgcm;
	}
	public String getCtlativ() {
		return ctlativ;
	}
	public void setCtlativ(String ctlativ) {
		this.ctlativ = ctlativ;
	}
	public String getEncufcd() {
		return encufcd;
	}
	public void setEncufcd(String encufcd) {
		this.encufcd = encufcd;
	}
	public String getEnncgtl() {
		return enncgtl;
	}
	public void setEnncgtl(String enncgtl) {
		this.enncgtl = enncgtl;
	}
	public String getEncufrg() {
		return encufrg;
	}
	public void setEncufrg(String encufrg) {
		this.encufrg = encufrg;
	}
	public String getEncsexo() {
		return encsexo;
	}
	public void setEncsexo(String encsexo) {
		this.encsexo = encsexo;
	}
	public String getEncnome() {
		return encnome;
	}
	public void setEncnome(String encnome) {
		this.encnome = encnome;
	}
	public String getEncdocm() {
		return encdocm;
	}
	public void setEncdocm(String encdocm) {
		this.encdocm = encdocm;
	}
	public String getEndnasc() {
		return endnasc;
	}
	public void setEndnasc(String endnasc) {
		this.endnasc = endnasc;
	}
	public String getEndexrg() {
		return endexrg;
	}
	public void setEndexrg(String endexrg) {
		this.endexrg = endexrg;
	}
	public String getEncfone() {
		return encfone;
	}
	public void setEncfone(String encfone) {
		this.encfone = encfone;
	}
	public String getEncrg() {
		return encrg;
	}
	public void setEncrg(String encrg) {
		this.encrg = encrg;
	}
	public String getEncoerg() {
		return encoerg;
	}
	public void setEncoerg(String encoerg) {
		this.encoerg = encoerg;
	}
	public String getEncdorg() {
		return encdorg;
	}
	public void setEncdorg(String encdorg) {
		this.encdorg = encdorg;
	}
	public String getEnlendr() {
		return enlendr;
	}
	public void setEnlendr(String enlendr) {
		this.enlendr = enlendr;
	}
	public String getEnncgcd() {
		return enncgcd;
	}
	public void setEnncgcd(String enncgcd) {
		this.enncgcd = enncgcd;
	}
	public String getEnccep() {
		return enccep;
	}
	public void setEnccep(String enccep) {
		this.enccep = enccep;
	}
	public String getEncbair() {
		return encbair;
	}
	public void setEncbair(String encbair) {
		this.encbair = encbair;
	}
	public String getCtcsqop() {
		return ctcsqop;
	}
	public void setCtcsqop(String ctcsqop) {
		this.ctcsqop = ctcsqop;
	}
	public String getCtyspre() {
		return ctyspre;
	}
	public void setCtyspre(String ctyspre) {
		this.ctyspre = ctyspre;
	}
	public String getCtytari() {
		return ctytari;
	}
	public void setCtytari(String ctytari) {
		this.ctytari = ctytari;
	}
	public String getCtdsqop() {
		return ctdsqop;
	}
	public void setCtdsqop(String ctdsqop) {
		this.ctdsqop = ctdsqop;
	}
	public String getCtnsqop() {
		return ctnsqop;
	}
	public void setCtnsqop(String ctnsqop) {
		this.ctnsqop = ctnsqop;
	}
	public FormFile getArquivo() {
		return arquivo;
	}
	public void setArquivo(FormFile arquivo) {
		this.arquivo = arquivo;
	}
	public String getCtncgbc() {
		return ctncgbc;
	}
	public void setCtncgbc(String ctncgbc) {
		this.ctncgbc = ctncgbc;
	}
	public String getCtcdcbc() {
		return ctcdcbc;
	}
	public void setCtcdcbc(String ctcdcbc) {
		this.ctcdcbc = ctcdcbc;
	}
	public String getCtcagen() {
		return ctcagen;
	}
	public void setCtcagen(String ctcagen) {
		this.ctcagen = ctcagen;
	}
	public String getCtncoms() {
		return ctncoms;
	}
	public void setCtncoms(String ctncoms) {
		this.ctncoms = ctncoms;
	}
	public String getCtycoms() {
		return ctycoms;
	}
	public void setCtycoms(String ctycoms) {
		this.ctycoms = ctycoms;
	}
	public String getCtnexat() {
		return ctnexat;
	}
	public void setCtnexat(String ctnexat) {
		this.ctnexat = ctnexat;
	}
	public String getCtyexat() {
		return ctyexat;
	}
	public void setCtyexat(String ctyexat) {
		this.ctyexat = ctyexat;
	}
	public String getCtncodg() {
		return ctncodg;
	}
	public void setCtncodg(String ctncodg) {
		this.ctncodg = ctncodg;
	}
	public String getCtnnumr() {
		return ctnnumr;
	}
	public void setCtnnumr(String ctnnumr) {
		this.ctnnumr = ctnnumr;
	}
	public String getCtnc2en() {
		return ctnc2en;
	}
	public void setCtnc2en(String ctnc2en) {
		this.ctnc2en = ctnc2en;
	}
	public String getCtcnmcl() {
		return ctcnmcl;
	}
	public void setCtcnmcl(String ctcnmcl) {
		this.ctcnmcl = ctcnmcl;
	}
	public String getCtncgen() {
		return ctncgen;
	}
	public void setCtncgen(String ctncgen) {
		this.ctncgen = ctncgen;
	}
	public String getCtcnman() {
		return ctcnman;
	}
	public void setCtcnman(String ctcnman) {
		this.ctcnman = ctcnman;
	}
	public String getCtyvalr() {
		return ctyvalr;
	}
	public void setCtyvalr(String ctyvalr) {
		this.ctyvalr = ctyvalr;
	}
	public String getCtdverb() {
		return ctdverb;
	}
	public void setCtdverb(String ctdverb) {
		this.ctdverb = ctdverb;
	}
	public String getCtncgfp() {
		return ctncgfp;
	}
	public void setCtncgfp(String ctncgfp) {
		this.ctncgfp = ctncgfp;
	}
	public String getCtcdcfp() {
		return ctcdcfp;
	}
	public void setCtcdcfp(String ctcdcfp) {
		this.ctcdcfp = ctcdcfp;
	}
	public String getCtncgsc() {
		return ctncgsc;
	}
	public void setCtncgsc(String ctncgsc) {
		this.ctncgsc = ctncgsc;
	}
	public String getCtcdcsc() {
		return ctcdcsc;
	}
	public void setCtcdcsc(String ctcdcsc) {
		this.ctcdcsc = ctcdcsc;
	}
	public String getCtncgtc() {
		return ctncgtc;
	}
	public void setCtncgtc(String ctncgtc) {
		this.ctncgtc = ctncgtc;
	}
	public String getCtcdctc() {
		return ctcdctc;
	}
	public void setCtcdctc(String ctcdctc) {
		this.ctcdctc = ctcdctc;
	}
	public String getCtncgtp() {
		return ctncgtp;
	}
	public void setCtncgtp(String ctncgtp) {
		this.ctncgtp = ctncgtp;
	}
	public String getCtcdctp() {
		return ctcdctp;
	}
	public void setCtcdctp(String ctcdctp) {
		this.ctcdctp = ctcdctp;
	}
	public String getCtcmrtp() {
		return ctcmrtp;
	}
	public void setCtcmrtp(String ctcmrtp) {
		this.ctcmrtp = ctcmrtp;
	}
	public String getCtnqtpc() {
		return ctnqtpc;
	}
	public void setCtnqtpc(String ctnqtpc) {
		this.ctnqtpc = ctnqtpc;
	}
	public String getCtnpraz() {
		return ctnpraz;
	}
	public void setCtnpraz(String ctnpraz) {
		this.ctnpraz = ctnpraz;
	}
	public String getCtlfisi() {
		return ctlfisi;
	}
	public void setCtlfisi(String ctlfisi) {
		this.ctlfisi = ctlfisi;
	}
	public String getCtdbxfi() {
		return ctdbxfi;
	}
	public void setCtdbxfi(String ctdbxfi) {
		this.ctdbxfi = ctdbxfi;
	}
	public String getCtdextn() {
		return ctdextn;
	}
	public void setCtdextn(String ctdextn) {
		this.ctdextn = ctdextn;
	}
	public String getCtncadt() {
		return ctncadt;
	}
	public void setCtncadt(String ctncadt) {
		this.ctncadt = ctncadt;
	}
	public String getCtdcadt() {
		return ctdcadt;
	}
	public void setCtdcadt(String ctdcadt) {
		this.ctdcadt = ctdcadt;
	}
	public String getCtlaudi() {
		return ctlaudi;
	}
	public void setCtlaudi(String ctlaudi) {
		this.ctlaudi = ctlaudi;
	}
	public String getCtncgpd() {
		return ctncgpd;
	}
	public void setCtncgpd(String ctncgpd) {
		this.ctncgpd = ctncgpd;
	}
	public String getCtcdcpd() {
		return ctcdcpd;
	}
	public void setCtcdcpd(String ctcdcpd) {
		this.ctcdcpd = ctcdcpd;
	}
	public String getCtccpf() {
		return ctccpf;
	}
	public void setCtccpf(String ctccpf) {
		this.ctccpf = ctccpf;
	}
	public String getCtccadt() {
		return ctccadt;
	}
	public void setCtccadt(String ctccadt) {
		this.ctccadt = ctccadt;
	}
		

}
