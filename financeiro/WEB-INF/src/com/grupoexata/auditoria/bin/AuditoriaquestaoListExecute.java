package com.grupoexata.auditoria.bin;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.grupoexata.auditoria.bean.BeanAuditoriaquestao;
import com.grupoexata.auditoria.dao.ModelFiltroauditoria;

public class AuditoriaquestaoListExecute extends AbstractAuditoriaListExecute<BeanAuditoriaquestao> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -309374510005477230L;
	private ModelFiltroauditoria filtroauditoria = ModelFiltroauditoria.getIntance();	

	public AuditoriaquestaoListExecute(Map<String, Object> map,
			List<BeanAuditoriaquestao> list, AuditoriaquestaoExecute auditoriaquestaoExecute) {
		super(map, list, auditoriaquestaoExecute);
	}
	
	public AuditoriaquestaoListExecute(Map<String, Object> map, AuditoriaquestaoExecute auditoriaquestaoExecute) {
		this(map, null, auditoriaquestaoExecute);
		Integer gpancodg = (Integer)map.get("gpancodg");
		Integer ctncodg = (Integer)map.get("ctncodg");
		List<BeanAuditoriaquestao> list = filtroauditoria.listAuditoriaquestao(gpancodg, ctncodg); 
		setList(list);
	}

}
