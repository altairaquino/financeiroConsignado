package com.grupoexata.folha.util;

import java.sql.Connection;
import java.util.List;

public interface Model<T>{
	public Connection getConnection();
	public void closeConnection();
	public T get(T id);
	public List<T> list();
    public List<T> listSearch(String param);
	public void set(T obj);
	public void add(T obj);
	public void remove(T id);
}
