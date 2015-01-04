package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanGrauInstrucao;
import com.grupoexata.folha.util.Model;

public class ModelGrauInstrucao implements Model<BeanGrauInstrucao>{
	private static final String QR_GRAU_INSTRUCAO_BY_ID ="SELECT GINNCODG, GINCDESC FROM GRAU_INSTRUCAO WHERE GINNCODG = ?";
	private static final String QR_GRAU_INSTRUCAO_LIST ="SELECT GINNCODG, GINCDESC FROM GRAU_INSTRUCAO";
	private static final String QR_GRAU_INSTRUCAO_SET ="UPDATE GRAU_INSTRUCAO SET GINCDESC = ? WHERE GINNCODG = ?";
	private static final String QR_GRAU_INSTRUCAO_ADD ="INSERT INTO GRAU_INSTRUCAO(GINCDESC) VALUES (?)";
	private static final String QR_GRAU_INSTRUCAO_REMOVE ="DELETE FROM GRAU_INSTRUCAO WHERE GINNCODG = ?";
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
	public BeanGrauInstrucao get(BeanGrauInstrucao bean){
		BeanGrauInstrucao obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_GRAU_INSTRUCAO_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getGinncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanGrauInstrucao.class);
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
	public List<BeanGrauInstrucao> list(){
		List<BeanGrauInstrucao> list = new ArrayList<BeanGrauInstrucao>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_GRAU_INSTRUCAO_LIST);
			list = Utils.getObjectsStr(st, BeanGrauInstrucao.class);
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
	public List<BeanGrauInstrucao> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanGrauInstrucao obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_GRAU_INSTRUCAO_SET);
			st.setString(1, obj.getGincdesc());
			st.setInt(2, Integer.parseInt(obj.getGinncodg()));
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
	public void add(BeanGrauInstrucao obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_GRAU_INSTRUCAO_ADD);
			st.setString(1, obj.getGincdesc());
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
	public void remove(BeanGrauInstrucao obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_GRAU_INSTRUCAO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getGinncodg()));
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
