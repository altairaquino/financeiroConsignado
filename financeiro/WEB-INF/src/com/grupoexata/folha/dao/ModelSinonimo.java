package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanSinonimo;
import com.grupoexata.folha.util.Model;

public class ModelSinonimo implements Model<BeanSinonimo>{
	private static final String QR_SINONIMO_BY_ID ="SELECT SINNCODG, SINCCGOCP, SINCTITULO, SINNCGOCP FROM SINONIMO WHERE SINNCODG = ?";
	private static final String QR_SINONIMO_LIST ="SELECT SINNCODG, SINCCGOCP, SINCTITULO, SINNCGOCP FROM SINONIMO";
	private static final String QR_SINONIMO_SET ="UPDATE SINONIMO SET SINCCGOCP = ? , SINCTITULO = ? , SINNCGOCP = ? WHERE SINNCODG = ?";
	private static final String QR_SINONIMO_ADD ="INSERT INTO SINONIMO(SINCCGOCP , SINCTITULO , SINNCGOCP) VALUES (? , ? , ?)";
	private static final String QR_SINONIMO_REMOVE ="DELETE FROM SINONIMO WHERE SINNCODG = ?";
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
	public BeanSinonimo get(BeanSinonimo bean){
		BeanSinonimo obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SINONIMO_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getSinncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanSinonimo.class);
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
	public List<BeanSinonimo> list(){
		List<BeanSinonimo> list = new ArrayList<BeanSinonimo>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SINONIMO_LIST);
			list = Utils.getObjectsStr(st, BeanSinonimo.class);
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
	public List<BeanSinonimo> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanSinonimo obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SINONIMO_SET);
			st.setString(1, obj.getSinccgocp());
			st.setString(2, obj.getSinctitulo());
			st.setInt(3, Integer.parseInt(obj.getSinncgocp()));
			st.setInt(4, Integer.parseInt(obj.getSinncodg()));
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
	public void add(BeanSinonimo obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SINONIMO_ADD);
			st.setString(1, obj.getSinccgocp());
			st.setString(2, obj.getSinctitulo());
			st.setInt(3, Integer.parseInt(obj.getSinncgocp()));
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
	public void remove(BeanSinonimo obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SINONIMO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getSinncodg()));
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
