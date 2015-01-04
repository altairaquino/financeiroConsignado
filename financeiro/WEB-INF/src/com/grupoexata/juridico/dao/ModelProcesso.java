package com.grupoexata.juridico.dao;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.juridico.struts.bean.BeanProcesso;

public class ModelProcesso {
	
	public static ModelProcesso getInstance(){
		return new ModelProcesso();
	}
	
	public BeanProcesso getProcesso(int proncodg){
		BeanProcesso processo = null;
		try {
			String sql = "SELECT * FROM VW_PROCESSO WHERE PRONCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, proncodg);
			
			processo = Utils.getObjectsStrFirst(st, BeanProcesso.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return processo;
	}
	
	public BeanProcesso getProcessoPorNumero(String procnumr){
		BeanProcesso processo = null;
		try {
			String sql = "SELECT * FROM VW_PROCESSO WHERE PROCNUMR = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, procnumr);
			
			processo = Utils.getObjectsStrFirst(st, BeanProcesso.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return processo;
	}

	public List<BeanProcesso> pesquisa(String procnumr){
		List<BeanProcesso> processos = new ArrayList<BeanProcesso>();
		try {
			String sql = "SELECT * FROM VW_PROCESSO WHERE (CAST(PROCNUMR AS VARCHAR(100)) = ? OR PROCNMCL LIKE '%"+procnumr.replaceAll("[ ]", "%")+"%' OR CAST(PROCCPF AS VARCHAR(100)) = ? OR CAST(PRONCODG2 AS VARCHAR(100)) = ?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, procnumr);
			st.setString(2, procnumr);
			st.setString(3, procnumr);
			
			processos.addAll(Utils.getObjectsStr(st, BeanProcesso.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return processos;
	}
	
	public void insert(BeanProcesso processo){
		try {
			String sql = "EXECUTE PROCEDURE " +
					     " SP_INSERE_PROCESSO(?, ?, ?, ?, ?," +
					     " ?, ?, ?, ?, ?, ?, ?, " +
					     " ?, ?, ?, ?, ?, ?," +
					     " ?, ?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, processo.getProcnmcl().toUpperCase());
			st.setString(2, ValidaObjeto.removeCharOfInteger(processo.getProccpf()));
			st.setDate(3, Utils.stringToDateSQL(processo.getProdnasc()));
			st.setString(4, processo.getProcsexo());
			st.setString(5, ValidaObjeto.removeCharOfInteger(processo.getProcfone()));
			st.setString(6, ValidaObjeto.removeCharOfInteger(processo.getProcfone2()));
			st.setInt(7, Integer.parseInt(processo.getProncgtl()));
			st.setString(8, processo.getProclogr());
			st.setInt(9, Integer.parseInt(processo.getProncgcd()));
			st.setString(10, ValidaObjeto.removeCharOfInteger(processo.getProccep()));
			st.setString(11, processo.getProcbair());
			st.setString(12, processo.getProcnumr());
			st.setInt(13, Integer.parseInt(processo.getProncgtac()));
			st.setInt(14, Integer.parseInt(processo.getProncgfap()));
			st.setString(15, processo.getProcloca());
			
			if (!ValidaObjeto.validaInteiro(processo.getProncons())){
				st.setNull(16, Types.INTEGER);
			}else{
				st.setInt(16, Integer.parseInt(processo.getProncons()));
			}
			
			st.setString(17, processo.getProcobs());
			
			if (!ValidaObjeto.validaInteiro(processo.getPronc2en())){
				st.setNull(18, Types.INTEGER);
			}else{
				st.setInt(18, Integer.parseInt(processo.getPronc2en()));
			}
			
			st.setInt(19, Integer.parseInt(processo.getProncadt()));
			st.setDate(20, Utils.stringToDateSQL(processo.getProdfase()));
			
			if (!ValidaObjeto.validaInteiro(processo.getPronsupe())){
				st.setNull(21, Types.INTEGER);
			}else{
				st.setInt(21, Integer.parseInt(processo.getPronsupe()));
			}
			
			st.setString(22, processo.getProcdcfap());
			
			st.executeUpdate();
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	public void update(BeanProcesso processo){
		try {
			String sql = "UPDATE PROCESSO SET" +
					" PRONC2EN = ?, PROCNUMR = ?, PRONCGTAC = ?," +
					" PROCLOCA = ?, PROCOBS = ?, PRODALTE = 'NOW', " +
					" PRONALTE = ?, PRONCONS = ?, PRONSUPE = ?" +
					" WHERE PRONCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			if (!ValidaObjeto.validaInteiro(processo.getPronc2en())){
				st.setNull(1, Types.INTEGER);
			}else{
				st.setInt(1, Integer.parseInt(processo.getPronc2en()));
			}

			st.setString(2, processo.getProcnumr());
			
			st.setInt(3, Integer.parseInt(processo.getProncgtac()));
			
			st.setString(4, processo.getProcloca());
			st.setString(5, processo.getProcobs());
			
			st.setInt(6, Integer.parseInt(processo.getPronalte()));
			
			if (!ValidaObjeto.validaInteiro(processo.getProncons())){
				st.setNull(7, Types.INTEGER);
			}else{
				st.setInt(7, Integer.parseInt(processo.getProncons()));
			}			
			
			if (!ValidaObjeto.validaInteiro(processo.getPronsupe())){
				st.setNull(8, Types.INTEGER);
			}else{
				st.setInt(8, Integer.parseInt(processo.getPronsupe()));
			}
			st.setInt(9, Integer.parseInt(processo.getProncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	

}
