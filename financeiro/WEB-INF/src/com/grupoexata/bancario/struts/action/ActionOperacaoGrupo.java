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

import com.grupoexata.bancario.dao.ModelGrupo;
import com.grupoexata.bancario.dao.ModelOperacao;
import com.grupoexata.bancario.dao.ModelOperacaoGrupo;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.struts.bean.BeanOperacao;
import com.grupoexata.bancario.struts.bean.BeanOperacaoGrupo;
import com.grupoexata.bancario.struts.form.FormOperacao;
import com.grupoexata.bancario.struts.form.FormOperacaoGrupo;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionOperacaoGrupo extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACOP:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			String grncodg = request.getParameter("grncodg");
			
			request.setAttribute("grupo", ModelGrupo.getInstance().getGrupo(Integer.parseInt(grncodg)));
			request.setAttribute("ls_operacaogrupo", ModelOperacaoGrupo.getInstance().getOperacoesDoGrupo(Integer.parseInt(grncodg)));
			
			fw.setPath("/operacaoGrupoLista.do");
			
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
		FormOperacaoGrupo formOperacao = (FormOperacaoGrupo)form;
		ActionMessages erros = new ActionMessages();
		try {			
			
			
			if (!ValidaObjeto.validaString(formOperacao.getGocdcgr())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Link é obrigatório."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/operacaoGrupoNovo.do");
			}else{
			
				BeanOperacaoGrupo operacao = new BeanOperacaoGrupo();
				BeanUtils.copyProperties(operacao, formOperacao);
				
				ModelOperacaoGrupo.getInstance().update(operacao);
				
				fw = lista(mapping, form, request, response);
				
				request.getSession().removeAttribute("formOperacaoGrupo");
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
			request.getSession().removeAttribute("formOperacaoGrupo");
			
			fw.setPath("/operacaoGrupoNovo.do");
			
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
		FormOperacaoGrupo formOperacaoGrupo = (FormOperacaoGrupo)form;
		ActionMessages erros = new ActionMessages();
		try {
			
			
			if (!ValidaObjeto.validaString(formOperacaoGrupo.getGoncggr())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Link é obrigatório."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/operacaoGrupoNovo.do");
			}else{
				BeanOperacaoGrupo operacaoGrupo = new BeanOperacaoGrupo();
				BeanUtils.copyProperties(operacaoGrupo, formOperacaoGrupo);
				
				ModelOperacaoGrupo.getInstance().inserir(operacaoGrupo);
				
				fw = lista(mapping, form, request, response);
				
				request.getSession().removeAttribute("formOperacaoGrupo");
				
			}
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0002";
		
		ActionForward fw = new ActionForward();
		try {			
			
			String goncodg = request.getParameter("goncodg");
			String grncodg = request.getParameter("grncodg");
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (usuario != null) {
				ModelOperacaoGrupo.getInstance().delete(Integer.parseInt(goncodg));
			}
			
			fw.setPath("/actionOperacaoGrupo.do?m=lista&grncodg="+ grncodg);
								
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;
	}
	

}
