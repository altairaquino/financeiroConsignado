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
				&nbsp;Pagamento de Ajuda de Custo&nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="4" style="text-align: center; background-color: #DDDDDD;">  Pagamento de Ajuda de Custo </th>
				</tr>
				<tr>				
					<td colspan="4">
						<input type="button" class="btn_hot" value="Novo" onclick="window.location = 'actionPagtoajcust.do?m=novo'">
					</td>
				</tr>
                <tr>
					<th>
						Pagamento
					</th>
					<th>
						Inicio da semana
					</th>
					<th>
						Tipo ajuda de custo
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				<logic:empty name="listaPagtoajcust">
					<th colspan="4">
						 Não há Pagamento de Ajuda de Custo Cadastrado.
					</th>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b001" name="listaPagtoajcust">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b001" property="pacdpagto"/>&nbsp;&nbsp;
					</td>
					<td>
						<bean:write name="b001" property="pacdsem"/>&nbsp;&nbsp;
					</td>
					<td>
						<bean:write name="b001" property="paccdctac"/>&nbsp;&nbsp;
					</td>
					<td>
					&nbsp;&nbsp;
						<a href="actionPagtoajcust.do?m=opcao&pacncodg=<bean:write name="b001" property="pacncodg"/>">
							Visualizar
						</a>
					</td>
				</tr>
				</logic:iterate>
			</tbody>
			</table>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>
