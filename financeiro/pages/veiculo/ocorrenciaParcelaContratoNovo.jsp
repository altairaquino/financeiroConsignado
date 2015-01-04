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
				&nbsp;   Cadastro de Ocorr�ncia da Parcela (Pend�ncia)    &nbsp;
			</legend>
			<html:form action="actionOcorrenciaParcelaContrato" onsubmit="return window.confirm('Confirmar a inclus�o da ocorr�ncia com os dados fornecidos?')">
			<input type="hidden" name="oconpcct" value="<bean:write name="parcelacontratoauto" property="pccncodg"/>">
			<html:hidden property="m" value="cadastro"/>
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
						Vencimento
					</th>
					<td style="color: red; font-weight: bold;">
						<bean:write name="parcelacontratoauto" property="pccdvenc"/>						
					</td>
				</tr>
				<tr>
					<th>
						Prazo
					</th>
					<th style="color: blue;">
						<bean:write name="parcelacontratoauto" property="pccdpraz"/>				
					</th>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> 
						Cadastro de Ocorr�ncia da Parcela (Pend�ncia)
					</th>
				</tr>
				<tr>
					<th style="vertical-align: top;">
						Considera��es
					</th>
					<td style="color: red; font-weight: bold;">
						1 - Antes de registrar a ocorr�ncia, verifique no sistema do banco se o cliente efetuou o pagamento.<br>
						2 - Caso o cliente n�o tenha efetuado o pagamento, registre em dias um novo prazo para pagamento.<br>
						3 - Registre no campo Observa��es todas as ocorr�ncias do contato com o cliente.		
					</td>
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
						Novo Prazo em dias
					</th>
					<td>
						<html:select property="ocondias" style="width: 100px;">
							<html:option value="3">3 dias</html:option>
							<html:option value="6">6 dias</html:option>
							<html:option value="9">9 dias</html:option>
							<html:option value="12">12 dias</html:option>
							<html:option value="15">15 dias</html:option>
						</html:select>
						<font style="color: red;">Obs: Os dias ser�o acrescentados ao prazo anterior.</font>					
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
						<html:submit styleClass="btn_hot">Registrar Ocorr�cia</html:submit>
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