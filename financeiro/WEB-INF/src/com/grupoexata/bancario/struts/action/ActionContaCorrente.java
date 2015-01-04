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

import com.grupoexata.bancario.dao.ModelBanco;
import com.grupoexata.bancario.dao.ModelContaCorrente;
import com.grupoexata.bancario.dao.ModelEntidade;
import com.grupoexata.bancario.dao.ModelTipoContaCorrente;
import com.grupoexata.bancario.struts.bean.BeanContaCorrente;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.struts.form.FormContaCorrente;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionContaCorrente extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACCO:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			String enncodg = request.getParameter("enncodg"); 
			
			request.setAttribute("ls_contacorrente", ModelContaCorrente.getInstance().getContaCorrentesDaEntidade(Integer.parseInt(enncodg)));
			request.setAttribute("angariador", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			
			fw.setPath("/contaCorrenteLista.do");
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
			String enncodg = request.getParameter("enncodg");		
			
			request.getSession().removeAttribute("formContaCorrente");
			
			request.getSession().setAttribute("ls_tipocontacorrente", ModelTipoContaCorrente.getInstance().getTiposContaCorrente());
			request.getSession().setAttribute("ls_banco", ModelBanco.getInstance().getBancosTodos());
			request.getSession().setAttribute("angariador", ModelEntidade
					.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			
			fw.setPath("/contaCorrenteNovo.do");
			
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
		FormContaCorrente formContaCorrente = (FormContaCorrente)form;
		try {
			
			String concodg = request.getParameter("concodg");
			
			request.getSession().setAttribute("ls_banco",ModelBanco.getInstance().getBancosTodos());
			request.getSession().setAttribute("ls_tipocontacorrente", ModelTipoContaCorrente.getInstance().getTiposContaCorrente());
			
			BeanContaCorrente conta = ModelContaCorrente.getInstance().getContaCorrente(Integer.parseInt(concodg));
			
			BeanUtils.copyProperties(formContaCorrente, conta);
			
			fw.setPath("/contaCorrenteEditar.do");
			
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
		final String METODO = "0004";
		ActionForward fw = new ActionForward();
		FormContaCorrente formContaCorrente = (FormContaCorrente)form;
		ActionMessages erros = new ActionMessages();
		
		if (!ValidaObjeto.validaString(formContaCorrente.getCocnumr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nº da Conta Corrente é obrigatório."));
		}
		
		if (!ValidaObjeto.validaString(formContaCorrente.getCocagen())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Agência da Conta Corrente é obrigatório."));
		}
		if (!ValidaObjeto.validaString(formContaCorrente.getCoctitu())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Titular da Conta Corrente é obrigatório."));
		}
		
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/contaCorrenteNovo.do");			
		}else{
		
			try {
				
				BeanContaCorrente conta = new BeanContaCorrente();
				BeanUtils.copyProperties(conta, formContaCorrente);
				
				ModelContaCorrente.getInstance().inserir(conta);	
							
				request.getSession().removeAttribute("formContaCorrente");
				request.getSession().removeAttribute("ls_tipocontacorrente");
				request.getSession().removeAttribute("ls_banco");
				request.getSession().removeAttribute("angariador");
				
				fw.setPath("/actionContaCorrente.do?m=lista&enncodg="+formContaCorrente.getConcgen());
				
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Conta Corrente Cadastrado com Sucesso!"));
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0005";
		ActionForward fw = new ActionForward();
		FormContaCorrente formContaCorrente = (FormContaCorrente)form;
		ActionMessages erros = new ActionMessages();
		
		if (!ValidaObjeto.validaString(formContaCorrente.getCocnumr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nº da Conta Corrente é obrigatório."));
		}
		
		if (!ValidaObjeto.validaString(formContaCorrente.getCocagen())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Agência da Conta Corrente é obrigatório."));
		}
		
		if (!ValidaObjeto.validaString(formContaCorrente.getCoctitu())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Titular da Conta Corrente é obrigatório."));
		}
		
		
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/contaCorrenteEditar.do");
		}else{
		
			try {
				
				BeanContaCorrente conta = new BeanContaCorrente();
				BeanUtils.copyProperties(conta, formContaCorrente);
				
				ModelContaCorrente.getInstance().update(conta);				
							
				request.getSession().removeAttribute("formContaCorrente");
				request.getSession().removeAttribute("ls_banco");
				request.getSession().removeAttribute("ls_tipocontacorrente");
				
				fw.setPath("/actionContaCorrente.do?m=lista&enncodg="+conta.getConcgen());
								
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}		
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward dados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0006";
		ActionForward fw = new ActionForward();
		try {
			String concodg = request.getParameter("concodg");
			
			request.setAttribute("contacorrente", ModelContaCorrente.getInstance().getContaCorrente(Integer.parseInt(concodg)));
			fw.setPath("/contaCorrenteDados.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward listaFornec(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();
		
		try {
			String enncodg = request.getParameter("enncodg"); 
			
			request.setAttribute("ls_contacorrente", ModelContaCorrente.getInstance().getContaCorrentesDaEntidade(Integer.parseInt(enncodg)));
			request.setAttribute("fornecedor", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			
			fw.setPath("/contaCorrenteFornecLista.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward novoFornec(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0008";
		ActionForward fw = new ActionForward();
		try {
			String enncodg = request.getParameter("enncodg");		
			
			request.getSession().removeAttribute("formContaCorrente");
			
			request.getSession().setAttribute("ls_tipocontacorrente", ModelTipoContaCorrente.getInstance().getTiposContaCorrente());
			request.getSession().setAttribute("ls_banco", ModelBanco.getInstance().getBancosTodos());
			request.getSession().setAttribute("fornecedor", ModelEntidade
					.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			
			fw.setPath("/contaCorrenteFornecNovo.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward editarFornec(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0009";
		ActionForward fw = new ActionForward();
		FormContaCorrente formContaCorrente = (FormContaCorrente)form;
		try {
			
			String concodg = request.getParameter("concodg");
			
			request.getSession().setAttribute("ls_banco",ModelBanco.getInstance().getBancosTodos());
			request.getSession().setAttribute("ls_tipocontacorrente", ModelTipoContaCorrente.getInstance().getTiposContaCorrente());
			
			BeanContaCorrente conta = ModelContaCorrente.getInstance().getContaCorrente(Integer.parseInt(concodg));
			
			BeanUtils.copyProperties(formContaCorrente, conta);
			
			fw.setPath("/contaCorrenteFornecEditar.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward cadastroFornec(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0010";
		ActionForward fw = new ActionForward();
		FormContaCorrente formContaCorrente = (FormContaCorrente)form;
		ActionMessages erros = new ActionMessages();
		
		if (!ValidaObjeto.validaString(formContaCorrente.getCocnumr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nº da Conta Corrente é obrigatório."));
		}
		
		if (!ValidaObjeto.validaString(formContaCorrente.getCocagen())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Agência da Conta Corrente é obrigatório."));
		}
		if (!ValidaObjeto.validaString(formContaCorrente.getCoctitu())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Titular da Conta Corrente é obrigatório."));
		}
		
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/contaCorrenteFornecNovo.do");	
		}else{
		
			try {
				
				BeanContaCorrente conta = new BeanContaCorrente();
				BeanUtils.copyProperties(conta, formContaCorrente);
				
				ModelContaCorrente.getInstance().inserir(conta);	
							
				request.getSession().removeAttribute("formContaCorrente");
				request.getSession().removeAttribute("ls_tipocontacorrente");
				request.getSession().removeAttribute("ls_banco");
				request.getSession().removeAttribute("fornecedor");
				
				fw.setPath("/actionContaCorrente.do?m=listaFornec&enncodg="+formContaCorrente.getConcgen());
				
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Conta Corrente Cadastrado com Sucesso!"));
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward updateFornec(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0011";
		ActionForward fw = new ActionForward();
		FormContaCorrente formContaCorrente = (FormContaCorrente)form;
		ActionMessages erros = new ActionMessages();
		
		if (!ValidaObjeto.validaString(formContaCorrente.getCocnumr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nº da Conta Corrente é obrigatório."));
		}
		
		if (!ValidaObjeto.validaString(formContaCorrente.getCocagen())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Agência da Conta Corrente é obrigatório."));
		}
		
		if (!ValidaObjeto.validaString(formContaCorrente.getCoctitu())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Titular da Conta Corrente é obrigatório."));
		}
		
		
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/contaCorrenteFornecEditar.do");
		}else{
		
			try {
				
				BeanContaCorrente conta = new BeanContaCorrente();
				BeanUtils.copyProperties(conta, formContaCorrente);
				
				ModelContaCorrente.getInstance().update(conta);				
							
				request.getSession().removeAttribute("formContaCorrente");
				request.getSession().removeAttribute("ls_banco");
				request.getSession().removeAttribute("ls_tipocontacorrente");
				
				fw.setPath("/actionContaCorrente.do?m=listaFornec&enncodg="+conta.getConcgen());
								
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}		
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward listaEmpr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0012";
		ActionForward fw = new ActionForward();
		
		try {
			String enncodg = request.getParameter("enncodg"); 
			
			request.setAttribute("ls_contacorrente", ModelContaCorrente.getInstance().getContaCorrentesDaEntidade(Integer.parseInt(enncodg)));
			request.setAttribute("empregado", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			
			fw.setPath("/contaCorrenteEmprLista.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward novoEmpr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0013";
		ActionForward fw = new ActionForward();
		try {
			String enncodg = request.getParameter("enncodg");		
			
			request.getSession().removeAttribute("formContaCorrente");
			
			request.getSession().setAttribute("ls_tipocontacorrente", ModelTipoContaCorrente.getInstance().getTiposContaCorrente());
			request.getSession().setAttribute("ls_banco", ModelBanco.getInstance().getBancosTodos());
			request.getSession().setAttribute("empregado", ModelEntidade
					.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			
			fw.setPath("/contaCorrenteEmprNovo.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward editarEmpr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0014";
		ActionForward fw = new ActionForward();
		FormContaCorrente formContaCorrente = (FormContaCorrente)form;
		try {
			
			String concodg = request.getParameter("concodg");
			
			request.getSession().setAttribute("ls_banco",ModelBanco.getInstance().getBancosTodos());
			request.getSession().setAttribute("ls_tipocontacorrente", ModelTipoContaCorrente.getInstance().getTiposContaCorrente());
			
			BeanContaCorrente conta = ModelContaCorrente.getInstance().getContaCorrente(Integer.parseInt(concodg));
			
			BeanUtils.copyProperties(formContaCorrente, conta);
			
			fw.setPath("/contaCorrenteEmprEditar.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward cadastroEmpr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0015";
		ActionForward fw = new ActionForward();
		FormContaCorrente formContaCorrente = (FormContaCorrente)form;
		ActionMessages erros = new ActionMessages();
		
		if (!ValidaObjeto.validaString(formContaCorrente.getCocnumr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nº da Conta Corrente é obrigatório."));
		}
		
		if (!ValidaObjeto.validaString(formContaCorrente.getCocagen())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Agência da Conta Corrente é obrigatório."));
		}
		if (!ValidaObjeto.validaString(formContaCorrente.getCoctitu())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Titular da Conta Corrente é obrigatório."));
		}
		
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/contaCorrenteEmprNovo.do");
		}else{
		
			try {
				
				BeanContaCorrente conta = new BeanContaCorrente();
				BeanUtils.copyProperties(conta, formContaCorrente);
				
				ModelContaCorrente.getInstance().inserir(conta);	
							
				request.getSession().removeAttribute("formContaCorrente");
				request.getSession().removeAttribute("ls_tipocontacorrente");
				request.getSession().removeAttribute("ls_banco");
				request.getSession().removeAttribute("empregado");
				
				fw.setPath("/actionContaCorrente.do?m=listaEmpr&enncodg="+formContaCorrente.getConcgen());
				
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Conta Corrente Cadastrado com Sucesso!"));
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
				
		return fw;
	}
	
	public ActionForward updateEmpr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0016";
		ActionForward fw = new ActionForward();
		FormContaCorrente formContaCorrente = (FormContaCorrente)form;
		ActionMessages erros = new ActionMessages();
		
		if (!ValidaObjeto.validaString(formContaCorrente.getCocnumr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nº da Conta Corrente é obrigatório."));
		}
		
		if (!ValidaObjeto.validaString(formContaCorrente.getCocagen())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Agência da Conta Corrente é obrigatório."));
		}
		
		if (!ValidaObjeto.validaString(formContaCorrente.getCoctitu())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Titular da Conta Corrente é obrigatório."));
		}
		
		
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/contaCorrenteEmprEditar.do");
		}else{
		
			try {
				
				BeanContaCorrente conta = new BeanContaCorrente();
				BeanUtils.copyProperties(conta, formContaCorrente);
				
				ModelContaCorrente.getInstance().update(conta);				
							
				request.getSession().removeAttribute("formContaCorrente");
				request.getSession().removeAttribute("ls_banco");
				request.getSession().removeAttribute("ls_tipocontacorrente");
				
				fw.setPath("/actionContaCorrente.do?m=listaEmpr&enncodg="+conta.getConcgen());
								
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}		
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward listaCaixaWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0017";
		ActionForward fw = new ActionForward();
		
		try {
			String enncodg = request.getParameter("enncodg"); 
			
			request.setAttribute("ls_contacorrente", ModelContaCorrente.getInstance().getContaCorrentesDaEntidade(Integer.parseInt(enncodg)));
			request.setAttribute("fornecedor", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			
			fw.setPath("/contaCorrenteListaCaixaWindow.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0018";
		
		ActionForward fw = new ActionForward();
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		String concodg = request.getParameter("concodg");
		try {
			
			BeanContaCorrente conta = ModelContaCorrente.getInstance().getContaCorrente(Integer.parseInt(concodg));
		
			if (usuario != null && conta != null)
				ModelContaCorrente.getInstance().delete(conta);				
						
			request.getSession().removeAttribute("formContaCorrente");
			request.getSession().removeAttribute("ls_banco");
			request.getSession().removeAttribute("ls_tipocontacorrente");
			
			fw.setPath("/actionContaCorrente.do?m=lista&enncodg="+conta.getConcgen());
							
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	public ActionForward deleteEmpr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0019";
		
		ActionForward fw = new ActionForward();
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		String concodg = request.getParameter("concodg");
		try {
			
			BeanContaCorrente conta = ModelContaCorrente.getInstance().getContaCorrente(Integer.parseInt(concodg));
			
			if (usuario != null && conta != null)
				ModelContaCorrente.getInstance().delete(conta);				
			
			request.getSession().removeAttribute("formContaCorrente");
			request.getSession().removeAttribute("ls_banco");
			request.getSession().removeAttribute("ls_tipocontacorrente");
			
			fw.setPath("/actionContaCorrente.do?m=listaEmpr&enncodg="+conta.getConcgen());
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	public ActionForward deleteFornec(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0020";
		
		ActionForward fw = new ActionForward();
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		String concodg = request.getParameter("concodg");
		try {
			
			BeanContaCorrente conta = ModelContaCorrente.getInstance().getContaCorrente(Integer.parseInt(concodg));
			
			if (usuario != null && conta != null)
				ModelContaCorrente.getInstance().delete(conta);				
			
			request.getSession().removeAttribute("formContaCorrente");
			request.getSession().removeAttribute("ls_banco");
			request.getSession().removeAttribute("ls_tipocontacorrente");
			
			fw.setPath("/actionContaCorrente.do?m=listaFornec&enncodg="+conta.getConcgen());
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}

}
