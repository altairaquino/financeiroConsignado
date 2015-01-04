package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanTipoFuncAgencia;
import com.grupoexata.bancario.utils.Utils;

public class ModelTipoFuncAgencia {

	public static ModelTipoFuncAgencia getInstance(){
		return new ModelTipoFuncAgencia();
	}
	
	public ArrayList<BeanTipoFuncAgencia> getTiposFuncAgencia(){
		ArrayList<BeanTipoFuncAgencia> tipos = new ArrayList<BeanTipoFuncAgencia>();
		try {
			String sql = " SELECT * FROM VW_TIPOFUNCAGENCIA";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			tipos.addAll(Utils.getObjectsStr(st, BeanTipoFuncAgencia.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tipos;
		
	}
	
	public ArrayList<BeanTipoFuncAgencia> getTiposFuncAgenciaTipo(String tfactipo){
		ArrayList<BeanTipoFuncAgencia> tipos = new ArrayList<BeanTipoFuncAgencia>();
		try {
			String sql = " SELECT * FROM VW_TIPOFUNCAGENCIA WHERE TFACTIPO = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, tfactipo);
			
			tipos.addAll(Utils.getObjectsStr(st, BeanTipoFuncAgencia.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipos;
		
	}
	
	public BeanTipoFuncAgencia getTipoFuncAgencia(int tfancodg){
		BeanTipoFuncAgencia tipo = null;
		try {
			String sql = "SELECT * FROM VW_TIPOFUNCAGENCIA WHERE TFANCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tfancodg);
			
			List<BeanTipoFuncAgencia> l = Utils.getObjectsStr(st, BeanTipoFuncAgencia.class); 
			
			if (!l.isEmpty())
				tipo = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tipo;
		
	}
	
}
