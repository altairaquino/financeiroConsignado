package com.grupoexata.bancario.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.grupoexata.bancario.dao.Banco;
import com.grupoexata.bancario.dao.ModelListaEmail;
import com.grupoexata.bancario.struts.bean.BeanContrato;
import com.grupoexata.bancario.struts.bean.BeanEntidade;
import com.grupoexata.bancario.struts.bean.BeanLigacaoSqi;
import com.grupoexata.bancario.struts.bean.BeanListaEmail;
import com.grupoexata.juridico.struts.bean.BeanLigacaoJuridico;


public class EnviarEmail {
	
	private final boolean DEBUG = false;
	
	private String[] emailsSinergia = new String[]{"marcio.rocha@exatagrupo.com.br",
			                               "renato.rocha@exatagrupo.com.br",
			                               "wergila.furtado@exatagrupo.com.br",
			                               "luciano.furtado@exatagrupo.com.br",
			                               "danielle.guedes@exatagrupo.com.br"};
	
	private String[] emailsComercial = new String[]{"marcio.rocha@exatagrupo.com.br",
            										"renato.rocha@exatagrupo.com.br",
										            "wergila.furtado@exatagrupo.com.br",
										            "luciano.furtado@exatagrupo.com.br",
										            "danielle.guedes@exatagrupo.com.br",
										            "bruno.soares@exatagrupo.com.br"};
	
	private HtmlEmail email;
	
	public static EnviarEmail getInstance(){
		return new EnviarEmail();
	}
	
	public EnviarEmail(){
		if (email == null)
			email = new HtmlEmail();
		criarAutenticacao();
	}

	private void criarAutenticacao() {
		try {
			email.setHostName("smtp.gmail.com");
			email.setAuthentication("sistema@exatagrupo.com.br", "sistema");
			email.setSmtpPort(465);
			email.setFrom("sistema@exatagrupo.com.br", "Sistema Exata");
			email.addBcc("sistema@exatagrupo.com.br");
			email.addHeader("X-Priority", "1");
			email.addHeader("Priority", "Urgent");
			email.addHeader("Importance", "high");
			email.setDebug(DEBUG);
						
		} catch (EmailException e) {
			e.printStackTrace();
		}		
	}
	
