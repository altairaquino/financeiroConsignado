package com.grupoexata.folha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanFiltroajcust;

public class ModelFiltroajcust {
	private static final String QR_FILTROAJCUST_BY_ID ="SELECT * FROM  VW_FILTROAJCUST WHERE FACNCODG = ?";
	private static final String QR_FILTROAJCUST_LIST_BY_FACNCGFIL = "SELECT * FROM  VW_FILTROAJCUST WHERE FACNCGFIL = ?";
	private static final String QR_FILTROAJCUST_ADD ="EXECUTE PROCEDURE SP_ADD_FILTROAJCUST( ?, ?, ?)";
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
	public BeanFiltroajcust get(BeanFiltroajcust bean){
		BeanFiltroajcust obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FILTROAJCUST_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getFacncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanFiltroajcust.class);
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
	public List<BeanFiltroajcust> list(String facncgfil){
		List<BeanFiltroajcust> list = new ArrayList<BeanFiltroajcust>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FILTROAJCUST_LIST_BY_FACNCGFIL);
			st.setInt(1, Integer.parseInt(facncgfil));
			list = Utils.getObjectsStr(st, BeanFiltroajcust.class);
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

	public void add(List<BeanFiltroajcust> list){
		PreparedStatement st = null;
		try { 
			st = getConnection().prepareStatement(QR_FILTROAJCUST_ADD);
			for (BeanFiltroajcust obj : list) {
				if(obj.getFacncodg() == null){
					st.setNull(1, Types.INTEGER);
				}else{
					st.setInt(1, Integer.parseInt(obj.getFacncodg()));
				}
				st.setInt(2, Integer.parseInt(obj.getFacncgajc()));
				st.setInt(3, Integer.parseInt(obj.getFacncgfil()));
				st.executeUpdate();
			}
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
