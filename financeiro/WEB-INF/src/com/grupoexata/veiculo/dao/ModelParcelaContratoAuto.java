package com.grupoexata.veiculo.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.veiculo.struts.bean.BeanParcelaContratoAuto;

public class ModelParcelaContratoAuto {

	public static ModelParcelaContratoAuto getInstance(){
		return new ModelParcelaContratoAuto();
	}
	
	public ArrayList<BeanParcelaContratoAuto> getParcelasContratoAuto(int ctancodg){
		ArrayList<BeanParcelaContratoAuto> parcelas = new ArrayList<BeanParcelaContratoAuto>();
		try {
			String sql = " SELECT * FROM VW_PARCCONTRATOAUTO WHERE PCCNCTAU = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ctancodg);
			
			parcelas.addAll(Utils.getObjectsStr(st, BeanParcelaContratoAuto.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return parcelas;
		
	}
	
	public BeanParcelaContratoAuto getParcelaContratoAuto(int pccncodg){
		BeanParcelaContratoAuto parcela = null;
		try {
			String sql = "SELECT * FROM VW_PARCCONTRATOAUTO WHERE PCCNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, pccncodg);
			
			List<BeanParcelaContratoAuto> l = Utils.getObjectsStr(st, BeanParcelaContratoAuto.class); 
			
			if (!l.isEmpty())
				parcela = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return parcela;		
	}

	public void baixar(BeanParcelaContratoAuto beanParcelaContratoAuto) {
		try {
			String sql = " UPDATE PARCCONTRATOAUTO SET PCCDPGTO = ?, PCCNCGEN = ?" +
					     " WHERE PCCNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setDate(1, Utils.stringToDateSQL(beanParcelaContratoAuto.getPccdpgto()));
			st.setInt(2, Integer.parseInt(beanParcelaContratoAuto.getPccncgen()));
			st.setInt(3, Integer.parseInt(beanParcelaContratoAuto.getPccncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}
