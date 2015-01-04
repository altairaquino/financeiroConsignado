package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanLigacaoSqi;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ModelLigacaoSqi {

	public static ModelLigacaoSqi getInstance(){
		return new ModelLigacaoSqi();
	}
	
	public ArrayList<BeanLigacaoSqi> getLigacoesDoCorretor(int enncodg){
		ArrayList<BeanLigacaoSqi> fases = new ArrayList<BeanLigacaoSqi>();
		try {
			String sql = " SELECT * FROM VW_LIGACAOSQI WHERE LGSNCGCT = ? AND LGSLATIV = 'T'";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			
			fases.addAll(Utils.getObjectsStr(st, BeanLigacaoSqi.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return fases;
		
	}
	
	public ArrayList<BeanLigacaoSqi> getLigacoesParaEnvioEmail(String data){
		ArrayList<BeanLigacaoSqi> fases = new ArrayList<BeanLigacaoSqi>();
		try {
			String sql = " SELECT * FROM VW_LigacaoSqi" +
						 " WHERE LGSDDATA = ? " +
						 " AND LGSLMAIL = 'T'" +
						 " AND LGSLATIV = 'T'";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setDate(1, Utils.stringToDateSQL(data));
			
			fases.addAll(Utils.getObjectsStr(st, BeanLigacaoSqi.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fases;
		
	}
	
	public ArrayList<BeanLigacaoSqi> getUltimasLigacoesDoCorretor(int enncodg){
		ArrayList<BeanLigacaoSqi> fases = new ArrayList<BeanLigacaoSqi>();
		try {
			String sql = " SELECT FIRST 5 * FROM VW_LigacaoSqi WHERE LGSNCGCT = ? AND LGSLATIV = 'T' ORDER BY LGSDDATA DESC";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			
			fases.addAll(Utils.getObjectsStr(st, BeanLigacaoSqi.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return fases;
		
	}
	
	public BeanLigacaoSqi getLigacaoSqi(int lgsncodg){
		BeanLigacaoSqi ligacao = null;
		
		try {
			String sql = "SELECT * FROM VW_LigacaoSqi WHERE LGSNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, lgsncodg);
			
			List<BeanLigacaoSqi> l = Utils.getObjectsStr(st, BeanLigacaoSqi.class); 
			
			if (!l.isEmpty())
				ligacao = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return ligacao;
		
	}
	
	public void insert(BeanLigacaoSqi ligacaoSqi){
		try {
			String sql = " INSERT INTO LIGACAOSQI(LGSNCGEN, LGSNC2EN, LGSMOBSV, LGSDPROX, LGSLMAIL)" +
						 " VALUES(?, ?, ?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(ligacaoSqi.getLgsncgct()));
			st.setInt(2, Integer.parseInt(ligacaoSqi.getLgsncgus()));
			st.setString(3, ligacaoSqi.getLgsmobsv());
			
			if (ValidaObjeto.validaData(ligacaoSqi.getLgsdprox())){
				st.setDate(4, Utils.stringToDateSQL(ligacaoSqi.getLgsdprox()));				
			}else{
				st.setNull(4, Types.DATE);
			}
			
			st.setString(5, ligacaoSqi.getLgslmail());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(BeanLigacaoSqi ligacaoJuridico){
		try {
			String sql = " UPDATE LIGACAOSQI SET LGSNCGEN = ?, LGSNC2EN = ?, LGSMOBSV = ?" +
						 " WHERE LGSNCODG = (?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(ligacaoJuridico.getLgsncgct()));
			st.setInt(2, Integer.parseInt(ligacaoJuridico.getLgsncgus()));
			st.setString(3, ligacaoJuridico.getLgsmobsv());
			st.setInt(4, Integer.parseInt(ligacaoJuridico.getLgsncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cancel(int ligacaoJuridico){
		try {
			String sql = " UPDATE LIGACAOSQI SET LGSLATIV = 'F'" +
						 " WHERE LGSNCODG = (?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ligacaoJuridico);
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
