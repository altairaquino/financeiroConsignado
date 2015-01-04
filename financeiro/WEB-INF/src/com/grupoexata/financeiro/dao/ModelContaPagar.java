package com.grupoexata.financeiro.dao;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.financeiro.struts.bean.BeanContaPagar;

public class ModelContaPagar {
	
	public static ModelContaPagar getInstance(){
		return new ModelContaPagar();
	}
	
	public BeanContaPagar getContaPagar(int cpncodg){
		BeanContaPagar plano = null;
		try {
			
			String sql = "SELECT * FROM VW_CONTAPAGAR WHERE CPNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, cpncodg);
			
			List<BeanContaPagar> l = Utils.getObjectsStr(st, BeanContaPagar.class);
			
			if (!l.isEmpty()){
				plano = l.get(0);
			}						
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plano;
	}
	
	public ArrayList<BeanContaPagar> getContasPagar(){
		ArrayList<BeanContaPagar> contas = new ArrayList<BeanContaPagar>();
		try {
			
			String sql = "SELECT * FROM VW_CONTAPAGAR WHERE CPDPGTO IS NULL ORDER BY CPDDATA";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			contas.addAll(Utils.getObjectsStr(st, BeanContaPagar.class));								
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contas;
	}
	
	public void cancela(BeanContaPagar beanContaPagar){
		try {
			String sql = "UPDATE CONTAPAGAR SET CPLATIV = 'F' WHERE CPNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(beanContaPagar.getCpncodg()));
			
			st.executeUpdate();								
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
		
	public ArrayList<BeanContaPagar> getContasDoDia(Date data){
		ArrayList<BeanContaPagar> centros = new ArrayList<BeanContaPagar>();
		try {
			
			String sql = "SELECT * FROM VW_CONTAPAGAR WHERE CPDDATA = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setDate(1, new java.sql.Date(data.getTime()));
						
			centros.addAll(Utils.getObjectsStr(st, BeanContaPagar.class));	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return centros;
	}
	
	public void inserir(BeanContaPagar conta){
		try {
			String sql = " INSERT INTO CONTAPAGAR(CPDDATA,CPNCONT,CPCDESC," +
					     " CPCDOCM, CPYVALR, CPNCGEN, CPNFORN, CPNCOCO, CPNFILI)" +
					     " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setDate(1, Utils.stringToDateSQL(conta.getCpddata()));
			st.setInt(2, Integer.parseInt(conta.getCpncont()));
			st.setString(3, conta.getCpcdesc().toUpperCase());
			st.setString(4, conta.getCpcdocm().toUpperCase());
			st.setFloat(5, Float.valueOf(Utils.converteFloatBR(conta.getCpyvalr())));
			st.setInt(6, Integer.parseInt(conta.getCpncgen()));
			
			if (ValidaObjeto.validaInteiro(conta.getCpnforn())){
				st.setInt(7, Integer.parseInt(conta.getCpnforn()));
			}else{
				st.setNull(7, Types.INTEGER);
			}
			
			if (ValidaObjeto.validaInteiro(conta.getCpncoco())){
				st.setInt(8, Integer.parseInt(conta.getCpncoco()));
			}else{
				st.setNull(8, Types.INTEGER);
			}
			
			if (ValidaObjeto.validaInteiro(conta.getCpnfili())){
				st.setInt(9, Integer.parseInt(conta.getCpnfili()));
			}else{
				st.setNull(9, Types.INTEGER);
			}
			
			st.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(BeanContaPagar conta){
		try {
			String sql = " UPDATE CONTAPAGAR SET CPDDATA = ?,CPNCONT = ?,CPCDESC = ?," +
						 " CPCDOCM = ?, CPYVALR = ?, CPNCGEN = ?, CPNFORN = ?, CPNCOCO = ?, CPNFILI = ?" +
						 " WHERE CPNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setDate(1, Utils.stringToDateSQL(conta.getCpddata()));
			st.setInt(2, Integer.parseInt(conta.getCpncont()));
			st.setString(3, conta.getCpcdesc().toUpperCase());
			st.setString(4, conta.getCpcdocm().toUpperCase());
			st.setFloat(5, Float.valueOf(Utils.converteFloatBR(conta.getCpyvalr())));
			st.setInt(6, Integer.parseInt(conta.getCpncgen()));
			
			if (ValidaObjeto.validaInteiro(conta.getCpnforn())){
				st.setInt(7, Integer.parseInt(conta.getCpnforn()));
			}else{
				st.setNull(7, Types.INTEGER);
			}
			
			if (ValidaObjeto.validaInteiro(conta.getCpncoco())){
				st.setInt(8, Integer.parseInt(conta.getCpncoco()));
			}else{
				st.setNull(8, Types.INTEGER);
			}
			
			if (ValidaObjeto.validaInteiro(conta.getCpnfili())){
				st.setInt(9, Integer.parseInt(conta.getCpnfili()));
			}else{
				st.setNull(9, Types.INTEGER);
			}
			
			st.setInt(10, Integer.parseInt(conta.getCpncodg()));
			
			st.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BeanContaPagar getContaPagar(BeanContaPagar beanContaPagar){
		BeanContaPagar mov = null;
		try {
			
			String sql = " SELECT FIRST 1 * FROM VW_CONTAPAGAR WHERE CPNCONT = ? AND CPDDATA = ? AND CPNCGEN = ?" +
					     " ORDER BY CPNCODG DESC";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(beanContaPagar.getCpncont()));
			st.setDate(2, Utils.stringToDateSQL(beanContaPagar.getCpddata()));
			st.setInt(3, Integer.parseInt(beanContaPagar.getCpncgen()));
			
			List<BeanContaPagar> l = Utils.getObjectsStr(st, BeanContaPagar.class);
			
			if (!l.isEmpty()){
				mov = l.get(0);
			}	
			
			st.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mov;
	}

	public void pagar(BeanContaPagar beanContaPagar) {
		try {
			String sql = " UPDATE CONTAPAGAR " +
					     " SET CPDPGTO = ?," +
					     " CPNFLQU = ?"+
					     " WHERE CPNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setDate(1, Utils.stringToDateSQL(beanContaPagar.getCpdpgto()));
			st.setInt(2, Integer.parseInt(beanContaPagar.getCpnflqu()));
			st.setInt(3, Integer.parseInt(beanContaPagar.getCpncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
