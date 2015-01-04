package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanGrupoEmail;
import com.grupoexata.bancario.utils.Utils;

public class ModelGrupoEmail {

	public static ModelGrupoEmail getInstance(){
		return new ModelGrupoEmail();
	}
	
	public ArrayList<BeanGrupoEmail> getGrupoEmails(){
		ArrayList<BeanGrupoEmail> GrupoEmails = new ArrayList<BeanGrupoEmail>();
		try {
			String sql = " SELECT * FROM VW_GRUPOEMAIL";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			GrupoEmails.addAll(Utils.getObjectsStr(st, BeanGrupoEmail.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return GrupoEmails;
		
	}
	
	public BeanGrupoEmail getGrupoEmail(int gemncodg){
		BeanGrupoEmail GrupoEmail = null;
		
		try {
			String sql = "SELECT * FROM VW_GRUPOEMAIL WHERE GEMNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, gemncodg);
			
			List<BeanGrupoEmail> l = Utils.getObjectsStr(st, BeanGrupoEmail.class);
			
			if (!l.isEmpty())
				GrupoEmail = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return GrupoEmail;
		
	}
	
	public void inserir(BeanGrupoEmail beanGrupoEmail){
		try {
			String sql = "INSERT INTO GRUPOEMAIL (GEMCDESC) VALUES (?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, beanGrupoEmail.getGemcdesc().toUpperCase());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(BeanGrupoEmail beanGrupoEmail){
		try {
			String sql = "UPDATE GRUPOEMAIL SET GEMCDESC = ? WHERE GEMNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, beanGrupoEmail.getGemcdesc().toUpperCase());
			st.setInt(2, Integer.parseInt(beanGrupoEmail.getGemncodg()));
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
