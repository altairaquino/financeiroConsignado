package com.grupoexata.auditoria.util;

import java.io.Serializable;

public class OperadorOR implements Operador, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2230615047131938025L;
	
	public OperadorOR() {
	}

	@Override
	public boolean execute(ListExecute list) {
		boolean ret = false;
		int i = 0;
		while(i < list.size()){
			ret = ret || list.get(i);
			i++;
		}
		return ret;
	}

	@Override
	public boolean execute(boolean... operando) {
		boolean ret = false;
		int i = 0;
		while(i < operando.length){
			ret = ret || operando[i];
			i++;
		}
		return ret;
	}

	@Override
	public String getName() {
		return "OR";
	}

	@Override
	public boolean execute(ListExecute... list) {
		boolean ret = false;
		int i = 0;
		while(i < list.length){
			ret = ret || execute(list[i]);
			i++;
		}
		return ret;
	}

}
