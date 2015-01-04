package com.grupoexata.folha.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.dao.ModelEmpresa;
import com.grupoexata.folha.bean.BeanTipoajcust;
import com.grupoexata.folha.dao.ModelTipoajcust;
import com.grupoexata.folha.struts.form.FormTipoajcust;

public class ActionTipoajcust extends DispatchAction {

	private final static String ERRO_CLASS = "ACTAC:";
	private ModelTipoajcust md = new ModelTipoajcust();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listaTipoajcust", md.list());
			fw.setPath("/tipoajcustLista.do");
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
			request.getSession().removeAttribute("formTipoajcust");
			request.getSession().setAttribute("empresas", ModelEmpresa.getInstance().list());
			fw.setPath("/tipoajcustNovo.do");
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
		FormTipoajcust  formTipoajcust = (FormTipoajcust)form;
		//validar bean
		formTipoajcust.validarForm(erros);
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/tipoajcustNovo.do");
		}else{
			try {
				BeanTipoajcust Tipoajcust = new BeanTipoajcust();
				BeanUtils.copyProperties(Tipoajcust,  formTipoajcust);
				md.add(Tipoajcust);
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
			FormTipoajcust formTipoajcust = (FormTipoajcust)form;
			BeanTipoajcust Tipoajcust = new BeanTipoajcust();
			Tipoajcust.setTacncodg(request.getParameter("tacncodg"));
			Tipoajcust = md.get(Tipoajcust);
			BeanUtils.copyProperties(formTipoajcust, Tipoajcust);
			fw.setPath("/tipoajcustEditar.do");
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
		FormTipoajcust  formTipoajcust = (FormTipoajcust)form;
		//validar bean
		formTipoajcust.validarForm(erros);
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/tipoajcustEditar.do");
		}else{
			try {
				BeanTipoajcust Tipoajcust = new BeanTipoajcust();
				BeanUtils.copyProperties(Tipoajcust,  formTipoajcust);
				md.set(Tipoajcust);
				fw = lista(mapping, formTipoajcust, request, response);
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
}
