package com.grupoexata.financeiro.struts.action;

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

import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.utils.GeraRelatorio;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.financeiro.dao.ModelContaFixa;
import com.grupoexata.financeiro.dao.ModelPlanoConta;
import com.grupoexata.financeiro.struts.bean.BeanContaFixa;
import com.grupoexata.financeiro.struts.form.FormContaFixa;

public class ActionContaFixa extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACCONTAFIXA:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {			
			request.setAttribute("ls_contafixa", ModelContaFixa.getInstance().getContasFixas());
			
			fw.setPath("/contaFixaLista.do");
			
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
		FormContaFixa formContaFixa = (FormContaFixa)form;
		ActionMessages erros = new ActionMessages();
		try {
			
			if (!ValidaObjeto.validaInteiro(formContaFixa.getCofncont())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição da conta é obrigatória."));
			}
			if (!ValidaObjeto.validaString(formContaFixa.getCofcdesc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Observação é obrigatório."));
			}
			if (!ValidaObjeto.validaInteiro(formContaFixa.getCofndia())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Dia é obrigatório."));
			}
			if (!ValidaObjeto.validaString(formContaFixa.getCofcdocm())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Documento é obrigatório."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/contaFixaEditar.do");
			}else{
				BeanContaFixa beanContaFixa = new BeanContaFixa();
				BeanUtils.copyProperties(beanContaFixa, formContaFixa);				
				
				ModelContaFixa.getInstance().update(beanContaFixa);
				
				request.getSession().removeAttribute("formContaFixa");
				request.getSession().removeAttribute("ls_contas");
			
				fw = lista(mapping, formContaFixa, request, response);
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
		FormContaFixa formContaFixa = (FormContaFixa)form;
			
		try {
			
			String cofncodg = request.getParameter("cofncodg");
			
			BeanContaFixa beanContaFixa = ModelContaFixa.getInstance().getContaFixa(Integer.parseInt(cofncodg));
			BeanUtils.copyProperties(formContaFixa, beanContaFixa);
			
			request.getSession().setAttribute("ls_contas", ModelPlanoConta.getInstance().getPlanosContaSemFilhoPorTipo(2));
						
			fw.setPath("/contaFixaEditar.do");
								
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
			
			request.getSession().removeAttribute("formContaFixa");
			
			fw.setPath("/contaFixaNovo.do");
			
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
		FormContaFixa formContaFixa = (FormContaFixa)form;
		ActionMessages erros = new ActionMessages();
		try {
			
			if (!ValidaObjeto.validaInteiro(formContaFixa.getCofncont())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Descrição da conta é obrigatória."));
			}
			if (!ValidaObjeto.validaInteiro(formContaFixa.getCofndia())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Dia é obrigatório."));
			}
			if (!ValidaObjeto.validaString(formContaFixa.getCofcdocm())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Documento é obrigatório."));
			}
						
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/contaFixaNovo.do");
			}else{
				BeanContaFixa beanContaFixa = new BeanContaFixa();
				BeanUtils.copyProperties(beanContaFixa, formContaFixa);		
				
				ModelContaFixa.getInstance().inserir(beanContaFixa);
				
				request.getSession().removeAttribute("formContaFixa");
				
				fw = lista(mapping, formContaFixa, request, response);
			}	
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
		final String METODO = "0006";
		ActionForward fw = new ActionForward();
		
		try {
			
			Map<Object,Object> map = new HashMap<Object, Object>();
			
			
			map.put("REPORT_NAME", "contas_fixas");
							
			GeraRelatorio.geracao(request, response, map, false);
			
			fw = null;
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward importar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();
		
		try {
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (usuario != null){
				ModelContaFixa.getInstance().importar(Integer.parseInt(usuario.getEnncodg()));
			}
			
			fw.setPath("/actionContaFixa.do?m=lista");
			
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
		final String METODO = "0008";
		ActionForward fw = new ActionForward();
		
		try {
			String cofncodg = request.getParameter("cofncodg");
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (usuario != null && cofncodg != null){
				ModelContaFixa.getInstance().cancelar(Integer.parseInt(cofncodg));
			}
			
			fw.setPath("/actionContaFixa.do?m=lista");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
