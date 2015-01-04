package com.grupoexata.juridico.dao;

import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.juridico.struts.bean.BeanAudiencia;

public class ModelAudiencia {

	public static ModelAudiencia getInstance(){
		return new ModelAudiencia();
	}
	
	public ArrayList<BeanAudiencia> getAudienciasDoProcesso(int proncodg){
		ArrayList<BeanAudiencia> audiencias = new ArrayList<BeanAudiencia>();
		try {
			String sql = " SELECT * FROM VW_Audiencia WHERE ADCNCGPRO = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, proncodg);
			
			audiencias.addAll(Utils.getObjectsStr(st, BeanAudiencia.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return audiencias;
		
	}	
	
	public BeanAudiencia getAudiencia(int adcncodg){
		BeanAudiencia ligacao = null;
		
		try {
			String sql = "SELECT * FROM VW_Audiencia WHERE ADCNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, adcncodg);
			
			List<BeanAudiencia> l = Utils.getObjectsStr(st, BeanAudiencia.class); 
			
			if (!l.isEmpty())
				ligacao = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return ligacao;
		
	}
	
	public void insert(BeanAudiencia audiencia){
		try {
			String sql = " INSERT INTO AUDIENCIA(ADCNCGPRO, ADCDDATA, ADCHHORA, ADCNCGCD, ADCCADVO, ADCCDESC, ADCNCADT)" +
						 " VALUES(?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(audiencia.getAdcncgpro()));
			st.setDate(2, Utils.stringToDateSQL(audiencia.getAdcddata()));
			st.setTime(3, Time.valueOf(audiencia.getAdchhora()));
			st.setInt(4, Integer.parseInt(audiencia.getAdcncgcd()));
			st.setString(5, audiencia.getAdccadvo().toUpperCase());
			st.setString(6, audiencia.getAdccdesc());
			st.setInt(7, Integer.parseInt(audiencia.getAdcncadt()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(BeanAudiencia audiencia){
		try {
			String sql = " UPDATE AUDIENCIA SET ADCDDATA = ?, ADCHHORA = ?, " +
					     " ADCNCGCD = ?, ADCCADVO = ?, ADCCDESC = ?, ADCNALTE = ?" +
					     " WHERE ADCNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setDate(1, Utils.stringToDateSQL(audiencia.getAdcddata()));
			st.setTime(2, Time.valueOf(audiencia.getAdchhora()));
			st.setInt(3, Integer.parseInt(audiencia.getAdcncgcd()));
			st.setString(4, audiencia.getAdccadvo().toUpperCase());
			st.setString(5, audiencia.getAdccdesc());
			st.setInt(6, Integer.parseInt(audiencia.getAdcnalte()));
			st.setInt(7, Integer.parseInt(audiencia.getAdcncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
