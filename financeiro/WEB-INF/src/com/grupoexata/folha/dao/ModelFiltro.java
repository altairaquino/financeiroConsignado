package com.grupoexata.folha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanFiltro;

public class ModelFiltro {
	private static final String QR_FILTRO_BY_ID ="SELECT * FROM FILTRO WHERE FILNCODG = ?";
	private static final String QR_FILTRO_LIST ="SELECT * FROM FILTRO";
	private static final String QR_FILTRO_SET ="UPDATE FILTRO SET FILCDESC = ? WHERE FILNCODG = ?";
	private static final String QR_FILTRO_ADD ="INSERT INTO FILTRO(FILCDESC) VALUES (?)";
	private static final String QR_FILTRO_REMOVE ="DELETE FROM FILTRO WHERE FILNCODG = ?";
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
	public BeanFiltro get(BeanFiltro bean){
		BeanFiltro obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FILTRO_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getFilncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanFiltro.class);
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
	public List<BeanFiltro> list(){
		List<BeanFiltro> list = new ArrayList<BeanFiltro>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FILTRO_LIST);
			list = Utils.getObjectsStr(st, BeanFiltro.class);
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


	public void set(BeanFiltro obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FILTRO_SET);
			st.setString(1, obj.getFilcdesc());
			st.setInt(2, Integer.parseInt(obj.getFilncodg()));
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
	

	public void add(BeanFiltro obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FILTRO_ADD);
			st.setString(1, obj.getFilcdesc());
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

	public void remove(BeanFiltro obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FILTRO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getFilncodg()));
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
