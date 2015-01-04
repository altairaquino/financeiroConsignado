package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanGrandegrupo;
import com.grupoexata.folha.util.Model;

public class ModelGrandegrupo implements Model<BeanGrandegrupo>{
	private static final String QR_GRANDEGRUPO_BY_ID ="SELECT GGRNCODG, GGRCCODG, GGRCTITULO FROM GRANDEGRUPO WHERE GGRNCODG = ?";
	private static final String QR_GRANDEGRUPO_LIST ="SELECT GGRNCODG, GGRCCODG, GGRCTITULO FROM GRANDEGRUPO";
	private static final String QR_GRANDEGRUPO_SET ="UPDATE GRANDEGRUPO SET GGRCCODG = ? , GGRCTITULO = ? WHERE GGRNCODG = ?";
	private static final String QR_GRANDEGRUPO_ADD ="INSERT INTO GRANDEGRUPO(GGRCCODG , GGRCTITULO) VALUES (? , ?)";
	private static final String QR_GRANDEGRUPO_REMOVE ="DELETE FROM GRANDEGRUPO WHERE GGRNCODG = ?";
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
	public BeanGrandegrupo get(BeanGrandegrupo bean){
		BeanGrandegrupo obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_GRANDEGRUPO_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getGgrncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanGrandegrupo.class);
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
	public List<BeanGrandegrupo> list(){
		List<BeanGrandegrupo> list = new ArrayList<BeanGrandegrupo>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_GRANDEGRUPO_LIST);
			list = Utils.getObjectsStr(st, BeanGrandegrupo.class);
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
	public List<BeanGrandegrupo> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanGrandegrupo obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_GRANDEGRUPO_SET);
			st.setString(1, obj.getGgrccodg());
			st.setString(2, obj.getGgrctitulo());
			st.setInt(3, Integer.parseInt(obj.getGgrncodg()));
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
	public void add(BeanGrandegrupo obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_GRANDEGRUPO_ADD);
			st.setString(1, obj.getGgrccodg());
			st.setString(2, obj.getGgrctitulo());
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
	public void remove(BeanGrandegrupo obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_GRANDEGRUPO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getGgrncodg()));
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
