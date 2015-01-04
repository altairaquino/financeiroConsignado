	<%@include file="topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
			
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">	
				
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Re-an�lise de Comiss�es do Agente de Cr�dito   &nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">
						Re-an�lise de Comiss�es do Agente de Cr�dito
					</th>
				</tr>
				<tr>
					<td>
						<b>Efetuar Re-an�lise de comiss�o de todos os corretores Ativos</b><br>
						<font color="red">Com essa op��o � poss�vel efetuar a r�-an�lise do spread da EXATA com cada Agente de Cr�dito
						de acordo com a tabela de produ��o do corretor nos ultimos 90 dias.</font>
					</td>
					<td>
						<input type="button" class="btn_hot" value="Efetuar a Re-an�lise" onclick="if (window.confirm('Confirma a Re-An�lise da comiss�o de todos os corretores?\nAp�s a confirma��o a opera��o n�o poder� ser revertida.')){window.location = 'actionAngariador.do?m=ajustarSpread'}">
					</td>
				</tr>							
			</tbody>			
			</table>
			<logic:present name="ls_angariador">
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table  style="width: 100%">
			<tbody>
				<tr>
					<th style="background-color: #DDD; font-weight: bold; text-align: center;" colspan="3">
						Rela��o de Corretores (Resultado)
					</th>
				</tr>
				<tr style="background-color: #DDD; font-weight: bold;">
					<th>
						C�digo
					</th>
					<th>
						Nome
					</th>
					<th>
						Novo Spread
					</th>
				</tr>
				<logic:iterate name="ls_angariador" id="b">
				<tr>
					<td>
						<bean:write name="b" property="anncgen"/>
					</td>
					<td>
						<bean:write name="b" property="ancnmen"/>
					</td>
					<td>
						<bean:write name="b" property="annspre"/>
					</td>
				</tr>
				</logic:iterate>							
			</tbody>			
			</table>
			</div>
			</logic:present>
					
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>