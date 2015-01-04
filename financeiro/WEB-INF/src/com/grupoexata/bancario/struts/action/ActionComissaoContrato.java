package com.grupoexata.bancario.struts.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.dao.ModelComissaoContrato;
import com.grupoexata.bancario.dao.ModelContrato;
import com.grupoexata.bancario.struts.bean.BeanComissaoContrato;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.struts.form.FormComissaoContrato;
import com.grupoexata.bancario.utils.GeraRelatorio;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionComissaoContrato extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACCC:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0001";
		
		ActionForward fw = new ActionForward();
		
		try {
		
			String ctncodg = request.getParameter("ctncodg");
			request.setAttribute("contrato", ModelContrato.getInstance().getContrato(Integer.parseInt(ctncodg)));
			request.setAttribute("ls_comissaocontrato", ModelComissaoContrato.getInstance().getComissoesDoContrato(Integer.parseInt(ctncodg)));
			fw.setPath("/comissaoContratoLista.do");
		
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward novo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0002";
		
		ActionForward fw = new ActionForward();
		try {
			
			request.getSession().removeAttribute("formComissaoContrato");
			
			fw.setPath("/comissaoContratoNovo.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0003";
		
		ActionForward fw = new ActionForward();
		FormComissaoContrato formComissaoContrato = (FormComissaoContrato)form;
		try {
			
			String pdncodg = request.getParameter("pdncodg");
						
			BeanComissaoContrato comissaoContrato = ModelComissaoContrato.getInstance().getComissaoContrato(Integer.parseInt(pdncodg));
			BeanUtils.copyProperties(formComissaoContrato, comissaoContrato);
			
			fw.setPath("/comissaoContratoEditar.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	/*
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormComissaoContrato formComissaoContrato = (FormComissaoContrato)form;
		ActionMessages erros = new ActionMessages();
		
		if (!ValidaObjeto.validaString(formComissaoContrato.getPdcdesc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome do ComissaoContrato é obrigatório."));
		}
		
		if (!ValidaObjeto.validaString(formComissaoContrato.getPdcabrv())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Abreviação do Nome do ComissaoContrato é obrigatório."));
		}
		
		
		if (!erros.isEmpty()){
			fw.setPath("/comissaoContratoNovo.do");			
		}else{
		
			try {
				
				BeanComissaoContrato comissaoContrato = new BeanComissaoContrato();
				BeanUtils.copyProperties(comissaoContrato, formComissaoContrato);
				
				ModelComissaoContrato.getInstance().inserir(comissaoContrato);			
							
				request.getSession().removeAttribute("formComissaoContrato");
				request.getSession().removeAttribute("ls_banco");
				
				fw = lista(mapping, form, request, response);
				
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","ComissaoContrato Cadastrado com Sucesso!"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		saveErrors(request, erros);
		
		return fw;
	}*/
	
	public ActionForward updateComissoes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0004";
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		
		String [] ccncodg = request.getParameterValues("ccncodg");
		String [] ccnperc = request.getParameterValues("ccnperc");
		
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		
		if (!erros.isEmpty() || usuario == null){
			fw.setPath("/comissaoContratoEditar.do");
		}else{
		
			try {
				
				for (int i = 0; i < ccnperc.length; i++) {
					if (ValidaObjeto.validaFloat(Utils.converteFloatBR(ccnperc[i]))) {
						BeanComissaoContrato comissaoContrato = new BeanComissaoContrato();
						comissaoContrato.setCcnperc(ccnperc[i]);
						comissaoContrato.setCcncodg(ccncodg[i]);
						comissaoContrato.setCcnc2en(usuario.getEnncodg());
						ModelComissaoContrato.getInstance().update(comissaoContrato);
					}
				}
							
				request.getSession().removeAttribute("formComissaoContrato");
				
				fw.setPath("/contratoPesquisaComissao.do");
								
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}		
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward recalculaComissaoContrato(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0005";
		
		ActionForward fw = new ActionForward();
		String ctncodg = request.getParameter("ctncodg");
		
		try {
			
			
			ModelContrato.getInstance().recalculaComissaoContrato(Integer.parseInt(ctncodg));
			
			fw.setPath("/actionComissaoContrato.do?m=lista&ctncodg="+ctncodg);
						
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward relatorios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0006";
		
		ActionMessages erros = new ActionMessages();
		ActionForward fw = new ActionForward();
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		
		if (!ValidaObjeto.validaData(data1)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial é inválida."));
		}
		if (!ValidaObjeto.validaData(data2)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Final é inválida."));
		}
		
		if (erros.isEmpty()){
			if (Utils.strBRToDate(data1).after(Utils.strBRToDate(data2))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial não pode ser maior que a final."));
			}
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/relatoriosProducao.do");
			saveErrors(request, erros);
		}else{
		
			try {
				
				Map<Object,Object> map = new HashMap<Object, Object>();
				
				map.put("DATA_INICIO", Utils.strBRToDate(data1));
				map.put("DATA_FIM", Utils.strBRToDate(data2));		
								
				switch (Integer.parseInt(request.getParameter("tipo"))) {
					case 1:
						map.put("REPORT_NAME", "producao_agente_spread");
						break;
					case 2:
						map.put("REPORT_NAME", "producao_percent_resumo_geral");
						break;
					case 3:
						map.put("REPORT_NAME", "producao_percent_resumo");
						break;
					default:
						map.put("REPORT_NAME", "producao_agente_spread");
						break;
				}
								
				GeraRelatorio.geracao(request, response, map, false);
				
				fw = null;
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		
		return fw;
	}

	public ActionForward relatoriosDiversos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0006";
		
		ActionForward fw = new ActionForward();
			
		try {
				
			Map<Object,Object> map = new HashMap<Object, Object>();
				
			switch (Integer.parseInt(request.getParameter("tipo"))) {
				case 1:
					map.put("REPORT_NAME", "producao_agente_spread");
					break;
				case 2:
					map.put("REPORT_NAME", "producao_percent_resumo_geral");
					break;
				case 3:
					map.put("REPORT_NAME", "producao_percent_resumo");
					break;
				default:
					map.put("REPORT_NAME", "producao_agente_spread");
				break;
			}
				
			GeraRelatorio.geracao(request, response, map, false);
				
			fw = null;
				
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;
	}
	

}
