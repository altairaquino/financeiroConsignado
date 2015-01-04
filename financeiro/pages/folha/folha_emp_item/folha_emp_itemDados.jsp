<%@include file="topo.jsp" %>
<script type="text/javascript" src="scripts/jquery.js"></script>
</head>
<body>
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;Dados Cadastrais folha_emp_item&nbsp;
			</legend>
			<table style="width: 550px;">
			<tbody>
				<tr>
					<td style="text-align: right;" colspan="2">											
						<input type="button" class="btn_hot" value="Editar folha_emp_item" onclick="window.location = 'actionFolhaEmpItem.do?m=dados&feincodg=<bean:write name="folha_emp_item" property="feincodg"/>'">
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Dados Cadastrais folha_emp_item </th>
				</tr>
                				<tr>
					<td>
						FEINCODG
					</td>
					<td>
						<bean:write name="folha_emp_item" property="feincodg"/>
					</td>
				</tr>
				<tr>
					<td>
						FEINCGFOE
					</td>
					<td>
						<bean:write name="folha_emp_item" property="feincgfoe"/>
					</td>
				</tr>
				<tr>
					<td>
						FEINCGDER
					</td>
					<td>
						<bean:write name="folha_emp_item" property="feincgder"/>
					</td>
				</tr>
				<tr>
					<td>
						FEIYVALOR
					</td>
					<td>
						<bean:write name="folha_emp_item" property="feiyvalor"/>
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
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>
