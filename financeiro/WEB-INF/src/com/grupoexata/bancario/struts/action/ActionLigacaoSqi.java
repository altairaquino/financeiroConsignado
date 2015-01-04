package com.grupoexata.bancario.struts.action;

import java.util.HashMap;
import java.util.List;
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

import com.grupoexata.bancario.dao.ModelEntidade;
import com.grupoexata.bancario.dao.ModelLigacaoSqi;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.struts.bean.BeanLigacaoSqi;
import com.grupoexata.bancario.struts.form.FormLigacaoSqi;
import com.grupoexata.bancario.utils.EnviarEmail;
import com.grupoexata.bancario.utils.GeraRelatorio;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionLigacaoSqi extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACLGS:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			String enncodg = request.getParameter("enncodg");
			
			request.setAttribute("contato", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			request.setAttribute("ls_ligacaosqi", ModelLigacaoSqi.getInstance().getLigacoesDoCorretor(Integer.parseInt(enncodg)));
			
			fw.setPath("/ligacaoSqiLista.do");
			
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
		
			
		try {
			
			fw.setPath("/");
								
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
			request.getSession().removeAttribute("formLigacaoSqi");
			
			fw.setPath("/ligacaoSqiNovo.do");
			
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
		FormLigacaoSqi formLigacaoSqi = (FormLigacaoSqi)form;
		ActionMessages erros = new ActionMessages();
		try {			
			
			if (!ValidaObjeto.validaInteiro(formLigacaoSqi.getLgsncgct())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe no contato antes de salvar."));
			}
			if (!ValidaObjeto.validaString(formLigacaoSqi.getLgsmobsv())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Ocorrências/Observações é obrigatório."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/ligacaoSqiNovo.do");
			}else{
				
				BeanLigacaoSqi beanLigacaoJuridico = new BeanLigacaoSqi();
				BeanUtils.copyProperties(beanLigacaoJuridico, formLigacaoSqi);
				
				ModelLigacaoSqi.getInstance().insert(beanLigacaoJuridico);
				
				request.getSession().removeAttribute("formLigacaoSqi");
				
				request.setAttribute("msg", "Ligação registrada com sucesso!");

				fw = novo(mapping, form, request, response);
				
			}			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	public ActionForward enviaEmail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0006";
		
		ActionForward fw = new ActionForward();
		FormLigacaoSqi formLigacaoSqi = (FormLigacaoSqi)form;
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		try {			

			if (usuario != null){
				List<BeanLigacaoSqi> list = ModelLigacaoSqi.getInstance().getLigacoesParaEnvioEmail(formLigacaoSqi.getLgsdprox());
				EnviarEmail.getInstance().enviarEmailSqiDia(list);
				
				request.setAttribute("msg", "E-mail enviado com sucesso!");
			}
			
			request.getSession().removeAttribute("formLigacaoSqi");
			
			fw.setPath("/ligacaoSqiNovo.do");
			
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
			fw.setPath("/relatoriosJuridico.do");
			saveErrors(request, erros);
		}else{
		
			try {
				
				Map<Object,Object> map = new HashMap<Object, Object>();
				
				map.put("DATA_INICIO", Utils.strBRToDate(data1));
				map.put("DATA_FIM", Utils.strBRToDate(data2));	
								
				switch (Integer.parseInt(request.getParameter("tipo"))) {
					case 1:
						map.put("REPORT_NAME", "sqi_agendamento_ligacoes");
						break;
					case 2:
						map.put("REPORT_NAME", "sqi_ligacoes_realizadas");
						break;
					default:
						map.put("REPORT_NAME", "sqi_agendamento_ligacoes");
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
