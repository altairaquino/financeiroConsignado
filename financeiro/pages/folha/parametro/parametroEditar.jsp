<%@include file="/pages/topo.jsp" %>
<style type="text/css">
	.style1{
		text-align: right;
		width: 120px;
		color:#660000;
		padding-right: 5px;
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
		<logic:present name="msg">
			<font style="font-weight: bold; color: red;"><bean:write name="msg"/></font>
		</logic:present>
		<fieldset>
			<legend>
				&nbsp;Cadastro parametro (alteração)&nbsp;
			</legend>
            <html:form action="actionParametro" onsubmit="return window.confirm('Confirmar a inclusÃ£o do parametro com os dados fornecidos?')">
			<html:hidden property="m" value="update"/>
            		<html:hidden property="parncodg"/>
			<fieldset>
				<legend>Parâmetro</legend>
			<table style="width: 100%;">
				<tr>
					<td class="style1">
						FGTS(%)
					</td>
					<td>
						<html:text property="parnfgts" ></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						FGTS Adicional(%)
					</td>
					<td>
						<html:text property="parnfgad" ></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Idade máx. para sálario família(anos)
					</td>
					<td>
						<html:text property="parnmfam" ></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Carga horária mensal(h)
					</td>
					<td>
						<html:text property="parnchms" ></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Carga horária semanal(h)
					</td>
					<td>
						<html:text property="parnchsm" ></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Salário mínimo(R$)
					</td>
					<td>
						<html:text property="parysmin" ></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						FGTS na recisão(%)
					</td>
					<td>
						<html:text property="parnfgrc" ></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						FGTS na recisão Adicional(%)
					</td>
					<td>
						<html:text property="parnfgra" ></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						PIS sobre folha de pagamento(%)
					</td>
					<td>
						<html:text property="parnpis" ></html:text>
					</td>
				</tr>
				</table>
				</fieldset>
				<fieldset>
				<legend>Imposto de Renda</legend>
				<table style="width: 100%;">
				<tr>
					<td class="style1">
						Redução da Base de calculo(R$)
					</td>
					<td>
						<html:text property="paryrbc" ></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Desconto por dependente(R$)
					</td>
					<td>
						<html:text property="paryddep" ></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Máximo de dependente
					</td>
					<td>
						<html:text property="parnmdep" ></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Aposentadoria(anos)
					</td>
					<td>
						<html:text property="parnapos" ></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Desconto mínimo(R$)
					</td>
					<td>
						<html:text property="parndmin" ></html:text>
					</td>
				</tr>
				</table>
				</fieldset>
				<fieldset>
				<legend>INSS</legend>
				<table style="width: 100%;">
				<tr>
					<td class="style1">
						Prolabore/Autônomos(%)
					</td>
					<td>
						<html:text property="parnprob" ></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Especial(FPAS639)(%)
					</td>
					<td>
						<html:text property="parnespe" ></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Sat. ocorrências 04 e 08(%)
					</td>
					<td>
						<html:text property="parnsat" ></html:text>
					</td>
				</tr>
				</table>
			</fieldset>
			<table style="width: 100%;">
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = '#adefinir'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Cadastrar parametro</html:submit>
					</td>
				</tr>			
			</table>
            </html:form>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>
