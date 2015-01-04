package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanSubgrupo;
import com.grupoexata.folha.util.Model;

public class ModelSubgrupo implements Model<BeanSubgrupo>{
	private static final String QR_SUBGRUPO_BY_ID ="SELECT SGRNCODG, SGRCCODG, SGRCTITULO, SGRNCGSGP, SGRCCGSGP FROM SUBGRUPO WHERE SGRNCODG = ?";
	private static final String QR_SUBGRUPO_LIST ="SELECT SGRNCODG, SGRCCODG, SGRCTITULO, SGRNCGSGP, SGRCCGSGP FROM SUBGRUPO";
	private static final String QR_SUBGRUPO_SET ="UPDATE SUBGRUPO SET SGRCCODG = ? , SGRCTITULO = ? , SGRNCGSGP = ? , SGRCCGSGP = ? WHERE SGRNCODG = ?";
	private static final String QR_SUBGRUPO_ADD ="INSERT INTO SUBGRUPO(SGRCCODG , SGRCTITULO , SGRNCGSGP , SGRCCGSGP) VALUES (? , ? , ? , ?)";
	private static final String QR_SUBGRUPO_REMOVE ="DELETE FROM SUBGRUPO WHERE SGRNCODG = ?";
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
	public BeanSubgrupo get(BeanSubgrupo bean){
		BeanSubgrupo obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SUBGRUPO_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getSgrncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanSubgrupo.class);
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
	public List<BeanSubgrupo> list(){
		List<BeanSubgrupo> list = new ArrayList<BeanSubgrupo>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SUBGRUPO_LIST);
			list = Utils.getObjectsStr(st, BeanSubgrupo.class);
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
	public List<BeanSubgrupo> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanSubgrupo obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SUBGRUPO_SET);
			st.setString(1, obj.getSgrccodg());
			st.setString(2, obj.getSgrctitulo());
			st.setInt(3, Integer.parseInt(obj.getSgrncgsgp()));
			st.setString(4, obj.getSgrccgsgp());
			st.setInt(5, Integer.parseInt(obj.getSgrncodg()));
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
	public void add(BeanSubgrupo obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SUBGRUPO_ADD);
			st.setString(1, obj.getSgrccodg());
			st.setString(2, obj.getSgrctitulo());
			st.setInt(3, Integer.parseInt(obj.getSgrncgsgp()));
			st.setString(4, obj.getSgrccgsgp());
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
	public void remove(BeanSubgrupo obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_SUBGRUPO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getSgrncodg()));
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
