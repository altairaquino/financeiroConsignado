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

import com.grupoexata.bancario.dao.ModelEmpresa;
import com.grupoexata.bancario.utils.GeraRelatorio;
import com.grupoexata.folha.bean.BeanFolha;
import com.grupoexata.folha.dao.ModelFolha;
import com.grupoexata.folha.struts.form.FormFolha;

public class ActionFolha extends DispatchAction {

	private final static String ERRO_CLASS = "ACFOL:";
	private ModelFolha md = new ModelFolha();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listafolha", md.list());
			fw.setPath("/folhaLista.do");
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
			request.getSession().removeAttribute("formFolha");
			request.getSession().setAttribute("empresas", ModelEmpresa.getInstance().list());
			fw.setPath("/folhaNovo.do");
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
		FormFolha  formfolha = (FormFolha)form;
		//validar bean
		formfolha.validarForm(erros);
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/folhaNovo.do");
		}else{
			try {
				BeanFolha folha = new BeanFolha();
				BeanUtils.copyProperties(folha,  formfolha);
				md.add(folha);
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
			FormFolha formfolha = (FormFolha)form;
			BeanFolha folha = new BeanFolha();
			folha.setFolncodg(request.getParameter("folncodg"));
			folha = md.get(folha);
			BeanUtils.copyProperties(formfolha, folha);
			if(request.getSession().getAttribute("empresas") == null){
				request.getSession().setAttribute("empresas", ModelEmpresa.getInstance().list());
			}
			fw.setPath("/folhaEditar.do");
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
		FormFolha  formfolha = (FormFolha)form;
		//validar bean
		formfolha.validarForm(erros);
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/folhaEditar.do");
		}else{
			try {
				BeanFolha folha = new BeanFolha();
				BeanUtils.copyProperties(folha,  formfolha);
				md.set(folha);
				//fw = lista(mapping, formfolha, request, response);
				fw.setPath("/actionFolha.do?m=opcao&folncodg=" + folha.getFolncodg());
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
			BeanFolha folha = new BeanFolha();
			folha.setFolncodg(request.getParameter("folncodg"));
			request.getSession().setAttribute("folha", md.get(folha));
			fw.setPath("/folhaDados.do");
			//fw.setPath("/pages/folha/folha_emp/folha_empDados.jsp");
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
				List<BeanFolha> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "Folha n?o encontrado!"));
				} else {
					request.setAttribute("listaPesqfolha", list);
				}
			}
			fw.setPath("/folhaPesquisa.do");
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
	public ActionForward relatorio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0008";
		ActionForward fw = new ActionForward();
		String folncodg = request.getParameter("folncodg");
		try {
			Map<Object,Object> map = new HashMap<Object, Object>();
			map.put("FOENCGFOL", Integer.parseInt(folncodg));
			String report_name = null;
			String tipo = request.getParameter("tipo");
			tipo = tipo == null ? "0" : tipo;
			switch (tipo.charAt(0)) {
			case '1':
				report_name = "resumo_folha_centrocusto";
				break;
			
			case '2':
				report_name = "resumo_folha_centrocusto2";
				break;
			default:
				report_name = "resumo_folha";
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
	
	
	public ActionForward montarRelatorio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0009";
		ActionForward fw = new ActionForward();

		ActionMessages erros = new ActionMessages();
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/folhaNovo.do");
		}else{
			try {
				BeanFolha folha = new BeanFolha();
				folha.setFolncodg(request.getParameter("folncodg"));
				request.getSession().setAttribute("folha", md.get(folha));
				fw.setPath("/folhaRelatorios.do");
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
	
	public ActionForward regerar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0005";
		ActionForward fw = new ActionForward();
		try {
			BeanFolha folha = new BeanFolha ();
			folha.setFolncodg(request.getParameter("folncodg"));
			md.set(md.get(folha));
			request.setAttribute("msg", "Folha regerada com sucesso!");
			fw.setPath("/actionFolha.do?m=opcao&folncodg=" + folha.getFolncodg());
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
