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
				&nbsp;   Altera��o de Ocorr�ncia da Parcela (Pend�ncia)    &nbsp;
			</legend>
			<html:form action="actionOcorrenciaParcelaContrato" onsubmit="return window.confirm('Confirmar a altera��o da ocorr�ncia com os dados fornecidos?')">
			<html:hidden property="oconcodg"/>
			<html:hidden property="oconpcct"/>
			<html:hidden property="m" value="update"/>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> 
						Dados da Parcela (Pend�ncia)
					</th>
				</tr>
				<tr>
					<th>
						N� do Contrato
					</th>
					<td>
						<bean:write name="parcelacontratoauto" property="pcccnrct"/>
					</td>
				</tr>
				<tr>
					<th>
						N� da Parcela
					</th>
					<td>
						<bean:write name="parcelacontratoauto" property="pccnnumr"/> �					
					</td>
				</tr>
				<tr>
					<th>
						Valor da Parcela
					</th>
					<th>
						R$ <bean:write name="parcelacontratoauto" property="pccyvalr"/>					
					</th>
				</tr>
				<tr>
					<th>
						Data de Vencimento
					</th>
					<td style="color: red; font-weight: bold;">
						<bean:write name="parcelacontratoauto" property="pccdvenc"/>						
					</td>
				</tr>
				<tr>
					<th>
						�ltimo prazo
					</th>
					<th style="color: blue;">
						<bean:write name="parcelacontratoauto" property="pccdpraz"/>						
					</th>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> 
						Altera��o de Ocorr�ncia da Parcela (Pend�ncia)
					</th>
				</tr>
				<tr>
					<th>
						Houve contato com o cliente?
					</th>
					<td>
						<html:radio property="ocolcont" value="T">&nbsp;Sim</html:radio>&nbsp;&nbsp;
						<html:radio property="ocolcont" value="F">&nbsp;Nao</html:radio>						
					</td>
				</tr>
				<tr>
					<th> 
						Novo Prazo para pgto
					</th>
					<td>
						<html:text property="ocodpraz" size="10" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/> <font style="color: red;">Obs: Novo prazo n�o pode ser menor que o �ltimo.</font>						
					</td>
				</tr>
				<tr>
					<th style="vertical-align: top;">
						Observa��es
					</th>
					<td>
						<html:textarea property="ococobsv" cols="65" rows="2"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Cancelar" onclick="window.location = 'actionOcorrenciaParcelaContrato.do?m=lista&pccncodg=<bean:write name="parcelacontratoauto" property="pccncodg"/>';">
					</td>
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Atualizar Ocorr�cia</html:submit>
					</td>
				</tr>				
				
			</tbody>	
			</table>
			</html:form>
		
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>