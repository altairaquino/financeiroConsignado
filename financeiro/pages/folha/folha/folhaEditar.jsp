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
				<a href="actionFolha.do?m=lista">Folha</a>&nbsp;>&nbsp;
			<a href="actionFolha.do?m=opcao&folncodg=<bean:write name="folha" property="folncodg"/>">
			<bean:write name="folha" property="foldmes"/>&nbsp;|&nbsp;
				Nº <bean:write name="folha" property="folnnum"/>&nbsp;|&nbsp;
				<bean:write name="folha" property="folcdesc"/>&nbsp;|&nbsp;
				<bean:write name="folha" property="epcnome"/>
			</a>&nbsp;>&nbsp;
			Dados Básicos&nbsp;
			</legend>
            <html:form action="actionFolha" onsubmit="return window.confirm('Confirmar a alteração do folha com os dados fornecidos?')">
			<html:hidden property="m" value="update"/>
			<html:hidden property="folncodg"/>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Cadastro folha (alteração)</th>
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
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionFolha.do?m=opcao&folncodg=<bean:write name="formFolha" property="folncodg"/>'">
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
