package com.grupoexata.folha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanMotivo;

public class ModelMotivo {
	
	private static final String QR_MOTIVO_BY_ID ="SELECT * FROM VW_MOTIVO WHERE MOTNCODG = ?";
	private static final String QR_MOTIVO_LIST ="SELECT * FROM VW_MOTIVO";
	private static final String QR_MOTIVO_SET ="UPDATE MOTIVO SET MOTCDESC = ?, MOTLCANC = ? WHERE MOTNCODG = ?";
	private static final String QR_MOTIVO_ADD ="INSERT INTO MOTIVO(MOTCDESC, MOTLCANC) VALUES (?, ?)";
	private static final String QR_MOTIVO_REMOVE ="DELETE FROM MOTIVO WHERE MOTNCODG = ?";
	
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
	public BeanMotivo get(BeanMotivo bean){
		BeanMotivo obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_MOTIVO_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getMotncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanMotivo.class);
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
	public List<BeanMotivo> list(){
		List<BeanMotivo> list = new ArrayList<BeanMotivo>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_MOTIVO_LIST);
			list = Utils.getObjectsStr(st, BeanMotivo.class);
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


	public void set(BeanMotivo obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_MOTIVO_SET);
			st.setString(1, obj.getMotcdesc());
			st.setString(2, obj.getMotlcanc());
			st.setInt(3, Integer.parseInt(obj.getMotncodg()));
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
	
	public void add(BeanMotivo obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_MOTIVO_ADD);
			st.setString(1, obj.getMotcdesc());
			st.setString(2, obj.getMotlcanc());
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
	

	public void remove(BeanMotivo obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_MOTIVO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getMotncodg()));
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
