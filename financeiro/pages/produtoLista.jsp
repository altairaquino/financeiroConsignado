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
				&nbsp;   Listagem de Conv�nios    &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="5"> Lista de Conv�nios Cadastrados </th>
				</tr>
				<tr>
					<th colspan="5">
						<input type="button" class="btn_hot" value="Novo Conv�nio" onclick="window.location = 'actionProduto.do?m=novo'">
					</th>
				</tr>
				<tr>
					<!-- 
					<th>
						Banco
					</th>
					 -->
					<th>
						N�mero
					</th>
					<th>
						Nome do Conv�nio
					</th>
					<th>
						Abrevia��o
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_produto">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<%-- 
					<td>
						<bean:write name="b" property="pdcdcbc"/>
					</td>
					--%>
					<td style="text-align: center; font-weight: bold;">
						<bean:write name="b" property="pdnnumr"/>
					</td>
					<td>
						<bean:write name="b" property="pdcdesc"/>
					</td>
					<td>
						<bean:write name="b" property="pdcabrv"/>
					</td>
					<td>
						<input type="image" src="imagens/tabela.jpg" title="Tabelas" onclick="window.location = 'actionTabelaProduto.do?m=lista&pdncodg=<bean:write name="b" property="pdncodg"/>'">
						<input type="image" src="imagens/editar.gif" title="Editar Conv�nio" onclick="window.location = 'actionProduto.do?m=editar&pdncodg=<bean:write name="b" property="pdncodg"/>'">
					</td>
				</tr>
				</logic:iterate>
				<tr>
					<th colspan="5">
						<input type="button" class="btn_hot" value="Novo Conv�nio" onclick="window.location = 'actionProduto.do?m=novo'">&nbsp;
						<input type="button" class="btn_hot" value="Relat�rio Geral" onclick="window.location = 'actionProduto.do?m=relatorioGeral'">
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