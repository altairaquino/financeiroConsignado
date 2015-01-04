package com.grupoexata.bancario.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.grupoexata.bancario.utils.ValidaObjeto;

public class FormEntidade extends ActionForm{
	
	
	private static final long serialVersionUID = 1L;
	private String enncodg;
	private String encnome;
	private String enncgte;
	private String encdcte;
	private String encsgte;
	private String enncgpp = "1";
	private String encdcpp;
	private String enctpdc;
	private String encsexo = "M";
	private String enclogn;
	private String encsenh;
	private String encsen2;
	private String encsen3;
	private String encdocm;
	private String endexrg;
	private String encrg  ;
	private String encoerg;
	private String encufrg;
	private String encdorg;
	private String endnasc;
	private String encconj;
	private String encapel;
	private String enncgec;
	private String eccdesc;
	private String encpai;
	private String encmae;
	private String enncgtl = "33";
	private String encdctl;
	private String enlendr;
	private String encfone;
	private String enccell;
	private String encmail;
	private String enncgcd;
	private String encufcd;
	private String encdccd;
	private String enccep;
	private String encbair;
	private String enmobs;
	private String enlativ;	
	
	public String getEndexrg() {
		return endexrg;
	}
	public void setEndexrg(String endexrg) {
		this.endexrg = endexrg;
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
	public String getEncufrg() {
		return encufrg;
	}
	public void setEncufrg(String encufrg) {
		this.encufrg = encufrg;
	}
	public String getEncdorg() {
		return encdorg;
	}
	public void setEncdorg(String encdorg) {
		this.encdorg = encdorg;
	}
	public String getEnncodg() {
		return enncodg;
	}
	public void setEnncodg(String enncodg) {
		this.enncodg = enncodg;
	}
	public String getEncnome() {
		return encnome;
	}
	public void setEncnome(String encnome) {
		this.encnome = encnome;
	}
	public String getEnncgte() {
		return enncgte;
	}
	public void setEnncgte(String enncgte) {
		this.enncgte = enncgte;
	}
	public String getEncdcte() {
		return encdcte;
	}
	public void setEncdcte(String encdcte) {
		this.encdcte = encdcte;
	}
	public String getEncsgte() {
		return encsgte;
	}
	public void setEncsgte(String encsgte) {
		this.encsgte = encsgte;
	}
	public String getEnncgpp() {
		return enncgpp;
	}
	public void setEnncgpp(String enncgpp) {
		this.enncgpp = enncgpp;
	}
	public String getEncdcpp() {
		return encdcpp;
	}
	public void setEncdcpp(String encdcpp) {
		this.encdcpp = encdcpp;
	}
	public String getEnctpdc() {
		return enctpdc;
	}
	public void setEnctpdc(String enctpdc) {
		this.enctpdc = enctpdc;
	}
	public String getEncsexo() {
		return encsexo;
	}
	public void setEncsexo(String encsexo) {
		this.encsexo = encsexo;
	}
	public String getEnclogn() {
		return enclogn;
	}
	public void setEnclogn(String enclogn) {
		this.enclogn = enclogn;
	}
	public String getEncsenh() {
		return encsenh;
	}
	public void setEncsenh(String encsenh) {
		this.encsenh = encsenh;
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
	public String getEncconj() {
		return encconj;
	}
	public void setEncconj(String encconj) {
		this.encconj = encconj;
	}
	public String getEncapel() {
		return encapel;
	}
	public void setEncapel(String encapel) {
		this.encapel = encapel;
	}
	public String getEnncgec() {
		return enncgec;
	}
	public void setEnncgec(String enncgec) {
		this.enncgec = enncgec;
	}
	public String getEccdesc() {
		return eccdesc;
	}
	public void setEccdesc(String eccdesc) {
		this.eccdesc = eccdesc;
	}
	public String getEncpai() {
		return encpai;
	}
	public void setEncpai(String encpai) {
		this.encpai = encpai;
	}
	public String getEncmae() {
		return encmae;
	}
	public void setEncmae(String encmae) {
		this.encmae = encmae;
	}
	public String getEnncgtl() {
		return enncgtl;
	}
	public void setEnncgtl(String enncgtl) {
		this.enncgtl = enncgtl;
	}
	public String getEncdctl() {
		return encdctl;
	}
	public void setEncdctl(String encdctl) {
		this.encdctl = encdctl;
	}
	public String getEnlendr() {
		return enlendr;
	}
	public void setEnlendr(String enlendr) {
		this.enlendr = enlendr;
	}
	public String getEncfone() {
		return encfone;
	}
	public void setEncfone(String encfone) {
		this.encfone = encfone;
	}
	public String getEnccell() {
		return enccell;
	}
	public void setEnccell(String enccell) {
		this.enccell = enccell;
	}
	public String getEncmail() {
		return encmail;
	}
	public void setEncmail(String encmail) {
		this.encmail = encmail;
	}
	public String getEnncgcd() {
		return enncgcd;
	}
	public void setEnncgcd(String enncgcd) {
		this.enncgcd = enncgcd;
	}
	public String getEncufcd() {
		return encufcd;
	}
	public void setEncufcd(String encufcd) {
		this.encufcd = encufcd;
	}
	public String getEncdccd() {
		return encdccd;
	}
	public void setEncdccd(String encdccd) {
		this.encdccd = encdccd;
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
	public String getEnmobs() {
		return enmobs;
	}
	public void setEnmobs(String enmobs) {
		this.enmobs = enmobs;
	}
	public String getEnlativ() {
		return enlativ;
	}
	public void setEnlativ(String enlativ) {
		this.enlativ = enlativ;
	}
	public String getEncsen2() {
		return encsen2;
	}
	public void setEncsen2(String encsen2) {
		this.encsen2 = encsen2;
	}
	public String getEncsen3() {
		return encsen3;
	}
	public void setEncsen3(String encsen3) {
		this.encsen3 = encsen3;
	}
	
	public void validarFuncionario(ActionMessages erros){
		if (!ValidaObjeto.validaString(getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome é obrigatório!"));
		}		
		if (!ValidaObjeto.validaString(getEncdocm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF é requerido!"));
		}
		if (!ValidaObjeto.validaString(getEndnasc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento é Obrigatório!"));
		}else{
			if (!ValidaObjeto.validaData(getEndnasc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento inválida!"));
			}
		}
		
		if (!ValidaObjeto.validaString(getEnlendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Endereço é obrigatório!"));
		}
		
		if (getEnncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Cidade é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(getEnccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CEP é inválido!"));
		}
		
		if (!ValidaObjeto.validaString(getEncbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Bairro é obrigatório!"));
		}
		
	}

}
