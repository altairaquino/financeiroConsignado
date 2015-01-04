package com.grupoexata.financeiro.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.financeiro.dao.ModelPlanoConta;
import com.grupoexata.financeiro.struts.bean.BeanPlanoConta;
import com.grupoexata.financeiro.struts.form.FormPlanoConta;

public class ActionPlanoConta extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACPLC:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			String plcncodg = request.getParameter("plcncodg");
			
			if (plcncodg == null){
				request.setAttribute("ls_planoconta", ModelPlanoConta.getInstance().getPlanosContaPai());
			}else{
				request.setAttribute("ls_planoconta", ModelPlanoConta.getInstance().getPlanosContaFilho(
						Integer.parseInt(plcncodg)));				
				BeanPlanoConta plc = ModelPlanoConta.getInstance().getPlanoConta(Integer.parseInt(plcncodg));
				request.setAttribute("pai", plc.getPlcncodg());
				request.setAttribute("avo", plc.getPlcnpai());
			}
			
			fw.setPath("/planoContaLista.do");
			
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
		
			
		try {
			
			fw.setPath("/");
								
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
		
		try {			
			
			fw.setPath("/");
			
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
		FormPlanoConta formPlanoConta = (FormPlanoConta)form;
		try {			
			
			String plcnpai = request.getParameter("plcnpai");
			
			BeanPlanoConta contaPai = ModelPlanoConta.getInstance().getPlanoConta(Integer.parseInt(plcnpai));
			
			formPlanoConta.setPlcnpai(contaPai.getPlcncodg());
			formPlanoConta.setPlcnempr(contaPai.getPlcnempr());
			formPlanoConta.setPlccpai(contaPai.getPlccdesc());
			formPlanoConta.setPlcntipo(contaPai.getPlcntipo());
			formPlanoConta.setPlccdesc("");
			
			fw.setPath("/planoContaNovo.do");
			
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
		FormPlanoConta formPlanoConta = (FormPlanoConta)form;
		ActionMessages erros = new ActionMessages();
		try {			
			
			if (!ValidaObjeto.validaString(formPlanoConta.getPlccdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nome do Plano de Conta é obrigatório."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/planoContaNovo.do");
			}else{
				
				BeanPlanoConta beanPlanoConta = new BeanPlanoConta();
				BeanUtils.copyProperties(beanPlanoConta, formPlanoConta);
				
				ModelPlanoConta.getInstance().inserir(beanPlanoConta);
				
				request.getSession().removeAttribute("formPlanoConta");
				
				fw.setPath("/actionPlanoConta.do?m=lista&plcncodg="+formPlanoConta.getPlcnpai());
				
			}			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward pesquisaWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0006";
	
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormPlanoConta formPlanoConta = (FormPlanoConta) form;
		
		if (!ValidaObjeto.validaString(formPlanoConta.getPlccdesc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome da descrição."));
		}else{
			if (formPlanoConta.getPlccdesc().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome da descrição."));
			}
		}
		
		try {
			if (erros.isEmpty()) {
				List<BeanPlanoConta> planos = ModelPlanoConta.getInstance()
						.pesquisa(formPlanoConta.getPlccdesc());
				
				if (planos.isEmpty()) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nenhuma descrição encontrada!"));
				} else {
					request.setAttribute("ls_planoconta", planos);
				}
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formPlanoConta");
		
		fw.setPath("/planoContaPesquisaCaixaWindow.do");
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward listaCaixaWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();
		
		try {
			String plcntipo = request.getParameter("plcntipo"); 
			
			request.setAttribute("ls_planoconta", ModelPlanoConta.getInstance().getPlanosContaPorTipo(Integer.parseInt(plcntipo)));
			
			fw.setPath("/planoContaPesquisaCaixaWindow.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
