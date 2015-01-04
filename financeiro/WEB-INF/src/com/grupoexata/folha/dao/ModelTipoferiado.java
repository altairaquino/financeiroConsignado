package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanTipoferiado;
import com.grupoexata.folha.util.Model;

public class ModelTipoferiado implements Model<BeanTipoferiado>{
	private static final String QR_TIPOFERIADO_BY_ID ="SELECT TFENCODG, TFECDESC FROM TIPOFERIADO WHERE TFENCODG = ?";
	private static final String QR_TIPOFERIADO_LIST ="SELECT TFENCODG, TFECDESC FROM TIPOFERIADO";
	private static final String QR_TIPOFERIADO_SET ="UPDATE TIPOFERIADO SET TFECDESC = ? WHERE TFENCODG = ?";
	private static final String QR_TIPOFERIADO_ADD ="INSERT INTO TIPOFERIADO(TFECDESC) VALUES (?)";
	private static final String QR_TIPOFERIADO_REMOVE ="DELETE FROM TIPOFERIADO WHERE TFENCODG = ?";
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
	public BeanTipoferiado get(BeanTipoferiado bean){
		BeanTipoferiado obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPOFERIADO_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getTfencodg()));
			obj = Utils.getObjectsStrFirst(st, BeanTipoferiado.class);
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
	public List<BeanTipoferiado> list(){
		List<BeanTipoferiado> list = new ArrayList<BeanTipoferiado>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPOFERIADO_LIST);
			list = Utils.getObjectsStr(st, BeanTipoferiado.class);
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
	public List<BeanTipoferiado> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanTipoferiado obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPOFERIADO_SET);
			st.setString(1, obj.getTfecdesc());
			st.setInt(2, Integer.parseInt(obj.getTfencodg()));
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
	public void add(BeanTipoferiado obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPOFERIADO_ADD);
			st.setString(1, obj.getTfecdesc());
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
	public void remove(BeanTipoferiado obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPOFERIADO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getTfencodg()));
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
