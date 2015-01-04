package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanCategoriaEmpregado;
import com.grupoexata.folha.util.Model;

public class ModelCategoriaEmpregado implements Model<BeanCategoriaEmpregado>{
	private static final String QR_CATEGORIA_EMPREGADO_BY_ID ="SELECT CTENCODG, CTECDESC FROM CATEGORIA_EMPREGADO WHERE CTENCODG = ?";
	private static final String QR_CATEGORIA_EMPREGADO_LIST ="SELECT CTENCODG, CTECDESC FROM CATEGORIA_EMPREGADO";
	private static final String QR_CATEGORIA_EMPREGADO_SET ="UPDATE CATEGORIA_EMPREGADO SET CTECDESC = ? WHERE CTENCODG = ?";
	private static final String QR_CATEGORIA_EMPREGADO_ADD ="INSERT INTO CATEGORIA_EMPREGADO(CTECDESC) VALUES (?)";
	private static final String QR_CATEGORIA_EMPREGADO_REMOVE ="DELETE FROM CATEGORIA_EMPREGADO WHERE CTENCODG = ?";
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
	public BeanCategoriaEmpregado get(BeanCategoriaEmpregado bean){
		BeanCategoriaEmpregado obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_CATEGORIA_EMPREGADO_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getCtencodg()));
			obj = Utils.getObjectsStrFirst(st, BeanCategoriaEmpregado.class);
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
	public List<BeanCategoriaEmpregado> list(){
		List<BeanCategoriaEmpregado> list = new ArrayList<BeanCategoriaEmpregado>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_CATEGORIA_EMPREGADO_LIST);
			list = Utils.getObjectsStr(st, BeanCategoriaEmpregado.class);
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
	public List<BeanCategoriaEmpregado> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanCategoriaEmpregado obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_CATEGORIA_EMPREGADO_SET);
			st.setString(1, obj.getCtecdesc());
			st.setInt(2, Integer.parseInt(obj.getCtencodg()));
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
	public void add(BeanCategoriaEmpregado obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_CATEGORIA_EMPREGADO_ADD);
			st.setString(1, obj.getCtecdesc());
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
	public void remove(BeanCategoriaEmpregado obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_CATEGORIA_EMPREGADO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getCtencodg()));
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
