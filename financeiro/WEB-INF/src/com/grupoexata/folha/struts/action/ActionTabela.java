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

import com.grupoexata.folha.bean.BeanTabela;
import com.grupoexata.folha.dao.ModelTabela;
import com.grupoexata.folha.struts.form.FormTabela;

public class ActionTabela extends DispatchAction {

	private final static String ERRO_CLASS = "ACTAB:";
	private ModelTabela md = new ModelTabela();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			String tabncgttb = request.getParameter("tabncgttb");
			request.setAttribute("listatabela", md.list(tabncgttb));
			fw.setPath("/tabelaLista.do");
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
			FormTabela formtabela = new FormTabela();
			formtabela.setTabncgttb(request.getParameter("ttbncodg"));
			request.getSession().setAttribute("formTabela",formtabela);
			fw.setPath("/tabelaNovo.do");
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
		FormTabela  formtabela = (FormTabela)form;
		//validar bean
		formtabela.validarForm(erros);
		if (!erros.isEmpty()){
			fw.setPath("/tabelaNovo.do");
			saveErrors(request, erros);
		}else{
			try {
				BeanTabela tabela = new BeanTabela();
				BeanUtils.copyProperties(tabela,  formtabela);
				md.add(tabela);
				fw.setPath("/actionTabela.do?m=lista&tabncgttb=" + tabela.getTabncgttb());
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
			FormTabela formtabela = (FormTabela)form;
			BeanTabela tabela = new BeanTabela();
			tabela.setTabncodg(request.getParameter("tabncodg"));
			tabela = md.get(tabela);
			BeanUtils.copyProperties(formtabela, tabela);
			fw.setPath("/tabelaEditar.do");
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
		FormTabela  formtabela = (FormTabela)form;
		//validar bean
		formtabela.validarForm(erros);
		if (!erros.isEmpty()){
			fw.setPath("/tabelaEditar.do");
			saveErrors(request, erros);
		}else{
			try {
				BeanTabela tabela = new BeanTabela();
				BeanUtils.copyProperties(tabela,  formtabela);
				md.set(tabela);
				fw.setPath("/actionTabela.do?m=lista&tabncgttb=" + tabela.getTabncgttb());
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
			BeanTabela tabela = new BeanTabela();
			tabela.setTabncodg(request.getParameter("tabncodg"));
			request.setAttribute("tabela", md.get(tabela));
			fw.setPath("/tabelaDados.do");
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
				List<BeanTabela> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "Tabela n�o encontrado!"));
				} else {
					request.setAttribute("listaPesqtabela", list);
				}
			}
			fw.setPath("/tabelaPesquisa.do");
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
