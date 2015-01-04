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

import com.grupoexata.bancario.dao.ModelGrupoEmail;
import com.grupoexata.bancario.struts.bean.BeanGrupoEmail;
import com.grupoexata.bancario.struts.form.FormGrupoEmail;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionGrupoEmail extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACGEM:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			request.setAttribute("ls_grupoemail", ModelGrupoEmail.getInstance().getGrupoEmails());
			
			fw.setPath("/grupoEmailLista.do");
			
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
		FormGrupoEmail formGrupoEmail = (FormGrupoEmail)form;
		ActionMessages erros = new ActionMessages();
		try {			
			
			if (!ValidaObjeto.validaString(formGrupoEmail.getGemcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nome é requerido."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/grupoEmailEditar.do");
			}else{
				BeanGrupoEmail beanGrupoEmail = new BeanGrupoEmail();
				BeanUtils.copyProperties(beanGrupoEmail, formGrupoEmail);
				
				ModelGrupoEmail.getInstance().update(beanGrupoEmail);
				
				request.getSession().removeAttribute("formGrupoEmail");
				
				fw = lista(mapping, formGrupoEmail, request, response);
				
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
		FormGrupoEmail formGrupoEmail = (FormGrupoEmail)form;
		try {			
			
			String gemncodg = request.getParameter("gemncodg");
			BeanGrupoEmail beanGrupoEmail = ModelGrupoEmail.getInstance().getGrupoEmail(Integer.parseInt(gemncodg));
			BeanUtils.copyProperties(formGrupoEmail, beanGrupoEmail);			
			
			fw.setPath("/grupoEmailEditar.do");
			
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
			
			request.getSession().removeAttribute("formGrupoEmail");
			
			fw.setPath("/grupoEmailNovo.do");
			
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
		FormGrupoEmail formGrupoEmail = (FormGrupoEmail)form;
		ActionMessages erros = new ActionMessages();
		try {			
			
			if (!ValidaObjeto.validaString(formGrupoEmail.getGemcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nome é requerido."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/grupoEmailNovo.do");
			}else{
				BeanGrupoEmail beanGrupoEmail = new BeanGrupoEmail();
				BeanUtils.copyProperties(beanGrupoEmail, formGrupoEmail);
				
				ModelGrupoEmail.getInstance().inserir(beanGrupoEmail);
				
				request.getSession().removeAttribute("formGrupoEmail");
				
				fw = lista(mapping, formGrupoEmail, request, response);
				
			}
						
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
