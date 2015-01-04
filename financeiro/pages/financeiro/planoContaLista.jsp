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
				&nbsp;   Plano de Contas    &nbsp;
			</legend>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th colspan="4"> 
						Planos de Conta 
					</th>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%">
			<tbody>
				<tr>
					<th>
						Código
					</th>
					<th>
						Plano de Conta
					</th>
					<th>
						Superior
					</th>
					<th>
						&nbsp;
					</th>				
				</tr><logic:empty name="ls_planoconta">
				<tr>
					<td colspan="4">
						Não há Plano de Contas Cadastrado.
					</td>
				</tr>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_planoconta">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="plcncodg"/>
					</td>
					<td>
						<a href="actionPlanoConta.do?m=lista&plcncodg=<bean:write name="b" property="plcncodg"/>">
							<bean:write name="b" property="plccdesc"/>
						</a>
					</td>
					<td>
						<bean:write name="b" property="plccpai"/>
					</td>
					<td>
						<input type="image" src="imagens/detalhe.gif" title="" onclick="window.location = 'actionPlanoConta.do?m=lista&plcncodg=<bean:write name="b" property="plcncodg"/>'">
					</td>
				</tr>
				</logic:iterate>
				<logic:present name="pai">
				<tr>
					<td colspan="2">
						<logic:present name="avo">
							<input type="button" value="Voltar" onclick="window.location = 'actionPlanoConta.do?m=lista&plcncodg=<bean:write name="avo"/>'">
						</logic:present>
						<logic:notPresent name="avo">
							<input type="button" value="Voltar" onclick="window.location = 'actionPlanoConta.do?m=lista'">
						</logic:notPresent>
					</td>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Novo Plano de Conta" onclick="window.location = 'actionPlanoConta.do?m=novo&plcnpai=<bean:write name="pai"/>'">
					</td>
				</tr>
				</logic:present>
			
			</tbody>			
			</table>	
			</div>
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>