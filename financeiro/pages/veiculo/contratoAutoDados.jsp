	<%@include file="../topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>		
			
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Dados do Contrato - Veículo    &nbsp;
			</legend>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<td colspan="2">
						<input type="button" value="Editar Dados" onclick="window.location = 'actionContratoAuto.do?m=editar&ctancodg=<bean:write name="contratoauto" property="ctancodg"/>'">
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> 
						Dados do Contrato - Veículo
					</th>
				</tr>
				<tr>
					<th style="width: 25%">
						Cliente
					</th>
					<td style="width: 75%">
						<bean:write name="contratoauto" property="ctacdocm" /> - <bean:write name="contratoauto" property="ctacnmen" />
					</td>
				</tr>
				<tr>
					<th> 
						Nº do Contrato
					</th>
					<td>
						<bean:write name="contratoauto" property="ctacnumr"/>						
					</td>
				</tr>
				<tr>
					<th>
						Forma de Pagamento
					</th>
					<td>
						<bean:write name="contratoauto" property="ctacfpto"/>
					</td>
				</tr>
				<tr>
					<th>
						Data Base
					</th>
					<td>
						<bean:write name="contratoauto" property="ctadbase"/>
					</td>
				</tr>	
				<tr>
					<th>
						Filial
					</th>
					<td>
						<bean:write name="contratoauto" property="ctacdcep"/>
					</td>
				</tr>
				<tr>
					<th>
						Operador
					</th>
					<td>
						<bean:write name="contratoauto" property="ctacnmop"/>
					</td>
				</tr>			
				<tr>
					<th>
						Loja Veículo
					</th>
					<td>
						<bean:write name="contratoauto" property="ctacnmlj"/>
					</td>
				</tr>			
				<tr>
					<th>
						Valor do Contrato (R$)
					</th>
					<td>
						<bean:write name="contratoauto" property="ctayvalr"/>						
					</td>
				</tr>
				<tr>
					<th>
						Plano
					</th>
					<td>
						<bean:write name="contratoauto" property="ctanplan"/>&nbsp;Meses												
					</td>
				</tr>
				<tr>
					<th>
						Valor Parcela (R$)
					</th>
					<td>
						<bean:write name="contratoauto" property="ctayvlpc"/>						
					</td>
				</tr>
				<tr>
					<th style="vertical-align: top;">
						Observações												
					</th>
					<td>
						<bean:write name="contratoauto" property="ctacobsv"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="Voltar" onclick="window.location = 'actionContratoAuto.do?m=opcoes&ctancodg=<bean:write name="contratoauto" property="ctancodg"/>'">
					</td>
				</tr>			
				
			</tbody>	
			</table>
			
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>