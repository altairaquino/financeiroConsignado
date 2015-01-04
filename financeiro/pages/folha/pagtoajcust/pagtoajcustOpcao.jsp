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
			<bean:write name="pagtoajcust" property="pacdsem"/>
			</legend>
			<logic:present name="msg">
		  	<br><font style="color: red; font-weight: bold;"><bean:write name="msg"/></font>
		  	</logic:present>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<td>Dados Básicos</td>
					<td>											
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionPagtoajcust.do?m=editar&pacncodg=<bean:write name="pagtoajcust" property="pacncodg"/>'">		
					</td>
				</tr>
				<tr>
					<td>Funcionários</td>
					<td>											
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionPagtoajcustemp.do?m=lista'">		
						<input type="button" class="btn_hot" value="Regerar" onclick="window.location = 'actionPagtoajcust.do?m=regerar&pacncodg=<bean:write name="pagtoajcust" property="pacncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>Relatórios</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionPagtoajcust.do?m=montarRelatorio&folncodg=<bean:write name="pagtoajcust" property="pacncodg"/>'">		
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
