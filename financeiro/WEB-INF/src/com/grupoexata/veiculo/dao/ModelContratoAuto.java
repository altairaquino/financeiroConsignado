package com.grupoexata.veiculo.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.veiculo.struts.bean.BeanContratoAuto;

public class ModelContratoAuto {

	public static ModelContratoAuto getInstance(){
		return new ModelContratoAuto();
	}
	
	public BeanContratoAuto getContratoAuto(int ctancodg){
		BeanContratoAuto contrato = null;
		try {
			String sql = "SELECT * FROM VW_CONTRATOAUTO WHERE CTANCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ctancodg);
			
			List<BeanContratoAuto> l = Utils.getObjectsStr(st, BeanContratoAuto.class); 
			
			if (!l.isEmpty())
				contrato = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return contrato;
		
	}
	
	public BeanContratoAuto getContratoAutoPorVeiculo(int veincodg){
		BeanContratoAuto contrato = null;
		try {
			String sql = "SELECT * FROM VW_CONTRATOAUTO WHERE CTANVEIC = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, veincodg);
			
			List<BeanContratoAuto> l = Utils.getObjectsStr(st, BeanContratoAuto.class); 
			
			if (!l.isEmpty())
				contrato = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return contrato;
		
	}
	
	public ArrayList<BeanContratoAuto> getContratoAutoPorCPF(String ctacdocm){
		ArrayList<BeanContratoAuto> contratos = new ArrayList<BeanContratoAuto>();
		try {
			String sql = "SELECT * FROM VW_CONTRATOAUTO WHERE CTACDOCM = ? ORDER BY CTADBASE DESC";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, ValidaObjeto.removeCharOfInteger(ctacdocm));
			
			contratos.addAll(Utils.getObjectsStr(st, BeanContratoAuto.class));			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return contratos;		
	}
	
	
	public void inserir(BeanContratoAuto contratoAuto){
		try {
			String sql = " INSERT INTO CONTRATOAUTO(CTACNUMR,CTANCGEN,CTANCGEP,CTAYVALR,CTANPLAN,CTAYVLPC,CTANFPTO,CTADBASE,CTACOBSV, CTANC2EN, CTANC3EN)" +
					     " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, contratoAuto.getCtacnumr());
			st.setInt(2, Integer.parseInt(contratoAuto.getCtancgen()));
			st.setInt(3, Integer.parseInt(contratoAuto.getCtancgep()));
			st.setFloat(4, Float.parseFloat(Utils.converteFloatBR(contratoAuto.getCtayvalr())));
			st.setInt(5, Integer.parseInt(contratoAuto.getCtanplan()));
			st.setFloat(6, Float.parseFloat(Utils.converteFloatBR(contratoAuto.getCtayvlpc())));
			st.setInt(7, Integer.parseInt(contratoAuto.getCtanfpto()));
			st.setDate(8, Utils.stringToDateSQL(contratoAuto.getCtadbase()));
			st.setString(9, contratoAuto.getCtacobsv());
			st.setInt(10, Integer.parseInt(contratoAuto.getCtancgop()));
			st.setInt(11, Integer.parseInt(contratoAuto.getCtancglj()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(BeanContratoAuto contratoAuto){
		try {
			String sql = " UPDATE CONTRATOAUTO SET CTACNUMR = ?, CTANCGEN = ?, CTANCGEP = ?,CTAYVALR = ?," +
					" CTANPLAN = ?,CTAYVLPC = ?,CTANFPTO = ?,CTADBASE = ?,CTACOBSV = ?, CTANC2EN = ?, CTANC3EN = ?" +
					     " WHERE CTANCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, contratoAuto.getCtacnumr());
			st.setInt(2, Integer.parseInt(contratoAuto.getCtancgen()));
			st.setInt(3, Integer.parseInt(contratoAuto.getCtancgep()));
			st.setFloat(4, Float.parseFloat(Utils.converteFloatBR(contratoAuto.getCtayvalr())));
			st.setInt(5, Integer.parseInt(contratoAuto.getCtanplan()));
			st.setFloat(6, Float.parseFloat(Utils.converteFloatBR(contratoAuto.getCtayvlpc())));
			st.setInt(7, Integer.parseInt(contratoAuto.getCtanfpto()));
			st.setDate(8, Utils.stringToDateSQL(contratoAuto.getCtadbase()));
			st.setString(9, contratoAuto.getCtacobsv());
			st.setInt(10, Integer.parseInt(contratoAuto.getCtancgop()));
			st.setInt(11, Integer.parseInt(contratoAuto.getCtancglj()));
			st.setInt(12, Integer.parseInt(contratoAuto.getCtancodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateQuest(BeanContratoAuto contratoAuto){
		try {
			String sql = " UPDATE CONTRATOAUTO" +
					     " SET CTALCARN = ?,CTALATEN = ?, CTALVEIC = ?, " +
					     " CTALINDI = ?,CTACOBIN = ? " +
					     " WHERE CTANCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, contratoAuto.getCtalcarn());
			st.setString(2, contratoAuto.getCtalaten());
			st.setString(3, contratoAuto.getCtalveic());
			st.setString(4, contratoAuto.getCtalindi());
			st.setString(5, contratoAuto.getCtacobin());
			st.setInt(6, Integer.parseInt(contratoAuto.getCtancodg()));
			
			st.executeUpdate();
			
			this.updateDataCarne(contratoAuto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateDataCarne(BeanContratoAuto contratoAuto){
		try {
			String sql = " UPDATE CONTRATOAUTO" +
					     " SET CTADCARN = ADDDAY(CTADCARN,15) " +
					     " WHERE CTANCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(contratoAuto.getCtancodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<BeanContratoAuto> getContratosPendentes(int ctancgep) {
		ArrayList<BeanContratoAuto> contratos = new ArrayList<BeanContratoAuto>();
		try {
			String sql = "SELECT * FROM VW_CONTRATOAUTO WHERE CTANCGEP = ? AND (CTALPDCN = 'T' OR CTALPDPC = 'T') ORDER BY CTADBASE";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ctancgep);
			
			contratos.addAll(Utils.getObjectsStr(st, BeanContratoAuto.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contratos;
	}
}
