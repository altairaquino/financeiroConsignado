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
				&nbsp;  Andamento do Processo (<bean:write name="processo" property="procnumr"/>) > Listagem &nbsp;
			</legend>
			<table  style="width: 100%">
			<tbody>
				<tr>
					<td>						
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionProcesso.do?m=opcoes&proncodg=<bean:write name="processo" property="proncodg"/>'">
						<input type="button" class="btn_hot" value="Novo" onclick="window.location = 'actionAndamentoProcesso.do?m=novo&proncodg=<bean:write name="processo" property="proncodg"/>'">&nbsp;&nbsp;
					</td>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table  style="width: 100%">
			<tbody>
				<tr>
					<th style="background-color: #DDD; font-weight: bold; text-align: center;" colspan="4"> 
						Andamento do Processo
					</th>
				</tr>
				<tr style="background-color: #DDD; font-weight: bold;">
					<th>
						Data
					</th>
					<th>
						Fase
					</th>
					<th>
						Descrição
					</th>					
					<th>
						Responsável
					</th>					
				</tr>
				<logic:iterate name="ls_andamentoprocesso" id="b">
				<tr>
					<td style="font-weight: bold;">
						<bean:write name="b" property="anpddata"/>
					</td>
					<td>
						<a href="actionAndamentoProcesso.do?m=editar&anpncodg=<bean:write name="b" property="anpncodg"/>">
							<bean:write name="b" property="anpcdcfap"/>
						</a>
					</td>
					<td>
						<bean:write name="b" property="anpcdesc"/>
					</td>
					<td>
						<bean:write name="b" property="anpcnmen"/>
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