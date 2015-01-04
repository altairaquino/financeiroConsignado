<%-- 
--%>
<%@include file="/pages/topo_.jsp" %>
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
				&nbsp;Dados Cadastrais folha_emp&nbsp;
			</legend>
			<table style="width: 550px;">
			<tbody>
				<tr>
					<td style="text-align: right;" colspan="2">											
						<input type="button" class="btn_hot" value="Editar folha_emp" onclick="window.location = 'actionFolhaEmp.do?m=dados&foencodg=<bean:write name="folha_emp" property="foencodg"/>'">
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Dados Cadastrais folha_emp </th>
				</tr>
                				<tr>
					<td>
						FOENCODG
					</td>
					<td>
						<bean:write name="folha_emp" property="foencodg"/>
					</td>
				</tr>
				<tr>
					<td>
						FOENCGEMP
					</td>
					<td>
						<bean:write name="folha_emp" property="foencgemp"/>
					</td>
				</tr>
				<tr>
					<td>
						FOENCGFOL
					</td>
					<td>
						<bean:write name="folha_emp" property="foencgfol"/>
					</td>
				</tr>

				<tr>
					<td colspan="2">
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
