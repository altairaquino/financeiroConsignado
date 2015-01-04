package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanComissaoContrato;
import com.grupoexata.bancario.utils.Utils;

public class ModelComissaoContrato {

	public static ModelComissaoContrato getInstance(){
		return new ModelComissaoContrato();
	}
	
	public ArrayList<BeanComissaoContrato> getComissoesDoContrato(int ctncodg){
		ArrayList<BeanComissaoContrato> comissoes = new ArrayList<BeanComissaoContrato>();
		try {
			String sql = " SELECT * FROM VW_COMISSAOCONTRATO WHERE CCNCGCT = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ctncodg);
			
			comissoes.addAll(Utils.getObjectsStr(st, BeanComissaoContrato.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return comissoes;
		
	}
	
	public BeanComissaoContrato getComissaoContrato(int ccncodg){
		BeanComissaoContrato comissao = null;
		
		try {
			String sql = "SELECT * FROM VW_COMISSAOCONTRATO WHERE CCNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ccncodg);
			
			List<BeanComissaoContrato> l = Utils.getObjectsStr(st, BeanComissaoContrato.class); 
			
			if (!l.isEmpty())
				comissao = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return comissao;
		
	}

	/*
	public void inserir(BeanComissaoContrato comissao) {
	
		try {
			String sql = "INSERT INTO COMISSAOCONTRATO (PDNCGBC, PDCDESC, PDCABRV) VALUES (?,?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(comissao.getPdncgbc()));
			st.setString(2, comissao.getPdcdesc().toUpperCase());
			st.setString(3, comissao.getPdcabrv().toUpperCase());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	*/
	
	public void update(BeanComissaoContrato comissao) {
		try {
			String sql = " UPDATE COMISSAOCONTRATO SET CCNPERC = ?, CCNC2EN = ?" +
					     " WHERE CCNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setFloat(1, Float.parseFloat(Utils.converteFloatBR(comissao.getCcnperc())));
			st.setInt(2, Integer.parseInt(comissao.getCcnc2en()));
			st.setInt(3, Integer.parseInt(comissao.getCcncodg()));
			
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
