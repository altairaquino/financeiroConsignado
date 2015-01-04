package com.grupoexata.bancario.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.dao.ModelFuncRegional;
import com.grupoexata.bancario.dao.ModelRegional;
import com.grupoexata.bancario.dao.ModelTipoFuncAgencia;
import com.grupoexata.bancario.struts.bean.BeanFuncRegional;
import com.grupoexata.bancario.struts.form.FormFuncRegional;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionFuncRegional extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACFRE:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			String rgncodg = request.getParameter("rgncodg");
			
			request.setAttribute("regional", ModelRegional.getInstance().getRegional(Integer.parseInt(rgncodg)));
			
			request.setAttribute("ls_funcRegional", ModelFuncRegional.getInstance().getFuncionariosDaRegional(Integer.parseInt(rgncodg)));
			
			fw.setPath("/funcRegionalLista.do");
			
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
		final String METODO = "0002";
		
		ActionForward fw = new ActionForward();
		FormFuncRegional formFuncRegional = (FormFuncRegional)form;
		ActionMessages erros = new ActionMessages();
		try {			
			
			if (!ValidaObjeto.validaInteiro(formFuncRegional.getFrencgen())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Funcionário é obrigatório."));
			}			
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/funcRegionalEditar.do");
			}else{
				
				BeanFuncRegional beanFuncRegional = new BeanFuncRegional();
				BeanUtils.copyProperties(beanFuncRegional, formFuncRegional);
				
				ModelFuncRegional.getInstance().update(beanFuncRegional);
				
				request.getSession().removeAttribute("formFuncRegional");
				request.getSession().removeAttribute("ls_tipofuncagencia");
				
				fw.setPath("/actionFuncRegional.do?m=lista&rgncodg="+formFuncRegional.getFrencgrg());
			}			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0003";
		ActionForward fw = new ActionForward();
		FormFuncRegional formFuncRegional = (FormFuncRegional)form;	
		try {			
			String frencodg = request.getParameter("frencodg");
			
			BeanFuncRegional beanFuncRegional = ModelFuncRegional.getInstance().getFuncRegional(Integer.parseInt(frencodg));
			
			BeanUtils.copyProperties(formFuncRegional, beanFuncRegional);
			
			request.getSession().setAttribute("ls_tipofuncagencia", ModelTipoFuncAgencia.getInstance().getTiposFuncAgenciaTipo("R"));
			
			fw.setPath("/funcRegionalEditar.do");
			
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
		final String METODO = "0004";
		ActionForward fw = new ActionForward();
		
		try {
			String rgncodg = request.getParameter("rgncodg");
			
			request.getSession().setAttribute("regional", ModelRegional.getInstance().getRegional(Integer.parseInt(rgncodg)));
			request.getSession().removeAttribute("formFuncRegional");
			request.getSession().setAttribute("ls_tipofuncagencia", ModelTipoFuncAgencia.getInstance().getTiposFuncAgenciaTipo("R"));
			
			fw.setPath("/funcRegionalNovo.do");
			
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
		final String METODO = "0005";
		ActionForward fw = new ActionForward();
		FormFuncRegional formFuncRegional = (FormFuncRegional)form;
		ActionMessages erros = new ActionMessages();
		try {			
			
			if (!ValidaObjeto.validaInteiro(formFuncRegional.getFrencgen())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Funcionário é obrigatório."));
			}
						
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/funcRegionalNovo.do");
			}else{
				
				BeanFuncRegional beanFuncRegional = new BeanFuncRegional();
				BeanUtils.copyProperties(beanFuncRegional, formFuncRegional);
				
				ModelFuncRegional.getInstance().inserir(beanFuncRegional);
				
				request.getSession().removeAttribute("regional");
				request.getSession().removeAttribute("formFuncRegional");
				request.getSession().removeAttribute("ls_tipofuncagencia");
				
				fw.setPath("/actionFuncRegional.do?m=lista&rgncodg="+formFuncRegional.getFrencgrg());
			}			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
