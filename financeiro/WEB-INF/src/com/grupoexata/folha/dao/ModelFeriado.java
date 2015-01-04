package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanFeriado;
import com.grupoexata.folha.util.Model;

public class ModelFeriado implements Model<BeanFeriado>{
	private static final String QR_FERIADO_BY_ID ="SELECT FERNCODG, FERCDESC, FERNDIA, FERNMES, FERLSEM, FERNDSEM, FERNCGTFE FROM FERIADO WHERE FERNCODG = ?";
	private static final String QR_FERIADO_LIST ="SELECT FERNCODG, FERCDESC, FERNDIA, FERNMES, FERLSEM, FERNDSEM, FERNCGTFE FROM FERIADO";
	private static final String QR_FERIADO_SET ="UPDATE FERIADO SET FERCDESC = ? , FERNDIA = ? , FERNMES = ? , FERLSEM = ? , FERNDSEM = ? , FERNCGTFE = ? WHERE FERNCODG = ?";
	private static final String QR_FERIADO_ADD ="INSERT INTO FERIADO(FERCDESC , FERNDIA , FERNMES , FERLSEM , FERNDSEM , FERNCGTFE) VALUES (? , ? , ? , ? , ? , ?)";
	private static final String QR_FERIADO_REMOVE ="DELETE FROM FERIADO WHERE FERNCODG = ?";
	@Override
	public Connection getConnection(){
		return Banco.getConnection();
	}
	@Override
	public void closeConnection(){
		try{
			if(getConnection() != null && !getConnection().isClosed()){
				getConnection().close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public BeanFeriado get(BeanFeriado bean){
		BeanFeriado obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FERIADO_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getFerncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanFeriado.class);
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
	@Override
	public List<BeanFeriado> list(){
		List<BeanFeriado> list = new ArrayList<BeanFeriado>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FERIADO_LIST);
			list = Utils.getObjectsStr(st, BeanFeriado.class);
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
	@Override
	public List<BeanFeriado> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanFeriado obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FERIADO_SET);
			st.setString(1, obj.getFercdesc());
			st.setInt(2, Integer.parseInt(obj.getFerndia()));
			st.setInt(3, Integer.parseInt(obj.getFernmes()));
			st.setString(4, obj.getFerlsem());
			st.setInt(5, Integer.parseInt(obj.getFerndsem()));
			st.setInt(6, Integer.parseInt(obj.getFerncgtfe()));
			st.setInt(7, Integer.parseInt(obj.getFerncodg()));
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
	
	@Override
	public void add(BeanFeriado obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FERIADO_ADD);
			st.setString(1, obj.getFercdesc());
			st.setInt(2, Integer.parseInt(obj.getFerndia()));
			st.setInt(3, Integer.parseInt(obj.getFernmes()));
			st.setString(4, obj.getFerlsem());
			st.setInt(5, Integer.parseInt(obj.getFerndsem()));
			st.setInt(6, Integer.parseInt(obj.getFerncgtfe()));
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
	@Override
	public void remove(BeanFeriado obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FERIADO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getFerncodg()));
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
