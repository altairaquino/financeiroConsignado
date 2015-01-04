package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanFuncionarioAgencia;
import com.grupoexata.bancario.utils.Utils;

public class ModelFuncionarioAgencia {

	public static ModelFuncionarioAgencia getInstance(){
		return new ModelFuncionarioAgencia();
	}
	
	public ArrayList<BeanFuncionarioAgencia> getFuncionariosDaAgencia(int enncodg){
		ArrayList<BeanFuncionarioAgencia> funcs = new ArrayList<BeanFuncionarioAgencia>();
		try {
			String sql = " SELECT * FROM VW_FUNCIONARIOAGENCIA WHERE FCANCGAG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			
			funcs.addAll(Utils.getObjectsStr(st, BeanFuncionarioAgencia.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return funcs;
		
	}
	
	public BeanFuncionarioAgencia getFuncionarioAgencia(int tpncodg){
		BeanFuncionarioAgencia func = null;
		
		try {
			String sql = "SELECT * FROM VW_FUNCIONARIOAGENCIA WHERE FCANCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tpncodg);
			
			List<BeanFuncionarioAgencia> l = Utils.getObjectsStr(st, BeanFuncionarioAgencia.class); 
			
			if (!l.isEmpty())
				func = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return func;
		
	}

	public void inserir(BeanFuncionarioAgencia beanFuncionarioAgencia) {
		try {
			String sql = " INSERT INTO FUNCIONARIOAGENCIA(FCANCGTFA, FCANCGEN, FCANC2EN, FCANMETA, FCANPERC)" +
					     " VALUES( ?, ?, ?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(beanFuncionarioAgencia.getFcancgtfa()));
			st.setInt(2, Integer.parseInt(beanFuncionarioAgencia.getFcancgag()));
			st.setInt(3, Integer.parseInt(beanFuncionarioAgencia.getFcancgfc()));
			st.setFloat(4, Float.parseFloat(Utils.converteFloatBR(beanFuncionarioAgencia.getFcanmeta())));
			st.setFloat(5, Float.parseFloat(Utils.converteFloatBR(beanFuncionarioAgencia.getFcanperc())));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void update(BeanFuncionarioAgencia beanFuncionarioAgencia) {
		try {
			String sql = " UPDATE FUNCIONARIOAGENCIA SET FCANCGTFA = ?, FCANCGEN = ?, FCANC2EN = ?, FCANMETA = ?, FCANPERC = ?, FCALATIV = ?"+
		                 " WHERE FCANCODG = ?";

			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(beanFuncionarioAgencia.getFcancgtfa()));
			st.setInt(2, Integer.parseInt(beanFuncionarioAgencia.getFcancgag()));
			st.setInt(3, Integer.parseInt(beanFuncionarioAgencia.getFcancgfc()));
			st.setFloat(4, Float.parseFloat(Utils.converteFloatBR(beanFuncionarioAgencia.getFcanmeta())));
			st.setFloat(5, Float.parseFloat(Utils.converteFloatBR(beanFuncionarioAgencia.getFcanperc())));
			st.setString(6, beanFuncionarioAgencia.getFcalativ());
			st.setInt(7, Integer.parseInt(beanFuncionarioAgencia.getFcancodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
