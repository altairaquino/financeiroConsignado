package com.grupoexata.auditoria.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.auditoria.bean.BeanAuditoriaquestao;
import com.grupoexata.auditoria.bean.BeanAuditoriatipo;
import com.grupoexata.auditoria.bean.BeanAuditoriavalor;
import com.grupoexata.auditoria.bean.BeanContratoauditoria;
import com.grupoexata.auditoria.bean.BeanFiltroauditoria;
import com.grupoexata.auditoria.bean.BeanGrupoauditoria;
import com.grupoexata.bancario.utils.Utils;

public class ModelFiltroauditoria extends Model{
	private static final String QR_FILTROAUDITORIA_LIST = "SELECT * FROM VW_FILTROAUDITORIA";
	private static final String QR_GRUPOAUDITORIA_BY_ID = "SELECT * FROM VW_GRUPOAUDITORIA WHERE GPANCODG = ?";
	private static final String QR_GRUPOAUDITORIA_LIST_BY_GPANCGGPA = "SELECT * FROM VW_GRUPOAUDITORIA WHERE GPANCGGPA = ?";
	private static final String QR_AUDITORIATIPO_LIST_BY_ATPNCGGPA = "SELECT * FROM VW_AUDITORIATIPO WHERE ATPNCGGPA = ?";
	private static final String QR_AUDITORIAVALOR_LIST_BY_AVLNCGGPA = "SELECT * FROM VW_AUDITORIAVALOR WHERE AVLNCGGPA = ?";
	
	private static final String QR_AUDITORIATIPO_BY_ID = "SELECT * FROM VW_AUDITORIATIPO WHERE ATPNCODG = ?";
	private static final String QR_AUDITORIAVALOR_BY_ID = "SELECT * FROM VW_AUDITORIAVALOR WHERE AVLNCODG = ?";
	
	
	private static final String QR_AUDITORIAQUESTAO_LIST_BY_AQSNCGGPA = "SELECT * FROM AUDITORIAQUESTAO WHERE AQSNCGGPA = ?";
	private static final String QR_ADD_CONTRATOAUDITORIA = "EXECUTE PROCEDURE SP_CONTRATOAUDITORIA(?, ?)";
	
	
	
	private static final String QR_AUDITORIAQUESTAO_CHECK = "SELECT * FROM VW_AUDITORIAQUESTAO_CHECK WHERE AQSNCGGPA = ? AND AQSNCGCT  = ?";
	
	
	public static ModelFiltroauditoria getIntance(){
		return new ModelFiltroauditoria();
	}
	
	public List<BeanFiltroauditoria> list(){
		return getList(BeanFiltroauditoria.class, QR_FILTROAUDITORIA_LIST);
	}
	
	public BeanGrupoauditoria getGrupoauditoria(int gpancodg){
		return getById(BeanGrupoauditoria.class, QR_GRUPOAUDITORIA_BY_ID, gpancodg);
	}
	
	public List<BeanGrupoauditoria> listGrupoauditoria(int gpancodg){
		return getListByFk(BeanGrupoauditoria.class, QR_GRUPOAUDITORIA_LIST_BY_GPANCGGPA, gpancodg);
	}
	
	public List<BeanAuditoriatipo> listAuditoriatipo(int gpancodg){
		return getListByFk(BeanAuditoriatipo.class, QR_AUDITORIATIPO_LIST_BY_ATPNCGGPA, gpancodg);
	}
	
	public List<BeanAuditoriavalor> listAuditoriavalor(int gpancodg){
		return getListByFk(BeanAuditoriavalor.class, QR_AUDITORIAVALOR_LIST_BY_AVLNCGGPA, gpancodg);
	}
	
	public List<BeanAuditoriaquestao> listAuditoriaquestao(int gpancodg){
		return getListByFk(BeanAuditoriaquestao.class, QR_AUDITORIAQUESTAO_LIST_BY_AQSNCGGPA, gpancodg);
	}
	public BeanAuditoriatipo getAuditoriatipo(int atpncodg) {
		return getById(BeanAuditoriatipo.class, QR_AUDITORIATIPO_BY_ID, atpncodg);
	}
	public BeanAuditoriavalor getAuditoriavalor(int avlncodg) {
		return getById(BeanAuditoriavalor.class, QR_AUDITORIAVALOR_BY_ID, avlncodg);
	}
	
	public List<BeanAuditoriaquestao> listAuditoriaquestao(int aqsncggpa, int aqsncgct){
		List<BeanAuditoriaquestao> list = new ArrayList<BeanAuditoriaquestao>();
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_AUDITORIAQUESTAO_CHECK);
			st.setInt(1, aqsncggpa);
			st.setInt(2, aqsncgct);
			list = Utils.getObjectsStr(st, BeanAuditoriaquestao.class);
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
	
	public void addContratoauditoria(BeanContratoauditoria cta){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_ADD_CONTRATOAUDITORIA);
			st.setInt(1, Integer.parseInt(cta.getCtancgct()));
			st.setInt(2, Integer.parseInt(cta.getCtancgfau()));
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
	
	public void addContratoauditorias(List<BeanContratoauditoria> list){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_ADD_CONTRATOAUDITORIA);
			for (BeanContratoauditoria cta : list) {
				st.setInt(1, Integer.parseInt(cta.getCtancgct()));
				st.setInt(2, Integer.parseInt(cta.getCtancgfau()));
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
	
	
}
