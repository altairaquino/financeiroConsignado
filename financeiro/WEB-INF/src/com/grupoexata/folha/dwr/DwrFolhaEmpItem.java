package com.grupoexata.folha.dwr;

import java.util.List;

import com.grupoexata.folha.bean.BeanFolhaEmp;
import com.grupoexata.folha.bean.BeanFolhaEmpItem;
import com.grupoexata.folha.dao.ModelFolhaEmp;
import com.grupoexata.folha.dao.ModelFolhaEmpItem;

public class DwrFolhaEmpItem {
	
	public static String getFolhaEmpItems(String feincgfoe, String onclick) {
		StringBuffer sb = new StringBuffer();
		try {
			List<BeanFolhaEmpItem> list = ModelFolhaEmpItem.getInstance().list(feincgfoe);
			BeanFolhaEmp folhaEmp = ModelFolhaEmp.getInstance().getByFoencodg(feincgfoe);
			
			sb.append("\t\t\t<fieldset>");
			sb.append("\t\t\t<legend>" + folhaEmp.getEncnome() + "</legend>");
			sb.append("\t\t\t<table style=\"width: 100%;\">");
			if(list.isEmpty()){
				sb.append("\t\t\t\t<tr>");
				sb.append("\t\t\t\t\t<td>");
				sb.append("<font style=\"color:red;\">Sem item pro funcionário<font>");
				sb.append("\t\t\t\t\t</td>");
				sb.append("\t\t\t\t</tr>");
			}else{
				String event = "";
				if(onclick != null && !onclick.equals("")){
					event = " onclick=\"" + onclick + "\"";
				}
				
				sb.append("\t\t\t\t<tr>\n");
				sb.append("\t\t\t\t\t<th>\n");
				sb.append("\t\t\t\t\t\t" + "Item" + "\n");
				sb.append("\t\t\t\t\t</th>\n");
				
				sb.append("\t\t\t\t\t<th style=\"text-align: right; width: 100px;\">\n"); 
				sb.append("\t\t\t\t\t\t" + "Base" + "\n");
				sb.append("\t\t\t\t\t</th>\n");
				
				sb.append("\t\t\t\t\t<th style=\"text-align: right; width: 100px;\">\n");
				sb.append("\t\t\t\t\t\t" + "Valor" + "\n");
				sb.append("\t\t\t\t\t</th>\n");
				
				sb.append("\t\t\t\t\t<th>&nbsp;</th>\n");
				
				
				sb.append("\t\t\t\t</tr>\n");
				
				for (int i = 0; i < list.size() ; i++) {
					BeanFolhaEmpItem fei =  list.get(i);
					sb.append("\t\t\t\t<tr>\n");
					sb.append("\t\t\t\t\t<td colspan=\"4\"><hr style=\"padding: 0px; margin: 0px;\">\n");
					sb.append("\t\t\t\t\t</td>\n");
					sb.append("\t\t\t\t</tr>\n");
					
					sb.append("\t\t\t\t<tr>\n");					
					
					String s = fei.getDercoper();
					s = s == null ? "" : s;
					s = s.trim();
					s = !s.equals("") ? "(" + s + ")" : s;
					sb.append("\t\t\t\t\t<td>\n");
					sb.append("\t\t\t\t\t\t<a href=\"#\">" + fei.getDerccodg() + " - " + fei.getDercdesc() + s + "</a>\n");
					sb.append("\t\t\t\t\t</td>\n");
					
					sb.append("\t\t\t\t\t<td style=\"text-align: right; width: 100px;\">\n"); 
					sb.append("\t\t\t\t\t\t" + fei.getFeiybase() + "\n");
					sb.append("\t\t\t\t\t</td>\n");
					
					sb.append("\t\t\t\t\t<td style=\"text-align: right; width: 100px;\">\n");
					sb.append("\t\t\t\t\t\t" + fei.getFeiyvalor() + "\n");
					sb.append("\t\t\t\t\t</td>\n");
					sb.append("\t\t\t\t\t<td style=\"padding-left: 10px; width: 200px;\">&nbsp;</td>\n");
					
					sb.append("\t\t\t\t</tr>\n");
				}
				sb.append("\t\t\t\t<tr>\n");
				sb.append("\t\t\t\t\t<td colspan=\"4\"><hr style=\"padding: 0px; margin: 0px;\">\n");
				sb.append("\t\t\t\t\t</td>\n");
				sb.append("\t\t\t\t</tr>\n");
				
				sb.append("\t\t\t\t<tr>\n");
				sb.append("\t\t\t\t\t<th>Líquido</th>\n");
				sb.append("\t\t\t\t\t<th>&nbsp;</th>\n");
				sb.append("\t\t\t\t\t<th style=\"text-align: right; width: 100px;\">" + folhaEmp.getFoeyliqd()+ "</th>\n");
				sb.append("\t\t\t\t\t<th>&nbsp;</th>\n");
				sb.append("\t\t\t\t</tr>\n");
			}
			sb.append("\t\t\t</table>");
			sb.append("\t\t\t</fieldset>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
