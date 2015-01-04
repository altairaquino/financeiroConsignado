package com.grupoexata.veiculo.dao;

import java.sql.PreparedStatement;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.veiculo.struts.bean.BeanVeiculo;

public class ModelVeiculo {

	public static ModelVeiculo getInstance(){
		return new ModelVeiculo();
	}
	
	public BeanVeiculo getVeiculo(int veincodg){
		BeanVeiculo marca = null;
		try {
			String sql = "SELECT * FROM VW_VEICULO WHERE VEINCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, veincodg);
			
			List<BeanVeiculo> l = Utils.getObjectsStr(st, BeanVeiculo.class);
			
			if (!l.isEmpty())
				marca = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return marca;
		
	}
	
	
	public void update(BeanVeiculo veiculo){
		try {
			String sql = " UPDATE VEICULO SET VEINMARC = ?, VEICMODE = ?, VEINANOF = ?, VEIYVALR = ?, VEICPLAC = ?, VEICCHAS = ?," +
					     " VEICRENA = ?, VEINCOMB = ?, VEICUFLI = ?" +
					     " WHERE VEINCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(veiculo.getVeinmarc()));
			st.setString(2, veiculo.getVeicmode().toUpperCase());
			st.setInt(3, Integer.parseInt(veiculo.getVeinanof()));
			st.setFloat(4, Float.parseFloat(Utils.converteFloatBR(veiculo.getVeiyvalr())));
			st.setString(5, veiculo.getVeicplac());
			st.setString(6, veiculo.getVeicchas().toUpperCase());
			st.setString(7, veiculo.getVeicrena());
			st.setInt(8, Integer.parseInt(veiculo.getVeincomb()));
			st.setString(9, veiculo.getVeicufli());
			st.setInt(10, Integer.parseInt(veiculo.getVeincodg()));		
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
