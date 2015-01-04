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
				&nbsp;   Relatórios Diversos (Por Setor)  &nbsp;
			</legend>
			<table style="width: 650px;" align="center">
			<tbody>
				<tr>
					<td style="text-align: right;" colspan="3">
						&nbsp;
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="3">
						Relat�rios Diversos (Por Setor)
					</th>
				</tr>
				<tr>
					<th>
						Descrição
					</th>
					<th>
						Setor
					</th>
					<th>
						Visualizar
					</th>
				</tr>
				<tr>
					<td>
						Rela��o de Funcion�rio Ativos e Inativos (Folha)
					</td>
					<td>
						RH					
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionParametro.do?m=relatorio&tipo=1'">
					</td>
				</tr>										
				<tr>
					<td>
						Rela��o de Agente de Cr�dito por Tipo
					</td>
					<td>
						Banc�rio					
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionParametro.do?m=relatorio&tipo=2'">
					</td>
				</tr>										
				<tr>
					<td>
						Pend�ncia de F�sicos por  Agente de Cr�dito
					</td>
					<td>
						Formaliza��o					
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionParametro.do?m=relatorio&tipo=3'">
					</td>
				</tr>										
			</tbody>
			</table>
			
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>