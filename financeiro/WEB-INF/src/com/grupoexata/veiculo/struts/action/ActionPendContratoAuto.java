package com.grupoexata.veiculo.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.veiculo.dao.ModelContratoAuto;
import com.grupoexata.veiculo.dao.ModelPendContratoAuto;
import com.grupoexata.veiculo.struts.bean.BeanPendContratoAuto;

public class ActionPendContratoAuto extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACCTA:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			String ctancodg = request.getParameter("ctancodg");
			
			request.setAttribute("ls_pendcontratoauto", ModelPendContratoAuto.getInstance().getPendenciasContratoAuto(Integer.parseInt(ctancodg)));
			request.setAttribute("contratoauto", ModelContratoAuto.getInstance().getContratoAuto(Integer.parseInt(ctancodg)));
			
			fw.setPath("/pendContratoAutoLista.do");
			
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
	
	public ActionForward baixar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0006";
		ActionForward fw = new ActionForward();
		
		try {		
			
			String pdcncodg = request.getParameter("pdcncodg");
			BeanPendContratoAuto beanPendContratoAuto = ModelPendContratoAuto.getInstance().getPendContratoAuto(Integer.parseInt(pdcncodg));
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			beanPendContratoAuto.setPdcncgen(usuario.getEnncodg());
			
			ModelPendContratoAuto.getInstance().baixar(beanPendContratoAuto);
			
			fw.setPath("/actionPendContratoAuto.do?m=lista&ctancodg="+beanPendContratoAuto.getPdcnctau());
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		return fw;
	}
	

}
