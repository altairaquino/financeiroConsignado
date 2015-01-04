package com.grupoexata.financeiro.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.financeiro.struts.bean.BeanFormaLiquidez;

public class ModelFormaLiquidez {

	public static ModelFormaLiquidez getInstance(){
		return new ModelFormaLiquidez();
	}
	
	public ArrayList<BeanFormaLiquidez> getFormasDeLiquidez(){
		ArrayList<BeanFormaLiquidez> formas = new ArrayList<BeanFormaLiquidez>();
		try {
			String sql = " SELECT * FROM VW_FORMALIQUIDEZ";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			formas.addAll(Utils.getObjectsStr(st, BeanFormaLiquidez.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return formas;
		
	}
	
	public BeanFormaLiquidez getFormaLiquidez(int flqncodg){
		BeanFormaLiquidez formaLiquidez = null;
		try {
			String sql = "SELECT * FROM VW_FORMALIQUIDEZ WHERE FLQNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, flqncodg);
			
			List<BeanFormaLiquidez> l = Utils.getObjectsStr(st, BeanFormaLiquidez.class); 
			
			if (!l.isEmpty())
				formaLiquidez = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return formaLiquidez;
		
	}
	
	public void inserir(BeanFormaLiquidez formaLiquidez){
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
	
	public void update(BeanFormaLiquidez formaLiquidez){
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
	}
}
