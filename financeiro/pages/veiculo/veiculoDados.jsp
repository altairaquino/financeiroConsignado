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
				&nbsp;   Dados do Bem - Veículo    &nbsp;
			</legend>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<td colspan="2">
						<input type="button" value="Editar Dados" onclick="window.location = 'actionVeiculo.do?m=editar&veincodg=<bean:write name="veiculo" property="veincodg"/>'">
					</td>
				</tr>
				<tr>
					<td colspan="2" style="font-size: 13px;">
						<b>Contrato nº:</b> <bean:write name="contratoauto" property="ctacnumr"/><br>
						<b>Valor:</b> R$ <bean:write name="contratoauto" property="ctayvalr"/><br>
						<b>Plano:</b> <bean:write name="contratoauto" property="ctanplan"/> meses<br>
						<b>Valor da Parcela:</b> <bean:write name="contratoauto" property="ctayvlpc"/><br>
						<b>Cliente:</b> <bean:write name="contratoauto" property="ctacnmen"/> - <b>CPF:</b> <bean:write name="contratoauto" property="ctacdocm"/> | <b>Fones:</b> <bean:write name="contratoauto" property="ctacfone"/> | <bean:write name="contratoauto" property="ctaccell"/>						
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> 
						Dados do Bem - Veículo
					</th>
				</tr>
				<tr>
					<th style="width: 25%;">
						Marca
					</th>
					<td style="widows: 75%;">
						<bean:write name="veiculo" property="veicmarc" />
					</td>
				</tr>
				<tr>
					<th> 
						Modelo
					</th>
					<td>
						<bean:write name="veiculo" property="veicmode"/>	
					</td>
				</tr>
				<tr>
					<th>
						Ano de Fabricação
					</th>
					<td>
						<bean:write name="veiculo" property="veinanof"/>
					</td>
				</tr>
				<tr>
					<th>
						Valor de Mercado
					</th>
					<td>
						R$ <bean:write name="veiculo" property="veiyvalr"/>
					</td>
				</tr>	
				<tr>
					<th>
						Placa
					</th>
					<td>
						<bean:write name="veiculo" property="veicplac"/>
					</td>
				</tr>				
				<tr>
					<th>
						Chassi
					</th>
					<td>
						<bean:write name="veiculo" property="veicchas"/>						
					</td>
				</tr>
				<tr>
					<th>
						Renavan
					</th>
					<td>
						<bean:write name="veiculo" property="veicrena"/>												
					</td>
				</tr>
				<tr>
					<th>
						UF Licenciamento
					</th>
					<td>
						<bean:write name="veiculo" property="veicufli"/>						
					</td>
				</tr>
				<tr>
					<th>
						Combustível												
					</th>
					<td>
						<bean:write name="veiculo" property="veiccomb"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						&nbsp;
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