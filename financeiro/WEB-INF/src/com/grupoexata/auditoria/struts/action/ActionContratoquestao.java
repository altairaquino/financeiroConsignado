package com.grupoexata.auditoria.struts.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.auditoria.bean.BeanContratoquestao;
import com.grupoexata.auditoria.dao.ModelQuestionario;
import com.grupoexata.auditoria.struts.form.FormContratoquestao;
import com.grupoexata.bancario.dao.ModelContrato;
import com.grupoexata.bancario.struts.bean.BeanContrato;
import com.grupoexata.bancario.utils.GeraRelatorio;
import com.grupoexata.bancario.utils.Utils;

public class ActionContratoquestao extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACCTQN:";
	
	public ActionForward montar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		try {
			
			String ctncodg = request.getParameter("ctncodg");
			
			BeanContrato beanContrato = ModelContrato.getInstance().getContrato(Integer.parseInt(ctncodg));
			
			request.getSession().setAttribute("contrato", beanContrato);
			
			fw.setPath("/questionario.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS + METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward inserir(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0002";
		ActionForward fw = new ActionForward();
		ActionMessages messages = new ActionMessages();
		
		try {
			Enumeration<String> eparams = request.getParameterNames();
			List<String> listParams = new ArrayList<String>();
			FormContratoquestao formctq = (FormContratoquestao)form;
			Pattern p = Pattern. compile("^qst_[0-9]+$");
			while(eparams.hasMoreElements()){
				String s = eparams.nextElement(); 
				if(p.matcher(s).matches()){
					listParams.add(s);
				}
			}
			List<BeanContratoquestao> list = new ArrayList<BeanContratoquestao>();
			List<BeanContratoquestao> listErro = new ArrayList<BeanContratoquestao>();
			for (String s : listParams) {
				String ss[] = request.getParameter(s).trim().split(";");
				s = s + "_" + formctq.getCtqncgqtn();
				s = s + "_" + formctq.getCtqncgct();
				s = s + "_" + formctq.getCtqncgen();
				s = s + "_" + ss[0];
				s = s + "_" + ss[1];
				ss = s.split("_");
				for (int i = 0; i < ss.length; i++) {
					ss[i] = ss[i].trim();
				}
				BeanContratoquestao ctq = new BeanContratoquestao();
				ctq.setCtqncgqst(ss[1]);
				ctq.setCtqncgqtn(ss[2]);
				ctq.setCtqncgct(ss[3]);
				ctq.setCtqncgen(ss[4]);
				ctq.setCtqncgopc(ss[5]);
				ctq.setCtqcnumr(ss[6]);
				String opc = ctq.getCtqncgopc();
				if(opc != null && !opc.equals("x")){
					list.add(ctq);
				}else{
					listErro.add(ctq);
				}
			}
			if(listErro.isEmpty()){
				ModelQuestionario.getIntance().addQuestaos(list);
				fw.setPath("/actionContrato.do?m=listaAuditoria");
				request.getSession().removeAttribute("contrato");
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Respondido com sucesso."));
			}else{
				StringBuffer sb = new StringBuffer();
				
				if(listErro.size() > 1){
					sb.append("Questões não respodidas: ");
				}else{
					sb.append("Questão não respodida: ");
				}
				
				boolean primeiro = true;
				
				for (BeanContratoquestao erro : listErro) {
					if(primeiro){
						primeiro = false;
					}else{
						sb.append(", ");
					}
					sb.append(erro.getCtqcnumr());
				}
				
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default",sb.toString()));
				fw.setPath("/questionario.do");
				
			}
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		saveErrors(request, messages);
		
		return fw;
	}
	
	public ActionForward relatorios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0003";
		ActionForward fw = new ActionForward();
		
		try {
			
			String ctdcadt = request.getParameter("ctdcadt");
			
			Map<Object,Object> map = new HashMap<Object, Object>();
			
			map.put("DATA", Utils.strBRToDate(ctdcadt));
			
			map.put("REPORT_NAME", "auditoria_contratos_data");
							
			GeraRelatorio.geracao(request, response, map, false);
			
			fw = null;
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
}
