package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanTipoAdmissao;
import com.grupoexata.folha.util.Model;

public class ModelTipoAdmissao implements Model<BeanTipoAdmissao>{
	private static final String QR_TIPO_ADMISSAO_BY_ID ="SELECT TADNCODG, TADCDESC FROM TIPO_ADMISSAO WHERE TADNCODG = ?";
	private static final String QR_TIPO_ADMISSAO_LIST ="SELECT TADNCODG, TADCDESC FROM TIPO_ADMISSAO";
	private static final String QR_TIPO_ADMISSAO_SET ="UPDATE TIPO_ADMISSAO SET TADCDESC = ? WHERE TADNCODG = ?";
	private static final String QR_TIPO_ADMISSAO_ADD ="INSERT INTO TIPO_ADMISSAO(TADCDESC) VALUES (?)";
	private static final String QR_TIPO_ADMISSAO_REMOVE ="DELETE FROM TIPO_ADMISSAO WHERE TADNCODG = ?";
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
	public BeanTipoAdmissao get(BeanTipoAdmissao bean){
		BeanTipoAdmissao obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPO_ADMISSAO_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getTadncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanTipoAdmissao.class);
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
	public List<BeanTipoAdmissao> list(){
		List<BeanTipoAdmissao> list = new ArrayList<BeanTipoAdmissao>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPO_ADMISSAO_LIST);
			list = Utils.getObjectsStr(st, BeanTipoAdmissao.class);
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
	public List<BeanTipoAdmissao> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanTipoAdmissao obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPO_ADMISSAO_SET);
			st.setString(1, obj.getTadcdesc());
			st.setInt(2, Integer.parseInt(obj.getTadncodg()));
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
	public void add(BeanTipoAdmissao obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPO_ADMISSAO_ADD);
			st.setString(1, obj.getTadcdesc());
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
	public void remove(BeanTipoAdmissao obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TIPO_ADMISSAO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getTadncodg()));
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
