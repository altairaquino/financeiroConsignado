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
				&nbsp;<a href="actionDescRend.do?m=lista">Descontos e Rendimentos</a>&nbsp;>&nbsp;<a href="actionDescRend.do?m=opcao&derncodg=<bean:write name="desc_rend" property="derncodg"/>"><bean:write name="desc_rend" property="dercdesc"/></a>&nbsp;> &nbsp;Dados B�sicos&nbsp;
			</legend>
            <html:form action="actionDescRend" onsubmit="return window.confirm('Confirmar a altera��o do Descontos e Rendimentos com os dados fornecidos?')">
			<html:hidden property="m" value="update"/>
            		<html:hidden property="derncodg"/>

			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Cadastro de Descontos e Rendimentos (altera��o) </th>
				</tr>
				<tr>
					<td class="style1">
						C�digo
					</td>
					<td>
						<html:text property="derccodg" size="10" maxlength="10"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Descri��o
					</td>
					<td>
						<html:text property="dercdesc" size="60" maxlength="60"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Tipo
					</td>
					<td>
						<html:select property="derctipo">
						<html:option value="">Selecione o tipo</html:option>
						<html:option value="C">Calculado</html:option>
						<html:option value="X">Formula</html:option>
						<html:option value="H">Frequencia</html:option>
						<html:option value="V">Variavel</html:option>
						<html:option value="T">Tabela</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Afeta l�quido?
					</td>
					<td>
						<html:radio styleId="derlliqdT" property="derlliqd" value="T">SIM</html:radio>
						<html:radio styleId="derlliqdF" property="derlliqd" value="F">N�O</html:radio>
					</td>
				</tr>
				<tr>
					<td class="style1">
						multiplicando
					</td>
					<td>
						<html:text property="dernmult" ></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						F�rmula
					</td>
					<td>
						<html:text property="dercform" size="60" maxlength="60"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Cancelar" onclick="window.location = 'actionDescRend.do?m=opcao&derncodg=<bean:write name="formDescRend" property="derncodg"/>'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Cadastrar desc_rend</html:submit>
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
