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
				&nbsp;Filtro&nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="3" style="text-align: center; background-color: #DDDDDD;">  Filtro</th>
				</tr>
				<tr>				
					<td colspan="3">
						<input type="button" class="btn_hot" value="Novo" onclick="window.location = 'actionFiltro.do?m=novo'">
					</td>
				</tr>
                <tr>
					<th>
						Código
					</th>
					<th>
						Descrição
					</th>
				</tr>
				<logic:empty name="listaFiltro">
					<th colspan="2">
						 Não há Filtro Cadastrado.
					</th>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b001" name="listaFiltro">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td style="text-align: right;width: 30px;">
						<bean:write name="b001" property="filncodg"/>&nbsp;&nbsp;
					</td>
					<td>
					&nbsp;&nbsp;
						<a href="actionFiltro.do?m=opcao&filncodg=<bean:write name="b001" property="filncodg"/>">
							<bean:write name="b001" property="filcdesc"/>
						</a>
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
