package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanFormaPagamento;
import com.grupoexata.bancario.utils.Utils;

public class ModelFormaPagamento {

	public static ModelFormaPagamento getInstance(){
		return new ModelFormaPagamento();
	}
	
	public ArrayList<BeanFormaPagamento> getFormasDePagamento(){
		ArrayList<BeanFormaPagamento> formas = new ArrayList<BeanFormaPagamento>();
		try {
			String sql = " SELECT FPNCODG, FPCDESC FROM VW_FORMAPAGAMENTO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			formas.addAll(Utils.getObjectsStr(st, BeanFormaPagamento.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return formas;
		
	}
	
	public BeanFormaPagamento getFormaPagamento(int fpncodg){
		BeanFormaPagamento formaPagamento = null;
		try {
			String sql = "SELECT FPNCODG, FPCDESC FROM VW_FORMAPAGAMENTO WHERE FPNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, fpncodg);
			
			List<BeanFormaPagamento> l = Utils.getObjectsStr(st, BeanFormaPagamento.class); 
			
			if (!l.isEmpty())
				formaPagamento = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return formaPagamento;
		
	}
}
