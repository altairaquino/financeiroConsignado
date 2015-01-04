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
				&nbsp;Cadastro dependente (alteração) > <bean:write name="empregado" property="encnome"/>&nbsp;
			</legend>
            <html:form action="actionDependente" onsubmit="return window.confirm('Confirmar a alteração do dependente com os dados fornecidos?')">
			<html:hidden property="m" value="update"/>
            		<html:hidden property="depncodg"/>
					<html:hidden property="depncgen"/>

			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Cadastro dependente (alteração) </th>
				</tr>
				<tr>
					<td class="style1">
						Nome
					</td>
					<td>
						<html:text property="depcnome" size="60" maxlength="60" style="width: 350px;"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Nascimento
					</td>
					<td>
						<html:text property="depdnasc" onkeyup="criaMascara(this, '##/##/####');"></html:text>
					</td>
				</tr>


				<tr>
					<td>
						<input type="button" class="btn_hot" value="Cancelar" onclick="window.location = 'actionDependente.do?m=lista&enncodg=<bean:write name="formDependente" property="depncgen"/>'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Cadastrar dependente</html:submit>
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
