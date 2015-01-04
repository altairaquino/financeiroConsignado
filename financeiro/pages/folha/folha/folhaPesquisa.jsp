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
				&nbsp;Pesquisa folha&nbsp;
			</legend>
            <html:form action="actionFolha">
			<input type="hidden" name="m" value="pesquisa">
			<table style="width: 550px;">
			<tbody>
                <tr>
                    <td colspan="7" style="text-align: right;">
						<input type="text" name="param" value="">
					</td>
				</tr>
				<tr>
                    <td colspan="7" style="text-align: right;">
						<html:submit styleClass="btn_hot">Pesquisar</html:submit>
					</td>
				</tr>
                				<logic:present name="listaPesqfolha">
					<th>
						Resultado.
					</th>
				<tr>
					<th>
						FOLNCODG
					</th>
					<th>
						FOLNDIAS
					</th>
					<th>
						FOLNNUM
					</th>
					<th>
						FOLDDATA
					</th>
					<th>
						FOLDCADT
					</th>
					<th>
						FOLDMES
					</th>
					<th>
						FOLNCGEP
					</th>
				</tr>
				<logic:empty name="listaPesqfolha">
					<th>
						 Não há folha Cadastrado.
					</th>
				</logic:empty>
				<logic:notEmpty name="listaPesqfolha">
				<% int c = 0; %>
				<logic:iterate id="b001" name="listaPesqfolha">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
					<a href="actionFolha.do?m=editar&folncodg=<bean:write name="folha" property="folncodg"/>">
						<bean:write name="b001" property="folncodg"/>
					</a>
					</td>
					<td>
						<bean:write name="b001" property="folndias"/>
					</td>
					<td>
						<bean:write name="b001" property="folnnum"/>
					</td>
					<td>
						<bean:write name="b001" property="folddata"/>
					</td>
					<td>
						<bean:write name="b001" property="foldcadt"/>
					</td>
					<td>
						<bean:write name="b001" property="foldmes"/>
					</td>
					<td>
						<bean:write name="b001" property="folncgep"/>
					</td>
				</tr>
				</logic:iterate>
				</logic:notEmpty>
				</logic:present>

				<tr>
					<td colspan="7">
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