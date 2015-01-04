package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanTipoSolicitacao;
import com.grupoexata.bancario.utils.Utils;

public class ModelTipoSolicitacao {

	public static ModelTipoSolicitacao getInstance(){
		return new ModelTipoSolicitacao();
	}
	
	public ArrayList<BeanTipoSolicitacao> getTipoSolicitacoes(){
		ArrayList<BeanTipoSolicitacao> tipos = new ArrayList<BeanTipoSolicitacao>();
		try {
			String sql = " SELECT * FROM VW_TIPOSOLICITACAO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			tipos.addAll(Utils.getObjectsStr(st, BeanTipoSolicitacao.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipos;
		
	}
	
	public BeanTipoSolicitacao getTipoSolicitacao(int tisncodg){
		BeanTipoSolicitacao tipos = null;
		try {
			String sql = "SELECT * FROM VW_TIPOSOLICITACAO WHERE TISNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tisncodg);
			
			List<BeanTipoSolicitacao> l = Utils.getObjectsStr(st, BeanTipoSolicitacao.class); 
			
			if (!l.isEmpty())
				tipos = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tipos;
		
	}
}
