package com.grupoexata.bancario.struts.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.grupoexata.bancario.struts.form.FormContrato;
import com.grupoexata.bancario.utils.ImportarGcom;
import com.grupoexata.bancario.utils.ManipulaArquivo;

public class ActionLayout extends DispatchAction {
	
	private final static String ERRO_CLASS = "ACLAY:";
	
	public ActionForward uploadArquivo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		final String METODO = "0001";
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormContrato formContrato = (FormContrato)form;
		try {
			String diretorio = getServlet().getServletConfig().getServletContext().getRealPath("/retornos/");
			FormFile myFile = formContrato.getArquivo();
			ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
			int ret = manipulaArquivo.gravar(myFile, diretorio);
			if(ret != 0){
				String s = "";
				switch (ret) {
				case ManipulaArquivo.ERROR_CRIAR_DIR:
					s = "Erro ao criar diretorio.";
					break;
				case ManipulaArquivo.ERROR_SIZE_EXCEDIDO:
					s = "Arquivo excedeu o tamanho limite."; 
					break;
				case ManipulaArquivo.ERROR_CRIAR_ARQ:
					s = "Erro ao criar arquivo";
					break;
				}
				erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.default",s));
				saveErrors(request, erros);
			}else{
				File file = new File(diretorio + File.separator + myFile.getFileName());
				ImportarGcom.importar(file);
				request.setAttribute("msg", "Arquivo impotado com sucesso!");
			}
			fw.setPath("/layoutlistaArquivos.do");			
		} catch (Exception e) {
			request.setAttribute("erro", ERRO_CLASS+METODO);
			fw.setPath("/erro.do");
			e.printStackTrace();
		}
	
		return fw;
	}
	
}
