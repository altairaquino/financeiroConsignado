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

import com.grupoexata.folha.bean.BeanFolhaEmpItem;
import com.grupoexata.folha.dao.ModelFolhaEmpItem;
import com.grupoexata.folha.struts.form.FormFolhaEmpItem;

public class ActionFolhaEmpItem extends DispatchAction {

	private final static String ERRO_CLASS = "ACFEI:";
	private ModelFolhaEmpItem md = new ModelFolhaEmpItem();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listafolha_emp_item", md.list());
			fw.setPath("/folha_emp_itemLista.do");
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
			request.getSession().removeAttribute("formFolhaEmpItem");
			fw.setPath("/folha_emp_itemNovo.do");
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
		FormFolhaEmpItem  formfolha_emp_item = (FormFolhaEmpItem)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/folha_emp_itemNovo.do");
		}else{
			try {
				BeanFolhaEmpItem folha_emp_item = new BeanFolhaEmpItem();
				BeanUtils.copyProperties(folha_emp_item,  formfolha_emp_item);
				md.add(folha_emp_item);
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
			FormFolhaEmpItem formfolha_emp_item = (FormFolhaEmpItem)form;
			BeanFolhaEmpItem folha_emp_item = new BeanFolhaEmpItem();
			BeanUtils.copyProperties(formfolha_emp_item, folha_emp_item);
			fw.setPath("/folha_emp_itemEditar.do");
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
		FormFolhaEmpItem  formfolha_emp_item = (FormFolhaEmpItem)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/folha_emp_itemEditar.do");
		}else{
			try {
				BeanFolhaEmpItem folha_emp_item = new BeanFolhaEmpItem();
				BeanUtils.copyProperties(folha_emp_item,  formfolha_emp_item);
				md.set(folha_emp_item);
				fw.setPath("/actionFolhaEmpItem.do?m=dados&feincodg=" + folha_emp_item.getFeincodg() + "");
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
			BeanFolhaEmpItem folha_emp_item = new BeanFolhaEmpItem();
			folha_emp_item.setFeincodg(request.getParameter("feincodg"));
			request.setAttribute("folha_emp_item", md.get(folha_emp_item));
			fw.setPath("/folha_emp_itemDados.do");
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
						"errors.detail", "Min. de 4 letras ou n?meros"));
			} else {
				List<BeanFolhaEmpItem> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "FolhaEmpItem n?o encontrado!"));
				} else {
					request.setAttribute("listaPesqfolha_emp_item", list);
				}
			}
			fw.setPath("/folha_emp_itemPesquisa.do");
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
