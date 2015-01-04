	<%@include file="topo.jsp" %>
	
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">	
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Relatórios Diversos (Gerenciais)  &nbsp;
			</legend>
			<html:form action="actionContrato">
			<html:hidden property="m" value="relatoriosGerenciais"/>
			<center>
			<table style="width: 500px;">
			<tbody>
				<tr>
					<td style="text-align: right;" colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2">
						Relatórios Diversos (Gerenciais)
					</th>
				</tr>
				<tr>
					<td>
						Mês
					</td>
					<td>
						<select name="mes" style="width: 250px;">
							<option value="0">Janeiro</option>
							<option value="1">Fevereiro</option>
							<option value="2">Março</option>
							<option value="3">Abril</option>
							<option value="4">Maio</option>
							<option value="5">Junho</option>
							<option value="6">Julho</option>
							<option value="7">Agosto</option>
							<option value="8">Setembro</option>
							<option value="9">Outubro</option>
							<option value="10">Novembro</option>
							<option value="11">Dezembro</option>						
						</select>						
					</td>
				</tr>
				<tr>
					<td>
						Ano
					</td>
					<td>
						<select name="ano" style="width: 250px;">
							<option value="108">2008</option>
							<option value="109">2009</option>							
							<option value="110" selected="selected">2010</option>							
							<option value="111">2011</option>					
						</select>
					</td>
				</tr>
				<tr>
					<td>
						Tipo
					</td>
					<td>
						<select name="tipo" style="width: 400px;">
							<option value="1">Metas por Agência (SINERGIA)</option>
							<option value="2">Acompanhamento do Sinergia por Agência</option>
							<option value="3">Acompanhamento do Agente de Crédito [MENSAL]</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" colspan="2">
						<html:submit styleClass="btn_hot" value="Visualizar Relatório"/>					
					</td>
				</tr>			
			</tbody>
			</table>
			</center>	
			</html:form>
			
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>