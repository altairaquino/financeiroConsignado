package com.grupoexata.folha.struts.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.utils.GeraRelatorio;
import com.grupoexata.folha.bean.BeanPagtoajcust;
import com.grupoexata.folha.dao.ModelFiltro;
import com.grupoexata.folha.dao.ModelPagtoajcust;
import com.grupoexata.folha.dao.ModelTipoajcust;
import com.grupoexata.folha.struts.form.FormPagtoajcust;

public class ActionPagtoajcust extends DispatchAction {

	private final static String ERRO_CLASS = "ACPAC:";
	private ModelPagtoajcust md = new ModelPagtoajcust();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listaPagtoajcust", md.list());
			fw.setPath("/pagtoajcustLista.do");
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
			request.getSession().removeAttribute("formPagtoajcust");
			request.getSession().setAttribute("listaTipoajcust", new ModelTipoajcust().list());
			request.getSession().setAttribute("listaFiltro", new ModelFiltro().list());
			fw.setPath("/pagtoajcustNovo.do");
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
		FormPagtoajcust  formPagtoajcust = (FormPagtoajcust)form;
		//validar bean
		formPagtoajcust.validarForm(erros);
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/pagtoajcustNovo.do");
		}else{
			try {
				BeanPagtoajcust pagtoajcust = new BeanPagtoajcust();
				BeanUtils.copyProperties(pagtoajcust,  formPagtoajcust);
				md.add(pagtoajcust);
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
			FormPagtoajcust formPagtoajcust = (FormPagtoajcust)form;
			BeanPagtoajcust pagtoajcust = new BeanPagtoajcust();
			pagtoajcust.setPacncodg(request.getParameter("pacncodg"));
			pagtoajcust = md.get(pagtoajcust);
			BeanUtils.copyProperties(formPagtoajcust, pagtoajcust);
			request.getSession().setAttribute("listaTipoajcust", new ModelTipoajcust().list());
			request.getSession().setAttribute("listaFiltro", new ModelFiltro().list());
			fw.setPath("/pagtoajcustEditar.do");
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
		FormPagtoajcust  formPagtoajcust = (FormPagtoajcust)form;
		//validar bean
		formPagtoajcust.validarForm(erros);
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/pagtoajcustEditar.do");
		}else{
			try {
				BeanPagtoajcust pagtoajcust = new BeanPagtoajcust();
				BeanUtils.copyProperties(pagtoajcust,  formPagtoajcust);
				md.set(pagtoajcust);
				fw.setPath("/actionPagtoajcust.do?m=opcao&pacncodg=" + pagtoajcust.getPacncodg());
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
			BeanPagtoajcust pagtoajcust = new BeanPagtoajcust();
			pagtoajcust.setPacncodg(request.getParameter("pacncodg"));
			pagtoajcust = md.get(pagtoajcust);
			request.getSession().setAttribute("pagtoajcust", pagtoajcust);
			fw.setPath("/pagtoajcustOpcao.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}finally{
			//md.closeConnection();
		}
		return fw;
	}
	public ActionForward montarRelatorio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();

		//ActionMessages erros = new ActionMessages();	
		try {
			fw.setPath("/pagtoajcustRelatorios.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}finally{
			//md.closeConnection();
		}
		return fw;
	}
	
	public ActionForward relatorio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0008";
		ActionForward fw = new ActionForward();
		String pacncodg = request.getParameter("pacncodg");
		try {
			Map<Object,Object> map = new HashMap<Object, Object>();
			map.put("PCENCGPAC", Integer.parseInt(pacncodg));
			String report_name = null;
			String tipo = request.getParameter("tipo");
			tipo = tipo == null ? "0" : tipo;
			switch (tipo.charAt(0)) {
			case '1':
				report_name = "relat_ajuda_custo scc";
				break;
			case '2':
				report_name = "relat_ajuda_custo_ebanc";
				break;
			default:
				report_name = "relat_ajuda_custo";
			}
			map.put("REPORT_NAME", report_name);
			GeraRelatorio.geracao(request, response, map, false);
			fw = null;
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward regerar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0005";
		ActionForward fw = new ActionForward();
		try {
			BeanPagtoajcust pagtoajcust = new BeanPagtoajcust();
			pagtoajcust.setPacncodg(request.getParameter("pacncodg"));
			md.set(md.get(pagtoajcust));
			request.setAttribute("msg", "Ajuda de custo dos funcionários regerada com sucesso!");
			fw.setPath("/pagtoajcustOpcao.do");
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
