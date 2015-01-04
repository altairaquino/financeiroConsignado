package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanSubgrupoprincipal;
import com.grupoexata.folha.util.Model;

public class ModelSubgrupoprincipal implements Model<BeanSubgrupoprincipal>{
	private static final String QR_SUBGRUPOPRINCIPAL_BY_ID ="SELECT SGPNCODG, SGPCCODG, SGPCTITULO, SGPNCGGGR, SGPCCGGGR FROM SUBGRUPOPRINCIPAL WHERE SGPNCODG = ?";
	private static final String QR_SUBGRUPOPRINCIPAL_LIST ="SELECT SGPNCODG, SGPCCODG, SGPCTITULO, SGPNCGGGR, SGPCCGGGR FROM SUBGRUPOPRINCIPAL";
	private static final String QR_SUBGRUPOPRINCIPAL_SET ="UPDATE SUBGRUPOPRINCIPAL SET SGPCCODG = ? , SGPCTITULO = ? , SGPNCGGGR = ? , SGPCCGGGR = ? WHERE SGPNCODG = ?";
	private static final String QR_SUBGRUPOPRINCIPAL_ADD ="INSERT INTO SUBGRUPOPRINCIPAL(SGPCCODG , SGPCTITULO , SGPNCGGGR , SGPCCGGGR) VALUES (? , ? , ? , ?)";
	private static final String QR_SUBGRUPOPRINCIPAL_REMOVE ="DELETE FROM SUBGRUPOPRINCIPAL WHERE SGPNCODG = ?";
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
	public BeanSubgrupoprincipal get(BeanSubgrupoprincipal bean){
		BeanSubgrupoprincipal obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SUBGRUPOPRINCIPAL_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getSgpncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanSubgrupoprincipal.class);
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
	public List<BeanSubgrupoprincipal> list(){
		List<BeanSubgrupoprincipal> list = new ArrayList<BeanSubgrupoprincipal>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SUBGRUPOPRINCIPAL_LIST);
			list = Utils.getObjectsStr(st, BeanSubgrupoprincipal.class);
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
	public List<BeanSubgrupoprincipal> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanSubgrupoprincipal obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SUBGRUPOPRINCIPAL_SET);
			st.setString(1, obj.getSgpccodg());
			st.setString(2, obj.getSgpctitulo());
			st.setInt(3, Integer.parseInt(obj.getSgpncgggr()));
			st.setString(4, obj.getSgpccgggr());
			st.setInt(5, Integer.parseInt(obj.getSgpncodg()));
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
	public void add(BeanSubgrupoprincipal obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SUBGRUPOPRINCIPAL_ADD);
			st.setString(1, obj.getSgpccodg());
			st.setString(2, obj.getSgpctitulo());
			st.setInt(3, Integer.parseInt(obj.getSgpncgggr()));
			st.setString(4, obj.getSgpccgggr());
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
	public void remove(BeanSubgrupoprincipal obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SUBGRUPOPRINCIPAL_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getSgpncodg()));
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
