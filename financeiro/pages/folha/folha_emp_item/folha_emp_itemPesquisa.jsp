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
				&nbsp;Pesquisa folha_emp_item&nbsp;
			</legend>
            <html:form action="actionFolhaEmpItem">
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
                				<logic:present name="listaPesqfolha_emp_item">
					<th>
						Resultado.
					</th>
				<tr>
					<th>
						FEINCODG
					</th>
					<th>
						FEINCGFOE
					</th>
					<th>
						FEINCGDER
					</th>
					<th>
						FEIYVALOR
					</th>
				</tr>
				<logic:empty name="listaPesqfolha_emp_item">
					<th>
						 Não há folha_emp_item Cadastrado.
					</th>
				</logic:empty>
				<logic:notEmpty name="listaPesqfolha_emp_item">
				<% int c = 0; %>
				<logic:iterate id="b001" name="listaPesqfolha_emp_item">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
					<a href="actionFolhaEmpItem.do?m=editar&feincodg=<bean:write name="folha_emp_item" property="feincodg"/>">
						<bean:write name="b001" property="feincodg"/>
					</a>
					</td>
					<td>
						<bean:write name="b001" property="feincgfoe"/>
					</td>
					<td>
						<bean:write name="b001" property="feincgder"/>
					</td>
					<td>
						<bean:write name="b001" property="feiyvalor"/>
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