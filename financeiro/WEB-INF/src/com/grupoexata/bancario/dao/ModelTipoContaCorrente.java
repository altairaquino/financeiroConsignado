package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanTipoContaCorrente;
import com.grupoexata.bancario.utils.Utils;

public class ModelTipoContaCorrente {

	public static ModelTipoContaCorrente getInstance(){
		return new ModelTipoContaCorrente();
	}
	
	public ArrayList<BeanTipoContaCorrente> getTiposContaCorrente(){
		ArrayList<BeanTipoContaCorrente> logradouros = new ArrayList<BeanTipoContaCorrente>();
		try {
			String sql = " SELECT * FROM VW_TIPOCONTACORRENTE";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			logradouros.addAll(Utils.getObjectsStr(st, BeanTipoContaCorrente.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return logradouros;
		
	}
	
	public BeanTipoContaCorrente getTipoContaCorrente(int tctncodg){
		BeanTipoContaCorrente tipo = null;
		try {
			String sql = "SELECT * FROM VW_TIPOCONTACORRENTE WHERE TCTNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tctncodg);
			
			List<BeanTipoContaCorrente> l = Utils.getObjectsStr(st, BeanTipoContaCorrente.class); 
			
			if (!l.isEmpty())
				tipo = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tipo;
		
	}
}
