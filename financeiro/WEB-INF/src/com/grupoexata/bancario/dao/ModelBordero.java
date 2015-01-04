package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanBordero;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ModelBordero {

	public static ModelBordero getInstance(){
		return new ModelBordero();
	}
	
	public ArrayList<BeanBordero> getBorderos(){
		ArrayList<BeanBordero> contas = new ArrayList<BeanBordero>();
		try {
			String sql = " SELECT * FROM VW_BORDERO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			contas.addAll(Utils.getObjectsStr(st, BeanBordero.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return contas;
		
	}
	
	public BeanBordero getBordero(int borncodg){
		BeanBordero conta = null;
		
		try {
			String sql = "SELECT * FROM VW_BORDERO WHERE BORNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, borncodg);
			
			List<BeanBordero> l = Utils.getObjectsStr(st, BeanBordero.class); 
			
			if (!l.isEmpty())
				conta = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conta;
		
	}

	public void inserir(BeanBordero bordero){
		try {
			String sql = "INSERT INTO BORDERO(BORCNUMR, BORCNRPT, BORDBANC, BORNCGEN, BORCOBSV)VALUES(?, ?, ?, ?, ?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, bordero.getBorcnumr());
			st.setString(2, bordero.getBorcnrpt());
			
			if (ValidaObjeto.validaData(bordero.getBordbanc())){
				st.setDate(3, Utils.stringToDateSQL(bordero.getBordbanc()));
			}else{
				st.setNull(3, Types.DATE);
			}
			
			st.setInt(4, Integer.parseInt(bordero.getBorncgen()));
			
			st.setString(5, bordero.getBorcobsv());	
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void update(BeanBordero bordero) {
		try {
			String sql = " UPDATE BORDERO SET " +
						 " BORCNUMR = ?,  " +
						 " BORCNRPT = ?, " +
						 " BORDBANC = ?, " +
						 " BORNCGEN = ?," +
						 " BORCOBSV = ?" +
						 " WHERE BORNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, bordero.getBorcnumr());
			st.setString(2, bordero.getBorcnrpt());
			
			if (ValidaObjeto.validaData(bordero.getBordbanc())){
				st.setDate(3, Utils.stringToDateSQL(bordero.getBordbanc()));
			}else{
				st.setNull(3, Types.DATE);
			}
			
			st.setInt(4, Integer.parseInt(bordero.getBorncgen()));
			
			st.setString(5, bordero.getBorcobsv());
			
			st.setInt(6, Integer.parseInt(bordero.getBorncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
