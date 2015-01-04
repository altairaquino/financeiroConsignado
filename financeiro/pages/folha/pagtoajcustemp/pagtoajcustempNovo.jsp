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
				&nbsp;Cadastro Pagamento de Ajuda de Custo&nbsp;
			</legend>
            <html:form action="actionPagtoajcust" onsubmit="return window.confirm('Confirmar a inclus�o do Pagamento de Ajuda de Custo com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>

			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Cadastro Pagamento de Ajuda de Custo </th>
				</tr>               
				<tr>
					<td class="style1">
						Tipo ajuda de custo
					</td>
					<td>
						<html:select property="pacncgtac">
							<html:option value="-1">------</html:option>
							<html:optionsCollection name="listaTipoajcust" label="taccdesc" value="tacncodg"/>
						</html:select>
					</td>
				</tr>				
				<tr>
					<td class="style1">
						Data de pagamento
					</td>
					<td>
						<html:text property="pacdpagto"  onkeyup="criaMascara(this, '##/##/####');"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Inicio da Semana
					</td>
					<td>
						<html:text property="pacdsem"  onkeyup="criaMascara(this, '##/##/####');"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Qtd de dias com feriado
					</td>
					<td>
						<html:text property="pacnnfer"  onkeyup="criaMascara(this, '##');"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Cancelar" onclick="window.location = 'actionPagtoajcust.do?m=lista'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Cadastrar</html:submit>
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
