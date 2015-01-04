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
				&nbsp;Dados Cadastrais empregado_centrocusto&nbsp;
			</legend>
			<table style="width: 550px;">
			<tbody>
				<tr>
					<td style="text-align: right;" colspan="2">											
						<input type="button" class="btn_hot" value="Editar empregado_centrocusto" onclick="window.location = 'actionEmpregadoCentrocusto.do?m=dados&eccncodg=<bean:write name="empregado_centrocusto" property="eccncodg"/>'">
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Dados Cadastrais empregado_centrocusto </th>
				</tr>
                				<tr>
					<td>
						ECCNCODG
					</td>
					<td>
						<bean:write name="empregado_centrocusto" property="eccncodg"/>
					</td>
				</tr>
				<tr>
					<td>
						ECCNCGEMP
					</td>
					<td>
						<bean:write name="empregado_centrocusto" property="eccncgemp"/>
					</td>
				</tr>
				<tr>
					<td>
						ECCNCGCR
					</td>
					<td>
						<bean:write name="empregado_centrocusto" property="eccncgcr"/>
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
