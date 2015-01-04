package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class FormDescRend extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String derncodg;
	private String derccodg;
	private String dercdesc;
	private String derctipo;
	private String derlliqd;
	private String dernmult;
	private String dercform;

	public String getDerncodg() {
		return this.derncodg;
	}

	public void setDerncodg(String derncodg) {
		this.derncodg = derncodg;
	}

	public String getDerccodg() {
		return this.derccodg;
	}

	public void setDerccodg(String derccodg) {
		this.derccodg = derccodg;
	}

	public String getDercdesc() {
		return this.dercdesc;
	}

	public void setDercdesc(String dercdesc) {
		this.dercdesc = dercdesc;
	}

	public String getDerctipo() {
		return this.derctipo;
	}

	public void setDerctipo(String derctipo) {
		this.derctipo = derctipo;
	}

	public String getDerlliqd() {
		return this.derlliqd;
	}

	public void setDerlliqd(String derlliqd) {
		this.derlliqd = derlliqd;
	}

	public String getDernmult() {
		return this.dernmult;
	}

	public void setDernmult(String dernmult) {
		this.dernmult = dernmult;
	}

	public String getDercform() {
		return this.dercform;
	}

	public void setDercform(String dercform) {
		this.dercform = dercform;
	}
	
	public void validarForm(ActionMessages erros){
		if(ValidaObjeto.isNullId(getDerccodg())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Código"));
		}
		if(ValidaObjeto.isNullId(getDercdesc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Descrição"));
		}
		if(ValidaObjeto.isNullId(getDerctipo())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Tipo"));
		}
		if(!ValidaObjeto.isNullId(getDernmult()) && !ValidaObjeto.validaFloat(Utils.converteFloatBR(getDernmult()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.invalid", "multiplicando"));
		}
		if(ValidaObjeto.isNullId(getDerlliqd())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Afeta líquido"));
		}
		
	}
}
