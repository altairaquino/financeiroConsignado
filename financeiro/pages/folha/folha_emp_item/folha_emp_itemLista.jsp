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
				&nbsp;folha_emp_item&nbsp;
			</legend>
			<table style="width: 550px;">
			<tbody>
				<tr>
					<th colspan="4" style="text-align: center; background-color: #DDDDDD;">  folha_emp_item </th>
				</tr>
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
				<logic:empty name="listafolha_emp_item">
					<th>
						 Não há folha_emp_item Cadastrado.
					</th>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b001" name="listafolha_emp_item">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<th>
						<bean:write name="b001" property="feincodg"/>
					</th>
					<th>
						<bean:write name="b001" property="feincgfoe"/>
					</th>
					<th>
						<bean:write name="b001" property="feincgder"/>
					</th>
					<th>
						<bean:write name="b001" property="feiyvalor"/>
					</th>
				</tr>
				</logic:iterate>

				<tr>
					<td colspan="4">
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
