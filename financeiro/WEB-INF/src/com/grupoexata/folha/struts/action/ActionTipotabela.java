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

import com.grupoexata.folha.bean.BeanTipotabela;
import com.grupoexata.folha.dao.ModelTipotabela;
import com.grupoexata.folha.struts.form.FormTipotabela;

public class ActionTipotabela extends DispatchAction {

	private final static String ERRO_CLASS = "ACTTB:";
	private ModelTipotabela md = new ModelTipotabela();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listatipotabela", md.list());
			fw.setPath("/tipotabelaLista.do");
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
			request.getSession().removeAttribute("formTipotabela");
			fw.setPath("/tipotabelaNovo.do");
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
		FormTipotabela  formtipotabela = (FormTipotabela)form;
		//validar bean
		formtipotabela.validarForm(erros);
		if (!erros.isEmpty()){
			fw.setPath("/tipotabelaNovo.do");
			saveErrors(request, erros);
		}else{
			try {
				BeanTipotabela tipotabela = new BeanTipotabela();
				BeanUtils.copyProperties(tipotabela,  formtipotabela);
				md.add(tipotabela);
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
			FormTipotabela formtipotabela = (FormTipotabela)form;
			BeanTipotabela tipotabela = new BeanTipotabela();
			tipotabela.setTtbncodg(request.getParameter("ttbncodg"));
			tipotabela = md.get(tipotabela);
			BeanUtils.copyProperties(formtipotabela, tipotabela);
			fw.setPath("/tipotabelaEditar.do");
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
		FormTipotabela  formtipotabela = (FormTipotabela)form;
		//validar bean
		formtipotabela.validarForm(erros);
		if (!erros.isEmpty()){
			fw.setPath("/tipotabelaEditar.do");
			saveErrors(request, erros);
		}else{
			try {
				BeanTipotabela tipotabela = new BeanTipotabela();
				BeanUtils.copyProperties(tipotabela,  formtipotabela);
				md.set(tipotabela);
				//fw.setPath("/actionTipotabela.do?m=dados&ttbncodg=" + tipotabela.getTtbncodg() + "");
				fw = lista(mapping, formtipotabela, request, response);
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

	public ActionForward opcao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0006";
		ActionForward fw = new ActionForward();

		try {
			BeanTipotabela tipotabela = new BeanTipotabela();
			tipotabela.setTtbncodg(request.getParameter("ttbncodg"));
			request.getSession().setAttribute("tipotabela", md.get(tipotabela));
			fw.setPath("/tipotabelaOpcao.do");
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
						"errors.detail", "Min. de 4 letras ou números"));
			} else {
				List<BeanTipotabela> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "Tipotabela não encontrado!"));
				} else {
					request.setAttribute("listaPesqtipotabela", list);
				}
			}
			fw.setPath("/tipotabelaPesquisa.do");
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
