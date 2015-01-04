package com.grupoexata.financeiro.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.financeiro.struts.bean.BeanCaixa;

public class ModelCaixa {
	
	public static ModelCaixa getInstance(){
		return new ModelCaixa();
	}
	
	public BeanCaixa getCaixa(int caxncodg){
		BeanCaixa caixa = null;
		try {
			
			String sql = "SELECT * FROM VW_CAIXA WHERE CAXNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, caxncodg);
			
			List<BeanCaixa> l = Utils.getObjectsStr(st, BeanCaixa.class);
			
			if (!l.isEmpty()){
				caixa = l.get(0);
			}						
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return caixa;
	}
	
	public ArrayList<BeanCaixa> getUltimosCaixas(){
		ArrayList<BeanCaixa> caixas = new ArrayList<BeanCaixa>();
		try {
			
			String sql = "SELECT /*FIRST 30*/ * FROM VW_CAIXA ORDER BY CAXDDATA DESC";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			caixas.addAll(Utils.getObjectsStr(st, BeanCaixa.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return caixas;
	}
		
	public void fecharCaixa(BeanCaixa beanCaixa){
		try {			
			String sql = "UPDATE CAIXA SET " +
					" CAXDFECH = CAST('NOW' AS DATE)," +
					" CAXNRESP = ?" +
					" WHERE CAXNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(beanCaixa.getCaxnresp()));
			st.setInt(2, Integer.parseInt(beanCaixa.getCaxncodg()));
						
			st.executeUpdate();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean caixaFechado(Date data){
		boolean ret = false;
		try {			
			String sql = "SELECT * FROM CAIXA WHERE CAXDDATA = ? AND CAXDFECH IS NOT NULL";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setDate(1, new java.sql.Date(data.getTime()));
			
			ret =  st.executeQuery().next();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public boolean podeFecharCaixa(int caxncodg){
		boolean ret = false;
		try {			
			String sql = " SELECT  CAXNCODG, CAXNEMPR, CAXDDATA, CAXDFECH, CAXYSALD, CAXNRESP FROM   VW_CAIXA" +
					     " WHERE CAXDFECH IS NULL  AND CAXDDATA < (SELECT CAXDDATA FROM CAIXA  WHERE CAXNCODG = ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, caxncodg);
			
			ret =  !st.executeQuery().next();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	

}
