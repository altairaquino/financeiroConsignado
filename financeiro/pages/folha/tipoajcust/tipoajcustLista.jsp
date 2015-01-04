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
				&nbsp;Tipo Ajuda de Custo&nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="3" style="text-align: center; background-color: #DDDDDD;">  Tipo Ajuda de Custo </th>
				</tr>
				<tr>				
					<td colspan="3">
						<input type="button" class="btn_hot" value="Novo" onclick="window.location = 'actionTipoajcust.do?m=novo'">
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
				<logic:empty name="listaTipoajcust">
					<th colspan="2">
						 Não há Tipo Ajuda de Custo Cadastrado.
					</th>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b001" name="listaTipoajcust">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td style="text-align: right;width: 30px;">
						<bean:write name="b001" property="tacncodg"/>&nbsp;&nbsp;
					</td>
					<td>
					&nbsp;&nbsp;
						<a href="actionTipoajcust.do?m=editar&tacncodg=<bean:write name="b001" property="tacncodg"/>">
							<bean:write name="b001" property="taccdesc"/>
						</a>
					</td>
				</tr>
				</logic:iterate>

				<tr>
					<td colspan="3">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = '#adefinir'">
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
