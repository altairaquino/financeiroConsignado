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
				&nbsp;Pesquisa tabela&nbsp;
			</legend>
            <html:form action="actionTabela">
			<input type="hidden" name="m" value="pesquisa">
			<table style="width: 550px;">
			<tbody>
                <tr>
                    <td colspan="6" style="text-align: right;">
						<input type="text" name="param" value="">
					</td>
				</tr>
				<tr>
                    <td colspan="6" style="text-align: right;">
						<html:submit styleClass="btn_hot">Pesquisar</html:submit>
					</td>
				</tr>
                				<logic:present name="listaPesqtabela">
					<th>
						Resultado.
					</th>
				<tr>
					<th>
						TABNCODG
					</th>
					<th>
						TABNCGTTB
					</th>
					<th>
						TABNLIMT
					</th>
					<th>
						TABNPDES
					</th>
					<th>
						TABNPARC
					</th>
					<th>
						TABNXXX
					</th>
				</tr>
				<logic:empty name="listaPesqtabela">
					<th>
						 Não há tabela Cadastrado.
					</th>
				</logic:empty>
				<logic:notEmpty name="listaPesqtabela">
				<% int c = 0; %>
				<logic:iterate id="b001" name="listaPesqtabela">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
					<a href="actionTabela.do?m=editar&tabncodg=<bean:write name="tabela" property="tabncodg"/>">
						<bean:write name="b001" property="tabncodg"/>
					</a>
					</td>
					<td>
						<bean:write name="b001" property="tabncgttb"/>
					</td>
					<td>
						<bean:write name="b001" property="tabnlimt"/>
					</td>
					<td>
						<bean:write name="b001" property="tabnpdes"/>
					</td>
					<td>
						<bean:write name="b001" property="tabnparc"/>
					</td>
					<td>
						<bean:write name="b001" property="tabnxxx"/>
					</td>
				</tr>
				</logic:iterate>
				</logic:notEmpty>
				</logic:present>

				<tr>
					<td colspan="6">
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