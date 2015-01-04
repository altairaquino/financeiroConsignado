package com.grupoexata.bancario.struts.action;

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

import com.grupoexata.bancario.dao.ModelEntidade;
import com.grupoexata.bancario.dao.ModelFuncionarioAgencia;
import com.grupoexata.bancario.dao.ModelTipoFuncAgencia;
import com.grupoexata.bancario.struts.bean.BeanFuncionarioAgencia;
import com.grupoexata.bancario.struts.form.FormFuncionarioAgencia;
import com.grupoexata.bancario.utils.GeraRelatorio;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionFuncionarioAgencia extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACFCA:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			String enncodg = request.getParameter("enncodg");
			
			request.setAttribute("angariador", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			
			request.setAttribute("ls_funcionarioagencia", ModelFuncionarioAgencia.getInstance().getFuncionariosDaAgencia(Integer.parseInt(enncodg)));
			
			fw.setPath("/funcionarioAgenciaLista.do");
			
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
		FormFuncionarioAgencia formFuncionarioAgencia = (FormFuncionarioAgencia)form;
		ActionMessages erros = new ActionMessages();
		try {			
			
			if (!ValidaObjeto.validaInteiro(formFuncionarioAgencia.getFcancgfc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Funcionário é obrigatório."));
			}
			if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formFuncionarioAgencia.getFcanmeta()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Meta é obrigatória."));
			}
			if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formFuncionarioAgencia.getFcanperc()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Comissão é obrigatória."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/funcionarioAgenciaEditar.do");
			}else{
				
				BeanFuncionarioAgencia beanFuncionarioAgencia = new BeanFuncionarioAgencia();
				BeanUtils.copyProperties(beanFuncionarioAgencia, formFuncionarioAgencia);
				
				ModelFuncionarioAgencia.getInstance().update(beanFuncionarioAgencia);
				
				request.getSession().removeAttribute("formFuncionarioAgencia");
				request.getSession().removeAttribute("ls_tipofuncagencia");
				
				fw.setPath("/actionFuncionarioAgencia.do?m=lista&enncodg="+formFuncionarioAgencia.getFcancgag());
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
		FormFuncionarioAgencia formFuncionarioAgencia = (FormFuncionarioAgencia)form;	
		try {			
			String fcancodg = request.getParameter("fcancodg");
			
			BeanFuncionarioAgencia beanFuncionarioAgencia = ModelFuncionarioAgencia.getInstance().getFuncionarioAgencia(Integer.parseInt(fcancodg));
			
			BeanUtils.copyProperties(formFuncionarioAgencia, beanFuncionarioAgencia);
			
			request.getSession().setAttribute("ls_tipofuncagencia", ModelTipoFuncAgencia.getInstance().getTiposFuncAgenciaTipo("S"));
			
			fw.setPath("/funcionarioAgenciaEditar.do");
			
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
			
			request.getSession().setAttribute("angariador", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			request.getSession().removeAttribute("formFuncionarioAgencia");
			request.getSession().setAttribute("ls_tipofuncagencia", ModelTipoFuncAgencia.getInstance().getTiposFuncAgenciaTipo("S"));
			
			fw.setPath("/funcionarioAgenciaNovo.do");
			
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
		FormFuncionarioAgencia formFuncionarioAgencia = (FormFuncionarioAgencia)form;
		ActionMessages erros = new ActionMessages();
		try {			
			
			if (!ValidaObjeto.validaInteiro(formFuncionarioAgencia.getFcancgfc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Funcionário é obrigatório."));
			}
			if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formFuncionarioAgencia.getFcanmeta()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Meta é obrigatória."));
			}
			if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formFuncionarioAgencia.getFcanperc()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Comissão é obrigatória."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/funcionarioAgenciaNovo.do");
			}else{
				
				BeanFuncionarioAgencia beanFuncionarioAgencia = new BeanFuncionarioAgencia();
				BeanUtils.copyProperties(beanFuncionarioAgencia, formFuncionarioAgencia);
				
				ModelFuncionarioAgencia.getInstance().inserir(beanFuncionarioAgencia);
				
				request.getSession().removeAttribute("angariador");
				request.getSession().removeAttribute("formFuncionarioAgencia");
				request.getSession().removeAttribute("ls_tipofuncagencia");
				
				fw.setPath("/actionFuncionarioAgencia.do?m=lista&enncodg="+formFuncionarioAgencia.getFcancgag());
			}			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward imprimeCarta(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0006";
		ActionForward fw = new ActionForward();
		
		try {
			
			String enncodg = request.getParameter("enncodg");
			
			Map<Object,Object> map = new HashMap<Object, Object>();
			
			map.put("ENNCODG", Integer.parseInt(enncodg));
			
			map.put("REPORT_NAME", "carta_apresentacao_sinergia2");
							
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
