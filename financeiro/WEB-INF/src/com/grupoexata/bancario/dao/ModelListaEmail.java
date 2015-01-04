package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanListaEmail;
import com.grupoexata.bancario.utils.Utils;

public class ModelListaEmail {

	public static ModelListaEmail getInstance(){
		return new ModelListaEmail();
	}
	
	public ArrayList<BeanListaEmail> getListaEmails(){
		ArrayList<BeanListaEmail> ListaEmails = new ArrayList<BeanListaEmail>();
		try {
			String sql = " SELECT * FROM VW_LISTAEMAIL";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			ListaEmails.addAll(Utils.getObjectsStr(st, BeanListaEmail.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return ListaEmails;
		
	}
	
	public ArrayList<BeanListaEmail> getListaEmailPorGrupo(int gemncodg){
		ArrayList<BeanListaEmail> ListaEmails = new ArrayList<BeanListaEmail>();
		try {
			String sql = " SELECT * FROM VW_LISTAEMAIL WHERE LTENCGGEM = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, gemncodg);
			
			ListaEmails.addAll(Utils.getObjectsStr(st, BeanListaEmail.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return ListaEmails;
		
	}
	
	public ArrayList<BeanListaEmail> getListaEmailPorGrupoParaEnvio(int gemncodg){
		ArrayList<BeanListaEmail> ListaEmails = new ArrayList<BeanListaEmail>();
		try {
			String sql = " SELECT * FROM VW_LISTAEMAIL WHERE LTENCGGEM = ? AND LTELATIV = 'T' AND LTECMAIL IS NOT NULL";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, gemncodg);
			
			ListaEmails.addAll(Utils.getObjectsStr(st, BeanListaEmail.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return ListaEmails;
		
	}
	
	public BeanListaEmail getListaEmail(int ltencodg){
		BeanListaEmail ListaEmail = null;		
		try {
			String sql = "SELECT * FROM VW_LISTAEMAIL WHERE LTENCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ltencodg);
			
			List<BeanListaEmail> l = Utils.getObjectsStr(st, BeanListaEmail.class); 
			
			if (!l.isEmpty())
				ListaEmail = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ListaEmail;
		
	}

	public void inserir(BeanListaEmail beanListaEmail) {
		try {
			String sql = "INSERT INTO LISTAEMAIL(LTENCGGEM, LTENCGEN)VALUES(?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(beanListaEmail.getLtencggem()));
			st.setInt(2, Integer.parseInt(beanListaEmail.getLtencgen()));
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void update(BeanListaEmail beanListaEmail) {
		try {
			String sql = "UPDATE LISTAEMAIL SET LTENCGGEM = ?, LTENCGEN = ?, LTELATIV = ? WHERE LTENCODG = (?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(beanListaEmail.getLtencggem()));
			st.setInt(2, Integer.parseInt(beanListaEmail.getLtencgen()));
			st.setString(3, beanListaEmail.getLtelativ());
			st.setInt(4, Integer.parseInt(beanListaEmail.getLtencodg()));
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void delete(BeanListaEmail beanListaEmail) {
		try {
			String sql = "DELETE FROM LISTAEMAIL WHERE LTENCODG = (?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(beanListaEmail.getLtencodg()));
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
