	<%@include file="topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend class="red">
				&nbsp;   <bean:write name="contacorrente" property="cocnmen"/> > Conta Corrente    &nbsp;
			</legend>
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: right;" >
						<input type="button" value="Editar" onclick="window.location = 'actionContaCorrente.do?m=editar&enncodg=<bean:write name="contacorrente" property="concgen"/>'">
					</th>
				</tr>
				<tr>
					<th style="width: 100px;">
						Banco
					</th>
					<td style="width: 400px;">
						 <bean:write name="contacorrente" property="cocdcbc"/>
					</td>
				</tr>
				<tr>
					<th>
						Agência
					</th>
					<td>
						 <bean:write name="contacorrente" property="cocagen"/>
					</td>
				</tr>
				<tr>
					<th>
						Conta
					</th>
					<td>
						 <bean:write name="contacorrente" property="cocnumr"/>
					</td>
				</tr>
				<tr>
					<th>
						Titular
					</th>
					<td>
						 <bean:write name="contacorrente" property="coctitu"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionEntidade.do?m=opcoesAngariador&enncodg=<bean:write name="contacorrente" property="concgen"/>'">
					</td>
					<th style="text-align: right;">
						<input type="button" value="Editar" onclick="window.location = 'actionContaCorrente.do?m=editar&enncodg=<bean:write name="contacorrente" property="concgen"/>'">						
					</th>
				</tr>				
			</tbody>			
			</table>		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>