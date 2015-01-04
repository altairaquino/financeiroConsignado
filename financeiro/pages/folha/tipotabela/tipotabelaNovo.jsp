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
				&nbsp;Cadastro tipotabela&nbsp;
			</legend>
            <html:form action="actionTipotabela" onsubmit="return window.confirm('Confirmar a inclusão do tipotabela com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>
            		<html:hidden property="ttbncodg"/>

			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Cadastro tipotabela</th>
				</tr>
                				<tr>
					<td class="style1">
						Código
					</td>
					<td>
						<html:text property="ttbncodg" disabled="true"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Descrição
					</td>
					<td>
						<html:text property="ttbcdesc" size="60" maxlength="60"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Sigla
					</td>
					<td>
						<html:text property="ttbcsigl" size="4" maxlength="4"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Cancelar" onclick="window.location = 'actionTipotabela.do?m=lista'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Cadastro tipotabela</html:submit>
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
