package com.grupoexata.folha.dao;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.util.Model;
import com.grupoexata.folha.bean.BeanFolhaEmp;

public class ModelFolhaEmp implements Model<BeanFolhaEmp>{
	private static final String QR_FOLHA_EMP_BY_ID ="SELECT FOENCODG, FOENCGEMP, FOENCGFOL, FOEYLIQD FROM FOLHA_EMP WHERE FOENCODG = ?";
	private static final String QR_FOLHA_EMP_BY_FOENCODG ="SELECT FOENCODG, FOENCGEMP, FOENCGFOL, FOEYLIQD, ENNCODG, ENCNOME FROM VW_FOLHA_EMP WHERE FOENCODG = ?";
	private static final String QR_FOLHA_EMP_LIST ="SELECT FOENCODG, FOENCGEMP, FOENCGFOL, FOEYLIQD, ENNCODG, ENCNOME FROM VW_FOLHA_EMP WHERE FOENCGFOL = ? ORDER BY ENCNOME";
	private static final String QR_FOLHA_EMP_SET ="UPDATE FOLHA_EMP SET FOENCGEMP = ? , FOENCGFOL = ? WHERE FOENCODG = ?";
	private static final String QR_FOLHA_EMP_ADD ="INSERT INTO FOLHA_EMP(FOENCGEMP , FOENCGFOL) VALUES (? , ?)";
	private static final String QR_FOLHA_EMP_REMOVE ="DELETE FROM FOLHA_EMP WHERE FOENCODG = ?";
	
	public static ModelFolhaEmp getInstance(){
		return new ModelFolhaEmp();
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
	
	
	public BeanFolhaEmp getByFoencodg(String foencodg){
		BeanFolhaEmp obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FOLHA_EMP_BY_FOENCODG);
			st.setInt(1, Integer.parseInt(foencodg));
			obj = Utils.getObjectsStrFirst(st, BeanFolhaEmp.class);
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
	public BeanFolhaEmp get(BeanFolhaEmp bean){
		BeanFolhaEmp obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FOLHA_EMP_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getFoencodg()));
			obj = Utils.getObjectsStrFirst(st, BeanFolhaEmp.class);
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
	public List<BeanFolhaEmp> list(String folncodg){
		List<BeanFolhaEmp> list = new ArrayList<BeanFolhaEmp>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FOLHA_EMP_LIST);
			st.setInt(1, Integer.parseInt(folncodg));
			list = Utils.getObjectsStr(st, BeanFolhaEmp.class);
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
	public List<BeanFolhaEmp> list(){
		List<BeanFolhaEmp> list = new ArrayList<BeanFolhaEmp>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FOLHA_EMP_LIST);
			list = Utils.getObjectsStr(st, BeanFolhaEmp.class);
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
	public List<BeanFolhaEmp> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanFolhaEmp obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FOLHA_EMP_SET);
			st.setInt(1, Integer.parseInt(obj.getFoencgemp()));
			st.setInt(2, Integer.parseInt(obj.getFoencgfol()));
			st.setInt(3, Integer.parseInt(obj.getFoencodg()));
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
	public void add(BeanFolhaEmp obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FOLHA_EMP_ADD);
			st.setInt(1, Integer.parseInt(obj.getFoencgemp()));
			st.setInt(2, Integer.parseInt(obj.getFoencgfol()));
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
	public void remove(BeanFolhaEmp obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_FOLHA_EMP_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getFoencodg()));
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
