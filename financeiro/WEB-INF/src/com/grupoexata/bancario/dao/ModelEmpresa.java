package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanEmpresa;
import com.grupoexata.bancario.utils.Utils;

public class ModelEmpresa {
	
	public static ModelEmpresa getInstance(){
		return new ModelEmpresa();
	}
	
	public BeanEmpresa getEmpresa(){
		
		BeanEmpresa empresa = null;
		try {
			String sql = "SELECT * FROM VW_EMPRESA WHERE EPLMATR = 'T'";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			List<BeanEmpresa> l = Utils.getObjectsStr(st, BeanEmpresa.class);
			
			if (!l.isEmpty()){
				empresa = l.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empresa;		
	}
	
	public ArrayList<BeanEmpresa> getEmpresasFiliais(){
		
		ArrayList<BeanEmpresa> empresas = new ArrayList<BeanEmpresa>();
		try {
			String sql = "SELECT * FROM VW_EMPRESA WHERE EPLMATR = 'F'";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			empresas.addAll(Utils.getObjectsStr(st, BeanEmpresa.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empresas;		
	}
	
	public ArrayList<BeanEmpresa> getEmpresas(){
		
		ArrayList<BeanEmpresa> empresas = new ArrayList<BeanEmpresa>();
		try {
			String sql = "SELECT * FROM VW_EMPRESA";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			empresas.addAll(Utils.getObjectsStr(st, BeanEmpresa.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empresas;		
	}

	public List<BeanEmpresa> list(){
		
		List<BeanEmpresa> list = null;
		try {
			String sql = "SELECT * FROM VW_EMPRESA";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			list = Utils.getObjectsStr(st, BeanEmpresa.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;		
	}

}
