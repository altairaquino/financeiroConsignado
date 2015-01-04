package com.grupoexata.bancario.struts.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.struts.upload.FormFile;

import com.grupoexata.auditoria.bin.BinFiltroauditoria;
import com.grupoexata.auditoria.dao.ModelFiltroauditoria;
import com.grupoexata.bancario.dao.ModelAngariador;
import com.grupoexata.bancario.dao.ModelCidade;
import com.grupoexata.bancario.dao.ModelContrato;
import com.grupoexata.bancario.dao.ModelEntidade;
import com.grupoexata.bancario.dao.ModelFormaPagamento;
import com.grupoexata.bancario.dao.ModelProduto;
import com.grupoexata.bancario.dao.ModelTabelaProduto;
import com.grupoexata.bancario.dao.ModelTipoLogradouro;
import com.grupoexata.bancario.struts.bean.BeanAngariador;
import com.grupoexata.bancario.struts.bean.BeanContrato;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.struts.form.FormContrato;
import com.grupoexata.bancario.utils.CPF;
import com.grupoexata.bancario.utils.EnviarEmail;
import com.grupoexata.bancario.utils.FormataObj;
import com.grupoexata.bancario.utils.GeraRelatorio;
import com.grupoexata.bancario.utils.RemoverAcentos;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionContrato extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACCT:";
	
	public ActionForward listaSemFisico(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {			
			request.setAttribute("ls_contrato", ModelContrato.getInstance().getContratosSemFisico());
			
			fw.setPath("/contratosSemFisico.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
	
		return fw;
	}
		
	
	public ActionForward visualizaComissoes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0002";
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		
		try {
			
			String ctnnumr = request.getParameter("ctnnumr");
			
			BeanContrato contrato = ModelContrato.getInstance().getContratoPorNumero(ctnnumr);
			
			if (contrato != null){
				request.setAttribute("contrato", contrato);
				fw.setPath("/actionComissaoContrato.do?m=lista&ctncodg="+contrato.getCtncodg());
			}else{
				fw.setPath("/contratoPesquisaComissao.do");
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Contrato n�o encontrado."));
			}
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		saveErrors(request, erros);
		
		return fw;
	}
	public ActionForward baixaFisico(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0003";
		
		ActionForward fw = new ActionForward();
		
		try {			
			
			String ctncodg = request.getParameter("ctncodg");
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (usuario != null){
				ModelContrato.getInstance().baixaFisico(
						Integer.parseInt(ctncodg),
						Integer.parseInt(usuario.getEnncodg()));
			}
			
			fw = listaSemFisico(mapping, form, request, response);
			
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
			String enncodg = request.getParameter("enncodg");
			BeanEntidade entidade = ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg));
			
			request.getSession().removeAttribute("formContrato");
						
			request.getSession().setAttribute("ls_produto", ModelProduto.getInstance().getProdutos());
			request.getSession().setAttribute("ls_formapagamento", ModelFormaPagamento.getInstance().getFormasDePagamento());
			
			request.getSession().setAttribute("angariador", entidade);
			
			fw.setPath("/contratoNovo.do");
			
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
		FormContrato formContrato = (FormContrato)form;
		
		BeanEntidade cliente = ModelEntidade.getInstance().getBeanEntidadePorCPF(ValidaObjeto.removeCharOfInteger(formContrato.getCtnc2en()),1);
		
		if (cliente == null){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Cliente informado n�o esta cadastrado."));	
		}
		/*
		if (!ValidaObjeto.validaNumeroContrato(formContrato.getCtnnumr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","N�mero do contrato inv�lido."));
		}
		*/
		
		if (ModelContrato.getInstance().getContratoPorNumero(formContrato.getCtnnumr()) != null){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Número do contrato já cadastrado no sistema."));
		}
		
		if (!ValidaObjeto.validaString(formContrato.getCtyvalr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Valor do contrato é obrigat�rio."));
		}else{
			if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formContrato.getCtyvalr()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Valor do contrato está incorreto."));
			}			
		}
		
		if (formContrato.getCtncgfp().equals("2")){
			if (formContrato.getCtncgbc().equals("-1") || (formContrato.getCtcagen() == null || formContrato.getCtcagen().equals(""))){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Banco e agência são obrigatórios para ordem de pagamento."));
			}
		}else{
			formContrato.setCtncgbc(null);
			formContrato.setCtcagen(null);
		}
		
		if (!ValidaObjeto.validaString(formContrato.getCtnnumr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","N�mero do contrato � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaString(formContrato.getCtncgtp()) || formContrato.getCtncgtp().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Tabela do produto � obrigat�rio."));
		}
		
		
		if (!erros.isEmpty()){			
			fw.setPath("/contratoNovo.do");
		}else{		
			try {			
				BeanContrato contrato = new BeanContrato();
				
				BeanUtils.copyProperties(contrato, formContrato);
				
				contrato.setCtnc2en(cliente.getEnncodg());
				
				ModelContrato.getInstance().inserir(contrato);
				
				fw.setPath("/angariadorPesquisaContrato.do");
				
				request.getSession().removeAttribute("formContrato");
				
				request.getSession().removeAttribute("ls_produto");
				request.getSession().removeAttribute("ls_formapagamento");
				request.getSession().removeAttribute("ls_tabelaproduto");
				
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Cadastro de Contrato Efetuado com Sucesso."));
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		saveErrors(request, erros);
		
		return fw;
	}
	

	public ActionForward opcoes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0006";		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		try {
			String ctncodg = request.getParameter("ctncodg");
			
			if (!ModelContrato.getInstance().temSpreadPositivo(Integer.parseInt(ctncodg))){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Contrato com Spread Negativo. Reveja comiss�es antes de pagar."));
				saveErrors(request, erros);
				request.setAttribute("negativo", "negativo");
			}
			
			BeanContrato contrato = ModelContrato.getInstance().getContrato(Integer.parseInt(ctncodg));
			BeanAngariador ang = ModelAngariador.getInstance().getAngariador(Integer.parseInt(contrato.getCtncgen()));
			
			request.setAttribute("contrato", contrato);
			request.setAttribute("ang", ang);
			
			request.setAttribute("data_pgto", new FormataObj().formataData(Utils.proximaDataPagamento()));
			
			fw.setPath("/contratoPage.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		
		return fw;		
	}
	
	public ActionForward pesquisaFisico(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0007";
		
		ActionForward fw = new ActionForward();
		
		try {
			
			String ctnnumr = request.getParameter("ctnnumr");
			
			request.setAttribute("ls_contrato", ModelContrato.getInstance().getContratoPorNumeroSemFisico(ctnnumr));
			
			fw.setPath("/contratosSemFisico.do");
			
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
		
		final String METODO = "0008";
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		
		try {
			
			String ctnnumr = request.getParameter("ctnnumr");
			
			BeanContrato contrato = ModelContrato.getInstance().getContratoPorNumero(ctnnumr);
			request.setAttribute("data_pgto", new FormataObj().formataData(Utils.proximaDataPagamento()));
			
			if (contrato != null){
				request.setAttribute("contrato", contrato);
				fw.setPath("/actionContrato.do?m=opcoes&ctncodg="+contrato.getCtncodg());
			}else{
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nenhum contrato encontrado!"));
				saveErrors(request, erros);
				fw.setPath("/contratoPesquisa.do");
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
		
		final String METODO = "0010";
		
		ActionForward fw = new ActionForward();
				
		try {
			
			String ctncodg = request.getParameter("ctncodg");
			
			BeanContrato contrato = ModelContrato.getInstance().getContrato(Integer.parseInt(ctncodg));
			
			request.setAttribute("contrato", contrato);
			
			fw.setPath("/contratoDados.do");								
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;		
	}
	
	public ActionForward relatorios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0011";
		
		ActionMessages erros = new ActionMessages();
		ActionForward fw = new ActionForward();
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		
		if (!ValidaObjeto.validaData(data1)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial � inv�lida."));
		}
		if (!ValidaObjeto.validaData(data2)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Final � inv�lida."));
		}
		
		if (erros.isEmpty()){
			if (Utils.strBRToDate(data1).after(Utils.strBRToDate(data2))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial n�o pode ser maior que a final."));
			}
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/contratoRelatorios.do");
			saveErrors(request, erros);
		}else{
		
			try {
				
				Map<Object,Object> map = new HashMap<Object, Object>();
				
				map.put("DATA_INICIO", Utils.strBRToDate(data1));
				map.put("DATA_FIM", Utils.strBRToDate(data2));
				
								
				switch (Integer.parseInt(request.getParameter("tipo"))) {
					case 1:
						map.put("REPORT_NAME", "comissao_angariador_periodo");
						break;
					case 10:
						map.put("REPORT_NAME", "pendencia_fisico_agente");
						break;
					case 2:
						map.put("REPORT_NAME", "relat_producao_periodo");
						break;
					case 3:
						map.put("REPORT_NAME", "comissao_angariador_resumo");
						break;
					case 4:
						map.put("REPORT_NAME", "relat_producao_produto");
						break;
					case 5:
						map.put("REPORT_NAME", "relat_producao_angariador");
						break;
					case 6:
						map.put("REPORT_NAME", "relat_contrato_extorno");
						break;
					case 7:
						map.put("REPORT_NAME", "relat_producao_periodo_corretor");
						break;
					case 8:
						map.put("REPORT_NAME", "tarifas_corretor_periodo");
						break;
					case 9:
						map.put("REPORT_NAME", "relat_op_nao_confirmada_corretor");
						break;
					case 0:
						map.put("REPORT_NAME", "relat_producao_produto_agente");
						break;
					case 11:
						map.put("REPORT_NAME", "relat_ops_por_corretor");
						break;
					default:
						map.put("REPORT_NAME", "comissao_angariador_periodo");
						break;
				}
								
				GeraRelatorio.geracao(request, response, map, false);
				
				fw = null;
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		
		return fw;
	}
	
	
	public ActionForward registraAvervacao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0012";
		
		ActionMessages erros = new ActionMessages();
		ActionForward fw = new ActionForward();
		FormContrato formContrato = (FormContrato)form;
		
		if (!ValidaObjeto.validaData(formContrato.getCtdverb())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data do pagamento � inv�lida."));
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/actionContrato.do?m=opcoes&ctncodg="+formContrato.getCtncodg());
			saveErrors(request, erros);
		}else{
			try {
				BeanContrato beanContrato = new BeanContrato();
				BeanUtils.copyProperties(beanContrato, formContrato);
				
				ModelContrato.getInstance().registraAvervacao(beanContrato);
				
				fw.setPath("/actionContrato.do?m=opcoes&ctncodg="+formContrato.getCtncodg());
				
				request.getSession().removeAttribute("formContrato");
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
			
		}
		
		
		return fw;
	}
	
	public ActionForward registraExtorno(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0013";
		
		ActionMessages erros = new ActionMessages();
		ActionForward fw = new ActionForward();
		FormContrato formContrato = (FormContrato)form;
		
		if (!ValidaObjeto.validaData(formContrato.getCtdextn())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data do extorno � inv�lida."));
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/actionContrato.do?m=opcoes&ctncodg="+formContrato.getCtncodg());
			saveErrors(request, erros);
		}else{
			try {
				BeanContrato beanContrato = new BeanContrato();
				BeanUtils.copyProperties(beanContrato, formContrato);
				
				ModelContrato.getInstance().registraExtorno(beanContrato);
				
				fw.setPath("/actionContrato.do?m=opcoes&ctncodg="+formContrato.getCtncodg());
				
				request.getSession().removeAttribute("formContrato");
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
			
		}		
		
		return fw;
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0014";
		
		ActionForward fw = new ActionForward();
		FormContrato formContrato = (FormContrato)form;
		
		try {
			
			String ctncodg = request.getParameter("ctncodg");
			
			if (ValidaObjeto.validaInteiro(ctncodg)){
				
				BeanContrato contrato = ModelContrato.getInstance().getContrato(Integer.parseInt(ctncodg));
				
				request.getSession().setAttribute("ls_produto", ModelProduto.getInstance().getProdutos());
				request.getSession().setAttribute("ls_formapagamento", ModelFormaPagamento.getInstance().getFormasDePagamento());
				request.getSession().setAttribute("ls_tabelaproduto", ModelTabelaProduto.getInstance().getTabelaDoProduto(Integer.parseInt(contrato.getCtncgpd())));
						
				Utils.copyProperties(formContrato, contrato);
				fw.setPath("/contratoEditar.do");
				
			}else{				
				fw.setPath("/home.do");
			}
			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward finalizaContrato(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0015";
		
		ActionForward fw = new ActionForward();
		FormContrato formContrato = (FormContrato)form;
		ActionMessages erros = new ActionMessages();
		try {
			BeanContrato beanContrato = new BeanContrato();
			BeanUtils.copyProperties(beanContrato, formContrato);
			
			if (ModelContrato.getInstance().temSpreadPositivo(Integer.parseInt(formContrato.getCtncodg()))){
				ModelContrato.getInstance().finalizaContrato(beanContrato);
				fw.setPath("/contratoPesquisa.do");
			}else{
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Contrato com Spread Negativo. Reveja comiss�es antes de pagar."));
				saveErrors(request, erros);
				fw.setPath("/actionContrato.do?m=opcoes&ctncodg="+formContrato.getCtncodg());
			}			
			
			
			request.getSession().removeAttribute("formContrato");
			
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
		
		final String METODO = "0016";
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormContrato formContrato = (FormContrato)form;
						
		BeanEntidade cliente = ModelEntidade.getInstance().getBeanEntidadePorCPF(ValidaObjeto.removeCharOfInteger(formContrato.getCtccpf()),1);
		
		if (cliente == null){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Cliente informado n�o esta cadastrado."));	
		}
				
		if (!ValidaObjeto.validaString(formContrato.getCtyvalr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Valor do contrato � obrigat�rio."));
		}else{
			if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formContrato.getCtyvalr()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Valor do contrato est� incorreto."));
			}			
		}
		
		if (formContrato.getCtncgfp().equals("2")){
			if (formContrato.getCtncgbc().equals("-1") || (formContrato.getCtcagen() == null || formContrato.getCtcagen().equals(""))){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Banco e ag�ncia s�o obrigat�rios para ordem de pagamento."));
			}
		}else{
			formContrato.setCtncgbc(null);
			formContrato.setCtcagen(null);
		}
		
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formContrato.getCtnexat()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Comiss�o da exata est� incorreta."));
		}
		
		if (!ValidaObjeto.validaString(formContrato.getCtnnumr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","N�mero do contrato � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaString(formContrato.getCtncgtp()) || formContrato.getCtncgtp().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Tabela do produto � obrigat�rio."));
		}
		
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		
		if (!erros.isEmpty() || usuario == null){		
			fw.setPath("/contratoEditar.do");
		}else{		
			try {			
				BeanContrato contrato = new BeanContrato();
				
				BeanUtils.copyProperties(contrato, formContrato);
				
				contrato.setCtnc2en(cliente.getEnncodg());
				contrato.setCtnalte(usuario.getEnncodg());				
												
				ModelContrato.getInstance().update(contrato);
				
				fw.setPath("/actionContrato.do?m=dados&ctncodg="+formContrato.getCtncodg());
				
				request.getSession().removeAttribute("formContrato");
				
				request.getSession().removeAttribute("ls_produto");
				request.getSession().removeAttribute("ls_formapagamento");
				request.getSession().removeAttribute("ls_tabelaproduto");
				
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Contrato Atualizado com Sucesso."));
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward pesquisaCPF(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0017";
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		
		try {
			
			String ctnnumr = request.getParameter("ctccpf");
			
			ArrayList<BeanContrato> contratos = ModelContrato.getInstance().getContratoPorCPF(ctnnumr);
			
			if (!contratos.isEmpty()){
				request.setAttribute("ls_contrato", contratos);
				fw.setPath("/contratoLista.do");
			}else{
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nenhum contrato encontrado!"));
				saveErrors(request, erros);
				fw.setPath("/contratoPesquisaCPF.do");
			}						
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;		
	}
	
	public ActionForward novo2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0018";
		
		ActionForward fw = new ActionForward();
		
		try {
			
			request.getSession().removeAttribute("formContrato");
						
			request.getSession().setAttribute("ls_produto", ModelProduto.getInstance().getProdutos());
			request.getSession().setAttribute("ls_formapagamento", ModelFormaPagamento.getInstance().getFormasDePagamento());
			
			fw.setPath("/contratoNovoAng.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward meus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		final String METODO = "0019";
		ActionForward fw = new ActionForward();
		
		try {			
			
			BeanEntidade angariador = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (angariador != null){						
				request.setAttribute("ls_contrato", ModelContrato.getInstance().getContratosDoAngariador(Integer.parseInt(angariador.getEnncodg())));
				fw.setPath("/contratosAngariador.do");
			}else{
				fw.setPath("/home.do");				
			}
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
	
		return fw;
	}
	
	public ActionForward cadastroPeloAngariador(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0020";
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormContrato formContrato = (FormContrato)form;
		
		BeanEntidade cliente = ModelEntidade.getInstance().getBeanEntidadePorCPF(ValidaObjeto.removeCharOfInteger(formContrato.getCtnc2en()),1);
		
		if (!ValidaObjeto.validaNumeroContrato(formContrato.getCtnnumr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","N�mero do contrato inv�lido."));
		}
		
		if (ModelContrato.getInstance().getContratoPorNumero(formContrato.getCtnnumr()) != null){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","N�mero do contrato j� cadastrado no sistema."));
		}
		
				
		if (!ValidaObjeto.validaString(formContrato.getCtyvalr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Valor do contrato � obrigat�rio."));
		}else{
			if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formContrato.getCtyvalr()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Valor do contrato est� incorreto."));
			}			
		}
		
		if (formContrato.getCtncgfp().equals("2")){
			if (formContrato.getCtncgbc().equals("-1") || (formContrato.getCtcagen() == null || formContrato.getCtcagen().equals(""))){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Banco e ag�ncia s�o obrigat�rios para ordem de pagamento."));
			}
		}else{
			formContrato.setCtncgbc(null);
			formContrato.setCtcagen(null);
		}
		
		if (!ValidaObjeto.validaString(formContrato.getCtnnumr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","N�mero do contrato � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaString(formContrato.getCtncgtp()) || formContrato.getCtncgtp().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Tabela do produto � obrigat�rio."));
		}
		
		if (cliente == null){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Cliente informado n�o esta cadastrado."));	
		}
		
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		
		if (!erros.isEmpty() || usuario == null){		
			fw.setPath("/contratoNovoAng.do");
		}else{		
			try {			
				BeanContrato contrato = new BeanContrato();
				
				BeanUtils.copyProperties(contrato, formContrato);
				
				contrato.setCtnc2en(cliente.getEnncodg());
				
				ModelContrato.getInstance().inserir(contrato);
				
				fw.setPath("/home.do");
				
				EnviarEmail.getInstance().enviarEmailCadastroContrato(ModelContrato.getInstance().getContratoPorNumero(contrato.getCtnnumr()),usuario);				
				
				request.getSession().removeAttribute("formContrato");
				
				request.getSession().removeAttribute("ls_produto");
				request.getSession().removeAttribute("ls_formapagamento");
				request.getSession().removeAttribute("ls_tabelaproduto");
				
				request.setAttribute("msg","Cadastro de Contrato Efetuado com Sucesso.");
				
				if (ModelContrato.getInstance().temProducaoSuspeita(Integer.parseInt(usuario.getEnncodg()))){
					EnviarEmail.getInstance().enviarEmailSupeitaFraude(usuario);
				}				
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward semFisicoDoAngariador(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		final String METODO = "0021";
		ActionForward fw = new ActionForward();
		
		try {			
			
			String enncodg = request.getParameter("enncodg");
			BeanEntidade angariador = ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg));
			
			request.setAttribute("angariador", angariador);
			request.setAttribute("ls_contrato", ModelContrato.getInstance().getContratosSemFisicoDoAngariador(Integer.parseInt(enncodg)));
			
			fw.setPath("/contratosSemFisicoDoAngariador.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
	
		return fw;
	}
	
	@SuppressWarnings("deprecation")
	public ActionForward relatoriosGerenciais(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0022";
		
		ActionForward fw = new ActionForward();
		
		String ano = request.getParameter("ano");
		String mes = request.getParameter("mes");
		
		try {
			
			Map<Object,Object> map = new HashMap<Object, Object>();
			
			map.put("DATA", new Date(Integer.parseInt(ano),Integer.parseInt(mes),1));
			
							
			switch (Integer.parseInt(request.getParameter("tipo"))) {
				case 1:
					map.put("REPORT_NAME", "relat_meta_agencias");
					break;
				case 2:
					map.put("REPORT_NAME", "sinergia_acompanhamento_producao");
					break;
				case 3:
					map.put("REPORT_NAME", "corretor_acompanhamento_producao");
					break;
				
				default:
					map.put("REPORT_NAME", "corretor_acompanhamento_producao");
					break;
			}
							
			GeraRelatorio.geracao(request, response, map, false);
			
			fw = null;
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
	
		return fw;
	}
	
	public ActionForward relatoriosAngariador(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0023";
		
		ActionMessages erros = new ActionMessages();
		ActionForward fw = new ActionForward();
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		
		if (!ValidaObjeto.validaData(data1)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial � inv�lida."));
		}
		if (!ValidaObjeto.validaData(data2)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Final � inv�lida."));
		}
		
		if (erros.isEmpty()){
			if (Utils.strBRToDate(data1).after(Utils.strBRToDate(data2))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial n�o pode ser maior que a final."));
			}
		}
		
		if (!erros.isEmpty() || usuario == null){
			fw.setPath("/contratoRelatoriosAngariador.do");
			saveErrors(request, erros);
		}else{
		
			try {
				
				Map<Object,Object> map = new HashMap<Object, Object>();
				
				map.put("DATA_INICIO", Utils.strBRToDate(data1));
				map.put("DATA_FIM", Utils.strBRToDate(data2));				
				
				
				map.put("ENNCODG", new Integer(usuario.getEnncodg()));
								
				switch (request.getParameter("tipo").charAt(0)) {
					case '1':
						map.put("REPORT_NAME", "comissao_angariador_periodo2");
						break;
					case '2':
						map.put("REPORT_NAME", "relat_producao_periodo2");
						break;
					case '3':
						map.put("REPORT_NAME", "relat_contrato_extorno2");
						break;					
					default:
						map.put("REPORT_NAME", "comissao_angariador_periodo2");
						break;
				}
								
				GeraRelatorio.geracao(request, response, map, false);
				
				fw = null;
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		
		return fw;
	}
	
	public ActionForward listaAuditoria(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		final String METODO = "0024";
		ActionForward fw = new ActionForward();
		String ctdcadt = request.getParameter("ctdcadt");
		
		try {
			
			if (!ValidaObjeto.validaData(ctdcadt)){
				request.setAttribute("ls_contrato", ModelContrato.getInstance().
						getContratosAuditoria(Utils.incrementaData(new Date(), -1)));
			}else{
				request.setAttribute("ls_contrato", ModelContrato.getInstance().
						getContratosAuditoria(Utils.strBRToDate(ctdcadt)));
			}
			
			request.setAttribute("data", new FormataObj().formataData(ctdcadt == null?Utils.incrementaData(new Date(), -1):Utils.strBRToDate(ctdcadt)));

			fw.setPath("/contratoListaAuditoria.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
	
		return fw;
	}
	
	public ActionForward auditar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		final String METODO = "0025";
		ActionForward fw = new ActionForward();
		String ctncodg = request.getParameter("ctncodg");
		
		try {
			
			request.setAttribute("contrato", ModelContrato.getInstance().getContrato(Integer.parseInt(ctncodg)));
			
			fw.setPath("/contratoAuditoria.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
	
		return fw;
	}
	
	public ActionForward uploadArquivo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		final String METODO = "0026";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormContrato formContrato = (FormContrato)form;
		try {
			BeanContrato contrato = ModelContrato.getInstance().getContrato(Integer.parseInt(formContrato.getCtncodg()));
			
			String realPath = getServlet().getServletConfig().getServletContext().getRealPath("/documentos/");
			
			String diretorio = realPath + "/" +contrato.getCtnnumr();
			
			File file = new File(diretorio);
			
			FormFile myFile = formContrato.getArquivo();
			
			if (!file.exists()){
				file.mkdir();
			}
			
			if (file.isDirectory()){
				if(myFile!=null){
					//String contentType = myFile.getContentType();
					String fileName = RemoverAcentos.remover(myFile.getFileName());	
					//byte[] fileData    = myFile.getFileData();
					String filePath = diretorio;
					if(fileName != null && !fileName.equals("") && myFile.getFileSize() < (1024*1024)){
						File fileToCreate = new File(filePath, fileName);
						if(!fileToCreate.exists()){
							FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
							fileOutStream.write(myFile.getFileData());
							fileOutStream.flush();
							fileOutStream.close();
						}
					}else{
						erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Arquivo excedeu o tamanho limite."));
						saveErrors(request, erros);
					}				
				}
			}
			
			fw.setPath("/actionContrato.do?m=listaArquivos&ctncodg="+contrato.getCtncodg());
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
	
		return fw;
	}
	
	public ActionForward uploadArquivo2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		final String METODO = "0026";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormContrato formContrato = (FormContrato)form;
		try {
			BeanContrato contrato = ModelContrato.getInstance().getContrato(Integer.parseInt(formContrato.getCtncodg()));
			
			String realPath = getServlet().getServletConfig().getServletContext().getRealPath("/documentos/");
			
			String diretorio = realPath + "/" +contrato.getCtnnumr();
			
			File file = new File(diretorio);
			
			FormFile myFile = formContrato.getArquivo();
			
			if (!file.exists()){
				file.mkdir();
			}
			
			if (file.isDirectory()){
				if(myFile!=null){
					//String contentType = myFile.getContentType();
					String fileName = RemoverAcentos.remover(myFile.getFileName());	
					//byte[] fileData    = myFile.getFileData();
					String filePath = diretorio;
					if(fileName != null && !fileName.equals("") && myFile.getFileSize() < (1024*1024)){
						File fileToCreate = new File(filePath, fileName);
						if(!fileToCreate.exists()){
							FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
							fileOutStream.write(myFile.getFileData());
							fileOutStream.flush();
							fileOutStream.close();
						}
					}else{
						erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Arquivo excedeu o tamanho limite."));
						saveErrors(request, erros);
					}				
				}
			}
			
			fw.setPath("/actionContrato.do?m=listaArquivos2&ctncodg="+contrato.getCtncodg());
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
	
		return fw;
	}
	
	public ActionForward listaArquivos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		final String METODO = "0027";
		ActionForward fw = new ActionForward();		
		try {
			
			String ctncodg = request.getParameter("ctncodg");
			
			request.setAttribute("contrato", ModelContrato.getInstance().getContrato(Integer.parseInt(ctncodg)));
			
			fw.setPath("/contratoListaArquivos.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
		
	}
	
	
	
	public ActionForward pesquisaPorCorretor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0028";
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		
		try {
			
			String ctnnumr = request.getParameter("ctnnumr");
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			BeanContrato contrato = ModelContrato.getInstance().getContratoPorNumeroDoCorretor(ctnnumr, Integer.parseInt(usuario.getEnncodg()));
			
			if (contrato != null){
				fw.setPath("/actionContrato.do?m=listaArquivos2&ctncodg="+contrato.getCtncodg());
			}else{
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Nenhum contrato encontrado!"));
				saveErrors(request, erros);
				fw.setPath("/contratoPesquisa2.do");
			}						
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;		
	}
	
	public ActionForward listaArquivos2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		final String METODO = "0029";
		ActionForward fw = new ActionForward();		
		try {
			
			String ctncodg = request.getParameter("ctncodg");
			
			request.setAttribute("contrato", ModelContrato.getInstance().getContrato(Integer.parseInt(ctncodg)));
			
			fw.setPath("/contratoListaArquivos2.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
		
	}
	
	public ActionForward confirmaSaqueOP(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0030";
		
		ActionForward fw = new ActionForward();
		FormContrato formContrato = (FormContrato)form;
		
		try {
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (usuario != null){
				ModelContrato.getInstance().confirmaSaqueOP(Integer.parseInt(formContrato.getCtncodg()), 
						Integer.parseInt(usuario.getEnncodg()));
			}
			
			fw.setPath("/actionContrato.do?m=listaConfirmaOP");
			
			request.getSession().removeAttribute("formContrato");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;
	}
	
	public ActionForward listaConfirmaOP(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		final String METODO = "0031";
		ActionForward fw = new ActionForward();	
		try {
			
			request.setAttribute("ls_contrato", ModelContrato.getInstance().
						getListaConfirmaOP());			
			
			fw.setPath("/contratoListaConfirmaOP.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
	
		return fw;
	}
	
	public ActionForward dadosWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0032";
		ActionForward fw = new ActionForward();
		try {
		
			String ctncodg = request.getParameter("ctncodg");
			request.setAttribute("contrato", ModelContrato.getInstance().getContrato(Integer.parseInt(ctncodg)));
			fw.setPath("/contratoDadosWindow.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward novoCompleto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0033";
		
		ActionForward fw = new ActionForward();
		
		try {
			
			request.getSession().removeAttribute("formContrato");
						
			request.getSession().setAttribute("ls_produto", ModelProduto.getInstance().getProdutos());
			request.getSession().setAttribute("ls_tabelaproduto", ModelTabelaProduto.getInstance().getTabelaDoProduto(1));
			request.getSession().setAttribute("ls_formapagamento", ModelFormaPagamento.getInstance().getFormasDePagamento());
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			
			fw.setPath("/contratoNovoCompleto.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward cadastroCompleto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0034";
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormContrato formContrato = (FormContrato)form;
		
		if (!ValidaObjeto.validaString(formContrato.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome � obrigat�rio!"));
		}	
		if (!ValidaObjeto.validaString(formContrato.getEncdocm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF � requerido!"));
		}else{
			if (!CPF.validar(ValidaObjeto.removeCharOfInteger(formContrato.getEncdocm()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF � inv�lido!"));
			}
		}
		if (!ValidaObjeto.validaString(formContrato.getEndnasc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento � Obrigat�rio!"));
		}else{
			if (!ValidaObjeto.validaData(formContrato.getEndnasc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento inv�lida!"));
			}
		}
		
		if (!ValidaObjeto.validaString(formContrato.getEndexrg())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data do RG � Obrigat�rio!"));
		}else{
			if (!ValidaObjeto.validaData(formContrato.getEndexrg())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data do RG inv�lida!"));
			}
		}
		
		if (!ValidaObjeto.validaString(formContrato.getEncrg())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","RG � requerido!"));
		}
		
		if (!ValidaObjeto.validaString(formContrato.getEncoerg())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Org�o Expedidor do RG � obrigat�rio!"));
		}
		
		if (!ValidaObjeto.validaString(formContrato.getEncdorg())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Documento de Origem do RG � obrigat�rio!"));
		}
		
		if (!ValidaObjeto.validaString(formContrato.getEncmae())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome da m�e � obrigat�rio!"));
		}
		
		if (!ValidaObjeto.validaString(formContrato.getEnlendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Endere�o � obrigat�rio!"));
		}
		
		if (formContrato.getEnncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Cidade � obrigat�rio!"));
		}
		
		if (!ValidaObjeto.validaFone(formContrato.getEncfone())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Fone digitado esta incorreto!"));
		}
		
		if (!ValidaObjeto.validaString(formContrato.getEnccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CEP � obrigat�rio!"));
		}
		
		if (!ValidaObjeto.validaString(formContrato.getEncbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Bairro � obrigat�rio!"));
		}
		
		if (!ValidaObjeto.validaNumeroContrato(formContrato.getCtnnumr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","N�mero do contrato inv�lido."));
		}
		
		if (ModelContrato.getInstance().getContratoPorNumero(formContrato.getCtnnumr()) != null){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","N�mero do contrato j� cadastrado no sistema."));
		}
				
		if (!ValidaObjeto.validaString(formContrato.getCtyvalr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Valor do contrato � obrigat�rio."));
		}else{
			if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formContrato.getCtyvalr()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Valor do contrato est� incorreto."));
			}	
		}
		
		if (formContrato.getCtncgfp().equals("2")){
			if (formContrato.getCtncgbc().equals("-1") || (formContrato.getCtcagen() == null || formContrato.getCtcagen().equals(""))){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Banco e ag�ncia s�o obrigat�rios para ordem de pagamento."));
			}
		}else{
			formContrato.setCtncgbc(null);
			formContrato.setCtcagen(null);
		}
		
		if (!ValidaObjeto.validaString(formContrato.getCtnnumr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","N�mero do contrato � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaString(formContrato.getCtncgtp()) || formContrato.getCtncgtp().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Tabela do produto � obrigat�rio."));
		}
		
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		
		if (!erros.isEmpty() || usuario == null){
			fw.setPath("/contratoNovoCompleto.do");
		}else{		
			try {			
				BeanContrato contrato = new BeanContrato();
				
				BeanUtils.copyProperties(contrato, formContrato);
				
				ModelContrato.getInstance().inserirCompleto(contrato);
				
				fw.setPath("/home.do");
				
				class Thr extends Thread{

					BeanContrato ct;
					BeanEntidade us;
					
					public Thr(BeanContrato ct, BeanEntidade us){
						this.ct = ct;
						this.us = us;
					}
					
					@Override
					public void run(){
						ModelFiltroauditoria.getIntance().addContratoauditorias(new BinFiltroauditoria(Integer.parseInt(ct.getCtncodg())).executar(null));				
						
						EnviarEmail.getInstance().enviarEmailCadastroContrato(ct, us);	
						
						if (ModelContrato.getInstance().temProducaoSuspeita(Integer.parseInt(us.getEnncodg()))){
							EnviarEmail.getInstance().enviarEmailSupeitaFraude(us);
						}
						
					}
				};				
				
				BeanContrato ct = ModelContrato.getInstance().getContratoPorNumero(contrato.getCtnnumr());
				
				Thr t = new Thr(ct, usuario);
				
				t.start();
				
				request.getSession().removeAttribute("formContrato");
				
				request.getSession().removeAttribute("ls_produto");
				request.getSession().removeAttribute("ls_formapagamento");
				request.getSession().removeAttribute("ls_tabelaproduto");
				request.getSession().removeAttribute("ls_estado");
				request.getSession().removeAttribute("ls_tipologradouro");
				
				request.setAttribute("msg","Cadastro de Contrato Efetuado com Sucesso.");				
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward relatoriosSituacao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0035";
		
		ActionMessages erros = new ActionMessages();
		ActionForward fw = new ActionForward();
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		
		if (!ValidaObjeto.validaData(data1)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial � inv�lida."));
		}
		if (!ValidaObjeto.validaData(data2)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Final � inv�lida."));
		}
		
		if (erros.isEmpty()){
			if (Utils.strBRToDate(data1).after(Utils.strBRToDate(data2))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial n�o pode ser maior que a final."));
			}
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/contratoRelatorioSituacao.do");
			saveErrors(request, erros);
		}else{
		
			try {
				
				Map<Object,Object> map = new HashMap<Object, Object>();
				
				map.put("DATA_INICIO", Utils.strBRToDate(data1));
				map.put("DATA_FIM", Utils.strBRToDate(data2));
				map.put("SITUACAO", Integer.parseInt(request.getParameter("ctncgsc")));
				
								
				switch (request.getParameter("tipo").charAt(0)) {
					case '1':
						map.put("REPORT_NAME", "relat_producao_periodo_situacao");
						break;
					default:
						map.put("REPORT_NAME", "relat_producao_periodo_situacao");
						break;
				}
								
				GeraRelatorio.geracao(request, response, map, false);
				
				fw = null;
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		
		return fw;
	}
	
	public ActionForward relatorioFuncionario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0036";
		
		ActionMessages erros = new ActionMessages();
		ActionForward fw = new ActionForward();
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		
		if (!ValidaObjeto.validaData(data1)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial � inv�lida."));
		}
		if (!ValidaObjeto.validaData(data2)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Final � inv�lida."));
		}
		
		if (erros.isEmpty()){
			if (Utils.strBRToDate(data1).after(Utils.strBRToDate(data2))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial n�o pode ser maior que a final."));
			}
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/relatorioFuncionarioComissao.do");
			saveErrors(request, erros);
		}else{
			
			try {
				
				Map<Object,Object> map = new HashMap<Object, Object>();
				
				map.put("DATA_INICIO", Utils.strBRToDate(data1));
				map.put("DATA_FIM", Utils.strBRToDate(data2));
				
				
				switch (request.getParameter("tipo").charAt(0)) {
					case '1':
						map.put("REPORT_NAME", "comissao_func_agencia_periodo2");
						break;
					case '2':
						map.put("REPORT_NAME", "comissao_funcionario_resumo");
						break;
					case '3':
						map.put("REPORT_NAME", "relat_comissao_func_agencias");
						break;
					case '4':
						map.put("REPORT_NAME", "producao_sinergia_mensal");
						break;
					case '5':
						map.put("REPORT_NAME", "sinergia_producao_geral");
						break;
					default:
						map.put("REPORT_NAME", "comissao_funcionario_resumo");
						break;
				}
				
				GeraRelatorio.geracao(request, response, map, false);
				
				fw = null;
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		
		return fw;
	}
	
	public ActionForward cancela(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0037";
		
		ActionMessages erros = new ActionMessages();
		ActionForward fw = new ActionForward();
		FormContrato formContrato = (FormContrato)form;
		
		if (!ValidaObjeto.validaInteiro(formContrato.getCtncodg())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","C�digo inv�lido."));
		}
		
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		
		if (!erros.isEmpty() || usuario == null){
			saveErrors(request, erros);
		}else{
			try {
				ModelContrato.getInstance().cancela(Integer.parseInt(formContrato.getCtncodg()));
				
				request.getSession().removeAttribute("formContrato");
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
			
		}
		
		fw.setPath("/actionContrato.do?m=opcoes&ctncodg="+formContrato.getCtncodg());
		
		return fw;
	}
	
	public ActionForward registraPagamento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0038";
		
		ActionMessages erros = new ActionMessages();
		ActionForward fw = new ActionForward();
		FormContrato formContrato = (FormContrato)form;
		
		if (!ValidaObjeto.validaData(formContrato.getCtdpgto())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data do pagamento � inv�lida."));
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/actionContrato.do?m=opcoes&ctncodg="+formContrato.getCtncodg());
			saveErrors(request, erros);
		}else{
			try {
				BeanContrato beanContrato = new BeanContrato();
				BeanUtils.copyProperties(beanContrato, formContrato);
				
				ModelContrato.getInstance().registraPagamento(beanContrato);
				
				fw.setPath("/actionContrato.do?m=opcoes&ctncodg="+formContrato.getCtncodg());
				
				request.getSession().removeAttribute("formContrato");
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
			
		}
		return fw;
	}
		
	public ActionForward relatoriosGCOM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0039";
		
		ActionMessages erros = new ActionMessages();
		ActionForward fw = new ActionForward();
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		
		if (!ValidaObjeto.validaData(data1)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial � inv�lida."));
		}
		if (!ValidaObjeto.validaData(data2)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Final � inv�lida."));
		}
		
		if (erros.isEmpty()){
			if (Utils.strBRToDate(data1).after(Utils.strBRToDate(data2))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial n�o pode ser maior que a final."));
			}
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/relatoriosGCOM.do");
			saveErrors(request, erros);
		}else{
		
			try {
				
				Map<Object,Object> map = new HashMap<Object, Object>();
				
				map.put("DATA_INICIO", Utils.strBRToDate(data1));
				map.put("DATA_FIM", Utils.strBRToDate(data2));
				
								
				switch (Integer.parseInt(request.getParameter("tipo"))) {
					case 1:
						map.put("REPORT_NAME", "gcom_diverge_plano");
						break;
					case 2:
						map.put("REPORT_NAME", "gcom_nao_cadastrado");
						break;
					case 3:
						map.put("REPORT_NAME", "gcom_pagamentos");
						break;					
					default:
						map.put("REPORT_NAME", "gcom_diverge_plano");
						break;
				}
								
				GeraRelatorio.geracao(request, response, map, false);
				
				fw = null;
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		
		return fw;
	}
	
	public ActionForward relatoriosSinergia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0040";
		
		ActionMessages erros = new ActionMessages();
		ActionForward fw = new ActionForward();
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		
		if (!ValidaObjeto.validaData(data1)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial � inv�lida."));
		}
		if (!ValidaObjeto.validaData(data2)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Final � inv�lida."));
		}
		
		if (erros.isEmpty()){
			if (Utils.strBRToDate(data1).after(Utils.strBRToDate(data2))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial n�o pode ser maior que a final."));
			}
		}
		
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		
		if (!erros.isEmpty() || usuario == null){
			fw.setPath("/relatoriosSinergia.do");
			saveErrors(request, erros);
		}else{
		
			try {
				
				Map<Object,Object> map = new HashMap<Object, Object>();
				
				map.put("DATA_INICIO", Utils.strBRToDate(data1));
				map.put("DATA_FIM", Utils.strBRToDate(data2));
				map.put("ENNCODG", new Integer(usuario.getEnncodg()));
				
								
				switch (Integer.parseInt(request.getParameter("tipo"))) {
					case 1:
						map.put("REPORT_NAME", "sinergia_producao_agencia");
						break;
					case 2:
						map.put("REPORT_NAME", "sinergia_comissao_agencia_contrato");
						break;
					case 3:
						map.put("REPORT_NAME", "comissao_angariador_resumo");
						break;					
					default:
						map.put("REPORT_NAME", "sinergia_producao_agencia");
						break;
				}
								
				GeraRelatorio.geracao(request, response, map, false);
				
				fw = null;
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		
		return fw;
	}	
	public ActionForward relatoriosSinergiaSup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		final String METODO = "0041";
		
		ActionMessages erros = new ActionMessages();
		ActionForward fw = new ActionForward();
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		
		if (!ValidaObjeto.validaData(data1)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial � inv�lida."));
		}
		if (!ValidaObjeto.validaData(data2)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Final � inv�lida."));
		}
		
		if (erros.isEmpty()){
			if (Utils.strBRToDate(data1).after(Utils.strBRToDate(data2))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial n�o pode ser maior que a final."));
			}
		}
		
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		
		if (!erros.isEmpty() || usuario == null){
			fw.setPath("/relatoriosSinergiaSup.do");
			saveErrors(request, erros);
		}else{
			
			try {
				
				Map<Object,Object> map = new HashMap<Object, Object>();
				
				map.put("DATA_INICIO", Utils.strBRToDate(data1));
				map.put("DATA_FIM", Utils.strBRToDate(data2));
				map.put("ENNCODG", new Integer(request.getParameter("ctncgen")));
				
				
				switch (Integer.parseInt(request.getParameter("tipo"))) {
					case 1:
						map.put("REPORT_NAME", "sinergia_comissao_agencia_contrato");
						break;
					case 2:
						map.put("REPORT_NAME", "sinergia_producao_agencia");
						break;
					case 3:
						map.put("REPORT_NAME", "relat_contrato_extorno2");
						break;
					default:
						map.put("REPORT_NAME", "sinergia_producao_agencia");
					break;
				}
				
				GeraRelatorio.geracao(request, response, map, false);
				
				fw = null;
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		
		return fw;
	}	

}
