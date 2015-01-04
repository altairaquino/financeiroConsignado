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
				&nbsp;Parâmetros > <bean:write name="tipotabela" property="ttbcdesc"/>&nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<td>
					<input type="button" class="btn_hot" value="Novo" onclick="window.location = 'actionTabela.do?m=novo&ttbncodg=<bean:write name="tipotabela" property="ttbncodg"/>'">
					</td>
				</tr>
				<tr>
					<th colspan="5" style="text-align: center; background-color: #DDDDDD;">  Parâmetros </th>
				</tr>
                <tr>
                <%-- 
					<th>
						Código
					</th>
					<th>
						TABNCGTTB
					</th>
                --%>
					<th>
						Limite(R$)
					</th>
					<th>
						Percentual(%)
					</th>
					<th>
						Parcela(R$)
					</th>
					<th>
						Percentual(%)
					</th>
				</tr>
				<logic:empty name="listatabela">
					<th>
						 Não há tabela Cadastrado.
					</th>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b001" name="listatabela">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
				<%--
					<td>
						<bean:write name="b001" property="tabncodg"/>
					</td>
					<td>
						<bean:write name="b001" property="tabncgttb"/>
					</td>
				 --%>
					<td align="right">
						<bean:write name="b001" property="tabnlimt"/>
					</td>
					<td align="right">
						<bean:write name="b001" property="tabnpdes"/>
					</td>
					<td align="right">
						<bean:write name="b001" property="tabnparc"/>
					</td>
					<td align="right">
						<bean:write name="b001" property="tabnxxx"/>
					</td>
					<td>
						<a href="actionTabela.do?m=editar&tabncodg=<bean:write name="b001" property="tabncodg"/>">editar</a>
					</td>
				</tr>
				</logic:iterate>
				<tr>
					<td colspan="5">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionTipotabela.do?m=opcao&ttbncodg=<bean:write name="tipotabela" property="ttbncodg"/>'">
					</td>
				</tr>
			</tbody>
			</table>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>
