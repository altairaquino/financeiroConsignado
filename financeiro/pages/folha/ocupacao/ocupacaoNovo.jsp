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
				&nbsp;Cadastro ocupacao&nbsp;
			</legend>
            <html:form action="actionOcupacao" onsubmit="return window.confirm('Confirmar a inclusão do ocupacao com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>
			<table style="width: 550px;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Cadastro ocupacao </th>
				</tr>
                				<tr>
					<td>
						OCPCCODG
					</td>
					<td>
						<html:text property="ocpccodg"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						OCPCTITULO
					</td>
					<td>
						<html:text property="ocpctitulo"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						OCPNCGFAM
					</td>
					<td>
						<html:text property="ocpncgfam"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						OCPCCGFAM
					</td>
					<td>
						<html:text property="ocpccgfam"></html:text>
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = '#adefinir'">
					</td>
				</tr>
                <tr>
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Cadastrar ocupacao</html:submit>
					</td>
				</tr>
			</tbody>			
			</table>
            </html:form>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>
