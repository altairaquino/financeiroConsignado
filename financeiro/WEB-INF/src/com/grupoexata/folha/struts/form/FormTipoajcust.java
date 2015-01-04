package com.grupoexata.folha.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.grupoexata.bancario.utils.ValidaObjeto;

public class FormTipoajcust  extends ActionForm{
	
	private static final long serialVersionUID = -6061371834485064553L;
	private String tacncodg;
	private String taccdesc;
	public String getTacncodg() {
		return tacncodg;
	}
	public void setTacncodg(String tacncodg) {
		this.tacncodg = tacncodg;
	}
	public String getTaccdesc() {
		return taccdesc;
	}
	public void setTaccdesc(String taccdesc) {
		this.taccdesc = taccdesc;
	}
	public void validarForm(ActionMessages erros) {
		if(ValidaObjeto.isNullId(taccdesc)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Nome"));
		}
	} 
}
