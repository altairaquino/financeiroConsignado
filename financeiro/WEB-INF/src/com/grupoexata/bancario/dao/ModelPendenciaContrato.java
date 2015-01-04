package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanPendenciaContrato;
import com.grupoexata.bancario.utils.Utils;

public class ModelPendenciaContrato {

	public static ModelPendenciaContrato getInstance(){
		return new ModelPendenciaContrato();
	}
	
	public ArrayList<BeanPendenciaContrato> getPendenciasDoContrato(int ctncodg){
		ArrayList<BeanPendenciaContrato> pendencias = new ArrayList<BeanPendenciaContrato>();
		try {
			String sql = " SELECT * FROM VW_PENDENCIACONTRATO WHERE PENCGCT = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ctncodg);
			
			pendencias.addAll(Utils.getObjectsStr(st, BeanPendenciaContrato.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return pendencias;
		
	}
	
	
	public BeanPendenciaContrato getPendenciaContrato(int pencodg){
		BeanPendenciaContrato pendencia = null;
		try {
			String sql = "SELECT * FROM VW_PENDENCIACONTRATO WHERE PENCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, pencodg);
			
			List<BeanPendenciaContrato> l = Utils.getObjectsStr(st, BeanPendenciaContrato.class); 
			
			if (!l.isEmpty())
				pendencia = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pendencia;		
	}
}
