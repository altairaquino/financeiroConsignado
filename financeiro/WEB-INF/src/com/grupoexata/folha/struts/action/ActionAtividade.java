package com.grupoexata.folha.struts.action;

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

import com.grupoexata.bancario.dao.ModelCidade;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.utils.GeraRelatorio;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.folha.bean.BeanAtividade;
import com.grupoexata.folha.dao.ModelAtividade;
import com.grupoexata.folha.struts.form.FormAtividade;

public class ActionAtividade extends DispatchAction {

	private final static String ERRO_CLASS = "ACATI:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		try {
			
			String enncodg = request.getParameter("enncodg");
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (enncodg == null && usuario != null){
				request.setAttribute("ls_atividade", 
						ModelAtividade.getInstance().getAtividadesDaEntidade(Integer.parseInt(usuario.getEnncodg())));
			}else{
				request.setAttribute("ls_atividade", 
						ModelAtividade.getInstance().getAtividadesDaEntidade(Integer.parseInt(enncodg)));
			}			
			
			fw.setPath("/atividadeLista.do");
			
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
		final String METODO = "0002";
		ActionForward fw = new ActionForward();

		try {
			
			request.getSession().removeAttribute("formAtividade");
			
			request.getSession().setAttribute("ls_data", ModelAtividade.getInstance().datasAtividade());
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			
			fw.setPath("/atividadeNovo.do");
			
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
		final String METODO = "0003";
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormAtividade formAtividade = (FormAtividade)form;
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		
		try {
			
			if (formAtividade.getAtithora() != null && formAtividade.getAtithora().length() == 5){
				formAtividade.setAtithora(formAtividade.getAtithora().concat(":00"));
			}
			
			if (!ValidaObjeto.validaData(formAtividade.getAtiddata())) {
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Data é obrigatória."));
			}
			if (!ValidaObjeto.validaHora(formAtividade.getAtithora())) {
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Hora da atividade é obrigatória."));
			}
			if (!ValidaObjeto.validaString(formAtividade.getAticloca())) {
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Local é obrigatório."));
			}
			if (!ValidaObjeto.validaString(formAtividade.getAticcont())) {
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Contato é obrigatório."));
			}
			if (!ValidaObjeto.validaString(formAtividade.getAticfone())) { 
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Fone do Contato é obrigatório."));
			}
			if (!ValidaObjeto.validaString(formAtividade.getAticdesc())) { 
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Descrição é obrigatória."));
			}
			if (!ValidaObjeto.validaString(formAtividade.getAticpers())) { 
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Perspectiva é obrigatória."));
			}
			if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formAtividade.getAtiygast()))) { 
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Valor é obrigatória."));
			}
			
			if (ValidaObjeto.validaInteiro(formAtividade.getAtinodin()) && ValidaObjeto.validaInteiro(formAtividade.getAtinodfn())){
				if (Integer.parseInt(formAtividade.getAtinodin()) >= Integer.parseInt(formAtividade.getAtinodfn())){
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Odômetro inicial é maior que o final."));
				}
			}else{
				if (!ValidaObjeto.validaInteiro(formAtividade.getAtinodin()) ^ !ValidaObjeto.validaInteiro(formAtividade.getAtinodfn())){
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Odômetro inicial e final devem ser informados."));
				}				
			}
			
			if (!erros.isEmpty() || usuario == null){
				saveErrors(request, erros);
				fw.setPath("/atividadeNovo.do");
			} else {
				BeanAtividade beanAtividade = new BeanAtividade();
				BeanUtils.copyProperties(beanAtividade, formAtividade);
				beanAtividade.setAtincgen(usuario.getEnncodg());
				
				ModelAtividade.getInstance().insert(beanAtividade);
				
				request.getSession().removeAttribute("ls_data");
				request.getSession().removeAttribute("formAtividade");
				
				fw = lista(mapping, formAtividade, request, response);
				
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
		final String METODO = "0004";
		ActionForward fw = new ActionForward();
		FormAtividade formAtividade = (FormAtividade)form;
		try {
			String atincodg = request.getParameter("atincodg");
			
			BeanAtividade beanAtividade = ModelAtividade.getInstance().get(Integer.parseInt(atincodg));
			
			BeanUtils.copyProperties(formAtividade, beanAtividade);
			
			request.getSession().setAttribute("ls_data", ModelAtividade.getInstance().datasAtividade());
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_cidade", ModelCidade.getInstance().getCidadesDoEstado(beanAtividade.getAticufcd()));
		
			
			fw.setPath("/atividadeEditar.do");
			
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
		final String METODO = "0005";
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormAtividade formAtividade = (FormAtividade)form;
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		
		try {
			
			if (formAtividade.getAtithora() != null && formAtividade.getAtithora().length() == 5){
				formAtividade.setAtithora(formAtividade.getAtithora().concat(":00"));
			}
			
			if (!ValidaObjeto.validaData(formAtividade.getAtiddata())) {
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Data é obrigatória."));
			}	
			if (!ValidaObjeto.validaHora(formAtividade.getAtithora())) {
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Hora da atividade é obrigatória."));
			}
			if (!ValidaObjeto.validaString(formAtividade.getAticloca())) {
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Local é obrigatório."));
			}
			if (!ValidaObjeto.validaString(formAtividade.getAticcont())) {
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Contato é obrigatório."));
			}
			if (!ValidaObjeto.validaString(formAtividade.getAticfone())) { 
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Fone do Contato é obrigatório."));
			}
			if (!ValidaObjeto.validaString(formAtividade.getAticdesc())) { 
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Descrição é obrigatória."));
			}
			if (!ValidaObjeto.validaString(formAtividade.getAticpers())) { 
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Perspectiva é obrigatória."));
			}
			if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formAtividade.getAtiygast()))) { 
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Valor é obrigatória."));
			}
			
			if (ValidaObjeto.validaInteiro(formAtividade.getAtinodin()) && ValidaObjeto.validaInteiro(formAtividade.getAtinodfn())){
				if (Integer.parseInt(formAtividade.getAtinodin()) >= Integer.parseInt(formAtividade.getAtinodfn())){
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Odômetro inicial é maior que o final."));
				}
			}else{
				if (!ValidaObjeto.validaInteiro(formAtividade.getAtinodin()) ^ !ValidaObjeto.validaInteiro(formAtividade.getAtinodfn())){
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default", "Odômetro inicial e final devem ser informados."));
				}				
			}
			
			if (!erros.isEmpty() || usuario == null){
				saveErrors(request, erros);
				fw.setPath("/atividadeEditar.do");
			} else {
				BeanAtividade beanAtividade = new BeanAtividade();
				BeanUtils.copyProperties(beanAtividade, formAtividade);
				beanAtividade.setAtincgen(usuario.getEnncodg());
				
				ModelAtividade.getInstance().update(beanAtividade);
				
				request.getSession().removeAttribute("ls_data");
				request.getSession().removeAttribute("formAtividade");
				
				fw = lista(mapping, formAtividade, request, response);
				
			}
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
		final String METODO = "0006";
		
		ActionForward fw = new ActionForward();
		try {
			String atincodg = request.getParameter("atincodg");
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (usuario != null){
				ModelAtividade.getInstance().cancelar(
						Integer.parseInt(atincodg),
						Integer.parseInt(usuario.getEnncodg()));				
			}
			
			fw = lista(mapping, form, request, response);
			
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
		
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		
		if (!ValidaObjeto.validaData(data1)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial é inválida."));
		}
		if (!ValidaObjeto.validaData(data2)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Final é inválida."));
		}
		/*
		if (erros.isEmpty()){
			if (Utils.strBRToDate(data1).after(Utils.strBRToDate(data2))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial não pode ser maior que a final."));
			}
		}
		*/
		if (!erros.isEmpty() || usuario == null){
			fw.setPath("/atividadeRelatorios.do");
			saveErrors(request, erros);
		}else{
		
			try {
				
				Map<Object,Object> map = new HashMap<Object, Object>();
				
				map.put("DATA_INICIO", Utils.strBRToDate(data1));
				map.put("DATA_FIM", Utils.strBRToDate(data2));		
				
				
				map.put("ENNCODG", new Integer(request.getParameter("enncodg")));
								
				switch (request.getParameter("tipo").charAt(0)) {
					case '1':
						map.put("REPORT_NAME", "atividade_usuario_detalhes");
						break;
					case '2':
						map.put("REPORT_NAME", "atividade_km_usuario");
						break;				
					default:
						map.put("REPORT_NAME", "atividade_usuario_detalhe");
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
