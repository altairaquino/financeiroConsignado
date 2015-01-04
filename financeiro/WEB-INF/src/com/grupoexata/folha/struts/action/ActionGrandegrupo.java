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

import com.grupoexata.folha.bean.BeanGrandegrupo;
import com.grupoexata.folha.dao.ModelGrandegrupo;
import com.grupoexata.folha.struts.form.FormGrandegrupo;
import com.grupoexata.folha.util.Model;

public class ActionGrandegrupo extends DispatchAction {

	private final static String ERRO_CLASS = "ACGGR:";
	private Model<BeanGrandegrupo> md = new ModelGrandegrupo();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listagrandegrupo", md.list());
			fw.setPath("/grandegrupoLista.do");
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
			request.getSession().removeAttribute("formGrandegrupo");
			fw.setPath("/grandegrupoNovo.do");
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
		FormGrandegrupo  formgrandegrupo = (FormGrandegrupo)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/grandegrupoNovo.do");
		}else{
			try {
				BeanGrandegrupo grandegrupo = new BeanGrandegrupo();
				BeanUtils.copyProperties(grandegrupo,  formgrandegrupo);
				md.add(grandegrupo);
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
			FormGrandegrupo formgrandegrupo = (FormGrandegrupo)form;
			BeanGrandegrupo grandegrupo = new BeanGrandegrupo();
			BeanUtils.copyProperties(formgrandegrupo, grandegrupo);
			fw.setPath("/grandegrupoEditar.do");
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
		FormGrandegrupo  formgrandegrupo = (FormGrandegrupo)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/grandegrupoEditar.do");
		}else{
			try {
				BeanGrandegrupo grandegrupo = new BeanGrandegrupo();
				BeanUtils.copyProperties(grandegrupo,  formgrandegrupo);
				md.set(grandegrupo);
				fw.setPath("/actionGrandegrupo.do?m=dados&ggrncodg=" + grandegrupo.getGgrncodg() + "");
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
			BeanGrandegrupo grandegrupo = new BeanGrandegrupo();
			grandegrupo.setGgrncodg(request.getParameter("ggrncodg"));
			request.setAttribute("grandegrupo", md.get(grandegrupo));
			fw.setPath("/grandegrupoDados.do");
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
				List<BeanGrandegrupo> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "Grandegrupo n�o encontrado!"));
				} else {
					request.setAttribute("listaPesqgrandegrupo", list);
				}
			}
			fw.setPath("/grandegrupoPesquisa.do");
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
