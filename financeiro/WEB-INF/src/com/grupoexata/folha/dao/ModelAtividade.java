package com.grupoexata.folha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.FormataObj;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.folha.bean.BeanAtividade;

public class ModelAtividade {
	
	private static final String QR_ATIVIDADE_BY_ID ="SELECT * FROM VW_ATIVIDADE WHERE ATINCODG = ?";
	private static final String QR_ATIVIDADE_LIST ="SELECT * FROM VW_ATIVIDADE";
	
	public static ModelAtividade getInstance(){
		return new ModelAtividade();
	}
	
	public Connection getConnection(){
		return Banco.getConnection();
	}
	
	public void closeConnection(){
		try{
			if(getConnection() != null && !getConnection().isClosed()){
				getConnection().close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public BeanAtividade get(int atincodg){
		BeanAtividade obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_ATIVIDADE_BY_ID);
			st.setInt(1, atincodg);
			obj = Utils.getObjectsStrFirst(st, BeanAtividade.class);
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
	
	public List<BeanAtividade> list(){
		List<BeanAtividade> list = new ArrayList<BeanAtividade>();
		PreparedStatement st = null;
		try {
			
			st = getConnection().prepareStatement(QR_ATIVIDADE_LIST);
			list = Utils.getObjectsStr(st, BeanAtividade.class);
			
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
	
	public List<BeanAtividade> getAtividadesDaEntidade(int enncodg){
		List<BeanAtividade> list = new ArrayList<BeanAtividade>();
		PreparedStatement st = null;
		try {
			String query = "SELECT * FROM VW_ATIVIDADE " +
					       " WHERE ATINCGEN = ? " +
					       " AND ATINCANC = 'F'" +
					       " AND ATIDDATA >= CAST(ADDDAY('NOW',-1) AS DATE)";
			
			st = getConnection().prepareStatement(query);
			st.setInt(1, enncodg);
			
			list = Utils.getObjectsStr(st, BeanAtividade.class);
			
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
	
	public List<String> datasAtividade(){
		List<String> datas = new ArrayList<String>();
		try {
			
			Date d = new Date();
			datas.add(new FormataObj().formataData(d));
			datas.add(new FormataObj().formataData(Utils.incrementaData(d, -1)));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return datas;
	}


	public void update(BeanAtividade beanAtividade){
		PreparedStatement st = null;
		try {
			String query = " UPDATE ATIVIDADE SET ATINCGEN = ?, ATIDDATA = ?, ATITHORA = ?, ATICLOCA = ?, " +
					       " ATICCONT = ?, ATICFONE = ?, ATICDESC = ?, ATICOBSV = ?, ATICPERS = ?, ATIYGAST = ?, " +
					       " ATINODIN = ?, ATINODFN = ?, ATINCGCD = ?, ATICENDR = ?, ATICBAIR = ?"+
						   " WHERE ATINCODG = ?";

				st = getConnection().prepareStatement(query);
				
				st.setInt(1, Integer.parseInt(beanAtividade.getAtincgen()));
				st.setDate(2, Utils.stringToDateSQL(beanAtividade.getAtiddata()));
				st.setTime(3, Time.valueOf(beanAtividade.getAtithora()));
				st.setString(4, beanAtividade.getAticloca().toUpperCase());
				st.setString(5, beanAtividade.getAticcont().toUpperCase());
				st.setString(6, ValidaObjeto.removeCharOfInteger(beanAtividade.getAticfone()));
				st.setString(7, beanAtividade.getAticdesc());
				st.setString(8, beanAtividade.getAticobsv());
				st.setString(9, beanAtividade.getAticpers());
				st.setFloat(10, Float.parseFloat(Utils.converteFloatBR(beanAtividade.getAtiygast())));
				
				if (ValidaObjeto.validaInteiro(beanAtividade.getAtinodin())){
					st.setInt(11, Integer.parseInt(beanAtividade.getAtinodin()));
				}else{
					st.setNull(11, Types.INTEGER);
				}
				if (ValidaObjeto.validaInteiro(beanAtividade.getAtinodfn())){
					st.setInt(12, Integer.parseInt(beanAtividade.getAtinodfn()));
				}else{
					st.setNull(12, Types.INTEGER);
				}
				
				if (Integer.parseInt(beanAtividade.getAtincgcd())!= -1){
					st.setInt(13, Integer.parseInt(beanAtividade.getAtincgcd()));
				}else{
					st.setNull(13, Types.INTEGER);
				}
				st.setString(14, beanAtividade.getAticendr());
				st.setString(15, beanAtividade.getAticbair());
				
				st.setInt(16, Integer.parseInt(beanAtividade.getAtincodg()));
				
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
	
	public void insert(BeanAtividade beanAtividade){
		PreparedStatement st = null;
		try {
			
			String query = " INSERT INTO ATIVIDADE(ATINCGEN, ATIDDATA, ATITHORA, ATICLOCA, " +
					       " ATICCONT, ATICFONE, ATICDESC, ATICOBSV, ATICPERS, ATIYGAST, " +
					       " ATINODIN, ATINODFN, ATINCGCD, ATICENDR, ATICBAIR)"+
						   " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			st = getConnection().prepareStatement(query);
			
			st.setInt(1, Integer.parseInt(beanAtividade.getAtincgen()));
			st.setDate(2, Utils.stringToDateSQL(beanAtividade.getAtiddata()));
			st.setTime(3, Time.valueOf(beanAtividade.getAtithora()));
			st.setString(4, beanAtividade.getAticloca().toUpperCase());
			st.setString(5, beanAtividade.getAticcont().toUpperCase());
			st.setString(6, ValidaObjeto.removeCharOfInteger(beanAtividade.getAticfone()));
			st.setString(7, beanAtividade.getAticdesc());
			st.setString(8, beanAtividade.getAticobsv());
			st.setString(9, beanAtividade.getAticpers());
			st.setFloat(10, Float.parseFloat(Utils.converteFloatBR(beanAtividade.getAtiygast())));
			
			if (ValidaObjeto.validaInteiro(beanAtividade.getAtinodin())){
				st.setInt(11, Integer.parseInt(beanAtividade.getAtinodin()));
			}else{
				st.setNull(11, Types.INTEGER);
			}
			if (ValidaObjeto.validaInteiro(beanAtividade.getAtinodfn())){
				st.setInt(12, Integer.parseInt(beanAtividade.getAtinodfn()));
			}else{
				st.setNull(12, Types.INTEGER);
			}
			
			if (Integer.parseInt(beanAtividade.getAtincgcd())!= -1){
				st.setInt(13, Integer.parseInt(beanAtividade.getAtincgcd()));
			}else{
				st.setNull(13, Types.INTEGER);
			}
			st.setString(14, beanAtividade.getAticendr());
			st.setString(15, beanAtividade.getAticbair());
			
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

	public void cancelar(int atincodg, int enncodg) {
		PreparedStatement st = null;
		try {
			
			String query = " UPDATE ATIVIDADE SET" +
					       " ATINCANC = 'T'"+
						   " WHERE ATINCODG = ?" +
						   " AND ATINCGEN = ?";
			
			st = getConnection().prepareStatement(query);
			
			st.setInt(1, atincodg);
			st.setInt(2, enncodg);
			
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
