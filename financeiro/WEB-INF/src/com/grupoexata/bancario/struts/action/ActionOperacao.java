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

import com.grupoexata.bancario.dao.ModelOperacao;
import com.grupoexata.bancario.struts.bean.BeanOperacao;
import com.grupoexata.bancario.struts.form.FormOperacao;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionOperacao extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACOP:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			request.setAttribute("ls_operacao", ModelOperacao.getInstance().getOperacoes());
			
			request.getSession().removeAttribute("ls_operacao_pai");
			
			fw.setPath("/operacaoLista.do");
			
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
		FormOperacao formOperacao = (FormOperacao)form;
		ActionMessages erros = new ActionMessages();
		try {			
			
			if (!ValidaObjeto.validaInteiro(formOperacao.getOpncodg())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Código é obrigatório."));
			}
			
			if (!ValidaObjeto.validaString(formOperacao.getOpcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição é obrigatória."));
			}
			if (!ValidaObjeto.validaString(formOperacao.getOpchint())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Legenda é obrigatória."));
			}
			if (!ValidaObjeto.validaString(formOperacao.getOpclink())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Link é obrigatório."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/operacaoNovo.do");
			}else{
			
				BeanOperacao operacao = new BeanOperacao();
				BeanUtils.copyProperties(operacao, formOperacao);
				
				ModelOperacao.getInstance().update(operacao);
				
				fw = lista(mapping, form, request, response);
				
				request.getSession().removeAttribute("formOperacao");
				request.getSession().removeAttribute("ls_operacao_pai");
			}
								
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
		FormOperacao formOperacao = (FormOperacao)form;
		try {
			
			String opncodg = request.getParameter("opncodg"); 
			BeanOperacao beanOperacao = ModelOperacao.getInstance().getOperacao(Integer.parseInt(opncodg));
			BeanUtils.copyProperties(formOperacao, beanOperacao);
			
			request.getSession().setAttribute("ls_operacao_pai", ModelOperacao.getInstance().getOperacoesPai());
			
			fw.setPath("/operacaoEditar.do");
			
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
			request.getSession().removeAttribute("formOperacao");
			
			request.getSession().setAttribute("ls_operacao_pai", ModelOperacao.getInstance().getOperacoesPai());
			
			fw.setPath("/operacaoNovo.do");
			
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
		FormOperacao formOperacao = (FormOperacao)form;
		ActionMessages erros = new ActionMessages();
		try {
			
			if (!ValidaObjeto.validaInteiro(formOperacao.getOpncodg())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Código é obrigatório."));
			}else{
				if (ModelOperacao.getInstance().getOperacao(Integer.parseInt(formOperacao.getOpncodg())) != null){
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Operação já existente com o código informado."));
				}
			}
			
			if (!ValidaObjeto.validaString(formOperacao.getOpcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição é obrigatória."));
			}
			if (!ValidaObjeto.validaString(formOperacao.getOpchint())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Legenda é obrigatória."));
			}
			if (!ValidaObjeto.validaString(formOperacao.getOpclink())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Link é obrigatório."));
			}
			
			
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/operacaoNovo.do");
			}else{
				BeanOperacao operacao = new BeanOperacao();
				BeanUtils.copyProperties(operacao, formOperacao);
				
				ModelOperacao.getInstance().inserir(operacao);
				
				fw = lista(mapping, form, request, response);
				
				request.getSession().removeAttribute("formOperacao");
				request.getSession().removeAttribute("ls_operacao_pai");
			}
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
