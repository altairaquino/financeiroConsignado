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
				&nbsp;  <bean:write name="angariador" property="encnome"/> > LISTA DE CONTRATOS PENDENTE DE FÍSICOS    &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<td colspan="5">
						<input type="button" value="Voltar" onclick="window.location = 'actionEntidade.do?m=opcoesAngariador&enncodg=<bean:write name="angariador" property="enncodg"/>'">
					</td>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%">
			<tbody>
				<logic:notEmpty name="ls_contrato">
				<tr>
					<th>
						Número
					</th>
					<th>
						C.P.F.
					</th>
					<th>
						Cliente
					</th>
					<th>
						Data
					</th>
					<th>
						Valor
					</th>
					<th>
						Status
					</th>
				</tr>		
				</logic:notEmpty>		
				<logic:empty name="ls_contrato">
				<tr>
					<td colspan="5" style="color: red;"> 
						NÃO HÁ CONTRATOS PENDENTES DE FÍSICO PARA ESTE Agente de Crédito
					</td>
				</tr>				
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_contrato">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td> 
						<bean:write name="b" property="ctnnumr"/>
					</td>
					<td>
						<bean:write name="b" property="ctccpf"/>
					</td>
					<td> 
						<bean:write name="b" property="ctcnmcl"/>
					</td>
					<td> 
						<bean:write name="b" property="ctdcadt"/>
					</td>
					<td> 
						R$ <bean:write name="b" property="ctyvalr"/> 
					</td>
					<td>
						<bean:write name="b" property="ctcdcsc"/>
					</td>					
				</tr>
				</logic:iterate>
				<tr>
					<td colspan="5" style="font-weight: bold;">
						Total de Contratos sem físico: <%= c %> Contratos
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<input type="button" value="Voltar" onclick="window.location = 'actionEntidade.do?m=opcoesAngariador&enncodg=<bean:write name="angariador" property="enncodg"/>'">
					</td>
				</tr>
			</tbody>			
			</table>	
			</div>
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>