<%@include file="/pages/topo.jsp" %>
<style type="text/css">
	.style1{
		text-align: right;
		width: 120px;
		color:#660000;
	}
</style>
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
				&nbsp;Cadastro Tipo Ajuda de Custo (alteração)&nbsp;
			</legend>
            <html:form action="actionTipoajcust" onsubmit="return window.confirm('Confirmar a alteração do Tipo Ajuda de Custo com os dados fornecidos?')">
			<html:hidden property="m" value="update"/>
            		<html:hidden property="tacncodg"/>

			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Cadastro Tipo Ajuda de Custo (alteração) </th>
				</tr>
                				<tr>
					<td class="style1">
						Código
					</td>
					<td>
						<html:text property="tacncodg" disabled="true"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Descrição
					</td>
					<td>
						<html:text property="taccdesc" size="60" maxlength="60"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Cancelar" onclick="window.location = 'actionTipoajcust.do?m=lista'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Editar Tipo Ajuda de Custo</html:submit>
					</td>
				</tr>
			</tbody>			
			</table>
            </html:form>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>
