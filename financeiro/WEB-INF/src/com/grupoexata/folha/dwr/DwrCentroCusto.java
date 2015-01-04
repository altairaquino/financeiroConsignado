package com.grupoexata.folha.dwr;

import java.util.Iterator;
import java.util.List;

import com.grupoexata.financeiro.dao.ModelCentroCusto;
import com.grupoexata.financeiro.struts.bean.BeanCentroCusto;

public class DwrCentroCusto {
	
	public static String getCentroCustos(String eccncgemp, String onclick) {
		StringBuffer sb = new StringBuffer();
		try {
			List<BeanCentroCusto> list = ModelCentroCusto.getInstance().listCentroCustoDisp(eccncgemp);
			sb.append("\t\t\t<table style=\"width: 100%;\">");
			if(list.isEmpty()){
				sb.append("\t\t\t\t<tr>");
				sb.append("\t\t\t\t\t<td>");
				sb.append("<font style=\"color:red;\">Centro de custo nï¿½o cadastrado<font>");
				sb.append("\t\t\t\t\t</td>");
				sb.append("\t\t\t\t</tr>");
			}else{
				int c = 0;
				String event = "";
				if(onclick != null && !onclick.equals("")){
					event = " onclick=\"" + onclick + "\"";
				}
				for (BeanCentroCusto cc : list) {
					sb.append("\t\t\t\t<tr style=\"background-color: " + (c++%2==0?"#DDD":"#FFF") + "\">\n");
					sb.append("\t\t\t\t\t<td>");
					sb.append("\t\t\t\t\t\t" + cc.getCrcdesc());
					sb.append("\t\t\t\t\t</td>");
					sb.append("\t\t\t\t\t<td>");
					sb.append("\t\t\t\t\t\t");
					String x = cc.getCrlativ().equals("T")?"check.jpg":"cancela.jpg";
					sb.append("<a href=\"#\"" + String.format(event, cc.getCrncodg()) + ">");
					sb.append("<img src=\"imagens/"+x+"\" alt=\"\">");
					sb.append("</a>\n");
					sb.append("\t\t\t\t\t</td>");
					sb.append("\t\t\t\t</tr>");
				}
			}
			sb.append("\t\t\t</table>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static void addCentroCusto(String empncodg, String crncodg) {
		ModelCentroCusto.getInstance().addRemoverEcc(Integer.parseInt(empncodg), Integer.parseInt(crncodg));
	}
	
	public static String getCentroCustosMovimento(String movncodg, String onclick) {
		StringBuffer sb = new StringBuffer();
		try {
			List<BeanCentroCusto> list = ModelCentroCusto.getInstance().listCentroCustoMovimento(movncodg);
			sb.append("\t\t\t<table style=\"width: 100%;\">");
			if(list.isEmpty()){
				sb.append("\t\t\t\t<tr>");
				sb.append("\t\t\t\t\t<td>");
				sb.append("<font style=\"color:red;\">Centro de custo não cadastrado.<font>");
				sb.append("\t\t\t\t\t</td>");
				sb.append("\t\t\t\t</tr>");
			}else{
				int c = 0;
				String event = "";
				if(onclick != null && !onclick.equals("")){
					event = " onclick=\"" + onclick + "\"";
				}
				
				
				Iterator<BeanCentroCusto> it = list.iterator();
				
				//for (BeanCentroCusto cc : list) {
				while (it.hasNext()){
					BeanCentroCusto cc = it.next();
					String x = cc.getCrlativ().equals("T")?"check.jpg":"cancela.jpg";
					sb.append("\t\t\t\t<tr style=\"background-color: " + (c++%2==0?"#DDD":"#FFF") + "\">\n");
					sb.append("\t\t\t\t\t<td>");
					sb.append("<a href=\"#\"" + String.format(event, cc.getCrncodg()) + ">");
					sb.append("<img src=\"imagens/"+x+"\" alt=\"\">");
					sb.append("</a>\n");
					sb.append("\t\t\t\t\t\t" + cc.getCrcdesc());
					sb.append("\t\t\t\t\t</td>");
					
					if (it.hasNext()){
						cc = it.next();
						
						sb.append("\t\t\t\t\t<td>");
						x = cc.getCrlativ().equals("T")?"check.jpg":"cancela.jpg";
						sb.append("<a href=\"#\"" + String.format(event, cc.getCrncodg()) + ">");
						sb.append("<img src=\"imagens/"+x+"\" alt=\"\">");
						sb.append("</a>\n");
						sb.append("\t\t\t\t\t\t" + cc.getCrcdesc());
						sb.append("\t\t\t\t\t</td>");
						
						if (it.hasNext()){
							cc = it.next();
							
							sb.append("\t\t\t\t\t<td>");
							x = cc.getCrlativ().equals("T")?"check.jpg":"cancela.jpg";
							sb.append("<a href=\"#\"" + String.format(event, cc.getCrncodg()) + ">");
							sb.append("<img src=\"imagens/"+x+"\" alt=\"\">");
							sb.append("</a>\n");
							sb.append("\t\t\t\t\t\t" + cc.getCrcdesc());
							sb.append("\t\t\t\t\t</td>");
							
						}
					}
					
					sb.append("\t\t\t\t</tr>");
				}
			}
			sb.append("\t\t\t</table>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static void addCentroCustoMovimento(String movncodg, String crncodg) {
		ModelCentroCusto.getInstance().addRemoverCrm(Integer.parseInt(movncodg), Integer.parseInt(crncodg));
	}
}
