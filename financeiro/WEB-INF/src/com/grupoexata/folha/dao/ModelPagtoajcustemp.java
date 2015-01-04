package com.grupoexata.folha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanPagtoajcustemp;

public class ModelPagtoajcustemp {
	private static final String QR_PAGTOAJCUSTEMP_BY_ID ="SELECT * FROM VW_PAGTOAJCUSTEMP WHERE PCENCODG = ?";
	private static final String QR_PAGTOAJCUSTEMP_LIST ="SELECT * FROM VW_PAGTOAJCUSTEMP";
	private static final String QR_PAGTOAJCUSTEMP_LIST_BY_PCENCGPAC ="SELECT * FROM VW_PAGTOAJCUSTEMP WHERE PCENCGPAC = ?";
	private static final String QR_PAGTOAJCUSTEMP_SET ="UPDATE PAGTOAJCUSTEMP SET PCENCGEMP = ?, PCENCGTAC = ?, PCENCGPAC = ?, PCEYVALOR = ?, PCEDPAGTO = ?, PCEYACRES = ?, PCEYDESC = ? WHERE PCENCODG = ?";
	private static final String QR_PAGTOAJCUSTEMP_SET_LIST ="UPDATE PAGTOAJCUSTEMP SET PCEYACRES = ?, PCEYDESC = ?, PCELBLOQ = ?, PCEYVALOR = ? WHERE PCENCODG = ?";
	private static final String QR_PAGTOAJCUSTEMP_ADD ="INSERT INTO PAGTOAJCUSTEMP( PCENCGEMP, PCENCGTAC, PCENCGPAC, PCEYVALOR, PCEDPAGTO, PCEYACRES, PCEYDESC) VALUES( ?, ?, ?, ?, ?, ?, ?)";
	private static final String QR_PAGTOAJCUSTEMP_REMOVE ="DELETE FROM PAGTOAJCUSTEMP WHERE PCENCODG = ?";
	public Connection getConnection(){
		return Banco.getConnection();
	}
	public void closeConnection(){
		try{
			if(getConnection() != null && !getConnection().isClosed()){
				getConnection().close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public BeanPagtoajcustemp get(BeanPagtoajcustemp bean){
		BeanPagtoajcustemp obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_PAGTOAJCUSTEMP_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getPcencodg()));
			obj = Utils.getObjectsStrFirst(st, BeanPagtoajcustemp.class);
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
	
	public List<BeanPagtoajcustemp> list(){
		List<BeanPagtoajcustemp> list = new ArrayList<BeanPagtoajcustemp>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_PAGTOAJCUSTEMP_LIST);
			list = Utils.getObjectsStr(st, BeanPagtoajcustemp.class);
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
	
	public List<BeanPagtoajcustemp> list(String pcencgpac){
		List<BeanPagtoajcustemp> list = new ArrayList<BeanPagtoajcustemp>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_PAGTOAJCUSTEMP_LIST_BY_PCENCGPAC);
			st.setInt(1, Integer.parseInt(pcencgpac));
			list = Utils.getObjectsStr(st, BeanPagtoajcustemp.class);
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


	public void set(BeanPagtoajcustemp obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_PAGTOAJCUSTEMP_SET);
			
			st.setInt(1, Integer.parseInt(obj.getPcencgemp()));
			st.setInt(2, Integer.parseInt(obj.getPcencgtac()));
			if(obj.getPcencgpac() == null){
				st.setNull(3, Types.INTEGER);
			}else{
				st.setInt(3, Integer.parseInt(obj.getPcencgpac()));
			}
			st.setFloat(4, Float.parseFloat(Utils.converteFloatBR(obj.getPceyvalor())));			
			st.setDate(5, Utils.stringToDateSQL(obj.getPcedpagto()));
			st.setFloat(6, Float.parseFloat(Utils.converteFloatBR(obj.getPceyacres())));			
			st.setFloat(7, Float.parseFloat(Utils.converteFloatBR(obj.getPceydesc())));			
			st.setInt(8, Integer.parseInt(obj.getPcencodg()));
			
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
	
	
	public void set(List<BeanPagtoajcustemp> list){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_PAGTOAJCUSTEMP_SET_LIST);
			for (BeanPagtoajcustemp obj : list) {
				st.setFloat(1, Float.parseFloat(Utils.converteFloatBR(obj.getPceyacres())));			
				st.setFloat(2, Float.parseFloat(Utils.converteFloatBR(obj.getPceydesc())));			
				st.setString(3, obj.getPcelbloq());
				st.setFloat(4, Float.parseFloat(Utils.converteFloatBR(obj.getPceyvalor())));			
				st.setInt(5, Integer.parseInt(obj.getPcencodg()));
				st.executeUpdate();
			}
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

	public void add(BeanPagtoajcustemp obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_PAGTOAJCUSTEMP_ADD);
			st.setInt(1, Integer.parseInt(obj.getPcencgemp()));
			st.setInt(2, Integer.parseInt(obj.getPcencgtac()));
			if(obj.getPcencgpac() == null){
				st.setNull(3, Types.INTEGER);
			}else{
				st.setInt(3, Integer.parseInt(obj.getPcencgpac()));
			}
			st.setFloat(4, Float.parseFloat(Utils.converteFloatBR(obj.getPceyvalor())));			
			st.setDate(5, Utils.stringToDateSQL(obj.getPcedpagto()));
			st.setFloat(6, Float.parseFloat(Utils.converteFloatBR(obj.getPceyacres())));			
			st.setFloat(7, Float.parseFloat(Utils.converteFloatBR(obj.getPceydesc())));		
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

	public void remove(BeanPagtoajcustemp obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_PAGTOAJCUSTEMP_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getPcencodg()));
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
