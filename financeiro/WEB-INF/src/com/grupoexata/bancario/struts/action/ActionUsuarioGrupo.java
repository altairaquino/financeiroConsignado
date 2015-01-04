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
import com.grupoexata.bancario.dao.ModelUsuarioGrupo;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.struts.bean.BeanUsuarioGrupo;
import com.grupoexata.bancario.struts.form.FormUsuarioGrupo;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionUsuarioGrupo extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACUG:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			String grncodg = request.getParameter("grncodg");
			
			request.setAttribute("grupo", ModelGrupo.getInstance().getGrupo(Integer.parseInt(grncodg)));
			
			request.setAttribute("ls_usuariogrupo", ModelUsuarioGrupo.getInstance().getUsuariosDoGrupo(Integer.parseInt(grncodg)));
			
			fw.setPath("/usuarioGrupoLista.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
		
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0002";
		
		ActionForward fw = new ActionForward();
		try {			
			
			String ugncodg = request.getParameter("ugncodg");
			String grncodg = request.getParameter("grncodg");
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (usuario != null) {
				ModelUsuarioGrupo.getInstance().delete(Integer.parseInt(ugncodg));
			}
			
			fw.setPath("/actionUsuarioGrupo.do?m=lista&grncodg="+ grncodg);
								
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
		FormUsuarioGrupo formUsuarioGrupo = (FormUsuarioGrupo)form;
		try {
			
			String grncodg = request.getParameter("grncodg");
			BeanUsuarioGrupo grupo = ModelUsuarioGrupo.getInstance().getUsuarioGrupo(Integer.parseInt(grncodg));
			BeanUtils.copyProperties(formUsuarioGrupo, grupo);
			
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
			request.getSession().removeAttribute("formUsuarioGrupo");
			
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
		FormUsuarioGrupo formUsuarioGrupo = (FormUsuarioGrupo)form;
		ActionMessages erros = new ActionMessages();
		try {
			
			if (!ValidaObjeto.validaInteiro(formUsuarioGrupo.getUgncgus())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Código é obrigatório."));
			}
			
			if (!ValidaObjeto.validaString(formUsuarioGrupo.getUgncggr())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição é obrigatória."));
			}					
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/grupoNovo.do");
			}else{
				BeanUsuarioGrupo grupo = new BeanUsuarioGrupo();
				BeanUtils.copyProperties(grupo, formUsuarioGrupo);
				
				ModelUsuarioGrupo.getInstance().inserir(grupo);
				
				fw = lista(mapping, form, request, response);
				
				request.getSession().removeAttribute("formUsuarioGrupo");
				
			}
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}	

}
