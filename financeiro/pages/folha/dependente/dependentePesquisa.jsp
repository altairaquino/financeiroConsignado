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
				&nbsp;Pesquisa dependente&nbsp;
			</legend>
            <html:form action="actionDependente">
			<input type="hidden" name="m" value="pesquisa">
			<table style="width: 550px;">
			<tbody>
                <tr>
                    <td colspan="4" style="text-align: right;">
						<input type="text" name="param" value="">
					</td>
				</tr>
				<tr>
                    <td colspan="4" style="text-align: right;">
						<html:submit styleClass="btn_hot">Pesquisar</html:submit>
					</td>
				</tr>
                				<logic:present name="listaPesqdependente">
					<th>
						Resultado.
					</th>
				<tr>
					<th>
						DEPNCODG
					</th>
					<th>
						DEPNCGEN
					</th>
					<th>
						DEPCNOME
					</th>
					<th>
						DEPDNASC
					</th>
				</tr>
				<logic:empty name="listaPesqdependente">
					<th>
						 Não há dependente Cadastrado.
					</th>
				</logic:empty>
				<logic:notEmpty name="listaPesqdependente">
				<% int c = 0; %>
				<logic:iterate id="b001" name="listaPesqdependente">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
					<a href="actionDependente.do?m=editar&depncodg=<bean:write name="dependente" property="depncodg"/>">
						<bean:write name="b001" property="depncodg"/>
					</a>
					</td>
					<td>
						<bean:write name="b001" property="depncgen"/>
					</td>
					<td>
						<bean:write name="b001" property="depcnome"/>
					</td>
					<td>
						<bean:write name="b001" property="depdnasc"/>
					</td>
				</tr>
				</logic:iterate>
				</logic:notEmpty>
				</logic:present>

				<tr>
					<td colspan="4">
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