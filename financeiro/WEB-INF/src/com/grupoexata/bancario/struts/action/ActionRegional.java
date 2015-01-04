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

import com.grupoexata.bancario.dao.ModelRegional;
import com.grupoexata.bancario.struts.bean.BeanRegional;
import com.grupoexata.bancario.struts.form.FormRegional;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionRegional extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACRG:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			request.setAttribute("ls_regional", ModelRegional.getInstance().getRegionais());
			
			fw.setPath("/regionalLista.do");
			
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
		FormRegional formRegional = (FormRegional)form;
		ActionMessages erros = new ActionMessages();		
		try {			
			
			if(!ValidaObjeto.validaString(formRegional.getRgcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição é obrigatória!"));
			}
			
						
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/regionalEditar.do");
			}else{
				
				BeanRegional beanRegional = new BeanRegional();
				BeanUtils.copyProperties(beanRegional, formRegional);
				
				ModelRegional.getInstance().update(beanRegional);
				
				fw.setPath("/actionRegional.do?m=lista");
				
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
		FormRegional formRegional = (FormRegional)form;
		try {			
			String rgncodg = request.getParameter("rgncodg");
			
			BeanRegional beanRegional = ModelRegional.getInstance().getRegional(Integer.parseInt(rgncodg));
			BeanUtils.copyProperties(formRegional, beanRegional);
			
			fw.setPath("/regionalEditar.do");
			
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
			
			request.getSession().removeAttribute("formRegional");
			
			fw.setPath("/regionalNovo.do");
			
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
		FormRegional formRegional = (FormRegional)form;
		ActionMessages erros = new ActionMessages();		
		try {			
			
			if(!ValidaObjeto.validaString(formRegional.getRgcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição é obrigatória!"));
			}
			
						
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/regionalNovo.do");
			}else{
				
				BeanRegional beanRegional = new BeanRegional();
				BeanUtils.copyProperties(beanRegional, formRegional);
				
				ModelRegional.getInstance().insert(beanRegional);
				
				fw.setPath("/actionRegional.do?m=lista");
				
			}
			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
