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

import com.grupoexata.folha.bean.BeanTipoDescRend;
import com.grupoexata.folha.dao.ModelTipoDescRend;
import com.grupoexata.folha.struts.form.FormTipoDescRend;
import com.grupoexata.folha.util.Model;

public class ActionTipoDescRend extends DispatchAction {

	private final static String ERRO_CLASS = "ACTDR:";
	private Model<BeanTipoDescRend> md = new ModelTipoDescRend();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listatipo_desc_rend", md.list());
			fw.setPath("/tipo_desc_rendLista.do");
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
			request.getSession().removeAttribute("formTipoDescRend");
			fw.setPath("/tipo_desc_rendNovo.do");
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
		FormTipoDescRend  formtipo_desc_rend = (FormTipoDescRend)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/tipo_desc_rendNovo.do");
		}else{
			try {
				BeanTipoDescRend tipo_desc_rend = new BeanTipoDescRend();
				BeanUtils.copyProperties(tipo_desc_rend,  formtipo_desc_rend);
				md.add(tipo_desc_rend);
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
			FormTipoDescRend formtipo_desc_rend = (FormTipoDescRend)form;
			BeanTipoDescRend tipo_desc_rend = new BeanTipoDescRend();
			BeanUtils.copyProperties(formtipo_desc_rend, tipo_desc_rend);
			fw.setPath("/tipo_desc_rendEditar.do");
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
		FormTipoDescRend  formtipo_desc_rend = (FormTipoDescRend)form;
		//validar bean
		if (!erros.isEmpty()){
			fw.setPath("/tipo_desc_rendEditar.do");
		}else{
			try {
				BeanTipoDescRend tipo_desc_rend = new BeanTipoDescRend();
				BeanUtils.copyProperties(tipo_desc_rend,  formtipo_desc_rend);
				md.set(tipo_desc_rend);
				fw.setPath("/actionTipoDescRend.do?m=dados&tdrncodg=" + tipo_desc_rend.getTdrncodg() + "");
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
			BeanTipoDescRend tipo_desc_rend = new BeanTipoDescRend();
			tipo_desc_rend.setTdrncodg(request.getParameter("tdrncodg"));
			request.setAttribute("tipo_desc_rend", md.get(tipo_desc_rend));
			fw.setPath("/tipo_desc_rendDados.do");
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
				List<BeanTipoDescRend> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "TipoDescRend n�o encontrado!"));
				} else {
					request.setAttribute("listaPesqtipo_desc_rend", list);
				}
			}
			fw.setPath("/tipo_desc_rendPesquisa.do");
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
