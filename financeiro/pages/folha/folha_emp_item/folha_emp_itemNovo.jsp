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
				&nbsp;Cadastro folha_emp_item&nbsp;
			</legend>
            <html:form action="actionFolhaEmpItem" onsubmit="return window.confirm('Confirmar a inclusão do folha_emp_item com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>
			<table style="width: 550px;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Cadastro folha_emp_item </th>
				</tr>
                				<tr>
					<td>
						FEINCGFOE
					</td>
					<td>
						<html:text property="feincgfoe"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						FEINCGDER
					</td>
					<td>
						<html:text property="feincgder"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						FEIYVALOR
					</td>
					<td>
						<html:text property="feiyvalor"></html:text>
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = '#adefinir'">
					</td>
				</tr>
                <tr>
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Cadastrar folha_emp_item</html:submit>
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
