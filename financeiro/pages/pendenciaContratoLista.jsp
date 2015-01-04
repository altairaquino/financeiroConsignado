	<%@include file="topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend class="red">
				&nbsp;   Listagem de Pendências do Contrato    &nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="3"> Lista de Pendências do Contrato </th>
				</tr>
				<tr>
					<th colspan="3">
						<input type="button" value="Nova Pendência" onclick="window.location = 'actionPendenciaContrato.do?m=novo'">
					</th>
				</tr>
				<logic:notEmpty name="ls_pendenciacontrato">
				<tr>
					<th>
						Pendência
					</th>
					<th>
						Data
					</th>
					<th>
						Resolvida?
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				</logic:notEmpty>
				<logic:empty name="ls_pendenciacontrato">
				<tr>
					<td colspan="3" style="color: red; font-weight: bold;">
						Não há pendências para este contrato.
					</td>
				</tr>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_pendenciacontrato">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="pecdctd"/>
					</td>
					<td>
						<bean:write name="b" property="peddata"/>
					</td>
					<td>
						<bean:write name="b" property="pelresv"/>
					</td>
					<td>
						<input type="image" src="imagens/delete.gif" title="Visualizar Tabelas" onclick="window.location = 'actionTabelaProduto.do?m=lista&pdncodg=<bean:write name="b" property="pencodg"/>'">						
					</td>
				</tr>
				</logic:iterate>
				<tr>
					<th colspan="3">
						<input type="button" value="Nova Pendência" onclick="window.location = 'actionPendenciaContrato.do?m=novo'">
					</th>
				</tr>				
			</tbody>			
			</table>		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>