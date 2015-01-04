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
				&nbsp;Dados Cadastrais tipotabela > <bean:write name="tipotabela" property="ttbcdesc"/>&nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<td>
						Dados Básicos
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionTipotabela.do?m=editar&ttbncodg=<bean:write name="tipotabela" property="ttbncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Parâmetros
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionTabela.do?m=lista&tabncgttb=<bean:write name="tipotabela" property="ttbncodg"/>'">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionTipotabela.do?m=lista'">
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
