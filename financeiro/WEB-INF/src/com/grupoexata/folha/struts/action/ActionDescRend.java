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

import com.grupoexata.folha.bean.BeanDescRend;
import com.grupoexata.folha.dao.ModelDescRend;
import com.grupoexata.folha.struts.form.FormDescRend;

public class ActionDescRend extends DispatchAction {

	private final static String ERRO_CLASS = "ACDER:";
	private ModelDescRend md = new ModelDescRend();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listadesc_rend", md.list());
			fw.setPath("/desc_rendLista.do");
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
			request.getSession().removeAttribute("formDescRend");
			fw.setPath("/desc_rendNovo.do");
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
		FormDescRend  formdesc_rend = (FormDescRend)form;
		//validar bean
		formdesc_rend.validarForm(erros);
		if (!erros.isEmpty()){
			fw.setPath("/desc_rendNovo.do");
			saveErrors(request, erros);
		}else{
			try {
				BeanDescRend desc_rend = new BeanDescRend();
				BeanUtils.copyProperties(desc_rend,  formdesc_rend);
				md.add(desc_rend);
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
			FormDescRend formdesc_rend = (FormDescRend)form;
			BeanDescRend desc_rend = new BeanDescRend();
			desc_rend.setDerncodg(request.getParameter("derncodg"));
			desc_rend = md.get(desc_rend);
			BeanUtils.copyProperties(formdesc_rend, desc_rend);
			fw.setPath("/desc_rendEditar.do");
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
		FormDescRend  formdesc_rend = (FormDescRend)form;
		//validar bean
		formdesc_rend.validarForm(erros);
		if (!erros.isEmpty()){
			fw.setPath("/desc_rendEditar.do");
			saveErrors(request, erros);
		}else{
			try {
				BeanDescRend desc_rend = new BeanDescRend();
				BeanUtils.copyProperties(desc_rend,  formdesc_rend);
				md.set(desc_rend);
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

	public ActionForward opcao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0006";
		ActionForward fw = new ActionForward();

		try {
			BeanDescRend desc_rend = new BeanDescRend();
			desc_rend.setDerncodg(request.getParameter("derncodg"));
			request.getSession().setAttribute("desc_rend", md.get(desc_rend));
			fw.setPath("/desc_rendOpcao.do");
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
				List<BeanDescRend> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "DescRend n�o encontrado!"));
				} else {
					request.setAttribute("listaPesqdesc_rend", list);
				}
			}
			fw.setPath("/desc_rendPesquisa.do");
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
