package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;

import com.grupoexata.bancario.struts.bean.BeanLigacao;
import com.grupoexata.bancario.utils.Utils;

public class ModelLigacao {

	public static ModelLigacao getInstance(){
		return new ModelLigacao();
	}
	
	public void inserir(BeanLigacao ligacao){
		try {
			String sql = " INSERT INTO LIGACAO(LGNCGEN,LGNC2EN,LGNCGML,LGNCGRL,LGCDOCM,LGYVALR,LGMOBSV)" +
					     " VALUES(?,?,?,?,?,?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(ligacao.getLgncgan()));
			st.setInt(2, Integer.parseInt(ligacao.getLgncgfc()));
			st.setInt(3, Integer.parseInt(ligacao.getLgncgml()));
			st.setInt(4, Integer.parseInt(ligacao.getLgncgrl()));
			st.setString(5, ligacao.getLgcdocm());
			st.setFloat(6, Float.parseFloat(Utils.converteFloatBR(ligacao.getLgyvalr())));
			st.setString(7, ligacao.getLgmobsv());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
				
	}
		
}
