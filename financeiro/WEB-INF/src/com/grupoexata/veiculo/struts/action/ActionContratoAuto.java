package com.grupoexata.veiculo.struts.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.dao.ModelEmpresa;
import com.grupoexata.bancario.dao.ModelEntidade;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.utils.CPF;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.veiculo.dao.ModelContratoAuto;
import com.grupoexata.veiculo.dao.ModelPgtoContratoAuto;
import com.grupoexata.veiculo.struts.bean.BeanContratoAuto;
import com.grupoexata.veiculo.struts.form.FormContratoAuto;

public class ActionContratoAuto extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACTG:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
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
		
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0002";
		ActionForward fw = new ActionForward();
		FormContratoAuto formContratoAuto = (FormContratoAuto)form;
		ActionMessages erros = new ActionMessages();
		BeanEntidade cliente = null;
		try {			
			
			if (!ValidaObjeto.validaString(formContratoAuto.getCtacdocm())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF do Cliente é obrigatório."));
			}else{
				if (!CPF.validar(ValidaObjeto.removeCharOfInteger(formContratoAuto.getCtacdocm()))){
					erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF do Cliente é inválido."));
				}else{
					cliente = ModelEntidade.getInstance().getBeanEntidadePorCPF(ValidaObjeto.removeCharOfInteger(formContratoAuto.getCtacdocm()), 1);
					if (cliente == null){
						erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF de Cliente não cadastrado no sistema."));
					}
				}
			}
			
			if (!ValidaObjeto.validaString(formContratoAuto.getCtacnumr())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Número do Contrato é obrigatório."));
			}
			
			if (!ValidaObjeto.validaData(formContratoAuto.getCtadbase())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data base é obrigatório ou foi informada inválida."));
			}
			
			if (!ValidaObjeto.validaInteiro(formContratoAuto.getCtancgop())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Operador é obrigatório."));
			}
			
			if (!ValidaObjeto.validaInteiro(formContratoAuto.getCtancglj())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Loja de Veículo é obrigatória."));
			}
			
			if (!ValidaObjeto.validaString(formContratoAuto.getCtayvalr()) || !ValidaObjeto.validaFloat(Utils.converteFloatBR(formContratoAuto.getCtayvalr()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Valor do contrato está incorreto ou não foi informado."));
			}
			
			if (!ValidaObjeto.validaString(formContratoAuto.getCtayvlpc()) || !ValidaObjeto.validaFloat(Utils.converteFloatBR(formContratoAuto.getCtayvlpc()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Valor da parcela está incorreto ou não foi informado."));
			}
						
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/contratoAutoEditar.do");
			}else{
				
				formContratoAuto.setCtancgen(cliente.getEnncodg());
				
				BeanContratoAuto beanContratoAuto = new BeanContratoAuto();
				BeanUtils.copyProperties(beanContratoAuto, formContratoAuto);
				
				ModelContratoAuto.getInstance().update(beanContratoAuto);
				
				request.getSession().removeAttribute("ls_pgtocontratoauto");
				request.getSession().removeAttribute("formContratoAuto");
				request.getSession().removeAttribute("ls_empresa");
				
				fw.setPath("/actionContratoAuto.do?m=dados&ctacdocm="+beanContratoAuto.getCtancodg());
				
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
		FormContratoAuto formContratoAuto = (FormContratoAuto)form;
		try {
			String ctancodg = request.getParameter("ctancodg");
			BeanContratoAuto beanContratoAuto = ModelContratoAuto.getInstance().getContratoAuto(Integer.parseInt(ctancodg));
			
			BeanUtils.copyProperties(formContratoAuto, beanContratoAuto);
			
			request.getSession().setAttribute("ls_pgtocontratoauto", ModelPgtoContratoAuto.getInstance().getPgtosContratoAuto());
			request.getSession().setAttribute("ls_empresa", ModelEmpresa.getInstance().getEmpresas());
			
			fw.setPath("/contratoAutoEditar.do");
			
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
			request.getSession().setAttribute("ls_pgtocontratoauto", ModelPgtoContratoAuto.getInstance().getPgtosContratoAuto());
			
			request.getSession().setAttribute("ls_empresa", ModelEmpresa.getInstance().getEmpresas());
			
			request.getSession().removeAttribute("formContratoAuto");
			
			fw.setPath("/contratoAutoNovo.do");
			
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
		FormContratoAuto formContratoAuto = (FormContratoAuto)form;
		ActionMessages erros = new ActionMessages();
		BeanEntidade cliente = null;
		try {			
			
			if (!ValidaObjeto.validaString(formContratoAuto.getCtacdocm())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF do Cliente é obrigatório."));
			}else{
				if (!CPF.validar(ValidaObjeto.removeCharOfInteger(formContratoAuto.getCtacdocm()))){
					erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF do Cliente é inválido."));
				}else{
					cliente = ModelEntidade.getInstance().getBeanEntidadePorCPF(ValidaObjeto.removeCharOfInteger(formContratoAuto.getCtacdocm()), 1);
					if (cliente == null){
						erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF de Cliente não cadastrado no sistema."));
					}
				}
			}
			
			if (!ValidaObjeto.validaString(formContratoAuto.getCtacnumr())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Número do Contrato é obrigatório."));
			}
			
			if (!ValidaObjeto.validaData(formContratoAuto.getCtadbase())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data base é obrigatório ou foi informada inválida."));
			}
			
			if (!ValidaObjeto.validaInteiro(formContratoAuto.getCtancgop())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Operador é obrigatório."));
			}
			
			if (!ValidaObjeto.validaInteiro(formContratoAuto.getCtancglj())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Loja de Veículo é obrigatória."));
			}
			
			if (!ValidaObjeto.validaString(formContratoAuto.getCtayvalr()) || !ValidaObjeto.validaFloat(Utils.converteFloatBR(formContratoAuto.getCtayvalr()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Valor do contrato está incorreto ou não foi informado."));
			}
			
			if (!ValidaObjeto.validaString(formContratoAuto.getCtanplan())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Plano do Contrato é obrigatório."));
			}
			
			if (!ValidaObjeto.validaString(formContratoAuto.getCtayvlpc()) || !ValidaObjeto.validaFloat(Utils.converteFloatBR(formContratoAuto.getCtayvlpc()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Valor da parcela está incorreto ou não foi informado."));
			}
			
			
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/contratoAutoNovo.do");
			}else{
				
				formContratoAuto.setCtancgen(cliente.getEnncodg());
				
				BeanContratoAuto beanContratoAuto = new BeanContratoAuto();
				BeanUtils.copyProperties(beanContratoAuto, formContratoAuto);
				
				ModelContratoAuto.getInstance().inserir(beanContratoAuto);
				
				request.getSession().removeAttribute("ls_pgtocontratoauto");
				request.getSession().removeAttribute("formContratoAuto");
				request.getSession().removeAttribute("ls_empresa");
				
				fw.setPath("/actionContratoAuto.do?m=pesquisaCPF&ctacdocm="+beanContratoAuto.getCtacdocm());
				
			}
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward pesquisaCPF(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0006";
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		
		try {
			
			String ctacdocm = request.getParameter("ctacdocm");
			
			ArrayList<BeanContratoAuto> contratos = ModelContratoAuto.getInstance().getContratoAutoPorCPF(ctacdocm);
			
			if (!contratos.isEmpty()){
				request.setAttribute("ls_contratoauto", contratos);
				fw.setPath("/contratoAutoLista.do");
			}else{
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nenhum contrato encontrado!"));
				saveErrors(request, erros);
				fw.setPath("/contratoAutoPesquisaCPF.do");
			}						
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;	
	}
	
	public ActionForward opcoes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();
		
		try {			
			
			String ctancodg = request.getParameter("ctancodg"); 
			
			request.setAttribute("contratoauto", ModelContratoAuto.getInstance().getContratoAuto(Integer.parseInt(ctancodg)));
			
			fw.setPath("/contratoAutoPage.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward dados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0008";
		ActionForward fw = new ActionForward();
		
		try {			
			
			String ctancodg = request.getParameter("ctancodg");
			
			request.setAttribute("contratoauto", ModelContratoAuto.getInstance().getContratoAuto(Integer.parseInt(ctancodg)));
			
			fw.setPath("/contratoAutoDados.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward listaPendente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0009";
		ActionForward fw = new ActionForward();
		FormContratoAuto formContratoAuto = (FormContratoAuto)form;
		try {
			
			if (formContratoAuto.getCtancgep() != null){
				request.setAttribute("ls_contratoauto", ModelContratoAuto.getInstance().getContratosPendentes(Integer.parseInt(formContratoAuto.getCtancgep())));
			}else{
				request.setAttribute("ls_contratoauto", ModelContratoAuto.getInstance().getContratosPendentes(1));
			}
			
			request.setAttribute("ls_empresa", ModelEmpresa.getInstance().getEmpresas());
			
			fw.setPath("/contratoAutoListaPendente.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward editarQuest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0010";
		ActionForward fw = new ActionForward();
		FormContratoAuto formContratoAuto = (FormContratoAuto)form;
		try {
			String ctancodg = request.getParameter("ctancodg");
			BeanContratoAuto beanContratoAuto = ModelContratoAuto.getInstance().getContratoAuto(Integer.parseInt(ctancodg));
			
			BeanUtils.copyProperties(formContratoAuto, beanContratoAuto);
			
			fw.setPath("/contratoAutoEditarQuest.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward updateQuest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0011";
		ActionForward fw = new ActionForward();
		FormContratoAuto formContratoAuto = (FormContratoAuto)form;
		try {
			BeanContratoAuto beanContratoAuto = new BeanContratoAuto();
			BeanUtils.copyProperties(beanContratoAuto, formContratoAuto);
			
			ModelContratoAuto.getInstance().updateQuest(beanContratoAuto);
			
			fw = listaPendente(mapping, formContratoAuto, request, response);
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}

}
