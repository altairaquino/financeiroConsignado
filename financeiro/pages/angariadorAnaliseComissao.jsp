	<%@include file="topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
			
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">	
				
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Re-análise de Comissões do Agente de Crédito   &nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">
						Re-análise de Comissões do Agente de Crédito
					</th>
				</tr>
				<tr>
					<td>
						<b>Efetuar Re-análise de comissão de todos os corretores Ativos</b><br>
						<font color="red">Com essa opção é possível efetuar a ré-análise do spread da EXATA com cada Agente de Crédito
						de acordo com a tabela de produção do corretor nos ultimos 90 dias.</font>
					</td>
					<td>
						<input type="button" class="btn_hot" value="Efetuar a Re-análise" onclick="if (window.confirm('Confirma a Re-Análise da comissão de todos os corretores?\nApós a confirmação a operação não poderá ser revertida.')){window.location = 'actionAngariador.do?m=ajustarSpread'}">
					</td>
				</tr>							
			</tbody>			
			</table>
			<logic:present name="ls_angariador">
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table  style="width: 100%">
			<tbody>
				<tr>
					<th style="background-color: #DDD; font-weight: bold; text-align: center;" colspan="3">
						Relação de Corretores (Resultado)
					</th>
				</tr>
				<tr style="background-color: #DDD; font-weight: bold;">
					<th>
						Código
					</th>
					<th>
						Nome
					</th>
					<th>
						Novo Spread
					</th>
				</tr>
				<logic:iterate name="ls_angariador" id="b">
				<tr>
					<td>
						<bean:write name="b" property="anncgen"/>
					</td>
					<td>
						<bean:write name="b" property="ancnmen"/>
					</td>
					<td>
						<bean:write name="b" property="annspre"/>
					</td>
				</tr>
				</logic:iterate>							
			</tbody>			
			</table>
			</div>
			</logic:present>
					
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>