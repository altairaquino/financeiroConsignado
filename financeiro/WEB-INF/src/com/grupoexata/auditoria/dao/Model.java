package com.grupoexata.auditoria.dao;

import java.sql.Connection;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;

public class Model {
	
	protected <B>B getById(Class<B> class0 ,String query, int id) {
		return Utils.getObjectStrById(class0,getConnection(),query, id);
	}
	
	protected <T>List<T> getListByFk(Class<T> class0 ,String query, int fk) {
		return Utils.getObjectsStrFk(class0,getConnection(),query, fk);
	}
	
	protected <T>List<T> getList(Class<T> class0 ,String query) {
		return Utils.getObjectsStr(class0, getConnection(),query);
	}
	
	public static Model getIntance(){
		return new Model();
	}
	public Connection getConnection(){
		return Banco.getConnection();
	}
}
