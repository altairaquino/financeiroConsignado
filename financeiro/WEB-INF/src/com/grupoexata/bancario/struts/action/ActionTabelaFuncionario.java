package com.grupoexata.bancario.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.dao.ModelFuncionarioAgencia;
import com.grupoexata.bancario.dao.ModelTabelaFuncionario;
import com.grupoexata.bancario.struts.bean.BeanTabelaFuncionario;

public class ActionTabelaFuncionario extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACTFC:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			String fcancodg = request.getParameter("fcancodg");
			request.setAttribute("funcionarioagencia", ModelFuncionarioAgencia.getInstance().getFuncionarioAgencia(Integer.parseInt(fcancodg)));
			request.setAttribute("ls_tabelafuncionario", ModelTabelaFuncionario.getInstance().getTabelasDoFuncionario(Integer.parseInt(fcancodg)));
			
			fw.setPath("/tabelaFuncionarioLista.do");
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
		
		String [] tfcncodg = request.getParameterValues("tfcncodg");
		String [] tfcncoms = request.getParameterValues("tfcncoms");
		String [] tfcncm70 = request.getParameterValues("tfcncm70");
		String [] tfcncm100 = request.getParameterValues("tfcncm100");
		String [] tfcncm150 = request.getParameterValues("tfcncm150");
		String tfcncgen = request.getParameter("tfcncgfca");
		
		try {
			
			for (int i = 0; i < tfcncoms.length; i++) {
				BeanTabelaFuncionario tabela = new BeanTabelaFuncionario();
				tabela.setTfcncoms(tfcncoms[i]);
				tabela.setTfcncm70(tfcncm70[i]);
				tabela.setTfcncm100(tfcncm100[i]);
				tabela.setTfcncm150(tfcncm150[i]);
				tabela.setTfcncodg(tfcncodg[i]);
				ModelTabelaFuncionario.getInstance().update(tabela);			
			}
						
			request.getSession().removeAttribute("formTabelaFuncionario");
			
			fw.setPath("/actionFuncionarioAgencia.do?m=lista&enncodg="+tfcncgen);
								
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;
	}
	
	
}
