package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanTipoAngariador;
import com.grupoexata.bancario.utils.Utils;

public class ModelTipoAngariador {

	public static ModelTipoAngariador getInstance(){
		return new ModelTipoAngariador();
	}
	
	public ArrayList<BeanTipoAngariador> getTiposAngariador(){
		ArrayList<BeanTipoAngariador> tipos = new ArrayList<BeanTipoAngariador>();
		try {
			String sql = " SELECT * FROM VW_TIPOANGARIADOR";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			tipos.addAll(Utils.getObjectsStr(st, BeanTipoAngariador.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tipos;
		
	}
	
	public BeanTipoAngariador getTipoAngariador(int tctncodg){
		BeanTipoAngariador tipo = null;
		try {
			String sql = "SELECT * FROM VW_TIPOANGARIADOR WHERE TNNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tctncodg);
			
			List<BeanTipoAngariador> l = Utils.getObjectsStr(st, BeanTipoAngariador.class); 
			
			if (!l.isEmpty())
				tipo = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tipo;
		
	}
	
}
