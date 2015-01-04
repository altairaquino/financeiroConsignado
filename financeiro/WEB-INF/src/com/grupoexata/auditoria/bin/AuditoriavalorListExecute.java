package com.grupoexata.auditoria.bin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.grupoexata.auditoria.bean.BeanAuditoriavalor;

public class AuditoriavalorListExecute extends AbstractAuditoriaListExecute<BeanAuditoriavalor> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -309374510005477230L;

	public AuditoriavalorListExecute(Map<String, Object> map,
			List<BeanAuditoriavalor> list, AuditoriaExecute<BeanAuditoriavalor> auditoriavalorExecute) {
		super(map, list, auditoriavalorExecute);
	}
	
	public AuditoriavalorListExecute(Map<String, Object> map, AuditoriaExecute<BeanAuditoriavalor> auditoriavalorExecute) {
		this(map, new ArrayList<BeanAuditoriavalor>(), auditoriavalorExecute);
	}

	
	

}
