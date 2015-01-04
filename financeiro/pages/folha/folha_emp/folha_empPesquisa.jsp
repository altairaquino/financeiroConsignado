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
				&nbsp;Pesquisa folha_emp&nbsp;
			</legend>
            <html:form action="actionFolhaEmp">
			<input type="hidden" name="m" value="pesquisa">
			<table style="width: 550px;">
			<tbody>
                <tr>
                    <td colspan="3" style="text-align: right;">
						<input type="text" name="param" value="">
					</td>
				</tr>
				<tr>
                    <td colspan="3" style="text-align: right;">
						<html:submit styleClass="btn_hot">Pesquisar</html:submit>
					</td>
				</tr>
                				<logic:present name="listaPesqfolha_emp">
					<th>
						Resultado.
					</th>
				<tr>
					<th>
						FOENCODG
					</th>
					<th>
						FOENCGEMP
					</th>
					<th>
						FOENCGFOL
					</th>
				</tr>
				<logic:empty name="listaPesqfolha_emp">
					<th>
						 Não há folha_emp Cadastrado.
					</th>
				</logic:empty>
				<logic:notEmpty name="listaPesqfolha_emp">
				<% int c = 0; %>
				<logic:iterate id="b001" name="listaPesqfolha_emp">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
					<a href="actionFolhaEmp.do?m=editar&foencodg=<bean:write name="folha_emp" property="foencodg"/>">
						<bean:write name="b001" property="foencodg"/>
					</a>
					</td>
					<td>
						<bean:write name="b001" property="foencgemp"/>
					</td>
					<td>
						<bean:write name="b001" property="foencgfol"/>
					</td>
				</tr>
				</logic:iterate>
				</logic:notEmpty>
				</logic:present>

				<tr>
					<td colspan="3">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = '#adefinir'">
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