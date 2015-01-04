package com.grupoexata.auditoria.util;

public interface Operador {
	public boolean execute(ListExecute list);
	public boolean execute(ListExecute ...list);
	public boolean execute(boolean ...operando);
	public String getName();
}
