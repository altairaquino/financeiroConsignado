package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanUsuarioGrupo;
import com.grupoexata.bancario.utils.Utils;

public class ModelUsuarioGrupo {

	public static ModelUsuarioGrupo getInstance(){
		return new ModelUsuarioGrupo();
	}
	
	public ArrayList<BeanUsuarioGrupo> getUsuariosDoGrupo(int grncodg){
		ArrayList<BeanUsuarioGrupo> UsuarioGrupos = new ArrayList<BeanUsuarioGrupo>();
		try {
			String sql = " SELECT * FROM VW_USUARIOGRUPO WHERE UGNCGGR = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, grncodg);
			
			UsuarioGrupos.addAll(Utils.getObjectsStr(st, BeanUsuarioGrupo.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return UsuarioGrupos;
		
	}
	
	public BeanUsuarioGrupo getUsuarioGrupo(int ugncodg){
		BeanUsuarioGrupo UsuarioGrupo = null;
		
		try {
			String sql = "SELECT * FROM VW_USUARIOGRUPO WHERE UGNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ugncodg);
			
			List<BeanUsuarioGrupo> l = Utils.getObjectsStr(st, BeanUsuarioGrupo.class); 
			
			if (!l.isEmpty())
				UsuarioGrupo = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return UsuarioGrupo;
		
	}

	public void inserir(BeanUsuarioGrupo usuarioGrupo) {
		try {
			String sql = "INSERT INTO USUARIOGRUPO (UGNCGUS, UGNCGGR) VALUES (?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(usuarioGrupo.getUgncgus()));
			st.setInt(2, Integer.parseInt(usuarioGrupo.getUgncggr()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean ehUsuarioGrupo(int enncodg, int grncodg) {
		boolean ret = false;
		try {
			String sql = "SELECT * FROM USUARIOGRUPO WHERE UGNCGUS = ? AND UGNCGGR = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			st.setInt(2, grncodg);
			
			ret = !Utils.getObjectsStr(st, BeanUsuarioGrupo.class).isEmpty();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
		
	}
	
	public void delete(int ugncodg) {
		try {
			String sql = "DELETE FROM USUARIOGRUPO WHERE UGNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, ugncodg);
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
