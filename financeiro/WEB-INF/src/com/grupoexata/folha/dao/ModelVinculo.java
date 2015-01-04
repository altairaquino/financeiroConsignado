package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanVinculo;
import com.grupoexata.folha.util.Model;

public class ModelVinculo implements Model<BeanVinculo>{
	private static final String QR_VINCULO_BY_ID ="SELECT VINNCODG, VINCCODG, VINCDESC FROM VINCULO WHERE VINNCODG = ?";
	private static final String QR_VINCULO_LIST ="SELECT VINNCODG, VINCCODG, VINCDESC FROM VINCULO";
	private static final String QR_VINCULO_SET ="UPDATE VINCULO SET VINCCODG = ? , VINCDESC = ? WHERE VINNCODG = ?";
	private static final String QR_VINCULO_ADD ="INSERT INTO VINCULO(VINCCODG , VINCDESC) VALUES (? , ?)";
	private static final String QR_VINCULO_REMOVE ="DELETE FROM VINCULO WHERE VINNCODG = ?";
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
	public BeanVinculo get(BeanVinculo bean){
		BeanVinculo obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_VINCULO_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getVinncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanVinculo.class);
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
	public List<BeanVinculo> list(){
		List<BeanVinculo> list = new ArrayList<BeanVinculo>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_VINCULO_LIST);
			list = Utils.getObjectsStr(st, BeanVinculo.class);
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
	public List<BeanVinculo> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanVinculo obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_VINCULO_SET);
			st.setString(1, obj.getVinccodg());
			st.setString(2, obj.getVincdesc());
			st.setInt(3, Integer.parseInt(obj.getVinncodg()));
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
	public void add(BeanVinculo obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_VINCULO_ADD);
			st.setString(1, obj.getVinccodg());
			st.setString(2, obj.getVincdesc());
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
	public void remove(BeanVinculo obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_VINCULO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getVinncodg()));
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
