package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.grupoexata.bancario.struts.bean.BeanOperacaoGrupo;
import com.grupoexata.bancario.utils.Utils;


public class ModelOperacaoGrupo {
	
	public static ModelOperacaoGrupo getInstance(){
		return new ModelOperacaoGrupo();
	}
	
	public BeanOperacaoGrupo getOperacaoGrupo(int opncodg){
		BeanOperacaoGrupo OperacaoGrupo = null;
		try {
			String sql = "SELECT * FROM VW_OperacaoGrupo WHERE GONCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, opncodg);
			
			OperacaoGrupo = Utils.getObjectsStrFirst(st,BeanOperacaoGrupo.class);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return OperacaoGrupo;
	}
	
	public void delete(int goncodg) {
		try {
			String sql = "DELETE FROM OPERACAOGRUPO WHERE GONCODG = (?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, goncodg);
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public ArrayList<BeanOperacaoGrupo> getOperacoes(){
		ArrayList<BeanOperacaoGrupo> operacoes = new ArrayList<BeanOperacaoGrupo>();
		try {
			String sql = "SELECT * FROM VW_OperacaoGrupo";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			operacoes.addAll(Utils.getObjectsStr(st,BeanOperacaoGrupo.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return operacoes;
	}
	
	
	
	public ArrayList<BeanOperacaoGrupo> getOperacoesDoGrupo(int grncodg){
		ArrayList<BeanOperacaoGrupo> operacoes = new ArrayList<BeanOperacaoGrupo>();
		try {
			String sql = "SELECT * FROM VW_OperacaoGrupo WHERE GONCGGR = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, grncodg);
			
			operacoes.addAll(Utils.getObjectsStr(st,BeanOperacaoGrupo.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return operacoes;
	}
	
	
	
	public void inserir(BeanOperacaoGrupo operacaoGrupo){
		try {
			String sql = "INSERT INTO OperacaoGrupo(GONCGOP, GONCGGR)" +
					     " VALUES (?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(operacaoGrupo.getGoncgop()));
			st.setInt(2, Integer.parseInt(operacaoGrupo.getGoncggr()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(BeanOperacaoGrupo operacaoGrupo){
		try {
			String sql = " UPDATE OperacaoGrupo set GONCGOP = ?, GONCGGR = ?" +
					     " WHERE GONCODG = (?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(operacaoGrupo.getGoncgop()));
			st.setInt(2, Integer.parseInt(operacaoGrupo.getGoncggr()));
			st.setInt(3, Integer.parseInt(operacaoGrupo.getGoncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
