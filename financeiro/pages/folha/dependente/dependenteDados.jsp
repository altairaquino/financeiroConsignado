<%@include file="topo.jsp" %>
<script type="text/javascript" src="scripts/jquery.js"></script>
</head>
<body>
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;Dados Cadastrais dependente&nbsp;
			</legend>
			<table style="width: 550px;">
			<tbody>
				<tr>
					<td style="text-align: right;" colspan="2">											
						<input type="button" class="btn_hot" value="Editar dependente" onclick="window.location = 'actionDependente.do?m=dados&depncodg=<bean:write name="dependente" property="depncodg"/>'">
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Dados Cadastrais dependente </th>
				</tr>
                				<tr>
					<td>
						DEPNCODG
					</td>
					<td>
						<bean:write name="dependente" property="depncodg"/>
					</td>
				</tr>
				<tr>
					<td>
						DEPNCGEN
					</td>
					<td>
						<bean:write name="dependente" property="depncgen"/>
					</td>
				</tr>
				<tr>
					<td>
						DEPCNOME
					</td>
					<td>
						<bean:write name="dependente" property="depcnome"/>
					</td>
				</tr>
				<tr>
					<td>
						DEPDNASC
					</td>
					<td>
						<bean:write name="dependente" property="depdnasc"/>
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = '#adefinir'">
					</td>
				</tr>
			</tbody>			
			</table>
		</fieldset>
		</div>
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>
