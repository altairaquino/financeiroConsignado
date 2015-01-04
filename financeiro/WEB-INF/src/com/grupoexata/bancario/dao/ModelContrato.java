package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanContrato;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ModelContrato {

	public static ModelContrato getInstance(){
		return new ModelContrato();
	}
	
	public ArrayList<BeanContrato> getContratosSemFisico(){
		ArrayList<BeanContrato> contratos = new ArrayList<BeanContrato>();
		try {
			String sql = " SELECT FIRST 50 * FROM VW_CONTRATO WHERE CTLFISI = 'F' ORDER BY CTDCADT DESC,CTCNMAN";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			contratos.addAll(Utils.getObjectsStr(st, BeanContrato.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return contratos;
		
	}
	
	public ArrayList<BeanContrato> getContratosSemFisicoDoAngariador(int enncodg){
		ArrayList<BeanContrato> contratos = new ArrayList<BeanContrato>();
		try {
			String sql = " SELECT * FROM VW_CONTRATO " +
						 " WHERE CTNCGEN = ? " +
					     " AND CTLFISI = 'F' " +
					     " AND CTLPGCM = 'T' " +
					     " ORDER BY CTDCADT";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			
			contratos.addAll(Utils.getObjectsStr(st, BeanContrato.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return contratos;
		
	}
	
	public ArrayList<BeanContrato> getContratosDoAngariador(int enncodg){
		ArrayList<BeanContrato> contratos = new ArrayList<BeanContrato>();
		try {
			String sql = " SELECT * FROM VW_CONTRATO WHERE CTNCGEN = ? AND CTNCGSC != 6 ORDER BY CTDCADT DESC";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			
			contratos.addAll(Utils.getObjectsStr(st, BeanContrato.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return contratos;
		
	}
	
	public BeanContrato getContrato(int ctncodg){
		BeanContrato contrato = null;
		try {
			String sql = "SELECT * FROM VW_CONTRATO WHERE CTNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ctncodg);
			
			List<BeanContrato> l = Utils.getObjectsStr(st, BeanContrato.class); 
			
			if (!l.isEmpty())
				contrato = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return contrato;
		
	}
	
	public BeanContrato getContratoPorNumero(String ctnnumr){
		BeanContrato contrato = null;
		try {
			String sql = "SELECT * FROM VW_CONTRATO WHERE CTNNUMR = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, ctnnumr);
			
			List<BeanContrato> l = Utils.getObjectsStr(st, BeanContrato.class); 
			
			if (!l.isEmpty())
				contrato = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return contrato;
		
	}
	
	public BeanContrato getContratoPorNumeroDoCorretor(String ctnnumr, int ctncgen){
		BeanContrato contrato = null;
		try {
			String sql = "SELECT * FROM VW_CONTRATO WHERE CTNNUMR = ? AND CTNCGEN = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, ctnnumr);
			st.setInt(2, ctncgen);
			
			List<BeanContrato> l = Utils.getObjectsStr(st, BeanContrato.class); 
			
			if (!l.isEmpty())
				contrato = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return contrato;
		
	}
	
	public ArrayList<BeanContrato> getContratoPorNumeroSemFisico(String ctnnumr){
		ArrayList<BeanContrato> contratos = new ArrayList<BeanContrato>();
		try {
			String sql = "SELECT * FROM VW_CONTRATO WHERE (CTNNUMR = '"+ctnnumr+"' OR CTCNMAN LIKE '%"+ctnnumr.replaceAll("[ ]", "%").toUpperCase()+"%') AND CTLFISI = 'F'";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			contratos.addAll(Utils.getObjectsStr(st, BeanContrato.class));			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return contratos;
		
	}
	
	public ArrayList<BeanContrato> getContratoPorCPF(String ctccpf){
		ArrayList<BeanContrato> contratos = new ArrayList<BeanContrato>();
		try {
			String sql = "SELECT * FROM VW_CONTRATO WHERE CTCCPF = ? ORDER BY CTDCADT DESC";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, ValidaObjeto.removeCharOfInteger(ctccpf));
			
			contratos.addAll(Utils.getObjectsStr(st, BeanContrato.class));			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return contratos;		
	}

	public void inserir(BeanContrato contrato) {
		try {
			String sql = "INSERT INTO CONTRATO(CTNNUMR,CTNC2EN,CTNCGEN,CTYVALR,CTNCGFP,CTNCGTP, CTNCADT, CTNCGBC, CTCAGEN)" +
					     "VALUES(?,?,?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, contrato.getCtnnumr());
			st.setInt(2, Integer.parseInt(contrato.getCtnc2en()));
			st.setInt(3, Integer.parseInt(contrato.getCtncgen()));
			st.setFloat(4, Float.parseFloat(Utils.converteFloatBR(contrato.getCtyvalr())));
			st.setInt(5, Integer.parseInt(contrato.getCtncgfp()));
			st.setInt(6, Integer.parseInt(contrato.getCtncgtp()));
			st.setInt(7, Integer.parseInt(contrato.getCtncadt()));
			
			if (ValidaObjeto.validaInteiro(contrato.getCtncgbc())){
				st.setInt(8, Integer.parseInt(contrato.getCtncgbc()));
			}else{
				st.setNull(8, Types.INTEGER);
			}
			
			if (ValidaObjeto.validaString(contrato.getCtcagen())){
				st.setString(9, contrato.getCtcagen());
			}else{
				st.setNull(9, Types.VARCHAR);
			}
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void inserirCompleto(BeanContrato contrato) {
		try {
			String sql = "EXECUTE PROCEDURE SP_INSERE_CONTRATO_COMPLETO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";					
   		
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, contrato.getEncnome().toUpperCase());
			st.setString(2, ValidaObjeto.removeCharOfInteger(contrato.getEncdocm()));
			st.setDate(3, Utils.stringToDateSQL(contrato.getEndnasc()));
			st.setString(4, contrato.getEncsexo());
			st.setString(5, contrato.getEncrg());
			st.setDate(6, Utils.stringToDateSQL(contrato.getEndexrg()));
			st.setString(7, contrato.getEncoerg().toUpperCase());
			st.setString(8, contrato.getEncufrg());
			st.setString(9, contrato.getEncdorg());
			st.setString(10, ValidaObjeto.removeCharOfInteger(contrato.getEncfone()));
			st.setInt(11, Integer.parseInt(contrato.getEnncgtl()));
			st.setString(12, contrato.getEnlendr().toUpperCase());
			st.setInt(13, Integer.parseInt(contrato.getEnncgcd()));
			st.setString(14, ValidaObjeto.removeCharOfInteger(contrato.getEnccep()));
			st.setString(15, contrato.getEncbair().toUpperCase());
			
			st.setString(16, contrato.getCtnnumr());
			st.setInt(17, Integer.parseInt(contrato.getCtncgen()));
			st.setInt(18, Integer.parseInt(contrato.getCtncadt()));
			st.setInt(19, Integer.parseInt(contrato.getCtncgfp()));
			st.setInt(20, Integer.parseInt(contrato.getCtncgtp()));
			st.setFloat(21, Float.parseFloat(Utils.converteFloatBR(contrato.getCtyvalr())));
			
			if (ValidaObjeto.validaInteiro(contrato.getCtncgbc())){
				st.setInt(22, Integer.parseInt(contrato.getCtncgbc()));
			}else{
				st.setNull(22, Types.INTEGER);
			}
			
			if (ValidaObjeto.validaString(contrato.getCtcagen())){
				st.setString(23, contrato.getCtcagen());
			}else{
				st.setNull(23, Types.VARCHAR);
			}
			
			st.setString(24, contrato.getEncmae().toUpperCase());
			st.setString(25, contrato.getEncpai().toUpperCase());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void baixaFisico(int ctncodg, int enncodg) {
		try {
			String sql = " UPDATE CONTRATO SET CTLFISI = 'T'," +
					     " CTNUSBX = ?," +
					     " CTDBXFI = 'NOW'" +
					     " WHERE CTNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			st.setInt(2, ctncodg);
			
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void cancela(int ctncodg) {
		try {
			String sql = " UPDATE CONTRATO SET " +
					" CTLATIV = 'F'," +
					" CTNCGSC = 9," +
					" CTDVERB = NULL" +
					" WHERE CTNCODG = ?" +
					" AND CTNCGSC != 6";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ctncodg);
			
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void confirmaSaqueOP(int ctncodg, int enncodg) {
		try {
			String sql = " UPDATE CONTRATO SET " +
					     " CTNSQOP = ?," +
					     " CTDSQOP = 'NOW',"+
					     " CTNCGSC = 10 " +
					     " WHERE CTNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			st.setInt(2, ctncodg);
			
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void registraAvervacao(BeanContrato contrato) {
		try {
			String sql = "EXECUTE PROCEDURE SP_REGISTRA_AVERBACAO_CONTRATO(?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(contrato.getCtncodg()));
			st.setDate(2, Utils.stringToDateSQL(contrato.getCtdverb()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void registraPagamento(BeanContrato contrato) {
		try {
			String sql = "EXECUTE PROCEDURE SP_REGISTRA_PGTO_CONTRATO(?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(contrato.getCtncodg()));
			st.setDate(2, Utils.stringToDateSQL(contrato.getCtdpgto()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void registraExtorno(BeanContrato contrato) {
		try {
			String sql = "EXECUTE PROCEDURE SP_REGISTRA_ESTORNO_CONTRATO(?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(contrato.getCtncodg()));
			st.setDate(2, Utils.stringToDateSQL(contrato.getCtdextn()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void finalizaContrato(BeanContrato contrato) {
		try {			
			String sql = "EXECUTE PROCEDURE SP_FINALIZA_CONTRATO(?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(contrato.getCtncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean temSpreadPositivo(int ctncodg){
		boolean ret = false;
		try {			
			String sql = "SELECT CTYSPRE FROM VW_CONTRATO WHERE CTNCODG = (?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ctncodg);
			
			ResultSet rs = st.executeQuery();
			
			rs.next();
			
			ret = rs.getFloat("CTYSPRE") >= 0.0f;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public void recalculaComissaoContrato(int ctncodg) {
		try {			
			String sql = "EXECUTE PROCEDURE SP_INSERE_COMISSAOCONTRATO(?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ctncodg);
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(BeanContrato contrato) {
		try {
			String sql = " UPDATE CONTRATO SET CTNNUMR = ?, CTYVALR = ?,CTNCGFP = ?,CTNCGTP = ?, " +
					     " CTNEXAT = ?, CTNCGEN = ?, CTNCGBC = ?, CTCAGEN = ?, CTNC2EN = ?, CTDALTE = ?, CTNALTE = ?" +
					     " WHERE CTNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, contrato.getCtnnumr());
			st.setFloat(2, Float.parseFloat(Utils.converteFloatBR(contrato.getCtyvalr())));
			st.setInt(3, Integer.parseInt(contrato.getCtncgfp()));
			st.setInt(4, Integer.parseInt(contrato.getCtncgtp()));
			st.setFloat(5, Float.parseFloat(Utils.converteFloatBR(contrato.getCtnexat())));
			st.setInt(6, Integer.parseInt(contrato.getCtncgen()));
			if (ValidaObjeto.validaInteiro(contrato.getCtncgbc())){
				st.setInt(7, Integer.parseInt(contrato.getCtncgbc()));
			}else{
				st.setNull(7, Types.INTEGER);
			}
			
			if (ValidaObjeto.validaString(contrato.getCtcagen())){
				st.setString(8, contrato.getCtcagen());
			}else{
				st.setNull(8, Types.VARCHAR);
			}
			
			st.setInt(9, Integer.parseInt(contrato.getCtnc2en()));
			st.setTimestamp(10, new java.sql.Timestamp(new Date().getTime()));
			st.setInt(11, Integer.parseInt(contrato.getCtnalte()));
			
			st.setInt(12, Integer.parseInt(contrato.getCtncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<BeanContrato> getContratosAuditoria(Date data) {
		ArrayList<BeanContrato> contratos = new ArrayList<BeanContrato>();
		try {
			String sql = " SELECT VW_CONTRATO.* FROM VW_CONTRATO " +
					     " INNER JOIN ANGARIADOR ON" +
					     "   CTNCGEN = ANNCGEN" +
					     " WHERE CAST(CTDCADT AS DATE) = ?" +
					     " AND NOT EXISTS (SELECT * FROM CONTRATOQUESTIONARIO WHERE CTNCODG = CQNNCGCT)" +
					     " AND EXISTS (SELECT * FROM CONTRATOAUDITORIA WHERE CTNCODG = CTANCGCT)" +
					     " ORDER BY ANNCGTN";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setDate(1, new java.sql.Date(data.getTime()));
			
			contratos.addAll(Utils.getObjectsStr(st, BeanContrato.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contratos;
	}
	
	public ArrayList<BeanContrato> getListaConfirmaOP() {
		ArrayList<BeanContrato> contratos = new ArrayList<BeanContrato>();
		try {
			String sql = " SELECT * FROM VW_CONTRATO " +
					     " WHERE CTDVERB IS NOT NULL " +
					     " AND CTNCGSC = 8" +
					     " AND NOT EXISTS("+
					     "       SELECT * FROM ANGARIADOR WHERE CTNCGEN = ANNCGEN AND ANNCGTN = 9)" +
					     " AND CTLATIV = 'T'" +
					     " ORDER BY CTNCGEN";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			contratos.addAll(Utils.getObjectsStr(st, BeanContrato.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contratos;
	}
	
	public ArrayList<BeanContrato> getListaConfirmaOP(String busca) {
		ArrayList<BeanContrato> contratos = new ArrayList<BeanContrato>();
		try {
			String sql = " SELECT * FROM VW_CONTRATO " +
					     " WHERE CTDVERB IS NOT NULL" +
					     " AND CTNCGSC = 8" +
					     " AND ()"+
					     " AND NOT EXISTS(SELECT * FROM ANGARIADOR WHERE CTNCGEN = ANNCGEN AND ANNCGTN = 9)" +
					     " ORDER BY CTNCGEN";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			contratos.addAll(Utils.getObjectsStr(st, BeanContrato.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contratos;
	}
	
	public boolean temProducaoSuspeita(int enncodg){
		boolean ret = false;
		try {			
			String sql = "SELECT SUSPEITO FROM VW_PRODUCAO_SUSPEITA WHERE COD_AGENTE = (?) AND SUSPEITO = 'T'";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			
			ResultSet rs = st.executeQuery();
			
			ret = rs.next();
			
			//ret = rs.getString("SUSPEITO").charAt(0) == 'T';
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
}
