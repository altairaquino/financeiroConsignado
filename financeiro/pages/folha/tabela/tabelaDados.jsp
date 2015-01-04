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
				&nbsp;Dados Cadastrais tabela&nbsp;
			</legend>
			<table style="width: 550px;">
			<tbody>
				<tr>
					<td style="text-align: right;" colspan="2">											
						<input type="button" class="btn_hot" value="Editar tabela" onclick="window.location = 'actionTabela.do?m=dados&tabncodg=<bean:write name="tabela" property="tabncodg"/>'">
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Dados Cadastrais tabela </th>
				</tr>
                				<tr>
					<td>
						TABNCODG
					</td>
					<td>
						<bean:write name="tabela" property="tabncodg"/>
					</td>
				</tr>
				<tr>
					<td>
						TABNCGTTB
					</td>
					<td>
						<bean:write name="tabela" property="tabncgttb"/>
					</td>
				</tr>
				<tr>
					<td>
						TABNLIMT
					</td>
					<td>
						<bean:write name="tabela" property="tabnlimt"/>
					</td>
				</tr>
				<tr>
					<td>
						TABNPDES
					</td>
					<td>
						<bean:write name="tabela" property="tabnpdes"/>
					</td>
				</tr>
				<tr>
					<td>
						TABNPARC
					</td>
					<td>
						<bean:write name="tabela" property="tabnparc"/>
					</td>
				</tr>
				<tr>
					<td>
						TABNXXX
					</td>
					<td>
						<bean:write name="tabela" property="tabnxxx"/>
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
