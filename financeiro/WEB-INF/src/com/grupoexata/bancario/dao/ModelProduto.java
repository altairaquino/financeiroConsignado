package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanProduto;
import com.grupoexata.bancario.utils.Utils;

public class ModelProduto {

	public static ModelProduto getInstance(){
		return new ModelProduto();
	}
	
	public ArrayList<BeanProduto> getProdutos(){
		ArrayList<BeanProduto> produtos = new ArrayList<BeanProduto>();
		try {
			String sql = " SELECT * FROM VW_PRODUTO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			produtos.addAll(Utils.getObjectsStr(st, BeanProduto.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return produtos;
		
	}
	
	public BeanProduto getProduto(int pdncodg){
		BeanProduto produto = null;
		
		try {
			String sql = "SELECT * FROM VW_PRODUTO WHERE PDNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, pdncodg);
			
			List<BeanProduto> l = Utils.getObjectsStr(st, BeanProduto.class); 
			
			if (!l.isEmpty())
				produto = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return produto;
		
	}

	public void inserir(BeanProduto produto) {
		try {
			String sql = "INSERT INTO PRODUTO (PDNCGBC, PDCDESC, PDCABRV, PDNNUMR) VALUES (?,?,?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(produto.getPdncgbc()));
			st.setString(2, produto.getPdcdesc().toUpperCase());
			st.setString(3, produto.getPdcabrv().toUpperCase());
			st.setInt(4, Integer.parseInt(produto.getPdnnumr()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void update(BeanProduto produto) {
		try {
			String sql = "UPDATE PRODUTO SET PDNCGBC = ?, PDCDESC = ?, PDCABRV = ?, PDNNUMR = ? WHERE PDNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(produto.getPdncgbc()));
			st.setString(2, produto.getPdcdesc().toUpperCase());
			st.setString(3, produto.getPdcabrv().toUpperCase());
			st.setInt(4, Integer.parseInt(produto.getPdnnumr()));
			st.setInt(5, Integer.parseInt(produto.getPdncodg()));
			
			
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
