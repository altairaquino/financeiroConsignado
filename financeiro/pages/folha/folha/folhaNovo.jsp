<%@include file="/pages/topo.jsp" %>
<style type="text/css">
	.style1{
		text-align: right;
		width: 120px;
		color:#660000;
	}
</style>
</head>
<body>
	<div id="container">
		<%@include file="/pages/menu.jsp" %>
		<%@include file="/pages/header.jsp" %>
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;Cadastro de Folha&nbsp;
			</legend>
            <html:form action="actionFolha" onsubmit="return window.confirm('Confirmar a inclusão do folha com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Cadastro folha </th>
				</tr>
				<tr>
					<td class="style1">
						Empresa
					</td>
					<td>
						<html:select property="folncgep">
						<html:option value="-1">Selecione uma empresa</html:option>
						<html:optionsCollection name="empresas" label="epcnome" value="epncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Número
					</td>
					<td>
						<html:text property="folnnum"  onkeyup="criaMascara(this, '###');"></html:text>
					</td>
				</tr>
				
				<tr>
					<td class="style1">
						Descrição
					</td>
					<td>
						<html:text property="folcdesc" maxlength="60" size="60"></html:text>
					</td>
				</tr>
                <tr>
					<td class="style1">
						Dias uteis
					</td>
					<td>
						<html:text property="folndias"  onkeyup="criaMascara(this, '##');"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Data de pagamento
					</td>
					<td>
						<html:text property="folddata"  onkeyup="criaMascara(this, '##/##/####');"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Competência(mm/aaaa)
					</td>
					<td>
						<html:text property="foldmes"  onkeyup="criaMascara(this, '##/####');"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = '#adefinir'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Cadastrar folha</html:submit>
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
