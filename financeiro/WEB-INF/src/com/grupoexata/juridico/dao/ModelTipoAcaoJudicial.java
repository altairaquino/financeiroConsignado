package com.grupoexata.juridico.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.juridico.struts.bean.BeanTipoAcaoJudicial;

public class ModelTipoAcaoJudicial {

	public static ModelTipoAcaoJudicial getInstance(){
		return new ModelTipoAcaoJudicial();
	}
	
	public ArrayList<BeanTipoAcaoJudicial> getTiposAcaoJudicial(){
		ArrayList<BeanTipoAcaoJudicial> tiposAcaoJudicial = new ArrayList<BeanTipoAcaoJudicial>();
		try {
			String sql = " SELECT * FROM VW_TIPOACAOJUDICIAL";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			tiposAcaoJudicial.addAll(Utils.getObjectsStr(st, BeanTipoAcaoJudicial.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tiposAcaoJudicial;
		
	}
	
	public BeanTipoAcaoJudicial getTipoAcaoJudicial(int grncodg){
		BeanTipoAcaoJudicial TipoAcaoJudicial = null;
		
		try {
			String sql = "SELECT * FROM VW_TIPOACAOJUDICIAL WHERE TACNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, grncodg);
			
			List<BeanTipoAcaoJudicial> l = Utils.getObjectsStr(st, BeanTipoAcaoJudicial.class); 
			
			if (!l.isEmpty())
				TipoAcaoJudicial = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return TipoAcaoJudicial;
		
	}
	
}
