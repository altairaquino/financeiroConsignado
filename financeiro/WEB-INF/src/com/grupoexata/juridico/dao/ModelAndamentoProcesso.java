package com.grupoexata.juridico.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.juridico.struts.bean.BeanAndamentoProcesso;

public class ModelAndamentoProcesso {

	public static ModelAndamentoProcesso getInstance(){
		return new ModelAndamentoProcesso();
	}
	
	public ArrayList<BeanAndamentoProcesso> getAndamentoDoProcesso(int proncodg){
		ArrayList<BeanAndamentoProcesso> fases = new ArrayList<BeanAndamentoProcesso>();
		try {
			String sql = " SELECT * FROM VW_ANDAMENTOPROCESSO WHERE ANPNCGPRO = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, proncodg);
			
			fases.addAll(Utils.getObjectsStr(st, BeanAndamentoProcesso.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return fases;
		
	}
	
	public BeanAndamentoProcesso getAndamentoProcesso(int anpncodg){
		BeanAndamentoProcesso faseProcesso = null;
		
		try {
			String sql = "SELECT * FROM VW_ANDAMENTOPROCESSO WHERE ANPNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, anpncodg);
			
			List<BeanAndamentoProcesso> l = Utils.getObjectsStr(st, BeanAndamentoProcesso.class); 
			
			if (!l.isEmpty())
				faseProcesso = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return faseProcesso;
		
	}
	
	public void insert(BeanAndamentoProcesso andamentoProcesso){
		try {
			String sql = "INSERT INTO " +
					" ANDAMENTOPROCESSO(ANPNCGPRO,ANPNCGFAP, ANPNCGEN, ANPDDATA, ANPCDESC)" +
					" VALUES(?, ?, ?, ?, ?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(andamentoProcesso.getAnpncgpro()));
			st.setInt(2, Integer.parseInt(andamentoProcesso.getAnpncgfap()));
			st.setInt(3, Integer.parseInt(andamentoProcesso.getAnpncgen()));
			st.setDate(4, Utils.stringToDateSQL(andamentoProcesso.getAnpddata()));
			st.setString(5, andamentoProcesso.getAnpcdesc());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(BeanAndamentoProcesso andamentoProcesso){
		try {
			String sql = "UPDATE " +
			" ANDAMENTOPROCESSO SET ANPNCGPRO = ?,ANPNCGFAP = ?, ANPNCGEN = ?, ANPDDATA = ?, ANPCDESC = ?" +
			" WHERE ANPNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(andamentoProcesso.getAnpncgpro()));
			st.setInt(2, Integer.parseInt(andamentoProcesso.getAnpncgfap()));
			st.setInt(3, Integer.parseInt(andamentoProcesso.getAnpncgen()));
			st.setDate(4, Utils.stringToDateSQL(andamentoProcesso.getAnpddata()));
			st.setString(5, andamentoProcesso.getAnpcdesc());
			st.setInt(6, Integer.parseInt(andamentoProcesso.getAnpncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
