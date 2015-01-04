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
				&nbsp;Descontos e Rendimentos&nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Novo" onclick="window.location = 'actionDescRend.do?m=novo'">
					</td>
				</tr>
					
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> Descontos e Rendimentos </th>
				</tr>
                <tr>
					<th>
						Código
					</th>
					<th>
						Descrição
					</th>					
				</tr>
				<logic:empty name="listadesc_rend">
					<th>
						 Não há desc_rend Cadastrado.
					</th>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b001" name="listadesc_rend">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td style="text-align: right; width: 30px;">
						<bean:write name="b001" property="derccodg"/>&nbsp;&nbsp;
					</td>
					<td>&nbsp;&nbsp;
						<a href="actionDescRend.do?m=opcao&derncodg=<bean:write name="b001" property="derncodg"/>">
						<bean:write name="b001" property="dercdesc"/>
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
