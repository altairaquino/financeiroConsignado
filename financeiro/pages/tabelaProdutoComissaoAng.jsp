	<%@include file="topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   COMISSÕES DO AGENTE DE CRÉDITO POR TABELA PRODUTO    &nbsp;
			</legend>
			<center>
			
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="4"> 
						DETALHES DA TABELA DO PRODUTO
					</th>
				</tr>
				<tr>
					<td colspan="4" style="font-size: 14px;">
						Produto: <bean:write name="tabelaproduto" property="tpcdcpd"/> <br>
						Tabela: <bean:write name="tabelaproduto" property="tpcdesc"/> <br>
						Prazo: <bean:write name="tabelaproduto" property="tpnpraz"/> Meses<br>
						Carência: 
						<logic:equal name="tabelaproduto" property="tpncare" value="0">
							Sem Carência
						</logic:equal>
						<logic:notEqual name="tabelaproduto" property="tpncare" value="0">
							<bean:write name="tabelaproduto" property="tpncare"/>
						</logic:notEqual>
						
					</td>
				</tr>				
				<tr>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>				
				
				<html:form action="actionTabelaAngariador">
				<html:hidden property="m" value="updateComissao"/>
				
				<input type="hidden" name="pdncodg" value="<bean:write name="tabelaproduto" property="tpncgpd"/>">
				<tr>
					<td colspan="2">
						<input type="button" value="Voltar" class="btn_hot" onclick="window.location = 'actionTabelaProduto.do?m=lista&pdncodg=<bean:write name="tabelaproduto" property="tpncgpd"/>'">
					</td>
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Salva Comissões</html:submit>
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">
						Cód. Ang.
					</th>
					<th>
						Agente de Crédito
					</th>
					<th>
						Líquido Exata
					</th>
					<th>
						Comissão
					</th>					
				</tr>				
				<logic:empty name="ls_tabelaangariador">
				<tr>
					<td colspan="4" style="color: red;">
						NÃO HÁ TABELA CADASTRADA
					</td>
				</tr>				
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_tabelaangariador">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<html:hidden name="b" property="tgncodg"/>
					<th  style="text-align: center;">
						<bean:write name="b" property="tgncgen"/>
					</th>
					<th>
						<bean:write name="b" property="tgcnmen"/>
					</th>
					<th  style="text-align: right;">
						<bean:write name="b" property="tgnliqu"/>%
					</th>
					<th  style="text-align: right;">
						<input type="text" name="tgncoms" onkeydown="Formata(this,8,event,2)" style="text-align: right;" size="8" maxlength="8" value="<bean:write name="b" property="tgncoms"/>" onfocus="if (this.value == '0,00'){this.value = '';}">%			
					</th>		
				</tr>
				</logic:iterate>
				
				<tr>
					<td colspan="2">
						<input type="button" value="Voltar" class="btn_hot" onclick="window.location = 'actionTabelaProduto.do?m=lista&pdncodg=<bean:write name="tabelaproduto" property="tpncgpd"/>'">
					</td>
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Salva Comissões</html:submit>
					</td>
				</tr>	
				</html:form>
			</tbody>			
			</table>
			</center>			
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>