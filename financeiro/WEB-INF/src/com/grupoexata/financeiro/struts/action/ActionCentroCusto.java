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
import com.grupoexata.financeiro.dao.ModelCentroCusto;
import com.grupoexata.financeiro.dao.ModelOrigemEntrada;
import com.grupoexata.financeiro.struts.bean.BeanCentroCusto;
import com.grupoexata.financeiro.struts.bean.BeanOrigemCentro;
import com.grupoexata.financeiro.struts.form.FormCentroCusto;


public class ActionCentroCusto extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACCR:";
	
	public ActionForward novo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		try {
			
			request.getSession().setAttribute("ls_origementrada", ModelOrigemEntrada.getInstance().getOrigensEntrada());
			
			request.getSession().removeAttribute("formCentroCusto");
			
			fw.setPath("/centroCustoNovo.do");
			
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
		final String METODO = "0002";
		ActionForward fw = new ActionForward();
		FormCentroCusto formCentroCusto = (FormCentroCusto)form;
		try {
			
			String crncodg = request.getParameter("crncodg");
			BeanCentroCusto beanCentroCusto = ModelCentroCusto.getInstance().getCentroCusto(Integer.parseInt(crncodg));
			
			BeanUtils.copyProperties(formCentroCusto, beanCentroCusto);
			
			request.getSession().setAttribute("ls_origementrada", ModelOrigemEntrada.getInstance().getOrigensEntrada());
			
			fw.setPath("/centroCustoEditar.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		return fw;
	}
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0003";
		ActionForward fw = new ActionForward();
		try {
			
			request.setAttribute("ls_centrocusto", ModelCentroCusto.getInstance().getCentrosCusto());
			
			fw.setPath("/centroCustoLista.do");
			
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
		final String METODO = "0004";
		ActionForward fw = new ActionForward();
		FormCentroCusto formCentroCusto = (FormCentroCusto)form;
		ActionMessages erros = new ActionMessages();
		try {
			
			if (!ValidaObjeto.validaString(formCentroCusto.getCrcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nome do Centro de Custo é obrigatório."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/centroCustoNovo.do");
			}else{
				
				BeanCentroCusto beanCentroCusto = new BeanCentroCusto();
				BeanUtils.copyProperties(beanCentroCusto, formCentroCusto);
				
				ModelCentroCusto.getInstance().inserir(beanCentroCusto);
				
				request.getSession().removeAttribute("formCentroCusto");
				
				fw = lista(mapping, formCentroCusto, request, response);
				
			}			
			
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
		final String METODO = "0005";
		ActionForward fw = new ActionForward();
		FormCentroCusto formCentroCusto = (FormCentroCusto)form;
		ActionMessages erros = new ActionMessages();
		try {
			
			if (!ValidaObjeto.validaString(formCentroCusto.getCrcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nome do Centro de Custo é obrigatório."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/centroCustoEditar.do");
			}else{
				
				BeanCentroCusto beanCentroCusto = new BeanCentroCusto();
				BeanUtils.copyProperties(beanCentroCusto, formCentroCusto);
				
				ModelCentroCusto.getInstance().update(beanCentroCusto);
				
				request.getSession().removeAttribute("formCentroCusto");
				
				fw = lista(mapping, formCentroCusto, request, response);
				
			}
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS + METODO);
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
		String orcncgore = request.getParameter("orcncgore");
		String orcncgcr = request.getParameter("orcncgcr");
		
		try {			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			BeanOrigemCentro beanOrigemCentro = new BeanOrigemCentro(); 
			beanOrigemCentro.setOrcncgcr(orcncgcr);
			beanOrigemCentro.setOrcncgore(orcncgore);
			
			if (usuario != null && orcncgore != null && orcncgcr != null){			
				ModelCentroCusto.getInstance().ativaDesativa(beanOrigemCentro);
			}
			
			fw.setPath("/actionCentroCusto.do?m=lista");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}

}
