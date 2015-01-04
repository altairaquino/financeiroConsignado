package com.grupoexata.folha.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.folha.bean.BeanFiltroajcust;
import com.grupoexata.folha.dao.ModelFiltroajcust;

public class ActionFiltroajcust extends DispatchAction {

	private final static String ERRO_CLASS = "ACFAC:";
	private ModelFiltroajcust md = new ModelFiltroajcust();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listaFiltroajcust", md.list(request.getParameter("filncodg")));
			fw.setPath("/filtroajcustLista.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}finally{
			//md.closeConnection();
		}
		return fw;
	}	

	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0002";
		ActionForward fw = new ActionForward();
		try {
			String filncodg = request.getParameter("filncodg");
			List<BeanFiltroajcust> list = md.list(filncodg);
			List<BeanFiltroajcust> list_add = new ArrayList<BeanFiltroajcust>();
			for (BeanFiltroajcust fac : list) {
				String nome = fac.getFacncgfil() + "_" + fac.getFacncgajc();				
				String ativo = request.getParameter("faclatv_" + nome);
				if(ativo == null || ativo.equals(""))ativo = "F";
				if(!fac.getFaclatv().equals(ativo)){
					if(ativo == "F"){
						fac.setFacncodg(null);
					}
					list_add.add(fac);
				}
			}
			if(!list_add.isEmpty()){
				md.add(list_add);
				request.setAttribute("msg", "Cadastro realizado com sucesso!");
			}
			fw = lista(mapping, form, request, response);
			fw.setPath("/actionFiltroajcust.do?m=lista&filncodg=" + filncodg);
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}finally{
			//md.closeConnection();
		}
		return fw;
	}
	
}
