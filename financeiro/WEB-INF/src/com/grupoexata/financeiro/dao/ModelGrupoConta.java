package com.grupoexata.financeiro.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.financeiro.struts.bean.BeanGrupoConta;

public class ModelGrupoConta {

	public static ModelGrupoConta getInstance(){
		return new ModelGrupoConta();
	}
	
	public ArrayList<BeanGrupoConta> getGruposConta(){
		ArrayList<BeanGrupoConta> grupos = new ArrayList<BeanGrupoConta>();
		try {
			String sql = " SELECT * FROM VW_GRUPOCONTA";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			grupos.addAll(Utils.getObjectsStr(st, BeanGrupoConta.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return grupos;
		
	}
	
	public BeanGrupoConta getGrupoConta(int gpcncodg){
		BeanGrupoConta grupoConta = null;
		try {
			String sql = "SELECT * FROM VW_GRUPOCONTA WHERE GPCNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, gpcncodg);
			
			List<BeanGrupoConta> l = Utils.getObjectsStr(st, BeanGrupoConta.class); 
			
			if (!l.isEmpty())
				grupoConta = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return grupoConta;		
	}
	
	/*
	public void inserir(BeanGrupoConta GrupoConta){
		try {
			String sql = "INSERT INTO GrupoConta (FLQCDESC, FLQLIMED) VALUES (?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, GrupoConta.getFlqcdesc().toUpperCase());
			st.setString(2, GrupoConta.getFlqlimed());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(BeanGrupoConta GrupoConta){
		try {
			String sql = " UPDATE GrupoConta " +
					     " SET FLQCDESC = ?," +
					     " FLQLIMED = ?"+
					     " WHERE FLQNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, GrupoConta.getFlqcdesc().toUpperCase());
			st.setString(2, GrupoConta.getFlqlimed());
			st.setInt(3, Integer.parseInt(GrupoConta.getFlqncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}
