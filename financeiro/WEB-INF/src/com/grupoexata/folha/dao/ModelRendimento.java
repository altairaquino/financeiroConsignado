package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanRendimento;
import com.grupoexata.folha.util.Model;

public class ModelRendimento implements Model<BeanRendimento>{
	private static final String QR_RENDIMENTO_BY_ID ="SELECT RENNCODG, RENYVALR, RENYPERC, RENNCGEMP, RENNCGDER FROM RENDIMENTO WHERE RENNCODG = ?";
	private static final String QR_RENDIMENTO_LIST ="SELECT RENNCODG, RENYVALR, RENYPERC, RENNCGEMP, RENNCGDER FROM RENDIMENTO";
	private static final String QR_RENDIMENTO_SET ="UPDATE RENDIMENTO SET RENYVALR = ? , RENYPERC = ? , RENNCGEMP = ? , RENNCGDER = ? WHERE RENNCODG = ?";
	private static final String QR_RENDIMENTO_ADD ="INSERT INTO RENDIMENTO(RENYVALR , RENYPERC , RENNCGEMP , RENNCGDER) VALUES (? , ? , ? , ?)";
	private static final String QR_RENDIMENTO_REMOVE ="DELETE FROM RENDIMENTO WHERE RENNCODG = ?";
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
	public BeanRendimento get(BeanRendimento bean){
		BeanRendimento obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_RENDIMENTO_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getRenncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanRendimento.class);
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
	public List<BeanRendimento> list(){
		List<BeanRendimento> list = new ArrayList<BeanRendimento>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_RENDIMENTO_LIST);
			list = Utils.getObjectsStr(st, BeanRendimento.class);
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
	public List<BeanRendimento> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanRendimento obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_RENDIMENTO_SET);
			st.setObject(1, obj.getRenyvalr());
			st.setObject(2, obj.getRenyperc());
			st.setInt(3, Integer.parseInt(obj.getRenncgemp()));
			st.setInt(4, Integer.parseInt(obj.getRenncgder()));
			st.setInt(5, Integer.parseInt(obj.getRenncodg()));
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
	public void add(BeanRendimento obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_RENDIMENTO_ADD);
			st.setObject(1, obj.getRenyvalr());
			st.setObject(2, obj.getRenyperc());
			st.setInt(3, Integer.parseInt(obj.getRenncgemp()));
			st.setInt(4, Integer.parseInt(obj.getRenncgder()));
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
	public void remove(BeanRendimento obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_RENDIMENTO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getRenncodg()));
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
