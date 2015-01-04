package com.grupoexata.bancario.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.dao.ModelCidade;
import com.grupoexata.bancario.dao.ModelFilial;
import com.grupoexata.bancario.dao.ModelTipoLogradouro;
import com.grupoexata.bancario.struts.bean.BeanFilial;
import com.grupoexata.bancario.struts.form.FormFilial;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionFilial extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACFL:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();		
		try {
			
			request.setAttribute("ls_filial", ModelFilial.getInstance().getFiliais());
			
			fw.setPath("/filialLista.do");
			
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
		FormFilial formFilial = (FormFilial)form;
		ActionMessages erros = new ActionMessages();
		try {
			
			if (!ValidaObjeto.validaString(formFilial.getFlcnome())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nome é requerido."));
			}
			/*
			if (!ValidaObjeto.validaString(formFilial.getFlcresp())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Responsável é requerido."));
			}
			if (!ValidaObjeto.validaString(formFilial.getFlcfone())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Telefone é requerido."));
			}
			if (!ValidaObjeto.validaEmail(formFilial.getFlcmail())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","E-mail inválido."));
			}
			if (!ValidaObjeto.validaString(formFilial.getFlclogr())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Endereço é requerido."));
			}
			if (!ValidaObjeto.validaString(formFilial.getFlccep())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","CEP é requerido."));
			}
			if (!ValidaObjeto.validaString(formFilial.getFlcbair())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Bairro é requerido."));
			}
			*/
			if (formFilial.getFlncgcd().equals("-1")){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Cidade é requerida!"));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/filialEditar.do");
			}else{
				
				BeanFilial beanFilial = new BeanFilial();
				BeanUtils.copyProperties(beanFilial, formFilial);
				
				ModelFilial.getInstance().update(beanFilial);
				
				request.getSession().removeAttribute("ls_cidade");
				request.getSession().removeAttribute("ls_estado");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("formFilial");
				
				fw = lista(mapping, formFilial, request, response);
			}
			
			
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
		FormFilial formFilial = (FormFilial)form;
		try {			
			String flncodg = request.getParameter("flncodg");
			
			BeanFilial beanFilial = ModelFilial.getInstance().getFilial(Integer.parseInt(flncodg));
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_cidade", ModelCidade.getInstance().getCidadesDoEstado(beanFilial.getFlcufcd()));
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			
			BeanUtils.copyProperties(formFilial, beanFilial);
			
			fw.setPath("/filialEditar.do");
			
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
			
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			
			request.getSession().removeAttribute("formFilial");
			
			fw.setPath("/filialNovo.do");
			
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
		FormFilial formFilial = (FormFilial)form;
		ActionMessages erros = new ActionMessages();
		try {
			
			if (!ValidaObjeto.validaString(formFilial.getFlcnome())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nome é requerido."));
			}
			/*
			if (!ValidaObjeto.validaString(formFilial.getFlcresp())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Responsável é requerido."));
			}
			if (!ValidaObjeto.validaString(formFilial.getFlcfone())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Telefone é requerido."));
			}
			if (!ValidaObjeto.validaEmail(formFilial.getFlcmail())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","E-mail inválido."));
			}
			if (!ValidaObjeto.validaString(formFilial.getFlclogr())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Endereço é requerido."));
			}
			if (!ValidaObjeto.validaString(formFilial.getFlccep())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","CEP é requerido."));
			}
			if (!ValidaObjeto.validaString(formFilial.getFlcbair())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Bairro é requerido."));
			}
			*/
			if (formFilial.getFlncgcd().equals("-1")){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Cidade é requerida!"));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/filialNovo.do");
			}else{
				
				BeanFilial beanFilial = new BeanFilial();
				BeanUtils.copyProperties(beanFilial, formFilial);
				
				ModelFilial.getInstance().inserir(beanFilial);
				
				request.getSession().removeAttribute("ls_cidade");
				request.getSession().removeAttribute("ls_estado");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("formFilial");
				
				fw = lista(mapping, formFilial, request, response);
			}
			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward pesquisaFilialWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0010";
	
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormFilial formFilial = (FormFilial) form;
		
		if (!ValidaObjeto.validaString(formFilial.getFlcnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome da Filial."));
		}else{
			if (formFilial.getFlcnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome da filial."));
			}
		}
		
				
		try {
			
			if (erros.isEmpty()) {
				List<BeanFilial> listaFilial = ModelFilial.getInstance().getPesquisaFilial(formFilial.getFlcnome());
				if (listaFilial.isEmpty()) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nenhuma filial encontrada!"));
				} else {
					request.setAttribute("ls_filial", listaFilial);
				}
			}
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formFilial");
		
		fw.setPath("/filialPesquisaWindow.do");
		saveErrors(request, erros);
		
		return fw;
	}
	

}
