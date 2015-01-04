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
				&nbsp;dependente > <bean:write name="empregado" property="encnome"/>  &nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="4" style="text-align: center; background-color: #DDDDDD;">  dependente </th>
				</tr>
				<tr>
					<th colspan="4">
						<input type="button" class="btn_hot" value="Novo" onclick="window.location = 'actionDependente.do?m=novo&enncodg=<bean:write name="empregado" property="enncodg"/>'">
					</th>
				</tr>
                <tr>
					<th>
						Nome
					</th>
					<th>
						Data de Nasc.
					</th>
				</tr>
				<logic:empty name="listadependente">
					<th>
						 Não há dependente Cadastrado.
					</th>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b001" name="listadependente">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<a href="actionDependente.do?m=editar&depncodg=<bean:write name="b001" property="depncodg"/>">
						<bean:write name="b001" property="depcnome"/>
						</a>
					</td>
					<td style="width: 100px;">
						<bean:write name="b001" property="depdnasc"/>
					</td>
				</tr>
				</logic:iterate>

				<tr>
					<td colspan="4">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionEntidade.do?m=opcoesFuncionario&enncodg=<bean:write name="empregado" property="enncodg"/>'">
					</td>
				</tr>
			</tbody>
			</table>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>
