package com.grupoexata.bancario.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.dao.ModelContaCorrente;
import com.grupoexata.bancario.dao.ModelPagamentoAgente;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.struts.bean.BeanPagamentoAgente;
import com.grupoexata.bancario.struts.form.FormPagamentoAgente;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.bancario.utils.Utils;

public class ActionPagamentoAgente extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACPGA:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			request.setAttribute("ls_pagamentoagente", ModelPagamentoAgente.getInstance().getPagamentosAgente());
						
			fw.setPath("/pagamentoAgenteLista.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
		
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0002";
		
		ActionForward fw = new ActionForward();
		FormPagamentoAgente formPagamentoAgente = (FormPagamentoAgente)form;
		BeanEntidade usuario = (BeanEntidade) request.getSession().getAttribute("usuario");
		try {
			
			
			if (usuario != null){
				
				BeanPagamentoAgente beanPagamentoAgente = new BeanPagamentoAgente();
				BeanUtils.copyProperties(beanPagamentoAgente, formPagamentoAgente);
				
				ModelPagamentoAgente.getInstance().update(beanPagamentoAgente);
				
			}
			
			request.getSession().removeAttribute("formPagamentoAgente");
			
			fw.setPath("/actionPagamentoAgente.do?m=dados&pgancodg="+formPagamentoAgente.getPgancodg());
								
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
		FormPagamentoAgente formPagamentoAgente = (FormPagamentoAgente)form;
		
		try {			
			String pgancodg = request.getParameter("pgancodg");
			
			BeanPagamentoAgente beanPagamentoAgente = ModelPagamentoAgente.getInstance().getPagamentoAgente(Integer.parseInt(pgancodg));
			
			BeanUtils.copyProperties(formPagamentoAgente, beanPagamentoAgente);			
			
			fw.setPath("/pagamentoAgenteEditar.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward dados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0006";
		ActionForward fw = new ActionForward();
		
		try {
			String pgancodg = request.getParameter("pgancodg");
			
			BeanPagamentoAgente pagamentoAgente = ModelPagamentoAgente.getInstance().getPagamentoAgente(Integer.parseInt(pgancodg));
			request.setAttribute("pagamentoagente", pagamentoAgente);
			
			request.setAttribute("ls_contacorrente", ModelContaCorrente.getInstance().getContaCorrentesDaEntidade(
					Integer.parseInt(pagamentoAgente.getPgancgan())));
			
			fw.setPath("/pagamentoAgenteDados.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward confirmaAgendamento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormPagamentoAgente formPagamentoAgente = (FormPagamentoAgente)form;
		try {					
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formPagamentoAgente.getPgaydesc()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Valor de desconto Inválido."));
			}		
			if (Float.parseFloat(Utils.converteFloatBR(formPagamentoAgente.getPgaydesc())) >= Float.parseFloat(Utils.converteFloatBR(formPagamentoAgente.getPgayvalr()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Valor de desconto não pode ser maior ou igual que o pagamento."));
			}
			
			if (erros.isEmpty() && usuario != null){
				
				BeanPagamentoAgente beanPagamentoAgente = new BeanPagamentoAgente();
				BeanUtils.copyProperties(beanPagamentoAgente, formPagamentoAgente);
				beanPagamentoAgente.setPgancgus(usuario.getEnncodg());
				
				ModelPagamentoAgente.getInstance().confirmaPagamento(beanPagamentoAgente);				
				fw = lista(mapping, formPagamentoAgente, request, response);
				
			}else{
				saveErrors(request, erros);
				fw.setPath("/actionPagamentoAgente.do?m=dados&pgancodg="+formPagamentoAgente.getPgancodg());
			}
			
			
			request.getSession().removeAttribute("formPagamentoAgente");
			
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
		final String METODO = "0004";
		ActionForward fw = new ActionForward();
		
		try {			
			
			fw.setPath("/");
			
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
		final String METODO = "0005";
		ActionForward fw = new ActionForward();
		
		try {			
			
			fw.setPath("/");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward importa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0008";
		ActionForward fw = new ActionForward();
		
		try {			
			String pgadbase = request.getParameter("pgadbase");
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (pgadbase != null && usuario != null){
				ModelPagamentoAgente.getInstance().importa(Utils.strBRToDate(pgadbase));
			}
			
			fw = lista(mapping, form, request, response);
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
