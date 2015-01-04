package com.grupoexata.folha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanAjudajcusto;

public class ModelAjudajcusto {
	private static final String QR_AJUDACUSTO_BY_ID ="SELECT * FROM VW_AJUDACUSTO WHERE AJCNCODG = ?";
	private static final String QR_AJUDAJCUSTO_LIST ="SELECT * FROM VW_AJUDACUSTO";
	private static final String QR_AJUDAJCUSTO_SET ="UPDATE TIPOAJCUST SET AJCNCGEMP = ?, AJCNCGTAC = ?, AJCYVALOR = ?, AJCLFREQ = ?, AJCLATIV = ? WHERE AJCNCODG = ?";
	private static final String QR_AJUDAJCUSTO_ADD ="EXECUTE PROCEDURE SP_ADD_AJUDACUSTO( ?, ?, ?, ?  )";
	private static final String QR_AJUDAJCUSTO_REMOVE ="DELETE FROM TIPOAJCUST WHERE AJCNCODG = ?";
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
	public BeanAjudajcusto get(BeanAjudajcusto bean){
		BeanAjudajcusto obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_AJUDACUSTO_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getAjcncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanAjudajcusto.class);
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
	public List<BeanAjudajcusto> list(){
		List<BeanAjudajcusto> list = new ArrayList<BeanAjudajcusto>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_AJUDAJCUSTO_LIST);
			list = Utils.getObjectsStr(st, BeanAjudajcusto.class);
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


	public void set(BeanAjudajcusto obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_AJUDAJCUSTO_SET);
			//st.setString(1, obj.getAjccdesc());
			st.setInt(2, Integer.parseInt(obj.getAjcncodg()));
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
	

	public void addAjudajcustos(List<BeanAjudajcusto> lista){
		PreparedStatement st = null;
		/*AJCNCODG INTEGER,  AJCNCGEMP INTEGER,
		  AJCNCGTAC INTEGER,  AJCYVALOR FLOAT*/
		try {
			st = getConnection().prepareStatement(QR_AJUDAJCUSTO_ADD);
			for (BeanAjudajcusto ac : lista) {
				if(ac.getAjcncodg() == null){
					st.setNull(1, Types.INTEGER);
				}else{
					st.setInt(1, Integer.parseInt(ac.getAjcncodg()));
				}
				st.setInt(2, Integer.parseInt(ac.getAjcncgemp()));
				st.setInt(3, Integer.parseInt(ac.getAjcncgtac()));
				if(ac.getAjcyvalor() == null){
					st.setNull(4, Types.FLOAT);
				}else{
					st.setFloat(4, Float.parseFloat(Utils.converteFloatBR(ac.getAjcyvalor())));
				}
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

	public void remove(BeanAjudajcusto obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_AJUDAJCUSTO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getAjcncodg()));
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
