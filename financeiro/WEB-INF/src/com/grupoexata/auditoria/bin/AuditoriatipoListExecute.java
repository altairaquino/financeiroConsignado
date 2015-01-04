package com.grupoexata.auditoria.bin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.grupoexata.auditoria.bean.BeanAuditoriatipo;

public class AuditoriatipoListExecute extends AbstractAuditoriaListExecute<BeanAuditoriatipo> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -309374510005477230L;

	public AuditoriatipoListExecute(Map<String, Object> map,
			List<BeanAuditoriatipo> list, AuditoriaExecute<BeanAuditoriatipo> auditoriatipoExecute) {
		super(map, list, auditoriatipoExecute);
	}
	
	public AuditoriatipoListExecute(Map<String, Object> map, AuditoriaExecute<BeanAuditoriatipo> auditoriatipoExecute) {
		this(map, new ArrayList<BeanAuditoriatipo>(), auditoriatipoExecute);
	}

}
