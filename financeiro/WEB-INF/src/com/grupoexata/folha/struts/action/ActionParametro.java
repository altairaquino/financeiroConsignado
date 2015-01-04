package com.grupoexata.folha.struts.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.utils.GeraRelatorio;
import com.grupoexata.folha.bean.BeanParametro;
import com.grupoexata.folha.dao.ModelParametro;
import com.grupoexata.folha.struts.form.FormParametro;

public class ActionParametro extends DispatchAction {

	private final static String ERRO_CLASS = "ACPAR:";
	private ModelParametro md = new ModelParametro();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listaparametro", md.list());
			fw.setPath("/parametroLista.do");
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
			request.getSession().removeAttribute("formParametro");
			fw.setPath("/parametroNovo.do");
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
		FormParametro  formparametro = (FormParametro)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/parametroNovo.do");
		}else{
			try {
				BeanParametro parametro = new BeanParametro();
				BeanUtils.copyProperties(parametro,  formparametro);
				md.add(parametro);
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
			FormParametro formparametro = (FormParametro)form;
			BeanParametro parametro = new BeanParametro();
			parametro =  md.get(parametro);
			BeanUtils.copyProperties(formparametro, parametro);
			fw.setPath("/parametroEditar.do");
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
		FormParametro  formparametro = (FormParametro)form;
		//validar bean
		formparametro.validarForm(erros);
		if (!erros.isEmpty()){
			fw.setPath("/parametroEditar.do");
			saveErrors(request, erros);
		}else{
			try {
				BeanParametro parametro = new BeanParametro();
				BeanUtils.copyProperties(parametro,  formparametro);
				md.set(parametro);
				request.setAttribute("msg", "Alterado com sucesso!");
				fw = editar(mapping, formparametro, request, response);
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
			BeanParametro parametro = new BeanParametro();
			parametro.setParncodg(request.getParameter("parncodg"));
			request.setAttribute("parametro", md.get(parametro));
			fw.setPath("/parametroDados.do");
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
				List<BeanParametro> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "Parametro n?o encontrado!"));
				} else {
					request.setAttribute("listaPesqparametro", list);
				}
			}
			fw.setPath("/parametroPesquisa.do");
			if (!messages.isEmpty()) {
				saveErrors(request, messages);
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		return fw;
	}
	
	public ActionForward relatorio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0008";
		
		ActionForward fw = new ActionForward();
		
					
		try {
			
			Map<Object,Object> map = new HashMap<Object, Object>();
							
			
			switch (Integer.parseInt(request.getParameter("tipo"))) {
				case 1:
					map.put("REPORT_NAME", "folha_func");
					break;
				case 2:
					map.put("REPORT_NAME", "relat_agentes");
					break;
				case 3:
					map.put("REPORT_NAME", "pendencia_fisico_agente");
					break;
				case 4:
					map.put("REPORT_NAME", "agente_credito_uf");
					break;
				case 5:
					map.put("REPORT_NAME", "func_aniversariantes");
					break;
				case 6:
					map.put("REPORT_NAME", "corretor_acompanhamento_producao");
					break;
				default:
					map.put("REPORT_NAME", "folha_func");
					break;
			}
			
			GeraRelatorio.geracao(request, response, map, false);
			
			fw = null;
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}


}
