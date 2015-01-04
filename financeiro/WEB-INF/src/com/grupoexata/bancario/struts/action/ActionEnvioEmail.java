package com.grupoexata.bancario.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.utils.EnviarEmail;

public class ActionEnvioEmail extends DispatchAction {
	
	private final static String ERRO_CLASS = "AC_EMAIL:";
	
	public ActionForward enviar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001"; 
		ActionForward fw = new ActionForward();
				
		try {
			
			String tipo = request.getParameter("tipo");
			
			fw.setPath("/home.do");
			
			switch (Integer.parseInt(tipo)) {
			case 1:
				EnviarEmail.getInstance().enviarEmailRelatorioGC();				
				break;
			case 2:
				EnviarEmail.getInstance().enviarEmailRelatorioSinergia();			
				break;
			default:
				break;
			}
			
			request.setAttribute("msg", "E-mail enviado com sucesso.");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
		
	public ActionForward enviarPorGrupo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0002";
		
		ActionForward fw = new ActionForward();			
		try {
			
			String titulo = request.getParameter("titulo");
			String grupo = request.getParameter("grupo");
			String conteudo = request.getParameter("conteudo");
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (usuario != null){
				conteudo += "<br><br>Enviado por: "+usuario.getEncnome();
				EnviarEmail.getInstance().enviarEmailPorGrupo(grupo, titulo, conteudo);
			}
			
			fw.setPath("/home.do");
			
			request.setAttribute("msg", "E-mail enviado com sucesso.");
								
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
			
			fw.setPath("/");
			
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
		
		try {			
			
			fw.setPath("/");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
