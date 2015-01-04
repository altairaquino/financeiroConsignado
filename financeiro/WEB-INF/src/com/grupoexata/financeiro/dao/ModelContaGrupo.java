package com.grupoexata.financeiro.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.financeiro.struts.bean.BeanContaGrupo;

public class ModelContaGrupo {
	
	public static ModelContaGrupo getInstance(){
		return new ModelContaGrupo();
	}
	
	public BeanContaGrupo getContaGrupo(int cogncodg){
		BeanContaGrupo contas = null;
		try {
			
			String sql = "SELECT * FROM VW_CONTAGRUPO WHERE COGNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, cogncodg);
			
			List<BeanContaGrupo> l = Utils.getObjectsStr(st, BeanContaGrupo.class);
			
			if (!l.isEmpty()){
				contas = l.get(0);
			}						
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contas;
	}
	
	public ArrayList<BeanContaGrupo> getContasDoGrupo(int gpcncodg){
		ArrayList<BeanContaGrupo> contas = new ArrayList<BeanContaGrupo>();
		try {
			
			String sql = "SELECT * FROM VW_CONTAGRUPO WHERE COGNCGGPC = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, gpcncodg);
			
			contas.addAll(Utils.getObjectsStr(st, BeanContaGrupo.class));						
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contas;
	}

	public ArrayList<BeanContaGrupo> getContasDoGrupoAtivas(int gpcncodg){
		ArrayList<BeanContaGrupo> contas = new ArrayList<BeanContaGrupo>();
		try {
			
			String sql = "SELECT * FROM VW_CONTAGRUPO WHERE (COGNCGGPC = ? OR 0 = ?) AND COGLATIV = 'T'";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, gpcncodg);
			st.setInt(2, gpcncodg);
			
			contas.addAll(Utils.getObjectsStr(st, BeanContaGrupo.class));						
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contas;
	}
	
	public void ativaDesativa(BeanContaGrupo beanContaGrupo){
		try {
			String sql = "UPDATE CONTAGRUPO SET COGLATIV = (IIF(COGLATIV = 'F','T','F')) WHERE COGNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(beanContaGrupo.getCogncodg()));
			
			st.executeUpdate();								
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void inserir(BeanContaGrupo conta){
		try {
			String sql = " INSERT INTO CONTAGRUPO (COGNCGGPC, COGCDESC) VALUES (?, ?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(conta.getCogncggpc()));
			st.setString(2, conta.getCogcdesc().toUpperCase());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(BeanContaGrupo conta){
		try {
			String sql = " UPDATE CONTAGRUPO SET COGNCGGPC = ?, " +
						 " COGCDESC = ? " +
						 " WHERE COGNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(conta.getCogncggpc()));
			st.setString(2, conta.getCogcdesc().toUpperCase());
			st.setInt(3, Integer.parseInt(conta.getCogncodg()));
			
			st.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
