	
	<%@include file="../topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset style="text-align: center;">
			<legend class="red">
				&nbsp;   Borderôs > Dados &nbsp;
			</legend>
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<td colspan="2" style="text-align: right;">
						<input type="button" class="btn_hot" value="Editar" title="Editar Borderô" onclick="window.location = 'actionBordero.do?m=editar&borncodg=<bean:write name="bordero" property="borncodg"/>'">
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="2">
						Dados do Borderô
					</th>
				</tr>
				<tr>
					<th style="width: 25%;">
						Número do borderô
					</th>
					<td style="width: 75%;">
						<bean:write name="bordero" property="borcnumr"/>
					</td>
				</tr>
				<tr>
					<th>
						Número do Protocolo
					</th>
					<td>
						<bean:write name="bordero" property="borcnrpt"/>
					</td>
				</tr>
				<tr>
					<th>
						Envio ao Banco
					</th>
					<td>
						<bean:write name="bordero" property="bordbanc"/>						
					</td>
				</tr>
				<tr>
					<th>
						Cadastrado por:
					</th>
					<td>
						<bean:write name="bordero" property="borcnmen"/>		
					</td>
				</tr>
				<tr>
					<th>
						Data de Cadastro
					</th>
					<td style="color: #00D;">
						<bean:write name="bordero" property="bordcadt"/>
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="2">
						Observações
					</th>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;">
						<textarea rows="3" cols="80" readonly="readonly"><bean:write name="bordero" property="borcobsv"/></textarea>						
					</th>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" value="Voltar" onclick="window.location = 'actionBordero.do?m=lista'">
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