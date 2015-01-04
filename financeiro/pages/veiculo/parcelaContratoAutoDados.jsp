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
				&nbsp;   Dados da parcela    &nbsp;
			</legend>
			<table style="width: 550px;">
			<tbody>
				<tr>
					<th colspan="2">
						<input type="button" value="Voltar" onclick="window.location = 'actionParcelaContratoAuto.do?m=lista&ctancodg=<bean:write name="parcelacontratoauto" property="pccnctau"/>'">
					</th>
				</tr>
				<tr>
					<th colspan="2" style="background-color: #DDD; text-align: center; font-weight: bold;"> 
						Dados da Parcela (Pendência)
					</th>
				</tr>
				<tr>
					<th>
						Nº do Contrato
					</th>
					<td>
						<bean:write name="parcelacontratoauto" property="pcccnrct"/>
					</td>
				</tr>
				<tr>
					<th>
						Nº da parcela
					</th>
					<td>
						<bean:write name="parcelacontratoauto" property="pccnnumr"/>º
					</td>
				</tr>
				<tr>
					<th>
						Valor
					</th>
					<td>
						R$ <bean:write name="parcelacontratoauto" property="pccyvalr"/>
					</td>
				</tr>
				<tr>
					<th>
						Vencimento
					</th>
					<th>
						<bean:write name="parcelacontratoauto" property="pccdvenc"/>
					</th>
				</tr>
				<tr>
					<th>
						Atrazo
					</th>
					<td style="color: red;">
						<bean:write name="parcelacontratoauto" property="pccndias"/>
					</td>
				</tr>
				<tr>
					<th>
						Prazo
					</th>
					<td>
						<bean:write name="parcelacontratoauto" property="pccdpraz"/>
					</td>					
				</tr>
				<logic:notEmpty name="parcelacontratoauto" property="pccdpgto">
				<tr>
					<th>
						Data de Pagamento
					</th>
					<td>
						<bean:write name="parcelacontratoauto" property="pccdpgto"/>
					</td>
				</tr>
				</logic:notEmpty>
				<logic:empty name="parcelacontratoauto" property="pccdpgto">
				<html:form action="actionParcelaContratoAuto" focus="pccdpgto">
				<html:hidden property="m" value="baixar"/>
				<input type="hidden" name="pccncodg" value="<bean:write name="parcelacontratoauto" property="pccncodg"/>">
				<input type="hidden" name="pccnctau" value="<bean:write name="parcelacontratoauto" property="pccnctau"/>">
				<input type="hidden" name="pccncgen" value="<bean:write name="usuario" property="enncodg"/>">
				<tr>
					<th>
						Data de Pagamento
					</th>
					<td>
						<html:text property="pccdpgto" size="10" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/>&nbsp;&nbsp;&nbsp;
						<html:submit>Baixar Pagamento</html:submit>
					</td>
				</tr>				
				</html:form>
				</logic:empty>								
			</tbody>		
			</table>
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>