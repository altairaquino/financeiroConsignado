package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanDependente;
import com.grupoexata.folha.util.Model;

public class ModelDependente implements Model<BeanDependente>{
	private static final String QR_DEPENDENTE_BY_ID ="SELECT DEPNCODG, DEPNCGEN, DEPCNOME, DEPDNASC FROM DEPENDENTE WHERE DEPNCODG = ?";
	private static final String QR_DEPENDENTE_LIST_BY_DEPNCGEN ="SELECT DEPNCODG, DEPNCGEN, DEPCNOME, DEPDNASC FROM DEPENDENTE WHERE DEPNCGEN = ?";
	private static final String QR_DEPENDENTE_LIST ="SELECT DEPNCODG, DEPNCGEN, DEPCNOME, DEPDNASC FROM DEPENDENTE";
	private static final String QR_DEPENDENTE_SET ="UPDATE DEPENDENTE SET DEPNCGEN = ? , DEPCNOME = ? , DEPDNASC = ? WHERE DEPNCODG = ?";
	private static final String QR_DEPENDENTE_ADD ="INSERT INTO DEPENDENTE(DEPNCGEN , DEPCNOME , DEPDNASC) VALUES (? , ? , ?)";
	private static final String QR_DEPENDENTE_REMOVE ="DELETE FROM DEPENDENTE WHERE DEPNCODG = ?";
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
	public BeanDependente get(BeanDependente bean){
		BeanDependente obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_DEPENDENTE_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getDepncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanDependente.class);
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
	public List<BeanDependente> listByDepncgen(String depncgen){
		List<BeanDependente> list = new ArrayList<BeanDependente>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_DEPENDENTE_LIST_BY_DEPNCGEN);
			st.setInt(1, Integer.parseInt(depncgen));
			list = Utils.getObjectsStr(st, BeanDependente.class);
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
	public List<BeanDependente> list(){
		List<BeanDependente> list = new ArrayList<BeanDependente>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_DEPENDENTE_LIST);
			list = Utils.getObjectsStr(st, BeanDependente.class);
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
	public List<BeanDependente> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanDependente obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_DEPENDENTE_SET);
			st.setInt(1, Integer.parseInt(obj.getDepncgen()));
			st.setString(2, obj.getDepcnome());
			st.setDate(3, Utils.stringToDateSQL(obj.getDepdnasc()));
			st.setInt(4, Integer.parseInt(obj.getDepncodg()));
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
	public void add(BeanDependente obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_DEPENDENTE_ADD);
			st.setInt(1, Integer.parseInt(obj.getDepncgen()));
			st.setString(2, obj.getDepcnome());
			st.setDate(3, Utils.stringToDateSQL(obj.getDepdnasc()));
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
	public void remove(BeanDependente obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_DEPENDENTE_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getDepncodg()));
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
