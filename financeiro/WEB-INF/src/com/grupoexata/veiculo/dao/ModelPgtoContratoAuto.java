package com.grupoexata.veiculo.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.veiculo.struts.bean.BeanPgtoContratoAuto;

public class ModelPgtoContratoAuto {

	public static ModelPgtoContratoAuto getInstance(){
		return new ModelPgtoContratoAuto();
	}
	
	public ArrayList<BeanPgtoContratoAuto> getPgtosContratoAuto(){
		ArrayList<BeanPgtoContratoAuto> pgtos = new ArrayList<BeanPgtoContratoAuto>();
		try {
			String sql = " SELECT * FROM VW_PGTOCONTRATOAUTO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			pgtos.addAll(Utils.getObjectsStr(st, BeanPgtoContratoAuto.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return pgtos;
		
	}
	
	public BeanPgtoContratoAuto getPgtoContratoAuto(int pgtncodg){
		BeanPgtoContratoAuto pgto = null;
		try {
			String sql = "SELECT * FROM VW_PGTOCONTRATOAUTO WHERE PGTNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, pgtncodg);
			
			List<BeanPgtoContratoAuto> l = Utils.getObjectsStr(st, BeanPgtoContratoAuto.class); 
			
			if (!l.isEmpty())
				pgto = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return pgto;
		
	}
	
	/*
	public void inserir(BeanPgtoContratoAuto formaLiquidez){
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
	
	public void update(BeanPgtoContratoAuto formaLiquidez){
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
