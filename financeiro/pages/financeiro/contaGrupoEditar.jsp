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
				&nbsp;   Contas do Grupo > Alteração &nbsp;
			</legend>
			<html:form action="actionContaGrupo" focus="cogcdesc" onsubmit="return window.confirm('Confirmar a alteração de Conta no Grupo?');">
			<html:hidden property="m" value="update"/>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center; font-size: 14px;">
						Alteração de Conta no Grupo
					</th>
				</tr>		
				<tr>
					<th style="width: 25%">
						Grupo Conta
					</th>
					<td style="width: 75%">
						<html:select property="cogncggpc" style="width: 200px;">
							<html:optionsCollection name="ls_grupoconta" label="gpccdesc" value="gpcncodg"/>
						</html:select>						
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Descrição da Conta
					</th>
					<td style="width: 75%">
						<html:text property="cogcdesc" size="50"/>						
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