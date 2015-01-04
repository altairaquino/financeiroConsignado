package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanItemDescRend;
import com.grupoexata.folha.util.Model;

public class ModelItemDescRend implements Model<BeanItemDescRend>{
	private static final String QR_ITEM_DESC_REND_BY_ID ="SELECT IDRNCODG, IDRNCGDER, IDRNCGTDR, IDRCTIPO FROM ITEM_DESC_REND WHERE IDRNCODG = ?";
	private static final String QR_ITEM_DESC_REND_LIST ="SELECT IDRNCODG, IDRNCGDER, IDRNCGTDR, IDRCTIPO FROM ITEM_DESC_REND";
	//private static final String QR_ITEM_DESC_REND_LIST_BY_IDRNCGDER ="SELECT IDRNCODG, IDRNCGDER, IDRNCGTDR, IDRCTIPO FROM ITEM_DESC_REND WHERE IDRNCGDER = ?";
	//private static final String QR_ITEM_DESC_REND_LIST_BY_IDRNCGDER ="SELECT IDRNCODG, DERNCODG \"IDRNCGDER.DERNCODG\", IDRCTIPO, TDRNCODG \"IDRNCGTDR.TDRNCODG\" , TDRCDESC \"IDRNCGTDR.TDRCDESC\"  FROM ITEM_DESC_REND LEFT JOIN TIPO_DESC_REND ON IDRNCGTDR = TDRNCODG LEFT JOIN DESC_REND ON  IDRNCGDER = DERNCODG WHERE IDRNCGDER = ?";
	private static final String QR_ITEM_DESC_REND_LIST_BY_IDRNCGDER ="SELECT IDRNCODG, DERNCODG \"IDRNCGDER.DERNCODG\", IDRCTIPO, TDRNCODG \"IDRNCGTDR.TDRNCODG\" , TDRCDESC \"IDRNCGTDR.TDRCDESC\"  FROM TIPO_DESC_REND LEFT JOIN ITEM_DESC_REND ON IDRNCGTDR = TDRNCODG and IDRNCGDER = ? LEFT JOIN DESC_REND ON IDRNCGDER = DERNCODG";
	private static final String QR_ITEM_DESC_REND_SET ="UPDATE ITEM_DESC_REND SET IDRNCGDER = ? , IDRNCGTDR = ? , IDRCTIPO = ? WHERE IDRNCODG = ?";
	private static final String QR_ITEM_DESC_REND_ADD ="INSERT INTO ITEM_DESC_REND(IDRNCGDER , IDRNCGTDR , IDRCTIPO) VALUES (? , ? , ?)";
	private static final String QR_ITEM_DESC_REND_REMOVE ="DELETE FROM ITEM_DESC_REND WHERE IDRNCODG = ?";
	private static final String QR_ITEM_DESC_REND_ADD_REMOVE ="SELECT IDRCTIPO FROM SP_ADD_REMOVE_IDR(?,?,?)";
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
	
	public BeanItemDescRend addRemove(BeanItemDescRend bean){
		BeanItemDescRend obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_ITEM_DESC_REND_ADD_REMOVE);
			st.setInt(1, Integer.parseInt(bean.getIdrncgder().getDerncodg()));
			st.setInt(2, Integer.parseInt(bean.getIdrncgtdr().getTdrncodg()));
			st.setString(3, bean.getIdrctipo()); 
			obj = Utils.getObjectsStrFirst(st, BeanItemDescRend.class);
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
	public BeanItemDescRend get(BeanItemDescRend bean){
		BeanItemDescRend obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_ITEM_DESC_REND_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getIdrncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanItemDescRend.class);
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
	public List<BeanItemDescRend> list(String idrncgder){
		List<BeanItemDescRend> list = new ArrayList<BeanItemDescRend>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_ITEM_DESC_REND_LIST_BY_IDRNCGDER);
			st.setInt(1, Integer.parseInt(idrncgder));
			list = Utils.getObjsStrRec(st, BeanItemDescRend.class);
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
	public List<BeanItemDescRend> list(){
		List<BeanItemDescRend> list = new ArrayList<BeanItemDescRend>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_ITEM_DESC_REND_LIST);
			list = Utils.getObjectsStr(st, BeanItemDescRend.class);
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
	public List<BeanItemDescRend> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanItemDescRend obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_ITEM_DESC_REND_SET);
			//st.setInt(1, Integer.parseInt(obj.getIdrncgder()));
//			st.setInt(2, Integer.parseInt(obj.getIdrncgtdr()));
			st.setString(3, obj.getIdrctipo());
			st.setInt(4, Integer.parseInt(obj.getIdrncodg()));
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
	public void add(BeanItemDescRend obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_ITEM_DESC_REND_ADD);
			//st.setInt(1, Integer.parseInt(obj.getIdrncgder()));
//			st.setInt(2, Integer.parseInt(obj.getIdrncgtdr()));
			st.setString(3, obj.getIdrctipo());
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
	public void remove(BeanItemDescRend obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_ITEM_DESC_REND_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getIdrncodg()));
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
