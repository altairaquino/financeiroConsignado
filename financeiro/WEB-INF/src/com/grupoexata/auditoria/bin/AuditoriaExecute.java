package com.grupoexata.auditoria.bin;

import java.util.Map;


public interface AuditoriaExecute<T> {
	public boolean execute(T t, Map<String, Object> map);
}
