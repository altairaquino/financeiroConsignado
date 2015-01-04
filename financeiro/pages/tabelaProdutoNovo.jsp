	
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
				&nbsp; <bean:write name="produto" property="pdcdesc"/> > Cadastro de Nova Tabela    &nbsp;
			</legend>
			<center>
			<html:form action="actionTabelaProduto" focus="tpcnumr" onsubmit="return window.confirm('Confirmar a inclus�o da tabela com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>
			<input type="hidden" name="tpncgpd" value="<bean:write name="produto" property="pdncodg"/>">
			<table style="width: 450px;">			
			<tbody>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="2"> 
						Cadastro de Nova Tabela
					</th>
				</tr>
				<tr>
					<td>
						Número
					</td>
					<td>
						<html:text property="tpcnumr" size="5" maxlength="5"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Nome
					</td>
					<td>
						<html:text property="tpcdesc" size="30" maxlength="30"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Qtd. Parcelas
					</td>
					<td>
						<html:text property="tpnqtpc" size="3" maxlength="3"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Carência
					</td>
					<td>
						<html:text property="tpncare" size="3" maxlength="3"></html:text> (em dias)
					</td>
				</tr>
				<tr>
					<td>
						Prazo
					</td>
					<td>
						<html:text property="tpnpraz" size="3" maxlength="3"></html:text> (em meses)
					</td>
				</tr>
				<tr>
					<td>
						Percentual Recebido
					</td>
					<td>
						<html:text property="tpnexat" size="8" maxlength="8" onkeydown="Formata(this,4,event,2)" style="text-align: right;"></html:text>(%)
					</td>
				</tr>
				<tr>
					<td>
						Percentual de Impostos
					</td>
					<td>
						<html:text property="tpnimpt" size="8" maxlength="8" onkeydown="Formata(this,4,event,2)" style="text-align: right;"></html:text>(%)
					</td>
				</tr>				
				<tr>
					<td>
						Zera a comissão?
					</td>
					<td>
						<html:radio property="tplzera" value="T">SIM</html:radio>&nbsp;&nbsp;
						<html:radio property="tplzera" value="F">NAO</html:radio>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'actionTabelaProduto.do?m=lista&pdncodg=<bean:write name="produto" property="pdncodg"/>'">
					</td>
					<td style="text-align: right;">						
						<html:submit styleClass="btn_hot">Salvar Tabela</html:submit>
					</td>
				</tr>			
			</tbody>			
			</table>	
			</html:form>
			</center>
			
					
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>