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

import com.grupoexata.folha.bean.BeanItemDescRend;
import com.grupoexata.folha.dao.ModelItemDescRend;
import com.grupoexata.folha.struts.form.FormItemDescRend;

public class ActionItemDescRend extends DispatchAction {

	private final static String ERRO_CLASS = "ACIDR:";
	private ModelItemDescRend md = new ModelItemDescRend();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listaitem_desc_rend", md.list(request.getParameter("idrncgder")));
			fw.setPath("/item_desc_rendLista.do");
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
			request.getSession().removeAttribute("formItemDescRend");
			fw.setPath("/item_desc_rendNovo.do");
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
		FormItemDescRend  formitem_desc_rend = (FormItemDescRend)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/item_desc_rendNovo.do");
		}else{
			try {
				BeanItemDescRend item_desc_rend = new BeanItemDescRend();
				BeanUtils.copyProperties(item_desc_rend,  formitem_desc_rend);
				md.add(item_desc_rend);
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
			FormItemDescRend formitem_desc_rend = (FormItemDescRend)form;
			BeanItemDescRend item_desc_rend = new BeanItemDescRend();
			BeanUtils.copyProperties(formitem_desc_rend, item_desc_rend);
			fw.setPath("/item_desc_rendEditar.do");
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
		FormItemDescRend  formitem_desc_rend = (FormItemDescRend)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/item_desc_rendEditar.do");
		}else{
			try {
				BeanItemDescRend item_desc_rend = new BeanItemDescRend();
				BeanUtils.copyProperties(item_desc_rend,  formitem_desc_rend);
				md.set(item_desc_rend);
				fw.setPath("/actionItemDescRend.do?m=dados&idrncodg=" + item_desc_rend.getIdrncodg() + "");
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
			BeanItemDescRend item_desc_rend = new BeanItemDescRend();
			item_desc_rend.setIdrncodg(request.getParameter("idrncodg"));
			request.setAttribute("item_desc_rend", md.get(item_desc_rend));
			fw.setPath("/item_desc_rendDados.do");
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
				List<BeanItemDescRend> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "ItemDescRend n�o encontrado!"));
				} else {
					request.setAttribute("listaPesqitem_desc_rend", list);
				}
			}
			fw.setPath("/item_desc_rendPesquisa.do");
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
