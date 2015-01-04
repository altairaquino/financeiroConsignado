package com.grupoexata.bancario.struts.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.dao.ModelBanco;
import com.grupoexata.bancario.dao.ModelProduto;
import com.grupoexata.bancario.struts.bean.BeanProduto;
import com.grupoexata.bancario.struts.form.FormProduto;
import com.grupoexata.bancario.utils.GeraRelatorio;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionProduto extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACPD:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			request.setAttribute("ls_produto", ModelProduto.getInstance().getProdutos());
			fw.setPath("/produtoLista.do");
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
			
			request.getSession().removeAttribute("formProduto");
			request.getSession().setAttribute("ls_banco", ModelBanco.getInstance().getBancos());
			fw.setPath("/produtoNovo.do");
			
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
		FormProduto formProduto = (FormProduto)form;
		try {
			
			String pdncodg = request.getParameter("pdncodg");
			
			request.getSession().setAttribute("ls_banco",ModelBanco.getInstance().getBancos());
			BeanProduto produto = ModelProduto.getInstance().getProduto(Integer.parseInt(pdncodg));
			BeanUtils.copyProperties(formProduto, produto);
			
			fw.setPath("/produtoEditar.do");
			
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
		FormProduto formProduto = (FormProduto)form;
		ActionMessages erros = new ActionMessages();
		
		if (!ValidaObjeto.validaInteiro(formProduto.getPdnnumr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Número do Produto é obrigatório."));
		}
		
		if (!ValidaObjeto.validaString(formProduto.getPdcdesc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome do Produto é obrigatório."));
		}
		
		if (!ValidaObjeto.validaString(formProduto.getPdcabrv())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Abreviação do Nome do Produto é obrigatório."));
		}
		
		
		if (!erros.isEmpty()){
			fw.setPath("/produtoNovo.do");			
		}else{
		
			try {
				
				BeanProduto produto = new BeanProduto();
				BeanUtils.copyProperties(produto, formProduto);
				
				ModelProduto.getInstance().inserir(produto);			
							
				request.getSession().removeAttribute("formProduto");
				request.getSession().removeAttribute("ls_banco");
				
				fw = lista(mapping, form, request, response);
				
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Produto Cadastrado com Sucesso!"));
				
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
		FormProduto formProduto = (FormProduto)form;
		ActionMessages erros = new ActionMessages();
		
		if (!ValidaObjeto.validaInteiro(formProduto.getPdnnumr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Número do Produto é obrigatório."));
		}
		
		if (!ValidaObjeto.validaString(formProduto.getPdcdesc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome do Produto é obrigatório."));
		}
		
		if (!ValidaObjeto.validaString(formProduto.getPdcabrv())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Abreviação do Nome do Produto é obrigatório."));
		}
		
		
		if (!erros.isEmpty()){
			fw.setPath("/produtoEditar.do");
		}else{
		
			try {
				
				BeanProduto produto = new BeanProduto();
				BeanUtils.copyProperties(produto, formProduto);
				
				ModelProduto.getInstance().update(produto);				
							
				request.getSession().removeAttribute("formProduto");
				request.getSession().removeAttribute("ls_banco");
				
				fw = lista(mapping, form, request, response);
								
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}		
		saveErrors(request, erros);
		
		return fw;
	}
	
	
	public ActionForward relatorioGeral(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		try {
			
			Map<Object,Object> map = new HashMap<Object, Object>();
			
			map.put("REPORT_NAME", "tabelaProdutos");
			
			GeraRelatorio.geracao(request, response, map, false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
