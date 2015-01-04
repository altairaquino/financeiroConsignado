package com.grupoexata.folha.dao;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.util.Model;
import com.grupoexata.folha.bean.BeanFolha;

public class ModelFolha implements Model<BeanFolha>{
	private static final String QR_FOLHA_BY_ID ="SELECT * FROM VW_FOLHA WHERE FOLNCODG = ?";
	private static final String QR_FOLHA_LIST ="SELECT * FROM VW_FOLHA";
	private static final String QR_FOLHA_SET ="UPDATE FOLHA SET FOLNDIAS = ? , FOLNNUM = ? , FOLDDATA = ? , FOLDCADT = ? , FOLDMES = ? , FOLNCGEP = ?, FOLCDESC = ? WHERE FOLNCODG = ?";
	private static final String QR_FOLHA_ADD ="INSERT INTO FOLHA(FOLNDIAS , FOLNNUM , FOLDDATA , FOLDMES , FOLNCGEP, FOLCDESC) VALUES (? , ? , ? , ? , ?, ?)";
	private static final String QR_FOLHA_REMOVE ="DELETE FROM FOLHA WHERE FOLNCODG = ?";
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
	public BeanFolha get(BeanFolha bean){
		BeanFolha obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FOLHA_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getFolncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanFolha.class);
			obj.setFoldmes(obj.getFoldmes().substring(3));
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
	public List<BeanFolha> list(){
		List<BeanFolha> list = new ArrayList<BeanFolha>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FOLHA_LIST);
			list = Utils.getObjectsStr(st, BeanFolha.class);
			for (BeanFolha beanFolha : list) {
				beanFolha.setFoldmes(beanFolha.getFoldmes().substring(3));
			}
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
	public List<BeanFolha> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanFolha obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FOLHA_SET);
			st.setInt(1, Integer.parseInt(obj.getFolndias()));
			st.setInt(2, Integer.parseInt(obj.getFolnnum()));
			st.setDate(3, Utils.stringToDateSQL(obj.getFolddata()));
			st.setDate(4, Utils.stringToDateSQL(obj.getFoldcadt()));
			st.setDate(5, Utils.stringToDateSQL("01/" + obj.getFoldmes()));
			st.setInt(6, Integer.parseInt(obj.getFolncgep()));
			st.setString(7, obj.getFolcdesc());
			st.setInt(8, Integer.parseInt(obj.getFolncodg()));
			
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
	public void add(BeanFolha obj){
		PreparedStatement st = null;
		try {
			/*FOLNDIAS , FOLNNUM , FOLDDATA , FOLDMES , FOLNCGEP*/
			st = getConnection().prepareStatement(QR_FOLHA_ADD);
			st.setInt(1, Integer.parseInt(obj.getFolndias()));
			st.setInt(2, Integer.parseInt(obj.getFolnnum()));
			st.setDate(3, Utils.stringToDateSQL(obj.getFolddata()));
			st.setDate(4, Utils.stringToDateSQL("01/" + obj.getFoldmes()));
			st.setInt(5, Integer.parseInt(obj.getFolncgep()));
			st.setString(6, obj.getFolcdesc());
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
	public void remove(BeanFolha obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FOLHA_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getFolncodg()));
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
