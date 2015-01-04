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

import com.grupoexata.folha.bean.BeanTipoferiado;
import com.grupoexata.folha.dao.ModelTipoferiado;
import com.grupoexata.folha.struts.form.FormTipoferiado;
import com.grupoexata.folha.util.Model;

public class ActionTipoferiado extends DispatchAction {

	private final static String ERRO_CLASS = "ACTFE:";
	private Model<BeanTipoferiado> md = new ModelTipoferiado();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listatipoferiado", md.list());
			fw.setPath("/tipoferiadoLista.do");
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
			request.getSession().removeAttribute("formTipoferiado");
			fw.setPath("/tipoferiadoNovo.do");
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
		FormTipoferiado  formtipoferiado = (FormTipoferiado)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/tipoferiadoNovo.do");
		}else{
			try {
				BeanTipoferiado tipoferiado = new BeanTipoferiado();
				BeanUtils.copyProperties(tipoferiado,  formtipoferiado);
				md.add(tipoferiado);
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
			FormTipoferiado formtipoferiado = (FormTipoferiado)form;
			BeanTipoferiado tipoferiado = new BeanTipoferiado();
			BeanUtils.copyProperties(formtipoferiado, tipoferiado);
			fw.setPath("/tipoferiadoEditar.do");
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
		FormTipoferiado  formtipoferiado = (FormTipoferiado)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/tipoferiadoEditar.do");
		}else{
			try {
				BeanTipoferiado tipoferiado = new BeanTipoferiado();
				BeanUtils.copyProperties(tipoferiado,  formtipoferiado);
				md.set(tipoferiado);
				fw.setPath("/actionTipoferiado.do?m=dados&tfencodg=" + tipoferiado.getTfencodg() + "");
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
			BeanTipoferiado tipoferiado = new BeanTipoferiado();
			tipoferiado.setTfencodg(request.getParameter("tfencodg"));
			request.setAttribute("tipoferiado", md.get(tipoferiado));
			fw.setPath("/tipoferiadoDados.do");
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
				List<BeanTipoferiado> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "Tipoferiado n�o encontrado!"));
				} else {
					request.setAttribute("listaPesqtipoferiado", list);
				}
			}
			fw.setPath("/tipoferiadoPesquisa.do");
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
