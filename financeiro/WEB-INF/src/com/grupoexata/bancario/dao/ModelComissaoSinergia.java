package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanComissaoSinergia;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ModelComissaoSinergia {

	public static ModelComissaoSinergia getInstance(){
		return new ModelComissaoSinergia();
	}
	
	public ArrayList<BeanComissaoSinergia> getComissaoPorCargo(int tfancodg){
		ArrayList<BeanComissaoSinergia> tabelas = new ArrayList<BeanComissaoSinergia>();
		try {
			String sql = " SELECT * FROM VW_COMISSAO_SINERGIA WHERE CMSNCGTFC = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tfancodg);
			
			tabelas.addAll(Utils.getObjectsStr(st, BeanComissaoSinergia.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tabelas;
		
	}
	
	
	public BeanComissaoSinergia getComissaoSinergia(int cmsncodg){
		BeanComissaoSinergia ComissaoSinergia = null;
		try {
			String sql = "SELECT * FROM VW_COMISSAO_SINERGIA WHERE CMSNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, cmsncodg);
			
			List<BeanComissaoSinergia> l = Utils.getObjectsStr(st, BeanComissaoSinergia.class); 
			
			if (!l.isEmpty())
				ComissaoSinergia = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return ComissaoSinergia;	
	}
	
	public void update(BeanComissaoSinergia beanComissaoSinergia){
		try {
			String sql = "UPDATE COMISSAO_SINERGIA SET CMSNPERC = ?, CMSNPC100 = ?, CMSNPC150 = ?, CMSNPC151 = ?  WHERE CMSNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			if (ValidaObjeto.validaFloat(Utils.converteFloatBR(beanComissaoSinergia.getCmsnperc()))){
				st.setFloat(1, Float.parseFloat(Utils.converteFloatBR(beanComissaoSinergia.getCmsnperc())));
			}else{
				st.setFloat(1, 0f);
			}
			if (ValidaObjeto.validaFloat(Utils.converteFloatBR(beanComissaoSinergia.getCmsnpc100()))){
				st.setFloat(2, Float.parseFloat(Utils.converteFloatBR(beanComissaoSinergia.getCmsnpc100())));
			}else{
				st.setFloat(2, 0f);
			}
			if (ValidaObjeto.validaFloat(Utils.converteFloatBR(beanComissaoSinergia.getCmsnpc150()))){
				st.setFloat(3, Float.parseFloat(Utils.converteFloatBR(beanComissaoSinergia.getCmsnpc150())));
			}else{
				st.setFloat(3, 0f);
			}
			if (ValidaObjeto.validaFloat(Utils.converteFloatBR(beanComissaoSinergia.getCmsnpc151()))){
				st.setFloat(4, Float.parseFloat(Utils.converteFloatBR(beanComissaoSinergia.getCmsnpc151())));
			}else{
				st.setFloat(4, 0f);
			}
			st.setInt(5, Integer.parseInt(beanComissaoSinergia.getCmsncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
