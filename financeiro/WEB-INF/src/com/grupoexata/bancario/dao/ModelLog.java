package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.grupoexata.bancario.struts.bean.BeanLog;
import com.grupoexata.bancario.utils.Utils;

public class ModelLog {
	
	public static ModelLog getInstance(){
		return new ModelLog();
	}
	
	public void registLogEntrada(int enncodg, String IP){
		try {
			String query = " INSERT INTO LOG (LGNCGEN,LGDENTR,LGCREQT)"+
						   " values(?,cast('now' as TIMESTAMP),?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(query);
			
			st.setInt(1, enncodg);
			st.setString(2, IP);
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	public void registLogSaida(int enncodg){
		try {
			String query = " UPDATE LOG l1 SET" +
						   " l1.LGDSAID = CAST('NOW' as TIMESTAMP)" +
						   " WHERE l1.LGDSAID IS NULL " +
						   " AND l1.LGNCODG = (select max(lgncodg) FROM LOG WHERE l1.lgncgen = lgncgen) " +
						   " AND l1.LGNCGEN = ? ";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(query);
			st.setInt(1, enncodg);
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<BeanLog> getLogsUsuario(int enncodg, int quant){
		ArrayList<BeanLog> logs = new ArrayList<BeanLog>();
		try {
			String query = " SELECT FIRST "+quant+" * FROM VW_LOG WHERE LGNCGEN = ? ORDER BY LGDENTR DESC";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(query);
			st.setInt(1, enncodg);
			
			logs.addAll(Utils.getObjectsStr(st, BeanLog.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return logs;
	}

}
