package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanRegional;
import com.grupoexata.bancario.utils.Utils;

public class ModelRegional {

	public static ModelRegional getInstance(){
		return new ModelRegional();
	}
	
	public ArrayList<BeanRegional> getRegionais(){
		ArrayList<BeanRegional> tipos = new ArrayList<BeanRegional>();
		try {
			String sql = " SELECT * FROM VW_Regional";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			tipos.addAll(Utils.getObjectsStr(st, BeanRegional.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tipos;
		
	}
	
	public BeanRegional getRegional(int rgncodg){
		BeanRegional tipo = null;
		try {
			String sql = "SELECT * FROM VW_Regional WHERE RGNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, rgncodg);
			
			List<BeanRegional> l = Utils.getObjectsStr(st, BeanRegional.class); 
			
			if (!l.isEmpty())
				tipo = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tipo;
		
	}

	public void insert(BeanRegional beanRegional) {
		try {
			String sql = "INSERT INTO REGIONAL (RGCDESC) VALUES (?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, beanRegional.getRgcdesc().toUpperCase());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public void update(BeanRegional beanRegional) {
		try {
			String sql = "UPDATE REGIONAL SET RGCDESC = ? WHERE RGNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, beanRegional.getRgcdesc().toUpperCase());
			st.setInt(2, Integer.parseInt(beanRegional.getRgncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}
