package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanFilial;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ModelFilial {

	public static ModelFilial getInstance(){
		return new ModelFilial();
	}
	
	public ArrayList<BeanFilial> getFiliais(){
		ArrayList<BeanFilial> bancos = new ArrayList<BeanFilial>();
		try {
			String sql = " SELECT * FROM VW_FILIAL ORDER BY FLCNOME";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			bancos.addAll(Utils.getObjectsStr(st, BeanFilial.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return bancos;
		
	}

	public void inserir(BeanFilial beanFilial){
		
		try {
			String sql = " INSERT INTO FILIAL(FLNCGEP, FLCNOME, FLCRESP, FLCFONE, FLCMAIL, FLNCGTL, FLCLOGR, FLCBAIR, FLNCGCD, FLCCEP)" +
					     " VALUES(1, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, beanFilial.getFlcnome().toUpperCase());
			st.setString(2, beanFilial.getFlcresp().toUpperCase());
			st.setString(3, ValidaObjeto.removeCharOfInteger(beanFilial.getFlcfone()));
			st.setString(4, beanFilial.getFlcmail());
			st.setInt(5, Integer.parseInt(beanFilial.getFlncgtl()));
			st.setString(6, beanFilial.getFlclogr().toUpperCase());
			st.setString(7, beanFilial.getFlcbair().toUpperCase());
			st.setInt(8, Integer.parseInt(beanFilial.getFlncgcd()));
			st.setString(9, ValidaObjeto.removeCharOfInteger(beanFilial.getFlccep()));
			
			st.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	public void update(BeanFilial beanFilial){
		
		try {
			String sql = " UPDATE FILIAL SET FLCNOME = ?, FLCRESP = ?, FLCFONE = ?, FLCMAIL = ?, FLNCGTL = ?, FLCLOGR = ?, FLCBAIR = ?, FLNCGCD = ?, FLCCEP = ?" +
					     " WHERE FLNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, beanFilial.getFlcnome().toUpperCase());
			st.setString(2, beanFilial.getFlcresp().toUpperCase());
			st.setString(3, ValidaObjeto.removeCharOfInteger(beanFilial.getFlcfone()));
			st.setString(4, beanFilial.getFlcmail());
			st.setInt(5, Integer.parseInt(beanFilial.getFlncgtl()));
			st.setString(6, beanFilial.getFlclogr().toUpperCase());
			st.setString(7, beanFilial.getFlcbair().toUpperCase());
			st.setInt(8, Integer.parseInt(beanFilial.getFlncgcd()));
			st.setString(9, ValidaObjeto.removeCharOfInteger(beanFilial.getFlccep()));
			st.setInt(10, Integer.parseInt(beanFilial.getFlncodg()));
			
			st.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	
	
	public BeanFilial getFilial(int flncodg){
		BeanFilial banco = null;
		try {
			String sql = "SELECT * FROM VW_FILIAL WHERE FLNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, flncodg);
			
			List<BeanFilial> l = Utils.getObjectsStr(st, BeanFilial.class); 
			
			if (!l.isEmpty())
				banco = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return banco;		
	}
	
	public ArrayList<BeanFilial> getPesquisaFilial(String str){
		ArrayList<BeanFilial> filiais = new ArrayList<BeanFilial>();
		try {
			String sql = "SELECT * FROM VW_FILIAL WHERE FLCNOME LIKE '%"+str.toUpperCase()+"%' OR FLCDCCD LIKE '%"+str.toUpperCase()+"%'";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			filiais.addAll(Utils.getObjectsStr(st, BeanFilial.class));			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return filiais;		
	}
}
