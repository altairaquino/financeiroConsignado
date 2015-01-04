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
				&nbsp;Dados Cadastrais ocupacao&nbsp;
			</legend>
			<table style="width: 550px;">
			<tbody>
				<tr>
					<td style="text-align: right;" colspan="2">											
						<input type="button" class="btn_hot" value="Editar ocupacao" onclick="window.location = 'actionOcupacao.do?m=dados&ocpncodg=<bean:write name="ocupacao" property="ocpncodg"/>'">
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Dados Cadastrais ocupacao </th>
				</tr>
                				<tr>
					<td>
						OCPNCODG
					</td>
					<td>
						<bean:write name="ocupacao" property="ocpncodg"/>
					</td>
				</tr>
				<tr>
					<td>
						OCPCCODG
					</td>
					<td>
						<bean:write name="ocupacao" property="ocpccodg"/>
					</td>
				</tr>
				<tr>
					<td>
						OCPCTITULO
					</td>
					<td>
						<bean:write name="ocupacao" property="ocpctitulo"/>
					</td>
				</tr>
				<tr>
					<td>
						OCPNCGFAM
					</td>
					<td>
						<bean:write name="ocupacao" property="ocpncgfam"/>
					</td>
				</tr>
				<tr>
					<td>
						OCPCCGFAM
					</td>
					<td>
						<bean:write name="ocupacao" property="ocpccgfam"/>
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
