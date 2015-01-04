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
				&nbsp;   TABELA DE COMISSÕES DO Agente de Crédito POR PRODUTO    &nbsp;
			</legend>
			<center>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="5">
						TABELA DE COMISSÕES DO Agente de Crédito POR PRODUTO
					</th>
				</tr>
				<tr>
					<td colspan="5" style="font-size: 14px;">
						Agente de Crédito: <B><bean:write name="angariador" property="encnome"/></B><br>
						C.P.F.: <B><bean:write name="angariador" property="encdocm"/></B>
					</td>
				</tr>
				<tr>
					<td colspan="5">
						&nbsp;
					</td>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th style="font-size: 10px;">
						Produto
					</th>
					<th style="font-size: 10px;">
						Tabela
					</th>
					<th style="font-size: 10px;">
						Prazo
					</th>
					<th style="font-size: 10px;">
						Carência
					</th>
					<th style="font-size: 10px;">
						Líquido Exata
					</th>
					<th style="font-size: 10px;">
						Comissão
					</th>					
				</tr>				
				<logic:empty name="ls_tabela_angariador">
				<tr>
					<td colspan="5" style="color: red;font-size: 10px;">
						NÃO HÁ TABELA PARA ESTE Agente de Crédito
					</td>
				</tr>				
				</logic:empty>
				<html:form action="actionTabelaAngariador" focusIndex="0">
				<html:hidden property="m" value="update"/>
				<input type="hidden" name="tgncgen" value="<bean:write name="angariador" property="enncodg"/>">
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_tabela_angariador">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<html:hidden name="b" property="tgncodg"/>
					<th style="font-size: 10px;">
						<bean:write name="b" property="tgcdcpd"/>
					</th>											
					<th style="font-size: 10px;">
						<bean:write name="b" property="tgcnmtp"/> - <bean:write name="b" property="tgcdctp"/>
					</th>
					<th style="font-size: 10px;">
						<bean:write name="b" property="tgnpraz"/> meses
					</th>
					<th style="font-size: 10px;">
						<bean:write name="b" property="tgncare"/> dias
					</th>
					<th style="font-size: 10px;">
						<bean:write name="b" property="tgnliqu"/> %
					</th>
					<th style="font-size: 10px;">
						<input type="text" name="tgncoms" onkeydown="Formata(this,8,event,2)" style="text-align: right;" size="8" maxlength="8" value="<bean:write name="b" property="tgncoms"/>" onfocus="if (this.value == '0,00'){this.value = '';}">			
					</th>		
				</tr>
				</logic:iterate>
				<tr>
					<td colspan="3">
						<input type="button" value="Voltar" class="btn_hot" onclick="window.location = 'actionEntidade.do?m=opcoesAngariador&enncodg=<bean:write name="angariador" property="enncodg"/>'">
					</td>
					<td colspan="3" style="text-align: right;">
						<html:submit styleClass="btn_hot">Salva Comissões</html:submit>
					</td>
				</tr>		
				</html:form>
			</tbody>			
			</table>
			</div>
			</center>			
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>