package com.grupoexata.juridico.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.juridico.dao.ModelAndamentoProcesso;
import com.grupoexata.juridico.dao.ModelFaseProcesso;
import com.grupoexata.juridico.dao.ModelProcesso;
import com.grupoexata.juridico.struts.bean.BeanAndamentoProcesso;
import com.grupoexata.juridico.struts.bean.BeanProcesso;
import com.grupoexata.juridico.struts.form.FormAndamentoProcesso;

public class ActionAndamentoProcesso extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACANP:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			String proncodg = request.getParameter("proncodg");
			
			BeanProcesso beanProcesso = ModelProcesso.getInstance().getProcesso(Integer.parseInt(proncodg));
			
			request.setAttribute("processo", beanProcesso); 
			request.setAttribute("ls_andamentoprocesso", 
					ModelAndamentoProcesso.getInstance().getAndamentoDoProcesso(
							Integer.parseInt(proncodg)
					)
				);
			
			fw.setPath("/andamentoProcessoLista.do");
			
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
		
		FormAndamentoProcesso formAndamentoProcesso = (FormAndamentoProcesso)form;
		ActionMessages erros = new ActionMessages();
		ActionForward fw = new ActionForward();
		
		try {			
			
			if (!ValidaObjeto.validaString(formAndamentoProcesso.getAnpcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição é obrigatória."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/andamentoProcessoEditar.do");
			}else{
				
				BeanAndamentoProcesso beanAndamentoProcesso = new BeanAndamentoProcesso();
				BeanUtils.copyProperties(beanAndamentoProcesso, formAndamentoProcesso);
				
				ModelAndamentoProcesso.getInstance().update(beanAndamentoProcesso);
				
				request.getSession().removeAttribute("processo");
				
				request.getSession().removeAttribute("ls_faseprocesso");
				
				request.getSession().removeAttribute("formAndamentoProcesso");
				
				fw.setPath("/actionAndamentoProcesso.do?m=lista&proncodg="+formAndamentoProcesso.getAnpncgpro());				
				
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
		FormAndamentoProcesso formAndamentoProcesso = (FormAndamentoProcesso)form;
			
		try {
			
			String anpncodg = request.getParameter("anpncodg");
			
			BeanAndamentoProcesso beanAndamentoProcesso = ModelAndamentoProcesso.getInstance().getAndamentoProcesso(Integer.parseInt(anpncodg));
			
			BeanUtils.copyProperties(formAndamentoProcesso, beanAndamentoProcesso);
			
			request.getSession().setAttribute("processo", ModelProcesso.getInstance().getProcesso(Integer.parseInt(beanAndamentoProcesso.getAnpncgpro())));
			
			request.getSession().setAttribute("ls_faseprocesso", ModelFaseProcesso.getInstance().getFasesDoProcesso());
			
			
			fw.setPath("/andamentoProcessoEditar.do");
			
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
		final String METODO = "0005";
		ActionForward fw = new ActionForward();
		
		try {			
			
			String proncodg = request.getParameter("proncodg");
			
			request.getSession().setAttribute("processo", ModelProcesso.getInstance().getProcesso(Integer.parseInt(proncodg)));
			
			request.getSession().setAttribute("ls_faseprocesso", ModelFaseProcesso.getInstance().getFasesDoProcesso());
						
			request.getSession().removeAttribute("formAndamentoProcesso");
			
			fw.setPath("/andamentoProcessoNovo.do");
			
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
		final String METODO = "0006";
		ActionForward fw = new ActionForward();
		
		FormAndamentoProcesso formAndamentoProcesso = (FormAndamentoProcesso)form;
		ActionMessages erros = new ActionMessages();
		
		try {			
			
			if (!ValidaObjeto.validaString(formAndamentoProcesso.getAnpcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição é obrigatória."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/andamentoProcessoNovo.do");
			}else{
				
				BeanAndamentoProcesso beanAndamentoProcesso = new BeanAndamentoProcesso();
				BeanUtils.copyProperties(beanAndamentoProcesso, formAndamentoProcesso);
				
				ModelAndamentoProcesso.getInstance().insert(beanAndamentoProcesso);
				
				request.getSession().removeAttribute("processo");
				
				request.getSession().removeAttribute("ls_faseprocesso");
				
				request.getSession().removeAttribute("formAndamentoProcesso");
				
				fw.setPath("/actionAndamentoProcesso.do?m=lista&proncodg="+formAndamentoProcesso.getAnpncgpro());				
				
			}
			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
}
