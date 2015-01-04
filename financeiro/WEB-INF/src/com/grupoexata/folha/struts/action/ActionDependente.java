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
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.folha.bean.BeanDependente;
import com.grupoexata.folha.dao.ModelDependente;
import com.grupoexata.folha.struts.form.FormDependente;

public class ActionDependente extends DispatchAction {

	private final static String ERRO_CLASS = "ACDEP:";
	private ModelDependente md = new ModelDependente();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			String enncodg = request.getParameter("enncodg");
			if(!ValidaObjeto.isNullId(enncodg)){
			}
			request.getSession().setAttribute("empregado", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			request.setAttribute("listadependente", md.listByDepncgen(enncodg));
			fw.setPath("/dependenteLista.do");
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
			if(request.getAttribute("erro") == null){
				FormDependente formDependente = new FormDependente();
				formDependente.setDepncgen((String)request.getParameter("enncodg"));
				request.getSession().setAttribute("formDependente", formDependente);
			}
			fw.setPath("/dependenteNovo.do");
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
		FormDependente  formdependente = (FormDependente)form;
		//validar bean
		formdependente.validarForm(erros);
		if (!erros.isEmpty()){
			fw.setPath("/dependenteNovo.do");
			saveErrors(request, erros);
		}else{
			try {
				BeanDependente dependente = new BeanDependente();
				BeanUtils.copyProperties(dependente,  formdependente);
				md.add(dependente);
				fw.setPath("/actionDependente.do?m=lista&enncodg=" + dependente.getDepncgen());
				
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
			FormDependente formdependente = (FormDependente)form;
			BeanDependente dependente = new BeanDependente();
			dependente.setDepncodg(request.getParameter("depncodg"));
			dependente = md.get(dependente);
			BeanUtils.copyProperties(formdependente, dependente);
			fw.setPath("/dependenteEditar.do");
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
		FormDependente  formdependente = (FormDependente)form;
		//validar bean
		formdependente.validarForm(erros);
		if (!erros.isEmpty()){
			fw.setPath("/dependenteEditar.do");
			saveErrors(request, erros);
		}else{
			try {
				BeanDependente dependente = new BeanDependente();
				BeanUtils.copyProperties(dependente,  formdependente);
				md.set(dependente);
				fw.setPath("/actionDependente.do?m=lista&enncodg=" + dependente.getDepncgen());
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
			BeanDependente dependente = new BeanDependente();
			dependente.setDepncodg(request.getParameter("depncodg"));
			request.setAttribute("dependente", md.get(dependente));
			fw.setPath("/dependenteDados.do");
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
				List<BeanDependente> list = md.listSearch(param);
				if (list.isEmpty()) {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail", "Dependente n�o encontrado!"));
				} else {
					request.setAttribute("listaPesqdependente", list);
				}
			}
			fw.setPath("/dependentePesquisa.do");
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
