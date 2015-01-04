package com.grupoexata.bancario.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.dao.ModelGrupoEmail;
import com.grupoexata.bancario.dao.ModelListaEmail;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.struts.bean.BeanListaEmail;
import com.grupoexata.bancario.struts.form.FormListaEmail;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionListaEmail extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACLTE:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			String gemncodg = request.getParameter("gemncodg");
			
			request.setAttribute("grupoemail", ModelGrupoEmail.getInstance().getGrupoEmail(Integer.parseInt(gemncodg)));
			
			request.setAttribute("ls_listaemail", ModelListaEmail.getInstance().getListaEmailPorGrupo(Integer.parseInt(gemncodg)));
			
			fw.setPath("/listaEmailLista.do");
			
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
		FormListaEmail formListaEmail = (FormListaEmail)form;
		try {		
			
			BeanListaEmail beanListaEmail = new BeanListaEmail();
			BeanUtils.copyProperties(beanListaEmail, formListaEmail);
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (usuario != null && beanListaEmail != null){
				ModelListaEmail.getInstance().update(beanListaEmail);
			}			
			
			fw.setPath("/actionListaEmail.do?m=lista&gemncodg="+beanListaEmail.getLtencggem());
			
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
		FormListaEmail formListaEmail = (FormListaEmail)form;	
		try {			
			
			String ltencodg = request.getParameter("ltencodg");
			BeanListaEmail beanListaEmail = ModelListaEmail.getInstance().getListaEmail(Integer.parseInt(ltencodg));
			BeanUtils.copyProperties(formListaEmail, beanListaEmail);
			
			fw.setPath("/listaEmailEditar.do");
			
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
		final String METODO = "0004";
		ActionForward fw = new ActionForward();
		
		try {		
			
			String ltencodg = request.getParameter("ltencodg");
			
			BeanListaEmail beanListaEmail = ModelListaEmail.getInstance().getListaEmail(Integer.parseInt(ltencodg));
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (usuario != null && beanListaEmail != null){
				ModelListaEmail.getInstance().delete(beanListaEmail);
			}			
			
			fw.setPath("/actionListaEmail.do?m=lista&gemncodg="+beanListaEmail.getLtencggem());
			
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
		FormListaEmail formListaEmail = (FormListaEmail)form;
		try {			
			BeanListaEmail beanListaEmail = new BeanListaEmail();
			BeanUtils.copyProperties(beanListaEmail, formListaEmail);
			
			if (ValidaObjeto.validaString(formListaEmail.getLtencgen())){
				ModelListaEmail.getInstance().inserir(beanListaEmail);
			}
			
			fw.setPath("/actionListaEmail.do?m=lista&gemncodg="+formListaEmail.getLtencggem());
			
			request.getSession().removeAttribute("formListaEmail");
			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
