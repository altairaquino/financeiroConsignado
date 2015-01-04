package com.grupoexata.folha.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.grupoexata.bancario.utils.ValidaObjeto;

public class FormPagtoajcust extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	private String pacncodg;
	private String pacncgtac;
	private String pacdsem;
	private String pacnnfer;
	private String pacdcadt;
	private String pacdpagto;
	private String pacnrefs;
	private String pacnefet;
	private String pacncgfil;
	
	public FormPagtoajcust() {
		super();
		pacnnfer = "0";
	}
	public String getPacncodg() {
		return pacncodg;
	}
	public void setPacncodg(String pacncodg) {
		this.pacncodg = pacncodg;
	}
	public String getPacncgtac() {
		return pacncgtac;
	}
	public void setPacncgtac(String pacncgtac) {
		this.pacncgtac = pacncgtac;
	}
	public String getPacdsem() {
		return pacdsem;
	}
	public void setPacdsem(String pacdsem) {
		this.pacdsem = pacdsem;
	}
	public String getPacnnfer() {
		return pacnnfer;
	}
	public void setPacnnfer(String pacnnfer) {
		this.pacnnfer = pacnnfer;
	}
	public String getPacdcadt() {
		return pacdcadt;
	}
	public void setPacdcadt(String pacdcadt) {
		this.pacdcadt = pacdcadt;
	}
	public String getPacdpagto() {
		return pacdpagto;
	}
	public void setPacdpagto(String pacdpagto) {
		this.pacdpagto = pacdpagto;
	}
	public String getPacnrefs() {
		return pacnrefs;
	}
	public void setPacnrefs(String pacnrefs) {
		this.pacnrefs = pacnrefs;
	}
	public String getPacnefet() {
		return pacnefet;
	}
	public void setPacnefet(String pacnefet) {
		this.pacnefet = pacnefet;
	}
	public String getPacncgfil() {
		return pacncgfil;
	}
	public void setPacncgfil(String pacncgfil) {
		this.pacncgfil = pacncgfil;
	}
	public void validarForm(ActionMessages erros) {
		if(ValidaObjeto.isNullId(pacncgtac)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Tipo ajuda de custo"));
		}
		if(ValidaObjeto.isNullId(pacdpagto)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Data de pagamento"));
		}else if(!ValidaObjeto.validaData(pacdpagto)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.date","a", "Data de pagamento"));
		}
		
		if(ValidaObjeto.isNullId(pacdsem)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Inicio da Semana"));
		}else if(!ValidaObjeto.validaData(pacdsem)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.date","o", "Inicio da Semana"));
		}
		if(ValidaObjeto.isNullId(pacnnfer)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Qtd de dias com feriado"));
		}	
		
		int ref = -1;
		if(ValidaObjeto.isNullId(pacnrefs)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "dias referentes"));
		}else{
			try {
				ref = Integer.parseInt(pacnrefs);
			} catch (Exception e) {}
		}
		int efet = -1;		
		if(ValidaObjeto.isNullId(pacnefet)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "dias efetivos"));
		}else{
			try {
				ref = Integer.parseInt(pacnrefs);
			} catch (Exception e) {}
		}
		if(ref != -1 && efet != -1 && (ref < efet)){
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.default", "dias efetivos não pode ser maior que dias referentes"));
		}
	}

}
