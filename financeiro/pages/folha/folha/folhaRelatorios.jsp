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
			<a href="actionFolha.do?m=lista">Folha</a>&nbsp;>&nbsp;
			<a href="actionFolha.do?m=opcao&folncodg=<bean:write name="folha" property="folncodg"/>">
				<bean:write name="folha" property="foldmes"/>&nbsp;|&nbsp;
				Nº <bean:write name="folha" property="folnnum"/>&nbsp;|&nbsp;
				<bean:write name="folha" property="folcdesc"/>&nbsp;|&nbsp;
				<bean:write name="folha" property="epcnome"/>
			</a>&nbsp;>&nbsp;
			Relatórios
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<td><a href="actionFolha.do?m=relatorio&folncodg=<bean:write name="folha" property="folncodg"/>">Funcionários - salário líquido</a></td>
				</tr>
				<tr>
					<td><a href="actionFolha.do?m=relatorio&folncodg=<bean:write name="folha" property="folncodg"/>&tipo=2">Funcionários - Centro de custo</a></td>
				</tr>
				<tr>
					<td><a href="actionFolha.do?m=relatorio&folncodg=<bean:write name="folha" property="folncodg"/>&tipo=1">Funcionários - salário líquido - Centro de custo</a></td>
				</tr>
			</tbody>			
			</table>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>
