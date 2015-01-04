package com.grupoexata.folha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanEmpregadoCentrocusto;
import com.grupoexata.folha.util.Model;

public class ModelEmpregadoCentrocusto implements Model<BeanEmpregadoCentrocusto>{
	private static final String QR_EMPREGADO_CENTROCUSTO_BY_ID ="SELECT ECCNCODG, ECCNCGEMP, ECCNCGCR FROM EMPREGADO_CENTROCUSTO WHERE ECCNCODG = ?";
	private static final String QR_EMPREGADO_CENTROCUSTO_LIST ="SELECT ECCNCODG, ECCNCGEMP, ECCNCGCR FROM EMPREGADO_CENTROCUSTO";
	private static final String QR_EMPREGADO_CENTROCUSTO_SET ="UPDATE EMPREGADO_CENTROCUSTO SET ECCNCGEMP = ? , ECCNCGCR = ? WHERE ECCNCODG = ?";
	private static final String QR_EMPREGADO_CENTROCUSTO_ADD ="INSERT INTO EMPREGADO_CENTROCUSTO(ECCNCGEMP , ECCNCGCR) VALUES (? , ?)";
	private static final String QR_EMPREGADO_CENTROCUSTO_REMOVE ="DELETE FROM EMPREGADO_CENTROCUSTO WHERE ECCNCODG = ?";
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
	public BeanEmpregadoCentrocusto get(BeanEmpregadoCentrocusto bean){
		BeanEmpregadoCentrocusto obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_EMPREGADO_CENTROCUSTO_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getEccncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanEmpregadoCentrocusto.class);
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
	public List<BeanEmpregadoCentrocusto> list(){
		List<BeanEmpregadoCentrocusto> list = new ArrayList<BeanEmpregadoCentrocusto>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_EMPREGADO_CENTROCUSTO_LIST);
			list = Utils.getObjectsStr(st, BeanEmpregadoCentrocusto.class);
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
	public List<BeanEmpregadoCentrocusto> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanEmpregadoCentrocusto obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_EMPREGADO_CENTROCUSTO_SET);
			st.setInt(1, Integer.parseInt(obj.getEccncgemp()));
			st.setInt(2, Integer.parseInt(obj.getEccncgcr()));
			st.setInt(3, Integer.parseInt(obj.getEccncodg()));
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
	public void add(BeanEmpregadoCentrocusto obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_EMPREGADO_CENTROCUSTO_ADD);
			st.setInt(1, Integer.parseInt(obj.getEccncgemp()));
			st.setInt(2, Integer.parseInt(obj.getEccncgcr()));
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
	public void remove(BeanEmpregadoCentrocusto obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_EMPREGADO_CENTROCUSTO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getEccncodg()));
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
