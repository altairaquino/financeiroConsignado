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
				&nbsp;   Rela��o de Ocorr�ncias    &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="7"> Rela��o de Ocorr�ncias </th>
				</tr>
				<logic:empty name="parcelacontratoauto" property="pccdpgto">
				<tr>
					<th colspan="7">
						<input type="button" value="Nova Ocorr�ncia" onclick="window.location = 'actionOcorrenciaParcelaContrato.do?m=novo&pccncodg=<bean:write name="parcelacontratoauto" property="pccncodg"/>'">
					</th>
				</tr>
				</logic:empty>
				<tr  style="background-color: #DDD; text-align: center;">
					<th colspan="7">
						Dados da parcela do Contrato
					</th>
				</tr>
				<tr>
					<td colspan="7">
						<b>Contrato:</b> <bean:write name="parcelacontratoauto" property="pcccnrct"/><br>
						<b>N�mero da Parcela:</b> <bean:write name="parcelacontratoauto" property="pccnnumr"/>�<br>
						<b>Valor:</b> R$ <bean:write name="parcelacontratoauto" property="pccyvalr"/><br>
						<b>Data de Vencimento:</b> <bean:write name="parcelacontratoauto" property="pccdvenc"/>
					</td>
				</tr>
				<logic:notEmpty name="ls_ocorrenciaparcelacontrato">
				<tr>					
					<th>
						Dias
					</th>									
					<th>
						Contato?
					</th>
					<th>
						Prazo
					</th>
					<th>
						Ocorr�ncias
					</th>					
				</tr>
				</logic:notEmpty>
				<logic:empty name="ls_ocorrenciaparcelacontrato">
				<tr>
					<td colspan="7" style="color: red;font-weight: bold;">
						N�o h� ocorr�ncia para esta parcela.
					</td>
				</tr>				
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_ocorrenciaparcelacontrato">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<th>
						<bean:write name="b" property="ocondias"/> dias
					</th>							
					<td>
						<logic:equal name="b" property="ocolcont" value="T">
							Sim
						</logic:equal>
						<logic:equal name="b" property="ocolcont" value="F">
							Nao
						</logic:equal>
					</td>
					<th>
						<bean:write name="b" property="ocodpraz"/>
					</th>
					<td>
						<bean:write name="b" property="ococobsv"/>
					</td>
					<%-- 
					<td>
						<input type="image" src="imagens/editar.gif" title="Editar Dados" onclick="window.location = 'actionOcorrenciaParcelaContrato.do?m=editar&oconcodg=<bean:write name="b" property="oconcodg"/>'">				
					</td>
					--%>
				</tr>
				</logic:iterate>
				<tr>
					<th colspan="6">
						<input type="button" value="Voltar" onclick="window.location = 'actionParcelaContratoAuto.do?m=lista&ctancodg=<bean:write name="contratoauto" property="ctancodg"/>'">
					</th>
				</tr>				
			</tbody>			
			</table>		
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>