package com.grupoexata.bancario.struts.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.dao.ModelAlteraSenha;
import com.grupoexata.bancario.dao.ModelEmpresa;
import com.grupoexata.bancario.dao.ModelEntidade;
import com.grupoexata.bancario.dao.ModelLog;
import com.grupoexata.bancario.dao.ModelOperacao;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.struts.form.FormLogin;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class ActionLogin extends DispatchAction {
	
	public ActionForward autenticaUsuario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormLogin formLogin = (FormLogin)form;
		
		String senha = formLogin.getSenha();
		String login = formLogin.getLogin();
		
		if (!ValidaObjeto.validaString(senha) || !ValidaObjeto.validaString(login)){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Login ou senha inválidos ou usuário inabilitado."));
		}
		
		@SuppressWarnings("deprecation")
		Date d = new Date(110, 9, 15);
		
		if (d.before(new Date())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Login ou senha inválidos ou usuário inabilitado."));
		}
		
		if (erros.isEmpty()){
			if(ModelEntidade.getInstance().autenticaUsuario(login, senha)){
				
				BeanEntidade usuario = ModelEntidade.getInstance().getBeanEntidadePorLogin(login);
				request.getSession().setAttribute("usuario", usuario);
				request.getSession().setAttribute("menu", ModelOperacao.getInstance().getMenu(Integer.parseInt(usuario.getEnncodg())));
				request.getSession().setAttribute("empresa", ModelEmpresa.getInstance().getEmpresa());
				
				if (usuario.getEnncgtl() == null || usuario.getEncmail() == null){
					request.setAttribute("msg","Seus dados estão desatualizados no sistema, inclusive seu e-mail! \nVá até o menu opções e recadastre seus dados." +
							"\nCom isso você sempre estará informado sobre as novidades.");
					
				}
				
				/*
				if (usuario.getEnncgte().equals("2")){
					BeanAngariador beanAngariador = ModelAngariador.getInstance().getAngariador(Integer.parseInt(usuario.getEnncodg()));
					if (beanAngariador != null)
						request.setAttribute("msg2",(Integer.parseInt(beanAngariador.getAnncgtn())== 3 ? "Parceiro":"Ag�ncia Sinergia") +", para a digita��o no NETCERTO informe o c�digo do sistema EXATA." +
								"\nCaso o c�digo n�o seja informado, pode haver� problema no pagamento das comiss�es."+
								"\nSeu c�digo de vendedor �: "+usuario.getEnncodg()+".");
					//request.setAttribute("msg2", ModelAngariador.getInstance().mensagemProducaoSinergia(Integer.parseInt(usuario.getEnncodg())));				
						
				}				
				*/
				ModelLog.getInstance().registLogEntrada(Integer.parseInt(usuario.getEnncodg()), request.getRemoteHost());
				
				request.getSession().removeAttribute("formLogin");
				
				if (ModelAlteraSenha.getInstance().isTimeChangePassword(Integer.parseInt(usuario.getEnncodg()))){
					request.getSession().setAttribute("bloqueiamenu", "bloqueiamenu");
					erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Foi solicitada uma altera��o de senha.<br>Altere sua senha para continuar tendo acesso ao sistema."));
					saveErrors(request, erros);
					fw.setPath("/usuarioAlteraSenha.do");
				}else{
					request.getSession().removeAttribute("bloqueiamenu");
					fw.setPath("/home.do");
				}
				
			}else{
				request.setAttribute("login_erro", "Login ou senha inválidos ou usuário inabilitado.");
				fw.setPath("/login.do");
			}
		}else{
			request.setAttribute("login_erro", "Informe o Login e a Senha.");
			fw.setPath("/login.do");
		}	
		
		return fw;
	}

	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		
		if (usuario != null){
			ModelLog.getInstance().registLogSaida(Integer.parseInt(usuario.getEnncodg()));
		}
		
		fw.setPath("/login.do");
		
		request.getSession().invalidate();
		
		return fw;
	}
	
	public ActionForward liberaMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		ActionForward fw = new ActionForward();
		
		request.getSession().removeAttribute("bloqueiamenu");
		
		fw.setPath("/home.do");
		
		return fw;
		
	}
		
	
	
	
	

}
