package com.grupoexata.folha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.folha.bean.BeanOcupacao;
import com.grupoexata.folha.bean.BeanOcupacaoBusca;
import com.grupoexata.folha.util.Model;

public class ModelOcupacao implements Model<BeanOcupacao>{
	private static final String QR_OCUPACAO_BY_ID ="SELECT OCPNCODG, OCPCCODG, OCPCTITULO, OCPNCGFAM, OCPCCGFAM FROM OCUPACAO WHERE OCPNCODG = ?";
	private static final String QR_OCUPACAO_BY_OCPCCODG ="SELECT OCPNCODG, OCPCCODG, OCPCTITULO, OCPNCGFAM, OCPCCGFAM FROM OCUPACAO WHERE OCPCCODG = ?";
	private static final String QR_OCUPACAO_BY_OCPCCGFAM ="SELECT OCPNCODG, OCPCCODG, OCPCTITULO, OCPNCGFAM, OCPCCGFAM FROM OCUPACAO WHERE OCPCCGFAM = ?";
	private static final String QR_OCUPACAO_LIST ="SELECT OCPNCODG, OCPCCODG, OCPCTITULO, OCPNCGFAM, OCPCCGFAM FROM OCUPACAO";
	private static final String QR_OCUPACAO_SET ="UPDATE OCUPACAO SET OCPCCODG = ? , OCPCTITULO = ? , OCPNCGFAM = ? , OCPCCGFAM = ? WHERE OCPNCODG = ?";
	private static final String QR_OCUPACAO_ADD ="INSERT INTO OCUPACAO(OCPCCODG , OCPCTITULO , OCPNCGFAM , OCPCCGFAM) VALUES (? , ? , ? , ?)";
	private static final String QR_OCUPACAO_REMOVE ="DELETE FROM OCUPACAO WHERE OCPNCODG = ?";
	private static final String QR_OCUPACAO_SEARCH_FAMILIA = "SELECT FAMILIA.FAMCTITULO AS TITULO, FAMILIA.FAMCCODG AS CODIGO, 'FAM�LIA' AS TIPO FROM FAMILIA WHERE LOWER( ( FAMILIA.FAMCTITULO) ) LIKE LOWER( (CAST(? AS VARCHAR(200)))) UNION ALL SELECT OCUPACAO.OCPCTITULO AS TITULO, OCUPACAO.OCPCCGFAM AS CODIGO, 'OCUPA��O ' AS TIPO FROM OCUPACAO WHERE LOWER( ( OCUPACAO.OCPCTITULO) ) LIKE LOWER( (CAST(? AS VARCHAR(200)))) UNION ALL SELECT SINONIMO.SINCTITULO AS TITULO, OCUPACAO.OCPCCGFAM AS CODIGO, 'SIN�NIMO ' AS TIPO FROM SINONIMO, OCUPACAO WHERE LOWER( ( SINONIMO.SINCTITULO) ) LIKE LOWER( (CAST(? AS VARCHAR(200)))) AND OCUPACAO.OCPNCODG = SINONIMO.SINNCGOCP";
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
	public BeanOcupacao get(BeanOcupacao bean){
		BeanOcupacao obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_OCUPACAO_BY_ID);
			st.setInt(1, Integer.parseInt(bean.getOcpncodg()));
			obj = Utils.getObjectsStrFirst(st, BeanOcupacao.class);
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
	public BeanOcupacao getOcpccodg(String ocpccodg){
		BeanOcupacao obj= null;
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_OCUPACAO_BY_OCPCCODG);
			st.setString(1, ocpccodg);
			obj = Utils.getObjectsStrFirst(st, BeanOcupacao.class);
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
	public List<BeanOcupacao> listByOcpccgfam(String ocpccgfam){
		List<BeanOcupacao> list = new ArrayList<BeanOcupacao>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_OCUPACAO_BY_OCPCCGFAM);
			st.setString(1, ocpccgfam);
			list = Utils.getObjectsStr(st, BeanOcupacao.class);
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
	public List<BeanOcupacao> list(){
		List<BeanOcupacao> list = new ArrayList<BeanOcupacao>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_OCUPACAO_LIST);
			list = Utils.getObjectsStr(st, BeanOcupacao.class);
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
	public List<BeanOcupacao> listSearch(String param){
		throw new NotImplementedException();
	}
	@Override
	public void set(BeanOcupacao obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_OCUPACAO_SET);
			st.setString(1, obj.getOcpccodg());
			st.setString(2, obj.getOcpctitulo());
			st.setInt(3, Integer.parseInt(obj.getOcpncgfam()));
			st.setString(4, obj.getOcpccgfam());
			st.setInt(5, Integer.parseInt(obj.getOcpncodg()));
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
	public void add(BeanOcupacao obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_OCUPACAO_ADD);
			st.setString(1, obj.getOcpccodg());
			st.setString(2, obj.getOcpctitulo());
			st.setInt(3, Integer.parseInt(obj.getOcpncgfam()));
			st.setString(4, obj.getOcpccgfam());
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
	public void remove(BeanOcupacao obj){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_OCUPACAO_REMOVE);
			st.setInt(1, Integer.parseInt(obj.getOcpncodg()));
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
	
	public List<BeanOcupacaoBusca> getListFamilia(String parte){
		List<BeanOcupacaoBusca> list = new ArrayList<BeanOcupacaoBusca>();
		PreparedStatement st = null;
		try {
			parte = " " + parte + " ";
			parte = parte.replaceAll(" +", "%");
			st = getConnection().prepareStatement(QR_OCUPACAO_SEARCH_FAMILIA);
			st.setString(1, parte);
			st.setString(2, parte);
			st.setString(3, parte);
			list = Utils.getObjectsStr(st, BeanOcupacaoBusca.class);
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
