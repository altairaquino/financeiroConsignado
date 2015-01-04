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

import com.grupoexata.bancario.dao.ModelBordero;
import com.grupoexata.bancario.struts.bean.BeanBordero;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.struts.form.FormBordero;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionBordero extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACBOR:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			request.setAttribute("ls_bordero", ModelBordero.getInstance().getBorderos());
			
			fw.setPath("/borderoLista.do");
			
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
		ActionMessages erros = new ActionMessages();
		FormBordero formBordero = (FormBordero)form;
		try {
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (!ValidaObjeto.validaString(formBordero.getBorcnumr())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Número do borderô é obrigatório."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/borderoNovo.do");				
			}else{
				BeanBordero beanBordero = new BeanBordero();
				BeanUtils.copyProperties(beanBordero, formBordero);
				
				beanBordero.setBorncgen(usuario.getEnncodg());
				
				ModelBordero.getInstance().update(beanBordero);
				
				request.getSession().removeAttribute("formBordero");
				
				fw.setPath("/actionBordero.do?m=dados&borncodg="+formBordero.getBorncodg());
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
		FormBordero formBordero = (FormBordero)form;
		try {			
			String borncodg = request.getParameter("borncodg");
			
			BeanBordero beanBordero = ModelBordero.getInstance().getBordero(Integer.parseInt(borncodg));
			
			BeanUtils.copyProperties(formBordero, beanBordero);	
			
			fw.setPath("/borderoEditar.do");
			
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
			
			request.getSession().removeAttribute("formBordero");
			
			fw.setPath("/borderoNovo.do");
			
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
		ActionMessages erros = new ActionMessages();
		FormBordero formBordero = (FormBordero)form;
		try {
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (!ValidaObjeto.validaString(formBordero.getBorcnumr())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Número do borderô é obrigatório."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/borderoNovo.do");				
			}else{
				BeanBordero beanBordero = new BeanBordero();
				BeanUtils.copyProperties(beanBordero, formBordero);
				
				beanBordero.setBorncgen(usuario.getEnncodg());
				
				ModelBordero.getInstance().inserir(beanBordero);
				
				request.getSession().removeAttribute("formBordero");
				
				fw = lista(mapping, formBordero, request, response);
			}			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward dados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0006";
		ActionForward fw = new ActionForward();		
		try {
			
			String borncodg = request.getParameter("borncodg");
			
			request.setAttribute("bordero", ModelBordero.getInstance().getBordero(Integer.parseInt(borncodg)));
			
			fw.setPath("/borderoDados.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
