package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanGrupo;
import com.grupoexata.bancario.utils.Utils;

public class ModelGrupo {

	public static ModelGrupo getInstance(){
		return new ModelGrupo();
	}
	
	public ArrayList<BeanGrupo> getGrupos(){
		ArrayList<BeanGrupo> Grupos = new ArrayList<BeanGrupo>();
		try {
			String sql = " SELECT * FROM VW_GRUPO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			Grupos.addAll(Utils.getObjectsStr(st, BeanGrupo.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return Grupos;
		
	}
	
	public BeanGrupo getGrupo(int grncodg){
		BeanGrupo Grupo = null;
		
		try {
			String sql = "SELECT * FROM VW_GRUPO WHERE GRNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, grncodg);
			
			List<BeanGrupo> l = Utils.getObjectsStr(st, BeanGrupo.class); 
			
			if (!l.isEmpty())
				Grupo = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Grupo;
		
	}

	public void inserir(BeanGrupo grupo) {
		try {
			String sql = "INSERT INTO GRUPO (GRNCODG, GRCDESC) VALUES (?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(grupo.getGrncodg()));
			st.setString(2, grupo.getGrcdesc());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void update(BeanGrupo grupo) {
		try {
			String sql = "UPDATE GRUPO SET GRCDESC = ? WHERE GRNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, grupo.getGrcdesc());
			st.setInt(2, Integer.parseInt(grupo.getGrncodg()));
			
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
