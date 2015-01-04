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
				&nbsp;Pesquisa empregado_centrocusto&nbsp;
			</legend>
            <html:form action="actionEmpregadoCentrocusto">
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
                				<logic:present name="listaPesqempregado_centrocusto">
					<th>
						Resultado.
					</th>
				<tr>
					<th>
						ECCNCODG
					</th>
					<th>
						ECCNCGEMP
					</th>
					<th>
						ECCNCGCR
					</th>
				</tr>
				<logic:empty name="listaPesqempregado_centrocusto">
					<th>
						 Não há empregado_centrocusto Cadastrado.
					</th>
				</logic:empty>
				<logic:notEmpty name="listaPesqempregado_centrocusto">
				<% int c = 0; %>
				<logic:iterate id="b001" name="listaPesqempregado_centrocusto">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
					<a href="actionEmpregadoCentrocusto.do?m=editar&eccncodg=<bean:write name="empregado_centrocusto" property="eccncodg"/>">
						<bean:write name="b001" property="eccncodg"/>
					</a>
					</td>
					<td>
						<bean:write name="b001" property="eccncgemp"/>
					</td>
					<td>
						<bean:write name="b001" property="eccncgcr"/>
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