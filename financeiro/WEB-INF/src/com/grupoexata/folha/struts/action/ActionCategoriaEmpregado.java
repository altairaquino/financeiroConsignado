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

import com.grupoexata.folha.bean.BeanCategoriaEmpregado;
import com.grupoexata.folha.dao.ModelCategoriaEmpregado;
import com.grupoexata.folha.struts.form.FormCategoriaEmpregado;
import com.grupoexata.folha.util.Model;

public class ActionCategoriaEmpregado extends DispatchAction {

	private final static String ERRO_CLASS = "ACCTE:";
	private Model<BeanCategoriaEmpregado> md = new ModelCategoriaEmpregado();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listacategoria_empregado", md.list());
			fw.setPath("/categoria_empregadoLista.do");
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
			request.getSession().removeAttribute("formCategoriaEmpregado");
			fw.setPath("/categoria_empregadoNovo.do");
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
		FormCategoriaEmpregado  formcategoria_empregado = (FormCategoriaEmpregado)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/categoria_empregadoNovo.do");
		}else{
			try {
				BeanCategoriaEmpregado categoria_empregado = new BeanCategoriaEmpregado();
				BeanUtils.copyProperties(categoria_empregado,  formcategoria_empregado);
				md.add(categoria_empregado);
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
			FormCategoriaEmpregado formcategoria_empregado = (FormCategoriaEmpregado)form;
			BeanCategoriaEmpregado categoria_empregado = new BeanCategoriaEmpregado();
			BeanUtils.copyProperties(formcategoria_empregado, categoria_empregado);
			fw.setPath("/categoria_empregadoEditar.do");
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
		FormCategoriaEmpregado  formcategoria_empregado = (FormCategoriaEmpregado)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/categoria_empregadoEditar.do");
		}else{
			try {
				BeanCategoriaEmpregado categoria_empregado = new BeanCategoriaEmpregado();
				BeanUtils.copyProperties(categoria_empregado,  formcategoria_empregado);
				md.set(categoria_empregado);
				fw.setPath("/actionCategoriaEmpregado.do?m=dados&ctencodg=" + categoria_empregado.getCtencodg() + "");
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
			BeanCategoriaEmpregado categoria_empregado = new BeanCategoriaEmpregado();
			categoria_empregado.setCtencodg(request.getParameter("ctencodg"));
			request.setAttribute("categoria_empregado", md.get(categoria_empregado));
			fw.setPath("/categoria_empregadoDados.do");
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
				List<BeanCategoriaEmpregado> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "CategoriaEmpregado n�o encontrado!"));
				} else {
					request.setAttribute("listaPesqcategoria_empregado", list);
				}
			}
			fw.setPath("/categoria_empregadoPesquisa.do");
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
