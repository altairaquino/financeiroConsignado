package com.grupoexata.auditoria.util;

import java.io.Serializable;

public class OperadorAND implements Operador, Serializable{

	private static final long serialVersionUID = 2230615047131938025L;
	
	public OperadorAND() {
	}

	@Override
	public boolean execute(ListExecute list) {
		boolean ret = list.size() > 0;
		int i = 0;
		while(ret && i < list.size()){			
			ret = ret && list.get(i);
			i++;
		}
		return ret;
	}

	@Override
	public boolean execute(boolean... operando) {
		boolean ret = operando.length > 0;
		int i = 0;
		while(ret && i < operando.length){
			ret = ret && operando[i];
			i++;
		}
		return ret;
	}

	@Override
	public String getName() {
		return "AND";
	}

	@Override
	public boolean execute(ListExecute... list) {
		boolean ret = list.length > 0;
		int i = 0;
		while(ret && i < list.length){
			ret = ret && execute(list[i]);
			i++;
		}
		return ret;
	}

}
