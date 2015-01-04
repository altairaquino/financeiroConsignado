package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanTabela;
import com.grupoexata.folha.util.Model;

public class ModelTabela implements Model<BeanTabela>{
	private static final String QR_TABELA_BY_ID ="SELECT TABNCODG, TABNCGTTB, TABNLIMT, TABNPDES, TABNPARC, TABNXXX FROM TABELA WHERE TABNCODG = ?";
	private static final String QR_TABELA_LIST ="SELECT TABNCODG, TABNCGTTB, TABNLIMT, TABNPDES, TABNPARC, TABNXXX FROM TABELA";
	private static final String QR_TABELA_LIST_BY_TABNCGTTB ="SELECT TABNCODG, TABNCGTTB, TABNLIMT, TABNPDES, TABNPARC, TABNXXX FROM TABELA WHERE TABNCGTTB = ?";
	private static final String QR_TABELA_SET ="UPDATE TABELA SET TABNCGTTB = ? , TABNLIMT = ? , TABNPDES = ? , TABNPARC = ? , TABNXXX = ? WHERE TABNCODG = ?";
	private static final String QR_TABELA_ADD ="INSERT INTO TABELA(TABNCGTTB , TABNLIMT , TABNPDES , TABNPARC , TABNXXX) VALUES (? , ? , ? , ? , ?)";
	private static final String QR_TABELA_REMOVE ="DELETE FROM TABELA WHERE TABNCODG = ?";
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
	public BeanTabela get(BeanTabela bean){
		BeanTabela obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TABELA_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getTabncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanTabela.class);
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
	public List<BeanTabela> list(String tabncgttb){
		List<BeanTabela> list = new ArrayList<BeanTabela>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TABELA_LIST_BY_TABNCGTTB);
			st.setInt(1, Integer.parseInt(tabncgttb));
			list = Utils.getObjectsStr(st, BeanTabela.class);
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
	public List<BeanTabela> list(){
		List<BeanTabela> list = new ArrayList<BeanTabela>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TABELA_LIST);
			list = Utils.getObjectsStr(st, BeanTabela.class);
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
	public List<BeanTabela> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanTabela obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TABELA_SET);
			st.setInt(1, Integer.parseInt(obj.getTabncgttb()));
			st.setFloat(2, Float.parseFloat(Utils.converteFloatBR(obj.getTabnlimt())));
			String s;
			s = obj.getTabnpdes();
			if(s !=null && !s.equals("")){
				st.setFloat(3, Float.parseFloat(Utils.converteFloatBR(s)));
			}else{
				st.setNull(3, Types.FLOAT);
			}
			s = obj.getTabnparc();
			if(s !=null && !s.equals("")){
				st.setFloat(4, Float.parseFloat(Utils.converteFloatBR(s)));
			}else{
				st.setNull(4, Types.FLOAT);
			}
			s = obj.getTabnxxx();
			if(s !=null && !s.equals("")){
				st.setFloat(5, Float.parseFloat(Utils.converteFloatBR(s)));
			}else{
				st.setNull(5, Types.FLOAT);
			}
			st.setInt(6, Integer.parseInt(obj.getTabncodg()));
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
	public void add(BeanTabela obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TABELA_ADD);
			st.setInt(1, Integer.parseInt(obj.getTabncgttb()));
			st.setFloat(2, Float.parseFloat(Utils.converteFloatBR(obj.getTabnlimt())));
			String s;
			s = obj.getTabnpdes();
			if(s !=null && !s.equals("")){
				st.setFloat(3, Float.parseFloat(Utils.converteFloatBR(s)));
			}else{
				st.setNull(3, Types.FLOAT);
			}
			s = obj.getTabnparc();
			if(s !=null && !s.equals("")){
				st.setFloat(4, Float.parseFloat(Utils.converteFloatBR(s)));
			}else{
				st.setNull(4, Types.FLOAT);
			}
			s = obj.getTabnxxx();
			if(s !=null && !s.equals("")){
				st.setFloat(5, Float.parseFloat(Utils.converteFloatBR(s)));
			}else{
				st.setNull(5, Types.FLOAT);
			}
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
	public void remove(BeanTabela obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_TABELA_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getTabncodg()));
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
