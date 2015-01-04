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
				&nbsp;   Dados do Contrato    &nbsp;
			</legend>
			<table style="width: 600px;">
			<tbody>
				<logic:equal name="contrato" property="ctlativ" value="T">
				<tr>
					<td colspan="2" style="text-align: right;"> 
						<input type="button" class="btn" value="Editar Dados" onclick="window.location = 'actionContrato.do?m=editar&ctncodg=<bean:write name="contrato" property="ctncodg"/>'">					
					</td> 
				</tr>
				</logic:equal>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">
						Dados do Contrato 
					</th>
				</tr>	
				<tr>
					<th style="width: 25%;">
						Código de Controle
					</th>
					<td style="font-weight: bold; width: 75%;">
						(<bean:write name="contrato" property="ctncodg"/>)&nbsp;
						(<bean:write name="contrato" property="ctdcadt"/> - <bean:write name="contrato" property="cttcadt"/>)			
					</td>
				</tr>
				<tr>
					<th>
						Agente de Crédito
					</th>
					<td style="color: red; font-weight: bold;">
						<bean:write name="contrato" property="ctcnman"/>
						<img src="imagens/lupa.gif" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('actionEntidade.do?m=dadosEntidadeWindow&enncodg=<bean:write name="contrato" property="ctncgen"/>', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-430)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-570)/2:0) + ',height = 430, width = 570');"/>				
					</td>
				</tr>	
				<tr>
					<th>
						Cliente
					</th>
					<td style="color: blue; font-weight: bold;">
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
						Produto
					</th>
					<td>
						<bean:write name="contrato" property="ctcdcpd"/>
					</td>
				</tr>				
				<tr>
					<th>
						Tabela Produto
					</th>
					<td>
						<bean:write name="contrato" property="ctcdctp"/>					
					</td>
				</tr>
				<tr>
					<th>
						Valor do Contrato
					</th>
					<td>
						R$ <bean:write name="contrato" property="ctyvalr"/>				
					</td>
				</tr>
				<tr>
					<th>
						Percentual da Exata
					</th>
					<td>
						<bean:write name="contrato" property="ctnexat"/>%				
					</td>
				</tr>
				<tr>
					<th>
						Status
					</th>
					<td>
						<bean:write name="contrato" property="ctcdcsc"/>						
					</td>
				</tr>
				<tr>
					<th>
						Data da Averbação
					</th>
					<td>
						<bean:write name="contrato" property="ctdverb"/>						
					</td>
				</tr>				
				<tr>
					<th>
						Data do Pagamento
					</th>
					<td>
						<bean:write name="contrato" property="ctdpgto"/>						
					</td>
				</tr>				
				<tr>
					<th>
						Cadastrado por:
					</th>
					<th>
						<bean:write name="contrato" property="ctccadt"/>
						<img src="imagens/lupa.gif" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('actionEntidade.do?m=dadosEntidadeWindow&enncodg=<bean:write name="contrato" property="ctncadt"/>', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-430)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-570)/2:0) + ',height = 430, width = 570');"/>									
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionContrato.do?m=opcoes&ctncodg=<bean:write name="contrato" property="ctncodg"/>'">
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