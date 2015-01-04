package com.grupoexata.bancario.struts.action;

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
import com.grupoexata.bancario.dao.ModelLigacao;
import com.grupoexata.bancario.dao.ModelMotivoLigacao;
import com.grupoexata.bancario.dao.ModelResultadoLigacao;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.struts.bean.BeanLigacao;
import com.grupoexata.bancario.struts.form.FormLigacao;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionLigacao extends DispatchAction {

	private final static String ERRO_CLASS = "ACLG:";
	
	public ActionForward novo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		
		ActionForward fw = new ActionForward();
		try {
			String enncodg = request.getParameter("enncodg");
			request.getSession().setAttribute("ls_motivoligacao", ModelMotivoLigacao.getInstance().getMotivosLigacao());			
			request.getSession().setAttribute("ls_resultadoligacao", ModelResultadoLigacao.getInstance().getResultadosLigacao());
			
			BeanEntidade entidade = ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg));
			
			request.getSession().setAttribute("angariador", entidade);
			
			request.getSession().removeAttribute("formLigacao");
			
			fw.setPath("/ligacaoNovo.do");
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
		final String METODO = "0002";
		
		ActionForward fw = new ActionForward();
		FormLigacao formLigacao = (FormLigacao)form;
		ActionMessages erros = new ActionMessages();
		
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formLigacao.getLgyvalr()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Valor Informado está incorreto."));
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/ligacaoNovo.do");			
		}else{
		
			try {
				
				BeanLigacao ligacao = new BeanLigacao();
				BeanUtils.copyProperties(ligacao, formLigacao);
				
				ModelLigacao.getInstance().inserir(ligacao);
				
							
				request.getSession().removeAttribute("angariador");
				request.getSession().removeAttribute("formLigacao");
				
				fw.setPath("/angariadorPesquisaLigacao.do");
				
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Ligação Registrada com Sucesso!"));
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		saveErrors(request, erros);
		
		return fw;
	}
	

}
