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

import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.financeiro.dao.ModelContaGrupo;
import com.grupoexata.financeiro.dao.ModelGrupoConta;
import com.grupoexata.financeiro.struts.bean.BeanContaGrupo;
import com.grupoexata.financeiro.struts.form.FormContaGrupo;

public class ActionContaGrupo extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACCOG:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			String gpcncodg = request.getParameter("gpcncodg");
			
			request.setAttribute("grupoconta", ModelGrupoConta.getInstance().getGrupoConta(Integer.parseInt(gpcncodg)));
			
			request.setAttribute("ls_contagrupo", ModelContaGrupo.getInstance().getContasDoGrupo(Integer.parseInt(gpcncodg)));
			
			fw.setPath("/contaGrupoLista.do");
			
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
		FormContaGrupo formContaGrupo = (FormContaGrupo)form;
		
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		try {			
			if (!ValidaObjeto.validaString(formContaGrupo.getCogcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição é obrigatória."));
			}
			
			if (!erros.isEmpty() || usuario == null){
				saveErrors(request, erros);
				fw.setPath("/contaGrupoEditar.do");		
			}else{
				BeanContaGrupo beanContaGrupo = new BeanContaGrupo();
				BeanUtils.copyProperties(beanContaGrupo, formContaGrupo);
				
				ModelContaGrupo.getInstance().update(beanContaGrupo);
				
				request.getSession().removeAttribute("formContaGrupo");
				request.getSession().removeAttribute("grupoconta");	
				request.getSession().removeAttribute("ls_grupoconta");		
				
				fw.setPath("/actionContaGrupo.do?m=lista&gpcncodg="+formContaGrupo.getCogncggpc());
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
		FormContaGrupo formContaGrupo = (FormContaGrupo)form;
		try {			
			String cogncodg = request.getParameter("cogncodg");
			BeanContaGrupo beanContaGrupo = ModelContaGrupo.getInstance().getContaGrupo(Integer.parseInt(cogncodg));
			
			BeanUtils.copyProperties(formContaGrupo, beanContaGrupo);
			
			request.getSession().setAttribute("ls_grupoconta", ModelGrupoConta.getInstance().getGruposConta());
			
			request.getSession().setAttribute("grupoconta", ModelGrupoConta.getInstance().getGrupoConta(Integer.parseInt(beanContaGrupo.getCogncggpc())));
			
			fw.setPath("/contaGrupoEditar.do");
			
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
			String gpcncodg = request.getParameter("gpcncodg");
			
			request.getSession().removeAttribute("formContaGrupo");
			request.getSession().setAttribute("grupoconta", ModelGrupoConta.getInstance().getGrupoConta(Integer.parseInt(gpcncodg)));
			
			fw.setPath("/contaGrupoNovo.do");
			
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
		FormContaGrupo formContaGrupo = (FormContaGrupo)form;
		
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		try {			
			if (!ValidaObjeto.validaString(formContaGrupo.getCogcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição é obrigatória."));
			}
			
			if (!erros.isEmpty() || usuario == null){
				saveErrors(request, erros);
				fw.setPath("/contaGrupoNovo.do");				
			}else{
				BeanContaGrupo beanContaGrupo = new BeanContaGrupo();
				BeanUtils.copyProperties(beanContaGrupo, formContaGrupo);
				
				ModelContaGrupo.getInstance().inserir(beanContaGrupo);
				
				request.getSession().removeAttribute("formContaGrupo");
				request.getSession().removeAttribute("grupoconta");	
				request.getSession().removeAttribute("ls_grupoconta");
				
				fw.setPath("/actionContaGrupo.do?m=lista&gpcncodg="+formContaGrupo.getCogncggpc());
			}
			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward ativaDesativa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0006";
		ActionForward fw = new ActionForward();
		FormContaGrupo formContaGrupo = (FormContaGrupo)form;
		try {			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			BeanContaGrupo beanContaGrupo = ModelContaGrupo.getInstance().getContaGrupo(Integer.parseInt(formContaGrupo.getCogncodg()));
			
			if (usuario != null){			
				ModelContaGrupo.getInstance().ativaDesativa(beanContaGrupo);
			}
			
			fw.setPath("/actionContaGrupo.do?m=lista&gpcncodg="+beanContaGrupo.getCogncggpc());
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
		

}
