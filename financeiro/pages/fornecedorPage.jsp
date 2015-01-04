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
				&nbsp;   Opções do Fornecedor > <bean:write name="fornecedor" property="encnome"/>   &nbsp;
			</legend>
			<h3><bean:write name="fornecedor" property="encnome"/> </h3>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; text-align: center;">
						Opções do Fornecedor 
					</th>
				</tr>
				<tr>
					<td>
						Dados Cadastrais do Fornecedor
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionEntidade.do?m=dadosFornecedor&enncodg=<bean:write name="fornecedor" property="enncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Contas Bancárias
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionContaCorrente.do?m=listaFornec&enncodg=<bean:write name="fornecedor" property="enncodg"/>'">
					</td>
				</tr>				
				<tr>
					<td>
						Últimos Pagamentos Recebidos
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionMovCaixa.do?m=listaDoFornecedor&enncodg=<bean:write name="fornecedor" property="enncodg"/>'">
					</td>
				</tr>				
			</tbody>			
			</table>	
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>