package com.grupoexata.bancario.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.dao.ModelComissaoSinergia;
import com.grupoexata.bancario.dao.ModelTipoFuncAgencia;
import com.grupoexata.bancario.struts.bean.BeanComissaoSinergia;

public class ActionComissaoSinergia extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACCMS:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			String tfancodg = request.getParameter("cmsncgtfc");
			
			if (tfancodg != null){
				request.setAttribute("ls_comissaosinergia", ModelComissaoSinergia.getInstance().getComissaoPorCargo(Integer.parseInt(tfancodg)));
				request.setAttribute("tipofuncagencia", ModelTipoFuncAgencia.getInstance().getTipoFuncAgencia(Integer.parseInt(tfancodg)));
			}else{
				request.setAttribute("ls_comissaosinergia", ModelComissaoSinergia.getInstance().getComissaoPorCargo(2));
				request.setAttribute("tipofuncagencia", ModelTipoFuncAgencia.getInstance().getTipoFuncAgencia(2));
			}		
			
			fw.setPath("/comissaoSinergiaLista.do");
			
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
		
		String [] cmsncodg = request.getParameterValues("cmsncodg");
		String [] cmsnperc = request.getParameterValues("cmsnperc");
		String [] cmsnpc100 = request.getParameterValues("cmsnpc100");
		String [] cmsnpc150 = request.getParameterValues("cmsnpc150");
		String [] cmsnpc151 = request.getParameterValues("cmsnpc151");
		String cmsncgtfc = request.getParameter("cmsncgtfc");
		
		try {
			
			for (int i = 0; i < cmsnperc.length; i++) {
				BeanComissaoSinergia comissao = new BeanComissaoSinergia();
				comissao.setCmsnperc(cmsnperc[i]);
				comissao.setCmsnpc100(cmsnpc100[i]);
				comissao.setCmsnpc150(cmsnpc150[i]);
				comissao.setCmsnpc151(cmsnpc151[i]);
				comissao.setCmsncodg(cmsncodg[i]);
				ModelComissaoSinergia.getInstance().update(comissao);
			}
						
			request.getSession().removeAttribute("formComissaoSinergia");
			
			fw.setPath("/actionComissaoSinergia.do?m=lista&cmsncgtfc="+cmsncgtfc);
								
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;
	}	
	

}
