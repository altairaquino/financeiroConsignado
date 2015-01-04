package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanSituacaoEmpregado;
import com.grupoexata.folha.util.Model;

public class ModelSituacaoEmpregado implements Model<BeanSituacaoEmpregado>{
	private static final String QR_SITUACAO_EMPREGADO_BY_ID ="SELECT STENCODG, STECCODG, STECDESC FROM SITUACAO_EMPREGADO WHERE STENCODG = ?";
	private static final String QR_SITUACAO_EMPREGADO_LIST ="SELECT STENCODG, STECCODG, STECDESC FROM SITUACAO_EMPREGADO";
	private static final String QR_SITUACAO_EMPREGADO_SET ="UPDATE SITUACAO_EMPREGADO SET STECCODG = ? , STECDESC = ? WHERE STENCODG = ?";
	private static final String QR_SITUACAO_EMPREGADO_ADD ="INSERT INTO SITUACAO_EMPREGADO(STECCODG , STECDESC) VALUES (? , ?)";
	private static final String QR_SITUACAO_EMPREGADO_REMOVE ="DELETE FROM SITUACAO_EMPREGADO WHERE STENCODG = ?";
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
	public BeanSituacaoEmpregado get(BeanSituacaoEmpregado bean){
		BeanSituacaoEmpregado obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SITUACAO_EMPREGADO_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getStencodg()));
			obj = Utils.getObjectsStrFirst(st, BeanSituacaoEmpregado.class);
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
	public List<BeanSituacaoEmpregado> list(){
		List<BeanSituacaoEmpregado> list = new ArrayList<BeanSituacaoEmpregado>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SITUACAO_EMPREGADO_LIST);
			list = Utils.getObjectsStr(st, BeanSituacaoEmpregado.class);
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
	public List<BeanSituacaoEmpregado> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanSituacaoEmpregado obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SITUACAO_EMPREGADO_SET);
			st.setString(1, obj.getSteccodg());
			st.setString(2, obj.getStecdesc());
			st.setInt(3, Integer.parseInt(obj.getStencodg()));
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
	public void add(BeanSituacaoEmpregado obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SITUACAO_EMPREGADO_ADD);
			st.setString(1, obj.getSteccodg());
			st.setString(2, obj.getStecdesc());
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
	public void remove(BeanSituacaoEmpregado obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SITUACAO_EMPREGADO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getStencodg()));
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
