package com.grupoexata.bancario.struts.bean;

import java.io.Serializable;

public class BeanLayoutitem implements Serializable{	
	
	private static final long serialVersionUID = 3475219909912818834L;
	
	private String litnidlay;
	private String litnbegn;
	private String litnend;
	private String litcvald;
	private String litcform;
	private String litcnmatr;
	private String litnidcla;
	private String litcnmcla;
	private String litlinit;
	private String litcvltip;	
	private String litnidtip;	
	
	public String getLitnidlay() {
		return litnidlay;
	}
	public void setLitnidlay(String litnidlay) {
		this.litnidlay = litnidlay;
	}
	public String getLitnbegn() {
		return litnbegn;
	}
	public void setLitnbegn(String litnbegn) {
		this.litnbegn = litnbegn;
	}
	public String getLitnend() {
		return litnend;
	}
	public void setLitnend(String litnend) {
		this.litnend = litnend;
	}
	public String getLitcvald() {
		return litcvald;
	}
	public void setLitcvald(String litcvald) {
		this.litcvald = litcvald;
	}
	public String getLitcform() {
		return litcform;
	}
	public void setLitcform(String litcform) {
		this.litcform = litcform;
	}
	public String getLitcnmatr() {
		return litcnmatr;
	}
	public void setLitcnmatr(String litcnmatr) {
		this.litcnmatr = litcnmatr;
	}
	public String getLitnidcla() {
		return litnidcla;
	}
	public void setLitnidcla(String litnidcla) {
		this.litnidcla = litnidcla;
	}
	public String getLitcnmcla() {
		return litcnmcla;
	}
	public void setLitcnmcla(String litcnmcla) {
		this.litcnmcla = litcnmcla;
	}
	public String getLitlinit() {
		return litlinit;
	}
	public void setLitlinit(String litlinit) {
		this.litlinit = litlinit;
	}
	public String getLitcvltip() {
		return litcvltip;
	}
	public void setLitcvltip(String litcvltip) {
		this.litcvltip = litcvltip;
	}
	
	public String getLitnidtip() {
		return litnidtip;
	}
	public void setLitnidtip(String litnidtip) {
		this.litnidtip = litnidtip;
	}
	public void imprimir(int cont, String value){
		System.out.println();
		System.out.print("\t" + cont +  " " + getLitcnmatr() + " ");
		System.out.print(getLitcnmcla() + " ");
		System.out.print(getLitcvald() + " ");
		System.out.print("\"" + get(value) + "\" ");
		System.out.print("\"" + getLitcform() + "\" ");
	}
	
	public String get(String str){
		return str.substring(Integer.parseInt(getLitnbegn()), Integer.parseInt(getLitnend()));
	}
	
}
