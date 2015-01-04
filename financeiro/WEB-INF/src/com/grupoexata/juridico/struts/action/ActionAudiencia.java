package com.grupoexata.juridico.struts.action;

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
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.juridico.dao.ModelAudiencia;
import com.grupoexata.juridico.dao.ModelProcesso;
import com.grupoexata.juridico.struts.bean.BeanAudiencia;
import com.grupoexata.juridico.struts.form.FormAudiencia;

public class ActionAudiencia extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACADC:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			String proncodg = request.getParameter("proncodg");
			request.setAttribute("processo", ModelProcesso.getInstance().getProcesso(Integer.parseInt(proncodg)));
			request.setAttribute("ls_audiencia", ModelAudiencia.getInstance().getAudienciasDoProcesso(Integer.parseInt(proncodg)));
			
			fw.setPath("/audienciaLista.do");
			
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
		FormAudiencia formAudiencia = (FormAudiencia)form;
		ActionMessages erros = new ActionMessages();
			
		try {
			
			if (formAudiencia.getAdchhora() != null && formAudiencia.getAdchhora().length() == 5){
				formAudiencia.setAdchhora(formAudiencia.getAdchhora().concat(":00"));
			}
			
			if (!ValidaObjeto.validaHora(formAudiencia.getAdchhora())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Hora Inválida."));
			}
			if (Integer.parseInt(formAudiencia.getAdcncgcd()) == -1){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Cidade é Obrigatória."));
			}
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (!erros.isEmpty() || usuario == null){
				saveErrors(request, erros);
				fw.setPath("/audienciaEditar.do");
			}else{
				BeanAudiencia beanAudiencia = new BeanAudiencia();
				BeanUtils.copyProperties(beanAudiencia, formAudiencia);
				
				beanAudiencia.setAdcnalte(usuario.getEnncodg());
				
				ModelAudiencia.getInstance().update(beanAudiencia);
				
				request.getSession().removeAttribute("formAudiencia");
				request.getSession().removeAttribute("ls_estado");
				
				fw.setPath("/actionAudiencia.do?m=lista&proncodg="+formAudiencia.getAdcncgpro());
				
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
		FormAudiencia formAudiencia = (FormAudiencia)form;
		try {
			
			String adcncodg = request.getParameter("adcncodg");
			BeanAudiencia beanAudiencia = ModelAudiencia.getInstance().getAudiencia(Integer.parseInt(adcncodg));
			
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_cidade", ModelCidade.getInstance().getCidadesDoEstado(beanAudiencia.getAdccufcd()));
			
			BeanUtils.copyProperties(formAudiencia, beanAudiencia);
			
			fw.setPath("/audienciaEditar.do");
			
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
			
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			
			request.getSession().removeAttribute("formAudiencia");
			
			fw.setPath("/audienciaNovo.do");
			
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
		FormAudiencia formAudiencia = (FormAudiencia)form;
		ActionMessages erros = new ActionMessages();
		try {			
			
			if (formAudiencia.getAdchhora() != null && formAudiencia.getAdchhora().length() == 5){
				formAudiencia.setAdchhora(formAudiencia.getAdchhora().concat(":00"));
			}
			
			if (!ValidaObjeto.validaHora(formAudiencia.getAdchhora())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Hora Inválida."));
			}
			if (Integer.parseInt(formAudiencia.getAdcncgcd()) == -1){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Cidade é Obrigatória."));
			}
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (!erros.isEmpty() || usuario == null){
				saveErrors(request, erros);
				fw.setPath("/audienciaNovo.do");
			}else{
				BeanAudiencia beanAudiencia = new BeanAudiencia();
				BeanUtils.copyProperties(beanAudiencia, formAudiencia);
				
				beanAudiencia.setAdcncadt(usuario.getEnncodg());
				
				ModelAudiencia.getInstance().insert(beanAudiencia);
				
				request.getSession().removeAttribute("formAudiencia");
				request.getSession().removeAttribute("ls_estado");
				
				fw.setPath("/actionAudiencia.do?m=lista&proncodg="+formAudiencia.getAdcncgpro());
				
			}
			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
