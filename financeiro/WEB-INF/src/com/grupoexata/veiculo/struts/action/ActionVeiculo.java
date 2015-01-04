package com.grupoexata.veiculo.struts.action;

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
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.veiculo.dao.ModelContratoAuto;
import com.grupoexata.veiculo.dao.ModelMarcaVeiculo;
import com.grupoexata.veiculo.dao.ModelTipoCombustivel;
import com.grupoexata.veiculo.dao.ModelVeiculo;
import com.grupoexata.veiculo.struts.bean.BeanContratoAuto;
import com.grupoexata.veiculo.struts.bean.BeanVeiculo;
import com.grupoexata.veiculo.struts.form.FormVeiculo;

public class ActionVeiculo extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACVEI:";
	
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
		ActionMessages erros = new ActionMessages();
		FormVeiculo formVeiculo = (FormVeiculo)form;
		try {
			
			if (!ValidaObjeto.validaString(formVeiculo.getVeicmode())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Modelo do Veículo é obrigatório."));
			}
			
			if (!ValidaObjeto.validaInteiro(formVeiculo.getVeinanof())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Ano de Fabricação do Veículo é obrigatório."));
			}
			
			if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formVeiculo.getVeiyvalr()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Valor de Mercado do Veículo é obrigatório."));
			}
			
			if (!ValidaObjeto.validaString(formVeiculo.getVeicchas())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Chassi do Veículo é obrigatório."));
			}			
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/veiculoEditar.do");
			}else{
				
				BeanVeiculo beanVeiculo = new BeanVeiculo();
				BeanUtils.copyProperties(beanVeiculo, formVeiculo);
				
				ModelVeiculo.getInstance().update(beanVeiculo);
				
				request.getSession().removeAttribute("ls_marcaveiculo");
				request.getSession().removeAttribute("ls_tipocombustivel");
				request.getSession().removeAttribute("ls_estado");
				
				BeanContratoAuto beanContratoAuto = ModelContratoAuto.getInstance().getContratoAutoPorVeiculo(Integer.parseInt(formVeiculo.getVeincodg()));
				
				fw.setPath("/actionVeiculo.do?m=dados&ctancodg="+beanContratoAuto.getCtancodg());
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
		FormVeiculo formVeiculo = (FormVeiculo)form;
		try {			
			String veincodg = request.getParameter("veincodg");
			
			BeanVeiculo beanVeiculo = ModelVeiculo.getInstance().getVeiculo(Integer.parseInt(veincodg));
			
			BeanUtils.copyProperties(formVeiculo, beanVeiculo);
			
			request.getSession().setAttribute("ls_marcaveiculo", ModelMarcaVeiculo.getInstance().getMarcasVeiculo());
			request.getSession().setAttribute("ls_tipocombustivel", ModelTipoCombustivel.getInstance().getTiposCombustivel());
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			
			
			fw.setPath("/veiculoEditar.do");
			
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
			
			fw.setPath("/");
			
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
		
		try {			
			
			fw.setPath("/");
			
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
		final String METODO = "0006";
		ActionForward fw = new ActionForward();
		try {
			
			String ctancodg = request.getParameter("ctancodg");
			
			BeanContratoAuto beanContratoAuto = ModelContratoAuto.getInstance().getContratoAuto(Integer.parseInt(ctancodg));
			BeanVeiculo beanVeiculo = ModelVeiculo.getInstance().getVeiculo(Integer.parseInt(beanContratoAuto.getCtanveic()));
			
			request.setAttribute("veiculo", beanVeiculo);
			request.setAttribute("contratoauto", beanContratoAuto);
			
			fw.setPath("/veiculoDados.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
