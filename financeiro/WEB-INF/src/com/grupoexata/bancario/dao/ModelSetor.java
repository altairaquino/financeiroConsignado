package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanSetor;
import com.grupoexata.bancario.utils.Utils;

public class ModelSetor {

	public static ModelSetor getInstance(){
		return new ModelSetor();
	}
	
	public ArrayList<BeanSetor> getSetores(){
		ArrayList<BeanSetor> setores = new ArrayList<BeanSetor>();
		try {
			String sql = " SELECT * FROM VW_SETOR";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			setores.addAll(Utils.getObjectsStr(st, BeanSetor.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return setores;
		
	}
	
	public BeanSetor getSetor(int setncodg){
		BeanSetor setor = null;
		try {
			String sql = "SELECT * FROM VW_SETOR WHERE SETNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, setncodg);
			
			List<BeanSetor> l = Utils.getObjectsStr(st, BeanSetor.class); 
			
			if (!l.isEmpty())
				setor = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return setor;
		
	}
}
