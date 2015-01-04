package com.grupoexata.folha.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.dao.ModelEmpresa;
import com.grupoexata.folha.bean.BeanAjudajcusto;
import com.grupoexata.folha.bean.BeanEmpregado;
import com.grupoexata.folha.bean.BeanTipoajcust;
import com.grupoexata.folha.dao.ModelAjudajcusto;
import com.grupoexata.folha.dao.ModelEmpregado;
import com.grupoexata.folha.dao.ModelTipoajcust;
import com.grupoexata.folha.struts.form.FormTipoajcust;

public class ActionAjudacusto extends DispatchAction {

	private final static String ERRO_CLASS = "ACAJC:";
	private ModelTipoajcust md = new ModelTipoajcust();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		try {
			
			List<BeanEmpregado> list1 = ModelEmpregado.getInstance().listaEntidadeEmpregadoAtivo();
			List<BeanTipoajcust> list2 = new ModelTipoajcust().list();
			List<BeanAjudajcusto> list3 = new ModelAjudajcusto().list();
			Map<String,BeanAjudajcusto> map = new HashMap<String,BeanAjudajcusto>();
			for (BeanAjudajcusto ajc : list3) {
				map.put("ajc_" +ajc.getAjcncgemp() + "_" + ajc.getAjcncgtac(), ajc);
			}
			request.setAttribute("listaempregado", list1);
			request.setAttribute("listatipoajcust", list2);
			request.setAttribute("mapajudacusto", map);
			fw.setPath("/ajudacustoLista.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}finally{
			//md.closeConnection();
		}
		return fw;
	}

	public ActionForward novo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0002";
		ActionForward fw = new ActionForward();

		try {
			request.getSession().removeAttribute("formTipoajcust");
			request.getSession().setAttribute("empresas", ModelEmpresa.getInstance().list());
			fw.setPath("/tipoajcustNovo.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}finally{
			//md.closeConnection();
		}
		return fw;
	}

	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0003";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();		
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw = lista(mapping, form, request, response);
		}else{
			try {
//				BeanTipoajcust Tipoajcust = new BeanTipoajcust();
//				BeanUtils.copyProperties(Tipoajcust,  formTipoajcust);
//				md.add(Tipoajcust);
				
				
				List<BeanEmpregado> list1 = new ModelEmpregado().listaEntidadeEmpregado();
				List<BeanTipoajcust> list2 = new ModelTipoajcust().list(); 
				List<BeanAjudajcusto> list3 = new ArrayList<BeanAjudajcusto>();
				for (BeanEmpregado empregado : list1) {
					for (BeanTipoajcust tipoajcust : list2) {
						String nome = "ajc_" + empregado.getEmpncodg() + "_" + tipoajcust.getTacncodg();
						String valNovo = request.getParameter(nome);
						if(valNovo != null && valNovo.equals("")){
							valNovo = null;
						}
						String valVelho = request.getParameter("v" + nome);
						boolean vn = valNovo != null;
						boolean vv = valVelho != null;  
						if((vn || vv) && !( vn && vv && valNovo.equals(valVelho))){
							BeanAjudajcusto ajudajcusto = new BeanAjudajcusto();
							ajudajcusto.setAjcncgemp(empregado.getEmpncodg());
							ajudajcusto.setAjcncgtac(tipoajcust.getTacncodg());
							ajudajcusto.setAjcncodg(request.getParameter("a" + nome));
							ajudajcusto.setAjcyvalor(valNovo);
							list3.add(ajudajcusto);
						}
					}
				}
				if(list3.size() > 0){
					new ModelAjudajcusto().addAjudajcustos(list3);
					request.setAttribute("msg", "Cadastro realizado com sucesso!");
				}
				fw = lista(mapping, form, request, response);
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

		try {
			FormTipoajcust formTipoajcust = (FormTipoajcust)form;
			BeanTipoajcust Tipoajcust = new BeanTipoajcust();
			Tipoajcust.setTacncodg(request.getParameter("tacncodg"));
			Tipoajcust = md.get(Tipoajcust);
			BeanUtils.copyProperties(formTipoajcust, Tipoajcust);
			fw.setPath("/tipoajcustEditar.do");
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}finally{
			//md.closeConnection();
		}
		return fw;
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0005";
		ActionForward fw = new ActionForward();

		ActionMessages erros = new ActionMessages();
		FormTipoajcust  formTipoajcust = (FormTipoajcust)form;
		//validar bean
		formTipoajcust.validarForm(erros);
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/tipoajcustEditar.do");
		}else{
			try {
				BeanTipoajcust Tipoajcust = new BeanTipoajcust();
				BeanUtils.copyProperties(Tipoajcust,  formTipoajcust);
				md.set(Tipoajcust);
				fw = lista(mapping, formTipoajcust, request, response);
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}finally{
				//md.closeConnection();
			}
		}
		return fw;
	}
}
