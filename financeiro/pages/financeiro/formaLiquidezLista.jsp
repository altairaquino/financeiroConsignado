	<%@include file="../topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
	
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset>
		
			<legend>
				&nbsp;   Formas de Pagamento Cadastradas    &nbsp;
			</legend>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th colspan="3">
						<input type="button" value="Nova Forma" onclick="window.location = 'actionFormaLiquidez.do?m=novo'">
					</th>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 500px;">
			<tbody>
				<logic:notEmpty name="ls_formaliquidez">
				<tr>
					<th>
						Descrição
					</th>
					<th>
						Liquidação Imediata?
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				</logic:notEmpty>
				<logic:empty name="ls_formaliquidez">
				<tr>
					<td colspan="3">
						Não há Forma de Pagamento Cadastrada.
					</td>
				</tr>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_formaliquidez">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="flqcdesc"/>
					</td>
					<td>
						<logic:equal name="b" property="flqlimed" value="T">
							Sim
						</logic:equal>
						<logic:equal name="b" property="flqlimed" value="F">
							Não
						</logic:equal>
					</td>
					<td>
						<input type="image" src="imagens/editar.gif" title="Editar Forma de Liquidez" onclick="window.location = 'actionFormaLiquidez.do?m=editar&flqncodg=<bean:write name="b" property="flqncodg"/>'">
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