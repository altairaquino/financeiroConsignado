package com.grupoexata.juridico.struts.action;

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

import com.grupoexata.bancario.dao.ModelCidade;
import com.grupoexata.bancario.dao.ModelEntidade;
import com.grupoexata.bancario.dao.ModelTipoLogradouro;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.utils.CPF;
import com.grupoexata.bancario.utils.GeraRelatorio;
import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.juridico.dao.ModelAndamentoProcesso;
import com.grupoexata.juridico.dao.ModelFaseProcesso;
import com.grupoexata.juridico.dao.ModelProcesso;
import com.grupoexata.juridico.dao.ModelTipoAcaoJudicial;
import com.grupoexata.juridico.struts.bean.BeanProcesso;
import com.grupoexata.juridico.struts.form.FormProcesso;

public class ActionProcesso extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACPRO:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			fw.setPath("/tabelaAngariadorLista.do");
			
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
		FormProcesso formProcesso = (FormProcesso)form;
		ActionMessages erros = new ActionMessages();
		try {
			
			BeanEntidade usuario = (BeanEntidade)request.getSession().getAttribute("usuario");
			
			if (!erros.isEmpty() || usuario == null){
				saveErrors(request, erros);
				fw.setPath("/processoEditar.do");
			}else{
				
				BeanProcesso beanProcesso = new BeanProcesso();
				BeanUtils.copyProperties(beanProcesso, formProcesso);	
				
				beanProcesso.setPronalte(usuario.getEnncodg());
				
				ModelProcesso.getInstance().update(beanProcesso);
				
				request.getSession().removeAttribute("ls_tipoacaojudicial");
				request.getSession().removeAttribute("formProcesso");
				
				fw.setPath("/actionProcesso.do?m=dados&proncodg="+formProcesso.getProncodg());
				
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
		FormProcesso formProcesso = (FormProcesso)form;
		try {
			
			String proncodg = request.getParameter("proncodg");
			
			BeanProcesso beanProcesso = ModelProcesso.getInstance().getProcesso(Integer.parseInt(proncodg));
			
			BeanUtils.copyProperties(formProcesso, beanProcesso);
			
			request.getSession().setAttribute("ls_tipoacaojudicial", ModelTipoAcaoJudicial.getInstance().getTiposAcaoJudicial());
			
			fw.setPath("/processoEditar.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}

	public ActionForward opcoes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0004";
		ActionForward fw = new ActionForward();
		
		try {
			String proncodg = request.getParameter("proncodg");
			
			BeanProcesso processo = ModelProcesso.getInstance().getProcesso(Integer.parseInt(proncodg));
			
			request.getSession().setAttribute("processo", processo);
			request.setAttribute("ls_andamentoprocesso", ModelAndamentoProcesso.getInstance().getAndamentoDoProcesso(Integer.parseInt(proncodg)));
			request.setAttribute("cliente", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(processo.getProncgen())));
			
			fw.setPath("/processoPage.do");
			
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
		final String METODO = "0005";
		ActionForward fw = new ActionForward();
		
		try {			
			
			request.getSession().removeAttribute("formProcesso");
			
			
			request.getSession().setAttribute("ls_tipoacaojudicial", ModelTipoAcaoJudicial.getInstance().getTiposAcaoJudicial());
			request.getSession().setAttribute("ls_faseprocesso", ModelFaseProcesso.getInstance().getFasesDoProcesso());
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			
			request.getSession().removeAttribute("formProcesso");
			
			fw.setPath("/processoNovo.do");
			
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
		final String METODO = "0006";
		ActionForward fw = new ActionForward();
		FormProcesso formProcesso = (FormProcesso)form;
		ActionMessages erros = new ActionMessages();
		try {		
			
			if (!ValidaObjeto.validaString(formProcesso.getProcnmcl())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nome é obrigatório!"));
			}	
			if (!ValidaObjeto.validaString(formProcesso.getProccpf())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF é requerido!"));
			}else{
				if (!CPF.validar(ValidaObjeto.removeCharOfInteger(formProcesso.getProccpf()))){
					erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CPF é inválido!"));
				}
			}
			if (!ValidaObjeto.validaString(formProcesso.getProdnasc())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento é Obrigatório!"));
			}else{
				if (!ValidaObjeto.validaData(formProcesso.getProdnasc())){
					erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Data de Nascimento inválida!"));
				}
			}
			
			if (!ValidaObjeto.validaString(formProcesso.getProclogr())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Endereço é obrigatório!"));
			}
			
			if (formProcesso.getProncgcd().equals("-1")){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Cidade é obrigatório!"));
			}
			/*
			if (!ValidaObjeto.validaFone(formProcesso.getProcfone())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Fone digitado esta incorreto!"));
			}
			
			if (!ValidaObjeto.validaString(formProcesso.getProccep())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","CEP é obrigatório!"));
			}
			
			if (!ValidaObjeto.validaString(formProcesso.getProcbair())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Bairro é obrigatório!"));
			}
			
			if (!ValidaObjeto.validaInteiro(formProcesso.getPronc2en())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Angariador é obrigatório!"));
			}

			if (!ValidaObjeto.validaInteiro(formProcesso.getProncons())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Consultor é obrigatório!"));
			}
			
			if (!ValidaObjeto.validaInteiro(formProcesso.getPronsupe())){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Supervisor é obrigatório!"));
			}
			
			if (!ValidaObjeto.validaString(formProcesso.getProcnumr())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Número do processo é requerido."));
			}
			
			if (ModelProcesso.getInstance().getProcessoPorNumero(formProcesso.getProcnumr()) != null){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Número do processo já cadastrado no sistema."));
			}
			*/
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/processoNovo.do");
			}else{
				
				BeanProcesso beanProcesso = new BeanProcesso();
				BeanUtils.copyProperties(beanProcesso, formProcesso);
				
				ModelProcesso.getInstance().insert(beanProcesso);
				
				
				request.getSession().removeAttribute("formProcesso");
				request.getSession().removeAttribute("ls_tipoacaojudicial");
				request.getSession().removeAttribute("ls_faseprocesso");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("ls_estado");
				
				if (ValidaObjeto.validaString(formProcesso.getProcnumr())){
					BeanProcesso processo = ModelProcesso.getInstance().getProcessoPorNumero(formProcesso.getProcnumr());
					fw.setPath("/actionProcesso.do?m=opcoes&proncodg="+processo.getProncodg());			
				}else{
					request.setAttribute("msg", "Processo Cadastrado com sucesso!");
					fw.setPath("/home.do");
				}
			}
			
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward dados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();
		
		try {
			
			String proncodg = request.getParameter("proncodg");
			
			request.setAttribute("processo", ModelProcesso.getInstance().getProcesso(Integer.parseInt(proncodg)));
			request.setAttribute("ls_andamentoprocesso", ModelAndamentoProcesso.getInstance().getAndamentoDoProcesso(Integer.parseInt(proncodg)));
			
			fw.setPath("/processoDados.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward pesquisa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0008";
	
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormProcesso formProcesso = (FormProcesso) form;
		
		if (!ValidaObjeto.validaString(formProcesso.getProcnumr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe um dos requisitos da pesquisa."));
		}else{
			if (formProcesso.getProcnumr().length() < 3){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Informe um dos requisitos da pesquisa."));
			}
		}
		
		try {
			if (erros.isEmpty()) {
				List<BeanProcesso> listaProcesso = ModelProcesso.getInstance()
						.pesquisa(formProcesso.getProcnumr().toUpperCase());
				if (listaProcesso.isEmpty()) {
					erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.default", "Nenhum processo encontrado!"));
				} else {
					request.setAttribute("ls_processo", listaProcesso);
				}
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		request.getSession().removeAttribute("formProcesso");
		
		fw.setPath("/processoPesquisa.do");
		saveErrors(request, erros);
		
		return fw;
	}	
	
	public ActionForward relatorios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0009";
		
		ActionMessages erros = new ActionMessages();
		ActionForward fw = new ActionForward();
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		
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
			fw.setPath("/relatoriosJuridico.do");
			saveErrors(request, erros);
		}else{
		
			try {
				
				Map<Object,Object> map = new HashMap<Object, Object>();
				
				map.put("DATA_INICIO", Utils.strBRToDate(data1));
				map.put("DATA_FIM", Utils.strBRToDate(data2));	
								
				switch (Integer.parseInt(request.getParameter("tipo"))) {
					case 1:
						map.put("REPORT_NAME", "juridico_agendamento_ligacoes");
						break;
					case 2:
						map.put("REPORT_NAME", "juridico_ligacoes_realizadas");
						break;
					case 3:
						map.put("REPORT_NAME", "juridico_processos_p_cidade");
						break;					
					case 4:
						map.put("REPORT_NAME", "juridico_processos_p_tipo");
						break;					
					case 5:
						map.put("REPORT_NAME", "juridico_audiencias_marcadas");
						break;					
					default:
						map.put("REPORT_NAME", "juridico_agendamento_ligacoes");
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
	public ActionForward documentos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String METODO = "0010";
		
		ActionForward fw = new ActionForward();		
			
		try {
			
			Map<Object,Object> map = new HashMap<Object, Object>();
			
			map.put("PRONCODG", new Integer(request.getParameter("proncodg")));
			
			switch (Integer.parseInt(request.getParameter("tipo"))) {
				case 1:
					map.put("REPORT_NAME", "juridico_oficio");
					break;
				case 2:
					map.put("REPORT_NAME", "juridico_etiq_cartao");
					break;
				case 3:
					map.put("REPORT_NAME", "juridico_endereco_envelope");
					break;
				default:
					map.put("REPORT_NAME", "juridico_oficio");
					break;
			}
			
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
