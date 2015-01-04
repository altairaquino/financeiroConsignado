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
				&nbsp;Cadastro folha_emp (alteração)&nbsp;
			</legend>
            <html:form action="actionFolhaEmp" onsubmit="return window.confirm('Confirmar a inclusÃ£o do folha_emp com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>
            		<html:hidden property="foencodg"/>

			<table style="width: 550px;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Cadastro folha_emp (alteração) </th>
				</tr>
                				<tr>
					<td>
						FOENCODG
					</td>
					<td>
						<html:text property="foencodg" disabled="true"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						FOENCGEMP
					</td>
					<td>
						<html:text property="foencgemp" ></html:text>
					</td>
				</tr>
				<tr>
					<td>
						FOENCGFOL
					</td>
					<td>
						<html:text property="foencgfol" ></html:text>
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = '#adefinir'">
					</td>
				</tr>
                <tr>
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Cadastrar folha_emp</html:submit>
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
