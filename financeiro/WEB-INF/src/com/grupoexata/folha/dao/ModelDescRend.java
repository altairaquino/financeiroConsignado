package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanDescRend;
import com.grupoexata.folha.util.Model;

public class ModelDescRend implements Model<BeanDescRend>{
	private static final String QR_DESC_REND_BY_ID ="SELECT DERNCODG, DERCCODG, DERCDESC, DERCTIPO, DERLLIQD, DERNMULT, DERCFORM FROM DESC_REND WHERE DERNCODG = ?";
	private static final String QR_DESC_REND_LIST ="SELECT DERNCODG, DERCCODG, DERCDESC, DERCTIPO, DERLLIQD, DERNMULT, DERCFORM FROM DESC_REND ORDER BY DERCCODG";
	private static final String QR_DESC_REND_SET ="UPDATE DESC_REND SET DERCCODG = ? , DERCDESC = ? , DERCTIPO = ? , DERLLIQD = ? , DERNMULT = ? , DERCFORM = ? WHERE DERNCODG = ?";
	private static final String QR_DESC_REND_ADD ="INSERT INTO DESC_REND(DERCCODG , DERCDESC , DERCTIPO , DERLLIQD , DERNMULT , DERCFORM) VALUES (? , ? , ? , ? , ? , ?)";
	private static final String QR_DESC_REND_REMOVE ="DELETE FROM DESC_REND WHERE DERNCODG = ?";
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
	public BeanDescRend get(BeanDescRend bean){
		BeanDescRend obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_DESC_REND_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getDerncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanDescRend.class);
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
	public List<BeanDescRend> list(){
		List<BeanDescRend> list = new ArrayList<BeanDescRend>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_DESC_REND_LIST);
			list = Utils.getObjectsStr(st, BeanDescRend.class);
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
	public List<BeanDescRend> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanDescRend obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_DESC_REND_SET);
			st.setString(1, obj.getDerccodg());
			st.setString(2, obj.getDercdesc());
			st.setString(3, obj.getDerctipo());
			st.setString(4, obj.getDerlliqd());
			String s = "";
			s = obj.getDernmult();
			if(s !=null && !s.equals("")){
				st.setFloat(5, Float.parseFloat(Utils.converteFloatBR(s)));
			}else{
				st.setNull(5, Types.FLOAT);
			}
			st.setString(6, obj.getDercform());
			st.setInt(7, Integer.parseInt(obj.getDerncodg()));
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
	public void add(BeanDescRend obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_DESC_REND_ADD);
			st.setString(1, obj.getDerccodg());
			st.setString(2, obj.getDercdesc());
			st.setString(3, obj.getDerctipo());
			st.setString(4, obj.getDerlliqd());
			String s = "";
			s = obj.getDernmult();
			if(s !=null && !s.equals("")){
				st.setFloat(5, Float.parseFloat(Utils.converteFloatBR(s)));
			}else{
				st.setNull(5, Types.FLOAT);
			}
			st.setString(6, obj.getDercform());
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
	public void remove(BeanDescRend obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_DESC_REND_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getDerncodg()));
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
