package com.grupoexata.veiculo.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.veiculo.struts.bean.BeanOcorrenciaParcelaContrato;

public class ModelOcorrenciaParcelaContrato {

	public static ModelOcorrenciaParcelaContrato getInstance(){
		return new ModelOcorrenciaParcelaContrato();
	}
	
	public ArrayList<BeanOcorrenciaParcelaContrato> getOcorrenciasDaParcelaContrato(int pccncodg){
		ArrayList<BeanOcorrenciaParcelaContrato> parcelas = new ArrayList<BeanOcorrenciaParcelaContrato>();
		try {
			String sql = " SELECT * FROM VW_OCORPARCELACONTRATO WHERE OCONPCCT = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, pccncodg);
			
			parcelas.addAll(Utils.getObjectsStr(st, BeanOcorrenciaParcelaContrato.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return parcelas;
		
	}
	
	public BeanOcorrenciaParcelaContrato getOcorrenciaParcelaContrato(int oconcodg){
		BeanOcorrenciaParcelaContrato parcela = null;
		try {
			String sql = "SELECT * FROM VW_OCORPARCELACONTRATO WHERE OCONCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, oconcodg);
			
			List<BeanOcorrenciaParcelaContrato> l = Utils.getObjectsStr(st, BeanOcorrenciaParcelaContrato.class); 
			
			if (!l.isEmpty())
				parcela = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return parcela;		
	}

	public void inserir(BeanOcorrenciaParcelaContrato beanOcorrenciaParcelaContrato) {
		try {
			String sql = " INSERT INTO OCORPARCELACONTRATO (OCONPCCT, OCOLCONT, OCONDIAS, OCOCOBSV) VALUES (?, ?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(beanOcorrenciaParcelaContrato.getOconpcct()));
			st.setString(2, beanOcorrenciaParcelaContrato.getOcolcont());
			st.setInt(3, Integer.parseInt(beanOcorrenciaParcelaContrato.getOcondias()));	
			st.setString(4, beanOcorrenciaParcelaContrato.getOcocobsv());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void update(BeanOcorrenciaParcelaContrato beanOcorrenciaParcelaContrato) {
		try {
			String sql = " UPDATE OCORPARCELACONTRATO SET OCONPCCT = ?, OCOLCONT = ?, OCODPRAZ = ?, OCOCOBSV = ? WHERE OCONCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(beanOcorrenciaParcelaContrato.getOconpcct()));
			st.setString(2, beanOcorrenciaParcelaContrato.getOcolcont());
			st.setDate(3, Utils.stringToDateSQL(beanOcorrenciaParcelaContrato.getOcodpraz()));			
			st.setString(4, beanOcorrenciaParcelaContrato.getOcocobsv());
			st.setInt(5, Integer.parseInt(beanOcorrenciaParcelaContrato.getOconcodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}
