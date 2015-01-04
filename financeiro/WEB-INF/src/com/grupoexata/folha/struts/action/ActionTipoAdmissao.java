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

import com.grupoexata.folha.bean.BeanTipoAdmissao;
import com.grupoexata.folha.dao.ModelTipoAdmissao;
import com.grupoexata.folha.struts.form.FormTipoAdmissao;
import com.grupoexata.folha.util.Model;

public class ActionTipoAdmissao extends DispatchAction {

	private final static String ERRO_CLASS = "ACTAD:";
	private Model<BeanTipoAdmissao> md = new ModelTipoAdmissao();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listatipo_admissao", md.list());
			fw.setPath("/tipo_admissaoLista.do");
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
			request.getSession().removeAttribute("formTipoAdmissao");
			fw.setPath("/tipo_admissaoNovo.do");
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
		FormTipoAdmissao  formtipo_admissao = (FormTipoAdmissao)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/tipo_admissaoNovo.do");
		}else{
			try {
				BeanTipoAdmissao tipo_admissao = new BeanTipoAdmissao();
				BeanUtils.copyProperties(tipo_admissao,  formtipo_admissao);
				md.add(tipo_admissao);
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
			FormTipoAdmissao formtipo_admissao = (FormTipoAdmissao)form;
			BeanTipoAdmissao tipo_admissao = new BeanTipoAdmissao();
			BeanUtils.copyProperties(formtipo_admissao, tipo_admissao);
			fw.setPath("/tipo_admissaoEditar.do");
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
		FormTipoAdmissao  formtipo_admissao = (FormTipoAdmissao)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/tipo_admissaoEditar.do");
		}else{
			try {
				BeanTipoAdmissao tipo_admissao = new BeanTipoAdmissao();
				BeanUtils.copyProperties(tipo_admissao,  formtipo_admissao);
				md.set(tipo_admissao);
				fw.setPath("/actionTipoAdmissao.do?m=dados&tadncodg=" + tipo_admissao.getTadncodg() + "");
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
			BeanTipoAdmissao tipo_admissao = new BeanTipoAdmissao();
			tipo_admissao.setTadncodg(request.getParameter("tadncodg"));
			request.setAttribute("tipo_admissao", md.get(tipo_admissao));
			fw.setPath("/tipo_admissaoDados.do");
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
				List<BeanTipoAdmissao> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "TipoAdmissao n�o encontrado!"));
				} else {
					request.setAttribute("listaPesqtipo_admissao", list);
				}
			}
			fw.setPath("/tipo_admissaoPesquisa.do");
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
