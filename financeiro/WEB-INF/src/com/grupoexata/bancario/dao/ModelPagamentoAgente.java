package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanPagamentoAgente;
import com.grupoexata.bancario.utils.Utils;

public class ModelPagamentoAgente {

	public static ModelPagamentoAgente getInstance(){
		return new ModelPagamentoAgente();
	}
	
	public ArrayList<BeanPagamentoAgente> getPagamentosAgenteData(Date data){
		ArrayList<BeanPagamentoAgente> pgtos = new ArrayList<BeanPagamentoAgente>();
		try {
			String sql = " SELECT * FROM VW_PAGAMENTOAGENTE WHERE PGADBASE = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setDate(1, new java.sql.Date(data.getTime()));
			
			pgtos.addAll(Utils.getObjectsStr(st, BeanPagamentoAgente.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pgtos;
	}
	
	public ArrayList<BeanPagamentoAgente> getPagamentosAgente(){
		ArrayList<BeanPagamentoAgente> pgtos = new ArrayList<BeanPagamentoAgente>();
		try {
			String sql = " SELECT * FROM VW_PAGAMENTOAGENTE " +
						 " WHERE PGADPGTO IS NULL"+
					     " ORDER BY PGADBASE, PGACNMAN";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			pgtos.addAll(Utils.getObjectsStr(st, BeanPagamentoAgente.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pgtos;
		
	}

	public BeanPagamentoAgente getPagamentoAgente(int pgancodg){
		BeanPagamentoAgente pgto = null;
		try {
			String sql = "SELECT * FROM VW_PAGAMENTOAGENTE WHERE PGANCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, pgancodg);
			
			List<BeanPagamentoAgente> l = Utils.getObjectsStr(st, BeanPagamentoAgente.class); 
			
			if (!l.isEmpty())
				pgto = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return pgto;		
	}	
	
	public void confirmaPagamento(BeanPagamentoAgente beanPagamentoAgente){
		try {
			String sql = " UPDATE PAGAMENTOAGENTE SET" +
					     " PGADPGTO = ?," +
					     " PGANC2EN = ?," +
					     " PGANCOCO = ?," +
					     " PGAYDESC = ?" +
					     " WHERE PGANCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setDate(1,  Utils.stringToDateSQL(beanPagamentoAgente.getPgadpgto()));
			st.setInt(2, Integer.parseInt(beanPagamentoAgente.getPgancgus()));
			st.setInt(3, Integer.parseInt(beanPagamentoAgente.getPgancoco()));
			st.setFloat(4, Float.parseFloat(Utils.converteFloatBR(beanPagamentoAgente.getPgaydesc())));
			st.setInt(5, Integer.parseInt(beanPagamentoAgente.getPgancodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(BeanPagamentoAgente beanPagamentoAgente) {
		try {
			String sql = " UPDATE PAGAMENTOAGENTE SET" +
					     " PGADBASE = ?," +
					     " PGAYVALR = ?" +
					     " WHERE PGANCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setDate(1,  Utils.stringToDateSQL(beanPagamentoAgente.getPgadbase()));
			st.setFloat(2, Float.parseFloat(Utils.converteFloatBR(beanPagamentoAgente.getPgayvalr())));
			st.setInt(3, Integer.parseInt(beanPagamentoAgente.getPgancodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void importa(Date data) {
		try {
			String sql = "EXECUTE PROCEDURE SP_COMISSAO_PARA_FINANCEIRO(?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setDate(1,  new java.sql.Date(data.getTime()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
