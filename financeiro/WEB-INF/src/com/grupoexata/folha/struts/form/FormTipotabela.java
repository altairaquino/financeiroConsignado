package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.grupoexata.bancario.utils.ValidaObjeto;

public class FormTipotabela extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String ttbncodg;
	private String ttbcdesc;
	private String ttbcsigl;

	public String getTtbncodg() {
		return this.ttbncodg;
	}

	public void setTtbncodg(String ttbncodg) {
		this.ttbncodg = ttbncodg;
	}

	public String getTtbcdesc() {
		return this.ttbcdesc;
	}

	public void setTtbcdesc(String ttbcdesc) {
		this.ttbcdesc = ttbcdesc;
	}

	public String getTtbcsigl() {
		return this.ttbcsigl;
	}

	public void setTtbcsigl(String ttbcsigl) {
		this.ttbcsigl = ttbcsigl;
	}
	
	public void validarForm(ActionMessages erros){
		if(ValidaObjeto.isNullId(getTtbcdesc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Descrição"));
		}
		if(ValidaObjeto.isNullId(getTtbcsigl())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Sigla"));
		}
	}
}
