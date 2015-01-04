package com.grupoexata.folha.dwr;

import com.grupoexata.folha.bean.BeanDescRend;
import com.grupoexata.folha.bean.BeanItemDescRend;
import com.grupoexata.folha.bean.BeanTipoDescRend;
import com.grupoexata.folha.dao.ModelItemDescRend;

public class DwrItemDescRend {
	public static String addItemDescRend(String valor, String onclick) {
		StringBuffer sb = new StringBuffer();
		try {
			String[] valores = valor.split(";");
			for (int i = 0; i < valores.length; i++) {
				valores[i] = valores[i].trim();
				valores[i] = valores[i].replace("_", "");
			}
			ModelItemDescRend md = new ModelItemDescRend();
			BeanDescRend der = new BeanDescRend();
			BeanTipoDescRend tdr = new BeanTipoDescRend();
			BeanItemDescRend bean = new BeanItemDescRend();
			bean.setIdrncgder(der);
			bean.setIdrncgtdr(tdr);
			der.setDerncodg(valores[0]);
			tdr.setTdrncodg(valores[1]);
			bean.setIdrctipo(valores[2]);
			bean = md.addRemove(bean);
			String t = (bean != null && bean.getIdrctipo() != null && !bean.getIdrctipo().equals("") ? bean.getIdrctipo() : "");
			String event = "";
			if(onclick != null && !onclick.equals("")){
				event = " onclick=\"" + onclick + "\"";
			}
			String pos = t.equals("+") ? "" : "_";
			String neg = t.equals("-") ? "" : "_";
			String nins = t.equals("") ? "" : "_";
			String sel;
			/*sb.append("\t\t\t\t\t<table>\n");
			sb.append("\t\t\t\t\t<tr>\n");
			sb.append("\t\t\t\t\t<td>\n");*/
			sel = pos.equals("_")? "style=\" cursor: pointer;\" " + String.format(event,"+"):"";
			sb.append("\t\t\t\t\t\t<img title=\" Positivo\" " + sel + " src=\" imagens/positivo" + pos + ".png\">\n");
		/*	sb.append("\t\t\t\t\t</td>\n");
			sb.append("\t\t\t\t\t<td>\n");*/
			sel = neg.equals("_")? "style=\" cursor: pointer;\" " + String.format(event,"-"):"";
			sb.append("\t\t\t\t\t\t<img title=\" Negativo\" " + sel + " src=\" imagens/negativo" + neg + ".png\">\n");
		/*	sb.append("\t\t\t\t\t</td>\n");
			sb.append("\t\t\t\t\t<td>\n");*/
			sel = nins.equals("_")? "style=\" cursor: pointer;\" " + String.format(event,""):"";
			sb.append("\t\t\t\t\t\t<img title=\" Não inside\" " + sel + " src=\" imagens/ninside" + nins + ".png\">\n");
			/*sb.append("\t\t\t\t\t</td>\n");
			sb.append("\t\t\t\t\t</tr>\n");
			sb.append("\t\t\t\t\t</table>\n");*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
