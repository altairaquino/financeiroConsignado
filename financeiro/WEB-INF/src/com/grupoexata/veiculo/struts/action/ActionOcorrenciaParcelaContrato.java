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

import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.veiculo.dao.ModelContratoAuto;
import com.grupoexata.veiculo.dao.ModelOcorrenciaParcelaContrato;
import com.grupoexata.veiculo.dao.ModelParcelaContratoAuto;
import com.grupoexata.veiculo.struts.bean.BeanOcorrenciaParcelaContrato;
import com.grupoexata.veiculo.struts.bean.BeanParcelaContratoAuto;
import com.grupoexata.veiculo.struts.form.FormOcorrenciaParcelaContrato;
import com.grupoexata.veiculo.struts.form.FormParcelaContratoAuto;

public class ActionOcorrenciaParcelaContrato extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACOCO:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			String pccncodg = request.getParameter("pccncodg");
			
			BeanParcelaContratoAuto beanParcelaContratoAuto = ModelParcelaContratoAuto.getInstance().getParcelaContratoAuto(Integer.parseInt(pccncodg));
			
			request.setAttribute("ls_ocorrenciaparcelacontrato", ModelOcorrenciaParcelaContrato.getInstance().getOcorrenciasDaParcelaContrato(Integer.parseInt(pccncodg)));
			request.setAttribute("contratoauto", ModelContratoAuto.getInstance().getContratoAuto(Integer.parseInt(beanParcelaContratoAuto.getPccnctau())));
			request.setAttribute("parcelacontratoauto", beanParcelaContratoAuto);
			
			fw.setPath("/ocorrenciaParcelaContratoLista.do");
			
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
		FormOcorrenciaParcelaContrato formOcorrenciaParcelaContrato = (FormOcorrenciaParcelaContrato)form;
		try {	
			
			if (!ValidaObjeto.validaData(formOcorrenciaParcelaContrato.getOcodpraz())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Prazo é inválido."));
			}
			
			if (!ValidaObjeto.validaString(formOcorrenciaParcelaContrato.getOcocobsv())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Observação é obrigatório."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/ocorrenciaParcelaContratoEditar.do");
			}else{
				BeanOcorrenciaParcelaContrato beanOcorrenciaParcelaContrato = new BeanOcorrenciaParcelaContrato();
				BeanUtils.copyProperties(beanOcorrenciaParcelaContrato, formOcorrenciaParcelaContrato);
				
				ModelOcorrenciaParcelaContrato.getInstance().update(beanOcorrenciaParcelaContrato);
				
				request.getSession().removeAttribute("parcelacontratoauto");
				request.getSession().removeAttribute("formOcorrenciaParcelaContrato");
				
				fw.setPath("/actionOcorrenciaParcelaContrato.do?m=lista&pccncodg="+beanOcorrenciaParcelaContrato.getOconpcct());
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
		FormOcorrenciaParcelaContrato formOcorrenciaParcelaContrato = (FormOcorrenciaParcelaContrato)form;
		try {		
			
			String oconcodg = request.getParameter("oconcodg");
			
			BeanOcorrenciaParcelaContrato beanOcorrenciaParcelaContrato = ModelOcorrenciaParcelaContrato.getInstance().getOcorrenciaParcelaContrato(Integer.parseInt(oconcodg));
			
			BeanParcelaContratoAuto beanParcelaContratoAuto = ModelParcelaContratoAuto.getInstance().getParcelaContratoAuto(Integer.parseInt(beanOcorrenciaParcelaContrato.getOconpcct()));
			
			BeanUtils.copyProperties(formOcorrenciaParcelaContrato, beanOcorrenciaParcelaContrato);
			
			request.getSession().setAttribute("parcelacontratoauto", beanParcelaContratoAuto);
			
			fw.setPath("/ocorrenciaParcelaContratoEditar.do");
			
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
			String pccncodg = request.getParameter("pccncodg");
			
			BeanParcelaContratoAuto beanParcelaContratoAuto = ModelParcelaContratoAuto.getInstance().getParcelaContratoAuto(Integer.parseInt(pccncodg));
			
			request.getSession().removeAttribute("formOcorrenciaParcelaContrato");
			
			request.getSession().setAttribute("parcelacontratoauto", beanParcelaContratoAuto);
			
			fw.setPath("/ocorrenciaParcelaContratoNovo.do");
			
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
		ActionMessages erros = new ActionMessages();
		FormOcorrenciaParcelaContrato formOcorrenciaParcelaContrato = (FormOcorrenciaParcelaContrato)form;
		try {	
			
			if (!ValidaObjeto.validaString(formOcorrenciaParcelaContrato.getOcocobsv())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Observação é obrigatório."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/ocorrenciaParcelaContratoNovo.do");
			}else{
				BeanOcorrenciaParcelaContrato beanOcorrenciaParcelaContrato = new BeanOcorrenciaParcelaContrato();
				BeanUtils.copyProperties(beanOcorrenciaParcelaContrato, formOcorrenciaParcelaContrato);
				
				ModelOcorrenciaParcelaContrato.getInstance().inserir(beanOcorrenciaParcelaContrato);
				
				request.getSession().removeAttribute("parcelacontratoauto");
				request.getSession().removeAttribute("formOcorrenciaParcelaContrato");
				
				fw.setPath("/actionOcorrenciaParcelaContrato.do?m=lista&pccncodg="+beanOcorrenciaParcelaContrato.getOconpcct());
			}
			
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
			
			String oconcodg = request.getParameter("oconcodg");
			BeanOcorrenciaParcelaContrato beanOcorrenciaParcelaContrato = ModelOcorrenciaParcelaContrato.getInstance().getOcorrenciaParcelaContrato(Integer.parseInt(oconcodg));
			
			request.getSession().setAttribute("ocorrenciaparcelacontrato", beanOcorrenciaParcelaContrato);
			
			fw.setPath("/ocorrenciaParcelaContratoDados.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward baixar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormParcelaContratoAuto formParcelaContratoAuto = (FormParcelaContratoAuto)form;
		try {			
			
			if (!ValidaObjeto.validaData(formParcelaContratoAuto.getPccdpgto())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data do Pagamento está incorreta!"));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/parcelaContratoAutoDados.do");
			}else{
				BeanParcelaContratoAuto beanParcelaContratoAuto = new BeanParcelaContratoAuto();
				BeanUtils.copyProperties(beanParcelaContratoAuto, formParcelaContratoAuto);
				
				ModelParcelaContratoAuto.getInstance().baixar(beanParcelaContratoAuto);
				
				request.getSession().removeAttribute("parcelacontratoauto");
				request.getSession().removeAttribute("formParcelaContratoAuto");
				
				fw.setPath("/actionParcelaContratoAuto.do?m=lista&ctancodg="+beanParcelaContratoAuto.getPccnctau());
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}

}
