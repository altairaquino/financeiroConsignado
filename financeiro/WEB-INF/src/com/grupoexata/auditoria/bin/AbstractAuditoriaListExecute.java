package com.grupoexata.auditoria.bin;

import java.util.List;
import java.util.Map;

import com.grupoexata.auditoria.util.ListExecute;

public abstract class AbstractAuditoriaListExecute<T> implements ListExecute{
	
	private Map<String, Object> map;
	private List<T> list;
	private AuditoriaExecute<T> auditoriaExecute;
	
	public AbstractAuditoriaListExecute(Map<String, Object> map,
			List<T> list, AuditoriaExecute<T> auditoriaExecute) {
		this.map = map;
		this.list = list;
		this.auditoriaExecute = auditoriaExecute;
	}

	@Override
	public boolean get(int index) {
		return auditoriaExecute.execute(list.get(index), map);
	}

	@Override
	public int size() {
		return list.size();
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public AuditoriaExecute<T> getAuditoriaExecute() {
		return auditoriaExecute;
	}

	public void setAuditoriaExecute(AuditoriaExecute<T> auditoriaExecute) {
		this.auditoriaExecute = auditoriaExecute;
	}

}