	private void enviar(boolean ssl){
		try {
			email.setSSL(true);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public void enviarEmailCadastroContrato(BeanContrato contrato, BeanEntidade entidade){
		StringBuffer msg = new StringBuffer();
		msg.append("<HTML>");
		msg.append("<head><meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"></head>");
		msg.append("<BODY>");
		msg.append("<table style=\"width: 600px;\">");
		msg.append("	<tbody>");
		msg.append("		<tr>");
		msg.append("			<td colspan=\"2\"> ");
		msg.append("				ESTE É UM E-MAIL AUTOMATICO ENVIADO PELO SISTEMA DA EXATA CONSULTORIA E NÃO DEVE SER RESPONDIDO. " +
				"PARA MAIORES INFORMAÇÕES UTILIZE O FONE QUE SE ENCONTRA NO RODAPÉ DESTE E-MAIL.");
		msg.append("			</td>");
		msg.append("		</tr>");
		msg.append("		<tr>");
		msg.append("			<td colspan=\"2\"> ");
		msg.append("				<img src='http://www.exatagrupo.com.br/portal/imagens/logo_exata.gif' border='0' width='350' height: 80px;>");
		msg.append("			</td>");
		msg.append("		</tr>");
		msg.append("		<tr>");
		msg.append("			<th colspan=\"2\" style=\"text-align: center; background-color: #DDDDDD;\"> ");
		msg.append("				Cadastro de Novo Contrato no sistema Exata");
		msg.append("			</th>");
		msg.append("		</tr>");
		msg.append("		<tr>");
		msg.append("			<td style=\"width: 200px;\">");
		msg.append("				CORRETOR/ANGARIADOR");
		msg.append("			</td>");
		msg.append("			<td style=\"color: red; font-weight: bold; width: 400px;\">");
		msg.append("				"+ contrato.getCtcnman() +" ");
		msg.append("			</td>");
		msg.append("		</tr>				");
		msg.append("		<tr>");
		msg.append("			<td>");
		msg.append("				Cliente/CPF");
		msg.append("			</td>");
		msg.append("			<td>");
		msg.append("				"+ contrato.getCtcnmcl() +"");
		msg.append("				/"+ contrato.getCtccpf() +"");
		msg.append("			</td>");
		msg.append("		</tr>");
		msg.append("		<tr>");
		msg.append("			<td> ");
		msg.append("				Nº do Contrato");
		msg.append("			</td>");
		msg.append("			<td>");
		msg.append("				"+ contrato.getCtnnumr() +"");
		msg.append("			</td>");
		msg.append("		</tr>");
		msg.append("		<tr>");
		msg.append("			<td> ");
		msg.append("				Forma de Pagamento");
		msg.append("			</td>");
		msg.append("			<td>");
		msg.append("				"+ contrato.getCtcdcfp() +"");
		msg.append("			</td>");
		msg.append("		</tr>");
		if (contrato.getCtncgfp().equals("2")){
			msg.append("		<tr>");
			msg.append("			<td> ");
			msg.append("				Banco");
			msg.append("			</td>");
			msg.append("			<td>");
			msg.append("				"+ contrato.getCtcdcbc() +" - Agência: "+contrato.getCtcagen());
			msg.append("			</td>");
			msg.append("		</tr>");
		}
		msg.append("		<tr>");
		msg.append("			<td> ");
		msg.append("				Produto");
		msg.append("			</td>");
		msg.append("			<td>");
		msg.append("				"+ contrato.getCtcdcpd() +"");
		msg.append("			</td>");
		msg.append("		</tr>				");
		msg.append("		<tr>");
		msg.append("			<td>");
		msg.append("				Tabela Produto");
		msg.append("			</td>");
		msg.append("			<td>");
		msg.append("				"+ contrato.getCtcdctp() +"");
		msg.append("			</td>");
		msg.append("		</tr>");
		msg.append("		<tr>");
		msg.append("			<td>");
		msg.append("				Valor do Contrato");
		msg.append("			</td>");
		msg.append("			<td>");
		msg.append("				R$ "+ contrato.getCtyvalr() +"");
		msg.append("			</td>");
		msg.append("		</tr>");
		msg.append("		<tr>");
		msg.append("			<td>");
		msg.append("				Data de Cadastro");
		msg.append("			</td>");
		msg.append("			<td>");
		msg.append("				 "+ contrato.getCtdcadt() +"");
		msg.append("			</td>");
		msg.append("		</tr>");
		msg.append("		<tr>");
		msg.append("			<td colspan=\"2\" style=\"font-size: 10px; font-weight: bold;\">");
		msg.append("				(*) Caso tenha alguma dúvida sobre os contratos entre em contato com e Exata pelo FONE: (0xx85)4009-1300.");
		msg.append("			</td>");
		msg.append("		</tr>		");
		msg.append("	</tbody>			");
		msg.append("	</table><br><br><br>");
		msg.append("</BODY></HTML>");
		
		try {
			
			if (entidade != null && entidade.getEncmail() != null){
				email.addTo(entidade.getEncmail(), entidade.getEncnome());
				email.setSubject("CONFIRMAÇÃO DE PROPOSTA ("+ contrato.getCtnnumr()+")");
				email.setHtmlMsg(msg.toString());
				enviar(true);
			}
			
		} catch (EmailException e) {
			e.printStackTrace();
		}	
		
	}
	public void enviarEmailRelatorioGC(){
		try{	
			PreparedStatement st = Banco.getConnection().prepareStatement("SELECT p.RET FROM SP_RELAT_REGIONAL_GC_HTML p");
			ResultSet rs = st.executeQuery();
			
			StringBuffer msg = new StringBuffer();
			msg.append("<pre style='font-size: 12px;'>");
			msg.append("<b>PRODUÇÃO CONSIGNADO EXTERNO</b><br>");
			msg.append("<b>DATA:"+new FormataObj().formataData(new Date())+"</b><br>");
			while (rs.next()) {
				msg.append(rs.getString(1));				
			}
			msg.append("</pre>");
			
			try {
				
				for (int i = 0;i < emailsComercial.length;i++) {		
					email.addTo(emailsComercial[i]);
				}
				
				//email.addTo("altairaquino@gmail.com");
				
				email.setSubject("Relatório Regional por GC "+new FormataObj().formataData(new Date()));
				email.setHtmlMsg(msg.toString());
				enviar(true);
				
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	public void enviarEmailRelatorioSinergia(){
		try{	
			PreparedStatement st = Banco.getConnection().prepareStatement("SELECT p.RET FROM SP_RELAT_PROD_SINERGIA p");
			ResultSet rs = st.executeQuery();
			
			StringBuffer msg = new StringBuffer();
			
			msg.append("<pre style='font-size: 12px;'>");
			msg.append("<b>PRODUÇÃO CONSIGNADO SINERGIA</b><br>");
			msg.append("<b>DATA:"+new FormataObj().formataData(new Date())+"</b><br>");
			while (rs.next()) {
				msg.append(rs.getString(1));
			}
			msg.append("</pre>");
			
			try {
				
				for (int i = 0;i < emailsSinergia.length;i++) {
					email.addTo(emailsSinergia[i]);
				}
				
				//email.addTo("altairaquino@gmail.com");
				
				email.setSubject("Relatório Sinergia "+new FormataObj().formataData(new Date()));
				email.setHtmlMsg(msg.toString());
				enviar(true);				
				
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}		
	}

	public void enviarEmailPorGrupo(String grupo, String titulo, String conteudo){
		try{
			
			StringBuffer msg = new StringBuffer();
			msg.append("<pre  style='font-size: 15px;'>"+conteudo);
			msg.append("<br>------------------------------------------------");
			msg.append("<br>Enviado pelo Sistema Exata em: "+new FormataObj().formataData(new Date()));
			msg.append("<br>http://www.exatagrupo.com.br</pre>");
			
			try {
				
				ArrayList<BeanListaEmail> lista = ModelListaEmail.getInstance().getListaEmailPorGrupoParaEnvio(Integer.parseInt(grupo));
				
				for (BeanListaEmail beanListaEmail : lista) {
					email.addTo(beanListaEmail.getLtecmail(), beanListaEmail.getLtecnome());
				}				
				
				email.setSubject(titulo);
				email.setHtmlMsg(msg.toString());
				enviar(true);
				
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public void enviarEmailJuridico(BeanLigacaoJuridico beanLigacaoJuridico){
		try{
			
			StringBuffer msg = new StringBuffer();
			msg.append("<pre  style='font-size: 15px;'>");
			msg.append("<b>Registro de ligação no sistema com pedido de envio por e-mail.</b><br>");
			msg.append("<br>Registrado por: "+beanLigacaoJuridico.getLgjcnmus());
			msg.append("<br>Contato: "+beanLigacaoJuridico.getLgjcnmct());
			msg.append("<br>----------------OCORRÊNCIA----------------------");
			msg.append("<br>"+beanLigacaoJuridico.getLgjmobsv());	
			msg.append("<br>------------------------------------------------");
			msg.append("<br>Enviado pelo Sistema Exata em: "+new FormataObj().formataData(new Date()));
			msg.append("<br>http://www.exatagrupo.com.br</pre>");
			
			try {
				
//				email.addTo("email.ligacoes.consignado@exatagrupo.com.br", "SQI Jurídico");
				
				email.setSubject("Registro de ligação - Sistema Exata");
				email.setHtmlMsg(msg.toString());
				enviar(true);
				
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	public void enviarEmailJuridicoDia(List<BeanLigacaoJuridico> ligacoes){
		try{
			
			StringBuffer msg = new StringBuffer();
			msg.append("<pre  style='font-size: 13px;'>");
			msg.append("<b>Registro de ligação no sistema com pedido de envio por e-mail.</b><br>");
			
			for (BeanLigacaoJuridico beanLigacaoJuridico : ligacoes) {
				msg.append("<br>Registrado por: <b>"+beanLigacaoJuridico.getLgjcnmus()+"</b>");
				msg.append("<br>Contato: <b>"+beanLigacaoJuridico.getLgjcnmct()+"</b>");
				msg.append("<br>Data: "+beanLigacaoJuridico.getLgjddata());
				msg.append("<br>----------------OCORRÊNCIA--------------------");
				msg.append("<br>"+beanLigacaoJuridico.getLgjmobsv());
				msg.append("<br>--------------------#####---------------------<br>");
			}
			
			msg.append("<br>---------FIM DAS MENSAGENS--------------------");
			msg.append("<br>Enviado pelo Sistema Exata em: "+new FormataObj().formataData(new Date()));
			msg.append("<br>http://www.exatagrupo.com.br</pre>");
			
			try {
				
				email.addTo("email.ligacoes.juridico@exatagrupo.com.br", "SQI Jurídico");
								
				email.setSubject("Registro de ligação - Sistema Exata");
				email.setHtmlMsg(msg.toString());
				
				if (!ligacoes.isEmpty()){
					enviar(true);
				}
				
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public void enviarEmailSqiDia(List<BeanLigacaoSqi> ligacoes){
		try{
			
			StringBuffer msg = new StringBuffer();
			msg.append("<pre  style='font-size: 13px;'>");
			msg.append("<b>Registro de ligação no sistema com pedido de envio por e-mail.</b><br>");
			
			for (BeanLigacaoSqi beanLigacaoSqi : ligacoes) {
				msg.append("<br>Registrado por: <b>"+beanLigacaoSqi.getLgscnmus()+"</b>");
				msg.append("<br>Contato: <b>"+beanLigacaoSqi.getLgscnmct()+"</b>");
				msg.append("<br>Data: "+beanLigacaoSqi.getLgsddata());
				msg.append("<br>----------------OCORRÊNCIA--------------------");
				msg.append("<br>"+beanLigacaoSqi.getLgsmobsv());
				msg.append("<br>--------------------#####---------------------<br>");
			}
			
			msg.append("<br>---------FIM DAS MENSAGENS--------------------");
			msg.append("<br>Enviado pelo Sistema Exata em: "+new FormataObj().formataData(new Date()));
			msg.append("<br>http://www.exatagrupo.com.br</pre>");
			
			try {
				
				
				email.addTo("email.ligacoes.consignado@exatagrupo.com.br", "SQI");
				
				email.setSubject("Registro de ligação - Sistema Exata");
				email.setHtmlMsg(msg.toString());
				
				if (!ligacoes.isEmpty()){
					enviar(true);
				}
				
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
	}

	public void enviarEmailSupeitaFraude(BeanEntidade agente){
		try{
			String sql = " SELECT a.COD_AGENTE, a.NOME_AGENTE, a.META, a.PROD_DIA, a.QUANTID, a.META_DIA, a.PERC_30, a.SUSPEITO " +
					     " FROM VW_PRODUCAO_SUSPEITA a" +
					     " WHERE A.COD_AGENTE = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(agente.getEnncodg()));
			
			ResultSet rs = st.executeQuery();
			
			if (rs.next()){
			
				FormataObj formata = new FormataObj();
				
				StringBuffer msg = new StringBuffer();
				msg.append("<pre  style='font-size: 15px;'>");
				msg.append("<br>Producao do Parceiro com suspeita de fraude.");
				msg.append("<br>NOME: "+agente.getEncnome()+".");
				msg.append("<br>META: R$ "+formata.formataValor(rs.getFloat("META"))+".");
				//msg.append("<br>DATA DE CADASTRO: "+agente.getEncnome()+".");
				msg.append("<br>PRODUÇÃO HOJE: R$ "+formata.formataValor(rs.getFloat("PROD_DIA"))+".");
				msg.append("<br>QUANTIDADE DE CONTRATOS DE HOJE: "+rs.getInt("QUANTID")+" Contrato(s).");
				msg.append("<br>------------------------------------------------");				
				msg.append("<br>Enviado pelo Sistema Exata em: "+formata.formataData(new Date()));
				msg.append("<br>http://www.exatagrupo.com.br</pre>");
				
				try {
					
					email.addTo("prevencao.fraudes@exatagrupo.com.br", "Prevenção Fraudes");
					
					email.setSubject("Atenção: Produção Suspeita de Fraude: "+agente.getEncnome());
					email.setHtmlMsg(msg.toString());
					enviar(true);
					
				} catch (EmailException e) {
					e.printStackTrace();
				}
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) {
		BeanEntidade e = new BeanEntidade();
		e.setEnncodg(("9817"));
		EnviarEmail.getInstance().enviarEmailSupeitaFraude(e);
	}

}
