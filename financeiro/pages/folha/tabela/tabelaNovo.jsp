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
				&nbsp;Cadastro tabela&nbsp;
			</legend>
            <html:form action="actionTabela" onsubmit="return window.confirm('Confirmar a inclusão do tabela com os dados fornecidos?')">
            <html:hidden property="m" value="cadastro"/>
            <html:hidden property="tabncgttb"/>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Cadastro tabela</th>
				</tr>
				<tr>
					<td class="style1">
						Limite(R$)
					</td>
					<td>
						<html:text property="tabnlimt"  onkeydown="Formata(this,10,event,2)"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Percentual(%)
					</td>
					<td>
						<html:text property="tabnpdes" onkeydown="Formata(this,10,event,2)"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Parcela(R$)
					</td>
					<td>
						<html:text property="tabnparc" onkeydown="Formata(this,10,event,2)"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Percentual(%)
					</td>
					<td>
						<html:text property="tabnxxx" onkeydown="Formata(this,10,event,2)"></html:text>
					</td>
				</tr>

				<tr>
					<td>
						<input type="button" class="btn_hot" value="Cancelar" onclick="window.location = 'actionTabela.do?m=lista&tabncgttb=<bean:write name="tipotabela" property="ttbncodg"/>'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Cadastrar tabela</html:submit>
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
