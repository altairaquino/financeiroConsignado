package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanContaCorrente;
import com.grupoexata.bancario.utils.Utils;

public class ModelContaCorrente {

	public static ModelContaCorrente getInstance(){
		return new ModelContaCorrente();
	}
	
	public ArrayList<BeanContaCorrente> getContaCorrentesDaEntidade(int enncodg){
		ArrayList<BeanContaCorrente> contas = new ArrayList<BeanContaCorrente>();
		try {
			String sql = " SELECT * FROM VW_CONTACORRENTE WHERE CONCGEN = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			
			contas.addAll(Utils.getObjectsStr(st, BeanContaCorrente.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return contas;
		
	}
	
	public BeanContaCorrente getContaCorrente(int concodg){
		BeanContaCorrente conta = null;
		
		try {
			String sql = "SELECT * FROM VW_CONTACORRENTE WHERE CONCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, concodg);
			
			List<BeanContaCorrente> l = Utils.getObjectsStr(st, BeanContaCorrente.class); 
			
			if (!l.isEmpty())
				conta = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conta;
		
	}

	public void inserir(BeanContaCorrente conta) {
		try {
			String sql = "INSERT INTO CONTACORRENTE (CONCGEN, CONTCON, CONCGBC, COCAGEN,COCNUMR,COCTITU) VALUES (?,?,?,?,?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(conta.getConcgen()));
			st.setInt(2, Integer.parseInt(conta.getContcon()));
			st.setInt(3, Integer.parseInt(conta.getConcgbc()));
			st.setString(4, conta.getCocagen());
			st.setString(5, conta.getCocnumr());
			st.setString(6, conta.getCoctitu().toUpperCase());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void update(BeanContaCorrente conta) {
		try {
			String sql = " UPDATE CONTACORRENTE SET CONCGBC = ?, COCAGEN = ?, COCNUMR = ?, COCTITU = ?, CONTCON = ?, COLPRIN = ? " +
					     " WHERE CONCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(conta.getConcgbc()));
			st.setString(2, conta.getCocagen().toUpperCase());
			st.setString(3, conta.getCocnumr().toUpperCase());
			st.setString(4, conta.getCoctitu().toUpperCase());
			st.setInt(5, Integer.parseInt(conta.getContcon()));
			st.setString(6, conta.getColprin());
			st.setInt(7, Integer.parseInt(conta.getConcodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void delete(BeanContaCorrente conta) {
		try {
			String sql = " UPDATE CONTACORRENTE SET" +
					     " COLATIV = 'F'" +
						 " WHERE CONCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(conta.getConcodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
