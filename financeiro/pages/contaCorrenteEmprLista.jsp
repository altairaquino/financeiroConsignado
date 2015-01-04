	
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
				&nbsp;   Lista de Conta Corrente (Empregado) > <bean:write name="empregado" property="encnome"/>  &nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<td colspan="7">
						<input type="button" class="btn_hot" value="Nova Conta Corrente" title="Incluir nova conta corrente" onclick="window.location = 'actionContaCorrente.do?m=novoEmpr&enncodg=<bean:write name="empregado" property="enncodg"/>'">
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="7">
						Lista de Contas Corrente
					</th>
				</tr>
				<logic:notEmpty name="ls_contacorrente">
				<tr style="background-color: #DDD;text-align: center;">
					<th>
						Tipo de Conta
					</th>
					<th>
						Banco
					</th>					
					<th>
						Ag�ncia
					</th>
					<th>
						N�mero
					</th>
					<th>
						Titular
					</th>
					<th>
						Principal?
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				</logic:notEmpty>
				<logic:empty name="ls_contacorrente">
				<tr>
					<th colspan="7" style="color: red;">
						N�o h� contas cadastradas.
					</th>
				</tr>
				</logic:empty>
				<logic:iterate id="b" name="ls_contacorrente">
				<tr>
					<td>
						<bean:write name="b" property="coctcon"/>
					</td>
					<td>
						<bean:write name="b" property="cocdcbc"/>
					</td>
					<td>
						<bean:write name="b" property="cocagen"/>
					</td>
					<td>
						<bean:write name="b" property="cocnumr"/>
					</td>
					<td>
						<bean:write name="b" property="coctitu"/>
					</td>
					<td>
						<logic:equal name="b" property="colprin" value="T">
							SIM
						</logic:equal>
						<logic:equal name="b" property="colprin" value="F">
							NAO
						</logic:equal>						
					</td>
					<td>
						<input type="image" src="imagens/editar.gif" title="Editar Conta Corrente" onclick="window.location = 'actionContaCorrente.do?m=editarEmpr&concodg=<bean:write name="b" property="concodg"/>'">
						<input type="image" src="imagens/cancela.jpg" title="Deletar Conta Corrente" onclick="if (window.confirm('Confirmar a exclus�o da conta?')) {window.location = 'actionContaCorrente.do?m=deleteEmpr&concodg=<bean:write name="b" property="concodg"/>'}">
					</td>
				</tr>
				</logic:iterate>
				<tr>
					<td colspan="7">
						<input type="button" class="btn" value="Voltar" onclick="window.location = 'actionEntidade.do?m=opcoesFuncionario&enncodg=<bean:write name="empregado" property="enncodg"/>'">
					</td>
				</tr>			
			
			</tbody>			
			</table>	
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>