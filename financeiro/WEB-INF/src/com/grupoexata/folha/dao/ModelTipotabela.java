package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanTipotabela;
import com.grupoexata.folha.util.Model;

public class ModelTipotabela implements Model<BeanTipotabela>{
	private static final String QR_TIPOTABELA_BY_ID ="SELECT TTBNCODG, TTBCDESC, TTBCSIGL FROM TIPOTABELA WHERE TTBNCODG = ?";
	private static final String QR_TIPOTABELA_LIST ="SELECT TTBNCODG, TTBCDESC, TTBCSIGL FROM TIPOTABELA";
	private static final String QR_TIPOTABELA_SET ="UPDATE TIPOTABELA SET TTBCDESC = ? , TTBCSIGL = ? WHERE TTBNCODG = ?";
	private static final String QR_TIPOTABELA_ADD ="INSERT INTO TIPOTABELA(TTBCDESC , TTBCSIGL) VALUES (? , ?)";
	private static final String QR_TIPOTABELA_REMOVE ="DELETE FROM TIPOTABELA WHERE TTBNCODG = ?";
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
	public BeanTipotabela get(BeanTipotabela bean){
		BeanTipotabela obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPOTABELA_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getTtbncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanTipotabela.class);
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
	public List<BeanTipotabela> list(){
		List<BeanTipotabela> list = new ArrayList<BeanTipotabela>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPOTABELA_LIST);
			list = Utils.getObjectsStr(st, BeanTipotabela.class);
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
	public List<BeanTipotabela> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanTipotabela obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPOTABELA_SET);
			st.setString(1, obj.getTtbcdesc());
			st.setString(2, obj.getTtbcsigl());
			st.setInt(3, Integer.parseInt(obj.getTtbncodg()));
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
	public void add(BeanTipotabela obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPOTABELA_ADD);
			st.setString(1, obj.getTtbcdesc());
			st.setString(2, obj.getTtbcsigl());
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
	public void remove(BeanTipotabela obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPOTABELA_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getTtbncodg()));
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
