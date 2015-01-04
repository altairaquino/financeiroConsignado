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

import com.grupoexata.folha.bean.BeanSubgrupoprincipal;
import com.grupoexata.folha.dao.ModelSubgrupoprincipal;
import com.grupoexata.folha.struts.form.FormSubgrupoprincipal;
import com.grupoexata.folha.util.Model;

public class ActionSubgrupoprincipal extends DispatchAction {

	private final static String ERRO_CLASS = "ACSGP:";
	private Model<BeanSubgrupoprincipal> md = new ModelSubgrupoprincipal();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listasubgrupoprincipal", md.list());
			fw.setPath("/subgrupoprincipalLista.do");
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
			request.getSession().removeAttribute("formSubgrupoprincipal");
			fw.setPath("/subgrupoprincipalNovo.do");
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
		FormSubgrupoprincipal  formsubgrupoprincipal = (FormSubgrupoprincipal)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/subgrupoprincipalNovo.do");
		}else{
			try {
				BeanSubgrupoprincipal subgrupoprincipal = new BeanSubgrupoprincipal();
				BeanUtils.copyProperties(subgrupoprincipal,  formsubgrupoprincipal);
				md.add(subgrupoprincipal);
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
			FormSubgrupoprincipal formsubgrupoprincipal = (FormSubgrupoprincipal)form;
			BeanSubgrupoprincipal subgrupoprincipal = new BeanSubgrupoprincipal();
			BeanUtils.copyProperties(formsubgrupoprincipal, subgrupoprincipal);
			fw.setPath("/subgrupoprincipalEditar.do");
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
		FormSubgrupoprincipal  formsubgrupoprincipal = (FormSubgrupoprincipal)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/subgrupoprincipalEditar.do");
		}else{
			try {
				BeanSubgrupoprincipal subgrupoprincipal = new BeanSubgrupoprincipal();
				BeanUtils.copyProperties(subgrupoprincipal,  formsubgrupoprincipal);
				md.set(subgrupoprincipal);
				fw.setPath("/actionSubgrupoprincipal.do?m=dados&sgpncodg=" + subgrupoprincipal.getSgpncodg() + "");
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
			BeanSubgrupoprincipal subgrupoprincipal = new BeanSubgrupoprincipal();
			subgrupoprincipal.setSgpncodg(request.getParameter("sgpncodg"));
			request.setAttribute("subgrupoprincipal", md.get(subgrupoprincipal));
			fw.setPath("/subgrupoprincipalDados.do");
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
				List<BeanSubgrupoprincipal> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "Subgrupoprincipal n�o encontrado!"));
				} else {
					request.setAttribute("listaPesqsubgrupoprincipal", list);
				}
			}
			fw.setPath("/subgrupoprincipalPesquisa.do");
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
