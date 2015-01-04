package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanFuncRegional;
import com.grupoexata.bancario.utils.Utils;

public class ModelFuncRegional {

	public static ModelFuncRegional getInstance(){
		return new ModelFuncRegional();
	}
	
	public ArrayList<BeanFuncRegional> getFuncionariosDaRegional(int rgncodg){
		ArrayList<BeanFuncRegional> funcs = new ArrayList<BeanFuncRegional>();
		try {
			String sql = " SELECT * FROM VW_FUNCREGIONAL WHERE FRENCGRG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, rgncodg);
			
			funcs.addAll(Utils.getObjectsStr(st, BeanFuncRegional.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return funcs;
		
	}
	
	public BeanFuncRegional getFuncRegional(int frencodg){
		BeanFuncRegional func = null;
		
		try {
			String sql = "SELECT * FROM VW_FUNCREGIONAL WHERE FRENCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, frencodg);
			
			List<BeanFuncRegional> l = Utils.getObjectsStr(st, BeanFuncRegional.class); 
			
			if (!l.isEmpty())
				func = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return func;
		
	}

	
	public void inserir(BeanFuncRegional beanFuncRegional) {
		try {
			String sql = " INSERT INTO FUNCREGIONAL(FRENCGTFA, FRENCGRG, FRENCGEN)" +
					     " VALUES( ?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(beanFuncRegional.getFrencgtfa()));
			st.setInt(2, Integer.parseInt(beanFuncRegional.getFrencgrg()));
			st.setInt(3, Integer.parseInt(beanFuncRegional.getFrencgen()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void update(BeanFuncRegional beanFuncRegional) {
		try {
			String sql = " UPDATE FUNCREGIONAL SET FRENCGTFA = ?, FRENCGRG = ?, FRENCGEN = ?, FRELATIV = ?"+
		                 " WHERE FRENCODG = ?";

			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(beanFuncRegional.getFrencgtfa()));
			st.setInt(2, Integer.parseInt(beanFuncRegional.getFrencgrg()));
			st.setInt(3, Integer.parseInt(beanFuncRegional.getFrencgen()));
			st.setString(4, beanFuncRegional.getFrelativ());
			st.setInt(5, Integer.parseInt(beanFuncRegional.getFrencodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}
