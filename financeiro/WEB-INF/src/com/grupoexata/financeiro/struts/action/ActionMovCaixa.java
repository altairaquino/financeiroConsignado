package com.grupoexata.financeiro.struts.action;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.dao.ModelEntidade;
import com.grupoexata.bancario.dao.ModelFilial;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.utils.FormataObj;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.financeiro.dao.ModelCaixa;
import com.grupoexata.financeiro.dao.ModelCentroCusto;
import com.grupoexata.financeiro.dao.ModelCentroMovCaixa;
import com.grupoexata.financeiro.dao.ModelFormaLiquidez;
import com.grupoexata.financeiro.dao.ModelGrupoConta;
import com.grupoexata.financeiro.dao.ModelMovCaixa;
import com.grupoexata.financeiro.dao.ModelOrigemEntrada;
import com.grupoexata.financeiro.struts.bean.BeanCaixa;
import com.grupoexata.financeiro.struts.bean.BeanCentroMovCaixa;
import com.grupoexata.financeiro.struts.bean.BeanMovCaixa;
import com.grupoexata.financeiro.struts.form.FormMovCaixa;

public class ActionMovCaixa extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACMOV:";
	
	public ActionForward novo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		try {
									
			request.getSession().setAttribute("ls_centrocusto", ModelCentroCusto.getInstance().getCentrosCustoAtivos());
			request.getSession().setAttribute("ls_formaliquidez", ModelFormaLiquidez.getInstance().getFormasDeLiquidez());
			request.getSession().setAttribute("ls_filiais", ModelFilial.getInstance().getFiliais());
			request.getSession().setAttribute("ls_origementrada", ModelOrigemEntrada.getInstance().getOrigensEntrada());
			request.getSession().setAttribute("ls_grupoconta", ModelGrupoConta.getInstance().getGruposConta());
			
			request.getSession().removeAttribute("formMovCaixa");
			
			fw.setPath("/movCaixaNovo.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}
	
	public ActionForward novoEntrada(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		ActionForward fw = new ActionForward();
		try {
			
			request.getSession().setAttribute("ls_centrocusto", ModelCentroCusto.getInstance().getCentrosCustoAtivos());
			request.getSession().setAttribute("ls_formaliquidez", ModelFormaLiquidez.getInstance().getFormasDeLiquidez());
			request.getSession().setAttribute("ls_filiais", ModelFilial.getInstance().getFiliais());
			request.getSession().setAttribute("ls_origementrada", ModelOrigemEntrada.getInstance().getOrigensEntrada());
			request.getSession().setAttribute("ls_grupoconta", ModelGrupoConta.getInstance().getGruposConta());
			
			request.getSession().removeAttribute("formMovCaixa");
			
			fw.setPath("/movimentoEntrada.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}
	
	public ActionForward novoSaida(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		ActionForward fw = new ActionForward();
		try {
			
			request.getSession().setAttribute("ls_centrocusto", ModelCentroCusto.getInstance().getCentrosCustoAtivos());
			request.getSession().setAttribute("ls_formaliquidez", ModelFormaLiquidez.getInstance().getFormasDeLiquidez());
			request.getSession().setAttribute("ls_filiais", ModelFilial.getInstance().getFiliais());
			request.getSession().setAttribute("ls_origementrada", ModelOrigemEntrada.getInstance().getOrigensEntrada());
			request.getSession().setAttribute("ls_grupoconta", ModelGrupoConta.getInstance().getGruposConta());
			
			request.getSession().removeAttribute("formMovCaixa");
			
			fw.setPath("/movimentoSaida.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}
	
	public ActionForward novoFuturo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		ActionForward fw = new ActionForward();
		try {
			
			request.getSession().setAttribute("ls_centrocusto", ModelCentroCusto.getInstance().getCentrosCustoAtivos());
			request.getSession().setAttribute("ls_formaliquidez", ModelFormaLiquidez.getInstance().getFormasDeLiquidez());
			request.getSession().setAttribute("ls_filiais", ModelFilial.getInstance().getFiliais());
			request.getSession().setAttribute("ls_origementrada", ModelOrigemEntrada.getInstance().getOrigensEntrada());
			request.getSession().setAttribute("ls_grupoconta", ModelGrupoConta.getInstance().getGruposConta());
			//request.getSession().setAttribute("ls_mov_dia", ModelMovCaixa.getInstance().getMovimentosDoDia());
			//request.getSession().setAttribute("saldo_dia", formatador.formataValor(ModelMovCaixa.getInstance().getSaldoDoDia()));			
			
			request.getSession().removeAttribute("formMovCaixa");
			
			fw.setPath("/contaPagarNovo.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}
	
	public ActionForward editarFuturo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		ActionForward fw = new ActionForward();
		FormMovCaixa formMovCaixa = (FormMovCaixa)form;
		try {
			String movncodg = request.getParameter("movncodg"); 
			
			BeanMovCaixa beanMovCaixa = ModelMovCaixa.getInstance().getMovCaixa(Integer.parseInt(movncodg));
			
			BeanUtils.copyProperties(formMovCaixa, beanMovCaixa);
			
			request.getSession().setAttribute("ls_centrocusto", ModelCentroCusto.getInstance().getCentrosCustoAtivos());
			request.getSession().setAttribute("ls_formaliquidez", ModelFormaLiquidez.getInstance().getFormasDeLiquidez());
			request.getSession().setAttribute("ls_filiais", ModelFilial.getInstance().getFiliais());
			request.getSession().setAttribute("ls_origementrada", ModelOrigemEntrada.getInstance().getOrigensEntrada());
			request.getSession().setAttribute("ls_grupoconta", ModelGrupoConta.getInstance().getGruposConta());
						
			fw.setPath("/contaPagarEditar.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward fw = new ActionForward();
		try {
			
			String caxncodg = request.getParameter("caxncodg");
			
			BeanCaixa beanCaixa = ModelCaixa.getInstance().getCaixa(Integer.parseInt(caxncodg));
			
			request.setAttribute("caixa", beanCaixa);
			request.setAttribute("ls_movcaixa", ModelMovCaixa.getInstance().getMovimentosDoDia(
					Utils.strBRToDate(beanCaixa.getCaxddata())));
			
			fw.setPath("/movCaixaLista.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}
	
	public ActionForward listaPagamentoFuturo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ActionForward fw = new ActionForward();
		try {
			
			request.setAttribute("ls_movcaixa", ModelMovCaixa.getInstance().listaPagamentosFuturos());
			
			fw.setPath("/contaPagarLista.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}
	
	public ActionForward listaDoFornecedor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ActionForward fw = new ActionForward();
		try {
			String enncodg = request.getParameter("enncodg");
			
			request.setAttribute("fornecedor", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			
			request.setAttribute("ls_movcaixa", ModelMovCaixa.getInstance().getMovimentosDoFornecedor(Integer.parseInt(enncodg)));
			
			fw.setPath("/movCaixaFornecedorLista.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}
	
	public ActionForward cancela(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward fw = new ActionForward();
		try {
			
			String movncodg = request.getParameter("movncodg");
			String caxncodg = request.getParameter("caxncodg");
			
			ModelMovCaixa.getInstance().cancela(Integer.parseInt(movncodg));
						
			fw.setPath("/actionMovCaixa.do?m=lista&caxncodg="+caxncodg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}
	
	public ActionForward dados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward fw = new ActionForward();
		try {
			
			String movncodg = request.getParameter("movncodg");
			
			BeanMovCaixa mov = ModelMovCaixa.getInstance().getMovCaixa(Integer.parseInt(movncodg)); 
			
			request.setAttribute("movcaixa", mov);
			request.setAttribute("ls_centromovcaixa", ModelCentroMovCaixa.getInstance().getCentrosMovCaixa(Integer.parseInt(mov.getMovncodg())));
						
			fw.setPath("/movCaixaDados.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}
	
	public ActionForward dadosFuturo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		ActionForward fw = new ActionForward();
		try {
			
			String movncodg = request.getParameter("movncodg");
			
			request.setAttribute("contapagar", ModelMovCaixa.getInstance().getMovCaixa(Integer.parseInt(movncodg)));
			
			request.setAttribute("ls_centromovcaixa", ModelCentroMovCaixa.getInstance().getCentrosMovCaixa(Integer.parseInt(movncodg)));
			request.setAttribute("ls_formaliquidez", ModelFormaLiquidez.getInstance().getFormasDeLiquidez());
			
			fw.setPath("/contaPagarDados.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}

	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		ActionForward fw = new ActionForward();
		FormMovCaixa formMovCaixa = (FormMovCaixa)form;
		
		try {

			String movncodg = request.getParameter("movncodg");
			
			BeanMovCaixa mov = ModelMovCaixa.getInstance().getMovCaixa(Integer.parseInt(movncodg));
			BeanUtils.copyProperties(formMovCaixa, mov);
			
			request.getSession().setAttribute("ls_formaliquidez", ModelFormaLiquidez.getInstance().getFormasDeLiquidez());
			request.getSession().setAttribute("ls_centromovcaixa", ModelCentroMovCaixa.getInstance().getCentrosMovCaixa(Integer.parseInt(movncodg)));
			request.getSession().setAttribute("ls_origementrada", ModelOrigemEntrada.getInstance().getOrigensEntrada());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		fw.setPath("/movCaixaEditar.do");
		return fw;
	}
	
		
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward fw = new ActionForward();
		FormMovCaixa formMovCaixa = (FormMovCaixa)form;
		ActionMessages erros = new ActionMessages();
		try {
			
			if (formMovCaixa.getMovltipo().equals("M")){
				if (!ValidaObjeto.validaData(formMovCaixa.getMovddata())){
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data do Movimento é obrigatória."));
				}else{
					if (ModelCaixa.getInstance().caixaFechado(Utils.strBRToDate(formMovCaixa.getMovddata()))){
						erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Caixa desta data esta fechado! Escolha outro dia para o movimento."));
					}
				}
			}
			
			if (!ValidaObjeto.validaData(formMovCaixa.getMovdvenc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data do Vencimento é obrigatória."));
			}

			if ((Integer.parseInt(formMovCaixa.getMovncggpc()) == 3) && (Integer.parseInt(formMovCaixa.getMovncgore()) == 0)){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Escolha a Fonta Pagadora."));
			}
			
			/*
			if (!ValidaObjeto.validaString(formMovCaixa.getMovcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Observação é obrigatório."));
			}
			if (!ValidaObjeto.validaString(formMovCaixa.getMovcdocm())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Documento é obrigatório."));
			}*/
			if (!ValidaObjeto.validaString(formMovCaixa.getMovyvalr())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Valor é obrigatório."));
			}
			if (formMovCaixa.getMovncecu() == null || formMovCaixa.getMovncecu().length == 0){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Escolha ao menos um centro de custo."));
			}
			
			if (!formMovCaixa.getMovncggpc().equals("3")){
				formMovCaixa.setMovncgore(null);
			}
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (!erros.isEmpty() || usuario == null){
				saveErrors(request, erros);
				if (formMovCaixa.getMovltipo().equals("M")){
					fw.setPath("/movCaixaNovo.do");
				}else{
					if (formMovCaixa.getMovltipo().equals("F")){
						fw.setPath("/contaPagarNovo.do");
					}else{
						fw.setPath("/home.do");
					}					
				}				
			}else{
				BeanMovCaixa beanMovCaixa = new BeanMovCaixa();
				BeanUtils.copyProperties(beanMovCaixa, formMovCaixa);
				
				beanMovCaixa.setMovncadt(usuario.getEnncodg());
				
				int qtd = Integer.parseInt(formMovCaixa.getMovnqtpc());
				
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTimeInMillis(Utils.strBRToDate(formMovCaixa.getMovdvenc()).getTime());
				
				for (int j = 1;j <= qtd;j++){
				
					int movncodg = ModelMovCaixa.getInstance().getValueGenerator("MOVNCODG_GEN");
					
					beanMovCaixa.setMovdvenc(new FormataObj().formataData(cal.getTime()));
					beanMovCaixa.setMovnnrpc(j+"");
					
					beanMovCaixa.setMovncodg(movncodg+"");
					
					ModelMovCaixa.getInstance().inserir(beanMovCaixa);
					
					for (int i = 0; i < formMovCaixa.getMovncecu().length; i++) {
						BeanCentroMovCaixa centroMovCaixa = new BeanCentroMovCaixa();
						centroMovCaixa.setCrmncgcr(formMovCaixa.getMovncecu()[i]);
						centroMovCaixa.setCrmnmovc(movncodg+"");
						ModelCentroMovCaixa.getInstance().inserir(centroMovCaixa);
					}					
					
					cal.add(Calendar.MONTH, 1);
					
										
				}
												
				request.getSession().removeAttribute("formMovCaixa");
				request.getSession().removeAttribute("ls_centrocusto");
				request.getSession().removeAttribute("ls_mov_dia");
				request.getSession().removeAttribute("ls_formaliquidez");
				request.getSession().removeAttribute("ls_origementrada");
				request.getSession().removeAttribute("ls_grupoconta");	
			
				if (formMovCaixa.getMovltipo().equals("M")){
					fw.setPath("/actionMovCaixa.do?m=novo");
				}else{
					if (formMovCaixa.getMovltipo().equals("F")){
						fw.setPath("/actionMovCaixa.do?m=listaPagamentoFuturo");
					}else{
						fw.setPath("/home.do");
					}					
				}
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}
	
	public ActionForward cadastroEntrada(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward fw = new ActionForward();
		FormMovCaixa formMovCaixa = (FormMovCaixa)form;
		ActionMessages erros = new ActionMessages();
		try {
			
			if (!ValidaObjeto.validaData(formMovCaixa.getMovddata())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data do Movimento é obrigatória."));
			}			
			
			if (!ValidaObjeto.validaData(formMovCaixa.getMovdvenc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data do Vencimento é obrigatória."));
			}

			if (Integer.parseInt(formMovCaixa.getMovncgore()) == 0){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Escolha a Fonta Pagadora."));
			}
			
			/*
			if (!ValidaObjeto.validaString(formMovCaixa.getMovcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Observação é obrigatório."));
			}
			if (!ValidaObjeto.validaString(formMovCaixa.getMovcdocm())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Documento é obrigatório."));
			}*/
			if (!ValidaObjeto.validaString(formMovCaixa.getMovyvalr())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Valor é obrigatório."));
			}
			if (formMovCaixa.getMovncecu() == null || formMovCaixa.getMovncecu().length == 0){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Escolha ao menos um centro de custo."));
			}
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (!erros.isEmpty() || usuario == null){
				saveErrors(request, erros);
				fw.setPath("/movimentoEntrada.do");
								
			}else{
				BeanMovCaixa beanMovCaixa = new BeanMovCaixa();
				BeanUtils.copyProperties(beanMovCaixa, formMovCaixa);
				
				beanMovCaixa.setMovncadt(usuario.getEnncodg());
				
				int qtd = Integer.parseInt(formMovCaixa.getMovnqtpc());
				
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTimeInMillis(Utils.strBRToDate(formMovCaixa.getMovdvenc()).getTime());
				
				for (int j = 1;j <= qtd;j++){
				
					int movncodg = ModelMovCaixa.getInstance().getValueGenerator("MOVNCODG_GEN");
					
					beanMovCaixa.setMovdvenc(new FormataObj().formataData(cal.getTime()));
					beanMovCaixa.setMovnnrpc(j+"");
					
					beanMovCaixa.setMovncodg(movncodg+"");
					
					ModelMovCaixa.getInstance().inserir(beanMovCaixa);
					
					for (int i = 0; i < formMovCaixa.getMovncecu().length; i++) {
						BeanCentroMovCaixa centroMovCaixa = new BeanCentroMovCaixa();
						centroMovCaixa.setCrmncgcr(formMovCaixa.getMovncecu()[i]);
						centroMovCaixa.setCrmnmovc(movncodg+"");
						ModelCentroMovCaixa.getInstance().inserir(centroMovCaixa);
					}					
					
					cal.add(Calendar.MONTH, 1);				
										
				}
												
				request.getSession().removeAttribute("formMovCaixa");
				request.getSession().removeAttribute("ls_centrocusto");
				request.getSession().removeAttribute("ls_formaliquidez");
				request.getSession().removeAttribute("ls_origementrada");
				request.getSession().removeAttribute("ls_grupoconta");
			
				fw = novoEntrada(mapping, formMovCaixa, request, response);
				
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}
	public ActionForward cadastroSaida(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		ActionForward fw = new ActionForward();
		FormMovCaixa formMovCaixa = (FormMovCaixa)form;
		ActionMessages erros = new ActionMessages();
		try {
			
			if (!ValidaObjeto.validaData(formMovCaixa.getMovddata())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data do Movimento é obrigatória."));
			}		
			
			if (!ValidaObjeto.validaData(formMovCaixa.getMovdvenc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data do Vencimento é obrigatória."));
			}
			
			if (!ValidaObjeto.validaInteiro(formMovCaixa.getMovncgcog())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição (Conta) é obrigatório."));
			}

			if (!ValidaObjeto.validaString(formMovCaixa.getMovyvalr())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Valor é obrigatório."));
			}
			
			if (formMovCaixa.getMovncecu() == null || formMovCaixa.getMovncecu().length == 0){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Escolha ao menos um centro de custo."));
			}
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (!erros.isEmpty() || usuario == null){
				saveErrors(request, erros);
				fw.setPath("/movimentoSaida.do");
				
			}else{
				BeanMovCaixa beanMovCaixa = new BeanMovCaixa();
				BeanUtils.copyProperties(beanMovCaixa, formMovCaixa);
				
				beanMovCaixa.setMovncadt(usuario.getEnncodg());
				
				int qtd = Integer.parseInt(formMovCaixa.getMovnqtpc());
				
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTimeInMillis(Utils.strBRToDate(formMovCaixa.getMovdvenc()).getTime());
				
				for (int j = 1;j <= qtd;j++){
					
					int movncodg = ModelMovCaixa.getInstance().getValueGenerator("MOVNCODG_GEN");
					String cod_mov = ModelMovCaixa.getInstance().getCodigoMovimento(formMovCaixa.getMovddata());
					
					
					beanMovCaixa.setMovdvenc(new FormataObj().formataData(cal.getTime()));
					beanMovCaixa.setMovnnrpc(j+"");
					
					beanMovCaixa.setMovncodg(movncodg+"");
					beanMovCaixa.setMovccodg(cod_mov);
					
					request.setAttribute("msg", "Código do Movimento Caixa: "+cod_mov);
					
					ModelMovCaixa.getInstance().inserir(beanMovCaixa);
					
					for (int i = 0; i < formMovCaixa.getMovncecu().length; i++) {
						BeanCentroMovCaixa centroMovCaixa = new BeanCentroMovCaixa();
						centroMovCaixa.setCrmncgcr(formMovCaixa.getMovncecu()[i]);
						centroMovCaixa.setCrmnmovc(movncodg+"");
						ModelCentroMovCaixa.getInstance().inserir(centroMovCaixa);
					}
					
					cal.add(Calendar.MONTH, 1);	
					
				}
				
				request.getSession().removeAttribute("formMovCaixa");
				request.getSession().removeAttribute("ls_centrocusto");
				request.getSession().removeAttribute("ls_formaliquidez");
				request.getSession().removeAttribute("ls_origementrada");
				request.getSession().removeAttribute("ls_grupoconta");
				
				fw = novoSaida(mapping, formMovCaixa, request, response);
				
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}
	
	public ActionForward excluirCentroCusto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();
		
		try {
			String crcncodg = request.getParameter("crmncodg");
			
			BeanCentroMovCaixa centroMovCaixa = ModelCentroMovCaixa.getInstance().getCentroMovCaixa(
					Integer.parseInt(crcncodg));
			
			ModelCentroMovCaixa.getInstance().excluir(centroMovCaixa);
			
			fw.setPath("/actionMovCaixa.do?m=dadosFuturo&movncodg="+centroMovCaixa.getCrmnmovc());
						
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
		
		ActionForward fw = new ActionForward();
		FormMovCaixa formMovCaixa = (FormMovCaixa)form;
		ActionMessages erros = new ActionMessages();
		try {
			
			if (!ValidaObjeto.validaData(formMovCaixa.getMovdvenc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data do Vencimento é obrigatória."));
			}
			
			/*
			if (!ValidaObjeto.validaInteiro(formMovCaixa.getMovncgcog())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Conta é obrigatória."));
			}
			if (!ValidaObjeto.validaString(formMovCaixa.getMovcdocm())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Documento é obrigatório."));
			}*/
			if (!ValidaObjeto.validaString(formMovCaixa.getMovyvalr())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Valor é obrigatório."));
			}
			
			if (formMovCaixa.getMovncggpc() != null && !formMovCaixa.getMovncggpc().equals("3")){
				formMovCaixa.setMovncgore(null);
			}
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (!erros.isEmpty() || usuario == null){
				saveErrors(request, erros);
				fw.setPath("/movCaixaEditar.do");
			}else{
				BeanMovCaixa beanMovCaixa = new BeanMovCaixa();
				BeanUtils.copyProperties(beanMovCaixa, formMovCaixa);
				
				beanMovCaixa.setMovncadt(usuario.getEnncodg());
				
				ModelMovCaixa.getInstance().update(beanMovCaixa);
				
				request.getSession().removeAttribute("formMovCaixa");
				request.getSession().removeAttribute("ls_formaliquidez");
				request.getSession().removeAttribute("ls_origementrada");
				request.getSession().removeAttribute("ls_centromovcaixa");
			
				fw.setPath("/actionMovCaixa.do?m=dados&movncodg="+formMovCaixa.getMovncodg());
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}
	
	public ActionForward pagarFuturo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		final String METODO = "0008";
		ActionForward fw = new ActionForward();
		FormMovCaixa formMovCaixa = (FormMovCaixa)form;
		ActionMessages erros = new ActionMessages();
		
		try {
			
			if (!ValidaObjeto.validaData(formMovCaixa.getMovddata())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data do Pagamento é obrigatória."));
			}else{
				if (ModelCaixa.getInstance().caixaFechado(Utils.strBRToDate(formMovCaixa.getMovddata()))){
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Caixa esta fechado para esta data de pagamento."));
				}
			}
			
			BeanEntidade usuario = (BeanEntidade) request.getSession().getAttribute("usuario");
			
			if (!erros.isEmpty() || usuario == null){
				saveErrors(request, erros);
				fw.setPath("/actionMovCaixa.do?m=dadosFuturo&movncodg="+formMovCaixa.getMovncodg());
			}else{
				BeanMovCaixa beanMovCaixa = new BeanMovCaixa();
				BeanUtils.copyProperties(beanMovCaixa, formMovCaixa);
				String cod_mov = ModelMovCaixa.getInstance().getCodigoMovimento(formMovCaixa.getMovddata());
				
				beanMovCaixa.setMovccodg(cod_mov);
				
				request.setAttribute("msg", "Código do Movimento Caixa: "+cod_mov);
				
				ModelMovCaixa.getInstance().pagarFuturo(beanMovCaixa);
				
				fw = listaPagamentoFuturo(mapping, formMovCaixa, request, response);
				
				request.getSession().removeAttribute("formMovCaixa");
				
			}	
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward cancelarFuturo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();
		
		try {
			String cpncodg = request.getParameter("movncodg");	
			
			BeanMovCaixa beanMovCaixa = ModelMovCaixa.getInstance().getMovCaixa(
					Integer.parseInt(cpncodg));
			
			ModelMovCaixa.getInstance().cancelar(beanMovCaixa);
			
			fw = listaPagamentoFuturo(mapping, form, request, response);
						
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
			String cpncodg = request.getParameter("movncodg");
			
			BeanMovCaixa beanMovCaixa = ModelMovCaixa.getInstance().getMovCaixa(
					Integer.parseInt(cpncodg));
			
			ModelMovCaixa.getInstance().cancelar(beanMovCaixa);
			
			fw.setPath("/actionCaixa.do?m=lista");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		
		return fw;
	}
	
	public ActionForward updateFuturo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward fw = new ActionForward();
		FormMovCaixa formMovCaixa = (FormMovCaixa)form;
		ActionMessages erros = new ActionMessages();
		try {
			
						
			if (!ValidaObjeto.validaData(formMovCaixa.getMovdvenc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data do Vencimento é obrigatória."));
			}

			/*
			if ((Integer.parseInt(formMovCaixa.getMovncggpc()) == 3) && (Integer.parseInt(formMovCaixa.getMovncgore()) == 0)){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Escolha a origem da entrada."));
			}*/
			
			/*
			if (!ValidaObjeto.validaString(formMovCaixa.getMovcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Observação é obrigatório."));
			}
			if (!ValidaObjeto.validaString(formMovCaixa.getMovcdocm())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Documento é obrigatório."));
			}*/
			
			if (!ValidaObjeto.validaString(formMovCaixa.getMovyvalr())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Valor é obrigatório."));
			}
						
			if (!formMovCaixa.getMovncggpc().equals("3")){
				formMovCaixa.setMovncgore(null);
			}
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (!erros.isEmpty() || usuario == null){
				fw.setPath("/contaPagarEditar.do");
				saveErrors(request, erros);								
			}else{
				
				BeanMovCaixa beanMovCaixa = new BeanMovCaixa();
				BeanUtils.copyProperties(beanMovCaixa, formMovCaixa);
				
				beanMovCaixa.setMovncadt(usuario.getEnncodg());
					
				ModelMovCaixa.getInstance().update(beanMovCaixa);
												
				request.getSession().removeAttribute("formMovCaixa");
				request.getSession().removeAttribute("ls_centrocusto");
				request.getSession().removeAttribute("ls_formaliquidez");
				request.getSession().removeAttribute("ls_origementrada");
				request.getSession().removeAttribute("ls_grupoconta");
			
				fw.setPath("/actionMovCaixa.do?m=dadosFuturo&movncodg="+beanMovCaixa.getMovncodg());
				
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fw;
	}

	public ActionForward pesquisaPagamentoFuturo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();
		
		try {
			String movddata = request.getParameter("movddata");
			
			request.setAttribute("ls_movcaixa", ModelMovCaixa.getInstance().listaPagamentosFuturosDia(movddata));
			
			request.getSession().removeAttribute("formMovCaixa");
			
			fw.setPath("/contaPagarLista.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		return fw;
	}
}
