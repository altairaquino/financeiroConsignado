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
				&nbsp;   TABELA DE COMISSÕES DO FUNCIONÁRIO POR PRODUTO    &nbsp;
			</legend>
			<center>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="6"> 
						TABELA DE COMISSÕES DO FUNCIONÁRIO POR PRODUTO
					</th>
				</tr>
				<tr>
					<td colspan="6" style="font-size: 14px;">
						FUNCIONÁRIO: <B><bean:write name="funcionarioagencia" property="fcacnmfc"/> - <bean:write name="funcionarioagencia" property="fcacdctfa"/></B><br>
						META PARA COMISSÃO: <B><bean:write name="funcionarioagencia" property="fcanmeta"/> %</B><br>
						AGÊNCIA/Agente de Crédito: <B><bean:write name="funcionarioagencia" property="fcacnmag"/></B>						
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<input type="button" value="Voltar" class="btn_hot" onclick="window.location = 'actionFuncionarioAgencia.do?m=lista&enncodg=<bean:write name="funcionarioagencia" property="fcancgag"/>'">	
					</td>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%;">
			<tbody>
				<tr style="background: #D00; color: white; border-style: dotted;">
					<th style="font-size: 10px;" rowspan="2">
						Produto
					</th>
					<th style="font-size: 10px;" rowspan="2">
						Tabela
					</th>
					<th style="font-size: 10px;" rowspan="2">
						Prazo
					</th>
					<th style="font-size: 10px; text-align: center;" colspan="4">
						Comissões
					</th>					
				</tr>				
				<tr style="background: #D00; color: white; border-style: dotted;">
					<th style="font-size: 10px; text-align: center;">
						90-100%
					</th>					
					<th style="font-size: 10px; text-align: center;">
						100-110%
					</th>					
					<th style="font-size: 10px; text-align: center;">
						110-120%
					</th>					
					<th style="font-size: 10px; text-align: center;">
						+120%
					</th>					
				</tr>	
				<logic:empty name="ls_tabelafuncionario">
				<tr>
					<td colspan="6" style="color: red;font-size: 10px;">
						NÃO HÁ TABELA PARA ESTE FUNCIONÁRIO&nbsp;&nbsp;&nbsp;
						<input type="button" value="Gerar Tabela" onclick="">
					</td>
				</tr>				
				</logic:empty>
				<html:form action="actionTabelaFuncionario" focusIndex="0">
				<html:hidden property="m" value="update"/>
				<input type="hidden" name="tfcncgfca" value="<bean:write name="funcionarioagencia" property="fcancgag"/>">
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_tabelafuncionario">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<html:hidden name="b" property="tfcncodg"/>
					<th style="font-size: 10px;">
						<bean:write name="b" property="tfccdcpd"/>
					</th>											
					<th style="font-size: 10px;">
						<bean:write name="b" property="tfccnmtp"/> - <bean:write name="b" property="tfccdctp"/>
					</th>
					<th style="font-size: 10px;">
						<bean:write name="b" property="tfcnpraz"/> meses
					</th>					
					<th style="font-size: 10px;">
						<input type="text" name="tfcncoms" onkeydown="Formata(this,8,event,2)" style="text-align: right;" size="8" maxlength="8" value="<bean:write name="b" property="tfcncoms"/>" onfocus="if (this.value == '0,00'){this.value = '';}">			
					</th>
					<th style="font-size: 10px;">
						<input type="text" name="tfcncm70" onkeydown="Formata(this,8,event,2)" style="text-align: right;" size="8" maxlength="8" value="<bean:write name="b" property="tfcncm70"/>" onfocus="if (this.value == '0,00'){this.value = '';}">			
					</th>		
					<th style="font-size: 10px;">
						<input type="text" name="tfcncm100" onkeydown="Formata(this,8,event,2)" style="text-align: right;" size="8" maxlength="8" value="<bean:write name="b" property="tfcncm100"/>" onfocus="if (this.value == '0,00'){this.value = '';}">			
					</th>		
					<th style="font-size: 10px;">
						<input type="text" name="tfcncm150" onkeydown="Formata(this,8,event,2)" style="text-align: right;" size="8" maxlength="8" value="<bean:write name="b" property="tfcncm150"/>" onfocus="if (this.value == '0,00'){this.value = '';}">			
					</th>		
				</tr>
				</logic:iterate>
				<tr>
					<td colspan="6" style="text-align: right;">
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