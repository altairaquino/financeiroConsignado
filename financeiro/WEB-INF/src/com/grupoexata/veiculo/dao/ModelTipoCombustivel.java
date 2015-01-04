package com.grupoexata.veiculo.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.veiculo.struts.bean.BeanTipoCombustivel;

public class ModelTipoCombustivel {

	public static ModelTipoCombustivel getInstance(){
		return new ModelTipoCombustivel();
	}
	
	public ArrayList<BeanTipoCombustivel> getTiposCombustivel(){
		ArrayList<BeanTipoCombustivel> combustivel = new ArrayList<BeanTipoCombustivel>();
		try {
			String sql = " SELECT * FROM VW_TIPOCOMBUSTIVEL";			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			combustivel.addAll(Utils.getObjectsStr(st, BeanTipoCombustivel.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return combustivel;		
	}
	
	public BeanTipoCombustivel getTipoCombustivel(int tconcodg){
		BeanTipoCombustivel combustivel = null;
		try {
			String sql = "SELECT * FROM VW_TIPOCOMBUSTIVEL WHERE TCONCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tconcodg);
			
			List<BeanTipoCombustivel> l = Utils.getObjectsStr(st, BeanTipoCombustivel.class); 
			
			if (!l.isEmpty())
				combustivel = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return combustivel;
		
	}
	
	/*
	public void inserir(BeanTipoCombustivel formaLiquidez){
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
	
	public void update(BeanTipoCombustivel formaLiquidez){
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
