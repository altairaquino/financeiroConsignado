<%@include file="/pages/topo.jsp" %>
<script type="text/javascript" src="scripts/jquery.js"></script>
</head>
<body>
	<div id="container">
		<%@include file="/pages/menu.jsp" %>
		<%@include file="/pages/header.jsp" %>
		<div id="content">
		<html:errors/>
		<logic:present name="msg">
			<font style="color: red; font-weight: bold;"><bean:write name="msg"/></font>
		</logic:present>
		<fieldset>
			<legend>
			<a href="actionFolha.do?m=lista">Folha</a>&nbsp;>&nbsp;
				<bean:write name="folha" property="foldmes"/>&nbsp;|&nbsp;
				Nº <bean:write name="folha" property="folnnum"/>&nbsp;|&nbsp;
				<bean:write name="folha" property="folcdesc"/>&nbsp;|&nbsp;
				<bean:write name="folha" property="epcnome"/>
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<td>Dados Básicos</td>
					<td>											
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionFolha.do?m=editar&folncodg=<bean:write name="folha" property="folncodg"/>'">		
					</td>
				</tr>
				<tr>
					<td>Funcionários</td>
					<td>											
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionFolhaEmp.do?m=lista&folncodg=<bean:write name="folha" property="folncodg"/>'">		
					</td>
				</tr>
				<tr>
					<td>Relatórios</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionFolha.do?m=montarRelatorio&folncodg=<bean:write name="folha" property="folncodg"/>'">		
					</td>
				</tr>
				<tr>
					<td>Regerar folha</td>
					<td>											
						<input type="button" class="btn_hot" value="Regerar" onclick="window.location = 'actionFolha.do?m=regerar&folncodg=<bean:write name="folha" property="folncodg"/>'">		
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionFolha.do?m=lista'">
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
