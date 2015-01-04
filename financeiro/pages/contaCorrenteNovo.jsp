	
	<%@include file="topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset style="text-align: center;">
			<legend class="red">
				&nbsp;   Cadastro de Conta Corrente > <bean:write name="angariador" property="encnome"/>  &nbsp;
			</legend>
			<html:form action="actionContaCorrente" focus="contcon">
			<input type="hidden" name="concgen" value="<bean:write name="angariador" property="enncodg"/>">
			<html:hidden property="m" value="cadastro"/>
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="2">
						Cadastro de Conta Corrente
					</th>
				</tr>
				<tr>
					<td>
						Tipo de Conta
					</td>
					<td>
						<html:select property="contcon" style="width: 350px;">
							<html:optionsCollection name="ls_tipocontacorrente" value="tctncodg" label="tctcdesc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Banco
					</td>
					<td>
						<html:select property="concgbc" style="width: 350px;">
							<html:optionsCollection name="ls_banco" value="bcncodg" label="bccdesc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Agência
					</td>
					<td>
						<html:text property="cocagen" size="8" maxlength="8"/>
					</td>
				</tr>
				<tr>
					<td>
						Número
					</td>
					<td>
						<html:text property="cocnumr" size="10" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<td>
						Titular
					</td>
					<td>
						<html:text property="coctitu" size="50" maxlength="48"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'actionContaCorrente.do?m=lista&enncodg=<bean:write name="angariador" property="enncodg"/>'">
					</td>
					<td style="text-align: right;">						
						<html:submit styleClass="btn_hot">Salvar Conta</html:submit>
					</td>
				</tr>				
			
			</tbody>			
			</table>	
			</html:form>
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>