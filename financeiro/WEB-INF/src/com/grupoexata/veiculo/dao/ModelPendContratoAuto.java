package com.grupoexata.veiculo.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.veiculo.struts.bean.BeanPendContratoAuto;

public class ModelPendContratoAuto {

	public static ModelPendContratoAuto getInstance(){
		return new ModelPendContratoAuto();
	}
	
	public ArrayList<BeanPendContratoAuto> getPendenciasContratoAuto(int ctancodg){
		ArrayList<BeanPendContratoAuto> pgtos = new ArrayList<BeanPendContratoAuto>();
		try {
			String sql = " SELECT * FROM VW_PENDCONTRATOAUTO WHERE PDCNCTAU = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ctancodg);
			
			pgtos.addAll(Utils.getObjectsStr(st, BeanPendContratoAuto.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return pgtos;
		
	}
	
	public BeanPendContratoAuto getPendContratoAuto(int pgtncodg){
		BeanPendContratoAuto pgto = null;
		try {
			String sql = "SELECT * FROM VW_PENDCONTRATOAUTO WHERE PDCNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, pgtncodg);
			
			List<BeanPendContratoAuto> l = Utils.getObjectsStr(st, BeanPendContratoAuto.class); 
			
			if (!l.isEmpty())
				pgto = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return pgto;
		
	}
	
	public void baixar(BeanPendContratoAuto beanPendContratoAuto){
		try {
			String sql = " UPDATE PENDCONTRATOAUTO SET PDCDBAIX = 'NOW', PDCNCGEN = ?" +
					     " WHERE PDCNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(beanPendContratoAuto.getPdcncgen()));
			st.setInt(2, Integer.parseInt(beanPendContratoAuto.getPdcncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
