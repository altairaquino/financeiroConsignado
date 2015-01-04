package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.grupoexata.bancario.utils.ValidaObjeto;

public class FormDependente extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String depncodg;
	private String depncgen;
	private String depcnome;
	private String depdnasc;

	public String getDepncodg() {
		return this.depncodg;
	}

	public void setDepncodg(String depncodg) {
		this.depncodg = depncodg;
	}

	public String getDepncgen() {
		return this.depncgen;
	}

	public void setDepncgen(String depncgen) {
		this.depncgen = depncgen;
	}

	public String getDepcnome() {
		return this.depcnome;
	}

	public void setDepcnome(String depcnome) {
		this.depcnome = depcnome;
	}

	public String getDepdnasc() {
		return this.depdnasc;
	}

	public void setDepdnasc(String depdnasc) {
		this.depdnasc = depdnasc;
	}
	public void validarForm(ActionMessages erros){
		if(ValidaObjeto.isNullId(getDepcnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Nome"));
		}
		if(ValidaObjeto.isNullId(getDepdnasc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Data de nascimento"));
		}else if(!ValidaObjeto.validaData(getDepdnasc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.date","e", "Data de nascimento"));
		}
	}
}
