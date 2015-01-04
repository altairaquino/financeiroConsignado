	
	<%@include file="../topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset style="text-align: center;">
			<legend class="red">
				&nbsp;   Borderôs > Relação  &nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<td colspan="5">
						<input type="button" class="btn_hot" value="Novo Borderô" title="Incluir novo Borderô" onclick="window.location = 'actionBordero.do?m=novo'">
					</td>
				</tr>
				<tr>
					<th colspan="5">
						Nº Borderô, Contrato&nbsp;
						<html:form action="actionContratoBordero" focus="ctbnnrct">
							<html:hidden property="m" value="pesquisa"/>
							<html:text property="ctbnnrct" size="15" maxlength="15"/>
							<html:submit styleClass="btn_hot">Pesquisar</html:submit>
						</html:form>						
					</th>					
				</tr>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="5">
						Relação de Borderôs
					</th>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%">
			<tbody>
				<logic:notEmpty name="ls_bordero">
				<tr style="background-color: #DDD;text-align: center;">
					<th>
						Número
					</th>
					<th>
						Protocolo
					</th>	
					<th>
						Envio ao Banco
					</th>
					<th>
						Data de Cadastro
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				</logic:notEmpty>
				<logic:empty name="ls_bordero">
				<tr>
					<th colspan="5" style="color: red;">
						Não há resultado de borderôs no sistema.
					</th>
				</tr>
				</logic:empty>
				<logic:iterate id="b" name="ls_bordero">
				<tr>
					<td>
						<a href="actionBordero.do?m=dados&borncodg=<bean:write name="b" property="borncodg"/>">
							<bean:write name="b" property="borcnumr"/>
						</a>
					</td>
					<td>
						<bean:write name="b" property="borcnrpt"/>
					</td>
					<td>
						<bean:write name="b" property="bordbanc"/>
					</td>
					<td style="color: #00D;">
						<bean:write name="b" property="bordcadt"/>
					</td>
					<td>
						<input type="image" src="imagens/editar.gif" title="Dados do Borderô" onclick="window.location = 'actionBordero.do?m=dados&borncodg=<bean:write name="b" property="borncodg"/>'">
						<input type="image" src="imagens/detalhe.gif" title="Contratos" onclick="window.location = 'actionContratoBordero.do?m=lista&borncodg=<bean:write name="b" property="borncodg"/>'">
					</td>
				</tr>
				</logic:iterate>
				
			</tbody>			
			</table>
			</div>
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>