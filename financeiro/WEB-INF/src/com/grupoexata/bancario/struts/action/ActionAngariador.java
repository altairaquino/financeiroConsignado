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

import com.grupoexata.bancario.dao.ModelAngariador;
import com.grupoexata.bancario.dao.ModelBanco;
import com.grupoexata.bancario.dao.ModelRegional;
import com.grupoexata.bancario.dao.ModelTipoAngariador;
import com.grupoexata.bancario.struts.bean.BeanAngariador;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.struts.form.FormAngariador;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionAngariador extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACAN:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			//request.setAttribute("ls_conta", ModelContaCorrente.getInstance().getContaCorrentesDaEntidade(enncodg)());
			fw.setPath("/contaCorrenteLista.do");
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
			
			request.getSession().removeAttribute("formContaCorrente");
			request.getSession().setAttribute("ls_banco", ModelBanco.getInstance().getBancos());
			fw.setPath("/contaCorrenteNovo.do");
			
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
		FormAngariador formAngariador = (FormAngariador)form;
		try {
			
			String enncodg = request.getParameter("enncodg");
			
			request.getSession().setAttribute("ls_tipoangariador", ModelTipoAngariador.getInstance().getTiposAngariador());
			request.getSession().setAttribute("ls_regional", ModelRegional.getInstance().getRegionais());
			
			BeanAngariador angariador = ModelAngariador.getInstance().getAngariador(Integer.parseInt(enncodg));
			BeanUtils.copyProperties(formAngariador, angariador);
			
			fw.setPath("/angariadorDadosEditar.do");
			
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
		FormAngariador formAngariador = (FormAngariador)form;
		ActionMessages erros = new ActionMessages();
		
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formAngariador.getAnnspre()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Spread do Angariador está incorreto."));
		}
		
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formAngariador.getAnnmeta()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Meta do Angariador está incorreto."));
		}		

		if (!ValidaObjeto.validaData(formAngariador.getAndcadt())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Cadastro está incorreta."));
		}		
		
		if (!erros.isEmpty()){
			fw.setPath("/angariadorDadosEditar.do");
		}else{
		
			try {
				
				BeanAngariador angariador = new BeanAngariador();
				BeanUtils.copyProperties(angariador, formAngariador);
				
				ModelAngariador.getInstance().update(angariador);	
							
				request.getSession().removeAttribute("formAngariador");
				request.getSession().removeAttribute("ls_tipoangariador");
				request.getSession().removeAttribute("ls_regional");
								
				fw.setPath("/actionAngariador.do?m=dados&enncodg="+angariador.getAnncgen());
								
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}		
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward dados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0006";
		ActionForward fw = new ActionForward();
		try {
			String enncodg = request.getParameter("enncodg");
			
			request.setAttribute("angariador2", ModelAngariador.getInstance().getAngariador(Integer.parseInt(enncodg)));
			fw.setPath("/angariadorDadosExibe.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}

	public ActionForward ajustarSpread(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();
		try {
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (usuario != null){
				ModelAngariador.getInstance().atualizaSpread();
				request.setAttribute("ls_angariador", ModelAngariador.getInstance().getAngariadores());
			}
			
			fw.setPath("/angariadorAnaliseComissao.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
