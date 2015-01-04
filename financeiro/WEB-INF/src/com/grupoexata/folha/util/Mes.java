package com.grupoexata.folha.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Mes {
	private GregorianCalendar calendar;;
	private List<Integer> semanas;
	private String nome;

	public Mes(int ano, int mes) {
		if(mes <1 || mes > 12){
			throw new IllegalArgumentException();
		}
		calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, ano);
		calendar.set(Calendar.MONTH, mes - 1);
		iniciar();
	}
	
	public Mes(Date data) {
		calendar = new GregorianCalendar();
		calendar.setTime(data);
		iniciar();
	}
	
	public GregorianCalendar getCalendar() {
		return calendar;
	}

	public void setCalendar(GregorianCalendar calendar) {
		this.calendar = calendar;
	}

	public List<Integer> getSemanas() {
		return semanas;
	}

	public void setSemanas(List<Integer> semanas) {
		this.semanas = semanas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private void iniciar(){
		semanas = new ArrayList<Integer>();
		calendar.set(Calendar.DATE, 1);
		nome = new SimpleDateFormat("MMMM").format(calendar.getTime());
		nome = nome.toUpperCase();
		int max_semana = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
		int semana = calendar.get(Calendar.WEEK_OF_YEAR);
		for (int i = 0; i < max_semana; i++) {
			semanas.add(semana + i);
		}
	}
	public void imprimir() {
		System.out.println("MAX MONTH: " +	calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		System.out.println("MAX WEEK_OF_MONTH: " +	calendar.getActualMaximum(Calendar.WEEK_OF_MONTH));
		System.out.println("WEEK_OF_YEAR: "	+ calendar.get(Calendar.WEEK_OF_YEAR));
		System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
		System.out.println("TIME: " + calendar.getTime());
		System.out.println("MES: " + nome);
		System.out.print("SEMANAS: ");
		for (Integer i :semanas) {
			System.out.print(", " + i);
		}
		System.out.println();
	}

	public static void main(String args[]) {
		Mes mes1 = new Mes(2009, 7);
		System.out.println("Calendario1");
		mes1.imprimir();
		Mes mes2 = new Mes(2009, 8);
		System.out.println("Calendario2");
		mes2.imprimir();
	}
}
