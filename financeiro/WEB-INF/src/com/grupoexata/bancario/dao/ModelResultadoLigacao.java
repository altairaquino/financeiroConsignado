package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanResultadoLigacao;
import com.grupoexata.bancario.utils.Utils;

public class ModelResultadoLigacao {

	public static ModelResultadoLigacao getInstance(){
		return new ModelResultadoLigacao();
	}
	
	public ArrayList<BeanResultadoLigacao> getResultadosLigacao(){
		ArrayList<BeanResultadoLigacao> resultados = new ArrayList<BeanResultadoLigacao>();
		try {
			String sql = " SELECT * FROM VW_RESULTADOLIGACAO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			resultados.addAll(Utils.getObjectsStr(st, BeanResultadoLigacao.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return resultados;
		
	}
	
	public BeanResultadoLigacao getResultadoLigacao(int rlncodg){
		BeanResultadoLigacao resultado = null;
		try {
			String sql = "SELECT * FROM VW_RESULTADOLIGACAO WHERE RLNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, rlncodg);
			
			List<BeanResultadoLigacao> l = Utils.getObjectsStr(st, BeanResultadoLigacao.class); 
			
			if (!l.isEmpty())
				resultado = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return resultado;
		
	}
}
