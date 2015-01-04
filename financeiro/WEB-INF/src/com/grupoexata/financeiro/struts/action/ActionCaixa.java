package com.grupoexata.financeiro.struts.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.utils.GeraRelatorio;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.financeiro.dao.ModelCaixa;
import com.grupoexata.financeiro.struts.bean.BeanCaixa;

public class ActionCaixa extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACCAIXA:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			request.setAttribute("ls_caixa", ModelCaixa.getInstance().getUltimosCaixas());
			
			fw.setPath("/caixaLista.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
		
	public ActionForward fechar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0002";
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
			
		try {
			
			String caxncodg = request.getParameter("caxncodg");
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			BeanCaixa beanCaixa = new BeanCaixa();
			beanCaixa.setCaxncodg(caxncodg);
			beanCaixa.setCaxnresp(usuario.getEnncodg());
			
			if (!ModelCaixa.getInstance().podeFecharCaixa(Integer.parseInt(caxncodg))){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Caixa não pode ser fechado porque existem fechamentos não realizados anteriormente."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);				
			}else{
				ModelCaixa.getInstance().fecharCaixa(beanCaixa);
			}
			
			fw.setPath("/actionMovCaixa.do?m=lista&caxncodg="+caxncodg);
								
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
		
		try {			
			
			fw.setPath("/");
			
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
	
	public ActionForward relatorio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0006";
		ActionForward fw = new ActionForward();
		
		try {			
			String caxncodg = request.getParameter("caxncodg");
			
			BeanCaixa caixa = ModelCaixa.getInstance().getCaixa(Integer.parseInt(caxncodg));
			
			Map<Object,Object> map = new HashMap<Object, Object>();
			
			map.put("DATA", Utils.strBRToDate(caixa.getCaxddata()));			
							
			map.put("REPORT_NAME", "movimento_caixa");
							
			GeraRelatorio.geracao(request, response, map, false);
			
			fw = null;			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	
	public ActionForward relatorioMovimento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0007";
		ActionMessages erros = new ActionMessages();
		ActionForward fw = new ActionForward();
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		
		if (!ValidaObjeto.validaData(data1)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial é inválida."));
		}
		if (!ValidaObjeto.validaData(data2)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Final é inválida."));
		}
		
		if (erros.isEmpty()){
			if (Utils.strBRToDate(data1).after(Utils.strBRToDate(data2))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial não pode ser maior que a final."));
			}
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/relatorioFinanceiro.do");
			saveErrors(request, erros);
		}else{
		
			try {
				
				Map<Object,Object> map = new HashMap<Object, Object>();
				
				map.put("DATA_INICIO", Utils.strBRToDate(data1));
				map.put("DATA_FIM", Utils.strBRToDate(data2));				
								
				switch (Integer.parseInt(request.getParameter("tipo"))) {
					case 1:
						map.put("REPORT_NAME", "contas_pagar_periodo");
						break;					
					case 2:
						map.put("REPORT_NAME", "financeiro_despesas_centrocusto");
						break;
					case 5:
						map.put("REPORT_NAME", "financeiro_centrocusto_receita");
						break;
					case 3:
						map.put("REPORT_NAME", "financeiro_pgto_futuro_contas");
						break;
					case 4:
						map.put("REPORT_NAME", "financeiro_pgto_futuro_sem_conta");
						break;
					case 6:
						map.put("REPORT_NAME", "financeiro_comissao_pend_pgto");
						break;
					case 7:
						map.put("REPORT_NAME", "financeiro_comissao_pgto_agendado");
						break;
					case 8:
						map.put("REPORT_NAME", "finan_faturamento");
						break;
					case 9:
						map.put("REPORT_NAME", "finan_despesa_filial");
						break;
					case 10:
						map.put("REPORT_NAME", "atividade_km_usuario");
						break;
					case 11:
						map.put("REPORT_NAME", "finan_resultado_periodo");
						break;
					case 12:
						map.put("REPORT_NAME", "finan_resultado_centrocusto");
						break;
								
					default:
						map.put("REPORT_NAME", "contas_pagar_periodo");
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
	
	public ActionForward relatorioMovimento2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0008";
		ActionMessages erros = new ActionMessages();
		ActionForward fw = new ActionForward();
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		
		if (!ValidaObjeto.validaData(data1)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial é inválida."));
		}
		if (!ValidaObjeto.validaData(data2)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Final é inválida."));
		}
		
		if (erros.isEmpty()){
			if (Utils.strBRToDate(data1).after(Utils.strBRToDate(data2))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial não pode ser maior que a final."));
			}
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/relatorioFinanceiro.do");
			saveErrors(request, erros);
		}else{
		
			try {
				
				Map<Object,Object> map = new HashMap<Object, Object>();
				
				map.put("DATA_INICIO", Utils.strBRToDate(data1));
				map.put("DATA_FIM", Utils.strBRToDate(data2));				
				map.put("CONTA", Integer.parseInt(request.getParameter("movncgcog")));				
				map.put("CENTRO", Integer.parseInt(request.getParameter("centrocusto")));				
								
				switch (Integer.parseInt(request.getParameter("tipo"))) {
					case 1:
						map.put("REPORT_NAME", "financeiro_movimento_conta");
						break;					
					
								
					default:
						map.put("REPORT_NAME", "financeiro_movimento_conta");
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
