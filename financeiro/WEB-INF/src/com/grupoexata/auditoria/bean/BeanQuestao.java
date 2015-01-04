package com.grupoexata.auditoria.bean;

import java.io.Serializable;
import java.util.List;

public class BeanQuestao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9191054331509284270L;
	private String qstncodg;
	private String qstncgqtn;
	private String qstncggpo;
	private String qstlatv;
	private String qstncgqst;
	private String qstcnumr;
	private String qstcdesc;
	private String qstncgopc;
	private String ctqncgen;
	private String ctqncgct;
	private List<BeanOpcao> opcaos;
	private List<BeanQuestao> questaos;

	public String getQstncodg() {
		return qstncodg;
	}

	public void setQstncodg(String qstncodg) {
		this.qstncodg = qstncodg;
	}

	public String getQstncgqtn() {
		return qstncgqtn;
	}

	public void setQstncgqtn(String qstncgqtn) {
		this.qstncgqtn = qstncgqtn;
	}

	public String getQstncggpo() {
		return qstncggpo;
	}

	public void setQstncggpo(String qstncggpo) {
		this.qstncggpo = qstncggpo;
	}

	public String getQstlatv() {
		return qstlatv;
	}

	public void setQstlatv(String qstlatv) {
		this.qstlatv = qstlatv;
	}

	public String getQstncgqst() {
		return qstncgqst;
	}

	public void setQstncgqst(String qstncgqst) {
		this.qstncgqst = qstncgqst;
	}

	public String getQstcnumr() {
		return qstcnumr;
	}

	public void setQstcnumr(String qstcnumr) {
		this.qstcnumr = qstcnumr;
	}

	public String getQstcdesc() {
		return qstcdesc;
	}

	public void setQstcdesc(String qstcdesc) {
		this.qstcdesc = qstcdesc;
	}

	public String getQstncgopc() {
		return qstncgopc;
	}

	public void setQstncgopc(String qstncgopc) {
		this.qstncgopc = qstncgopc;
	}

	public List<BeanOpcao> getOpcaos() {
		return opcaos;
	}

	public void setOpcaos(List<BeanOpcao> opcaos) {
		this.opcaos = opcaos;
	}

	public List<BeanQuestao> getQuestaos() {
		return questaos;
	}

	public void setQuestaos(List<BeanQuestao> questaos) {
		this.questaos = questaos;
	}

	public String getCtqncgen() {
		return ctqncgen;
	}

	public void setCtqncgen(String ctqncgen) {
		this.ctqncgen = ctqncgen;
	}

	public String getCtqncgct() {
		return ctqncgct;
	}

	public void setCtqncgct(String ctqncgct) {
		this.ctqncgct = ctqncgct;
	}

}
