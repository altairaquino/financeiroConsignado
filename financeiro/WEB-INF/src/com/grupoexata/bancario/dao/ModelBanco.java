package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanBanco;
import com.grupoexata.bancario.utils.Utils;

public class ModelBanco {

	public static ModelBanco getInstance(){
		return new ModelBanco();
	}
	
	public ArrayList<BeanBanco> getBancos(){
		ArrayList<BeanBanco> bancos = new ArrayList<BeanBanco>();
		try {
			String sql = " SELECT * FROM VW_BANCO " +
					     " WHERE EXISTS (SELECT BENCODG FROM BANCOEMPRESA" +
					     "               WHERE BENCGBC = BCNCODG AND BENCGEP = 1)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			bancos.addAll(Utils.getObjectsStr(st, BeanBanco.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return bancos;
		
	}
	
	public ArrayList<BeanBanco> getBancosTodos(){
		ArrayList<BeanBanco> bancos = new ArrayList<BeanBanco>();
		try {
			String sql = " SELECT * FROM VW_BANCO ";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			bancos.addAll(Utils.getObjectsStr(st, BeanBanco.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return bancos;
		
	}
	
	public BeanBanco getBanco(int bcncodg){
		BeanBanco banco = null;
		try {
			String sql = "SELECT * FROM VW_BANCO WHERE BCNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, bcncodg);
			
			List<BeanBanco> l = Utils.getObjectsStr(st, BeanBanco.class); 
			
			if (!l.isEmpty())
				banco = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return banco;		
	}
}
