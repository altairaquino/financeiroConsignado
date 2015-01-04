package com.grupoexata.folha.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Calendario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5066564079567289733L;
	private int ano;
	private int mes;
	private int limite;
	private List<Mes> meses;
	/**
	 * mes [1 - 12]<br>
	 * limite >= 0(é o intervalo de meses que terá antes e depois do parametro mes)
	 * */
	public Calendario(int ano, int mes, int limite) {
		iniciar(ano, mes, limite);
	}
	
	public Calendario(Date data, int limite) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(data);
		int ano = gc.get(Calendar.YEAR);
		int mes = gc.get(Calendar.MONTH);
		iniciar(ano, mes, limite);
	}
	
	private void iniciar(int ano, int mes, int limite) {
		if(mes <1 || mes > 12 || limite < 0){
			throw new IllegalArgumentException();
		}		
		mes--;
		this.ano = ano;
		this.mes = mes;
		this.limite = limite;
		int min = this.mes - this.limite;
		while(min < 0){
			min = 12 + min;
			this.ano--;
		}
		int max = min + (2 * this.limite) + 1;
		meses = new ArrayList<Mes>();
		for (int i = min ; i < max; i++) {
			Mes m = new Mes(this.ano +  (i / 12), (i % 12) + 1);
			meses.add(m);
		}
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public List<Mes> getMeses() {
		return meses;
	}

	public void setMeses(List<Mes> meses) {
		this.meses = meses;
	}

}
