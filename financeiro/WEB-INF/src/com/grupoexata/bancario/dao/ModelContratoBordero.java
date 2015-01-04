package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanBordero;
import com.grupoexata.bancario.struts.bean.BeanContratoBordero;
import com.grupoexata.bancario.utils.Utils;

public class ModelContratoBordero {

	public static ModelContratoBordero getInstance(){
		return new ModelContratoBordero();
	}
	
	public ArrayList<BeanContratoBordero> getContratosDoBordero(int borncodg){
		ArrayList<BeanContratoBordero> contratoBordero = new ArrayList<BeanContratoBordero>();
		try {
			String sql = " SELECT * FROM VW_CONTRATOBORDERO WHERE CTBNCGBOR = (?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, borncodg);
			
			contratoBordero.addAll(Utils.getObjectsStr(st, BeanContratoBordero.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return contratoBordero;
		
	}
	
	public BeanContratoBordero getContratoBordero(int ctbncodg){
		BeanContratoBordero contratoBordero = null;
		try {
			String sql = "SELECT * FROM VW_CONTRATOBORDERO WHERE CTBNCODG = (?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ctbncodg);
			
			List<BeanContratoBordero> l = Utils.getObjectsStr(st, BeanContratoBordero.class);
			
			if (!l.isEmpty())
				contratoBordero = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return contratoBordero;
		
	}

	public void inserir(BeanContratoBordero contratoBordero){
		try {
			String sql = "INSERT INTO CONTRATOBORDERO(CTBNCGCT, CTBNCGBOR)VALUES(?, ?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(contratoBordero.getCtbncgct()));
			st.setInt(2, Integer.parseInt(contratoBordero.getCtbncgbor()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void delete(BeanContratoBordero contratoBordero) {
		try {
			String sql = " DELETE FROM CONTRATOBORDERO "+
						 " WHERE CTBNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(contratoBordero.getCtbncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public boolean existeRegistro(BeanContratoBordero contratoBordero) {
		boolean existe = false;
		try {
			String sql = " SELECT * FROM CONTRATOBORDERO "+
						 " WHERE CTBNCGBOR = ? AND CTBNCGCT = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(contratoBordero.getCtbncgbor()));
			st.setInt(2, Integer.parseInt(contratoBordero.getCtbncgct()));
			
			existe = st.executeQuery().next();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return existe;
	}
	
	public void confirma(BeanContratoBordero contratoBordero) {
		try {
			String sql = " UPDATE CONTRATOBORDERO SET CTBLCONF = 'T'"+
						 " WHERE CTBNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(contratoBordero.getCtbncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public List<BeanBordero> getPesquisa(String str) {
		List<BeanBordero> list = new ArrayList<BeanBordero>();
		try {
			String sql = " SELECT * FROM VW_BORDERO" +
					     " WHERE BORCNUMR = ? " +
					     " OR EXISTS(SELECT * FROM VW_CONTRATOBORDERO" +
					     "           WHERE CTBNCGBOR = BORNCODG AND CTBNNRCT = ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, str);
			st.setString(2, str);
			
			list.addAll(Utils.getObjectsStr(st, BeanBordero.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
