package com.grupoexata.financeiro.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.financeiro.struts.bean.BeanCentroMovCaixa;

public class ModelCentroMovCaixa {

	public static ModelCentroMovCaixa getInstance(){
		return new ModelCentroMovCaixa();
	}
	
	public ArrayList<BeanCentroMovCaixa> getCentrosMovCaixa(int movncodg){
		ArrayList<BeanCentroMovCaixa> centros = new ArrayList<BeanCentroMovCaixa>();
		try {
			String sql = " SELECT * FROM VW_CENTROMOVCAIXA WHERE CRMNMOVC = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, movncodg);
			
			centros.addAll(Utils.getObjectsStr(st, BeanCentroMovCaixa.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return centros;
		
	}
	
	public BeanCentroMovCaixa getCentroMovCaixa(int crmncodg){
		BeanCentroMovCaixa centros = null;
		try {
			String sql = "SELECT * FROM VW_CENTROMOVCAIXA WHERE CRMNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, crmncodg);
			
			List<BeanCentroMovCaixa> l = Utils.getObjectsStr(st, BeanCentroMovCaixa.class); 
			
			if (!l.isEmpty())
				centros = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return centros;
		
	}
	
	public void inserir(BeanCentroMovCaixa centroMovCaixa){
		try {
			String sql = " INSERT INTO CENTROMOVCAIXA(CRMNMOVC,CRMNCGCR)" +
						 " VALUES(?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(centroMovCaixa.getCrmnmovc()));
			st.setInt(2, Integer.parseInt(centroMovCaixa.getCrmncgcr()));
			
			st.executeUpdate();
			
			st.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(BeanCentroMovCaixa beanCentroMovCaixa) {
		try {
			String sql = "DELETE FROM CENTROMOVCAIXA WHERE CRMNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(beanCentroMovCaixa.getCrmncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
