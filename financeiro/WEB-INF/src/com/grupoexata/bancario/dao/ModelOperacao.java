package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanOperacao;
import com.grupoexata.bancario.utils.Utils;


public class ModelOperacao {
	
	public static ModelOperacao getInstance(){
		return new ModelOperacao();
	}
	
	public BeanOperacao getOperacao(int opncodg){
		BeanOperacao operacao = null;
		try {
			String sql = "SELECT * FROM VW_OPERACAO WHERE OPNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, opncodg);
			
			List<BeanOperacao> l = Utils.getObjectsStr(st,BeanOperacao.class);
			
			if (!l.isEmpty())
				operacao = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return operacao;
	}
	
	
	public boolean usuarioTemAcessoOperacao(int usncodg, int opncodg){
		boolean ret = false;
		try {
			String sql = "SELECT OPNCODG, OPNPART, OPCDESC, OPCHINT, OPCLINK FROM VW_OPERACAO_USUARIO WHERE OPNCODG = ? AND OPNCGEN = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, opncodg);
			st.setInt(2, usncodg);
			
			ret = !Utils.getObjectsStr(st,BeanOperacao.class).isEmpty();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public ArrayList<BeanOperacao> getOperacoes(){
		ArrayList<BeanOperacao> operacoes = new ArrayList<BeanOperacao>();
		try {
			String sql = "SELECT * FROM VW_OPERACAO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			operacoes.addAll(Utils.getObjectsStr(st,BeanOperacao.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return operacoes;
	}
	
	public ArrayList<BeanOperacao> getOperacoesPaiDoUsuario(int usncodg){
		ArrayList<BeanOperacao> operacoes = new ArrayList<BeanOperacao>();
		try {
			String sql = "SELECT DISTINCT OPNCODG, OPNPART, OPCDESC, OPCHINT, OPCLINK FROM VW_OPERACAO_USUARIO WHERE OPNPART = 0 AND OPNCGEN = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, usncodg);
			
			operacoes.addAll(Utils.getObjectsStr(st,BeanOperacao.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return operacoes;
	}
	
	public ArrayList<BeanOperacao> getOperacoesPai(){
		ArrayList<BeanOperacao> operacoes = new ArrayList<BeanOperacao>();
		try {
			String sql = "SELECT * FROM VW_OPERACAO WHERE OPNPART = 0";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			operacoes.addAll(Utils.getObjectsStr(st,BeanOperacao.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return operacoes;
	}
	
	public ArrayList<BeanOperacao> getSubOperacoesDoUsuario(int usncodg, int opnpart){
		ArrayList<BeanOperacao> operacoes = new ArrayList<BeanOperacao>();
		try {
			String sql = "SELECT DISTINCT OPNCODG, OPNPART, OPCDESC, OPCHINT, OPCLINK FROM VW_OPERACAO_USUARIO WHERE OPNPART = ? AND OPNCGEN = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, opnpart);
			st.setInt(2, usncodg);
			
			operacoes.addAll(Utils.getObjectsStr(st,BeanOperacao.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return operacoes;
	}
	
	public String getMenu(int usncodg){
		
		StringBuffer buffer = new StringBuffer();
		Iterator<BeanOperacao> opp = this.getOperacoesPaiDoUsuario(usncodg).iterator();
		while(opp.hasNext()){
			buffer.append("<li>\n");
			BeanOperacao operacaop = opp.next();
			buffer.append("\t<a href=\"#\" title=\""+operacaop.getOpchint()+"\">"+operacaop.getOpcdesc()+"</a>\n");
			Iterator<BeanOperacao> opf = this.getSubOperacoesDoUsuario(usncodg, Integer.parseInt(operacaop.getOpncodg())).iterator();
			buffer.append("\t<ul>\n");
			while(opf.hasNext()){
				BeanOperacao operacaof = opf.next();
				buffer.append("\t\t<li>\n");
				buffer.append("\t\t\t<a href=\""+operacaof.getOpclink()+"\" title=\""+operacaof.getOpchint()+"\">"+operacaof.getOpcdesc()+"</a>\n");
				buffer.append("\t\t</li>\n");
			}
			buffer.append("\t</ul>\n");
			buffer.append("</li>\n");
			
		}
		
		
		return buffer.toString();
	}
	
	public void inserir(BeanOperacao operacao){
		try {
			String sql = "INSERT INTO OPERACAO(OPNCODG, OPNPART, OPCDESC, OPCHINT, OPCLINK)" +
					     " VALUES (?,?,?,?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(operacao.getOpncodg()));
			st.setInt(2, Integer.parseInt(operacao.getOpnpart()));
			st.setString(3, operacao.getOpcdesc());
			st.setString(4, operacao.getOpchint());
			st.setString(5, operacao.getOpclink());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(BeanOperacao operacao){
		try {
			String sql = " UPDATE OPERACAO set OPNPART = ?, OPCDESC = ?, OPCHINT = ?, OPCLINK = ?" +
					     " WHERE OPNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(operacao.getOpnpart()));
			st.setString(2, operacao.getOpcdesc());
			st.setString(3, operacao.getOpchint());
			st.setString(4, operacao.getOpclink());
			st.setInt(5, Integer.parseInt(operacao.getOpncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
