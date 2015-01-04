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

import com.grupoexata.folha.bean.BeanDataferiado;
import com.grupoexata.folha.dao.ModelDataferiado;
import com.grupoexata.folha.struts.form.FormDataferiado;
import com.grupoexata.folha.util.Model;

public class ActionDataferiado extends DispatchAction {

	private final static String ERRO_CLASS = "ACDFE:";
	private Model<BeanDataferiado> md = new ModelDataferiado();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listadataferiado", md.list());
			fw.setPath("/dataferiadoLista.do");
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
			request.getSession().removeAttribute("formDataferiado");
			fw.setPath("/dataferiadoNovo.do");
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
		FormDataferiado  formdataferiado = (FormDataferiado)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/dataferiadoNovo.do");
		}else{
			try {
				BeanDataferiado dataferiado = new BeanDataferiado();
				BeanUtils.copyProperties(dataferiado,  formdataferiado);
				md.add(dataferiado);
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
			FormDataferiado formdataferiado = (FormDataferiado)form;
			BeanDataferiado dataferiado = new BeanDataferiado();
			BeanUtils.copyProperties(formdataferiado, dataferiado);
			fw.setPath("/dataferiadoEditar.do");
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
		FormDataferiado  formdataferiado = (FormDataferiado)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/dataferiadoEditar.do");
		}else{
			try {
				BeanDataferiado dataferiado = new BeanDataferiado();
				BeanUtils.copyProperties(dataferiado,  formdataferiado);
				md.set(dataferiado);
				fw.setPath("/actionDataferiado.do?m=dados&dfencodg=" + dataferiado.getDfencodg() + "");
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
			BeanDataferiado dataferiado = new BeanDataferiado();
			dataferiado.setDfencodg(request.getParameter("dfencodg"));
			request.setAttribute("dataferiado", md.get(dataferiado));
			fw.setPath("/dataferiadoDados.do");
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
				List<BeanDataferiado> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "Dataferiado n�o encontrado!"));
				} else {
					request.setAttribute("listaPesqdataferiado", list);
				}
			}
			fw.setPath("/dataferiadoPesquisa.do");
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
