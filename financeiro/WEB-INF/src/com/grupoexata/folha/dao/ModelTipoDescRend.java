package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanTipoDescRend;
import com.grupoexata.folha.util.Model;

public class ModelTipoDescRend implements Model<BeanTipoDescRend>{
	private static final String QR_TIPO_DESC_REND_BY_ID ="SELECT TDRNCODG, TDRCDESC FROM TIPO_DESC_REND WHERE TDRNCODG = ?";
	private static final String QR_TIPO_DESC_REND_LIST ="SELECT TDRNCODG, TDRCDESC FROM TIPO_DESC_REND";
	private static final String QR_TIPO_DESC_REND_SET ="UPDATE TIPO_DESC_REND SET TDRCDESC = ? WHERE TDRNCODG = ?";
	private static final String QR_TIPO_DESC_REND_ADD ="INSERT INTO TIPO_DESC_REND(TDRCDESC) VALUES (?)";
	private static final String QR_TIPO_DESC_REND_REMOVE ="DELETE FROM TIPO_DESC_REND WHERE TDRNCODG = ?";
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
	public BeanTipoDescRend get(BeanTipoDescRend bean){
		BeanTipoDescRend obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPO_DESC_REND_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getTdrncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanTipoDescRend.class);
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
	public List<BeanTipoDescRend> list(){
		List<BeanTipoDescRend> list = new ArrayList<BeanTipoDescRend>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPO_DESC_REND_LIST);
			list = Utils.getObjectsStr(st, BeanTipoDescRend.class);
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
	public List<BeanTipoDescRend> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanTipoDescRend obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPO_DESC_REND_SET);
			st.setString(1, obj.getTdrcdesc());
			st.setInt(2, Integer.parseInt(obj.getTdrncodg()));
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
	public void add(BeanTipoDescRend obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPO_DESC_REND_ADD);
			st.setString(1, obj.getTdrcdesc());
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
	public void remove(BeanTipoDescRend obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPO_DESC_REND_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getTdrncodg()));
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
