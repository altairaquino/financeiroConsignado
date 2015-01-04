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
				&nbsp;   Listagem de Contratos    &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="5"> Lista de Contratos </th>
				</tr>
				<tr>
					<th colspan="5">
						<input type="button" value="Nova Pesquisa" onclick="window.location = 'contratoPesquisaCPF.do'">
					</th>
				</tr>				
				<tr>
					<th>
						&nbsp;
					</th>
					<th>
						Número
					</th>
					<th>
						Data
					</th>
					<th>
						Cliente
					</th>
					<th>
						Valor
					</th>					
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_contrato">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<input type="image" src="imagens/estrela.gif" title="Detalhes do Contrato" onclick="window.location = 'actionContrato.do?m=dados&ctncodg=<bean:write name="b" property="ctncodg"/>'">
					</td>
					<th>
						<bean:write name="b" property="ctnnumr"/>
					</th>
					<td>
						<bean:write name="b" property="ctdcadt"/>
					</td>
					<td>
						<bean:write name="b" property="ctcnmcl"/>
					</td>
					<th>
						<bean:write name="b" property="ctyvalr"/>
					</th>					
				</tr>
				</logic:iterate>
				<tr>
					<th colspan="5">
						<input type="button" value="Nova Pesquisa" onclick="window.location = 'contratoPesquisaCPF.do'">
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