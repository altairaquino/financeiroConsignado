package com.grupoexata.juridico.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.juridico.struts.bean.BeanFaseProcesso;

public class ModelFaseProcesso {

	public static ModelFaseProcesso getInstance(){
		return new ModelFaseProcesso();
	}
	
	public ArrayList<BeanFaseProcesso> getFasesDoProcesso(){
		ArrayList<BeanFaseProcesso> fases = new ArrayList<BeanFaseProcesso>();
		try {
			String sql = " SELECT * FROM VW_FASEPROCESSO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			fases.addAll(Utils.getObjectsStr(st, BeanFaseProcesso.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return fases;
		
	}
	
	public BeanFaseProcesso getFaseProcesso(int fapncodg){
		BeanFaseProcesso faseProcesso = null;
		
		try {
			String sql = "SELECT * FROM VW_FASEPROCESSO WHERE FAPNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, fapncodg);
			
			List<BeanFaseProcesso> l = Utils.getObjectsStr(st, BeanFaseProcesso.class); 
			
			if (!l.isEmpty())
				faseProcesso = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return faseProcesso;
		
	}
	
}
