package com.grupoexata.financeiro.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.financeiro.struts.bean.BeanCentroCusto;
import com.grupoexata.financeiro.struts.bean.BeanOrigemCentro;

public class ModelCentroCusto {
	
	private static final String QR_ADD_REMOVE_ECC = "EXECUTE PROCEDURE SP_ADD_REMOVE_ECC(?,?)";
	private static final String QR_ADD_REMOVE_CRM = "EXECUTE PROCEDURE SP_ADD_REMOVE_CRM(?,?)";
	
	public static ModelCentroCusto getInstance(){
		return new ModelCentroCusto();
	}
	
	public BeanCentroCusto getCentroCusto(int crncodg){
		BeanCentroCusto centro = null;
		try {
			
			String sql = "SELECT * FROM VW_CENTROCUSTO WHERE CRNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, crncodg);
			
			
			centro = Utils.getObjectsStrFirst(st, BeanCentroCusto.class);		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return centro;
	}
	
		
	public ArrayList<BeanCentroCusto> getCentroCustoPorOrigem(int orencodg){
		ArrayList<BeanCentroCusto> centros = new ArrayList<BeanCentroCusto>();
		try {
			
			String sql = "SELECT * FROM VW_CENTROCUSTO WHERE CRNCGORE = ? ORDER BY CRCDESC";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, orencodg);
			
			centros.addAll(Utils.getObjectsStr(st, BeanCentroCusto.class));	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return centros;
	}
	
	public ArrayList<BeanCentroCusto> getCentroCustoPorOrigemAtivos(int orencodg){
		ArrayList<BeanCentroCusto> centros = new ArrayList<BeanCentroCusto>();
		try {
			
			String sql = "SELECT * FROM VW_CENTROCUSTO WHERE CRNCGORE = ? AND CRLATIV = 'T' ORDER BY CRCDESC";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, orencodg);
			
			centros.addAll(Utils.getObjectsStr(st, BeanCentroCusto.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return centros;
	}
	
	public void ativaDesativa(BeanOrigemCentro beanOrigemCentro){
		try {
			String sql = " UPDATE ORIGEMCENTRO SET ORCLATIV = (IIF(ORCLATIV = 'F','T','F')) " +
					     " WHERE ORCNCGORE = ?" +
					     " AND ORCNCGCR = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(beanOrigemCentro.getOrcncgore()));
			st.setInt(2, Integer.parseInt(beanOrigemCentro.getOrcncgcr()));
			
			st.executeUpdate();						
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<BeanCentroCusto> getCentrosCusto(){
		ArrayList<BeanCentroCusto> centros = new ArrayList<BeanCentroCusto>();
		try {
			
			String sql = "SELECT * FROM VW_CENTROCUSTO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
						
			centros.addAll(Utils.getObjectsStr(st, BeanCentroCusto.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return centros;
	}		
	
	public ArrayList<BeanCentroCusto> getCentrosCustoAtivos(){
		ArrayList<BeanCentroCusto> centros = new ArrayList<BeanCentroCusto>();
		try {
			
			String sql = "SELECT * FROM CENTROCUSTO WHERE CRLATIV = 'T'";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			centros.addAll(Utils.getObjectsStr(st, BeanCentroCusto.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return centros;
	}
	
	public List<BeanCentroCusto> listCentroCustoDisp(String eccncgemp){
		List<BeanCentroCusto> centros = new ArrayList<BeanCentroCusto>();
		try {
			
			String sql = "SELECT CRNCODG, CRCDESC, IIF(ECCNCGEMP IS NULL,'F','T') CRLATIV FROM EMPREGADO_CENTROCUSTO RIGHT JOIN CENTROCUSTO ON ECCNCGCR = CRNCODG AND ECCNCGEMP = ? WHERE CRLATIV = 'T'";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(eccncgemp));						
			centros  = Utils.getObjectsStr(st, BeanCentroCusto.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return centros;
	}
	
	public List<BeanCentroCusto> listCentroCustoMovimento(String movncodg){
		List<BeanCentroCusto> centros = new ArrayList<BeanCentroCusto>();
		try {			
			String sql = "SELECT CRNCODG, CRCDESC, IIF(CRMNCGCR IS NULL,'F','T') CRLATIV FROM CENTROMOVCAIXA RIGHT JOIN CENTROCUSTO ON CRMNCGCR = CRNCODG AND CRMNMOVC = ? WHERE CRLATIV = 'T'";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(movncodg));
			
			centros  = Utils.getObjectsStr(st, BeanCentroCusto.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return centros;
	}
	
	public List<BeanCentroCusto> listCentroCustoEmp(String eccncgemp){
		List<BeanCentroCusto> centros = new ArrayList<BeanCentroCusto>();
		try {
			
			String sql = "SELECT CRNCODG, CRCDESC, CRLATIV FROM EMPREGADO_CENTROCUSTO LEFT JOIN CENTROCUSTO ON ECCNCGCR = CRNCODG WHERE CRLATIV = 'T' AND ECCNCGEMP = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(eccncgemp));						
			centros  = Utils.getObjectsStr(st, BeanCentroCusto.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return centros;
	}
	
	public void inserir(BeanCentroCusto centro){
		try {
			String sql = "INSERT INTO CENTROCUSTO (CRCDESC, CRLATIV) VALUES (?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, centro.getCrcdesc().toUpperCase());
			st.setString(2, centro.getCrlativ());
			
			st.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(BeanCentroCusto centro){
		try {
			String sql = " UPDATE CENTROCUSTO SET " +
					     " CRCDESC = ?, " +
					     " CRLATIV = ?" +
					     " WHERE CRNCODG = (?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, centro.getCrcdesc().toUpperCase());
			st.setString(2, centro.getCrlativ());
			st.setInt(3, Integer.parseInt(centro.getCrncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addRemoverEcc(int empncodg, int crncodg){
		PreparedStatement st = null;
		try {
			st = Banco.getConnection().prepareStatement(QR_ADD_REMOVE_ECC);
			st.setInt(1, empncodg);
			st.setInt(2, crncodg);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(st !=null && !st.isClosed()){
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void addRemoverCrm(int movncodg, int crncodg){
		PreparedStatement st = null;
		try {
			st = Banco.getConnection().prepareStatement(QR_ADD_REMOVE_CRM);
			st.setInt(1, movncodg);
			st.setInt(2, crncodg);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(st !=null && !st.isClosed()){
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

