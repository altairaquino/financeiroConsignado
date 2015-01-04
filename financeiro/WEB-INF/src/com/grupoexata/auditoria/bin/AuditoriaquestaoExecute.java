package com.grupoexata.auditoria.bin;

import java.util.Map;

import com.grupoexata.auditoria.bean.BeanAuditoriaquestao;

public class AuditoriaquestaoExecute implements AuditoriaExecute<BeanAuditoriaquestao>{
	
	public boolean execute(BeanAuditoriaquestao t, Map<String, Object> map) {
		String s = t.getAqslaudt(); 
		return  s != null && s.equals("T");
	}
}
