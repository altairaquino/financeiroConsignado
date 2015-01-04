package com.grupoexata.folha.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Semana {
	private GregorianCalendar calendar;;
	private List<Dia> dias;
	private int ano;
	private int numero;

	public Semana(int ano, int semana) {
		calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, ano);
		calendar.set(Calendar.WEEK_OF_YEAR, semana);
		iniciar();
	}
	
	public Semana(Date data) {
		calendar = new GregorianCalendar();
		calendar.setTime(data);
		iniciar();
	}
	
	private void iniciar(){
		ano = calendar.get(Calendar.YEAR);
		numero = calendar.get(Calendar.WEEK_OF_YEAR);
		dias = new ArrayList<Dia>();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		for (int i = Calendar.MONDAY; i < Calendar.SATURDAY; i++) {
			calendar.set(Calendar.DAY_OF_WEEK, i);
			dias.add(new Dia(calendar.getTime()));
		}
	}

	public List<Dia> getDias() {
		return dias;
	}

	public void setDias(List<Dia> dias) {
		this.dias = dias;
	}
	
	public String getLimite(){
		return dias.get(0).getData() + " -  " +	dias.get(dias.size() - 1).getData();
	}
	
	
	public GregorianCalendar getCalendar() {
		return calendar;
	}

	public void setCalendar(GregorianCalendar calendar) {
		this.calendar = calendar;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getDescricao(){
		return "Semana : " + numero + " [" + getLimite() + "]";
	}

	public void imprimir() {
		System.out.println("INICIO: " + dias.get(0).getData());
		System.out.println("TERMINO: " + dias.get(dias.size() - 1).getData());
		System.out.println("WEEK_OF_YEAR: "	+ calendar.get(Calendar.WEEK_OF_YEAR));
		System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
	}

	public static void main(String args[]) {
		Semana semana1 = new Semana(2009, 23);
		System.out.println("Calendario1");
		semana1.imprimir();
		Semana semana2 = new Semana(new Date());
		System.out.println("Calendario2");
		semana2.imprimir();
	}
}
