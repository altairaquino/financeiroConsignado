package com.grupoexata.bancario.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.dao.ModelEntidade;
import com.grupoexata.bancario.dao.ModelLog;

public class ActionLog extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACLG:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();		
		try {
			String enncodg = request.getParameter("enncodg");
			
			
			request.setAttribute("entidade", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			request.setAttribute("ls_log", ModelLog.getInstance().getLogsUsuario(Integer.parseInt(enncodg), 30));
			
			fw.setPath("/logLista.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}

}
