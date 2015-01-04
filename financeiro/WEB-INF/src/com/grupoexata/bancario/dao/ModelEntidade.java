package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ModelEntidade {
	
	public static ModelEntidade getInstance(){
		return new ModelEntidade();
	}
	
	public BeanEntidade getBeanEntidade(int enncodg){
		BeanEntidade usuario = null;
		try {
			String sql = " SELECT * FROM VW_ENTIDADE WHERE ENNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			List<BeanEntidade> l = Utils.getObjectsStr(st, BeanEntidade.class);
			if (!l.isEmpty()){
				usuario = l.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}	
	
	public BeanEntidade getBeanEntidade(int enncodg, int enncgte){
		BeanEntidade usuario = null;
		try {
			String sql = " SELECT * FROM VW_ENTIDADE WHERE ENNCODG = ? AND ENNCGTE = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			st.setInt(2, enncgte);
			
			List<BeanEntidade> l = Utils.getObjectsStr(st, BeanEntidade.class);
			if (!l.isEmpty()){
				usuario = l.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public List<BeanEntidade> getAgenciasDoSupervisor(int enncodg){
		List<BeanEntidade> agencias = new ArrayList<BeanEntidade>();
		try {
			String sql = " select * from ENTIDADE" +
					     " where exists (select * from FUNCIONARIOAGENCIA" +
					     " where enncodg = FCANCGEN and FCANC2EN = ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncodg);
			
			agencias.addAll(Utils.getObjectsStr(st, BeanEntidade.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return agencias;
	}
	
	public boolean existeCPF(String enccpf){
		boolean ret = false;
		try {
			
			String sql = "SELECT * FROM VW_ENTIDADE WHERE ENCCPF = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, enccpf);
						
			ret = !Utils.getObjectsStr(st, BeanEntidade.class).isEmpty();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public void alteraSenha(int enncodg, String novaSenha){
		try {
			String sql = " UPDATE ENTIDADE SET ENCSENH = ? WHERE ENNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, novaSenha);
			st.setInt(2, enncodg);
			
			st.executeUpdate();		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void anularSenha(int enncodg){		
		try {
			String sql = " UPDATE ENTIDADE SET ENCSENH = ? WHERE ENNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setNull(1, Types.VARCHAR);
			st.setInt(2, enncodg);
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BeanEntidade getBeanEntidadePorLogin(String usclogn){
		BeanEntidade usuario = null;
		try {
			String sql = "SELECT * FROM VW_ENTIDADE WHERE ENCLOGN = UPPER(?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, usclogn);
			
			List<BeanEntidade> l = Utils.getObjectsStr(st, BeanEntidade.class);
			
			if (!l.isEmpty()){
				usuario = l.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public BeanEntidade getBeanEntidadePorCPF(String encdocm){
		BeanEntidade entidade = null;
		try {
			String sql = "SELECT * FROM VW_ENTIDADE WHERE ENCDOCM = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, encdocm);
			List<BeanEntidade> l = Utils.getObjectsStr(st, BeanEntidade.class);
			if (!l.isEmpty()){
				entidade = l.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entidade;
	}
	
	public BeanEntidade getBeanEntidadePorCPF(String encdocm, int enncgte){
		BeanEntidade usuario = null;
		try {
			String sql = "SELECT * FROM VW_ENTIDADE WHERE ENCDOCM = ? AND ENNCGTE = ? ORDER BY ENNCODG DESC";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, encdocm);
			st.setInt(2, enncgte);
			List<BeanEntidade> l = Utils.getObjectsStr(st, BeanEntidade.class);
			if (!l.isEmpty()){
				usuario = l.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	public boolean autenticaUsuario(String usclogn, String uscpswd){
		boolean ret = false;
		try {
			String sql = "SELECT * FROM VW_LOGIN WHERE ENCLOGN = UPPER(?) AND ENCSENH = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, usclogn);
			
			BeanEntidade usuario = ModelEntidade.getInstance().getBeanEntidadePorLogin(usclogn);
			
			if (usuario != null){
				if (usuario.getEncsenh() != null && usuario.getEncsenh().length() > 17){
					st.setString(2, Utils.criptografaSenha(usclogn.toUpperCase(), uscpswd));
				}else{
					st.setString(2, uscpswd);
				}
				
				ret = !Utils.getObjectsStr(st, BeanEntidade.class).isEmpty();
				
				if (ret && usuario.getEncsenh().length() < 17){
					ModelEntidade.getInstance().alteraSenha(Integer.parseInt(usuario.getEnncodg()), 
							Utils.criptografaSenha(usclogn.toUpperCase(), uscpswd));
				}
			}else{
				ret = false;
			}
								
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public boolean inserirFuncionario(BeanEntidade beanEntidade) {
		boolean ret = false;
		PreparedStatement st = null;
		try {
			String sql = "INSERT INTO ENTIDADE( ENCNOME,ENNCGTE, ENNCGPP, ENCSEXO, ENCDOCM, ENDNASC, ENCFONE, ENCCELL,"+
					"ENCMAIL, ENNCGTL, ENLENDR, ENNCGCD, ENCCEP, ENCBAIR, ENMOBS, ENCPAI, ENCMAE)"+
					" VALUES( ?, 3, 1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, beanEntidade.getEncnome().toUpperCase());
			st.setString(2, beanEntidade.getEncsexo());
			st.setString(3, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncdocm()));
			st.setDate(4,Utils.stringToDateSQL(beanEntidade.getEndnasc()));
			
			
			if (ValidaObjeto.validaString(beanEntidade.getEncfone())){
				st.setString(5, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncfone()));
			}else{
				st.setNull(5, Types.VARCHAR);
			}
			if (ValidaObjeto.validaString(beanEntidade.getEnccell())){
				st.setString(6, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccell()));
			}else{
				st.setNull(6, Types.VARCHAR);
			}			
			if (ValidaObjeto.validaString(beanEntidade.getEncmail())){
				st.setString(7, beanEntidade.getEncmail());
			}else{
				st.setNull(7, Types.VARCHAR);
			}
			st.setInt(8, Integer.parseInt(beanEntidade.getEnncgtl()));
			st.setString(9, beanEntidade.getEnlendr().toUpperCase());
			st.setInt(10, Integer.parseInt(beanEntidade.getEnncgcd()));
			st.setString(11, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccep()));
			st.setString(12, beanEntidade.getEncbair().toUpperCase());
			st.setString(13, beanEntidade.getEnmobs());
			st.setString(14, beanEntidade.getEncpai());
			st.setString(15, beanEntidade.getEncmae());
			
			st.executeUpdate();
			ret = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(st !=null && !st.isClosed()){
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	public void updateFuncionario(BeanEntidade beanEntidade) {
		try {
			String sql = " UPDATE ENTIDADE SET ENCNOME = ?, ENCSEXO = ?, ENCDOCM = ?, ENDNASC = ?, ENCFONE = ?, ENCCELL = ?,"+
					     " ENCMAIL = ?, ENNCGTL = ?, ENLENDR = ?, ENNCGCD = ?, ENCCEP = ?, ENCBAIR = ?, ENMOBS = ?, ENCPAI = ?, ENCMAE = ?"+
					     " WHERE ENNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, beanEntidade.getEncnome().toUpperCase());
			st.setString(2, beanEntidade.getEncsexo());
			st.setString(3, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncdocm()));
			st.setDate(4,Utils.stringToDateSQL(beanEntidade.getEndnasc()));			
			
			if (ValidaObjeto.validaString(beanEntidade.getEncfone())){
				st.setString(5, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncfone()));
			}else{
				st.setNull(5, Types.VARCHAR);
			}
			if (ValidaObjeto.validaString(beanEntidade.getEnccell())){
				st.setString(6, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccell()));
			}else{
				st.setNull(6, Types.VARCHAR);
			}
			if (ValidaObjeto.validaString(beanEntidade.getEncmail())){
				st.setString(7, beanEntidade.getEncmail());
			}else{
				st.setNull(7, Types.VARCHAR);
			}
			st.setInt(8, Integer.parseInt(beanEntidade.getEnncgtl()));
			st.setString(9, beanEntidade.getEnlendr().toUpperCase());
			st.setInt(10, Integer.parseInt(beanEntidade.getEnncgcd()));
			st.setString(11, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccep()));
			st.setString(12, beanEntidade.getEncbair().toUpperCase());
			st.setString(13, beanEntidade.getEnmobs());
			st.setString(14, beanEntidade.getEncpai());
			st.setString(15, beanEntidade.getEncmae());
			st.setInt(16, Integer.parseInt(beanEntidade.getEnncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void inserirCliente(BeanEntidade beanEntidade) {
		try {
			String sql = "INSERT INTO ENTIDADE( ENCNOME,ENNCGTE, ENNCGPP, ENCSEXO, ENCDOCM, ENDNASC, ENCFONE, ENCCELL,"+
					"ENCMAIL, ENNCGTL, ENLENDR, ENNCGCD, ENCCEP, ENCBAIR, ENMOBS,ENCRG,ENCOERG, ENCDORG, ENCUFRG, ENDEXRG, ENCMAE, ENCPAI)"+
					" VALUES( ?, 1, 1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, beanEntidade.getEncnome().toUpperCase());
			st.setString(2, beanEntidade.getEncsexo());
			st.setString(3, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncdocm()));
			st.setDate(4,Utils.stringToDateSQL(beanEntidade.getEndnasc()));
			
			
			if (ValidaObjeto.validaString(beanEntidade.getEncfone())){
				st.setString(5, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncfone()));
			}else{
				st.setNull(5, Types.VARCHAR);
			}
			if (ValidaObjeto.validaString(beanEntidade.getEnccell())){
				st.setString(6, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccell()));
			}else{
				st.setNull(6, Types.VARCHAR);
			}			
			if (ValidaObjeto.validaString(beanEntidade.getEncmail())){
				st.setString(7, beanEntidade.getEncmail());
			}else{
				st.setNull(7, Types.VARCHAR);
			}
			st.setInt(8, Integer.parseInt(beanEntidade.getEnncgtl()));
			st.setString(9, beanEntidade.getEnlendr().toUpperCase());
			st.setInt(10, Integer.parseInt(beanEntidade.getEnncgcd()));
			st.setString(11, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccep()));
			st.setString(12, beanEntidade.getEncbair().toUpperCase());
			st.setString(13, beanEntidade.getEnmobs());
			st.setString(14, beanEntidade.getEncrg());
			st.setString(15, beanEntidade.getEncoerg());
			st.setString(16, beanEntidade.getEncdorg());
			st.setString(17, beanEntidade.getEncufrg());
			
			if (ValidaObjeto.validaData(beanEntidade.getEndexrg())){
				st.setDate(18, Utils.stringToDateSQL(beanEntidade.getEndexrg()));			
			}else{
				st.setNull(18, Types.DATE);
			}
			
			st.setString(19, beanEntidade.getEncmae().toUpperCase());
			st.setString(20, beanEntidade.getEncpai().toUpperCase());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void inserirAngariador(BeanEntidade beanEntidade) {
		try {
			String sql = "INSERT INTO ENTIDADE( ENCNOME,ENNCGTE, ENNCGPP, ENCSEXO, ENCDOCM, ENDNASC, ENCFONE, ENCCELL,"+
					"ENCMAIL, ENNCGTL, ENLENDR, ENNCGCD, ENCCEP, ENCBAIR, ENMOBS)"+
					" VALUES( ?, 2, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, beanEntidade.getEncnome().toUpperCase());
			st.setInt(2, Integer.parseInt(beanEntidade.getEnncgpp()));
			st.setString(3, beanEntidade.getEncsexo());
			st.setString(4, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncdocm()));
			
			if (ValidaObjeto.validaData(beanEntidade.getEndnasc())){
				st.setDate(5, Utils.stringToDateSQL(beanEntidade.getEndnasc()));
			}else{
				st.setNull(5, Types.DATE);
			}
			
			if (ValidaObjeto.validaString(beanEntidade.getEncfone())){
				st.setString(6, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncfone()));
			}else{
				st.setNull(6, Types.VARCHAR);
			}
			if (ValidaObjeto.validaString(beanEntidade.getEnccell())){
				st.setString(7, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccell()));
			}else{
				st.setNull(7, Types.VARCHAR);
			}			
			if (ValidaObjeto.validaString(beanEntidade.getEncmail())){
				st.setString(8, beanEntidade.getEncmail());
			}else{
				st.setNull(8, Types.VARCHAR);
			}
			st.setInt(9, Integer.parseInt(beanEntidade.getEnncgtl()));
			st.setString(10, beanEntidade.getEnlendr().toUpperCase());
			st.setInt(11, Integer.parseInt(beanEntidade.getEnncgcd()));
			st.setString(12, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccep()));
			st.setString(13, beanEntidade.getEncbair().toUpperCase());
			st.setString(14, beanEntidade.getEnmobs());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void inserirFornecedor(BeanEntidade beanEntidade) {
		try {
			String sql = "INSERT INTO ENTIDADE( ENCNOME,ENNCGTE, ENNCGPP, ENCDOCM, ENDNASC, ENCFONE, ENCCELL,"+
					"ENCMAIL, ENNCGTL, ENLENDR, ENNCGCD, ENCCEP, ENCBAIR, ENMOBS)"+
					" VALUES( ?, 7, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, beanEntidade.getEncnome().toUpperCase());
			st.setInt(2, Integer.parseInt(beanEntidade.getEnncgpp()));
			st.setString(3, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncdocm()));
			st.setDate(4,Utils.stringToDateSQL(beanEntidade.getEndnasc()));			
			
			if (ValidaObjeto.validaString(beanEntidade.getEncfone())){
				st.setString(5, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncfone()));
			}else{
				st.setNull(5, Types.VARCHAR);
			}
			if (ValidaObjeto.validaString(beanEntidade.getEnccell())){
				st.setString(6, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccell()));
			}else{
				st.setNull(6, Types.VARCHAR);
			}			
			if (ValidaObjeto.validaString(beanEntidade.getEncmail())){
				st.setString(7, beanEntidade.getEncmail());
			}else{
				st.setNull(7, Types.VARCHAR);
			}
			st.setInt(8, Integer.parseInt(beanEntidade.getEnncgtl()));
			st.setString(9, beanEntidade.getEnlendr().toUpperCase());
			st.setInt(10, Integer.parseInt(beanEntidade.getEnncgcd()));
			st.setString(11, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccep()));
			st.setString(12, beanEntidade.getEncbair().toUpperCase());
			st.setString(13, beanEntidade.getEnmobs());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateFornecedor(BeanEntidade beanEntidade) {
		try {
			String sql = "UPDATE ENTIDADE SET ENCNOME = ?, ENNCGPP = ?, ENCDOCM = ?, ENDNASC = ?, ENCFONE = ?, ENCCELL = ?,"+
					" ENCMAIL = ?, ENNCGTL = ?, ENLENDR = ?, ENNCGCD = ?, ENCCEP = ?, ENCBAIR = ?, ENMOBS = ?"+
					" WHERE ENNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, beanEntidade.getEncnome().toUpperCase());
			st.setInt(2, Integer.parseInt(beanEntidade.getEnncgpp()));
			st.setString(3, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncdocm()));
			st.setDate(4,Utils.stringToDateSQL(beanEntidade.getEndnasc()));			
			
			if (ValidaObjeto.validaString(beanEntidade.getEncfone())){
				st.setString(5, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncfone()));
			}else{
				st.setNull(5, Types.VARCHAR);
			}
			if (ValidaObjeto.validaString(beanEntidade.getEnccell())){
				st.setString(6, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccell()));
			}else{
				st.setNull(6, Types.VARCHAR);
			}			
			if (ValidaObjeto.validaString(beanEntidade.getEncmail())){
				st.setString(7, beanEntidade.getEncmail());
			}else{
				st.setNull(7, Types.VARCHAR);
			}
			
			st.setInt(8, Integer.parseInt(beanEntidade.getEnncgtl()));
			st.setString(9, beanEntidade.getEnlendr().toUpperCase());
			st.setInt(10, Integer.parseInt(beanEntidade.getEnncgcd()));
			st.setString(11, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccep()));
			st.setString(12, beanEntidade.getEncbair().toUpperCase());
			st.setString(13, beanEntidade.getEnmobs());
			st.setInt(14, Integer.parseInt(beanEntidade.getEnncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateCliente(BeanEntidade beanEntidade) {
		try {
			String sql = " UPDATE ENTIDADE SET ENCNOME = ?, ENCSEXO = ?, ENCDOCM = ?, ENDNASC = ?, ENCFONE = ?, ENCCELL = ?,"+
					     " ENCMAIL = ?, ENNCGTL = ?, ENLENDR = ?, ENNCGCD = ?, ENCCEP = ?, ENCBAIR = ?, ENMOBS = ?, ENCRG = ?," +
					     " ENCOERG = ?, ENCDORG = ?, ENCUFRG = ?, ENDEXRG = ?, ENCMAE = ?, ENCPAI = ?"+
					     " WHERE ENNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, beanEntidade.getEncnome().toUpperCase());
			st.setString(2, beanEntidade.getEncsexo());
			st.setString(3, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncdocm()));
			st.setDate(4,Utils.stringToDateSQL(beanEntidade.getEndnasc()));			
			
			if (ValidaObjeto.validaString(beanEntidade.getEncfone())){
				st.setString(5, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncfone()));
			}else{
				st.setNull(5, Types.VARCHAR);
			}
			if (ValidaObjeto.validaString(beanEntidade.getEnccell())){
				st.setString(6, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccell()));
			}else{
				st.setNull(6, Types.VARCHAR);
			}
			if (ValidaObjeto.validaString(beanEntidade.getEncmail())){
				st.setString(7, beanEntidade.getEncmail());
			}else{
				st.setNull(7, Types.VARCHAR);
			}
			st.setInt(8, Integer.parseInt(beanEntidade.getEnncgtl()));
			st.setString(9, beanEntidade.getEnlendr().toUpperCase());
			st.setInt(10, Integer.parseInt(beanEntidade.getEnncgcd()));
			st.setString(11, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccep()));
			st.setString(12, beanEntidade.getEncbair().toUpperCase());
			st.setString(13, beanEntidade.getEnmobs());
			st.setString(14, beanEntidade.getEncrg());
			st.setString(15, beanEntidade.getEncoerg());
			st.setString(16, beanEntidade.getEncdorg());
			st.setString(17, beanEntidade.getEncufrg());
			st.setDate(18, Utils.stringToDateSQL(beanEntidade.getEndexrg()));
			
			if (ValidaObjeto.validaString(beanEntidade.getEncmae())){
				st.setString(19, beanEntidade.getEncmae().toUpperCase());
			}else{
				st.setNull(19, Types.VARCHAR);
			}
			
			if (ValidaObjeto.validaString(beanEntidade.getEncpai())){
				st.setString(20, beanEntidade.getEncpai().toUpperCase());
			}else{
				st.setNull(20, Types.VARCHAR);
			}
			
			st.setInt(21, Integer.parseInt(beanEntidade.getEnncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BeanEntidade> getEntidadePorNomeCpf(String encnome) {
		List<BeanEntidade> entidades = new ArrayList<BeanEntidade>();
		try {
			String sql = "SELECT * FROM VW_ENTIDADE " +
					     " WHERE (ENCNOME LIKE upper('%"+encnome+"%') OR ENCDOCM = '"+encnome+"' " +
						 "OR CAST(ENNCODG AS VARCHAR(20)) = '"+encnome+"' )" +
						 " ORDER BY ENCNOME";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
						
			entidades = Utils.getObjectsStr(st, BeanEntidade.class);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return entidades;
	}	
	
	public List<BeanEntidade> getEntidadePorNomeCpf(String encnome, int enncgte) {
		List<BeanEntidade> entidades = new ArrayList<BeanEntidade>();
		try {
			String sql = " SELECT * FROM VW_ENTIDADE " +
					     " WHERE (ENCNOME LIKE upper('%"+encnome+"%') " +
					     		"OR ENCDOCM = '"+encnome+"' OR CAST(ENNCODG AS VARCHAR(20)) = '"+encnome+"')" +
			     				"AND ENNCGTE = ? " +
			     		 " ORDER BY ENCNOME";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, enncgte);
						
			entidades = Utils.getObjectsStr(st, BeanEntidade.class);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return entidades;
	}
	
	public void inserirLojaVeiculo(BeanEntidade beanEntidade) {
		try {
			String sql = "INSERT INTO ENTIDADE( ENCNOME,ENNCGTE, ENNCGPP, ENCDOCM, ENDNASC, ENCFONE, ENCCELL,"+
					"ENCMAIL, ENNCGTL, ENLENDR, ENNCGCD, ENCCEP, ENCBAIR, ENMOBS)"+
					" VALUES( ?, 9, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, beanEntidade.getEncnome().toUpperCase());
			st.setInt(2, 2);
			st.setString(3, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncdocm()));
			
			if (ValidaObjeto.validaData(beanEntidade.getEndnasc())){
				st.setDate(4,Utils.stringToDateSQL(beanEntidade.getEndnasc()));
			}else{
				st.setNull(4, Types.DATE);	
			}
			
			if (ValidaObjeto.validaString(beanEntidade.getEncfone())){
				st.setString(5, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncfone()));
			}else{
				st.setNull(5, Types.VARCHAR);
			}
			if (ValidaObjeto.validaString(beanEntidade.getEnccell())){
				st.setString(6, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccell()));
			}else{
				st.setNull(6, Types.VARCHAR);
			}			
			if (ValidaObjeto.validaString(beanEntidade.getEncmail())){
				st.setString(7, beanEntidade.getEncmail());
			}else{
				st.setNull(7, Types.VARCHAR);
			}
			st.setInt(8, Integer.parseInt(beanEntidade.getEnncgtl()));
			st.setString(9, beanEntidade.getEnlendr().toUpperCase());
			st.setInt(10, Integer.parseInt(beanEntidade.getEnncgcd()));
			st.setString(11, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccep()));
			st.setString(12, beanEntidade.getEncbair().toUpperCase());
			st.setString(13, beanEntidade.getEnmobs());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inserirContatoJuridico(BeanEntidade beanEntidade) {
		try {
			String sql = "INSERT INTO ENTIDADE( ENCNOME,ENNCGTE, ENNCGPP, ENCDOCM, ENCFONE, ENCCELL,"+
			"ENCMAIL, ENMOBS)"+
			" VALUES( ?, 10, 1, ?, ?, ?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, beanEntidade.getEncnome().toUpperCase());
			
			st.setString(2, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncdocm()));
			
						
			if (ValidaObjeto.validaString(beanEntidade.getEncfone())){
				st.setString(3, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncfone()));
			}else{
				st.setNull(3, Types.VARCHAR);
			}
			
			if (ValidaObjeto.validaString(beanEntidade.getEnccell())){
				st.setString(4, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccell()));
			}else{
				st.setNull(4, Types.VARCHAR);
			}			
			if (ValidaObjeto.validaString(beanEntidade.getEncmail())){
				st.setString(5, beanEntidade.getEncmail());
			}else{
				st.setNull(5, Types.VARCHAR);
			}
			
			st.setString(6, beanEntidade.getEnmobs());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updateContatoJuridico(BeanEntidade beanEntidade) {
		try {
			String sql = "UPDATE ENTIDADE SET ENCNOME = ?, ENCDOCM = ?, ENCFONE = ?, ENCCELL = ?,"+
						 "ENCMAIL = ?, ENMOBS = ?"+
						 " WHERE ENNCODG =  ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, beanEntidade.getEncnome().toUpperCase());
			
			st.setString(2, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncdocm()));
			
			
			if (ValidaObjeto.validaString(beanEntidade.getEncfone())){
				st.setString(3, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncfone()));
			}else{
				st.setNull(3, Types.VARCHAR);
			}
			
			if (ValidaObjeto.validaString(beanEntidade.getEnccell())){
				st.setString(4, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccell()));
			}else{
				st.setNull(4, Types.VARCHAR);
			}			
			if (ValidaObjeto.validaString(beanEntidade.getEncmail())){
				st.setString(5, beanEntidade.getEncmail());
			}else{
				st.setNull(5, Types.VARCHAR);
			}
			
			st.setString(6, beanEntidade.getEnmobs());
			
			st.setInt(7, Integer.parseInt(beanEntidade.getEnncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void inserirOperadorVeiculo(BeanEntidade beanEntidade) {
		try {
			String sql = "INSERT INTO ENTIDADE( ENCNOME,ENNCGTE, ENNCGPP, ENCDOCM, ENDNASC, ENCFONE, ENCCELL,"+
					"ENCMAIL, ENNCGTL, ENLENDR, ENNCGCD, ENCCEP, ENCBAIR, ENMOBS)"+
					" VALUES( ?, 8, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, beanEntidade.getEncnome().toUpperCase());
			st.setInt(2, 2);
			st.setString(3, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncdocm()));
			
			if (ValidaObjeto.validaData(beanEntidade.getEndnasc())){
				st.setDate(4,Utils.stringToDateSQL(beanEntidade.getEndnasc()));
			}else{
				st.setNull(4, Types.DATE);	
			}
			
			if (ValidaObjeto.validaString(beanEntidade.getEncfone())){
				st.setString(5, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncfone()));
			}else{
				st.setNull(5, Types.VARCHAR);
			}
			if (ValidaObjeto.validaString(beanEntidade.getEnccell())){
				st.setString(6, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccell()));
			}else{
				st.setNull(6, Types.VARCHAR);
			}			
			if (ValidaObjeto.validaString(beanEntidade.getEncmail())){
				st.setString(7, beanEntidade.getEncmail());
			}else{
				st.setNull(7, Types.VARCHAR);
			}
			st.setInt(8, Integer.parseInt(beanEntidade.getEnncgtl()));
			st.setString(9, beanEntidade.getEnlendr().toUpperCase());
			st.setInt(10, Integer.parseInt(beanEntidade.getEnncgcd()));
			st.setString(11, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccep()));
			st.setString(12, beanEntidade.getEncbair().toUpperCase());
			st.setString(13, beanEntidade.getEnmobs());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateLojaVeiculo(BeanEntidade beanEntidade) {
		try {
			String sql = " UPDATE ENTIDADE SET ENCNOME = ?, ENCDOCM = ?, ENCFONE = ?, ENCCELL = ?,"+
					     " ENCMAIL = ?, ENNCGTL = ?, ENLENDR = ?, ENNCGCD = ?, ENCCEP = ?, ENCBAIR = ?, ENMOBS = ?"+
					     " WHERE ENNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setString(1, beanEntidade.getEncnome().toUpperCase());
			st.setString(2, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncdocm()));
			
			if (ValidaObjeto.validaString(beanEntidade.getEncfone())){
				st.setString(3, ValidaObjeto.removeCharOfInteger(beanEntidade.getEncfone()));
			}else{
				st.setNull(3, Types.VARCHAR);
			}
			if (ValidaObjeto.validaString(beanEntidade.getEnccell())){
				st.setString(4, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccell()));
			}else{
				st.setNull(4, Types.VARCHAR);
			}
			if (ValidaObjeto.validaString(beanEntidade.getEncmail())){
				st.setString(5, beanEntidade.getEncmail());
			}else{
				st.setNull(5, Types.VARCHAR);
			}
			st.setInt(6, Integer.parseInt(beanEntidade.getEnncgtl()));
			st.setString(7, beanEntidade.getEnlendr().toUpperCase());
			st.setInt(8, Integer.parseInt(beanEntidade.getEnncgcd()));
			st.setString(9, ValidaObjeto.removeCharOfInteger(beanEntidade.getEnccep()));
			st.setString(10, beanEntidade.getEncbair().toUpperCase());
			st.setString(11, beanEntidade.getEnmobs());
			st.setInt(12, Integer.parseInt(beanEntidade.getEnncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
