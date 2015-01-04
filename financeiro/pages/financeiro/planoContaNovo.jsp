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
				&nbsp;   Cadastro de Plano de Contas    &nbsp;
			</legend>
			<html:form action="actionPlanoConta" focus="plccdesc">
			<html:hidden property="m" value="cadastro"/>
			<html:hidden property="plcnpai"/>
			<html:hidden property="plcnempr"/>
			<html:hidden property="plcntipo"/>
			<table style="width: 550px;">
			<tbody>
				<tr>
					<th colspan="2">
						Novo Plano de Contas
					</th>
				</tr>
				<tr>
					<th>
						Superior
					</th>
					<td style="font-weight: bold;color: red;">
						<bean:write name="formPlanoConta" property="plccpai"/>
					</td>
				</tr>
				<tr>
					<th>
						Nome do Plano
					</th>
					<td>
						<html:text property="plccdesc" size="60" maxlength="40"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Cancelar" onclick="window.location = 'actionPlanoConta.do?m=lista&plcncodg=<bean:write name="formPlanoConta" property="plcnpai"/>'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot" value="Salvar Plano de Conta"></html:submit>
					</td>
				</tr>			
			</tbody>			
			</table>
			</html:form>		
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>