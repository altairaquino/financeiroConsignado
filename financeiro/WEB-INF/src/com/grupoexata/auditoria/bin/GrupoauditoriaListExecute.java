package com.grupoexata.auditoria.bin;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.grupoexata.auditoria.bean.BeanGrupoauditoria;
import com.grupoexata.auditoria.dao.ModelFiltroauditoria;

public class GrupoauditoriaListExecute extends AbstractAuditoriaListExecute<BeanGrupoauditoria> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8466351384358690898L;
	private ModelFiltroauditoria filtroauditoria = ModelFiltroauditoria.getIntance();	
	
	public GrupoauditoriaListExecute(Map<String, Object> map,
			List<BeanGrupoauditoria> list, AuditoriaExecute<BeanGrupoauditoria> grupoauditoriaExecute) {
		super(map, list, grupoauditoriaExecute);
	}
	
	public GrupoauditoriaListExecute(Map<String, Object> map, AuditoriaExecute<BeanGrupoauditoria> grupoauditoriaExecute) {
		this(map,null, grupoauditoriaExecute);
		Integer gpancodg = (Integer)map.get("gpancodg");
		List<BeanGrupoauditoria> list = filtroauditoria.listGrupoauditoria(gpancodg); 
		setList(list);
	}
	

}
