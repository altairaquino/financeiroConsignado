package com.grupoexata.juridico.dao;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.juridico.struts.bean.BeanLigacaoJuridico;

public class ModelLigacaoJuridico {

	public static ModelLigacaoJuridico getInstance(){
		return new ModelLigacaoJuridico();
	}
	
	public ArrayList<BeanLigacaoJuridico> getLigacoesDoContato(int enncodg){
		ArrayList<BeanLigacaoJuridico> fases = new ArrayList<BeanLigacaoJuridico>();
		try {
			String sql = " SELECT * FROM VW_LigacaoJuridico WHERE LGJNCGCT = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			
			fases.addAll(Utils.getObjectsStr(st, BeanLigacaoJuridico.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return fases;
		
	}
	
	public ArrayList<BeanLigacaoJuridico> getLigacoesParaEnvioEmail(String data){
		ArrayList<BeanLigacaoJuridico> fases = new ArrayList<BeanLigacaoJuridico>();
		try {
			String sql = " SELECT * FROM VW_LigacaoJuridico" +
						 " WHERE LGJDDATA = ? " +
						 " AND LGJLMAIL = 'T'" +
						 " AND LGJLATIV = 'T'";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setDate(1, Utils.stringToDateSQL(data));
			
			fases.addAll(Utils.getObjectsStr(st, BeanLigacaoJuridico.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fases;
		
	}
	
	public ArrayList<BeanLigacaoJuridico> getUltimasLigacoesDoContato(int enncodg){
		ArrayList<BeanLigacaoJuridico> fases = new ArrayList<BeanLigacaoJuridico>();
		try {
			String sql = " SELECT FIRST 5 * FROM VW_LigacaoJuridico WHERE LGJNCGCT = ? ORDER BY LGJDDATA DESC";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			
			fases.addAll(Utils.getObjectsStr(st, BeanLigacaoJuridico.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return fases;
		
	}
	
	public BeanLigacaoJuridico getLigacaoJuridico(int lgjncodg){
		BeanLigacaoJuridico ligacao = null;
		
		try {
			String sql = "SELECT * FROM VW_LigacaoJuridico WHERE LGJNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, lgjncodg);
			
			List<BeanLigacaoJuridico> l = Utils.getObjectsStr(st, BeanLigacaoJuridico.class); 
			
			if (!l.isEmpty())
				ligacao = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return ligacao;
		
	}
	
	public void insert(BeanLigacaoJuridico ligacaoJuridico){
		try {
			String sql = " INSERT INTO LIGACAOJURIDICO(LGJNCGEN, LGJNC2EN, LGJMOBSV, LGJDPROX, LGJLMAIL)" +
						 " VALUES(?, ?, ?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(ligacaoJuridico.getLgjncgct()));
			st.setInt(2, Integer.parseInt(ligacaoJuridico.getLgjncgus()));
			st.setString(3, ligacaoJuridico.getLgjmobsv());
			
			if (ValidaObjeto.validaData(ligacaoJuridico.getLgjdprox())){
				st.setDate(4, Utils.stringToDateSQL(ligacaoJuridico.getLgjdprox()));				
			}else{
				st.setNull(4, Types.DATE);
			}
			
			st.setString(5, ligacaoJuridico.getLgjlmail());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(BeanLigacaoJuridico ligacaoJuridico){
		try {
			String sql = " UPDATE LIGACAOJURIDICO SET LGJNCGEN = ?, LGJNC2EN = ?, LGJMOBSV = ?" +
						 " WHERE LGJNCODG = (?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(ligacaoJuridico.getLgjncgct()));
			st.setInt(2, Integer.parseInt(ligacaoJuridico.getLgjncgus()));
			st.setString(3, ligacaoJuridico.getLgjmobsv());
			st.setInt(4, Integer.parseInt(ligacaoJuridico.getLgjncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
