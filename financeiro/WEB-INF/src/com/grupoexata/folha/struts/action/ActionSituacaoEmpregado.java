package com.grupoexata.folha.struts.action;

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

import com.grupoexata.folha.bean.BeanSituacaoEmpregado;
import com.grupoexata.folha.dao.ModelSituacaoEmpregado;
import com.grupoexata.folha.struts.form.FormSituacaoEmpregado;
import com.grupoexata.folha.util.Model;

public class ActionSituacaoEmpregado extends DispatchAction {

	private final static String ERRO_CLASS = "ACSTE:";
	private Model<BeanSituacaoEmpregado> md = new ModelSituacaoEmpregado();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listasituacao_empregado", md.list());
			fw.setPath("/situacao_empregadoLista.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}finally{
			//md.closeConnection();
		}
		return fw;
	}

	public ActionForward novo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0002";
		ActionForward fw = new ActionForward();

		try {
			request.getSession().removeAttribute("formSituacaoEmpregado");
			fw.setPath("/situacao_empregadoNovo.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}finally{
			//md.closeConnection();
		}
		return fw;
	}

	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0003";
		ActionForward fw = new ActionForward();

		ActionMessages erros = new ActionMessages();
		FormSituacaoEmpregado  formsituacao_empregado = (FormSituacaoEmpregado)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/situacao_empregadoNovo.do");
		}else{
			try {
				BeanSituacaoEmpregado situacao_empregado = new BeanSituacaoEmpregado();
				BeanUtils.copyProperties(situacao_empregado,  formsituacao_empregado);
				md.add(situacao_empregado);
				fw = lista(mapping, form, request, response);
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}finally{
				//md.closeConnection();
			}
		}
		return fw;
	}

	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0004";
		ActionForward fw = new ActionForward();

		try {
			FormSituacaoEmpregado formsituacao_empregado = (FormSituacaoEmpregado)form;
			BeanSituacaoEmpregado situacao_empregado = new BeanSituacaoEmpregado();
			BeanUtils.copyProperties(formsituacao_empregado, situacao_empregado);
			fw.setPath("/situacao_empregadoEditar.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}finally{
			//md.closeConnection();
		}
		return fw;
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0005";
		ActionForward fw = new ActionForward();

		ActionMessages erros = new ActionMessages();
		FormSituacaoEmpregado  formsituacao_empregado = (FormSituacaoEmpregado)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/situacao_empregadoEditar.do");
		}else{
			try {
				BeanSituacaoEmpregado situacao_empregado = new BeanSituacaoEmpregado();
				BeanUtils.copyProperties(situacao_empregado,  formsituacao_empregado);
				md.set(situacao_empregado);
				fw.setPath("/actionSituacaoEmpregado.do?m=dados&stencodg=" + situacao_empregado.getStencodg() + "");
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}finally{
				//md.closeConnection();
			}
		}
		return fw;
	}

	public ActionForward dados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0006";
		ActionForward fw = new ActionForward();

		try {
			BeanSituacaoEmpregado situacao_empregado = new BeanSituacaoEmpregado();
			situacao_empregado.setStencodg(request.getParameter("stencodg"));
			request.setAttribute("situacao_empregado", md.get(situacao_empregado));
			fw.setPath("/situacao_empregadoDados.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS + METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}finally{
			//md.closeConnection();
		}
		return fw;
	}

	public ActionForward pesquisa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();

		try {
			ActionMessages messages = new ActionMessages();
			String param = request.getParameter("param");
			if (param == null || param.length() < 4) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.detail", "Min. de 4 letras ou n�meros"));
			} else {
				List<BeanSituacaoEmpregado> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "SituacaoEmpregado n�o encontrado!"));
				} else {
					request.setAttribute("listaPesqsituacao_empregado", list);
				}
			}
			fw.setPath("/situacao_empregadoPesquisa.do");
			if (!messages.isEmpty()) {
				saveErrors(request, messages);
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}finally{
			//md.closeConnection();
		}
		return fw;
	}


}
