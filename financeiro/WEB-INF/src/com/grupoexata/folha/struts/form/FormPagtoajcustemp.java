package com.grupoexata.folha.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessages;

public class FormPagtoajcustemp extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String pceyacres;
	private String pcencodg;
	private String pceydesc;
	private String pcencgemp;
	private String pcencgtac;
	private String pcencgpac;
	private String pceyvalor;
	private String pcedpagto;
	public String getPceyacres() {
		return pceyacres;
	}
	public void setPceyacres(String pceyacres) {
		this.pceyacres = pceyacres;
	}
	public String getPcencodg() {
		return pcencodg;
	}
	public void setPcencodg(String pcencodg) {
		this.pcencodg = pcencodg;
	}
	public String getPceydesc() {
		return pceydesc;
	}
	public void setPceydesc(String pceydesc) {
		this.pceydesc = pceydesc;
	}
	public String getPcencgemp() {
		return pcencgemp;
	}
	public void setPcencgemp(String pcencgemp) {
		this.pcencgemp = pcencgemp;
	}
	public String getPcencgtac() {
		return pcencgtac;
	}
	public void setPcencgtac(String pcencgtac) {
		this.pcencgtac = pcencgtac;
	}
	public String getPcencgpac() {
		return pcencgpac;
	}
	public void setPcencgpac(String pcencgpac) {
		this.pcencgpac = pcencgpac;
	}
	public String getPceyvalor() {
		return pceyvalor;
	}
	public void setPceyvalor(String pceyvalor) {
		this.pceyvalor = pceyvalor;
	}
	public String getPcedpagto() {
		return pcedpagto;
	}
	public void setPcedpagto(String pcedpagto) {
		this.pcedpagto = pcedpagto;
	}
	public void validarForm(ActionMessages erros) {
		// TODO Auto-generated method stub
		
	}
	
}
