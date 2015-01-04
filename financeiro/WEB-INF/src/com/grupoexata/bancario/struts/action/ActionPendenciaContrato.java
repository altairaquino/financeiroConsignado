package com.grupoexata.bancario.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.dao.ModelPendenciaContrato;

public class ActionPendenciaContrato extends DispatchAction {

	private final static String ERRO_CLASS = "ACPE:";
	
	public ActionForward novo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		
		ActionForward fw = new ActionForward();
		try {
			
			fw.setPath("/pendenciaContratoNovo.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0002";
		
		ActionForward fw = new ActionForward();
		
		try {
			
			fw.setPath("/angariadorPesquisaLigacao.do");
			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
	
		
		return fw;
	}	
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0003";
		
		ActionForward fw = new ActionForward();
		
		try {
			
			String ctncodg = request.getParameter("ctncodg");
			
			request.setAttribute("", ModelPendenciaContrato.getInstance().getPendenciasDoContrato(Integer.parseInt(ctncodg)));
			
			fw.setPath("/pendenciaContratoLista.do");
			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
	
		
		return fw;
	}

}
