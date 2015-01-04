package com.grupoexata.auditoria.bean;

import java.io.Serializable;
import java.util.List;

public class BeanQuestionario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5802406542542274342L;
	private String qtnncodg;
	private String qtncdesc;
	private String qtnlatv;
	private List<BeanQuestao> questaos;

	public String getQtnncodg() {
		return qtnncodg;
	}

	public void setQtnncodg(String qtnncodg) {
		this.qtnncodg = qtnncodg;
	}

	public String getQtncdesc() {
		return qtncdesc;
	}

	public void setQtncdesc(String qtncdesc) {
		this.qtncdesc = qtncdesc;
	}

	public String getQtnlatv() {
		return qtnlatv;
	}

	public void setQtnlatv(String qtnlatv) {
		this.qtnlatv = qtnlatv;
	}

	public List<BeanQuestao> getQuestaos() {
		return questaos;
	}

	public void setQuestaos(List<BeanQuestao> questaos) {
		this.questaos = questaos;
	}

}
