package com.grupoexata.folha.struts.form;
import org.apache.struts.action.ActionForm;

public class FormFolhaEmpItem extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String feincodg;
	private String feincgfoe;
	private String feincgder;
	private String feiyvalor;
	private String feiybase;

	public String getFeincodg() {
		return this.feincodg;
	}

	public void setFeincodg(String feincodg) {
		this.feincodg = feincodg;
	}

	public String getFeincgfoe() {
		return this.feincgfoe;
	}

	public void setFeincgfoe(String feincgfoe) {
		this.feincgfoe = feincgfoe;
	}	

	public String getFeincgder() {
		return feincgder;
	}

	public void setFeincgder(String feincgder) {
		this.feincgder = feincgder;
	}

	public String getFeiyvalor() {
		return this.feiyvalor;
	}

	public void setFeiyvalor(String feiyvalor) {
		this.feiyvalor = feiyvalor;
	}

	public String getFeiybase() {
		return feiybase;
	}

	public void setFeiybase(String feiybase) {
		this.feiybase = feiybase;
	}
}
