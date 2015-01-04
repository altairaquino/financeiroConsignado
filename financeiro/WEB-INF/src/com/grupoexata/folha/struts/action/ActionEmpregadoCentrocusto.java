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

import com.grupoexata.bancario.dao.ModelEntidade;
import com.grupoexata.financeiro.dao.ModelCentroCusto;
import com.grupoexata.folha.bean.BeanEmpregado;
import com.grupoexata.folha.bean.BeanEmpregadoCentrocusto;
import com.grupoexata.folha.dao.ModelEmpregado;
import com.grupoexata.folha.dao.ModelEmpregadoCentrocusto;
import com.grupoexata.folha.struts.form.FormEmpregadoCentrocusto;
import com.grupoexata.folha.util.Model;

public class ActionEmpregadoCentrocusto extends DispatchAction {

	private final static String ERRO_CLASS = "ACECC:";
	private Model<BeanEmpregadoCentrocusto> md = new ModelEmpregadoCentrocusto();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			String empncodg = request.getParameter("empncodg");
			BeanEmpregado empregado = ModelEmpregado.getInstance().getBeanEmpregado(Integer.parseInt(empncodg));
			
			request.setAttribute("ls_empregadocentrocusto", ModelCentroCusto.getInstance().listCentroCustoEmp(empregado.getEmpncodg()));
			request.setAttribute("empregado", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(empregado.getEmpncgen())));
			
			request.getSession().setAttribute("funcionario",empregado);
			fw.setPath("/empregado_centrocustoLista.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	public ActionForward editarLista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			String empncodg = request.getParameter("empncodg");
			
			BeanEmpregado empregado = ModelEmpregado.getInstance().getBeanEmpregado(Integer.parseInt(empncodg));
			request.setAttribute("ls_empregadocentrocusto", ModelCentroCusto.getInstance().listCentroCustoEmp(empregado.getEmpncodg()));
			request.setAttribute("empregado", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(empregado.getEmpncgen())));
			request.getSession().setAttribute("funcionario",empregado);
			
			fw.setPath("/empregado_centrocustoEditarLista.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		return fw;
	}

	
	
	public ActionForward novo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0002";
		ActionForward fw = new ActionForward();

		try {
			
			fw.setPath("/empregado_centrocustoNovo.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		return fw;
	}

	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0003";
		ActionForward fw = new ActionForward();

		ActionMessages erros = new ActionMessages();
		FormEmpregadoCentrocusto  formempregado_centrocusto = (FormEmpregadoCentrocusto)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/empregado_centrocustoNovo.do");
		}else{
			try {
				BeanEmpregadoCentrocusto empregado_centrocusto = new BeanEmpregadoCentrocusto();
				BeanUtils.copyProperties(empregado_centrocusto,  formempregado_centrocusto);
				md.add(empregado_centrocusto);
				fw = lista(mapping, form, request, response);
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		return fw;
	}

	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0004";
		ActionForward fw = new ActionForward();
		FormEmpregadoCentrocusto formempregado_centrocusto = (FormEmpregadoCentrocusto)form;

		try {
			
			BeanEmpregadoCentrocusto empregado_centrocusto = new BeanEmpregadoCentrocusto();
			BeanUtils.copyProperties(formempregado_centrocusto, empregado_centrocusto);
			fw.setPath("/empregado_centrocustoEditar.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		return fw;
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0005";
		ActionForward fw = new ActionForward();

		ActionMessages erros = new ActionMessages();
		FormEmpregadoCentrocusto  formempregado_centrocusto = (FormEmpregadoCentrocusto)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/empregado_centrocustoEditar.do");
		}else{
			try {
				BeanEmpregadoCentrocusto empregado_centrocusto = new BeanEmpregadoCentrocusto();
				BeanUtils.copyProperties(empregado_centrocusto,  formempregado_centrocusto);
				md.set(empregado_centrocusto);
				fw.setPath("/actionEmpregadoCentrocusto.do?m=dados&eccncodg=" + empregado_centrocusto.getEccncodg() + "");
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
			BeanEmpregadoCentrocusto empregado_centrocusto = new BeanEmpregadoCentrocusto();
			empregado_centrocusto.setEccncodg(request.getParameter("eccncodg"));
			request.setAttribute("empregado_centrocusto", md.get(empregado_centrocusto));
			fw.setPath("/empregado_centrocustoDados.do");
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
				List<BeanEmpregadoCentrocusto> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "EmpregadoCentrocusto n�o encontrado!"));
				} else {
					request.setAttribute("listaPesqempregado_centrocusto", list);
				}
			}
			fw.setPath("/empregado_centrocustoPesquisa.do");
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
