package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanFamilia;
import com.grupoexata.folha.util.Model;

public class ModelFamilia implements Model<BeanFamilia>{
	private static final String QR_FAMILIA_BY_ID ="SELECT FAMNCODG, FAMCCODG, FAMCTITULO, FAMNCGSGR, FAMCCGSGR FROM FAMILIA WHERE FAMNCODG = ?";
	private static final String QR_FAMILIA_LIST ="SELECT FAMNCODG, FAMCCODG, FAMCTITULO, FAMNCGSGR, FAMCCGSGR FROM FAMILIA";
	private static final String QR_FAMILIA_SET ="UPDATE FAMILIA SET FAMCCODG = ? , FAMCTITULO = ? , FAMNCGSGR = ? , FAMCCGSGR = ? WHERE FAMNCODG = ?";
	private static final String QR_FAMILIA_ADD ="INSERT INTO FAMILIA(FAMCCODG , FAMCTITULO , FAMNCGSGR , FAMCCGSGR) VALUES (? , ? , ? , ?)";
	private static final String QR_FAMILIA_REMOVE ="DELETE FROM FAMILIA WHERE FAMNCODG = ?";
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
	public BeanFamilia get(BeanFamilia bean){
		BeanFamilia obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FAMILIA_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getFamncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanFamilia.class);
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
	public List<BeanFamilia> list(){
		List<BeanFamilia> list = new ArrayList<BeanFamilia>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FAMILIA_LIST);
			list = Utils.getObjectsStr(st, BeanFamilia.class);
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
	public List<BeanFamilia> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanFamilia obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FAMILIA_SET);
			st.setString(1, obj.getFamccodg());
			st.setString(2, obj.getFamctitulo());
			st.setInt(3, Integer.parseInt(obj.getFamncgsgr()));
			st.setString(4, obj.getFamccgsgr());
			st.setInt(5, Integer.parseInt(obj.getFamncodg()));
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
	public void add(BeanFamilia obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FAMILIA_ADD);
			st.setString(1, obj.getFamccodg());
			st.setString(2, obj.getFamctitulo());
			st.setInt(3, Integer.parseInt(obj.getFamncgsgr()));
			st.setString(4, obj.getFamccgsgr());
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
	public void remove(BeanFamilia obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FAMILIA_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getFamncodg()));
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
