	<%@include file="../topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Opções do Contrato de Auto    &nbsp;
			</legend>
			<h4>Contrato nº: <bean:write name="contratoauto" property="ctacnumr"/><br>
				Cliente: <bean:write name="contratoauto" property="ctacnmen"/><br>
				Valor: R$ <bean:write name="contratoauto" property="ctayvalr"/>
			</h4>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; text-align: center;">
						Opções do Contrato de Auto
					</th>
				</tr>
				<tr>
					<td>
						Dados do Contrato Auto
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionContratoAuto.do?m=dados&ctancodg=<bean:write name="contratoauto" property="ctancodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Dados do Bem - Auto
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionVeiculo.do?m=dados&ctancodg=<bean:write name="contratoauto" property="ctancodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Parcelas do Contrato
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionParcelaContratoAuto.do?m=lista&ctancodg=<bean:write name="contratoauto" property="ctancodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Pendências do Contrato
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionPendContratoAuto.do?m=lista&ctancodg=<bean:write name="contratoauto" property="ctancodg"/>'">
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