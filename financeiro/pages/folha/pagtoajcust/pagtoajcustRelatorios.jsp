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
				<a href="actionPagtoajcust.do?m=lista">Pagamento de ajuda de custo</a>&nbsp;>&nbsp;
				<a href="actionPagtoajcust.do?m=opcao&pacncodg=<bean:write name="pagtoajcust" property="pacncodg"/>">
			<bean:write name="pagtoajcust" property="pacdsem"/>
				</a>
			&nbsp;>&nbsp;
			Relatórios
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<td><a href="actionPagtoajcust.do?m=relatorio&pacncodg=<bean:write name="pagtoajcust" property="pacncodg"/>">Resumo de pagamento</a></td>
				</tr>
				<tr>
					<td><a href="actionPagtoajcust.do?m=relatorio&pacncodg=<bean:write name="pagtoajcust" property="pacncodg"/>&tipo=2">Resumo de pagamento - Documento Bancário</a></td>
				</tr>
				<tr>
					<td><a href="actionPagtoajcust.do?m=relatorio&pacncodg=<bean:write name="pagtoajcust" property="pacncodg"/>&tipo=1">Resumo de pagamento - sem conta bancaria</a></td>
				</tr>
			</tbody>			
			</table>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>
