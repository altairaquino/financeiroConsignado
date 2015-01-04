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
				&nbsp;folha&nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="8" style="text-align: center; background-color: #DDDDDD;">  folha </th>
				</tr>
				<tr>
					<td colspan="8">
						<input type="button" class="btn_hot" value="Novo" onclick="window.location = 'actionFolha.do?m=novo'">
					</td>
				</tr>
                <tr>
					<th>
						Competência
					</th>
					<th>
						Número
					</th>
					<th>
						Descrição
					</th>
					<th>
						Empresa
					</th>
					<th>
						Pagamento
					</th>
				</tr>
				<logic:empty name="listafolha">
					<th colspan="8">
						 Não há folha Cadastrado.
					</th>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b001" name="listafolha">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
				<%-- 
					<th>
						<bean:write name="b001" property="folncodg"/>
					</th>
					<th>
						<bean:write name="b001" property="folndias"/>
					</th>
				--%>
				<%--
					<th>
						<bean:write name="b001" property="foldcadt"/>
					</th>
				 --%>
					<td>
						<bean:write name="b001" property="foldmes"/>
					</td>
					<td>
						<bean:write name="b001" property="folnnum"/>
					</td>
					<td>
					<a href="actionFolha.do?m=opcao&folncodg=<bean:write name="b001" property="folncodg"/>">
						<bean:write name="b001" property="folcdesc"/>
					</a>
					</td>
					<td>
						<bean:write name="b001" property="epcnome"/>
					</td>
					<td>
						<bean:write name="b001" property="folddata"/>
					</td>
				</tr>
				</logic:iterate>
			</tbody>
			</table>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>
