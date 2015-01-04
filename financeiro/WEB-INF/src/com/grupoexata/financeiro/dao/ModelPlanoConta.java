package com.grupoexata.financeiro.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.financeiro.struts.bean.BeanPlanoConta;

public class ModelPlanoConta {
	
	public static ModelPlanoConta getInstance(){
		return new ModelPlanoConta();
	}
	
	public BeanPlanoConta getPlanoConta(int plcncodg){
		BeanPlanoConta plano = null;
		try {
			
			String sql = "SELECT * FROM VW_PLANOCONTA WHERE PLCNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, plcncodg);
			
			List<BeanPlanoConta> l = Utils.getObjectsStr(st, BeanPlanoConta.class); 
			
			if (!l.isEmpty()){
				plano = l.get(0);
			}						
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plano;
	}
	
	public ArrayList<BeanPlanoConta> getPlanosContaFilho(int pai){
		ArrayList<BeanPlanoConta> centros = new ArrayList<BeanPlanoConta>();
		try {
			
			String sql = "SELECT * FROM VW_PLANOCONTA WHERE PLCNPAI = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, pai);
						
			centros.addAll(Utils.getObjectsStr(st, BeanPlanoConta.class));	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return centros;
	}
	
	public ArrayList<BeanPlanoConta> getPlanosContaPorTipo(int plntipo){
		ArrayList<BeanPlanoConta> centros = new ArrayList<BeanPlanoConta>();
		try {
			
			String sql = "SELECT * FROM VW_PLANOCONTA P1 WHERE P1.PLCNTIPO = ? " +
					" AND NOT EXISTS(" +
					"       SELECT * FROM VW_PLANOCONTA P2 WHERE" +
					"       P2.PLCNPAI = P1.PLCNCODG" +
					" )ORDER BY P1.PLCCDESC";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, plntipo);
						
			centros.addAll(Utils.getObjectsStr(st, BeanPlanoConta.class));	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return centros;
	}
	
	public ArrayList<BeanPlanoConta> getPlanosContaTodos(){
		ArrayList<BeanPlanoConta> centros = new ArrayList<BeanPlanoConta>();
		try {
			
			String sql = "SELECT * FROM VW_PLANOCONTA";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
						
			centros.addAll(Utils.getObjectsStr(st, BeanPlanoConta.class));	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return centros;
	}
	
	public ArrayList<BeanPlanoConta> getPlanosContaSemFilho(){
		ArrayList<BeanPlanoConta> centros = new ArrayList<BeanPlanoConta>();
		try {
			
			String sql = " SELECT * FROM  VW_PLANOCONTA P1" +
					" WHERE NOT EXISTS(" +
					"       SELECT * FROM VW_PLANOCONTA P2 WHERE" +
					"       P2.PLCNPAI = P1.PLCNCODG" +
					" ) ORDER BY PLCNTIPO";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
						
			centros.addAll(Utils.getObjectsStr(st, BeanPlanoConta.class));	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return centros;
	}
	
	public ArrayList<BeanPlanoConta> getPlanosContaSemFilhoPorTipo(int plcntipo){
		ArrayList<BeanPlanoConta> centros = new ArrayList<BeanPlanoConta>();
		try {
			
			String sql = " SELECT * FROM  VW_PLANOCONTA P1" +
					" WHERE NOT EXISTS(" +
					"       SELECT * FROM VW_PLANOCONTA P2 WHERE" +
					"       P2.PLCNPAI = P1.PLCNCODG" +
					" )" +
					" AND PLCNTIPO = ? " +
					" ORDER BY PLCNTIPO";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, plcntipo);
						
			centros.addAll(Utils.getObjectsStr(st, BeanPlanoConta.class));	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return centros;
	}
	
	public String arvoreDoPlano(){
		String arvore = "d = new dTree('d');\n " +
				"d.add(0,-1,'Plano de Contas');\n";
		ArrayList<BeanPlanoConta> contas = this.getPlanosContaTodos();
		for (BeanPlanoConta beanPlanoConta : contas) {
			arvore += "d.add("+beanPlanoConta.getPlcncodg()+","+(beanPlanoConta.getPlcnpai() == null?"0":beanPlanoConta.getPlcnpai())+
			",'"+beanPlanoConta.getPlccdesc()+"',\"#\");\n";
		}
		arvore += "document.write(d);";
		return arvore;
	}
	
	public ArrayList<BeanPlanoConta> getPlanosContaPai(){
		ArrayList<BeanPlanoConta> centros = new ArrayList<BeanPlanoConta>();
		try {
			
			String sql = "SELECT * FROM VW_PLANOCONTA WHERE PLCNPAI IS NULL";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
						
			centros.addAll(Utils.getObjectsStr(st, BeanPlanoConta.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return centros;
	}
	
	public void inserir(BeanPlanoConta plano){
		try {
			String sql = "INSERT INTO PLANOCONTA(PLCNEMPR,PLCNPAI,PLCCDESC,PLCNTIPO)VALUES(?,?,?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(plano.getPlcnempr()));
			st.setInt(2, Integer.parseInt(plano.getPlcnpai()));
			st.setString(3, plano.getPlccdesc().toUpperCase());
			st.setInt(4, Integer.parseInt(plano.getPlcntipo()));
			
			st.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<BeanPlanoConta> pesquisa(String plccdesc) {
		ArrayList<BeanPlanoConta> planos = new ArrayList<BeanPlanoConta>();
		try {
			
			String sql = "SELECT * FROM VW_PLANOCONTA WHERE UPPER(PLCCDESC) LIKE '%"+plccdesc.replaceAll("[ ]", "%").toUpperCase()+"%'";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
						
			planos.addAll(Utils.getObjectsStr(st, BeanPlanoConta.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return planos;
	}

}
