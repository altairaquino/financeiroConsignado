package com.grupoexata.folha.dao;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.util.Model;
import com.grupoexata.folha.bean.BeanParametro;

public class ModelParametro implements Model<BeanParametro>{
	private static final String QR_PARAMETRO_BY_ID ="SELECT FIRST 1 PARNCODG, PARNFGTS, PARNFGAD, PARNMFAM, PARNCHMS, PARNCHSM, PARYSMIN, PARNFGRC, PARNFGRA, PARNPIS, PARYRBC, PARYDDEP, PARNMDEP, PARNAPOS, PARNDMIN, PARNPROB, PARNESPE, PARNSAT FROM PARAMETRO";
	private static final String QR_PARAMETRO_LIST ="SELECT PARNCODG, PARNFGTS, PARNFGAD, PARNMFAM, PARNCHMS, PARNCHSM, PARYSMIN, PARNFGRC, PARNFGRA, PARNPIS, PARYRBC, PARYDDEP, PARNMDEP, PARNAPOS, PARNDMIN, PARNPROB, PARNESPE, PARNSAT FROM PARAMETRO";
	private static final String QR_PARAMETRO_SET ="UPDATE PARAMETRO SET PARNFGTS = ? , PARNFGAD = ? , PARNMFAM = ? , PARNCHMS = ? , PARNCHSM = ? , PARYSMIN = ? , PARNFGRC = ? , PARNFGRA = ? , PARNPIS = ? , PARYRBC = ? , PARYDDEP = ? , PARNMDEP = ? , PARNAPOS = ? , PARNDMIN = ? , PARNPROB = ? , PARNESPE = ? , PARNSAT = ? WHERE PARNCODG = ?";
	private static final String QR_PARAMETRO_ADD ="INSERT INTO PARAMETRO(PARNFGTS , PARNFGAD , PARNMFAM , PARNCHMS , PARNCHSM , PARYSMIN , PARNFGRC , PARNFGRA , PARNPIS , PARYRBC , PARYDDEP , PARNMDEP , PARNAPOS , PARNDMIN , PARNPROB , PARNESPE , PARNSAT) VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	private static final String QR_PARAMETRO_REMOVE ="DELETE FROM PARAMETRO WHERE PARNCODG = ?";
	@Override
	public Connection getConnection(){
		return Banco.getConnection();
	}
	@Override
	public void closeConnection(){
		try{
			if(getConnection() != null && !getConnection().isClosed()){
				getConnection().close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public BeanParametro get(BeanParametro bean){
		BeanParametro obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_PARAMETRO_BY_ID);
			//st.setInt(1, Integer.parseInt(bean.getParncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanParametro.class);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(st != null && st.isClosed()){
					st.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return obj;
	}
	@Override
	public List<BeanParametro> list(){
		List<BeanParametro> list = new ArrayList<BeanParametro>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_PARAMETRO_LIST);
			list = Utils.getObjectsStr(st, BeanParametro.class);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(st != null && st.isClosed()){
					st.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public List<BeanParametro> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanParametro obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_PARAMETRO_SET);
			st.setFloat(1, Float.parseFloat(Utils.converteFloatBR(obj.getParnfgts())));
			st.setFloat(2, Float.parseFloat(Utils.converteFloatBR(obj.getParnfgad())));
			st.setInt(3, Integer.parseInt(obj.getParnmfam()));
			st.setInt(4, Integer.parseInt(obj.getParnchms()));
			st.setInt(5, Integer.parseInt(obj.getParnchsm()));
			st.setFloat(6, Float.parseFloat(Utils.converteFloatBR(obj.getParysmin())));
			st.setFloat(7, Float.parseFloat(Utils.converteFloatBR(obj.getParnfgrc())));
			st.setFloat(8, Float.parseFloat(Utils.converteFloatBR(obj.getParnfgra())));
			st.setFloat(9, Float.parseFloat(Utils.converteFloatBR(obj.getParnpis())));
			st.setFloat(10, Float.parseFloat(Utils.converteFloatBR(obj.getParyrbc())));
			st.setFloat(11, Float.parseFloat(Utils.converteFloatBR(obj.getParyddep())));
			st.setInt(12, Integer.parseInt(obj.getParnmdep()));
			st.setInt(13, Integer.parseInt(obj.getParnapos()));
			st.setInt(14, Integer.parseInt(obj.getParndmin()));
			st.setFloat(15, Float.parseFloat(Utils.converteFloatBR(obj.getParnprob())));
			st.setFloat(16, Float.parseFloat(Utils.converteFloatBR(obj.getParnespe())));
			st.setFloat(17, Float.parseFloat(Utils.converteFloatBR(obj.getParnsat())));
			st.setInt(18, Integer.parseInt(obj.getParncodg()));
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(st != null && st.isClosed()){
					st.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void add(BeanParametro obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_PARAMETRO_ADD);
			st.setObject(1, obj.getParnfgts());
			st.setObject(2, obj.getParnfgad());
			st.setInt(3, Integer.parseInt(obj.getParnmfam()));
			st.setInt(4, Integer.parseInt(obj.getParnchms()));
			st.setInt(5, Integer.parseInt(obj.getParnchsm()));
			st.setObject(6, obj.getParysmin());
			st.setObject(7, obj.getParnfgrc());
			st.setObject(8, obj.getParnfgra());
			st.setObject(9, obj.getParnpis());
			st.setObject(10, obj.getParyrbc());
			st.setObject(11, obj.getParyddep());
			st.setInt(12, Integer.parseInt(obj.getParnmdep()));
			st.setInt(13, Integer.parseInt(obj.getParnapos()));
			st.setInt(14, Integer.parseInt(obj.getParndmin()));
			st.setObject(15, obj.getParnprob());
			st.setObject(16, obj.getParnespe());
			st.setObject(17, obj.getParnsat());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(st != null && st.isClosed()){
					st.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	@Override
	public void remove(BeanParametro obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_PARAMETRO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getParncodg()));
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(st != null && st.isClosed()){
					st.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
