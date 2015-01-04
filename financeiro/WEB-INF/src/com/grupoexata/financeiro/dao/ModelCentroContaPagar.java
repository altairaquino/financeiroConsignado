package com.grupoexata.financeiro.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.financeiro.struts.bean.BeanCentroContaPagar;

public class ModelCentroContaPagar {

	public static ModelCentroContaPagar getInstance(){
		return new ModelCentroContaPagar();
	}
	
	public ArrayList<BeanCentroContaPagar> getCentrosContaPagar(int cpncodg){
		ArrayList<BeanCentroContaPagar> centros = new ArrayList<BeanCentroContaPagar>();
		try {
			String sql = " SELECT * FROM VW_CENTROCONTAPAGAR WHERE CRCNCGCP = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, cpncodg);
			
			centros.addAll(Utils.getObjectsStr(st, BeanCentroContaPagar.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return centros;
		
	}
	
	public BeanCentroContaPagar getCentroContaPagar(int crmncodg){
		BeanCentroContaPagar centros = null;
		try {
			String sql = "SELECT * FROM VW_CENTROCONTAPAGAR WHERE CRCNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, crmncodg);
			
			List<BeanCentroContaPagar> l = Utils.getObjectsStr(st, BeanCentroContaPagar.class); 
			
			if (!l.isEmpty())
				centros = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return centros;
		
	}
	
	public void inserir(BeanCentroContaPagar centroContaPagar){
		try {
			String sql = " INSERT INTO CENTROCONTAPAGAR(CRCNCGCP,CRCNCGCR)" +
						 " VALUES(?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(centroContaPagar.getCrcncgcp()));
			st.setInt(2, Integer.parseInt(centroContaPagar.getCrcncgcr()));
			
			st.executeUpdate();
			
			st.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void excluir(BeanCentroContaPagar beanCentroContaPagar) {
		try {
			String sql = "DELETE FROM CENTROCONTAPAGAR WHERE CRCNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(beanCentroContaPagar.getCrcncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
