package com.grupoexata.folha.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.grupoexata.bancario.utils.ValidaObjeto;

public class FormFiltro  extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4147070599157815249L;
	private String filncodg;
	private String filcdesc;
	
	
	public String getFilncodg() {
		return filncodg;
	}


	public void setFilncodg(String filncodg) {
		this.filncodg = filncodg;
	}


	public String getFilcdesc() {
		return filcdesc;
	}


	public void setFilcdesc(String filcdesc) {
		this.filcdesc = filcdesc;
	}


	public void validarForm(ActionMessages erros) {
		if(ValidaObjeto.isNullId(filcdesc)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Nome"));
		}
	} 
}
