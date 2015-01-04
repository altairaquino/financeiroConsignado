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
				&nbsp;   Contas do Grupo > Nova Conta &nbsp;
			</legend>
			<html:form action="actionContaGrupo" focus="cogcdesc" onsubmit="return window.confirm('Confirmar cadastro de Conta no Grupo?');">
			<html:hidden property="m" value="cadastro"/>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center; font-size: 14px;">
						Cadastro de Conta no Grupo
					</th>
				</tr>		
				<tr>
					<th style="width: 25%">
						Grupo Conta
					</th>
					<td style="width: 75%">
						<input type="hidden" name="cogncggpc" value="<bean:write name="grupoconta" property="gpcncodg"/>">
						<bean:write name="grupoconta" property="gpccdesc"/>
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Descrição da Conta
					</th>
					<td style="width: 75%">
						<html:text property="cogcdesc" size="40" maxlength="39"/>						
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'actionContaGrupo.do?m=lista&gpcncodg=<bean:write name="grupoconta" property="gpcncodg"/>'">					
					</td>
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot" value="Salvar Conta"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr>
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