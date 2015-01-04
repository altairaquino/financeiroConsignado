package com.grupoexata.folha.dao;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.folha.bean.BeanEmpregado;

public class ModelEmpregado{
	
	private static ModelEmpregado model;
	
	private static final String QR_EMPREGADO_LIST ="SELECT * FROM VW_EMPREGADO";
	private static final String QR_EMPREGADO_REMOVE ="DELETE FROM VW_EMPREGADO WHERE EMPNCODG = ?";
	
	public static ModelEmpregado getInstance(){
		if (model == null){
			model = new ModelEmpregado();
		}
		return model;
	}
	
	public BeanEmpregado getEntidadeEmpregado(String empncodg){
		BeanEmpregado obj= null;
		PreparedStatement st = null;
		try {
			String sql = " SELECT VW_ENTIDADE.* FROM VW_ENTIDADE" +
					     " LEFT JOIN EMPREGADO ON " +
					     "   ENNCODG = EMPNCGCEN " +
					     " WHERE ENNCODG = ? " +
					     " AND ENNCGTE = 3 " +
					     " AND ENLATIV = 'T'";
			
			st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(empncodg));
			
			obj = Utils.getObjectsStrFirst(st, BeanEmpregado.class);
			
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
	
	public BeanEmpregado getBeanEmpregadoPorEnt(int enncodg){
		BeanEmpregado empreg = null;
		try {
			String sql = " SELECT * FROM VW_EMPREGADO WHERE EMPNCGEN = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			List<BeanEmpregado> l = Utils.getObjectsStr(st, BeanEmpregado.class);
			
			if (!l.isEmpty()){
				empreg = l.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empreg;
	}
	
	public List<BeanEmpregado> listaEntidadeEmpregado(String param){
		List<BeanEmpregado> list = new ArrayList<BeanEmpregado>();
		PreparedStatement st = null;
		try {
			
			String sql = " SELECT * FROM VW_EMPREGADO" +
					     " WHERE (EMPCNMEN like UPPER('%"+param+"%') OR EMPCCPF = ?)";
			
			st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, param);
			
			list = Utils.getObjectsStr(st, BeanEmpregado.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public List<BeanEmpregado> listaEntidadeEmpregado(){
		List<BeanEmpregado> list = new ArrayList<BeanEmpregado>();
		PreparedStatement st = null;
		try {
			
			String sql = " SELECT VW_EMPREGADO.* FROM VW_ENTIDADE " +
					     " LEFT JOIN VW_EMPREGADO ON " +
					     "   ENNCODG = EMPNCGEN " +
					     " WHERE ENNCGTE = 3 " +
					     " AND ENLATIV = 'T' " +
					     " ORDER BY ENCNOME";
			
			st = Banco.getConnection().prepareStatement(sql);
			list.addAll(Utils.getObjectsStr(st, BeanEmpregado.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<BeanEmpregado> listaEntidadeEmpregadoAniversarioDia(){
		List<BeanEmpregado> list = new ArrayList<BeanEmpregado>();
		PreparedStatement st = null;
		try {
			String sql = " SELECT VW_EMPREGADO.* FROM VW_EMPREGADO" +
					     " INNER JOIN ENTIDADE ON" +
					     "   EMPNCGEN = ENNCODG" +
					     " WHERE EMPLATIV = 'T'" +
					     " AND EMPDDEMS IS NULL" +
					     " AND EXTRACT(MONTH FROM ENDNASC) = EXTRACT(MONTH FROM CAST('NOW' AS DATE)) " +
					     " AND EXTRACT(DAY FROM ENDNASC) BETWEEN EXTRACT(DAY FROM ADDDAY('NOW',-2)) AND EXTRACT(DAY FROM ADDDAY('NOW',2))";
			
			st = Banco.getConnection().prepareStatement(sql);
			
			list.addAll(Utils.getObjectsStr(st, BeanEmpregado.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<BeanEmpregado> listaEntidadeEmpregadoAtivo(){
		List<BeanEmpregado> list = new ArrayList<BeanEmpregado>();
		PreparedStatement st = null;
		try {
			String sql = " SELECT * FROM VW_EMPREGADO "+
					     " AND EMPDDEMS IS NULL" +
					     " ORDER BY ENCNOME";
			
			st = Banco.getConnection().prepareStatement(sql);
			list.addAll(Utils.getObjectsStr(st, BeanEmpregado.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public BeanEntidade listEntidadeEmpregado(){
		BeanEntidade obj= null;
		PreparedStatement st = null;
		try {
			String sql = " SELECT VW_ENTIDADE.* FROM VW_ENTIDADE " +
					     " LEFT JOIN EMPREGADO ON " +
					     "   ENNCODG = EMPNCGEN " +
					     " WHERE ENNCODG = ? AND ENNCGTE = 3 AND ENLATIV = 'T'";
			
			st = Banco.getConnection().prepareStatement(sql);
			obj = Utils.getObjectsStrFirst(st, BeanEntidade.class);
			
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
	public BeanEmpregado getBeanEmpregado(int empncodg){
		BeanEmpregado empreg = null;
		PreparedStatement st = null;
		try {
			String sql = "SELECT * FROM VW_EMPREGADO WHERE EMPNCODG = ?";
			
			st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, empncodg);
			
			empreg = Utils.getObjectsStrFirst(st, BeanEmpregado.class);
			
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
		return empreg;
	}
	public List<BeanEmpregado> list(){
		
		List<BeanEmpregado> list = new ArrayList<BeanEmpregado>();
		PreparedStatement st = null;
		try {
			st = Banco.getConnection().prepareStatement(QR_EMPREGADO_LIST);
			list = Utils.getObjectsStr(st, BeanEmpregado.class);
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
	
	public void set(BeanEmpregado empreg){
		PreparedStatement st = null;
		try {
			String sql = " UPDATE EMPREGADO SET " +
					     " EMPCCARG = ?, EMPCFGTS = ?," +
					     " EMPCMATR = ?, EMPCNCAT = ?, EMPCORG = ?, EMPCPIS = ?, " +
					     " EMPCRG = ?, EMPCSCAT = ?, EMPCTITL = ?, EMPCUFCT = ?, " +
					     " EMPCUFRG = ?, EMPDADMS = ?, EMPDFGTS = ?, EMPDTERM = ?, " +
					     " EMPLNIR = ?, EMPNCGCTE = ?, EMPNCGGIN = ?, EMPNCGOCP = ?," +
					     " EMPNCGSTE = ?, EMPNCGTAD = ?, EMPNCGVIN = ?, EMPNHRSM = ?, " +
					     " EMPNMA13 = ?, EMPYBASE = ?, EMPYCPSG = ?, EMPLATIV = ?, " +
					     " EMPLSREG = ?, EMPDDEMS = ?, EMPDINAT = ?, EMPNCGSET = ?, " +
					     " EMPLVALE = ?, EMPLCOMS = ?, EMPYAJUD = ?, EMPNCGFL = ?"+
					     " WHERE EMPNCODG = ?";
			
			st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, empreg.getEmpccarg());
			st.setString(2, empreg.getEmpcfgts());
			st.setString(3, empreg.getEmpcmatr());
			st.setString(4, empreg.getEmpcncat());
			st.setString(5, empreg.getEmpcorg());
			st.setString(6, empreg.getEmpcpis());
			st.setString(7, empreg.getEmpcrg());
			st.setString(8, empreg.getEmpcscat());
			st.setString(9, empreg.getEmpctitl());
			st.setString(10, empreg.getEmpcufct());
			st.setString(11, empreg.getEmpcufrg());
			
			if(ValidaObjeto.validaData(empreg.getEmpdadms())){
				st.setDate(12, Utils.stringToDateSQL(empreg.getEmpdadms()));
			}else{
				st.setNull(12, Types.DATE);
			}
			
			if(ValidaObjeto.validaData(empreg.getEmpdfgts())){
				st.setDate(13, Utils.stringToDateSQL(empreg.getEmpdfgts()));
			}else{
				st.setNull(13, Types.DATE);
			}
			
			if(ValidaObjeto.validaData(empreg.getEmpdterm())){
				st.setDate(14, Utils.stringToDateSQL(empreg.getEmpdterm()));
			}else{
				st.setNull(14, Types.DATE);
			}
			
			st.setString(15, empreg.getEmplnir());
			st.setInt(16, Integer.parseInt(empreg.getEmpncgcte()));
			st.setInt(17, Integer.parseInt(empreg.getEmpncggin()));
			st.setInt(18, Integer.parseInt(empreg.getEmpncgocp()));
			st.setInt(19, Integer.parseInt(empreg.getEmpncgste()));
			st.setInt(20, Integer.parseInt(empreg.getEmpncgtad()));
			st.setInt(21, Integer.parseInt(empreg.getEmpncgvin()));
			st.setFloat(22, Float.parseFloat(Utils.converteFloatBR(empreg.getEmpnhrsm())));
			
			if(ValidaObjeto.validaInteiro(empreg.getEmpnma13())){
				st.setInt(23, Integer.parseInt(empreg.getEmpnma13()));
			}else{
				st.setNull(23, Types.INTEGER);
			}
			
			st.setFloat(24, Float.parseFloat(Utils.converteFloatBR(empreg.getEmpybase())));
			st.setFloat(25, Float.parseFloat(Utils.converteFloatBR(empreg.getEmpycpsg())));
			st.setString(26, empreg.getEmplativ());
			st.setString(27, empreg.getEmplsreg());
			
			if(ValidaObjeto.validaData(empreg.getEmpddems())){
				st.setDate(28, Utils.stringToDateSQL(empreg.getEmpddems()));
			}else{
				st.setNull(28, Types.DATE);
			}
			
			if(ValidaObjeto.validaData(empreg.getEmpdinat())){
				st.setDate(29, Utils.stringToDateSQL(empreg.getEmpdinat()));
			}else{
				st.setNull(29, Types.DATE);
			}
			
			st.setInt(30, Integer.parseInt(empreg.getEmpncgset()));
			
			st.setString(31, empreg.getEmplvale());
			st.setString(32, empreg.getEmplcoms());
			
			if (ValidaObjeto.validaFloat(Utils.converteFloatBR(empreg.getEmpyajud()))){
				st.setFloat(33, Float.parseFloat(Utils.converteFloatBR(empreg.getEmpyajud())));				
			}else{
				st.setNull(33, Types.FLOAT);
			}	

			st.setInt(34, Integer.parseInt(empreg.getEmpncgfl()));
			
			st.setInt(35, Integer.parseInt(empreg.getEmpncodg()));

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
	
	public void add(BeanEmpregado empreg){
		PreparedStatement st = null;
		try {
			String sql = " INSERT INTO EMPREGADO(EMPCCARG, EMPCFGTS, EMPCMATR, EMPCNCAT," +
					                            "EMPCORG, EMPCPIS, EMPCRG, EMPCSCAT, EMPCTITL " +
					                            ",EMPCUFCT, EMPCUFRG, EMPDADMS, EMPDFGTS, EMPDTERM," +
					                            "EMPLNIR, EMPNCGCTE, EMPNCGGIN, EMPNCGOCP, EMPNCGSTE," +
					                            "EMPNCGTAD, EMPNCGVIN, EMPNHRSM, EMPNMA13, EMPYBASE," +
					                            "EMPYCPSG, EMPNCGCEN, EMPLATIV, EMPLSREG, EMPDDEMS," +
					                            "EMPDINAT, EMPNCGSET, EMPLVALE, EMPLCOMS, EMPYAJUD,EMPNCGFL) " +
					                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
					                                     ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? " +
					                                     ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, empreg.getEmpccarg());
			st.setString(2, empreg.getEmpcfgts());
			st.setString(3, empreg.getEmpcmatr());
			st.setString(4, empreg.getEmpcncat());
			st.setString(5, empreg.getEmpcorg());
			st.setString(6, empreg.getEmpcpis());
			st.setString(7, empreg.getEmpcrg());
			st.setString(8, empreg.getEmpcscat());
			st.setString(9, empreg.getEmpctitl());
			st.setString(10, empreg.getEmpcufct());
			st.setString(11, empreg.getEmpcufrg());
			
			if(ValidaObjeto.validaData(empreg.getEmpdadms())){
				st.setDate(12, Utils.stringToDateSQL(empreg.getEmpdadms()));
			}else{
				st.setNull(12, Types.DATE);
			}
			
			if(ValidaObjeto.validaData(empreg.getEmpdfgts())){
				st.setDate(13, Utils.stringToDateSQL(empreg.getEmpdfgts()));
			}else{
				st.setNull(13, Types.DATE);
			}
			
			if(ValidaObjeto.validaData(empreg.getEmpdterm())){
				st.setDate(14, Utils.stringToDateSQL(empreg.getEmpdterm()));
			}else{
				st.setNull(14, Types.DATE);
			}
			
			st.setString(15, empreg.getEmplnir());
			st.setInt(16, Integer.parseInt(empreg.getEmpncgcte()));
			st.setInt(17, Integer.parseInt(empreg.getEmpncggin()));
			st.setInt(18, Integer.parseInt(empreg.getEmpncgocp()));
			st.setInt(19, Integer.parseInt(empreg.getEmpncgste()));
			st.setInt(20, Integer.parseInt(empreg.getEmpncgtad()));
			st.setInt(21, Integer.parseInt(empreg.getEmpncgvin()));
			st.setFloat(22, Float.parseFloat(Utils.converteFloatBR(empreg.getEmpnhrsm())));
			
			if(ValidaObjeto.validaInteiro(empreg.getEmpnma13())){
				st.setInt(23, Integer.parseInt(empreg.getEmpnma13()));
			}else{
				st.setNull(23, Types.INTEGER);
			}
			
			st.setFloat(24, Float.parseFloat(Utils.converteFloatBR(empreg.getEmpybase())));
			st.setFloat(25, Float.parseFloat(Utils.converteFloatBR(empreg.getEmpycpsg())));
			st.setInt(26, Integer.parseInt(empreg.getEmpncgen()));
			st.setString(27, empreg.getEmplativ());
			st.setString(28, empreg.getEmplsreg());
			
			if(ValidaObjeto.validaData(empreg.getEmpddems())){
				st.setDate(29, Utils.stringToDateSQL(empreg.getEmpddems()));
			}else{
				st.setNull(29, Types.DATE);
			}	
			
			if(ValidaObjeto.validaData(empreg.getEmpdinat())){
				st.setDate(30, Utils.stringToDateSQL(empreg.getEmpdinat()));
			}else{
				st.setNull(30, Types.DATE);
			}
			
			st.setInt(31, Integer.parseInt(empreg.getEmpncgset()));
			
			st.setString(32, empreg.getEmplvale());
			st.setString(33, empreg.getEmplcoms());
			
			if (ValidaObjeto.validaFloat(Utils.converteFloatBR(empreg.getEmpyajud()))){
				st.setFloat(34, Float.parseFloat(Utils.converteFloatBR(empreg.getEmpyajud())));				
			}else{
				st.setNull(34, Types.FLOAT);
			}	
			
			st.setInt(35, Integer.parseInt(empreg.getEmpncgfl()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(st != null && !st.isClosed()){
					st.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void remove(BeanEmpregado obj){
		PreparedStatement st = null;
		try {
			st = Banco.getConnection().prepareStatement(QR_EMPREGADO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getEmpncodg()));
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
	
	public List<BeanEmpregado> listSearch(String param){
		List<BeanEmpregado> list = new ArrayList<BeanEmpregado>();
		PreparedStatement st = null;
		try {
			
			String sql = " SELECT * FROM VW_EMPREGADO " +
					     " WHERE EMPCCPF = ? OR EMPCNMEN LIKE UPPER('%"+param+"%') ";
					     
			
			st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, param);
			
			list = Utils.getObjectsStr(st, BeanEmpregado.class);
			
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
}
