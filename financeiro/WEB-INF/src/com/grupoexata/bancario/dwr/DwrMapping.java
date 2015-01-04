package com.grupoexata.bancario.dwr;

import java.util.Iterator;
import java.util.List;

import com.grupoexata.bancario.dao.ModelCidade;
import com.grupoexata.bancario.dao.ModelEntidade;
import com.grupoexata.bancario.dao.ModelLigacaoSqi;
import com.grupoexata.bancario.dao.ModelTabelaProduto;
import com.grupoexata.bancario.struts.bean.BeanCidade;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.struts.bean.BeanLigacaoSqi;
import com.grupoexata.bancario.struts.bean.BeanTabelaProduto;
import com.grupoexata.bancario.utils.ValidaObjeto;
import com.grupoexata.financeiro.dao.ModelCentroCusto;
import com.grupoexata.financeiro.struts.bean.BeanCentroCusto;
import com.grupoexata.juridico.dao.ModelLigacaoJuridico;
import com.grupoexata.juridico.struts.bean.BeanLigacaoJuridico;



public class DwrMapping {
	
	public static String getCidades(String UF, String campo){
		String ret = "<select name=\""+campo+"\" style=\"width: 300px\">\n";
		try {
			
			List<BeanCidade> list = ModelCidade.getInstance().getCidadesDoEstado(UF);
			Iterator<BeanCidade> iter = list.iterator();
			
			while (iter.hasNext()){
				BeanCidade cidade = iter.next();
				ret += "<option value=\""+cidade.getCdncodg()+"\">"+cidade.getCdcdesc()+"</option>\n";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		ret += "</select>\n";
		
		return ret;
	}
	
	public static String getCentrosDeCusto(String orencodg, String campo){
		String ret = "<select name=\""+campo+"\" style=\"width: 300px\">\n";
		try {
			
			List<BeanCentroCusto> list = ModelCentroCusto.getInstance().getCentroCustoPorOrigemAtivos(Integer.parseInt(orencodg));
			Iterator<BeanCentroCusto> iter = list.iterator();
			
			while (iter.hasNext()){
				BeanCentroCusto cidade = iter.next();
				ret += "<option value=\""+cidade.getCrncodg()+"\">"+cidade.getCrcdesc()+"</option>\n";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		ret += "</select>\n";
		
		return ret;
	}
	
	public static String getTabelasDoProduto(String pdncodg, String campo){
		String ret = "<select name=\""+campo+"\" style=\"width: 300px\">\n";
		try {
			
			List<BeanTabelaProduto> list = ModelTabelaProduto.getInstance().getTabelaDoProduto(Integer.parseInt(pdncodg));
			Iterator<BeanTabelaProduto> iter = list.iterator();
			
			while (iter.hasNext()){
				BeanTabelaProduto tabela = iter.next();
				ret += "<option value=\""+tabela.getTpncodg()+"\">"+tabela.getTpcdesc2()+"</option>\n";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		ret += "</select>\n";
		
		return ret;
	}
	
	public static String getLigacoesDoContato(String enncodg){
		String ret = "<table align=\"center\" style=\"width: 100%;\">";
		try {
			List<BeanLigacaoJuridico> list = ModelLigacaoJuridico.getInstance().getUltimasLigacoesDoContato(Integer.parseInt(enncodg));
			Iterator<BeanLigacaoJuridico> iter = list.iterator();
			
			if (list.isEmpty()){
				ret += "<tr><td colspan='2'>Não há registros anteriores.</td></tr>";
			}
			
			while (iter.hasNext()){
				BeanLigacaoJuridico tabela = iter.next();
				ret += "<tr>"+
							"<th style=\"color: #00D; width: 20%\">"+
								tabela.getLgjddata()+
							"</th>"+
							"<td style=\"width: 80%;\">"+
								tabela.getLgjmobsv()+
							"</td>"+
						"</tr>\n";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		ret += "</table>\n";
		
		return ret;
	}
	
	public static String getLigacoesDoSqi(String enncodg){
		String ret = "<table align=\"center\" style=\"width: 100%;\">";
		try {
			List<BeanLigacaoSqi> list = ModelLigacaoSqi.getInstance().getUltimasLigacoesDoCorretor(Integer.parseInt(enncodg));
			Iterator<BeanLigacaoSqi> iter = list.iterator();
			
			if (list.isEmpty()){
				ret += "<tr><td colspan='2'>Não há registros anteriores.</td></tr>";
			}
			
			while (iter.hasNext()){
				BeanLigacaoSqi tabela = iter.next();
				ret += "<tr>"+
				"<th style=\"color: #00D; width: 20%\">"+
				tabela.getLgsddata()+
				"</th>"+
				"<td style=\"width: 70%;\">"+
				tabela.getLgsmobsv()+
				"</td>"+
				"<td style=\"width: 10%;\"><img style = 'cursor: pointer'src='imagens/cancela.jpg' onclick='cancela("+tabela.getLgsncodg()+")'>"+"</td>"+
				"</tr>\n";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		ret += "</table>\n";
		
		return ret;
	}
	
	public static String getNomeEntidadePorCPF(String cpf){
		String ret = "";
		try {
			BeanEntidade entidade = ModelEntidade.getInstance().getBeanEntidadePorCPF(ValidaObjeto.removeCharOfInteger(cpf),1);
			if (entidade != null){
				ret = entidade.getEncnome();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static void cancelaLigacaoSQI(String codigo){
		try {
			ModelLigacaoSqi.getInstance().cancel(Integer.parseInt(codigo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
