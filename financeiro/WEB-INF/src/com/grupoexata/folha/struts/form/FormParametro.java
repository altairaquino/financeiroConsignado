package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.grupoexata.bancario.utils.Utils;
import com.grupoexata.bancario.utils.ValidaObjeto;

public class FormParametro extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String parncodg;
	private String parnfgts;
	private String parnfgad;
	private String parnmfam;
	private String parnchms;
	private String parnchsm;
	private String parysmin;
	private String parnfgrc;
	private String parnfgra;
	private String parnpis;
	private String paryrbc;
	private String paryddep;
	private String parnmdep;
	private String parnapos;
	private String parndmin;
	private String parnprob;
	private String parnespe;
	private String parnsat;

	public String getParncodg() {
		return this.parncodg;
	}

	public void setParncodg(String parncodg) {
		this.parncodg = parncodg;
	}

	public String getParnfgts() {
		return this.parnfgts;
	}

	public void setParnfgts(String parnfgts) {
		this.parnfgts = parnfgts;
	}

	public String getParnfgad() {
		return this.parnfgad;
	}

	public void setParnfgad(String parnfgad) {
		this.parnfgad = parnfgad;
	}

	public String getParnmfam() {
		return this.parnmfam;
	}

	public void setParnmfam(String parnmfam) {
		this.parnmfam = parnmfam;
	}

	public String getParnchms() {
		return this.parnchms;
	}

	public void setParnchms(String parnchms) {
		this.parnchms = parnchms;
	}

	public String getParnchsm() {
		return this.parnchsm;
	}

	public void setParnchsm(String parnchsm) {
		this.parnchsm = parnchsm;
	}

	public String getParysmin() {
		return this.parysmin;
	}

	public void setParysmin(String parysmin) {
		this.parysmin = parysmin;
	}

	public String getParnfgrc() {
		return this.parnfgrc;
	}

	public void setParnfgrc(String parnfgrc) {
		this.parnfgrc = parnfgrc;
	}

	public String getParnfgra() {
		return this.parnfgra;
	}

	public void setParnfgra(String parnfgra) {
		this.parnfgra = parnfgra;
	}

	public String getParnpis() {
		return this.parnpis;
	}

	public void setParnpis(String parnpis) {
		this.parnpis = parnpis;
	}

	public String getParyrbc() {
		return this.paryrbc;
	}

	public void setParyrbc(String paryrbc) {
		this.paryrbc = paryrbc;
	}

	public String getParyddep() {
		return this.paryddep;
	}

	public void setParyddep(String paryddep) {
		this.paryddep = paryddep;
	}

	public String getParnmdep() {
		return this.parnmdep;
	}

	public void setParnmdep(String parnmdep) {
		this.parnmdep = parnmdep;
	}

	public String getParnapos() {
		return this.parnapos;
	}

	public void setParnapos(String parnapos) {
		this.parnapos = parnapos;
	}

	public String getParndmin() {
		return this.parndmin;
	}

	public void setParndmin(String parndmin) {
		this.parndmin = parndmin;
	}

	public String getParnprob() {
		return this.parnprob;
	}

	public void setParnprob(String parnprob) {
		this.parnprob = parnprob;
	}

	public String getParnespe() {
		return this.parnespe;
	}

	public void setParnespe(String parnespe) {
		this.parnespe = parnespe;
	}

	public String getParnsat() {
		return this.parnsat;
	}

	public void setParnsat(String parnsat) {
		this.parnsat = parnsat;
	}
	public void validarForm(ActionMessages erros){		

		if (ValidaObjeto.isNullId(parnfgts))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "FGTS"));
		else if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(parnfgts)))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.invalid", "FGTS"));
		if (ValidaObjeto.isNullId(parnfgad))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "FGTS Adicional"));
		else if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(parnfgad)))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.invalid", "FGTS Adicional"));
		if (ValidaObjeto.isNullId(parnmfam))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Idade máx. para sálario família"));
		if (ValidaObjeto.isNullId(parnchms))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Carga horária mensal"));
		if (ValidaObjeto.isNullId(parnchsm))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Carga horária semanal"));
		if (ValidaObjeto.isNullId(parysmin))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Salário mínimo"));
		else if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(parysmin)))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.invalid", "Salário mínimo"));
		if (ValidaObjeto.isNullId(parnfgrc))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "FGTS na recisão"));
		else if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(parnfgrc)))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.invalid", "FGTS na recisão"));
		if (ValidaObjeto.isNullId(parnfgra))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "FGTS na recisão Adicional"));
		else if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(parnfgra)))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.invalid", "FGTS na recisão Adicional"));
		if (ValidaObjeto.isNullId(parnpis))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "PIS sobre folha de pagamento"));
		else if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(parnpis)))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.invalid", "PIS sobre folha de pagamento"));
		if (ValidaObjeto.isNullId(paryrbc))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Redução da Base de calculo"));
		else if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(paryrbc)))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.invalid", "Redução da Base de calculo"));
		if (ValidaObjeto.isNullId(paryddep))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Desconto por dependente"));
		else if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(paryddep)))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.invalid", "Desconto por dependente"));
		if (ValidaObjeto.isNullId(parnmdep))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Máximo de dependente"));
		if (ValidaObjeto.isNullId(parnapos))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Aposentadoria"));
		if (ValidaObjeto.isNullId(parndmin))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Desconto mínimo"));
		if (ValidaObjeto.isNullId(parnprob))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Prolabore/Autônomos"));
		else if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(parnprob)))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.invalid", "Prolabore/Autônomos"));
		if (ValidaObjeto.isNullId(parnespe))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Especial(FPAS639)"));
		else if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(parnespe)))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.invalid", "Especial(FPAS639)"));
		if (ValidaObjeto.isNullId(parnsat))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "Sat. ocorrências 04 e 08"));
		else if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(parnsat)))
			erros.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.invalid", "Sat. ocorrências 04 e 08"));
		
	}
}
