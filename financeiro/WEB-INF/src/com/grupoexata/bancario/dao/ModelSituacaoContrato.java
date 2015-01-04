package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanSituacaoContrato;
import com.grupoexata.bancario.utils.Utils;

public class ModelSituacaoContrato {

	public static ModelSituacaoContrato getInstance(){
		return new ModelSituacaoContrato();
	}
	
	public ArrayList<BeanSituacaoContrato> getSituacaoContratos(){
		ArrayList<BeanSituacaoContrato> SituacaoContratos = new ArrayList<BeanSituacaoContrato>();
		try {
			String sql = " SELECT * FROM VW_SITUACAOCONTRATO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			SituacaoContratos.addAll(Utils.getObjectsStr(st, BeanSituacaoContrato.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return SituacaoContratos;
		
	}
	
	public BeanSituacaoContrato getSituacaoContrato(int grncodg){
		BeanSituacaoContrato SituacaoContrato = null;
		
		try {
			String sql = "SELECT * FROM VW_SITUACAOCONTRATO WHERE SCNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, grncodg);
			
			List<BeanSituacaoContrato> l = Utils.getObjectsStr(st, BeanSituacaoContrato.class); 
			
			if (!l.isEmpty())
				SituacaoContrato = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SituacaoContrato;
		
	}
	
}
