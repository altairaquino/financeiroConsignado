package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class FormTabela extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String tabncodg;
	private String tabncgttb;
	private String tabnlimt;
	private String tabnpdes;
	private String tabnparc;
	private String tabnxxx;

	public String getTabncodg() {
		return this.tabncodg;
	}

	public void setTabncodg(String tabncodg) {
		this.tabncodg = tabncodg;
	}

	public String getTabncgttb() {
		return this.tabncgttb;
	}

	public void setTabncgttb(String tabncgttb) {
		this.tabncgttb = tabncgttb;
	}

	public String getTabnlimt() {
		return this.tabnlimt;
	}

	public void setTabnlimt(String tabnlimt) {
		this.tabnlimt = tabnlimt;
	}

	public String getTabnpdes() {
		return this.tabnpdes;
	}

	public void setTabnpdes(String tabnpdes) {
		this.tabnpdes = tabnpdes;
	}

	public String getTabnparc() {
		return this.tabnparc;
	}

	public void setTabnparc(String tabnparc) {
		this.tabnparc = tabnparc;
	}

	public String getTabnxxx() {
		return this.tabnxxx;
	}

	public void setTabnxxx(String tabnxxx) {
		this.tabnxxx = tabnxxx;
	}
	
	public void validarForm(ActionMessages erros){
		if(ValidaObjeto.isNullId(getTabnlimt())){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Limite"));
		}else
		if(!ValidaObjeto.validaFloat(Utils.converteFloatBR(getTabnlimt()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.invalid", "Limite"));
		}
		if(!ValidaObjeto.isNullId(getTabnpdes()) && !ValidaObjeto.validaFloat(Utils.converteFloatBR(getTabnpdes()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.invalid", "Percentual"));
		}
		if(!ValidaObjeto.isNullId(getTabnparc()) && !ValidaObjeto.validaFloat(Utils.converteFloatBR(getTabnparc()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.invalid", "Parcela"));
		}
		
	}
}
