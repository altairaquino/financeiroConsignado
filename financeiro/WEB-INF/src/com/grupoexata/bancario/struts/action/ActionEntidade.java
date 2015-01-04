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

import com.grupoexata.bancario.dao.ModelAlteraSenha;
import com.grupoexata.bancario.dao.ModelCidade;
import com.grupoexata.bancario.dao.ModelEntidade;
import com.grupoexata.bancario.dao.ModelTipoLogradouro;
import com.grupoexata.bancario.dao.ModelUsuarioGrupo;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.struts.form.FormEntidade;
import com.grupoexata.bancario.utils.CNPJ;
import com.grupoexata.bancario.utils.CPF;
import com.grupoexata.bancario.utils.GeraRelatorio;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.folha.bean.BeanEmpregado;
import com.grupoexata.folha.dao.ModelEmpregado;

public class ActionEntidade extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACEN:";

	public ActionForward verficaCPFCliente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0001";
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade)form;
		
		try {
			if (!ValidaObjeto.validaString(formEntidade.getEncdocm())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF é requerido!"));
			}else{
				if (!CPF.validar(ValidaObjeto.removeCharOfInteger(formEntidade.getEncdocm()))){
					erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF digitado é inválido!"));
				}
			}
			
			if (!erros.isEmpty()){
				fw.setPath("/clienteNovoCPF.do");
			}else{
				BeanEntidade entid = ModelEntidade.getInstance().getBeanEntidadePorCPF(ValidaObjeto.removeCharOfInteger(formEntidade.getEncdocm()), 1);
				
				if (entid != null){
					fw.setPath("/actionEntidade.do?m=dadosCliente&enncodg="+entid.getEnncodg());
				}else{
					request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
					request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
					fw.setPath("/clienteNovo.do");
				}
			}
			
			saveErrors(request, erros);
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward novoAngariador(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0002";
		ActionForward fw = new ActionForward();
		
		try{
			
			request.getSession().removeAttribute("formEntidade");
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			fw.setPath("/angariadorNovo.do");
						
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward cadastroAngariador(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0003";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade)form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome é obrigatório!"));
		}
		
		if (formEntidade.getEnncgpp().equals("1")){
			if (!ValidaObjeto.validaString(formEntidade.getEncdocm())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF é obrigatório."));
			}else{
				if (!CPF.validar(formEntidade.getEncdocm())){
					erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF inválido."));					
				}
			}
		}else{
			if (!ValidaObjeto.validaString(formEntidade.getEncdocm())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CNPJ é obrigatório."));
			}else{
				if (!CNPJ.validar(formEntidade.getEncdocm())){
					erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF inválido."));					
				}
			}
		}
		if (formEntidade.getEnncgpp().equals("1")){
			if (!ValidaObjeto.validaString(formEntidade.getEndnasc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento é Obrigatório!"));
			}else{
				if (!ValidaObjeto.validaData(formEntidade.getEndnasc())){
					erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento inválida!"));
				}
			}
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEnlendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Endereço é obrigatório!"));
		}
		
		if (formEntidade.getEnncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Cidade é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEnccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CEP é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEncbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Bairro é obrigatório!"));
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/angariadorNovo.do");
			saveErrors(request, erros);
		}else{
			
			try {
				BeanEntidade beanEntidade = new BeanEntidade();
				BeanUtils.copyProperties(beanEntidade, formEntidade);
				ModelEntidade.getInstance().inserirAngariador(beanEntidade);
				request.setAttribute("msg",
						"Angariador cadastrado com sucesso.");
				request.getSession().removeAttribute("ls_estado");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("formEntidade");
				fw.setPath("/home.do");
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
	
		return fw;
	}
	
	
	public ActionForward cadastroCliente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0004";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade)form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome é obrigatório!"));
		}		
		if (!ValidaObjeto.validaString(formEntidade.getEncdocm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF é requerido!"));
		}
		if (!ValidaObjeto.validaString(formEntidade.getEndnasc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento é Obrigatório!"));
		}else{
			if (!ValidaObjeto.validaData(formEntidade.getEndnasc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento inválida!"));
			}
		}
		
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		
		if (usuario != null && ModelUsuarioGrupo.getInstance().ehUsuarioGrupo(Integer.parseInt(usuario.getEnncodg()), 2)){
		
			if (!ValidaObjeto.validaString(formEntidade.getEndexrg())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data do RG é Obrigatório!"));
			}else{
				if (!ValidaObjeto.validaData(formEntidade.getEndexrg())){
					erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data do RG inválida!"));
				}
			}
			
			if (!ValidaObjeto.validaString(formEntidade.getEncrg())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","RG é requerido!"));
			}
			
			if (!ValidaObjeto.validaString(formEntidade.getEncoerg())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Orgão Expedidor do RG é obrigatório!"));
			}
			
			if (!ValidaObjeto.validaString(formEntidade.getEncdorg())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Documento de Origem do RG é obrigatório!"));
			}
			
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEncmae())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome da mãe é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEnlendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Endereço é obrigatório!"));
		}
		
		if (formEntidade.getEnncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Cidade é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEnccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CEP é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEncbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Bairro é obrigatório!"));
		}
		
		if (!erros.isEmpty() || usuario == null){
			fw.setPath("/clienteNovo.do");
			saveErrors(request, erros);
		}else{
			
			try {
				BeanEntidade beanEntidade = new BeanEntidade();
				BeanUtils.copyProperties(beanEntidade, formEntidade);
				ModelEntidade.getInstance().inserirCliente(beanEntidade);
				request.setAttribute("msg", "Cliente cadastrado com sucesso.");
				request.getSession().removeAttribute("ls_estado");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("formEntidade");
				fw.setPath("/home.do");
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
	
		return fw;
	}
	
	public ActionForward updateCliente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0005";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade)form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome é obrigatório!"));
		}		
		if (!ValidaObjeto.validaString(formEntidade.getEncdocm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF é requerido!"));
		}
		if (!ValidaObjeto.validaString(formEntidade.getEndnasc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento é Obrigatório!"));
		}else{
			if (!ValidaObjeto.validaData(formEntidade.getEndnasc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento inválida!"));
			}
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEndexrg())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data do RG é Obrigatório!"));
		}else{
			if (!ValidaObjeto.validaData(formEntidade.getEndexrg())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data do RG inválida!"));
			}
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEncrg())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","RG é requerido!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEncoerg())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Orgão Expedidor do RG é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEncdorg())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Documento de Origem do RG é obrigatório!"));
		}
		
		
		if (!ValidaObjeto.validaString(formEntidade.getEncmae())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome da mãe é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEnlendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Endereço é obrigatório!"));
		}
		
		if (formEntidade.getEnncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Cidade é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEnccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CEP é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEncbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Bairro é obrigatório!"));
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/clienteEditar.do");
			saveErrors(request, erros);
		}else{
			
			try {
				BeanEntidade beanEntidade = new BeanEntidade();
				BeanUtils.copyProperties(beanEntidade, formEntidade);
				ModelEntidade.getInstance().updateCliente(beanEntidade);
				request.setAttribute("msg",
						"Dados do Cliente alterados com sucesso.");
				request.getSession().removeAttribute("ls_estado");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("formEntidade");
				fw.setPath("/home.do");
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
	
		return fw;
	}
	
	public ActionForward updateClienteJuridico(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0005";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade)form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome é obrigatório!"));
		}		
		if (!ValidaObjeto.validaString(formEntidade.getEncdocm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF é requerido!"));
		}
		if (!ValidaObjeto.validaString(formEntidade.getEndnasc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento é Obrigatório!"));
		}else{
			if (!ValidaObjeto.validaData(formEntidade.getEndnasc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento inválida!"));
			}
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEnlendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Endereço é obrigatório!"));
		}
		
		if (formEntidade.getEnncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Cidade é obrigatório!"));
		}		
		
		if (!erros.isEmpty()){
			fw.setPath("/clienteEditarJuridico.do");
			saveErrors(request, erros);
		}else{
			
			try {
				BeanEntidade beanEntidade = new BeanEntidade();
				BeanUtils.copyProperties(beanEntidade, formEntidade);
				ModelEntidade.getInstance().updateCliente(beanEntidade);
				
				request.getSession().removeAttribute("ls_estado");
				request.getSession().removeAttribute("ls_cidade");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("formEntidade");
				
				fw.setPath("/actionProcesso.do?m=opcoes&proncodg="+request.getParameter("proncodg"));
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		
		return fw;
	}
	
	public ActionForward alteraSenha(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0006";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formUsuario = (FormEntidade)form;
		
		String senha = formUsuario.getEncsenh();
		String senh2 = formUsuario.getEncsen2();
		String senh3 = formUsuario.getEncsen3();
		
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
		
		try {
			if (usuario == null) {
				fw.setPath("/login.do");
			} else {

				if (!ValidaObjeto.validaString(senha)) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Senha Atual é requerida."));
				}

				if (!ValidaObjeto.validaString(senh2)) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nova Senha é requerida."));
				}

				if (!ValidaObjeto.validaString(senh3)) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default",
							"Repetição de Nova Senha é requerida."));
				}
				
				if (erros.isEmpty())

					if (!senh2.equals(senh3)) {
						erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
								"errors.default",
								"Nova Senha e Repetição não conferem."));
					}else{
						if (senha.equals(senh2)) {
							erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
									"errors.default",
							"Nova Senha não pode ser igual a atual."));
						}
					}

				if (erros.isEmpty()) {

					if (ModelEntidade.getInstance().autenticaUsuario(
							usuario.getEnclogn().toUpperCase(), senha)) {
						ModelEntidade.getInstance().alteraSenha(
								Integer.parseInt(usuario.getEnncodg()), senh2);
						
						request.setAttribute("msg", "Sua Senha foi alterada com sucesso.");
						
						fw.setPath("/home.do");
						
						request.getSession().removeAttribute("bloqueiamenu");
						
						ModelAlteraSenha.getInstance().registrarAlteracao(Integer.parseInt(usuario.getEnncodg()));
						
					} else {
						erros.add(ActionMessages.GLOBAL_MESSAGE,
										new ActionMessage("errors.default",
										"A senha atual não corresponde."));
						
						saveErrors(request, erros);
						fw.setPath("/usuarioAlteraSenha.do");
					}
				}else{
					saveErrors(request, erros);
					fw.setPath("/usuarioAlteraSenha.do");
				}


			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formEntidade");
		
		return fw;
	}
	
	public ActionForward pesquisaDadosCliente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade) form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do cliente."));
		}else{
			if (formEntidade.getEncnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do cliente."));
			}
		}
		
		try {
			if (erros.isEmpty()) {
				List<BeanEntidade> listaEntidade = ModelEntidade.getInstance()
						.getEntidadePorNomeCpf(formEntidade.getEncnome(), 1);
				if (listaEntidade.isEmpty()) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nenhum cliente encontrado!"));
				} else {
					request.setAttribute("ls_entidade", listaEntidade);
				}
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formEntidade");
		
		fw.setPath("/clientePesquisaDados.do");
		saveErrors(request, erros);
		
		return fw;
	}
	
	
	public ActionForward opcoesAngariador(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0008";
		ActionForward fw = new ActionForward();
		try {
			String enncodg = request.getParameter("enncodg");
			
			BeanEntidade angariador = ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg));
			
			request.setAttribute("angariador", angariador);
			
			fw.setPath("/angariadorPage.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward pesquisaAngariadorOpcoes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0009";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade) form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do angariador."));
		}else{
			if (formEntidade.getEncnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do angariador."));
			}
		}
		
		try {
			if (erros.isEmpty()) {
				List<BeanEntidade> listaEntidade = ModelEntidade.getInstance()
						.getEntidadePorNomeCpf(formEntidade.getEncnome(), 2);
				if (listaEntidade.isEmpty()) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nenhum Angariador encontrado!"));
				} else {
					request.setAttribute("ls_entidade", listaEntidade);
				}
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formEntidade");
		
		fw.setPath("/angariadorPesquisaDados.do");
		saveErrors(request, erros);
		
		return fw;
	}
	
	
	public ActionForward pesquisaAngariadorContrato(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0010";
	
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade) form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do angariador."));
		}else{
			if (formEntidade.getEncnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do angariador."));
			}
		}
		
		try {
			if (erros.isEmpty()) {
				List<BeanEntidade> listaEntidade = ModelEntidade.getInstance()
						.getEntidadePorNomeCpf(formEntidade.getEncnome(), 2);
				if (listaEntidade.isEmpty()) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nenhum cliente encontrado!"));
				} else {
					request.setAttribute("ls_entidade", listaEntidade);
				}
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formEntidade");
		
		fw.setPath("/angariadorPesquisaContrato.do");
		saveErrors(request, erros);
		
		return fw;
	}
	
	
	public ActionForward pesquisaFornecedorWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0010";
	
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade) form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do fornecedor."));
		}else{
			if (formEntidade.getEncnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do fornecedor."));
			}
		}
		
		try {
			if (erros.isEmpty()) {
				List<BeanEntidade> listaEntidade = ModelEntidade.getInstance()
						.getEntidadePorNomeCpf(formEntidade.getEncnome(), 7);
				if (listaEntidade.isEmpty()) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nenhum fornecedor encontrado!"));
				} else {
					request.setAttribute("ls_entidade", listaEntidade);
				}
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formEntidade");
		
		fw.setPath("/fornecedorPesquisaCaixaWindow.do");
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward pesquisaAngariadorContratoWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0010";
	
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade) form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do angariador."));
		}else{
			if (formEntidade.getEncnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do angariador."));
			}
		}
		
		try {
			if (erros.isEmpty()) {
				List<BeanEntidade> listaEntidade = ModelEntidade.getInstance()
						.getEntidadePorNomeCpf(formEntidade.getEncnome(), 2);
				if (listaEntidade.isEmpty()) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nenhum angariador encontrado!"));
				} else {
					request.setAttribute("ls_entidade", listaEntidade);
				}
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formEntidade");
		
		fw.setPath("/angariadorPesquisaWindow.do");
		saveErrors(request, erros);
		
		return fw;
	}
	public ActionForward pesquisaFuncionarioAgenciaWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0010";
	
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade) form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do funcionário."));
		}else{
			if (formEntidade.getEncnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do funcionário."));
			}
		}
		
		try {
			if (erros.isEmpty()) {
				List<BeanEntidade> listaEntidade = ModelEntidade.getInstance()
						.getEntidadePorNomeCpf(formEntidade.getEncnome(), 3);
				if (listaEntidade.isEmpty()) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nenhum funcionário encontrado!"));
				} else {
					request.setAttribute("ls_entidade", listaEntidade);
				}
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formEntidade");
		
		fw.setPath("/funcionarioAgenciaPesquisaWindow.do");
		saveErrors(request, erros);
		
		return fw;
	}
	
	
	public ActionForward pesquisaAngariadorLigacao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0011";
	
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade) form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do angariador."));
		}else{
			if (formEntidade.getEncnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do angariador."));
			}
		}
		
		try {
			if (erros.isEmpty()) {
				List<BeanEntidade> listaEntidade = ModelEntidade.getInstance()
						.getEntidadePorNomeCpf(formEntidade.getEncnome(), 2);
				if (listaEntidade.isEmpty()) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nenhum angariador encontrado!"));
				} else {
					request.setAttribute("ls_entidade", listaEntidade);
				}
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formEntidade");
		
		fw.setPath("/angariadorPesquisaLigacao.do");
		saveErrors(request, erros);
		
		return fw;
	}
		
	public ActionForward editarCliente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0012";
	
		ActionForward fw = new ActionForward();
		FormEntidade formEntidade = (FormEntidade)form;
		
		try {
			String enncodg = request.getParameter("enncodg");
			BeanEntidade entidade = ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg),1);
			BeanUtils.copyProperties(formEntidade, entidade);
			
			if (entidade.getEncufcd() != null){
				request.getSession().setAttribute("ls_cidade",ModelCidade.getInstance().getCidadesDoEstado(entidade.getEncufcd()));
			}else{
				request.getSession().setAttribute("ls_cidade",ModelCidade.getInstance().getCidadesDoEstado("AC"));
			}
			
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			
			fw.setPath("/clienteEditar.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;
	}
	
	public ActionForward editarClienteJuridico(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0012";
	
		ActionForward fw = new ActionForward();
		FormEntidade formEntidade = (FormEntidade)form;
		
		try {
			String enncodg = request.getParameter("enncodg");
			
			BeanEntidade entidade = ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg),1);
			BeanUtils.copyProperties(formEntidade, entidade);
			
			if (entidade.getEncufcd() != null){
				request.getSession().setAttribute("ls_cidade",ModelCidade.getInstance().getCidadesDoEstado(entidade.getEncufcd()));
			}else{
				request.getSession().setAttribute("ls_cidade",ModelCidade.getInstance().getCidadesDoEstado("AC"));
			}
			
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			
			fw.setPath("/clienteEditarJuridico.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;
	}
	
	public ActionForward editarAngariador(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0013";
	
		ActionForward fw = new ActionForward();
		FormEntidade formEntidade = (FormEntidade)form;
		
		try {
			String enncodg = request.getParameter("enncodg");
			BeanEntidade entidade = ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg));
			BeanUtils.copyProperties(formEntidade, entidade);
			
			if (entidade.getEncufcd() != null){
				request.getSession().setAttribute("ls_cidade",ModelCidade.getInstance().getCidadesDoEstado(entidade.getEncufcd()));
			}else{
				request.getSession().setAttribute("ls_cidade",ModelCidade.getInstance().getCidadesDoEstado("AC"));
			}
			
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			
			fw.setPath("/angariadorEditar.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;
	}
	
	public ActionForward dadosCliente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0014";
		ActionForward fw = new ActionForward();
		
		try {
			String enncodg = request.getParameter("enncodg");
			request.setAttribute("cliente", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg),1));
			fw.setPath("/clienteDados.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;
	}
	
	public ActionForward dadosAngariador(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0015";
		ActionForward fw = new ActionForward();
		
		try {
			String enncodg = request.getParameter("enncodg");
			request.setAttribute("angariador", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			
			fw.setPath("/angariadorDados.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;
	}
	
	public ActionForward updateAngariador(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0016";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade)form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome é obrigatório!"));
		}		
		if (!ValidaObjeto.validaString(formEntidade.getEncdocm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF é requerido!"));
		}
		if (!ValidaObjeto.validaString(formEntidade.getEndnasc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento é Obrigatório!"));
		}else{
			if (!ValidaObjeto.validaData(formEntidade.getEndnasc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento inválida!"));
			}
		}
		
		
		if (!ValidaObjeto.validaString(formEntidade.getEnlendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Endereço é obrigatório!"));
		}
		
		if (formEntidade.getEnncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Cidade é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEnccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CEP é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEncbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Bairro é obrigatório!"));
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/angariadorEditar.do");
			saveErrors(request, erros);
		}else{
			
			try {
				BeanEntidade beanEntidade = new BeanEntidade();
				BeanUtils.copyProperties(beanEntidade, formEntidade);
				ModelEntidade.getInstance().updateCliente(beanEntidade);
				request.getSession().removeAttribute("ls_estado");
				request.getSession().removeAttribute("ls_tipologradouro");
				
				fw.setPath("/actionEntidade.do?m=opcoesAngariador&enncodg="+formEntidade.getEnncodg());
				request.getSession().removeAttribute("formEntidade");
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
	
		return fw;
	}

	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		fw.setPath("/login.do");
		
		request.getSession().invalidate();
		
		return fw;
	}
	
	public ActionForward relatoriosAngariador(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0017";
		
		ActionMessages erros = new ActionMessages();
		ActionForward fw = new ActionForward();
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		String enncodg = request.getParameter("enncodg");
		
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
			fw.setPath("/actionEntidade.do?m=opcoesAngariador&enncodg="+enncodg);
			saveErrors(request, erros);
		}else{
		
			try {
				
				Map<Object,Object> map = new HashMap<Object, Object>();
				
				map.put("DATA_INICIO", Utils.strBRToDate(data1));
				map.put("DATA_FIM", Utils.strBRToDate(data2));
				map.put("ENNCODG", new Integer(enncodg));
				
								
				switch (request.getParameter("tipo").charAt(0)) {
					case '1':
						map.put("REPORT_NAME", "comissao_angariador_periodo2");
						break;
					case '2':
						map.put("REPORT_NAME", "relat_producao_periodo2");
						break;
					case '3':
						map.put("REPORT_NAME", "relat_contrato_extorno2");
						break;
					case '4':
						map.put("REPORT_NAME", "grafico_producao_corretor_barra");
						break;
					default:
						map.put("REPORT_NAME", "comissao_angariador_periodo2");
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
	
	public ActionForward defineSenha(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0018";
		
		ActionForward fw = new ActionForward();
		FormEntidade formUsuario = (FormEntidade)form;
		
		try {
			
			String enncodg = request.getParameter("enncodg");
			
			BeanEntidade entid = ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg));
				
			BeanUtils.copyProperties(formUsuario, entid);
			
			fw.setPath("/entidadeDefineSenha.do");	
		
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
				
		return fw;
	}
	
	public ActionForward updateSenha(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0019";
		
		ActionForward fw = new ActionForward();
		FormEntidade formUsuario = (FormEntidade)form;
		
		try {
			
			ModelEntidade.getInstance().alteraSenha(
					Integer.parseInt(formUsuario.getEnncodg()), 
					formUsuario.getEncsenh());
			
			fw.setPath("/home.do");
		
			request.setAttribute("msg", "Definição de Senha feita com sucesso!");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formEntidade");
		
		return fw;
	}
	
	public ActionForward pesquisaAngariadorSenha(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0020";
	
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade) form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do angariador."));
		}else{
			if (formEntidade.getEncnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do angariador."));
			}
		}
		
		try {
			if (erros.isEmpty()) {
				List<BeanEntidade> listaEntidade = ModelEntidade.getInstance()
						.getEntidadePorNomeCpf(formEntidade.getEncnome(), 2);
				if (listaEntidade.isEmpty()) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nenhum angariador encontrado!"));
				} else {
					request.setAttribute("ls_entidade", listaEntidade);
				}
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formEntidade");
		
		fw.setPath("/angariadorPesquisaSenha.do");
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward editarUsuario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0021";
	
		ActionForward fw = new ActionForward();
		FormEntidade formEntidade = (FormEntidade)form;
		
		try {
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (usuario != null){
				BeanEntidade entidade = ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(usuario.getEnncodg()));
				BeanUtils.copyProperties(formEntidade, entidade);
				
				if (entidade.getEncufcd() != null){
					request.getSession().setAttribute("ls_cidade",ModelCidade.getInstance().getCidadesDoEstado(entidade.getEncufcd()));
				}else{
					request.getSession().setAttribute("ls_cidade",ModelCidade.getInstance().getCidadesDoEstado("AC"));
				}
				
				request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
				request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
				
				fw.setPath("/usuarioEditar.do");
			}else{
				fw.setPath("/home.do");				
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;
	}
	
	public ActionForward updateUsuario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0022";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade)form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome é obrigatório!"));
		}		
		if (!ValidaObjeto.validaString(formEntidade.getEncdocm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF é requerido!"));
		}else{
			if (!CPF.validar(ValidaObjeto.removeCharOfInteger(formEntidade.getEncdocm()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF informado é inválido!"));
			}
		}
		if (!ValidaObjeto.validaString(formEntidade.getEndnasc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento é Obrigatório!"));
		}else{
			if (!ValidaObjeto.validaData(formEntidade.getEndnasc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento inválida!"));
			}
		}
		
		
		if (!ValidaObjeto.validaEmail(formEntidade.getEncmail())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","E-mail inválido!"));
		}
		if (!ValidaObjeto.validaString(formEntidade.getEnlendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Endereço é obrigatório!"));
		}
		
		if (formEntidade.getEnncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Cidade é obrigatória!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEnccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CEP é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEncbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Bairro é obrigatório!"));
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/usuarioEditar.do");
			saveErrors(request, erros);
		}else{
			
			try {
				BeanEntidade beanEntidade = new BeanEntidade();
				BeanUtils.copyProperties(beanEntidade, formEntidade);
				ModelEntidade.getInstance().updateCliente(beanEntidade);
				request.setAttribute("msg",
						"Dados Cadastrais alterados com sucesso.");
				
				request.getSession().removeAttribute("ls_estado");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("formEntidade");
				
				fw.setPath("/actionLogin.do?m=liberaMenu");
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
	
		return fw;
	}
	
	public ActionForward novoFornecedor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0023";
		ActionForward fw = new ActionForward();
		
		try{			
			request.getSession().removeAttribute("formEntidade");
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			
			fw.setPath("/fornecedorNovo.do");
						
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward cadastroFornecedor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0024";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade)form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome é obrigatório!"));
		}		
		if (!ValidaObjeto.validaString(formEntidade.getEncdocm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF é requerido!"));
		}else{
			if (!CPF.validar(formEntidade.getEncdocm()) && !CNPJ.validar(formEntidade.getEncdocm())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF/CNPF é inválido!"));
			}
		}
		if (!ValidaObjeto.validaString(formEntidade.getEndnasc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento é Obrigatório!"));
		}else{
			if (!ValidaObjeto.validaData(formEntidade.getEndnasc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento inválida!"));
			}
		}		
		
		if (!ValidaObjeto.validaString(formEntidade.getEnlendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Endereço é obrigatório!"));
		}
		
		if (formEntidade.getEnncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Cidade é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEnccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CEP é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEncbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Bairro é obrigatório!"));
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/fornecedorNovo.do");
			saveErrors(request, erros);
		}else{
			
			try {
				BeanEntidade beanEntidade = new BeanEntidade();
				BeanUtils.copyProperties(beanEntidade, formEntidade);
				ModelEntidade.getInstance().inserirFornecedor(beanEntidade);
				
				request.getSession().removeAttribute("ls_estado");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("formEntidade");
				
				BeanEntidade f = ModelEntidade.getInstance().getBeanEntidadePorCPF(formEntidade.getEncdocm(), 7);
				
				fw.setPath("/actionEntidade.do?m=opcoesFornecedor&enncodg="+f.getEnncodg());
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
	
		return fw;
	}
	
	public ActionForward opcoesFornecedor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0025";
		ActionForward fw = new ActionForward();
		try {
			String enncodg = request.getParameter("enncodg");
			
			BeanEntidade fornecedor = ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg));
			
			request.setAttribute("fornecedor", fornecedor);
			
			fw.setPath("/fornecedorPage.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward dadosFornecedor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0026";
		ActionForward fw = new ActionForward();		
		try {
		
			String enncodg = request.getParameter("enncodg");
			request.setAttribute("fornecedor", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			fw.setPath("/fornecedorDados.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward editarFornecedor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0027";
	
		ActionForward fw = new ActionForward();
		FormEntidade formEntidade = (FormEntidade)form;
		
		try {
			String enncodg = request.getParameter("enncodg");
			BeanEntidade entidade = ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg));
			BeanUtils.copyProperties(formEntidade, entidade);
			
			if (entidade.getEncufcd() != null){
				request.getSession().setAttribute("ls_cidade",ModelCidade.getInstance().getCidadesDoEstado(entidade.getEncufcd()));
			}else{
				request.getSession().setAttribute("ls_cidade",ModelCidade.getInstance().getCidadesDoEstado("AC"));
			}
			
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			
			fw.setPath("/fornecedorEditar.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward listaFornecedor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0027";
	
		ActionForward fw = new ActionForward();
		
		try {
			
			request.setAttribute("ls_entidade", ModelEntidade.getInstance().getEntidadePorNomeCpf("", 7));
			
			fw.setPath("/fornecedorLista.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward updateFornecedor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0028";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade)form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome é obrigatório!"));
		}		
		if (!ValidaObjeto.validaString(formEntidade.getEncdocm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF é requerido!"));
		}else{
			if (!CPF.validar(formEntidade.getEncdocm()) && !CNPJ.validar(formEntidade.getEncdocm())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF/CNPF é inválido!"));
			}
		}
		if (!ValidaObjeto.validaString(formEntidade.getEndnasc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento é Obrigatório!"));
		}else{
			if (!ValidaObjeto.validaData(formEntidade.getEndnasc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento inválida!"));
			}
		}		
		
		if (!ValidaObjeto.validaString(formEntidade.getEnlendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Endereço é obrigatório!"));
		}
		
		if (formEntidade.getEnncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Cidade é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEnccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CEP é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEncbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Bairro é obrigatório!"));
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/fornecedorEditar.do");
			saveErrors(request, erros);
		}else{
			
			try {
				BeanEntidade beanEntidade = new BeanEntidade();
				BeanUtils.copyProperties(beanEntidade, formEntidade);
				
				ModelEntidade.getInstance().updateFornecedor(beanEntidade);
				
				request.getSession().removeAttribute("ls_estado");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("formEntidade");
				
				fw.setPath("/actionEntidade.do?m=dadosFornecedor&enncodg="+formEntidade.getEnncodg());
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
	
		return fw;
	}

	public ActionForward pesquisaFornecedorOpcoes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0029";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade) form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do fornecedor."));
		}else{
			if (formEntidade.getEncnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do fornecedor."));
			}
		}
		
		try {
			if (erros.isEmpty()) {
				List<BeanEntidade> listaEntidade = ModelEntidade.getInstance()
						.getEntidadePorNomeCpf(formEntidade.getEncnome(), 7);
				if (listaEntidade.isEmpty()) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nenhum fornecedor encontrado!"));
				} else {
					request.setAttribute("ls_entidade", listaEntidade);
				}
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formEntidade");
		
		fw.setPath("/fornecedorPesquisa.do");
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward novoFuncionario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0030";
		ActionForward fw = new ActionForward();
		try{
			request.getSession().removeAttribute("formEntidade");
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			fw.setPath("/funcionarioNovo.do");
						
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}

	public ActionForward cadastroFuncionario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0031";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade)form;
		formEntidade.validarFuncionario(erros);
		
		if (ModelEntidade.getInstance().getBeanEntidadePorCPF(ValidaObjeto.removeCharOfInteger(formEntidade.getEncdocm()),3 ) != null){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","CPF já cadastrado para funcionário."));
		}
		
		if (!erros.isEmpty()){
			if(!formEntidade.getEnncgcd().equals("-1")){
				request.setAttribute("ls_cidade", ModelCidade.getInstance().getCidadesDoEstado(formEntidade.getEncufcd()));
			}
			fw.setPath("/funcionarioNovo.do");
			saveErrors(request, erros);
		}else{
			try {
				
				BeanEntidade beanEntidade = new BeanEntidade();
				BeanUtils.copyProperties(beanEntidade, formEntidade);
				ModelEntidade.getInstance().inserirFuncionario(beanEntidade);
				request.setAttribute("msg", "Funcionário cadastrado com sucesso.");
				request.getSession().removeAttribute("ls_estado");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("formEntidade");
				fw.setPath("/actionEmpregado.do?m=lista");
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
	
		return fw;
	}
	
	public ActionForward editarFuncionario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0032";
	
		ActionForward fw = new ActionForward();
		FormEntidade formEntidade = (FormEntidade)form;
		
		try {
			String enncodg = request.getParameter("enncodg");
			BeanEntidade entidade = ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg));
			BeanUtils.copyProperties(formEntidade, entidade);
			
			if (formEntidade.getEncufcd() != null && !formEntidade.getEnncgcd().equals("-1")){
				request.setAttribute("ls_cidade", ModelCidade.getInstance().getCidadesDoEstado(formEntidade.getEncufcd()));
			}			
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			
			fw.setPath("/funcionarioEditar.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;
	}
	
	public ActionForward updateFuncionario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0033";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade)form;
		formEntidade.validarFuncionario(erros);
		if (!erros.isEmpty()){
			if (formEntidade.getEncufcd() != null && !formEntidade.getEnncgcd().equals("-1")){
				request.setAttribute("ls_cidade", ModelCidade.getInstance().getCidadesDoEstado(formEntidade.getEncufcd()));
			}
			fw.setPath("/funcionarioEditar.do");
			saveErrors(request, erros);
		}else{
			
			try {
				BeanEntidade beanEntidade = new BeanEntidade();
				BeanUtils.copyProperties(beanEntidade, formEntidade);
				ModelEntidade.getInstance().updateFuncionario(beanEntidade);
				request.getSession().removeAttribute("ls_estado");
				request.getSession().removeAttribute("ls_tipologradouro");
				
				fw.setPath("/actionEntidade.do?m=opcoesFuncionario&enncodg="+formEntidade.getEnncodg());
				request.getSession().removeAttribute("formEntidade");
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
	
		return fw;
	}
	
	public ActionForward opcoesFuncionario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0034";
		ActionForward fw = new ActionForward();
		try {
			String enncodg = request.getParameter("enncodg");
			
			BeanEntidade funcionario = ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg));
			BeanEmpregado beanEmpregado = ModelEmpregado.getInstance().getBeanEmpregadoPorEnt(Integer.parseInt(enncodg));
			
			request.setAttribute("funcionario", funcionario);
			request.setAttribute("empreg", beanEmpregado);
			
			fw.setPath("/funcionarioPage.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	public ActionForward dadosFuncionario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0035";
		ActionForward fw = new ActionForward();
		
		try {
			String enncodg = request.getParameter("enncodg");
			
			BeanEntidade entidade = ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg),3);
			
			request.setAttribute("funcionario", entidade);
			
			fw.setPath("/funcionarioDados.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		
		return fw;
	}
	
	
	public ActionForward novoLojaVeiculo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0036";
		ActionForward fw = new ActionForward();
		
		try{			
			request.getSession().removeAttribute("formEntidade");
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			
			fw.setPath("/lojaVeiculoNovo.do");
						
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward cadastroLojaVeiculo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0037";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade)form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome é obrigatório!"));
		}		
		if (!ValidaObjeto.validaString(formEntidade.getEncdocm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CNPJ é requerido!"));
		}else{
			if (!CNPJ.validar(formEntidade.getEncdocm())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CNPJ é inválido!"));
			}
		}
				
		if (!ValidaObjeto.validaString(formEntidade.getEnlendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Endereço é obrigatório!"));
		}
		
		if (formEntidade.getEnncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Cidade é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEnccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CEP é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEncbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Bairro é obrigatório!"));
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/lojaVeiculoNovo.do");
			saveErrors(request, erros);
		}else{
			
			try {
				BeanEntidade beanEntidade = new BeanEntidade();
				BeanUtils.copyProperties(beanEntidade, formEntidade);
				ModelEntidade.getInstance().inserirLojaVeiculo(beanEntidade);
				
				request.getSession().removeAttribute("ls_estado");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("formEntidade");
				
				fw.setPath("/actionEntidade.do?m=listaLojaVeiculo");
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
	
		return fw;
	}
	
	public ActionForward dadosLojaVeiculo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0038";
		ActionForward fw = new ActionForward();		
		try {
		
			String enncodg = request.getParameter("enncodg");
			request.setAttribute("lojaveiculo", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			fw.setPath("/lojaVeiculoDados.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward editarLojaVeiculo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0039";
	
		ActionForward fw = new ActionForward();
		FormEntidade formEntidade = (FormEntidade)form;
		
		try {
			String enncodg = request.getParameter("enncodg");
			BeanEntidade entidade = ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg));
			BeanUtils.copyProperties(formEntidade, entidade);
			
			if (entidade.getEncufcd() != null){
				request.getSession().setAttribute("ls_cidade",ModelCidade.getInstance().getCidadesDoEstado(entidade.getEncufcd()));
			}else{
				request.getSession().setAttribute("ls_cidade",ModelCidade.getInstance().getCidadesDoEstado("AC"));
			}
			
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			
			fw.setPath("/lojaVeiculoEditar.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward listaLojaVeiculo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0040";
	
		ActionForward fw = new ActionForward();
		
		try {
			
			request.setAttribute("ls_entidade", ModelEntidade.getInstance().getEntidadePorNomeCpf("", 9));
			
			fw.setPath("/lojaVeiculoLista.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward updateLojaVeiculo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0041";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade)form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome é obrigatório!"));
		}		
		if (!ValidaObjeto.validaString(formEntidade.getEncdocm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CNPJ é requerido!"));
		}else{
			if (!CNPJ.validar(formEntidade.getEncdocm())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CNPF é inválido!"));
			}
		}
				
		if (!ValidaObjeto.validaString(formEntidade.getEnlendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Endereço é obrigatório!"));
		}
		
		if (formEntidade.getEnncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Cidade é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEnccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CEP é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEncbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Bairro é obrigatório!"));
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/lojaVeiculoEditar.do");
			saveErrors(request, erros);
		}else{
			
			try {
				BeanEntidade beanEntidade = new BeanEntidade();
				BeanUtils.copyProperties(beanEntidade, formEntidade);
				ModelEntidade.getInstance().updateLojaVeiculo(beanEntidade);
				
				request.getSession().removeAttribute("ls_estado");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("formEntidade");
				
				fw.setPath("/actionEntidade.do?m=dadosLojaVeiculo&enncodg="+beanEntidade.getEnncodg());
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
	
		return fw;
	}
	
	public ActionForward novoOperadorVeiculo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0042";
		ActionForward fw = new ActionForward();
		
		try{			
			request.getSession().removeAttribute("formEntidade");
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			
			fw.setPath("/operadorVeiculoNovo.do");
						
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward listaOperadorVeiculo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0043";
	
		ActionForward fw = new ActionForward();
		
		try {
			
			request.setAttribute("ls_entidade", ModelEntidade.getInstance().getEntidadePorNomeCpf("", 8));
			
			fw.setPath("/operadorVeiculoLista.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward cadastroOperadorVeiculo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0044";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade)form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome é obrigatório!"));
		}		
		if (!ValidaObjeto.validaString(formEntidade.getEncdocm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF é requerido!"));
		}else{
			if (!CPF.validar(ValidaObjeto.removeCharOfInteger(formEntidade.getEncdocm()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF é inválido!"));
			}
		}
				
		if (!ValidaObjeto.validaString(formEntidade.getEnlendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Endereço é obrigatório!"));
		}
		
		if (formEntidade.getEnncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Cidade é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEnccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CEP é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEncbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Bairro é obrigatório!"));
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/operadorVeiculoNovo.do");
			saveErrors(request, erros);
		}else{
			
			try {
				BeanEntidade beanEntidade = new BeanEntidade();
				BeanUtils.copyProperties(beanEntidade, formEntidade);
				ModelEntidade.getInstance().inserirOperadorVeiculo(beanEntidade);
				
				request.getSession().removeAttribute("ls_estado");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("formEntidade");
				
				fw.setPath("/actionEntidade.do?m=listaOperadorVeiculo");
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
	
		return fw;
	}
	
	public ActionForward dadosOperadorVeiculo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0045";
		ActionForward fw = new ActionForward();		
		try {
		
			String enncodg = request.getParameter("enncodg");
			request.setAttribute("operadorveiculo", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			fw.setPath("/operadorVeiculoDados.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward editarOperadorVeiculo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0046";
	
		ActionForward fw = new ActionForward();
		FormEntidade formEntidade = (FormEntidade)form;
		
		try {
			String enncodg = request.getParameter("enncodg");
			BeanEntidade entidade = ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg));
			BeanUtils.copyProperties(formEntidade, entidade);
			
			if (entidade.getEncufcd() != null){
				request.getSession().setAttribute("ls_cidade",ModelCidade.getInstance().getCidadesDoEstado(entidade.getEncufcd()));
			}else{
				request.getSession().setAttribute("ls_cidade",ModelCidade.getInstance().getCidadesDoEstado("AC"));
			}
			
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			
			fw.setPath("/operadorVeiculoEditar.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward updateOperadorVeiculo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0047";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade)form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome é obrigatório!"));
		}	
		if (!ValidaObjeto.validaString(formEntidade.getEncdocm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CNPJ é requerido!"));
		}else{
			if (!CPF.validar(ValidaObjeto.removeCharOfInteger(formEntidade.getEncdocm()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CNPF é inválido!"));
			}
		}
				
		if (!ValidaObjeto.validaString(formEntidade.getEnlendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Endereço é obrigatório!"));
		}
		
		if (formEntidade.getEnncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Cidade é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEnccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CEP é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEncbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Bairro é obrigatório!"));
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/operadorVeiculoEditar.do");
			saveErrors(request, erros);
		}else{
			
			try {
				BeanEntidade beanEntidade = new BeanEntidade();
				BeanUtils.copyProperties(beanEntidade, formEntidade);
				ModelEntidade.getInstance().updateLojaVeiculo(beanEntidade);
				
				request.getSession().removeAttribute("ls_estado");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("formEntidade");
				
				fw.setPath("/actionEntidade.do?m=dadosOperadorVeiculo&enncodg="+beanEntidade.getEnncodg());
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
	
		return fw;
	}
	
	public ActionForward pesquisaOperadorContratoWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0048";
	
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade) form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do operador."));
		}else{
			if (formEntidade.getEncnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do operador."));
			}
		}
		
		try {
			if (erros.isEmpty()) {
				List<BeanEntidade> listaEntidade = ModelEntidade.getInstance()
						.getEntidadePorNomeCpf(formEntidade.getEncnome(), 8);
				if (listaEntidade.isEmpty()) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nenhum operador encontrado!"));
				} else {
					request.setAttribute("ls_entidade", listaEntidade);
				}
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formEntidade");
		
		fw.setPath("/operadorContratoPesquisaWindow.do");
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward pesquisaLojaContratoWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0049";
	
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade) form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do loja."));
		}else{
			if (formEntidade.getEncnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do loja."));
			}
		}
		
		try {
			if (erros.isEmpty()) {
				List<BeanEntidade> listaEntidade = ModelEntidade.getInstance()
						.getEntidadePorNomeCpf(formEntidade.getEncnome(), 9);
				if (listaEntidade.isEmpty()) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nenhuma loja encontrado!"));
				} else {
					request.setAttribute("ls_entidade", listaEntidade);
				}
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formEntidade");
		
		fw.setPath("/lojaContratoPesquisaWindow.do");
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward dadosEntidadeWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0050";
		ActionForward fw = new ActionForward();		
		try {
		
			String enncodg = request.getParameter("enncodg");
			request.setAttribute("entidade", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			fw.setPath("/entidadeDadosWindow.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward pesquisaEntidadeWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0051";
	
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade) form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do fornecedor."));
		}else{
			if (formEntidade.getEncnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do fornecedor."));
			}
		}
		
		try {
			if (erros.isEmpty()) {
				List<BeanEntidade> listaEntidade = ModelEntidade.getInstance()
						.getEntidadePorNomeCpf(formEntidade.getEncnome());
				if (listaEntidade.isEmpty()) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nenhuma entidade encontrado!"));
				} else {
					request.setAttribute("ls_entidade", listaEntidade);
				}
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formEntidade");
		
		fw.setPath("/entidadePesquisaWindow.do");
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward novoContatoJuridico(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0052";
		ActionForward fw = new ActionForward();
		try{
			request.getSession().removeAttribute("formEntidade");
			
			fw.setPath("/contatoJuridicoNovo.do");
						
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward listaContatoJuridico(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0053";
	
		ActionForward fw = new ActionForward();
		
		try {
			
			request.setAttribute("ls_entidade", ModelEntidade.getInstance().getEntidadePorNomeCpf("", 10));
			
			fw.setPath("/contatoJuridicoLista.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward cadastroContatoJuridico(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0054";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade)form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome é obrigatório!"));
		}		
		if (!ValidaObjeto.validaString(formEntidade.getEncdocm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF é requerido!"));
		}
				
		/*
		if (!ValidaObjeto.validaString(formEntidade.getEnlendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Endereço é obrigatório!"));
		}
		
		if (formEntidade.getEnncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Cidade é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEnccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CEP é obrigatório!"));
		}
		
		if (!ValidaObjeto.validaString(formEntidade.getEncbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Bairro é obrigatório!"));
		}
		
		*/
		
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");		
		
		if (!erros.isEmpty() || usuario == null){
			fw.setPath("/contatoJuridicoNovo.do");
			saveErrors(request, erros);
		}else{
			
			try {
				
				BeanEntidade beanEntidade = new BeanEntidade();
				BeanUtils.copyProperties(beanEntidade, formEntidade);
				ModelEntidade.getInstance().inserirContatoJuridico(beanEntidade);
				
				fw = listaContatoJuridico(mapping, formEntidade, request, response);
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
	
		return fw;
	}

	public ActionForward updateContatoJuridico(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0055";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade)form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome é obrigatório!"));
		}		
		if (!ValidaObjeto.validaString(formEntidade.getEncdocm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF é requerido!"));
		}		
		
		
		BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");		
		
		if (!erros.isEmpty() || usuario == null){
			fw.setPath("/contatoJuridicoEditar.do");
			saveErrors(request, erros);
		}else{
			
			try {
				
				BeanEntidade beanEntidade = new BeanEntidade();
				BeanUtils.copyProperties(beanEntidade, formEntidade);
				ModelEntidade.getInstance().updateContatoJuridico(beanEntidade);
				
				fw.setPath("/actionEntidade.do?m=opcoesContatoJuridico&enncodg="+formEntidade.getEnncodg());
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		
		return fw;
	}
	
	public ActionForward editarContatoJuridico(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0056";
	
		ActionForward fw = new ActionForward();
		FormEntidade formEntidade = (FormEntidade)form;
		
		try {

			String enncodg = request.getParameter("enncodg");
			BeanEntidade entidade = ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg));
			BeanUtils.copyProperties(formEntidade, entidade);
			
			fw.setPath("/contatoJuridicoEditar.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}		
		return fw;
	}
	
	public ActionForward pesquisaContatoJuridicoWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0057";
	
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade) form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do contato."));
		}else{
			if (formEntidade.getEncnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do contato."));
			}
		}
		
		try {
			if (erros.isEmpty()) {
				List<BeanEntidade> listaEntidade = ModelEntidade.getInstance()
						.getEntidadePorNomeCpf(formEntidade.getEncnome(), 10);
				if (listaEntidade.isEmpty()) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nenhum contato encontrado!"));
				} else {
					request.setAttribute("ls_entidade", listaEntidade);
				}
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formEntidade");
		
		fw.setPath("/contatoJuridicoPesquisaWindow.do");
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward pesquisaFuncionarioWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0058";
	
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade) form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do funcionário."));
		}else{
			if (formEntidade.getEncnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do funcionário."));
			}
		}
		
		try {
			if (erros.isEmpty()) {
				List<BeanEntidade> listaEntidade = ModelEntidade.getInstance()
						.getEntidadePorNomeCpf(formEntidade.getEncnome(), 3);
				if (listaEntidade.isEmpty()) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nenhum funcionário encontrado!"));
				} else {
					request.setAttribute("ls_entidade", listaEntidade);
				}
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		request.getSession().removeAttribute("formEntidade");
		
		fw.setPath("/funcionarioPesquisaWindow.do");
		saveErrors(request, erros);
		
		return fw;
	}
	
	public ActionForward opcoesContatoJuridico(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String METODO = "0059";
		ActionForward fw = new ActionForward();
		try {
			String enncodg = request.getParameter("enncodg");
			
			BeanEntidade fornecedor = ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg));
			
			request.setAttribute("contato", fornecedor);
			
			fw.setPath("/contatoJuridicoPage.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward pesquisaContatoJuridico(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0060";
	
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormEntidade formEntidade = (FormEntidade) form;
		
		if (!ValidaObjeto.validaString(formEntidade.getEncnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do contato."));
		}else{
			if (formEntidade.getEncnome().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe o nome ou parte do nome do contato."));
			}
		}
		
		try {
			if (erros.isEmpty()) {
				List<BeanEntidade> listaEntidade = ModelEntidade.getInstance()
						.getEntidadePorNomeCpf(formEntidade.getEncnome(), 10);
				if (listaEntidade.isEmpty()) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nenhum contato encontrado!"));
				} else {
					request.setAttribute("ls_entidade", listaEntidade);
				}
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formEntidade");
		
		fw.setPath("/contatoJuridicoPesquisa.do");
		saveErrors(request, erros);
		
		return fw;
	}
			
}
