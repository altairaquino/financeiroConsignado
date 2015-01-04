package com.grupoexata.folha.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dia {
	private Date date;
	private SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat formatDataAbrev = new SimpleDateFormat("dd/MM");
	private SimpleDateFormat formatDia = new SimpleDateFormat("EEE");

	public Dia(Date date) {
		super();
		this.date = date;
	}

	public String getDia() {
		return formatDia.format(date).toUpperCase();
	}

	public String getData() {
		return formatData.format(date);
	}
	
	public String getDataAbrev() {
		return formatDataAbrev.format(date);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
