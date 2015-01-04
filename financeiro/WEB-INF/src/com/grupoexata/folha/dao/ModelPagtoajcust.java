package com.grupoexata.folha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanPagtoajcust;

public class ModelPagtoajcust {
	private static final String QR_PAGTOAJCUST_BY_ID ="SELECT * FROM VW_PAGTOAJCUST WHERE PACNCODG = ?";
	private static final String QR_PAGTOAJCUST_LIST ="SELECT * FROM VW_PAGTOAJCUST";
	private static final String QR_PAGTOAJCUST_SET ="UPDATE PAGTOAJCUST SET PACNCGTAC = ?, PACDCADT = ?, PACDSEM = ?, PACNNFER = ? , PACNREFS = ?, PACNEFET = ?, PACNCGFIL = ? WHERE PACNCODG = ?";
	private static final String QR_PAGTOAJCUST_ADD ="INSERT INTO PAGTOAJCUST( PACNCGTAC, PACDPAGTO, PACDSEM, PACNNFER, PACNREFS, PACNEFET, PACNCGFIL ) VALUES( ?, ?, ?, ?, ?, ?, ?)";
	private static final String QR_PAGTOAJCUST_REMOVE ="DELETE FROM PAGTOAJCUST WHERE PACNCODG = ?";
	public Connection getConnection(){
		return Banco.getConnection();
	}
	public void closeConnection(){
		try{
			if(getConnection() != null && !getConnection().isClosed()){
				getConnection().close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public BeanPagtoajcust get(BeanPagtoajcust bean){
		BeanPagtoajcust obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_PAGTOAJCUST_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getPacncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanPagtoajcust.class);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(st != null && st.isClosed()){
					st.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return obj;
	}
	public List<BeanPagtoajcust> list(){
		List<BeanPagtoajcust> list = new ArrayList<BeanPagtoajcust>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_PAGTOAJCUST_LIST);
			list = Utils.getObjectsStr(st, BeanPagtoajcust.class);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(st != null && st.isClosed()){
					st.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}


	public void set(BeanPagtoajcust obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_PAGTOAJCUST_SET);
			st.setInt(1, Integer.parseInt(obj.getPacncgtac()));
			st.setDate(2, Utils.stringToDateSQL(obj.getPacdpagto()));
			st.setDate(3, Utils.stringToDateSQL(obj.getPacdsem()));
			st.setInt(4, Integer.parseInt(obj.getPacnnfer()));
			st.setInt(5, Integer.parseInt(obj.getPacnrefs()));
			st.setInt(6, Integer.parseInt(obj.getPacnefet()));
			if(obj.getPacncgfil() == null || obj.getPacncgfil().equals("")){
				st.setNull(7, Types.INTEGER);
			}else{
				st.setInt(7, Integer.parseInt(obj.getPacncgfil()));
			}
			st.setInt(8, Integer.parseInt(obj.getPacncodg()));
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(st != null && st.isClosed()){
					st.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	

	public void add(BeanPagtoajcust obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_PAGTOAJCUST_ADD);
			st.setInt(1, Integer.parseInt(obj.getPacncgtac()));
			st.setDate(2, Utils.stringToDateSQL(obj.getPacdpagto()));
			st.setDate(3, Utils.stringToDateSQL(obj.getPacdsem()));
			st.setInt(4, Integer.parseInt(obj.getPacnnfer()));
			st.setInt(5, Integer.parseInt(obj.getPacnrefs()));
			st.setInt(6, Integer.parseInt(obj.getPacnefet()));
			if(obj.getPacncgfil() == null || obj.getPacncgfil().equals("")){
				st.setNull(7, Types.INTEGER);
			}else{
				st.setInt(7, Integer.parseInt(obj.getPacncgfil()));
			}
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(st != null && st.isClosed()){
					st.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public void remove(BeanPagtoajcust obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_PAGTOAJCUST_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getPacncodg()));
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(st != null && st.isClosed()){
					st.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
