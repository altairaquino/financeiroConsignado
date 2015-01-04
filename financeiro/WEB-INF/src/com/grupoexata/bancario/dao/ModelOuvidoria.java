package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;

import com.grupoexata.bancario.struts.bean.BeanOuvidoria;

public class ModelOuvidoria {

	public static ModelOuvidoria getInstance(){
		return new ModelOuvidoria();
	}
	
	public void insert(BeanOuvidoria beanOuvidoria){
		try {
			String sql = "INSERT INTO OUVIDORIA(OUVNCGEN,OUVNCGTIS,OUVNCGSET,OUVLIDET,OUVCDESC, OUVCASSU)" +
					"VALUES(?, ?, ?, ?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(beanOuvidoria.getOuvncgen()));
			st.setInt(2, Integer.parseInt(beanOuvidoria.getOuvncgtis()));
			st.setInt(3, Integer.parseInt(beanOuvidoria.getOuvncgset()));
			st.setString(4, beanOuvidoria.getOuvlidet());
			st.setString(5, beanOuvidoria.getOuvcdesc());
			st.setString(6, beanOuvidoria.getOuvcassu());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
