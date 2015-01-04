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
				&nbsp;  Relação de Fornecedores &nbsp;
			</legend>
			<table  style="width: 100%">
			<tbody>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Novo" onclick="window.location = 'actionEntidade.do?m=novoFornecedor'">&nbsp;&nbsp;
						<input type="button" class="btn_hot" value="Pesquisa" onclick="window.location = 'fornecedorPesquisa.do'">
					</td>
				</tr>		
				<tr>
					<th>Relação de Fornecedores</th>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table  style="width: 100%">
			<tbody>
				<tr>
					<th style="background-color: #DDD; font-weight: bold; text-align: center;" colspan="4"> 
						Relação de Fornecedores
					</th>
				</tr>
				<tr style="background-color: #DDD; font-weight: bold;">
					<th>
						Código
					</th>
					<th>
						Nome
					</th>
					<th>
						CPF/NCPJ
					</th>
					<th>
						Data de Nascimento
					</th>
				</tr>
				<logic:iterate name="ls_entidade" id="b">
				<tr>
					<td>
						<bean:write name="b" property="enncodg"/>
					</td>
					<td>
						<a href="actionEntidade.do?m=opcoesFornecedor&enncodg=<bean:write name="b" property="enncodg"/>">
							<bean:write name="b" property="encnome"/>
						</a>						
					</td>
					<td>
						<bean:write name="b" property="encdocm"/>
					</td>
					<td>
						<bean:write name="b" property="endnasc"/>
					</td>
				</tr>
				</logic:iterate>							
			</tbody>			
			</table>
			</div>
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>