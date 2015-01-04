	<%@include file="../topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend class="red">
				&nbsp;   Parcelas do contrato    &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="8">
						<input type="button" value="Voltar" onclick="window.location = 'actionContratoAuto.do?m=opcoes&ctancodg=<bean:write name="contratoauto" property="ctancodg"/>'">
					</th>
				</tr>
				<tr>
					<td colspan="8" style="font-size: 13px;">
						<b>Contrato nº:</b> <bean:write name="contratoauto" property="ctacnumr"/><br>
						<b>Valor:</b> R$ <bean:write name="contratoauto" property="ctayvalr"/><br>
						<b>Plano:</b> <bean:write name="contratoauto" property="ctanplan"/> meses<br>
						<b>Valor da Parcela:</b> <bean:write name="contratoauto" property="ctayvlpc"/><br>
						<b>Cliente:</b> <bean:write name="contratoauto" property="ctacnmen"/> - <b>CPF:</b> <bean:write name="contratoauto" property="ctacdocm"/> | <b>Fones:</b> <bean:write name="contratoauto" property="ctacfone"/> | <bean:write name="contratoauto" property="ctaccell"/>						
					</td>
				</tr>
				<tr>
					<th colspan="8"> 
						Parcelas do contrato
					</th>
				</tr>
			</tbody>
			</table>
			<div style="overflow: auto; width: 100%; height: 350px;">
			<table style="width: 100%">
			<tbody>
				<tr>
					<th>
						Nº
					</th>
					<th>
						Valor
					</th>
					<th>
						Vencimento
					</th>
					<th>
						Atrazo
					</th>
					<th>
						Pagamento
					</th>
					<th>
						Prazo
					</th>
					<th>
						Usuário Baixa
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_parcelacontratoauto">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<th style="text-align: center;">
						<bean:write name="b" property="pccnnumr"/>ª
					</th>
					<td>
						R$ <bean:write name="b" property="pccyvalr"/>
					</td>
					<th>
						<bean:write name="b" property="pccdvenc"/>
					</th>					
					<th style="color: red;">
						<bean:write name="b" property="pccndias"/>
					</th>
					<th style="color: blue;">
						<bean:write name="b" property="pccdpgto"/>
					</th>					
					<td>
						<bean:write name="b" property="pccdpraz"/>
					</td>
					<td>
						<bean:write name="b" property="pcccnmen"/>
					</td>
					<td style="text-align: center; background-color: yellow;">
						<input type="image" src="imagens/fone.gif" title="Registrar Ocorrência" onclick="window.location = 'actionOcorrenciaParcelaContrato.do?m=lista&pccncodg=<bean:write name="b" property="pccncodg"/>'">
						<input type="image" src="imagens/detalhe.gif" title="Detalhes" onclick="window.location = 'actionParcelaContratoAuto.do?m=dados&pccncodg=<bean:write name="b" property="pccncodg"/>'">							
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