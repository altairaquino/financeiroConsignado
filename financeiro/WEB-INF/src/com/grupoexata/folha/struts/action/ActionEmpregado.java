package com.grupoexata.folha.struts.action;

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
import com.grupoexata.bancario.dao.ModelFilial;
import com.grupoexata.bancario.dao.ModelSetor;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.folha.bean.BeanEmpregado;
import com.grupoexata.folha.dao.ModelCategoriaEmpregado;
import com.grupoexata.folha.dao.ModelEmpregado;
import com.grupoexata.folha.dao.ModelGrauInstrucao;
import com.grupoexata.folha.dao.ModelSituacaoEmpregado;
import com.grupoexata.folha.dao.ModelTipoAdmissao;
import com.grupoexata.folha.dao.ModelVinculo;
import com.grupoexata.folha.struts.form.FormEmpregado;

public class ActionEmpregado extends DispatchAction {

	private final static String ERRO_CLASS = "ACEMP:";
	private ModelEmpregado md = new ModelEmpregado();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			request.setAttribute("listaempregado", ModelEntidade.getInstance().getEntidadePorNomeCpf("", 3));
			request.setAttribute("listaempregado_aniv", ModelEmpregado.getInstance().listaEntidadeEmpregadoAniversarioDia());
			
			fw.setPath("/empregadoLista.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}finally{
			
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
			
			request.getSession().removeAttribute("formEmpregado");
			
			request.getSession().setAttribute("funcionario", ModelEntidade.getInstance().getBeanEntidade(Integer.parseInt(enncodg)));
			
			request.getSession().setAttribute("listasituacao_empregado", new ModelSituacaoEmpregado().list());
			request.getSession().setAttribute("listacategoria_empregado", new ModelCategoriaEmpregado().list());
			request.getSession().setAttribute("listavinculo", new ModelVinculo().list());
			request.getSession().setAttribute("listatipo_admissao", new ModelTipoAdmissao().list());
			request.getSession().setAttribute("listagrau_instrucao", new ModelGrauInstrucao().list());
			request.getSession().setAttribute("ls_setor", ModelSetor.getInstance().getSetores());
			request.getSession().setAttribute("ls_filial", ModelFilial.getInstance().getFiliais());
			
			fw.setPath("/empregadoNovo.do");
			
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
		FormEmpregado  formEmpregado = (FormEmpregado)form;
		
		formEmpregado.validarForm(erros);
		
		if (!erros.isEmpty()){			
			saveErrors(request, erros);
			fw.setPath("/empregadoNovo.do");
		}else{
			try {
				BeanEmpregado empregado = new BeanEmpregado();
				BeanUtils.copyProperties(empregado,  formEmpregado);
				
				md.add(empregado);
				
				fw.setPath("/actionEntidade.do?m=opcoesFuncionario&enncodg=" + formEmpregado.getEmpncgen());
				
				request.getSession().removeAttribute("listasituacao_empregado");
				request.getSession().removeAttribute("listacategoria_empregado");
				request.getSession().removeAttribute("listavinculo");
				request.getSession().removeAttribute("listatipo_admissao");
				request.getSession().removeAttribute("listagrau_instrucao");
				request.getSession().removeAttribute("ls_setor");
				request.getSession().removeAttribute("ls_filial");
				
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}		
		return fw;
	}

	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0004";
		ActionForward fw = new ActionForward();
		FormEmpregado formEmpregado = (FormEmpregado)form;
		try {
			String empncodg = request.getParameter("empncodg");
			
			BeanEmpregado beanEmpregado = new ModelEmpregado().getBeanEmpregado(Integer.parseInt(empncodg));
			
			BeanUtils.copyProperties(formEmpregado, beanEmpregado);
			
			request.getSession().setAttribute("listasituacao_empregado", new ModelSituacaoEmpregado().list());
			request.getSession().setAttribute("listacategoria_empregado", new ModelCategoriaEmpregado().list());
			request.getSession().setAttribute("listavinculo", new ModelVinculo().list());
			request.getSession().setAttribute("listatipo_admissao", new ModelTipoAdmissao().list());
			request.getSession().setAttribute("listagrau_instrucao", new ModelGrauInstrucao().list());
			request.getSession().setAttribute("ls_setor", ModelSetor.getInstance().getSetores());
			request.getSession().setAttribute("ls_filial", ModelFilial.getInstance().getFiliais());
			
			fw.setPath("/empregadoEditar.do");
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
		FormEmpregado  formempregado = (FormEmpregado)form;
		
		formempregado.validarForm(erros);
		
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/empregadoEditar.do");
		}else{
			
			try {
				
				BeanEmpregado empregado = new BeanEmpregado();
				BeanUtils.copyProperties(empregado,  formempregado);
				md.set(empregado);
				fw.setPath("/actionEmpregado.do?m=dados&empncodg=" + empregado.getEmpncodg());
				
				request.getSession().removeAttribute("listasituacao_empregado");
				request.getSession().removeAttribute("listacategoria_empregado");
				request.getSession().removeAttribute("listavinculo");
				request.getSession().removeAttribute("listatipo_admissao");
				request.getSession().removeAttribute("listagrau_instrucao");
				request.getSession().removeAttribute("ls_setor");
				request.getSession().removeAttribute("ls_filial");
		
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}
		}
		return fw;
	}

	public ActionForward dados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0006";
		ActionForward fw = new ActionForward();

		try {
			String empncodg = request.getParameter("empncodg");
			
			BeanEmpregado empregado = ModelEmpregado.getInstance().getBeanEmpregado(Integer.parseInt(empncodg));
			
			request.setAttribute("empregado", empregado);
			
			fw.setPath("/empregadoDados.do");				
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS + METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}

	public ActionForward pesquisa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();

		try {
			ActionMessages messages = new ActionMessages();
			String param = request.getParameter("param");
			if (param == null || param.length() < 4) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.detail", "Min. de 4 caracteres."));
			} else {
				List<BeanEntidade> list = ModelEntidade.getInstance().getEntidadePorNomeCpf(param, 3);
				request.setAttribute("listaPesqempregado", list); 
			}
			request.setAttribute("param", param);
			fw.setPath("/empregadoPesquisa.do");
			if (!messages.isEmpty()) {
				saveErrors(request, messages);
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}


}
