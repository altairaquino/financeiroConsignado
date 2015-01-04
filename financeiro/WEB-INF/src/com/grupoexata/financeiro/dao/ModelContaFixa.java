package com.grupoexata.financeiro.dao;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.financeiro.struts.bean.BeanContaFixa;

public class ModelContaFixa {
	
	public static ModelContaFixa getInstance(){
		return new ModelContaFixa();
	}
	
	public BeanContaFixa getContaFixa(int cofncodg){
		BeanContaFixa plano = null;
		try {
			
			String sql = "SELECT * FROM VW_CONTAFIXA WHERE COFNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, cofncodg);
			
			List<BeanContaFixa> l = Utils.getObjectsStr(st, BeanContaFixa.class);
			
			if (!l.isEmpty()){
				plano = l.get(0);
			}						
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plano;
	}
	
	public ArrayList<BeanContaFixa> getContasFixas(){
		ArrayList<BeanContaFixa> contas = new ArrayList<BeanContaFixa>();
		try {
			
			String sql = "SELECT * FROM VW_CONTAFIXA ";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			contas.addAll(Utils.getObjectsStr(st, BeanContaFixa.class));								
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contas;
	}
	
		
	public void inserir(BeanContaFixa conta){
		try {
			String sql = " INSERT INTO CONTAFIXA(COFNCONT,COFCDESC," +
					     " COFCDOCM, COFNDIA, COFNFORN, COFNCOCO, COFNFILI, COFYVALR)" +
					     " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(conta.getCofncont()));
			st.setString(2, conta.getCofcdesc().toUpperCase());
			st.setString(3, conta.getCofcdocm().toUpperCase());
			st.setInt(4, Integer.parseInt(conta.getCofndia()));
			
			if (ValidaObjeto.validaInteiro(conta.getCofnforn())){
				st.setInt(5, Integer.parseInt(conta.getCofnforn()));
			}else{
				st.setNull(5, Types.INTEGER);
			}
			
			if (ValidaObjeto.validaInteiro(conta.getCofncoco())){
				st.setInt(6, Integer.parseInt(conta.getCofncoco()));
			}else{
				st.setNull(6, Types.INTEGER);
			}
			
			if (ValidaObjeto.validaInteiro(conta.getCofnfili())){
				st.setInt(7, Integer.parseInt(conta.getCofnfili()));
			}else{
				st.setNull(7, Types.INTEGER);
			}
			
			if (ValidaObjeto.validaFloat(Utils.converteFloatBR(conta.getCofyvalr()))){
				st.setFloat(8, Float.parseFloat(Utils.converteFloatBR(conta.getCofyvalr())));
			}else{
				st.setNull(8, Types.FLOAT);
			}
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(BeanContaFixa conta){
		try {
			String sql = " UPDATE CONTAFIXA SET " +
					     " COFNCONT = ?," +
					     " COFCDESC = ?," +
					     " COFCDOCM = ?," +
					     " COFNDIA = ?," +
					     " COFNFORN = ?, " +
					     " COFNCOCO = ?," +
					     " COFNFILI = ?," +
					     " COFYVALR = ?" +
					     " WHERE COFNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(conta.getCofncont()));
			st.setString(2, conta.getCofcdesc().toUpperCase());
			st.setString(3, conta.getCofcdocm().toUpperCase());
			st.setInt(4, Integer.parseInt(conta.getCofndia()));
			
			if (ValidaObjeto.validaInteiro(conta.getCofnforn())){
				st.setInt(5, Integer.parseInt(conta.getCofnforn()));
			}else{
				st.setNull(5, Types.INTEGER);
			}
			
			if (ValidaObjeto.validaInteiro(conta.getCofncoco())){
				st.setInt(6, Integer.parseInt(conta.getCofncoco()));
			}else{
				st.setNull(6, Types.INTEGER);
			}
			
			if (ValidaObjeto.validaInteiro(conta.getCofnfili())){
				st.setInt(7, Integer.parseInt(conta.getCofnfili()));
			}else{
				st.setNull(7, Types.INTEGER);
			}
			
			if (ValidaObjeto.validaFloat(Utils.converteFloatBR(conta.getCofyvalr()))){
				st.setFloat(8, Float.parseFloat(Utils.converteFloatBR(conta.getCofyvalr())));
			}else{
				st.setNull(8, Types.FLOAT);
			}
			
			st.setInt(9, Integer.parseInt(conta.getCofncodg()));
			
			st.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void importar(int enncodg) {
		try {
			String sql = "EXECUTE PROCEDURE SP_IMPORTA_CONTAFIXA_DIA(?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void cancelar(int cofncodg) {
		try {
			String sql = " UPDATE CONTAFIXA SET " +
					     " COFLATIV = 'F'" +
					     " WHERE COFNCODG = (?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, cofncodg);
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
}
