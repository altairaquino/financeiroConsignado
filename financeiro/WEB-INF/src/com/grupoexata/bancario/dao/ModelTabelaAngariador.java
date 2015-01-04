package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanTabelaAngariador;
import com.grupoexata.bancario.utils.Utils;

public class ModelTabelaAngariador {

	public static ModelTabelaAngariador getInstance(){
		return new ModelTabelaAngariador();
	}
	
	public ArrayList<BeanTabelaAngariador> getTabelasDoAngariador(int tgncgen){
		ArrayList<BeanTabelaAngariador> tabelas = new ArrayList<BeanTabelaAngariador>();
		try {
			String sql = " SELECT * FROM VW_TABELA_ANGARIADOR WHERE TGNCGEN = ?" +
					     " ORDER BY TGNCGPD,TGNCGTP";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tgncgen);
			
			tabelas.addAll(Utils.getObjectsStr(st, BeanTabelaAngariador.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tabelas;
		
	}
	
	public ArrayList<BeanTabelaAngariador> getTabelasDoProduto(int tgncgtp){
		ArrayList<BeanTabelaAngariador> tabelas = new ArrayList<BeanTabelaAngariador>();
		try {
			String sql = " SELECT * FROM VW_TABELA_ANGARIADOR WHERE TGNCGTP = ?" +
					     " ORDER BY TGCNMEN";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tgncgtp);
			
			tabelas.addAll(Utils.getObjectsStr(st, BeanTabelaAngariador.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tabelas;
		
	}
	
	public BeanTabelaAngariador getTabelaAngariador(int tgncodg){
		BeanTabelaAngariador tabelaAngariador = null;
		try {
			String sql = "SELECT * FROM VW_TABELA_ANGARIADOR WHERE TGNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tgncodg);
			
			List<BeanTabelaAngariador> l = Utils.getObjectsStr(st, BeanTabelaAngariador.class); 
			
			if (!l.isEmpty())
				tabelaAngariador = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tabelaAngariador;	
	}
	
	public void update(BeanTabelaAngariador tabelaAngariador){
		try {
			String sql = "UPDATE TABELA_ANGARIADOR SET TGNCOMS = ? WHERE TGNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setFloat(1, Float.parseFloat(Utils.converteFloatBR(tabelaAngariador.getTgncoms())));
			st.setInt(2, Integer.parseInt(tabelaAngariador.getTgncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
