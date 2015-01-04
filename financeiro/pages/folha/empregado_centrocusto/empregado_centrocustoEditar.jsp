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
				&nbsp;Cadastro empregado_centrocusto (alteração)&nbsp;
			</legend>
            <html:form action="actionEmpregadoCentrocusto" onsubmit="return window.confirm('Confirmar a inclusão do empregado_centrocusto com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>
            		<html:hidden property="eccncodg"/>

			<table style="width: 550px;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Cadastro empregado_centrocusto (alteração) </th>
				</tr>
                				<tr>
					<td>
						ECCNCODG
					</td>
					<td>
						<html:text property="eccncodg" disabled="true"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						ECCNCGEMP
					</td>
					<td>
						<html:text property="eccncgemp" ></html:text>
					</td>
				</tr>
				<tr>
					<td>
						ECCNCGCR
					</td>
					<td>
						<html:text property="eccncgcr" ></html:text>
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = '#adefinir'">
					</td>
				</tr>
                <tr>
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Cadastrar empregado_centrocusto</html:submit>
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
