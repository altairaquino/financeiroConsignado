package com.grupoexata.bancario.utils;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class ValidaObjeto {
	
	public static boolean validaHora(String hora) throws IllegalArgumentException{
		boolean ret = false;
		try {
			if (hora != null){
				if (Time.valueOf(hora) != null){
					ret = true;
				}
			}
		} catch (Exception e) {
			ret = false;
		}
		return ret;		
	}

	public static boolean validaInteiro(String numero) {
		boolean ok = false;
		try {
			Integer.parseInt(numero);
			ok = true;
		} catch (Exception e) {
			ok = false;
		}
		return ok;
	}

	public static boolean validaFloat(String floatValue) {
		boolean ok = false;
		try {
			Float.parseFloat(floatValue);
			ok = true;
		} catch (Exception e) {
			ok = false;
		}
		return ok;
	}
	
	public static boolean validaEmail(String email) {
		boolean ok = false;
		try {
			Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
			//Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
			
			ok = validaString(email) && p.matcher(email).matches();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	public static boolean validaData(String data) {
		boolean ret = false;
		try {
			if (data != null) {
				if (data.length() == 10) {
					if (new SimpleDateFormat("dd/MM/yyyy").parse(data) != null)
						ret = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;

	}

	public static boolean validaString(String str) {
		if (str == null) {
			return false;
		} else {
			return !str.equals("");
		}
	}

	public static boolean validaNumeroContrato(String str) {
		if (!validaString(str)) {
			return false;
		} else {
			return (str.length() == 9 || str.length() == 10);
		}
	}
	
	public static boolean validaFone(String str) {
		return (validaString(str) && removeCharOfInteger(str).length() == 10);
	}

	public static String removeCharOfInteger(String str) {
		String ret = "";
		if (str != null) {
			ret = str.replace("/", "").replace("-", "").replace("(", "")
					.replace(")", "").replace("_", "").replace(".", "")
					.replace(",", "");
		}
		return ret;
	}

	public static boolean isNullId(String s) {
		return s == null || s.equals("-1") || s.equals("");
	}
	
	public static void main(String[] args) {
		System.out.println(ValidaObjeto.validaEmail("altair@exatagrupo.com.br"));
	}
	

}
