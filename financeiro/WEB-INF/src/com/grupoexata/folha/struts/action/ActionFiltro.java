package com.grupoexata.folha.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.folha.bean.BeanFiltro;
import com.grupoexata.folha.dao.ModelFiltro;
import com.grupoexata.folha.struts.form.FormFiltro;

public class ActionFiltro extends DispatchAction {

	private final static String ERRO_CLASS = "ACFIL:";
	private ModelFiltro md = new ModelFiltro();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listaFiltro", md.list());
			fw.setPath("/filtroLista.do");
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
			request.getSession().removeAttribute("formFiltro");
			fw.setPath("/filtroNovo.do");
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
		FormFiltro  formFiltro = (FormFiltro)form;
		//validar bean
		formFiltro.validarForm(erros);
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/filtroNovo.do");
		}else{
			try {
				BeanFiltro filtro = new BeanFiltro();
				BeanUtils.copyProperties(filtro,  formFiltro);
				md.add(filtro);
				fw = lista(mapping, form, request, response);
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
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
			FormFiltro formFiltro = (FormFiltro)form;
			BeanFiltro filtro = new BeanFiltro();
			filtro.setFilncodg(request.getParameter("filncodg"));
			filtro = md.get(filtro);
			BeanUtils.copyProperties(formFiltro, filtro);
			fw.setPath("/filtroEditar.do");
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
		FormFiltro  formFiltro = (FormFiltro)form;
		//validar bean
		formFiltro.validarForm(erros);
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/filtroEditar.do");
		}else{
			try {
				BeanFiltro filtro = new BeanFiltro();
				BeanUtils.copyProperties(filtro,  formFiltro);
				md.set(filtro);
				fw = lista(mapping, formFiltro, request, response);
				fw.setPath("/actionFiltro.do?m=editar&filncodg=" + filtro.getFilncodg());
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
			BeanFiltro filtro = new BeanFiltro();
			filtro.setFilncodg(request.getParameter("filncodg"));
			request.getSession().getServletContext().setAttribute("filtro", md.get(filtro));
			fw.setPath("/filtroOpcao.do");
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
