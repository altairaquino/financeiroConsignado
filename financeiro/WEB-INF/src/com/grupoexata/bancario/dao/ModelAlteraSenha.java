package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;

public class ModelAlteraSenha {

	public static ModelAlteraSenha getInstance(){
		return new ModelAlteraSenha();
	}
	
	public void registrarAlteracao(int enncodg) {
		try {
			String sql = "INSERT INTO ALTERASENHA (ALSNCGEN)VALUES(?) ";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
				st.setInt(1, enncodg);
				st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}
	
	public boolean isTimeChangePassword(int enncodg) {
		boolean ret = false;
		try {
			String sql = " SELECT FIRST 1 ALSNCODG, ALSNCGEN, ALSCNMEN, ALSDALTE, ALSDPROX, ALSNDIAS, ALSDCADT" +
					     " FROM VW_ALTERASENHA" +
					     " WHERE ALSNCGEN = ?" +
					     " AND ALSDPROX <= 'NOW' " +
					     " AND ALSNCODG = (SELECT MAX(ALSNCODG) FROM ALTERASENHA WHERE ALSNCGEN = ?)" +
					     " ORDER BY ALSNCODG DESC";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			st.setInt(2, enncodg);
				
			ret = st.executeQuery().next();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
		
	}
	
	
}
