<%@include file="/pages/topo.jsp" %>
<script type="text/javascript" src="scripts/jquery.js"></script>
</head>
<body>
	<div id="container">
		<%@include file="/pages/menu.jsp" %>
		<%@include file="/pages/header.jsp" %>
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp; Relação de Empregados&nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="4" style="text-align: center; background-color: #DDDDDD;">  Relação de Empregados </th>
				</tr>
				<tr>
					<td colspan="4">
						<input type="button" class="btn_hot" value="Novo" onclick="window.location = 'actionEntidade.do?m=novoFuncionario'">&nbsp;&nbsp;
						<input type="button" class="btn_hot" value="Pesquisa" onclick="window.location = 'empregadoPesquisa.do'">&nbsp;&nbsp;
						<input type="button" class="btn_hot" value="Relatório de Aniversariantes" onclick="window.location = 'actionParametro.do?m=relatorio&tipo=5'">
					</td>
				</tr>
				<logic:notEmpty name="listaempregado_aniv">
				<tr>
					<th colspan="4" style="text-align: center; background-color: #DDDDDD;">
						Próximos Aniversários
					</th>
				</tr>
				<tr>
					<th>
						Código				
					</th>
					<th>
						CPF
					</th>
					<th>
						Nome
					</th>
					<th>
						Data de Nascimento
					</th>					
				</tr>
				<logic:iterate id="emp" name="listaempregado_aniv">
				<tr style="background-color: teal; font-weight: bold; color: yellow;">
					<td>
						<bean:write name="emp" property="empncgen"/>
					</td>
					<td>
						<bean:write name="emp" property="empccpf"/>
					</td>
					<td>
						<bean:write name="emp" property="empcnmen"/>		
					</td>
					<td>
						<bean:write name="emp" property="empdnasc"/>
					</td>				
				</tr>
				</logic:iterate>
				</logic:notEmpty>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%">
			<tbody>
                <tr>
					<th>
						Código						
					</th>
					<th>
						CPF
					</th>
					<th>
						Login
					</th>
					<th>
						Nome
					</th>														
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b001" name="listaempregado">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b001" property="enncodg"/>
					</td>
					<td>
						<bean:write name="b001" property="encdocm"/>
					</td>
					<td>
						<bean:write name="b001" property="enclogn"/>
					</td>
					<td>
						<a href="actionEntidade.do?m=opcoesFuncionario&enncodg=<bean:write name="b001" property="enncodg"/>">
							<bean:write name="b001" property="encnome"/>
						</a>
					</td>					
				</tr>
				</logic:iterate>
				
			</tbody>
			</table>
			</div>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>
