package com.grupoexata.bancario.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.dao.ModelProduto;
import com.grupoexata.bancario.dao.ModelTabelaProduto;
import com.grupoexata.bancario.struts.bean.BeanTabelaProduto;
import com.grupoexata.bancario.struts.form.FormTabelaProduto;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionTabelaProduto extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACTP:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {			
			
			String pdncodg = request.getParameter("pdncodg");
			
			request.getSession().setAttribute("produto", ModelProduto.getInstance().getProduto(Integer.parseInt(pdncodg)));
			request.setAttribute("ls_tabelaproduto", ModelTabelaProduto.getInstance().getTabelaDoProduto(Integer.parseInt(pdncodg)));
			
			fw.setPath("/tabelaProdutoLista.do");
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
		final String METODO = "0002";
		
		ActionForward fw = new ActionForward();
		try {
			
			request.getSession().removeAttribute("formTabelaProduto");
						
			fw.setPath("/tabelaProdutoNovo.do");
			
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
		FormTabelaProduto formTabelaProduto = (FormTabelaProduto)form;
		try {
			
			String pdncodg = request.getParameter("pdncodg");
						
			BeanTabelaProduto tabelaproduto = ModelTabelaProduto.getInstance().getTabelaProduto(Integer.parseInt(pdncodg));
			BeanUtils.copyProperties(formTabelaProduto, tabelaproduto);
			
			fw.setPath("/tabelaProdutoEditar.do");
			
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
		FormTabelaProduto formTabelaProduto = (FormTabelaProduto)form;
		ActionMessages erros = new ActionMessages();
		
		if (!ValidaObjeto.validaString(formTabelaProduto.getTpcdesc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome da TabelaProduto é obrigatória."));
		}
		
		if (!ValidaObjeto.validaString(formTabelaProduto.getTpncare())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Carência da TabelaProduto é obrigatória."));
		}		
		
		if (!erros.isEmpty()){
			fw.setPath("/tabelaProdutoNovo.do");			
		}else{
		
			try {
				
				BeanTabelaProduto tabelaproduto = new BeanTabelaProduto();
				BeanUtils.copyProperties(tabelaproduto, formTabelaProduto);
				
				ModelTabelaProduto.getInstance().inserir(tabelaproduto);			
							
				request.getSession().removeAttribute("formTabelaProduto");
								
				fw.setPath("/actionTabelaProduto.do?m=lista&pdncodg="+formTabelaProduto.getTpncgpd());
				
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","TabelaProduto Cadastrado com Sucesso!"));
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0005";
		
		ActionForward fw = new ActionForward();
		FormTabelaProduto formTabelaProduto = (FormTabelaProduto)form;
		ActionMessages erros = new ActionMessages();
		
		if (!ValidaObjeto.validaString(formTabelaProduto.getTpcdesc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome da TabelaProduto é obrigatória."));
		}
		
		if (!ValidaObjeto.validaString(formTabelaProduto.getTpncare())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Carência da TabelaProduto é obrigatória."));
		}
		
		
		if (!erros.isEmpty()){
			fw.setPath("/tabelaProdutoEditar.do");
		}else{
		
			try {
				
				BeanTabelaProduto tabelaproduto = new BeanTabelaProduto();
				BeanUtils.copyProperties(tabelaproduto, formTabelaProduto);
				
				ModelTabelaProduto.getInstance().update(tabelaproduto);		
							
				request.getSession().removeAttribute("formTabelaProduto");
								
				fw.setPath("/actionTabelaProduto.do?m=lista&pdncodg="+formTabelaProduto.getTpncgpd());
								
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}		
		saveErrors(request, erros);
		
		return fw;
	}
	

}
