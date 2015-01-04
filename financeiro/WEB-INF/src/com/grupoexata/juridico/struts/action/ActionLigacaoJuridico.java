package com.grupoexata.juridico.struts.action;

import java.util.List;

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
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.utils.EnviarEmail;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.juridico.dao.ModelLigacaoJuridico;
import com.grupoexata.juridico.struts.bean.BeanLigacaoJuridico;
import com.grupoexata.juridico.struts.form.FormLigacaoJuridico;

public class ActionLigacaoJuridico extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACLGJ:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			String enncodg = request.getParameter("enncodg");
			
			request.setAttribute("contato", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			request.setAttribute("ls_ligacaojuridico", ModelLigacaoJuridico.getInstance().getLigacoesDoContato(Integer.parseInt(enncodg)));
			
			fw.setPath("/ligacaoJuridicoLista.do");
			
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
			request.getSession().removeAttribute("formLigacaoJuridico");
			
			fw.setPath("/ligacaoJuridicoNovo.do");
			
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
		FormLigacaoJuridico formLigacaoJuridico = (FormLigacaoJuridico)form;
		ActionMessages erros = new ActionMessages();
		try {			
			
			if (!ValidaObjeto.validaInteiro(formLigacaoJuridico.getLgjncgct())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe no contato antes de salvar."));
			}
			if (!ValidaObjeto.validaString(formLigacaoJuridico.getLgjmobsv())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Ocorrências/Observações é obrigatório."));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/ligacaoJuridicoNovo.do");
			}else{
				
				BeanLigacaoJuridico beanLigacaoJuridico = new BeanLigacaoJuridico();
				BeanUtils.copyProperties(beanLigacaoJuridico, formLigacaoJuridico);
				
				ModelLigacaoJuridico.getInstance().insert(beanLigacaoJuridico);
				
				request.getSession().removeAttribute("formLigacaoJuridico");
				
//				if (formLigacaoJuridico.getLgjlmail().equals("T")){
//					EnviarEmail.getInstance().enviarEmailJuridico(beanLigacaoJuridico);
//				}
				
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
		FormLigacaoJuridico formLigacaoJuridico = (FormLigacaoJuridico)form;
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		try {			

			if (usuario != null){
				List<BeanLigacaoJuridico> list = ModelLigacaoJuridico.getInstance().getLigacoesParaEnvioEmail(formLigacaoJuridico.getLgjdprox());
				EnviarEmail.getInstance().enviarEmailJuridicoDia(list);
				
				request.setAttribute("msg", "E-mail enviado com sucesso!");
			}
			
			request.getSession().removeAttribute("formLigacaoJuridico");
			fw.setPath("/ligacaoJuridicoNovo.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	

}
