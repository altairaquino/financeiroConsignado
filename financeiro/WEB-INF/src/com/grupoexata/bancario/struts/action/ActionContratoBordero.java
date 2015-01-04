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

import com.grupoexata.bancario.dao.ModelBordero;
import com.grupoexata.bancario.dao.ModelCidade;
import com.grupoexata.bancario.dao.ModelContrato;
import com.grupoexata.bancario.dao.ModelContratoBordero;
import com.grupoexata.bancario.dao.ModelFilial;
import com.grupoexata.bancario.dao.ModelTipoLogradouro;
import com.grupoexata.bancario.struts.bean.BeanBordero;
import com.grupoexata.bancario.struts.bean.BeanContrato;
import com.grupoexata.bancario.struts.bean.BeanContratoBordero;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.struts.bean.BeanFilial;
import com.grupoexata.bancario.struts.form.FormContratoBordero;
import com.grupoexata.bancario.struts.form.FormFilial;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionContratoBordero extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACCBO:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();	
		try {
			String borncodg = request.getParameter("borncodg");
			
			request.setAttribute("bordero", ModelBordero.getInstance().getBordero(Integer.parseInt(borncodg)));
			
			request.setAttribute("ls_contratobordero", ModelContratoBordero.getInstance().getContratosDoBordero(Integer.parseInt(borncodg)));
			
			fw.setPath("/contratoBorderoLista.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
		
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0002";
		ActionForward fw = new ActionForward();
		try {
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			String ctbncodg = request.getParameter("ctbncodg");
			BeanContratoBordero beanContratoBordero = ModelContratoBordero.getInstance().getContratoBordero(Integer.parseInt(ctbncodg));
			
			if (usuario != null){
				ModelContratoBordero.getInstance().delete(beanContratoBordero);
			}			
			
			fw.setPath("/actionContratoBordero.do?m=lista&borncodg="+beanContratoBordero.getCtbncgbor());
			
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
		FormContratoBordero formContratoBordero = (FormContratoBordero)form;
		ActionMessages erros = new ActionMessages();
		try {
			
			BeanContrato contrato = null;
			
			if (!ValidaObjeto.validaNumeroContrato(formContratoBordero.getCtbnnrct())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Número do contrato inválido."));
			}else{
				contrato = ModelContrato.getInstance().getContratoPorNumero(formContratoBordero.getCtbnnrct()); 
				if (contrato == null){
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Contrato inexistente no sistema."));					
				}else{
					if (contrato.getCtdbxfi() == null){
						erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Contrato sem baixa de físico."));				
					}else{
						formContratoBordero.setCtbncgct(contrato.getCtncodg());
					}
					BeanContratoBordero ctb = new BeanContratoBordero();
					BeanUtils.copyProperties(ctb, formContratoBordero);
					if (ModelContratoBordero.getInstance().existeRegistro(ctb)){
						erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Contrato já incluido neste borderô."));
					}
				}
			}
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (!erros.isEmpty() || usuario == null){
				saveErrors(request, erros);
			}else{
				
				BeanContratoBordero beanContratoBordero = new BeanContratoBordero();
				BeanUtils.copyProperties(beanContratoBordero, formContratoBordero);
				
				ModelContratoBordero.getInstance().inserir(beanContratoBordero);
				
				request.getSession().removeAttribute("formContratoBordero");
				
			}
			
			fw.setPath("/actionContratoBordero.do?m=lista&borncodg="+formContratoBordero.getCtbncgbor());
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward pesquisa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0006";
	
		ActionForward fw = new ActionForward();
		FormContratoBordero formContratoBordero = (FormContratoBordero) form;
		
		try {
			
			List<BeanBordero> listaBordero = ModelContratoBordero.getInstance().getPesquisa(formContratoBordero.getCtbnnrct());
			request.setAttribute("ls_bordero", listaBordero);			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formContratoBordero");
		
		fw.setPath("/borderoLista.do");
		
		return fw;
	}
	
	public ActionForward confirma(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();
		try {
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			String ctbncodg = request.getParameter("ctbncodg");
			BeanContratoBordero beanContratoBordero = ModelContratoBordero.getInstance().getContratoBordero(Integer.parseInt(ctbncodg));
			
			if (usuario != null){
				ModelContratoBordero.getInstance().confirma(beanContratoBordero);
			}			
			
			fw.setPath("/actionContratoBordero.do?m=lista&borncodg="+beanContratoBordero.getCtbncgbor());
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
