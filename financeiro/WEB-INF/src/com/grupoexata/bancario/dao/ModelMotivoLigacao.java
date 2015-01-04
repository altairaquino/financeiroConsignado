package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanMotivoLigacao;
import com.grupoexata.bancario.utils.Utils;

public class ModelMotivoLigacao {

	public static ModelMotivoLigacao getInstance(){
		return new ModelMotivoLigacao();
	}
	
	public ArrayList<BeanMotivoLigacao> getMotivosLigacao(){
		ArrayList<BeanMotivoLigacao> motivos = new ArrayList<BeanMotivoLigacao>();
		try {
			String sql = " SELECT MLNCODG, MLCDESC FROM VW_MOTIVOLIGACAO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			motivos.addAll(Utils.getObjectsStr(st, BeanMotivoLigacao.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return motivos;
		
	}
	
	public BeanMotivoLigacao getMotivoLigacao(int mlncodg){
		BeanMotivoLigacao motivo = null;
		try {
			String sql = "SELECT * FROM VW_MOTIVOLIGACAO WHERE MLNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, mlncodg);
			
			List<BeanMotivoLigacao> l = Utils.getObjectsStr(st, BeanMotivoLigacao.class); 
			
			if (!l.isEmpty())
				motivo = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return motivo;
		
	}
}
