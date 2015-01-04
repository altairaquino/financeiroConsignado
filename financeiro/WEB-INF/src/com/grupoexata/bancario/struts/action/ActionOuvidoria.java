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

import com.grupoexata.bancario.dao.ModelOuvidoria;
import com.grupoexata.bancario.dao.ModelSetor;
import com.grupoexata.bancario.dao.ModelTipoSolicitacao;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.struts.bean.BeanOuvidoria;
import com.grupoexata.bancario.struts.form.FormOuvidoria;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionOuvidoria extends DispatchAction {
	
	private final static String ERRO_CLASS = "AC_OUV:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			fw.setPath("/");
			
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
		
			
		try {
			
			fw.setPath("/");
								
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
		
		try {			
			
			fw.setPath("/");
			
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
			
			request.getSession().setAttribute("ls_setor", ModelSetor.getInstance().getSetores());
			request.getSession().setAttribute("ls_tiposolicitacao", ModelTipoSolicitacao.getInstance().getTipoSolicitacoes());
			
			fw.setPath("/ouvidoriaNovo.do");
			
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
		FormOuvidoria formOuvidoria = (FormOuvidoria)form;
		ActionMessages erros = new ActionMessages();
		try {			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");	
			
			if (!ValidaObjeto.validaString(formOuvidoria.getOuvcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição é obrigatória."));
			}
			
			if (!erros.isEmpty() || usuario == null){
				saveErrors(request, erros);
				fw.setPath("/ouvidoriaNovo.do");
			}else{
				
				BeanOuvidoria beanOuvidoria = new BeanOuvidoria();
				BeanUtils.copyProperties(beanOuvidoria, formOuvidoria);
				
				beanOuvidoria.setOuvncgen(usuario.getEnncodg());
				
				ModelOuvidoria.getInstance().insert(beanOuvidoria);				
				
				request.getSession().removeAttribute("ls_setor");
				request.getSession().removeAttribute("ls_tiposolicitacao");
				request.getSession().removeAttribute("formOuvidoria");
				
				request.setAttribute("msg", "Solicitação Registrada com sucesso!");
				
				fw.setPath("/home.do");
			}
			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
