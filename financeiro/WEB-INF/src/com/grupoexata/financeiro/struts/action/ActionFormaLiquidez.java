package com.grupoexata.financeiro.struts.action;

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
import com.grupoexata.financeiro.dao.ModelFormaLiquidez;
import com.grupoexata.financeiro.struts.bean.BeanFormaLiquidez;
import com.grupoexata.financeiro.struts.form.FormFormaLiquidez;

public class ActionFormaLiquidez extends DispatchAction {
	
	private final static String ERRO_CLASS = "AC_FLQ:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			request.setAttribute("ls_formaliquidez", ModelFormaLiquidez.getInstance().getFormasDeLiquidez());
			
			fw.setPath("/formaLiquidezLista.do");
			
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
		FormFormaLiquidez formFormaLiquidez = (FormFormaLiquidez)form;
		ActionMessages erros = new ActionMessages();
		try {			
			
			if (!ValidaObjeto.validaString(formFormaLiquidez.getFlqcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição é obrigatório."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/formaLiquidezNovo.do");
			}else{
			
				BeanFormaLiquidez beanFormaLiquidez = new BeanFormaLiquidez();
				BeanUtils.copyProperties(beanFormaLiquidez, formFormaLiquidez);
				
				ModelFormaLiquidez.getInstance().update(beanFormaLiquidez);
				
				request.getSession().removeAttribute("formFormaLiquidez");
				
				fw.setPath("/actionFormaLiquidez.do?m=lista");
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
		FormFormaLiquidez formFormaLiquidez = (FormFormaLiquidez)form;
		try {			
			
			String flqncodg = request.getParameter("flqncodg");
			
			BeanFormaLiquidez beanFormaLiquidez = ModelFormaLiquidez.getInstance().getFormaLiquidez(Integer.parseInt(flqncodg));
			BeanUtils.copyProperties(formFormaLiquidez, beanFormaLiquidez);
			
			fw.setPath("/formaLiquidezEditar.do");
			
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
			request.getSession().removeAttribute("formFormaLiquidez");
			
			fw.setPath("/formaLiquidezNovo.do");
			
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
		FormFormaLiquidez formFormaLiquidez = (FormFormaLiquidez)form;
		ActionMessages erros = new ActionMessages();
		try {			
			
			if (!ValidaObjeto.validaString(formFormaLiquidez.getFlqcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição é obrigatório."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/formaLiquidezNovo.do");
			}else{
			
				BeanFormaLiquidez beanFormaLiquidez = new BeanFormaLiquidez();
				BeanUtils.copyProperties(beanFormaLiquidez, formFormaLiquidez);
				
				ModelFormaLiquidez.getInstance().inserir(beanFormaLiquidez);
				
				request.getSession().removeAttribute("formFormaLiquidez");
				
				fw.setPath("/actionFormaLiquidez.do?m=lista");
			}
			
			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
