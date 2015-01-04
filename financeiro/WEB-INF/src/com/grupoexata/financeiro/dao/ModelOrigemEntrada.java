package com.grupoexata.financeiro.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.financeiro.struts.bean.BeanOrigemEntrada;

public class ModelOrigemEntrada {

	public static ModelOrigemEntrada getInstance(){
		return new ModelOrigemEntrada();
	}
	
	public ArrayList<BeanOrigemEntrada> getOrigensEntrada(){
		ArrayList<BeanOrigemEntrada> origens = new ArrayList<BeanOrigemEntrada>();
		try {
			String sql = " SELECT * FROM VW_ORIGEMENTRADA";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			origens.addAll(Utils.getObjectsStr(st, BeanOrigemEntrada.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return origens;
		
	}
	
	public BeanOrigemEntrada getOrigemEntrada(int orencodg){
		BeanOrigemEntrada origemEntrada = null;
		try {
			String sql = "SELECT * FROM VW_ORIGEMENTRADA WHERE ORENCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, orencodg);
			
			List<BeanOrigemEntrada> l = Utils.getObjectsStr(st, BeanOrigemEntrada.class); 
			
			if (!l.isEmpty())
				origemEntrada = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return origemEntrada;
		
	}	
	
}
