package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanAngariador;
import com.grupoexata.bancario.utils.FormataObj;
import com.grupoexata.bancario.utils.Utils;

public class ModelAngariador {

	public static ModelAngariador getInstance(){
		return new ModelAngariador();
	}
			
	public BeanAngariador getAngariador(int anncgen){
		BeanAngariador angariador = null;
		
		try {
			String sql = "SELECT * FROM VW_ANGARIADOR WHERE ANNCGEN = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, anncgen);
			
			List<BeanAngariador> l = Utils.getObjectsStr(st, BeanAngariador.class);
			
			if (!l.isEmpty()){
				angariador = l.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return angariador;
		
	}
	
	public ArrayList<BeanAngariador> getAngariadores(){
		ArrayList<BeanAngariador> lista = new ArrayList<BeanAngariador>();		
		try {
			String sql = "SELECT * FROM VW_ANGARIADOR " +
			             "WHERE ANNCGTN = 3 "+
					     "ORDER BY ANCNMEN";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			lista.addAll(Utils.getObjectsStr(st, BeanAngariador.class));
			
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
		
	}
		
	public void update(BeanAngariador angariador) {
		try {
			String sql = " UPDATE ANGARIADOR SET " +
					     " ANNSPRE = ?," +
					     " ANNMETA = ?," +
					     " ANLAGEN = ?," +
					     " ANNCGTN = ?," +
					     " ANNCGRG = ?," +
					     " ANDCADT = ?,"+
					     " ANLATIV = ?"+
					     " WHERE ANNCGEN = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setFloat(1, Float.parseFloat(Utils.converteFloatBR(angariador.getAnnspre())));
			st.setFloat(2, Float.parseFloat(Utils.converteFloatBR(angariador.getAnnmeta())));
			st.setString(3, angariador.getAnlagen());
			st.setInt(4, Integer.parseInt(angariador.getAnncgtn()));
			
			if (Integer.parseInt(angariador.getAnncgre()) == -1){
				st.setNull(5, Types.INTEGER);
			}else{
				st.setInt(5, Integer.parseInt(angariador.getAnncgre()));
			}
			
			st.setDate(6, Utils.stringToDateSQL(angariador.getAndcadt()));
			
			st.setString(7, angariador.getAnlativ());
			
			st.setInt(8, Integer.parseInt(angariador.getAnncgen()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public String mensagemProducaoSinergia(int enncodg){
		String msg = "";
		String sql = " SELECT COD_AGENCIA, NOME_AGENCIA, META_AGENCIA, PRODUCAO, PRODUCAO_DIA, PERC_ATING, PROJECAO, PERC_PROJ" +
				     " FROM SP_ACOMP_SINERGIA('NOW')" +
				     " WHERE COD_AGENCIA = ?";
		
		try {
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			
			ResultSet rs = st.executeQuery();
			
			FormataObj formata = new FormataObj();
			
			if (rs.next()){
				boolean atingiu = rs.getFloat("PERC_ATING") > 100f;
				msg += (!atingiu?"Olá ":"Parabéns ") +
					   rs.getString("NOME_AGENCIA")+
					   ".<br>Sua meta mensal é de R$ "+
					   formata.formataValor(rs.getFloat("META_AGENCIA"))+
					   " e até o momento foi <br>produzido R$ "+
					   formata.formataValor(rs.getFloat("PRODUCAO"))+
					   ", que representa "+
					   formata.formataValor(rs.getFloat("PERC_ATING"))+
					   "% da meta. <br>Sua meta diária de produção é R$ "+
					   formata.formataValor(rs.getFloat("PRODUCAO_DIA"))+".";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	public void atualizaSpread(){
		try {
			String sql = "EXECUTE PROCEDURE SP_ATUALIZA_SPREAD_CORRETORES";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(ModelAngariador.getInstance().mensagemProducaoSinergia(9817));
	}
}
