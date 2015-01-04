	
	<%@include file="../topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset style="text-align: center;">
			<legend>
				&nbsp;   Cadastro de Novo Grupo de E-mail    &nbsp;
			</legend>
			<html:form action="actionGrupoEmail" focus="gemcdesc" onsubmit="return window.confirm('Confirmar a inclusão do grupo de E-mail com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>
			<table style="width: 650px;">
			<tbody>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="2"> 
						Cadastro de Novo Grupo de E-mail
					</th>
				</tr>
				<tr>
					<td>
						Nome do Grupo
					</td>
					<td>
						<html:text property="gemcdesc" size="60" maxlength="60"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'actionGrupoEmail.do?m=lista'">
					</td>
					<td style="text-align: right;">						
						<html:submit styleClass="btn_hot">Salvar Grupo</html:submit>
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