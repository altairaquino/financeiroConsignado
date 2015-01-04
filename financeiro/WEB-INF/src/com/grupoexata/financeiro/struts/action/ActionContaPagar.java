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
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.financeiro.dao.ModelCaixa;
import com.grupoexata.financeiro.dao.ModelCentroContaPagar;
import com.grupoexata.financeiro.dao.ModelCentroCusto;
import com.grupoexata.financeiro.dao.ModelContaPagar;
import com.grupoexata.financeiro.dao.ModelFormaLiquidez;
import com.grupoexata.financeiro.struts.bean.BeanCentroContaPagar;
import com.grupoexata.financeiro.struts.bean.BeanContaPagar;
import com.grupoexata.financeiro.struts.form.FormContaPagar;

public class ActionContaPagar extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACCONTAPAGAR:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();		
		try {
			
			request.getSession().setAttribute("ls_contapagar", ModelContaPagar.getInstance().getContasPagar());
			
			fw.setPath("/contaPagarLista.do");
			
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
		FormContaPagar formContaPagar = (FormContaPagar)form;
		
		try {			
			String cpncodg = request.getParameter("cpncodg");
			
			BeanContaPagar beanContaPagar = ModelContaPagar.getInstance().getContaPagar(Integer.parseInt(cpncodg));
			
			BeanUtils.copyProperties(formContaPagar, beanContaPagar);
			
			request.setAttribute("ls_centrocontapagar", ModelCentroContaPagar.getInstance().getCentrosContaPagar(Integer.parseInt(beanContaPagar.getCpncodg())));
			request.setAttribute("ls_formaliquidez", ModelFormaLiquidez.getInstance().getFormasDeLiquidez());
			
			fw.setPath("/contaPagarEditar.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS + METODO);
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
			
			request.getSession().removeAttribute("formContaPagar");
			
			request.getSession().setAttribute("ls_centrocusto", ModelCentroCusto.getInstance().getCentrosCusto());
			//request.getSession().setAttribute("ls_contas", ModelPlanoConta.getInstance().getPlanosContaSemFilhoPorTipo(2));
			
			fw.setPath("/contaPagarNovo.do");
			
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
		
		FormContaPagar formContaPagar = (FormContaPagar)form;
		ActionMessages erros = new ActionMessages();
		try {
			
			if (!ValidaObjeto.validaData(formContaPagar.getCpddata())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data do Vencimento é obrigatória."));
			}
			/*else{
				if (new Date().after(Utils.strBRToDate(formContaPagar.getCpddata()))){
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Pagamento futuro não pode ser com data anterior a atual."));
				}
			}*/
			if (!ValidaObjeto.validaInteiro(formContaPagar.getCpncont())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição da conta é obrigatória."));
			}
			
			if (!ValidaObjeto.validaInteiro(formContaPagar.getCpnnrpc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Número de parcelas é obrigatória."));
			}
			/*
			if (!ValidaObjeto.validaString(formContaPagar.getCpcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Observação é obrigatório."));
			}
			*/
			if (!ValidaObjeto.validaString(formContaPagar.getCpcdocm())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Documento é obrigatório."));
			}
			if (!ValidaObjeto.validaString(formContaPagar.getCpyvalr())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Valor é obrigatório."));
			}
			if (formContaPagar.getCpncecu() == null || formContaPagar.getCpncecu().length == 0){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Escolha ao menos um centro de custo."));
			}
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (!erros.isEmpty() || usuario == null){
				saveErrors(request, erros);
				fw.setPath("/contaPagarNovo.do");
			}else{
				BeanContaPagar beanContaPagar = new BeanContaPagar();
				BeanUtils.copyProperties(beanContaPagar, formContaPagar);
							
				beanContaPagar.setCpncgen(usuario.getEnncodg());
				
				ModelContaPagar.getInstance().inserir(beanContaPagar);
				
				BeanContaPagar mov = ModelContaPagar.getInstance().getContaPagar(beanContaPagar);
				
				if (mov != null){
					for (int i = 0; i < formContaPagar.getCpncecu().length; i++) {
						BeanCentroContaPagar centroContaPagar = new BeanCentroContaPagar();
						centroContaPagar.setCrcncgcr(formContaPagar.getCpncecu()[i]);
						centroContaPagar.setCrcncgcp(mov.getCpncodg());
						ModelCentroContaPagar.getInstance().inserir(centroContaPagar);
					}
				}
												
				request.getSession().removeAttribute("formContaPagar");
				request.getSession().removeAttribute("ls_centrocusto");
				request.getSession().removeAttribute("ls_formaliquidez");
			
				fw = lista(mapping, formContaPagar, request, response);
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
			String cpncodg = request.getParameter("cpncodg");
			
			BeanContaPagar beanContaPagar = ModelContaPagar.getInstance().getContaPagar(Integer.parseInt(cpncodg));
			
			request.setAttribute("ls_centrocontapagar", ModelCentroContaPagar.getInstance().getCentrosContaPagar(Integer.parseInt(beanContaPagar.getCpncodg())));
			request.setAttribute("ls_formaliquidez", ModelFormaLiquidez.getInstance().getFormasDeLiquidez());
			request.setAttribute("contapagar", beanContaPagar);
			
			fw.setPath("/contaPagarDados.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward excluir(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();
		
		try {
			String crcncodg = request.getParameter("crmncodg");
			
			BeanCentroContaPagar centroContaPagar = ModelCentroContaPagar.getInstance().getCentroContaPagar(
					Integer.parseInt(crcncodg));
			
			ModelCentroContaPagar.getInstance().excluir(centroContaPagar);
			
			fw.setPath("/actionContaPagar.do?m=dados&cpncodg="+centroContaPagar.getCrcncgcp());
						
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();
		
		try {
			String cpncodg = request.getParameter("cpncodg");		
			
			BeanContaPagar contaPagar = ModelContaPagar.getInstance().getContaPagar(
					Integer.parseInt(cpncodg));
			
			ModelContaPagar.getInstance().cancela(contaPagar);
			
			fw = lista(mapping, form, request, response);
						
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward pagar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		final String METODO = "0008";
		ActionForward fw = new ActionForward();
		FormContaPagar formContaPagar = (FormContaPagar)form;
		ActionMessages erros = new ActionMessages();
		
		try {
			
			if (!ValidaObjeto.validaData(formContaPagar.getCpdpgto())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data do Pagamento é obrigatória."));
			}else{
				if (ModelCaixa.getInstance().caixaFechado(Utils.strBRToDate(formContaPagar.getCpdpgto()))){
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Caixa esta fechado para esta data de pagamento."));
				}
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/actionContaPagar.do?m=dados&cpncodg="+formContaPagar.getCpncodg());
			}else{
				BeanContaPagar beanContaPagar = new BeanContaPagar();
				BeanUtils.copyProperties(beanContaPagar, formContaPagar);				
				
				ModelContaPagar.getInstance().pagar(beanContaPagar);
				fw = lista(mapping, formContaPagar, request, response);
				
				request.getSession().removeAttribute("formContaPagar");
				
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
		
		final String METODO = "0009";
		ActionForward fw = new ActionForward();
		
		FormContaPagar formContaPagar = (FormContaPagar)form;
		ActionMessages erros = new ActionMessages();
		try {
			
			if (!ValidaObjeto.validaData(formContaPagar.getCpddata())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data do Vencimento é obrigatória."));
			}
			/*else{
				if (new Date().after(Utils.strBRToDate(formContaPagar.getCpddata()))){
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Pagamento futuro não pode ser com data anterior a atual."));
				}
			}*/
			if (!ValidaObjeto.validaInteiro(formContaPagar.getCpncont())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição da conta é obrigatória."));
			}
			/*
			if (!ValidaObjeto.validaString(formContaPagar.getCpcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Observação é obrigatório."));
			}
			*/
			if (!ValidaObjeto.validaString(formContaPagar.getCpcdocm())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Documento é obrigatório."));
			}
			if (!ValidaObjeto.validaString(formContaPagar.getCpyvalr())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Valor é obrigatório."));
			}
			/*
			if (formContaPagar.getCpncecu() == null || formContaPagar.getCpncecu().length == 0){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Escolha ao menos um centro de custo."));
			}
			*/
			
			BeanEntidade usuario = (BeanEntidade) request.getSession().getAttribute("usuario");
			
			if (!erros.isEmpty() || usuario == null){
				saveErrors(request, erros);
				fw.setPath("/contaPagarEditar.do");
			}else{
				
				BeanContaPagar beanContaPagar = new BeanContaPagar();
				
				BeanUtils.copyProperties(beanContaPagar, formContaPagar);
							
				beanContaPagar.setCpncgen(usuario.getEnncodg());
				
				ModelContaPagar.getInstance().update(beanContaPagar);
				
				request.getSession().removeAttribute("formContaPagar");
				request.getSession().removeAttribute("ls_centrocusto");
				request.getSession().removeAttribute("ls_formaliquidez");
			
				fw.setPath("/actionContaPagar.do?m=dados&cpncodg="+formContaPagar.getCpncodg());
			}	
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}	
	
}
