package com.grupoexata.folha.dao;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.util.Model;
import com.grupoexata.folha.bean.BeanFolhaEmpItem;

public class ModelFolhaEmpItem implements Model<BeanFolhaEmpItem>{
	private static final String QR_FOLHA_EMP_ITEM_BY_ID ="SELECT FEINCODG, FEINCGFOE, FEINCGDER, FEIYVALOR, FEIYBASE FROM FOLHA_EMP_ITEM WHERE FEINCODG = ?";
	private static final String QR_FOLHA_EMP_ITEM_BY_FEINCGFOE ="SELECT FEINCODG, FEINCGFOE, FEINCGDER, FEIYVALOR, FEIYBASE, DERNCODG, DERCCODG, DERCDESC, DERCOPER FROM VW_FOLHA_EMP_ITEM WHERE FEINCGFOE =?";
	private static final String QR_FOLHA_EMP_ITEM_LIST ="SELECT FEINCODG, FEINCGFOE, FEINCGDER, FEIYVALOR, FEIYBASE FROM FOLHA_EMP_ITEM";
	private static final String QR_FOLHA_EMP_ITEM_SET ="UPDATE FOLHA_EMP_ITEM SET FEINCGFOE = ? , FEINCGDER = ? , FEIYVALOR = ?, FEIYBASE = ? WHERE FEINCODG = ?";
	private static final String QR_FOLHA_EMP_ITEM_ADD ="INSERT INTO FOLHA_EMP_ITEM(FEINCGFOE , FEINCGDER , FEIYVALOR, FEIYBASE) VALUES (? , ? , ?, ?)";
	private static final String QR_FOLHA_EMP_ITEM_REMOVE ="DELETE FROM FOLHA_EMP_ITEM WHERE FEINCODG = ?";

	public static ModelFolhaEmpItem getInstance(){
		return new ModelFolhaEmpItem();
	}
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
	public BeanFolhaEmpItem get(BeanFolhaEmpItem bean){
		BeanFolhaEmpItem obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FOLHA_EMP_ITEM_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getFeincodg()));
			obj = Utils.getObjectsStrFirst(st, BeanFolhaEmpItem.class);
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
	public List<BeanFolhaEmpItem> list(String feincgfoe){
		List<BeanFolhaEmpItem> list = new ArrayList<BeanFolhaEmpItem>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FOLHA_EMP_ITEM_BY_FEINCGFOE);
			st.setInt(1, Integer.parseInt(feincgfoe));
			list = Utils.getObjectsStr(st, BeanFolhaEmpItem.class);
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
	public List<BeanFolhaEmpItem> list(){
		List<BeanFolhaEmpItem> list = new ArrayList<BeanFolhaEmpItem>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FOLHA_EMP_ITEM_LIST);
			list = Utils.getObjectsStr(st, BeanFolhaEmpItem.class);
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
	public List<BeanFolhaEmpItem> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanFolhaEmpItem obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FOLHA_EMP_ITEM_SET);
			st.setInt(1, Integer.parseInt(obj.getFeincgfoe()));
			st.setInt(2, Integer.parseInt(obj.getFeincgder()));
			st.setObject(3, obj.getFeiyvalor());
			st.setObject(4, obj.getFeiybase());
			st.setInt(5, Integer.parseInt(obj.getFeincodg()));
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
	public void add(BeanFolhaEmpItem obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FOLHA_EMP_ITEM_ADD);
			st.setInt(1, Integer.parseInt(obj.getFeincgfoe()));
			st.setInt(2, Integer.parseInt(obj.getFeincgder()));
			st.setObject(3, obj.getFeiyvalor());
			st.setObject(4, obj.getFeiybase());
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
	public void remove(BeanFolhaEmpItem obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FOLHA_EMP_ITEM_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getFeincodg()));
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
