package com.grupoexata.bancario.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.dao.ModelEntidade;
import com.grupoexata.bancario.dao.ModelTabelaAngariador;
import com.grupoexata.bancario.dao.ModelTabelaProduto;
import com.grupoexata.bancario.struts.bean.BeanTabelaAngariador;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionTabelaAngariador extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACTG:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			String enncodg = request.getParameter("enncodg");
			request.setAttribute("angariador", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			request.setAttribute("ls_tabela_angariador", ModelTabelaAngariador.getInstance().getTabelasDoAngariador(Integer.parseInt(enncodg)));
			fw.setPath("/tabelaAngariadorLista.do");
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
		
		String [] tgncodg = request.getParameterValues("tgncodg");
		String [] tgncoms = request.getParameterValues("tgncoms");
		String tgncgen = request.getParameter("tgncgen");
		
		try {
			
			for (int i = 0; i < tgncoms.length; i++) {
				if (ValidaObjeto.validaFloat(Utils.converteFloatBR(tgncoms[i]))) {
					BeanTabelaAngariador tabela = new BeanTabelaAngariador();
					tabela.setTgncoms(tgncoms[i]);
					tabela.setTgncodg(tgncodg[i]);
					ModelTabelaAngariador.getInstance().update(tabela);
				}
			}
						
			request.getSession().removeAttribute("formComissaoContrato");
			
			fw.setPath("/actionEntidade.do?m=opcoesAngariador&enncodg="+tgncgen);
								
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;
	}
	
	public ActionForward updateComissao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0003";
		
		ActionForward fw = new ActionForward();
		
		String [] tgncodg = request.getParameterValues("tgncodg");
		String [] tgncoms = request.getParameterValues("tgncoms");
		String pdncodg = request.getParameter("pdncodg");
				
		try {
			
			for (int i = 0; i < tgncoms.length; i++) {
				if (ValidaObjeto.validaFloat(Utils.converteFloatBR(tgncoms[i]))) {
					BeanTabelaAngariador tabela = new BeanTabelaAngariador();
					tabela.setTgncoms(tgncoms[i]);
					tabela.setTgncodg(tgncodg[i]);
					ModelTabelaAngariador.getInstance().update(tabela);
				}
			}
						
			request.getSession().removeAttribute("formComissaoContrato");
			
			fw.setPath("/actionTabelaProduto.do?m=lista&pdncodg="+pdncodg);
											
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;
	}
	
	public ActionForward listaAng(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0004";
		ActionForward fw = new ActionForward();
		
		try {			
			
			String tpncodg = request.getParameter("tpncodg");
			
			request.setAttribute("ls_tabelaangariador", ModelTabelaAngariador.getInstance().getTabelasDoProduto(Integer.parseInt(tpncodg)));
			
			request.setAttribute("tabelaproduto", ModelTabelaProduto.getInstance().getTabelaProduto(Integer.parseInt(tpncodg)));
			
			fw.setPath("/tabelaProdutoComissaoAng.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
