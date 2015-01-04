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
				&nbsp;   Pendências do contrato    &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="4">
						<input type="button" value="Voltar" onclick="window.location = 'actionContratoAuto.do?m=opcoes&ctancodg=<bean:write name="contratoauto" property="ctancodg"/>'">
					</th>
				</tr>
				<tr>
					<td colspan="4" style="font-size: 13px;">
						<b>Contrato nº:</b> <bean:write name="contratoauto" property="ctacnumr"/><br>
						<b>Valor:</b> R$ <bean:write name="contratoauto" property="ctayvalr"/><br>
						<b>Plano:</b> <bean:write name="contratoauto" property="ctanplan"/> meses<br>
						<b>Valor da Parcela:</b> <bean:write name="contratoauto" property="ctayvlpc"/><br>
						<b>Cliente:</b> <bean:write name="contratoauto" property="ctacnmen"/> - <b>CPF:</b> <bean:write name="contratoauto" property="ctacdocm"/> | <b>Fones:</b> <bean:write name="contratoauto" property="ctacfone"/> | <bean:write name="contratoauto" property="ctaccell"/>						
					</td>
				</tr>
				<tr>
					<th colspan="4"> 
						Pendências do contrato
					</th>
				</tr>
				<tr>
					<th>
						&nbsp;
					</th>
					<th>
						Pendência
					</th>
					<th>
						Data Baixa
					</th>
					<th>
						Digitado por
					</th>				
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_pendcontratoauto">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<th style="text-align: center;">
						<logic:empty name="b" property="pdcdbaix">
							<input type="image" src="imagens/cancela.jpg" onclick=" if (window.confirm('Confirmar a baixa da pendência?')){window.location = 'actionPendContratoAuto.do?m=baixar&pdcncodg=<bean:write name="b" property="pdcncodg"/>'}">
						</logic:empty>
						<logic:notEmpty name="b" property="pdcdbaix">
							<img src="imagens/check.jpg" border="0">
						</logic:notEmpty>
					</th>
					<td>
						&nbsp;<bean:write name="b" property="pdccpend"/>
					</td>
					<th>
						<bean:write name="b" property="pdcdbaix"/>
					</th>					
					<td>
						<bean:write name="b" property="pdccnmen"/>
					</td>				
				</tr>
				</logic:iterate>
								
			</tbody>			
			</table>		
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>