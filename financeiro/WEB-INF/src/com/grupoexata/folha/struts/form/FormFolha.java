package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.grupoexata.bancario.utils.ValidaObjeto;

public class FormFolha extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String folncodg;
	private String folndias;
	private String folnnum;
	private String folddata;
	private String foldcadt;
	private String foldmes;
	private String folncgep;
	private String folcdesc;

	public String getFolncodg() {
		return this.folncodg;
	}

	public void setFolncodg(String folncodg) {
		this.folncodg = folncodg;
	}

	public String getFolndias() {
		return this.folndias;
	}

	public void setFolndias(String folndias) {
		this.folndias = folndias;
	}

	public String getFolnnum() {
		return this.folnnum;
	}

	public void setFolnnum(String folnnum) {
		this.folnnum = folnnum;
	}

	public String getFolddata() {
		return this.folddata;
	}

	public void setFolddata(String folddata) {
		this.folddata = folddata;
	}

	public String getFoldcadt() {
		return this.foldcadt;
	}

	public void setFoldcadt(String foldcadt) {
		this.foldcadt = foldcadt;
	}

	public String getFoldmes() {
		return this.foldmes;
	}

	public void setFoldmes(String foldmes) {
		this.foldmes = foldmes;
	}

	public String getFolncgep() {
		return this.folncgep;
	}

	public void setFolncgep(String folncgep) {
		this.folncgep = folncgep;
	}
	
	public String getFolcdesc() {
		return folcdesc;
	}

	public void setFolcdesc(String folcdesc) {
		this.folcdesc = folcdesc;
	}

	public void validarForm(ActionMessages erros){
		int dias = -1;
		try {
			dias = Integer.parseInt(folndias);
		} catch (Exception e) {
		}
		if(ValidaObjeto.isNullId(folndias)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Dias uteis"));
		} else if( dias < 1 || dias > 31){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.invalid", "Dias uteis"));
		}
		if(ValidaObjeto.isNullId(folnnum)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Número"));
		}
		if(ValidaObjeto.isNullId(folcdesc)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Descrição"));
		}
		if(ValidaObjeto.isNullId(folddata)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Pagamento"));
		}else if(!ValidaObjeto.validaData(folddata)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.date","o", "Pagamento"));
		}
		if(ValidaObjeto.isNullId(foldmes)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Mês"));
		}else if(!ValidaObjeto.validaData("01/" + foldmes)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.date","o", "Mês"));
		}
		if(ValidaObjeto.isNullId(folncgep)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Empresa"));
		}
	}

}
