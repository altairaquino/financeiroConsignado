package com.grupoexata.folha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanTipoajcust;

public class ModelTipoajcust {
	private static final String QR_TIPOAJCUST_BY_ID ="SELECT * FROM TIPOAJCUST WHERE TACNCODG = ?";
	private static final String QR_TIPOAJCUST_LIST ="SELECT * FROM TIPOAJCUST";
	private static final String QR_TIPOAJCUST_SET ="UPDATE TIPOAJCUST SET TACCDESC = ? WHERE TACNCODG = ?";
	private static final String QR_TIPOAJCUST_ADD ="INSERT INTO TIPOAJCUST(TACCDESC) VALUES (?)";
	private static final String QR_TIPOAJCUST_REMOVE ="DELETE FROM TIPOAJCUST WHERE TACNCODG = ?";
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
	public BeanTipoajcust get(BeanTipoajcust bean){
		BeanTipoajcust obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPOAJCUST_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getTacncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanTipoajcust.class);
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
	public List<BeanTipoajcust> list(){
		List<BeanTipoajcust> list = new ArrayList<BeanTipoajcust>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPOAJCUST_LIST);
			list = Utils.getObjectsStr(st, BeanTipoajcust.class);
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


	public void set(BeanTipoajcust obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPOAJCUST_SET);
			st.setString(1, obj.getTaccdesc());
			st.setInt(2, Integer.parseInt(obj.getTacncodg()));
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
	

	public void add(BeanTipoajcust obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPOAJCUST_ADD);
			st.setString(1, obj.getTaccdesc());
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

	public void remove(BeanTipoajcust obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPOAJCUST_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getTacncodg()));
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
