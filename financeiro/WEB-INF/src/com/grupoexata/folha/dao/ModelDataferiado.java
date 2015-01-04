package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanDataferiado;
import com.grupoexata.folha.util.Model;

public class ModelDataferiado implements Model<BeanDataferiado>{
	private static final String QR_DATAFERIADO_BY_ID ="SELECT DFENCODG, DFENCGFER, DFEDDATA, DFENANO FROM DATAFERIADO WHERE DFENCODG = ?";
	private static final String QR_DATAFERIADO_LIST ="SELECT DFENCODG, DFENCGFER, DFEDDATA, DFENANO FROM DATAFERIADO";
	private static final String QR_DATAFERIADO_SET ="UPDATE DATAFERIADO SET DFENCGFER = ? , DFEDDATA = ? , DFENANO = ? WHERE DFENCODG = ?";
	private static final String QR_DATAFERIADO_ADD ="INSERT INTO DATAFERIADO(DFENCGFER , DFEDDATA , DFENANO) VALUES (? , ? , ?)";
	private static final String QR_DATAFERIADO_REMOVE ="DELETE FROM DATAFERIADO WHERE DFENCODG = ?";
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
	public BeanDataferiado get(BeanDataferiado bean){
		BeanDataferiado obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_DATAFERIADO_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getDfencodg()));
			obj = Utils.getObjectsStrFirst(st, BeanDataferiado.class);
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
	public List<BeanDataferiado> list(){
		List<BeanDataferiado> list = new ArrayList<BeanDataferiado>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_DATAFERIADO_LIST);
			list = Utils.getObjectsStr(st, BeanDataferiado.class);
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
	public List<BeanDataferiado> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanDataferiado obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_DATAFERIADO_SET);
			st.setInt(1, Integer.parseInt(obj.getDfencgfer()));
			st.setDate(2, Utils.stringToDateSQL(obj.getDfeddata()));
			st.setInt(3, Integer.parseInt(obj.getDfenano()));
			st.setInt(4, Integer.parseInt(obj.getDfencodg()));
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
	public void add(BeanDataferiado obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_DATAFERIADO_ADD);
			st.setInt(1, Integer.parseInt(obj.getDfencgfer()));
			st.setDate(2, Utils.stringToDateSQL(obj.getDfeddata()));
			st.setInt(3, Integer.parseInt(obj.getDfenano()));
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
	public void remove(BeanDataferiado obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_DATAFERIADO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getDfencodg()));
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
