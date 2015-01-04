	
	<%@include file="topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset style="text-align: center;">
			<legend>
				&nbsp;   <bean:write name="formContaCorrente" property="cocnmen"/> > Alteração de dados de Conta Corrente (Fornecedor)    &nbsp;
			</legend>
			<html:form action="actionContaCorrente" focus="concgbc">
			<html:hidden property="concodg"/>
			<html:hidden property="m" value="updateFornec"/>
			<table style="width: 650px;">
			<tbody>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="2">
						Alteração de Conta Corrente
					</th>
				</tr>
				<tr>
					<th style="width: 25%">
						Tipo de Conta
					</th>
					<td style="width: 75%">
						<html:select property="contcon" style="width: 350px;">
							<html:optionsCollection name="ls_tipocontacorrente" value="tctncodg" label="tctcdesc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th style="width: 100px;">
						Banco
					</th>
					<td style="width: 400px;">
						 <html:select property="concgbc" style="width: 300px;">
						 	<html:optionsCollection name="ls_banco" label="bccdesc" value="bcncodg"/>
						 </html:select>
					</td>
				</tr>
				<tr>
					<th>
						Agência
					</th>
					<td>
						 <html:text property="cocagen" size="10" maxlength="8"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						Conta
					</th>
					<td>
						<html:text property="cocnumr" size="12" maxlength="10"></html:text>						 
					</td>
				</tr>
				<tr>
					<th>
						Titular
					</th>
					<td>
						<html:text property="coctitu" size="47" maxlength="50"></html:text>						 
					</td>
				</tr>
				<tr>
					<th>
						Conta Principal?
					</th>
					<td>
						<html:radio property="colprin" value="T">SIM</html:radio>&nbsp;<html:radio property="colprin" value="F">NAO</html:radio>					 
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'actionContaCorrente.do?m=listaFornec&enncodg=<bean:write name="formContaCorrente" property="concgen"/>'">
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