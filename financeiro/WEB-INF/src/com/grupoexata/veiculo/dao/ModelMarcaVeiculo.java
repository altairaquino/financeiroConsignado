package com.grupoexata.veiculo.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.veiculo.struts.bean.BeanMarcaVeiculo;

public class ModelMarcaVeiculo {

	public static ModelMarcaVeiculo getInstance(){
		return new ModelMarcaVeiculo();
	}
	
	public ArrayList<BeanMarcaVeiculo> getMarcasVeiculo(){
		ArrayList<BeanMarcaVeiculo> marcas = new ArrayList<BeanMarcaVeiculo>();
		try {
			String sql = " SELECT * FROM VW_MARCAVEICULO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			marcas.addAll(Utils.getObjectsStr(st, BeanMarcaVeiculo.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return marcas;
		
	}
	
	public BeanMarcaVeiculo getMarcaVeiculo(int mvencodg){
		BeanMarcaVeiculo marca = null;
		try {
			String sql = "SELECT * FROM VW_MARCAVEICULO WHERE MVENCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, mvencodg);
			
			List<BeanMarcaVeiculo> l = Utils.getObjectsStr(st, BeanMarcaVeiculo.class); 
			
			if (!l.isEmpty())
				marca = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return marca;
		
	}
	
	/*
	public void inserir(BeanMarcaVeiculo formaLiquidez){
		try {
			String sql = "INSERT INTO FORMALIQUIDEZ (FLQCDESC, FLQLIMED) VALUES (?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, formaLiquidez.getFlqcdesc().toUpperCase());
			st.setString(2, formaLiquidez.getFlqlimed());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(BeanMarcaVeiculo formaLiquidez){
		try {
			String sql = " UPDATE FORMALIQUIDEZ " +
					     " SET FLQCDESC = ?," +
					     " FLQLIMED = ?"+
					     " WHERE FLQNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, formaLiquidez.getFlqcdesc().toUpperCase());
			st.setString(2, formaLiquidez.getFlqlimed());
			st.setInt(3, Integer.parseInt(formaLiquidez.getFlqncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
