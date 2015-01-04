package com.grupoexata.financeiro.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.FormataObj;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.financeiro.struts.bean.BeanMovCaixa;

public class ModelMovCaixa {
	
	public static ModelMovCaixa getInstance(){
		return new ModelMovCaixa();
	}
	
	public BeanMovCaixa getMovCaixa(int movncodg){
		BeanMovCaixa mov = null;
		try {
			
			String sql = "SELECT * FROM VW_MOVIMENTO WHERE MOVNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, movncodg);
			
			List<BeanMovCaixa> l = Utils.getObjectsStr(st, BeanMovCaixa.class);
			
			if (!l.isEmpty()){
				mov = l.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mov;
	}
	
	public BeanMovCaixa getMovCaixa(BeanMovCaixa beanMovCaixa){
		BeanMovCaixa mov = null;
		try {
			
			String sql = " SELECT FIRST 1 * FROM VW_MOVIMENTO WHERE MOVNCGCOG = ? AND MOVDVENC = ? AND MOVNCADT = ?" +
					     " ORDER BY MOVNCODG DESC";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(beanMovCaixa.getMovncgcog()));
			st.setDate(2, Utils.stringToDateSQL(beanMovCaixa.getMovdvenc()));
			st.setInt(3, Integer.parseInt(beanMovCaixa.getMovncadt()));
			
			List<BeanMovCaixa> l = Utils.getObjectsStr(st, BeanMovCaixa.class);
			
			if (!l.isEmpty()){
				mov = l.get(0);
			}				
			
			st.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mov;
	}
		

	public ArrayList<BeanMovCaixa> getMovimentosPorTipo(String tipo){
		ArrayList<BeanMovCaixa> movimentos = new ArrayList<BeanMovCaixa>();
		try {
			
			String sql = " SELECT * FROM VW_MOVIMENTO " +
					     " WHERE MOVLTIPO = ? AND MOVLATIV = 'T'";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, tipo);
			
			movimentos.addAll(Utils.getObjectsStr(st, BeanMovCaixa.class));	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movimentos;
	}
	
	public ArrayList<BeanMovCaixa> listaPagamentosFuturos(){
		ArrayList<BeanMovCaixa> movimentos = new ArrayList<BeanMovCaixa>();
		try {
			
			String sql = "SELECT * FROM VW_MOVIMENTO " +
					     " WHERE (MOVDVENC BETWEEN ADDMONTH('NOW',-1) AND ADDMONTH('NOW',1)) " +
					     " AND MOVLTIPO = 'F'" +
					     " AND MOVLATIV = 'T'" +
					     " ORDER BY MOVDVENC";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			movimentos.addAll(Utils.getObjectsStr(st, BeanMovCaixa.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movimentos;
	}
	
	public ArrayList<BeanMovCaixa> listaPagamentosFuturosDia(String date){
		ArrayList<BeanMovCaixa> movimentos = new ArrayList<BeanMovCaixa>();
		try {
			
			String sql = "SELECT * FROM VW_MOVIMENTO " +
			" WHERE MOVDVENC = ? " +
			" AND MOVLTIPO = 'F'" +
			" AND MOVLATIV = 'T'" +
			" ORDER BY MOVDVENC";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setDate(1, Utils.stringToDateSQL(date));
			
			movimentos.addAll(Utils.getObjectsStr(st, BeanMovCaixa.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movimentos;
	}

	public ArrayList<BeanMovCaixa> getMovimentosDoFornecedor(int enncodg){
		ArrayList<BeanMovCaixa> movimentos = new ArrayList<BeanMovCaixa>();
		try {
			
			String sql = "SELECT * FROM VW_MOVIMENTO " +
						" WHERE MOVNFORN = ? " +
						" AND MOVLTIPO = 'M'" +
						" AND MOVLATIV = 'T'" +
						" ORDER BY MOVDDATA";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			
			movimentos.addAll(Utils.getObjectsStr(st, BeanMovCaixa.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movimentos;
	}
	
	public ArrayList<BeanMovCaixa> getMovimentosDoDia(Date data){
		ArrayList<BeanMovCaixa> centros = new ArrayList<BeanMovCaixa>();
		try {
			
			String sql = "SELECT * FROM VW_MOVIMENTO WHERE MOVDDATA = ? AND MOVLATIV = 'T' AND MOVLTIPO = 'M'";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setDate(1, new java.sql.Date(data.getTime()));
						
			centros.addAll(Utils.getObjectsStr(st, BeanMovCaixa.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return centros;
	}
	
	public void inserir(BeanMovCaixa mov){
		try {
			String sql = " INSERT INTO MOVIMENTO(MOVDDATA, MOVNCGCOG, MOVCDESC," +
					     " MOVCDOCM, MOVYVALR, MOVNFLQU, MOVNCADT, MOVDVENC, " +
					     " MOVNFORN, MOVNCOCO, MOVNFILI, MOVNCGORE, MOVLTIPO," +
					     " MOVNCODG, MOVNNRPC, MOVNQTPC, MOVLPEND, MOVCCODG)" +
					     " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setDate(1, Utils.stringToDateSQL(mov.getMovddata()));
			
			if (ValidaObjeto.validaInteiro(mov.getMovncgcog())){
				st.setInt(2, Integer.parseInt(mov.getMovncgcog()));
			}else{
				st.setNull(2, Types.INTEGER);
			}		
			
			st.setString(3, mov.getMovcdesc().toUpperCase());
			
			
			st.setString(4, mov.getMovcdocm());
			st.setFloat(5, Float.valueOf(Utils.converteFloatBR(mov.getMovyvalr())));
			
			if (ValidaObjeto.validaInteiro(mov.getMovnflqu())){
				st.setInt(6, Integer.parseInt(mov.getMovnflqu()));
			}else{
				st.setNull(6, Types.INTEGER);
			}			
			
			st.setInt(7, Integer.parseInt(mov.getMovncadt()));
			st.setDate(8, Utils.stringToDateSQL(mov.getMovdvenc()));
			
			if (ValidaObjeto.validaInteiro(mov.getMovnforn())){
				st.setInt(9, Integer.parseInt(mov.getMovnforn()));
			}else{
				st.setNull(9, Types.INTEGER);
			}
			
			if (ValidaObjeto.validaInteiro(mov.getMovncoco())){
				st.setInt(10, Integer.parseInt(mov.getMovncoco()));
			}else{
				st.setNull(10, Types.INTEGER);
			}
			
			if (ValidaObjeto.validaInteiro(mov.getMovnfili())){
				st.setInt(11, Integer.parseInt(mov.getMovnfili()));
			}else{
				st.setNull(11, Types.INTEGER);
			}
			
			if (ValidaObjeto.validaInteiro(mov.getMovncgore()) && Integer.parseInt(mov.getMovncgore()) != 0){
				st.setInt(12, Integer.parseInt(mov.getMovncgore()));
			}else{
				st.setNull(12, Types.INTEGER);
			}
			
			st.setString(13, mov.getMovltipo());
			
			st.setInt(14, Integer.parseInt(mov.getMovncodg()));
			
			st.setInt(15, Integer.parseInt(mov.getMovnnrpc()));
			
			st.setInt(16, Integer.parseInt(mov.getMovnqtpc()));
			
			st.setString(17, mov.getMovlpend());
			
			st.setString(18, mov.getMovccodg());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cancelar(BeanMovCaixa beanMovCaixa){
		try {
			String sql = "UPDATE MOVIMENTO SET MOVLATIV = 'F' WHERE MOVNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(beanMovCaixa.getMovncodg()));
			
			st.executeUpdate();								
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void update(BeanMovCaixa mov){
		try {
			
			String sql = " UPDATE MOVIMENTO SET MOVDDATA = ?, MOVNCGCOG = ?, MOVCDESC = ?," +
		     " MOVCDOCM = ?, MOVYVALR = ?, MOVNFLQU = ?, MOVNCADT = ?, MOVDVENC = ?, " +
		     " MOVNFORN = ?, MOVNCOCO = ?, MOVNFILI = ?, MOVNCGORE = ?, MOVLTIPO = ?," +
		     " MOVNNRPC = ?, MOVNQTPC = ?" +
		     " WHERE MOVNCODG = ?";

		PreparedStatement st = Banco.getConnection().prepareStatement(sql);
		
		st.setDate(1, Utils.stringToDateSQL(mov.getMovddata()));
		
		if (ValidaObjeto.validaInteiro(mov.getMovncgcog())){
			st.setInt(2, Integer.parseInt(mov.getMovncgcog()));
		}else{
			st.setNull(2, Types.INTEGER);
		}
		
		st.setString(3, mov.getMovcdesc());
		
		if (ValidaObjeto.validaString(mov.getMovcdocm())){
			st.setString(4, mov.getMovcdocm().toUpperCase());
		}else{
			st.setNull(4, Types.VARCHAR);
		}
		
		st.setFloat(5, Float.valueOf(Utils.converteFloatBR(mov.getMovyvalr())));
		
		if (ValidaObjeto.validaInteiro(mov.getMovnflqu())){
			st.setInt(6, Integer.parseInt(mov.getMovnflqu()));
		}else{
			st.setNull(6, Types.INTEGER);
		}
		
		st.setInt(7, Integer.parseInt(mov.getMovncadt()));
		st.setDate(8, Utils.stringToDateSQL(mov.getMovdvenc()));
		
		if (ValidaObjeto.validaInteiro(mov.getMovnforn())){
			st.setInt(9, Integer.parseInt(mov.getMovnforn()));
		}else{
			st.setNull(9, Types.INTEGER);
		}
		
		if (ValidaObjeto.validaInteiro(mov.getMovncoco())){
			st.setInt(10, Integer.parseInt(mov.getMovncoco()));
		}else{
			st.setNull(10, Types.INTEGER);
		}
		
		if (ValidaObjeto.validaInteiro(mov.getMovnfili())){
			st.setInt(11, Integer.parseInt(mov.getMovnfili()));
		}else{
			st.setNull(11, Types.INTEGER);
		}
		
		if (ValidaObjeto.validaInteiro(mov.getMovncgore()) && Integer.parseInt(mov.getMovncgore()) != 0){
			st.setInt(12, Integer.parseInt(mov.getMovncgore()));
		}else{
			st.setNull(12, Types.INTEGER);
		}
		
		st.setString(13, mov.getMovltipo());
		
		st.setInt(14, Integer.parseInt(mov.getMovnnrpc()));
		
		st.setInt(15, Integer.parseInt(mov.getMovnqtpc()));

		st.setInt(16, Integer.parseInt(mov.getMovncodg()));
		
		st.executeUpdate();			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void cancela(int movncodg){
		try {
			
			String sql = " UPDATE MOVIMENTO SET MOVLATIV = 'F' WHERE MOVNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, movncodg);
			
			st.executeUpdate();	
			
			st.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pagarFuturo(BeanMovCaixa beanMovCaixa){
		try {
			
			String sql = " UPDATE MOVIMENTO SET " +
					     " MOVDDATA = ?," +
					     " MOVLTIPO = 'M'," +
					     " MOVNFLQU = ?," +
					     " MOVLPEND = ?," +
					     " MOVCCODG = ?"+
					     " WHERE MOVNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setDate(1, Utils.stringToDateSQL(beanMovCaixa.getMovddata()));
			st.setInt(2, Integer.parseInt(beanMovCaixa.getMovnflqu()));
			st.setString(3, beanMovCaixa.getMovlpend());
			st.setString(4, beanMovCaixa.getMovccodg());
			st.setInt(5, Integer.parseInt(beanMovCaixa.getMovncodg()));
			
			st.executeUpdate();
			
			st.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Integer getValueGenerator(String gen){
		int ret = -1;
		try {
			String sql = "SELECT GEN_ID("+gen.toUpperCase()+",1) FROM RDB$DATABASE";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			if (rs.next()){
				ret = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}		

	public String getCodigoMovimento(String data){
		String ret = "";
		try {
			String sql = "SELECT CODIGO FROM SP_GERA_NUMERO_MOVIMENTO(?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setDate(1, Utils.stringToDateSQL(data));
			
			FormataObj format = new FormataObj();
			
			ResultSet rs = st.executeQuery();
			
			if (rs.next()){
				ret = format.formataValor(rs.getInt(1), "00000") + ":" + format.formataData(Utils.strBRToDate(data), "dd.MM.yyyy"); 
			}
			
			st.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
		
}
