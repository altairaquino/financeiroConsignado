package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanTipoContrato;
import com.grupoexata.bancario.utils.Utils;

public class ModelTipoContrato {

	public static ModelTipoContrato getInstance(){
		return new ModelTipoContrato();
	}
	
	public ArrayList<BeanTipoContrato> getTiposContrato(){
		ArrayList<BeanTipoContrato> tipoContrato = new ArrayList<BeanTipoContrato>();
		try {
			String sql = " SELECT TCNCODG, TCCDESC FROM VW_TIPOCONTRATO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			tipoContrato.addAll(Utils.getObjectsStr(st, BeanTipoContrato.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tipoContrato;
		
	}
	
	public BeanTipoContrato getTipoContrato(int tcncodg){
		BeanTipoContrato tipoContrato = null;
		try {
			String sql = "SELECT TLNCODG, TLCDESC FROM VW_TIPOCONTRATO WHERE TCNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tcncodg);
			
			List<BeanTipoContrato> l = Utils.getObjectsStr(st, BeanTipoContrato.class); 
			
			if (!l.isEmpty())
				tipoContrato = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tipoContrato;
		
	}
}
