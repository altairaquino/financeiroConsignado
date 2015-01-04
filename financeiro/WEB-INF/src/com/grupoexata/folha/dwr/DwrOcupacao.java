package com.grupoexata.folha.dwr;

import java.util.List;

import com.grupoexata.folha.bean.BeanOcupacao;
import com.grupoexata.folha.bean.BeanOcupacaoBusca;
import com.grupoexata.folha.dao.ModelOcupacao;

public class DwrOcupacao {
	public static String getOcupacao(String ocpccodg) {
		String ret = "Ocupação não encontrada&-1";
		try {
			BeanOcupacao ocp = new ModelOcupacao().getOcpccodg(ocpccodg);
			if (ocp != null) {
				ret = ocp.getOcpctitulo()+"&"+ocp.getOcpncodg();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static String getOcupacoesFamilia(String familia, String onclick) {
		StringBuffer sb = new StringBuffer();
		try {
			List<BeanOcupacao> list = new ModelOcupacao().listByOcpccgfam(familia);
			if(list.isEmpty()){
				sb.append("<font style=\"color:red;\">sem resultados para \"" + familia +"\".<font>");
			}else{
				sb.append("<table>\n");
				sb.append("\t<tr>\n");

				sb.append("\t\t<th>\n");
				sb.append("\t\t\tCódigo\n");
				sb.append("\t\t</th>\n");
				
				sb.append("\t\t<th>\n");
				sb.append("\t\t\tOcupação\n");
				sb.append("\t\t</th>\n");
				
				sb.append("\t</tr>\n");
				int c = 0;
				String event = "";
				if(onclick != null && !onclick.equals("")){
					event = " onclick=\"" + onclick + "\"";
				}
				for (BeanOcupacao ocp : list) {
					sb.append("\t<tr style=\"background-color: " + (c++%2==0?"#DDD":"#FFF") + "\">\n");

					sb.append("\t\t<td>\n");
					sb.append("\t\t\t" + ocp.getOcpccodg() + "\n");
					sb.append("\t\t</td>\n");
					
					String e = String.format(event, "'" + ocp.getOcpncodg() + "'","'" + ocp.getOcpctitulo() + "'", "'" + ocp.getOcpccodg() + "'");
					sb.append("\t\t<td>\n");
					sb.append("\t\t\t<a href=\"#\"" + e + ">" + ocp.getOcpctitulo() + "</a>\n");
					sb.append("\t\t</td>\n");

					sb.append("\t</tr>\n");
				}
				sb.append("</table>\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String getFamilia(String parte, String onclick) {
		StringBuffer sb = new StringBuffer();
		try {
			if (parte != null && parte.length() > 3) {
				List<BeanOcupacaoBusca> list = new ModelOcupacao().getListFamilia(parte);
				if(list.isEmpty()){
					sb.append("<font style=\"color:red;\">sem resultados para \"" + parte +"\".<font>");
				}else{
					sb.append("<table>\n");
					sb.append("\t<tr>\n");
	
					sb.append("\t\t<th>\n");
					sb.append("\t\t\tTítulo\n");
					sb.append("\t\t</th>\n");
	
					sb.append("\t\t<th>\n");
					sb.append("\t\t\tCódigo\n");
					sb.append("\t\t</th>\n");
	
					sb.append("\t\t<th>\n");
					sb.append("\t\t\tTipo\n");
					sb.append("\t\t</th>\n");
	
					sb.append("\t</tr>\n");
					int c = 0;
					String event = "";
					if(onclick != null && !onclick.equals("")){
						event = " onclick=\"" + onclick + "\"";
					}
					for (BeanOcupacaoBusca ocpb : list) {
						sb.append("\t<tr style=\"background-color: " + (c++%2==0?"#DDD":"#FFF") + "\">\n");
	
						sb.append("\t\t<td>\n");
						sb.append("\t\t\t<a href=\"#\"" + String.format(event, ocpb.getCodigo()) + ">" + ocpb.getTitulo() + "</a>\n");
						sb.append("\t\t</td>\n");
	
						sb.append("\t\t<td>\n");
						sb.append("\t\t\t" + ocpb.getCodigo() + "\n");
						sb.append("\t\t</td>\n");
	
						sb.append("\t\t<td>\n");
						sb.append("\t\t\t" + ocpb.getTipo() + "\n");
						sb.append("\t\t</td>\n");
	
						sb.append("\t</tr>\n");
					}
					sb.append("</table>\n");
				}
			}else{
				sb.append("<font style=\"color:red;\">mínimo de 4 caracteres.<font>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
