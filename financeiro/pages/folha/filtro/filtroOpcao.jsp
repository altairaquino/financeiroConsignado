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
		<fieldset>
			<legend>
				<a href="actionFiltro.do?m=lista">Filtro de ajuda de custo</a>&nbsp;>&nbsp;
			<bean:write name="filtro" property="filcdesc"/>
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<td>Dados Básicos</td>
					<td>											
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionFiltro.do?m=editar&filncodg=<bean:write name="filtro" property="filncodg"/>'">		
					</td>
				</tr>
				<tr>
					<td>Funcionários</td>
					<td>											
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionFiltroajcust.do?m=lista&filncodg=<bean:write name="filtro" property="filncodg"/>'">		
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
