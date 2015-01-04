package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanTabelaFuncionario;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ModelTabelaFuncionario {

	public static ModelTabelaFuncionario getInstance(){
		return new ModelTabelaFuncionario();
	}
	
	public ArrayList<BeanTabelaFuncionario> getTabelasDoFuncionario(int fcancodg){
		ArrayList<BeanTabelaFuncionario> tabelas = new ArrayList<BeanTabelaFuncionario>();
		try {
			String sql = " SELECT * FROM VW_TABELAFUNCIONARIO WHERE TFCNCGFCA = ?" +
					     " ORDER BY TFCNCGPD,TFCNCGTP";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, fcancodg);
			
			tabelas.addAll(Utils.getObjectsStr(st, BeanTabelaFuncionario.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tabelas;
		
	}
	
	
	public BeanTabelaFuncionario getTabelaFuncionario(int tfcncodg){
		BeanTabelaFuncionario tabelaFuncionario = null;
		try {
			String sql = "SELECT * FROM VW_TABELAFUNCIONARIO WHERE TFCNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tfcncodg);
			
			List<BeanTabelaFuncionario> l = Utils.getObjectsStr(st, BeanTabelaFuncionario.class); 
			
			if (!l.isEmpty())
				tabelaFuncionario = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tabelaFuncionario;	
	}
	
	public void update(BeanTabelaFuncionario beanTabelaFuncionario){
		try {
			String sql = " UPDATE TABELAFUNCIONARIO SET " +
					     " TFCNCOMS = ?," +
					     " TFCNCM70 = ?," +
					     " TFCNCM100 = ?, " +
					     " TFCNCM150 = ?  " +
					     " WHERE TFCNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			if (ValidaObjeto.validaFloat(Utils.converteFloatBR(beanTabelaFuncionario.getTfcncoms()))){
				st.setFloat(1, Float.parseFloat(Utils.converteFloatBR(beanTabelaFuncionario.getTfcncoms())));
			}else{
				st.setFloat(1, 0f);
			}
			if (ValidaObjeto.validaFloat(Utils.converteFloatBR(beanTabelaFuncionario.getTfcncm70()))){
				st.setFloat(2, Float.parseFloat(Utils.converteFloatBR(beanTabelaFuncionario.getTfcncm70())));
			}else{
				st.setFloat(2, 0f);
			}
			if (ValidaObjeto.validaFloat(Utils.converteFloatBR(beanTabelaFuncionario.getTfcncm100()))){
				st.setFloat(3, Float.parseFloat(Utils.converteFloatBR(beanTabelaFuncionario.getTfcncm100())));
			}else{
				st.setFloat(3, 0f);
			}
			
			if (ValidaObjeto.validaFloat(Utils.converteFloatBR(beanTabelaFuncionario.getTfcncm150()))){
				st.setFloat(4, Float.parseFloat(Utils.converteFloatBR(beanTabelaFuncionario.getTfcncm150())));
			}else{
				st.setFloat(4, 0f);
			}
			
			st.setInt(5, Integer.parseInt(beanTabelaFuncionario.getTfcncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
