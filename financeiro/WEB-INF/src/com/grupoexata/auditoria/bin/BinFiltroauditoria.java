package com.grupoexata.auditoria.bin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.grupoexata.auditoria.bean.BeanAuditoriatipo;
import com.grupoexata.auditoria.bean.BeanAuditoriavalor;
import com.grupoexata.auditoria.bean.BeanContratoauditoria;
import com.grupoexata.auditoria.bean.BeanFiltroauditoria;
import com.grupoexata.auditoria.bean.BeanGrupoauditoria;
import com.grupoexata.auditoria.dao.ModelFiltroauditoria;
import com.grupoexata.auditoria.util.ListExecute;
import com.grupoexata.auditoria.util.Operador;
import com.grupoexata.auditoria.util.OperadorAND;
import com.grupoexata.auditoria.util.OperadorOR;
import com.grupoexata.bancario.utils.Utils;

public class BinFiltroauditoria {
	
	private ModelFiltroauditoria mfau;
	private AuditoriatipoExecute execatp;
	private AuditoriavalorExecute execavl;
	private AuditoriaquestaoExecute execaqs;
	private AuditoriaExecute<BeanGrupoauditoria> execgpa;
	private int ctncodg;
	
	
	public BinFiltroauditoria(int ctncodg) {
		this.ctncodg = ctncodg;
		mfau = ModelFiltroauditoria.getIntance();
		execatp = new AuditoriatipoExecute();
		execavl = new AuditoriavalorExecute();
		execaqs = new AuditoriaquestaoExecute();
		execgpa = new AuditoriaExecute<BeanGrupoauditoria>(){

			@Override
			public boolean execute(BeanGrupoauditoria t, Map<String, Object> map) {
				return grupoauditoria(t, map);
			}
			
		};
	}
	
	private static final Operador[] opers = {
		new OperadorAND(), 
		new OperadorOR()
	};
	
	private static Operador getOperador(String s){
		int i = -1;
		boolean b = false;
		while (!b && ++i < opers.length) {
			b = opers[i].getName().equals(s);
		}
		return b ? opers[i] : null;
	}	
	
	public List<BeanContratoauditoria> executar(Map<String, Object> m){
		List<BeanFiltroauditoria> l = mfau.list();		
		boolean filtro;
		List<BeanContratoauditoria> list = new ArrayList<BeanContratoauditoria>();
		Map<String, Object> map;
		for (BeanFiltroauditoria fau : l) {
			filtro = false;
			map = new HashMap<String, Object>();
			map.put("Filtroauditoria", fau);
			map.put("ctncodg", ctncodg);
			if(Utils.exists(fau.getFauncgatp())){
				filtro = auditoriatipo(fau, map);
			}else if(Utils.exists(fau.getFauncgavl())){
				filtro = auditoriavalor(fau, map);
			}else if(Utils.exists(fau.getFauncggpa())){
				filtro = grupoauditoria(fau, map);
			}
			if(filtro){
				BeanContratoauditoria contratoauditoria = new BeanContratoauditoria("" + ctncodg, fau.getFauncodg());
				list.add(contratoauditoria);
				//System.out.println(fau.getFauncodg() + "====");
			}
		}	
		return list;
	}
	
	public boolean grupoauditoria(BeanFiltroauditoria fau, Map<String, Object> map){
		BeanGrupoauditoria gpa = mfau.getGrupoauditoria(Integer.parseInt(fau.getFauncggpa()));
		return grupoauditoria(gpa, map);
	}
	public boolean grupoauditoria(BeanGrupoauditoria gpa, Map<String, Object> map){
		map.put("gpancodg", Integer.parseInt(gpa.getGpancodg()));
		Operador oper = getOperador(gpa.getGpacoper());
		List<ListExecute> list = new ArrayList<ListExecute>();
		List<ListExecute> listRemove = new ArrayList<ListExecute>();
		list.add(new AuditoriatipoListExecute(map, execatp));
		list.add(new AuditoriavalorListExecute(map, execavl));
		list.add(new AuditoriaquestaoListExecute(map, execaqs));
		list.add(new GrupoauditoriaListExecute(map, execgpa));
		for (ListExecute listExecute : list) {
			if(listExecute.size() <= 0){
				listRemove.add(listExecute);
			}
		}
		list.removeAll(listRemove);
		return oper.execute(list.toArray(new ListExecute[list.size()]));
	}
	
	
	private boolean auditoriatipo(BeanFiltroauditoria fau, Map<String, Object> map){
		BeanAuditoriatipo atp = mfau.getAuditoriatipo(Integer.parseInt(fau.getFauncgatp()));
		return execatp.execute(atp, map);
	}
	
	private boolean auditoriavalor(BeanFiltroauditoria fau, Map<String, Object> map){
		BeanAuditoriavalor avl = mfau.getAuditoriavalor(Integer.parseInt(fau.getFauncgavl()));
		return execavl.execute(avl, map);
	}
	
	public static void main(String[] args){
		int ctncodg = 20241;
		List<BeanContratoauditoria> l = new BinFiltroauditoria(ctncodg).executar(null);
		for (BeanContratoauditoria cta : l) {
			System.out.println("Retorno:" + cta.getCtancgfau());			
		}
		ModelFiltroauditoria.getIntance().addContratoauditorias(l);
	}
}
