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
				&nbsp;<a href="actionDescRend.do?m=lista">Descontos e Rendimentos</a>&nbsp;>&nbsp;<bean:write name="desc_rend" property="dercdesc"/>&nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
					<tr>
					<td>
						Dados Básicos
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionDescRend.do?m=editar&derncodg=<bean:write name="desc_rend" property="derncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Incidência
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionItemDescRend.do?m=lista&idrncgder=<bean:write name="desc_rend" property="derncodg"/>'">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionDescRend.do?m=lista'">
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
