package com.grupoexata.folha.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.folha.bean.BeanPagtoajcust;
import com.grupoexata.folha.bean.BeanPagtoajcustemp;
import com.grupoexata.folha.dao.ModelPagtoajcustemp;
import com.grupoexata.folha.struts.form.FormPagtoajcustemp;

public class ActionPagtoajcustemp extends DispatchAction {

	private final static String ERRO_CLASS = "ACTAC:";
	private ModelPagtoajcustemp md = new ModelPagtoajcustemp();

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();

		try {
			BeanPagtoajcust pagtoajcus = (BeanPagtoajcust)request.getSession().getAttribute("pagtoajcust");
			request.setAttribute("listaPagtoajcustemp", md.list(pagtoajcus.getPacncodg()));
			fw.setPath("/pagtoajcustempLista.do");
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
			request.getSession().removeAttribute("formPagtoajcustemp");
			fw.setPath("/pagtoajcustempNovo.do");
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
		FormPagtoajcustemp  formPagtoajcustemp = (FormPagtoajcustemp)form;
		//validar bean
		formPagtoajcustemp.validarForm(erros);
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/pagtoajcustempNovo.do");
		}else{
			try {
				BeanPagtoajcustemp Pagtoajcustemp = new BeanPagtoajcustemp();
				BeanUtils.copyProperties(Pagtoajcustemp,  formPagtoajcustemp);
				md.add(Pagtoajcustemp);
				fw = lista(mapping, form, request, response);
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

	public ActionForward cadastroMassa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			final String METODO = "0005";
			ActionForward fw = new ActionForward();
			try {
				BeanPagtoajcust pagtoajcus = (BeanPagtoajcust)request.getSession().getAttribute("pagtoajcust");
				List<BeanPagtoajcustemp> list = md.list(pagtoajcus.getPacncodg());
				List<BeanPagtoajcustemp> list_add = new ArrayList<BeanPagtoajcustemp>();
				for (BeanPagtoajcustemp pce : list) {
					String nome ="cod_" + pce.getPcencodg();
					String acres = request.getParameter("acres_" + nome);
					if(acres == null || acres.equals(""))acres = "0,00";					
					String valor = request.getParameter("valor_" + nome);
					if(valor == null || valor.equals(""))valor = "0,00";					
					String desc = request.getParameter("desc_" + nome);
					if(desc == null || desc.equals(""))desc = "0,00";
					String bloq = request.getParameter("bloq_" + nome);
					if(bloq == null || bloq.equals(""))bloq = "F";
					if(
							!pce.getPceyvalor().equals(valor)
							||!pce.getPceyacres().equals(acres) 
							||!pce.getPceydesc().equals(desc) 
							||!pce.getPcelbloq().equals(bloq)
							){
						pce.setPceyvalor(valor);
						pce.setPceyacres(acres);
						pce.setPceydesc(desc);
						pce.setPcelbloq(bloq);
						list_add.add(pce);
					}
				}
				if(!list_add.isEmpty()){
					md.set(list_add);
					request.setAttribute("msg", "Cadastro realizado com sucesso!");
				}
				fw = lista(mapping, form, request, response);
			} catch (Exception e) {
				request.setAttribute("erro", ERRO_CLASS+METODO);
				fw.setPath("/erro.do");
				e.printStackTrace();
			}finally{
				//md.closeConnection();
			}
		return fw;
	}
	public ActionForward editar(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		final String METODO = "0004";
		ActionForward fw = new ActionForward();

		try {
			FormPagtoajcustemp formPagtoajcustemp = (FormPagtoajcustemp)form;
			BeanPagtoajcustemp Pagtoajcustemp = new BeanPagtoajcustemp();
			Pagtoajcustemp.setPcencodg(request.getParameter("pcencodg"));
			Pagtoajcustemp = md.get(Pagtoajcustemp);
			BeanUtils.copyProperties(formPagtoajcustemp, Pagtoajcustemp);
			fw.setPath("/pagtoajcustempEditar.do");
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
		FormPagtoajcustemp  formPagtoajcustemp = (FormPagtoajcustemp)form;
		//validar bean
		formPagtoajcustemp.validarForm(erros);
		if (!erros.isEmpty()){
			saveErrors(request, erros);
			fw.setPath("/pagtoajcustempEditar.do");
		}else{
			try {
				BeanPagtoajcustemp Pagtoajcustemp = new BeanPagtoajcustemp();
				BeanUtils.copyProperties(Pagtoajcustemp,  formPagtoajcustemp);
				md.set(Pagtoajcustemp);
				fw = lista(mapping, formPagtoajcustemp, request, response);
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
