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

import com.grupoexata.bancario.dao.ModelGrupo;
import com.grupoexata.bancario.struts.bean.BeanGrupo;
import com.grupoexata.bancario.struts.form.FormGrupo;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionGrupo extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACGR:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			request.setAttribute("ls_grupo", ModelGrupo.getInstance().getGrupos());
			
			fw.setPath("/grupoLista.do");
			
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
		FormGrupo formGrupo = (FormGrupo)form;
		ActionMessages erros = new ActionMessages();
		try {			
			
			if (!ValidaObjeto.validaInteiro(formGrupo.getGrncodg())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Código é obrigatório."));
			}
			
			if (!ValidaObjeto.validaString(formGrupo.getGrcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição é obrigatória."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/grupoNovo.do");
			}else{
			
				BeanGrupo grupo = new BeanGrupo();
				BeanUtils.copyProperties(grupo, formGrupo);
				
				ModelGrupo.getInstance().update(grupo);
				
				fw = lista(mapping, form, request, response);
				
				request.getSession().removeAttribute("formGrupo");
				
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
		FormGrupo formGrupo = (FormGrupo)form;
		try {
			
			String grncodg = request.getParameter("grncodg"); 
			BeanGrupo grupo = ModelGrupo.getInstance().getGrupo(Integer.parseInt(grncodg));
			BeanUtils.copyProperties(formGrupo, grupo);
			
			fw.setPath("/grupoEditar.do");
			
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
			request.getSession().removeAttribute("formGrupo");
			
			fw.setPath("/grupoNovo.do");
			
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
		FormGrupo formGrupo = (FormGrupo)form;
		ActionMessages erros = new ActionMessages();
		try {
			
			if (!ValidaObjeto.validaInteiro(formGrupo.getGrncodg())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Código é obrigatório."));
			}else{
				if (ModelGrupo.getInstance().getGrupo(Integer.parseInt(formGrupo.getGrncodg())) != null){
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Grupo já existente com o código informado."));
				}
			}
			
			if (!ValidaObjeto.validaString(formGrupo.getGrcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição é obrigatória."));
			}						
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/grupoNovo.do");
			}else{
				BeanGrupo grupo = new BeanGrupo();
				BeanUtils.copyProperties(grupo, formGrupo);
				
				ModelGrupo.getInstance().inserir(grupo);
				
				fw = lista(mapping, form, request, response);
				
				request.getSession().removeAttribute("formGrupo");
				
			}
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
