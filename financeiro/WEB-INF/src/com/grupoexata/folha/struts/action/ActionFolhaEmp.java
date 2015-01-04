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

import com.grupoexata.folha.bean.BeanFolhaEmp;
import com.grupoexata.folha.dao.ModelFolhaEmp;
import com.grupoexata.folha.struts.form.FormFolhaEmp;

public class ActionFolhaEmp extends DispatchAction {

	private final static String ERRO_CLASS = "ACFOE:";
	private ModelFolhaEmp md = new ModelFolhaEmp();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			String folncodg = request.getParameter("folncodg");
			request.setAttribute("listafolha_emp", md.list(folncodg));
			fw.setPath("/folha_empLista.do");
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
			request.getSession().removeAttribute("formFolhaEmp");
			fw.setPath("/folha_empNovo.do");
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
		FormFolhaEmp  formfolha_emp = (FormFolhaEmp)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/folha_empNovo.do");
		}else{
			try {
				BeanFolhaEmp folha_emp = new BeanFolhaEmp();
				BeanUtils.copyProperties(folha_emp,  formfolha_emp);
				md.add(folha_emp);
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
			FormFolhaEmp formfolha_emp = (FormFolhaEmp)form;
			BeanFolhaEmp folha_emp = new BeanFolhaEmp();
			BeanUtils.copyProperties(formfolha_emp, folha_emp);
			fw.setPath("/folha_empEditar.do");
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
		FormFolhaEmp  formfolha_emp = (FormFolhaEmp)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/folha_empEditar.do");
		}else{
			try {
				BeanFolhaEmp folha_emp = new BeanFolhaEmp();
				BeanUtils.copyProperties(folha_emp,  formfolha_emp);
				md.set(folha_emp);
				fw.setPath("/actionFolhaEmp.do?m=dados&foencodg=" + folha_emp.getFoencodg() + "");
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
			BeanFolhaEmp folha_emp = new BeanFolhaEmp();
			folha_emp.setFoencodg(request.getParameter("foencodg"));
			request.setAttribute("folha_emp", md.get(folha_emp));
			fw.setPath("/folha_empDados.do");
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
				List<BeanFolhaEmp> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "FolhaEmp n?o encontrado!"));
				} else {
					request.setAttribute("listaPesqfolha_emp", list);
				}
			}
			fw.setPath("/folha_empPesquisa.do");
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
