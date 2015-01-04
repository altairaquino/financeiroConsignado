package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanTabelaProduto;
import com.grupoexata.bancario.utils.Utils;

public class ModelTabelaProduto {

	public static ModelTabelaProduto getInstance(){
		return new ModelTabelaProduto();
	}
	
	public ArrayList<BeanTabelaProduto> getTabelaDoProduto(int pdncodg){
		ArrayList<BeanTabelaProduto> produtos = new ArrayList<BeanTabelaProduto>();
		try {
			String sql = " SELECT * FROM VW_TABELAPRODUTO WHERE TPNCGPD = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, pdncodg);
			
			produtos.addAll(Utils.getObjectsStr(st, BeanTabelaProduto.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return produtos;
		
	}
	
	public BeanTabelaProduto getTabelaProduto(int tpncodg){
		BeanTabelaProduto produto = null;
		
		try {
			String sql = "SELECT * FROM VW_TABELAPRODUTO WHERE TPNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tpncodg);
			
			List<BeanTabelaProduto> l = Utils.getObjectsStr(st, BeanTabelaProduto.class); 
			
			if (!l.isEmpty())
				produto = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return produto;
		
	}

	public void inserir(BeanTabelaProduto tabelaProduto) {
		try {
			String sql = " INSERT INTO TABELAPRODUTO(TPNCGPD,TPCNUMR,TPCDESC,TPNQTPC,TPNCARE,TPNPRAZ,TPNIMPT, TPLZERA, TPNEXAT)" +
					     " VALUES( ?, ?,?, ?,?,?,?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(tabelaProduto.getTpncgpd()));
			st.setString(2, tabelaProduto.getTpcnumr());
			st.setString(3, tabelaProduto.getTpcdesc().toUpperCase());
			st.setInt(4, Integer.parseInt(tabelaProduto.getTpnqtpc()));
			st.setInt(5, Integer.parseInt(tabelaProduto.getTpncare()));
			st.setInt(6, Integer.parseInt(tabelaProduto.getTpnpraz()));
			st.setFloat(7, Float.parseFloat(Utils.converteFloatBR(tabelaProduto.getTpnimpt())));
			st.setString(8, tabelaProduto.getTplzera());
			st.setFloat(9, Float.parseFloat(Utils.converteFloatBR(tabelaProduto.getTpnexat())));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void update(BeanTabelaProduto tabelaProduto) {
		try {
			String sql = " UPDATE TABELAPRODUTO SET TPNCGPD = ?,TPCNUMR = ?,TPCDESC = ?,TPNQTPC = ?,TPNCARE = ?," +
					     " TPNPRAZ = ?,TPNIMPT = ?, TPLZERA = ?,  TPNEXAT = ?" +
		                 " WHERE TPNCODG =  ?";

			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(tabelaProduto.getTpncgpd()));
			st.setString(2, tabelaProduto.getTpcnumr());
			st.setString(3, tabelaProduto.getTpcdesc().toUpperCase());
			st.setInt(4, Integer.parseInt(tabelaProduto.getTpnqtpc()));
			st.setInt(5, Integer.parseInt(tabelaProduto.getTpncare()));
			st.setInt(6, Integer.parseInt(tabelaProduto.getTpnpraz()));
			st.setFloat(7, Float.parseFloat(Utils.converteFloatBR(tabelaProduto.getTpnimpt())));
			st.setString(8, tabelaProduto.getTplzera());
			st.setFloat(9, Float.parseFloat(Utils.converteFloatBR(tabelaProduto.getTpnexat())));
			st.setInt(10, Integer.parseInt(tabelaProduto.getTpncodg()));			
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
