package com.grupoexata.veiculo.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.veiculo.struts.bean.BeanTipoPendContratoAuto;

public class ModelTipoPendContratoAuto {

	public static ModelTipoPendContratoAuto getInstance(){
		return new ModelTipoPendContratoAuto();
	}
	
	public ArrayList<BeanTipoPendContratoAuto> getTiposPendContratoAuto(){
		ArrayList<BeanTipoPendContratoAuto> pendencias = new ArrayList<BeanTipoPendContratoAuto>();
		try {
			String sql = " SELECT * FROM VW_TIPOPENDCONTRATOAUTO";			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			pendencias.addAll(Utils.getObjectsStr(st, BeanTipoPendContratoAuto.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return pendencias;		
	}
	
	public BeanTipoPendContratoAuto getTipoPendContratoAuto(int tpdncodg){
		BeanTipoPendContratoAuto tipo = null;
		try {
			String sql = "SELECT * FROM VW_TIPOPENDCONTRATOAUTO WHERE TPDNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tpdncodg);
			
			List<BeanTipoPendContratoAuto> l = Utils.getObjectsStr(st, BeanTipoPendContratoAuto.class); 
			
			if (!l.isEmpty())
				tipo = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tipo;
		
	}
	
	/*
	public void inserir(BeanTipoPendContratoAuto formaLiquidez){
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
	
	public void update(BeanTipoPendContratoAuto formaLiquidez){
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
