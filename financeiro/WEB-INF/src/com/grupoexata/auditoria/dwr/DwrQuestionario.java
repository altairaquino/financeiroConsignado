package com.grupoexata.auditoria.dwr;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.grupoexata.auditoria.bean.BeanOpcao;
import com.grupoexata.auditoria.bean.BeanQuestao;
import com.grupoexata.auditoria.bean.BeanQuestionario;
import com.grupoexata.auditoria.dao.ModelQuestionario;
import com.grupoexata.bancario.utils.Utils;

public class DwrQuestionario implements Serializable{
	
	private static final long serialVersionUID = 3705906103709289942L;
	
	public static DwrQuestionario getInstance(){
		return new DwrQuestionario();
	}
	
	public static String getQuestionario(int qtnncodg, String ativar){
		BeanQuestionario questionario = ModelQuestionario.getIntance().get(qtnncodg);
		questionario.setQuestaos(ModelQuestionario.getIntance().listQuestaoByQstncgqtn(qtnncodg));
		StringBuffer sb = new StringBuffer();
		sb.append("<div class=\"questionario\" style='z-index: -1;'>");
		sb.append("<h3>Questionário: " + questionario.getQtncdesc() +"</h3>");
		for (BeanQuestao questao : questionario.getQuestaos()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ativar", ativar);
			sb.append(getQuestao(questao, map));
		}
		sb.append("</div>");
		return sb.toString();
	}
	
	
	private static StringBuffer getQuestao(BeanQuestao questao, Map<String, Object> map){
		//BeanQuestao questao = (BeanQuestao)map.get("questao");
		String numero = (String)map.get("numero");
		StringBuffer buffer = new StringBuffer();
		if(numero == null){
			buffer.append("<hr>\n");
		}
		numero = numero == null ? "" : numero + ".";
		numero += questao.getQstcnumr();
		Integer tab  = (Integer)map.get("tab");
		tab = tab == null ? 3 : tab + 1;
		String h = "h" + tab + "";
		String qst = "qst_" + questao.getQstncodg();
		buffer.append("<" + h +" style = \"position: relative; left: " + (tab - 2)*15 + "px;\">" + numero + ")&nbsp;" + questao.getQstcdesc() + "?</" + h +">\n");
		String id_div = "div_sitem_" + qst;
		String format_event_ativar = "";
		String ativar = (String)map.get("ativar");
		boolean temOpcao = Utils.exists(questao.getQstncgopc());
		if(temOpcao){
			String s = ativar == null ? "" : ativar;
			s = s.replace("%id_div", id_div);
			format_event_ativar =  " onclick=\"" + s + "\"";
		}else{
			ModelQuestionario.getIntance().getQuestaoRecusive(questao);
		}
		if(Utils.exists(questao.getQstncggpo())){
			questao.setOpcaos(ModelQuestionario.getIntance().listOpcaoByOpcncggpo(Integer.parseInt(questao.getQstncggpo())));
		}
		if(questao.getOpcaos() != null){
			for(BeanOpcao opcao:questao.getOpcaos()){
				String ativo = Utils.exists(format_event_ativar) && opcao.getOpcncodg().equals(questao.getQstncgopc())
					? "true" : "false";
				String 
				event_ativar = format_event_ativar.replace("%ativo", ativo);
				event_ativar = event_ativar.replace("%qstncodg", questao.getQstncodg());
				event_ativar = event_ativar.replace("%numero", numero);
				event_ativar = event_ativar.replace("%tab", tab.toString());
				//event_ativar = format_event_ativar.replace("%ativar",  format_event_ativar);
				buffer.append("<span style = \"position: relative; left: " + (tab - 1)*15 + "px;\"><input" + event_ativar + " type=\"radio\" name=\"" + qst + "\" value=\"" + opcao.getOpcncodg() + ";" + numero + "\">" + opcao.getOpccdesc() + "&nbsp;</span>\n");
			}
			buffer.append("<input style=\"display: none\" checked=\"checked\" type=\"radio\" name=\"" + qst + "\" value=\"x;" + numero + "\">\n");
		}
		if(questao.getQuestaos() != null){
			if(questao.getQuestaos().size() > 0){
				buffer.append("<div id=\"" + id_div + "\" class=\"questao_sitem\" style='z-index: -1;'>\n");
				buffer.append("<hr>\n");
			}
//			for(BeanQuestao questao1: questao.getQuestaos()){
//				Map<String, Object> map2 = new HashMap<String, Object>();
//				map2.put("questao", questao1);
//				map2.put("tab", tab);
//				map2.put("numero", numero);
//				map2.put("ativar", ativar);
//				buffer.append(getQuestao(questao1,map2));/***/
//			}
			buffer.append(getQuestaos(questao, numero, tab, ativar));
			if(questao.getQuestaos().size() > 0){
				buffer.append("</div>\n");
			}
		}else{
				buffer.append("<div>\n");
				buffer.append("<hr>\n");
				buffer.append("<div id=\"" + id_div + "\" class=\"questao_sitem\">\n");
				buffer.append("</div>\n");
				buffer.append("</div>\n");
		}
		return buffer;		
	}
	
	public static String getQuestaos(int qstncodg, String numero, Integer tab, String ativar){
		BeanQuestao questao = ModelQuestionario.getIntance().getQuestao(qstncodg);
		ModelQuestionario.getIntance().getQuestaoRecusive(questao);
		return getQuestaos(questao, numero, tab, ativar).toString();
	}
	public static StringBuffer getQuestaos(BeanQuestao questao, String numero, Integer tab, String ativar){		
		StringBuffer buffer = new StringBuffer(); 
		boolean primeiro = true;
		for(BeanQuestao questao1: questao.getQuestaos()){
			Map<String, Object> map2 = new HashMap<String, Object>();
			//map2.put("questao", questao1);
			if(primeiro){
				primeiro = false;
			}else{
				buffer.append("<hr>\n");
			}
			map2.put("tab", tab);
			map2.put("numero", numero); 
			map2.put("ativar", ativar);
			buffer.append(getQuestao(questao1,map2));/***/
		}
		return buffer;
	}
	public static String getQuestao(int qstncodg, String numero, Integer tab, String ativar){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("numero",numero);
		map.put("tab", tab);
		map.put("ativar", ativar);
		BeanQuestao questao = ModelQuestionario.getIntance().getQuestao(qstncodg);
		return getQuestao(questao, map).toString();
	}
}
