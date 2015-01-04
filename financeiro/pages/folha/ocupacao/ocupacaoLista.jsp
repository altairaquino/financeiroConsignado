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
				&nbsp;ocupacao&nbsp;
			</legend>
			<table style="width: 550px;">
			<tbody>
				<tr>
					<th colspan="5" style="text-align: center; background-color: #DDDDDD;">  ocupacao </th>
				</tr>
                				<tr>
					<th>
						OCPNCODG
					</th>
					<th>
						OCPCCODG
					</th>
					<th>
						OCPCTITULO
					</th>
					<th>
						OCPNCGFAM
					</th>
					<th>
						OCPCCGFAM
					</th>
				</tr>
				<logic:empty name="listaocupacao">
					<th>
						 Não há ocupacao Cadastrado.
					</th>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b001" name="listaocupacao">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<th>
						<bean:write name="b001" property="ocpncodg"/>
					</th>
					<th>
						<bean:write name="b001" property="ocpccodg"/>
					</th>
					<th>
						<bean:write name="b001" property="ocpctitulo"/>
					</th>
					<th>
						<bean:write name="b001" property="ocpncgfam"/>
					</th>
					<th>
						<bean:write name="b001" property="ocpccgfam"/>
					</th>
				</tr>
				</logic:iterate>

				<tr>
					<td colspan="5">
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
