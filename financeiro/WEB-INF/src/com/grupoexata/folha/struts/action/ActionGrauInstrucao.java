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

import com.grupoexata.folha.bean.BeanGrauInstrucao;
import com.grupoexata.folha.dao.ModelGrauInstrucao;
import com.grupoexata.folha.struts.form.FormGrauInstrucao;
import com.grupoexata.folha.util.Model;

public class ActionGrauInstrucao extends DispatchAction {

	private final static String ERRO_CLASS = "ACGIN:";
	private Model<BeanGrauInstrucao> md = new ModelGrauInstrucao();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listagrau_instrucao", md.list());
			fw.setPath("/grau_instrucaoLista.do");
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
			request.getSession().removeAttribute("formGrauInstrucao");
			fw.setPath("/grau_instrucaoNovo.do");
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
		FormGrauInstrucao  formgrau_instrucao = (FormGrauInstrucao)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/grau_instrucaoNovo.do");
		}else{
			try {
				BeanGrauInstrucao grau_instrucao = new BeanGrauInstrucao();
				BeanUtils.copyProperties(grau_instrucao,  formgrau_instrucao);
				md.add(grau_instrucao);
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
			FormGrauInstrucao formgrau_instrucao = (FormGrauInstrucao)form;
			BeanGrauInstrucao grau_instrucao = new BeanGrauInstrucao();
			BeanUtils.copyProperties(formgrau_instrucao, grau_instrucao);
			fw.setPath("/grau_instrucaoEditar.do");
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
		FormGrauInstrucao  formgrau_instrucao = (FormGrauInstrucao)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/grau_instrucaoEditar.do");
		}else{
			try {
				BeanGrauInstrucao grau_instrucao = new BeanGrauInstrucao();
				BeanUtils.copyProperties(grau_instrucao,  formgrau_instrucao);
				md.set(grau_instrucao);
				fw.setPath("/actionGrauInstrucao.do?m=dados&ginncodg=" + grau_instrucao.getGinncodg() + "");
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
			BeanGrauInstrucao grau_instrucao = new BeanGrauInstrucao();
			grau_instrucao.setGinncodg(request.getParameter("ginncodg"));
			request.setAttribute("grau_instrucao", md.get(grau_instrucao));
			fw.setPath("/grau_instrucaoDados.do");
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
				List<BeanGrauInstrucao> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "GrauInstrucao n�o encontrado!"));
				} else {
					request.setAttribute("listaPesqgrau_instrucao", list);
				}
			}
			fw.setPath("/grau_instrucaoPesquisa.do");
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
