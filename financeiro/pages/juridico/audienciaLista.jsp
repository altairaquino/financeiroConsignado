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
				&nbsp;  Audiências do Processo (<bean:write name="processo" property="procnumr"/>) > Listagem &nbsp;
			</legend>
			<table  style="width: 100%">
			<tbody>
				<tr>
					<td>				
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionProcesso.do?m=opcoes&proncodg=<bean:write name="processo" property="proncodg"/>'">&nbsp;&nbsp;
						<input type="button" class="btn_hot" value="Nova Audiência" onclick="window.location = 'actionAudiencia.do?m=novo&proncodg=<bean:write name="processo" property="proncodg"/>'">
					</td>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table  style="width: 100%">
			<tbody>
				<tr>
					<th style="background-color: #DDD; font-weight: bold; text-align: center;" colspan="5"> 
						Audiências do Processo
					</th>
				</tr>
				<logic:empty name="ls_audiencia">
				<tr>
					<td colspan="5">
						Não há audiências para este processo.
					</td>
				</tr>				
				</logic:empty>
				<logic:notEmpty name="ls_audiencia">
				<tr style="background-color: #DDD; font-weight: bold;">
					<th>
						Data
					</th>
					<th>
						Horário
					</th>
					<th>
						Local
					</th>	
					<th>
						Advogado
					</th>		
					<th>
						Observações
					</th>					
				</tr>
				</logic:notEmpty>
				<logic:iterate name="ls_audiencia" id="b">
				<tr>
					<td style="font-weight: bold;">
						<a href="actionAudiencia.do?m=editar&adcncodg=<bean:write name="b" property="adcncodg"/>">
							<bean:write name="b" property="adcddata"/>
						</a>
					</td>
					<td>
						<bean:write name="b" property="adchhora"/>
					</td>
					<td>
						<bean:write name="b" property="adccdccd"/> - <bean:write name="b" property="adccufcd"/>
					</td>
					<td>
						<bean:write name="b" property="adccadvo"/>
					</td>
					<td style="vertical-align: top;">
						<bean:write name="b" property="adccdesc"/>
					</td>
				</tr>
				</logic:iterate>							
			</tbody>			
			</table>
			</div>
		
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>