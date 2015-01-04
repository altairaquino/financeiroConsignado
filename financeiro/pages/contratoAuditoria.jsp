	<%@include file="topo.jsp" %>
	
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">
			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Auditoria de Contrato    &nbsp;
			</legend>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">
						Dados do Contrato
					</th>
				</tr>
				<tr>
					<th style="width: 25%;">
						Agente de Crédito
					</th>
					<td style="color: red; font-weight: bold; width: 75%;">
						<bean:write name="contrato" property="ctcnman"/>
						<img src="imagens/lupa.gif" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('actionEntidade.do?m=dadosEntidadeWindow&enncodg=<bean:write name="contrato" property="ctncgen"/>', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-430)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-570)/2:0) + ',height = 430, width = 570');"/>				
					</td>
				</tr>				
				<tr>
					<th style="width: 25%;">
						Cliente
					</th>
					<td style="color: blue; font-weight: bold; width: 75%;">
						<bean:write name="contrato" property="ctcnmcl"/>
						<img src="imagens/lupa.gif" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('actionEntidade.do?m=dadosEntidadeWindow&enncodg=<bean:write name="contrato" property="ctnc2en"/>', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-430)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-570)/2:0) + ',height = 430, width = 570');"/>
					</td>
				</tr>
				<tr>
					<th>
						Nº do Contrato
					</th>
					<td style="font-weight: bold;">
						<bean:write name="contrato" property="ctnnumr"/>						
					</td>
				</tr>
				<tr>
					<th> 
						Forma de Pagamento
					</th>
					<td>
						<bean:write name="contrato" property="ctcdcfp"/>
					</td>
				</tr>
				<logic:equal name="contrato" property="ctncgfp" value="2">
				<tr>
					<th> 
						Banco da O.P.
					</th>
					<td>
						<bean:write name="contrato" property="ctcdcbc"/>&nbsp;<b>Agência da O.P.:</b>&nbsp;
						<bean:write name="contrato" property="ctcagen"/>
					</td>
				</tr>
				</logic:equal>
				<tr>
					<th>
						Valor do Contrato
					</th>
					<td>
						R$ <bean:write name="contrato" property="ctyvalr"/>
					</td>
				</tr>
				<html:form action="actionContrato">
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">
						Auditoria do Contrato
					</th>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;">
						<input type="button" class="btn_hot" value="Registrar Auditoria" onclick="if (window.confirm('Confirmar os dados da auditoria para este contrato?')){this.form.submit();}">
					</td>
				</tr>
				</html:form>
				<tr>
					<td colspan="2">
						<input type="button" value="Voltar" onclick="window.location = 'actionContrato.do?m=listaAuditoria&ctdcadt=<bean:write name="contrato" property="ctdcadt"/>'">
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