package com.grupoexata.veiculo.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.veiculo.dao.ModelContratoAuto;
import com.grupoexata.veiculo.dao.ModelParcelaContratoAuto;
import com.grupoexata.veiculo.struts.bean.BeanParcelaContratoAuto;
import com.grupoexata.veiculo.struts.form.FormParcelaContratoAuto;

public class ActionParcelaContratoAuto extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACPCC:";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0001";
		ActionForward fw = new ActionForward();
		
		try {
			
			String ctancodg = request.getParameter("ctancodg");
			
			request.setAttribute("ls_parcelacontratoauto", ModelParcelaContratoAuto.getInstance().getParcelasContratoAuto(Integer.parseInt(ctancodg)));
			request.setAttribute("contratoauto", ModelContratoAuto.getInstance().getContratoAuto(Integer.parseInt(ctancodg)));
			
			fw.setPath("/parcelaContratoAutoLista.do");
			
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
			
			fw.setPath("/");
			
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
		
		try {			
			
			fw.setPath("/");
			
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
		final String METODO = "0006";
		ActionForward fw = new ActionForward();
		
		try {			
			
			String pccncodg = request.getParameter("pccncodg");
			BeanParcelaContratoAuto beanParcelaContratoAuto = ModelParcelaContratoAuto.getInstance().getParcelaContratoAuto(Integer.parseInt(pccncodg));
			
			request.getSession().setAttribute("parcelacontratoauto", beanParcelaContratoAuto);
			
			fw.setPath("/parcelaContratoAutoDados.do");
			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward baixar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String METODO = "0007";
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormParcelaContratoAuto formParcelaContratoAuto = (FormParcelaContratoAuto)form;
		try {			
			
			if (!ValidaObjeto.validaData(formParcelaContratoAuto.getPccdpgto())){
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default","Data do Pagamento está incorreta!"));
			}
			
			if (!erros.isEmpty()){
				saveErrors(request, erros);
				fw.setPath("/parcelaContratoAutoDados.do");
			}else{
				BeanParcelaContratoAuto beanParcelaContratoAuto = new BeanParcelaContratoAuto();
				BeanUtils.copyProperties(beanParcelaContratoAuto, formParcelaContratoAuto);
				
				ModelParcelaContratoAuto.getInstance().baixar(beanParcelaContratoAuto);
				
				request.getSession().removeAttribute("parcelacontratoauto");
				request.getSession().removeAttribute("formParcelaContratoAuto");
				
				fw.setPath("/actionParcelaContratoAuto.do?m=lista&ctancodg="+beanParcelaContratoAuto.getPccnctau());
			}
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
		
		return fw;
	}

}
