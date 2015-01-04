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
				&nbsp;   Lista de Grupos de E-mail   &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="3"> Lista de Grupos de E-mail </th>
				</tr>
				<tr>
					<th colspan="3">
						<input type="button" class="btn" value="Novo Grupo de E-mail" onclick="window.location = 'actionGrupoEmail.do?m=novo'">
					</th>
				</tr>
				<tr>
					<th>
						Código
					</th>
					<th>
						Nome do Grupo
					</th>
					<th>
						Opções
					</th>
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_grupoemail">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="gemncodg"/>
					</td>
					<td>
						<a href="actionGrupoEmail.do?m=editar&gemncodg=<bean:write name="b" property="gemncodg"/>">
							<bean:write name="b" property="gemcdesc"/>
						</a>
					</td>
					<td>
						<input type="image" src="imagens/usuario.gif" title="Participantes" onclick="window.location = 'actionListaEmail.do?m=lista&gemncodg=<bean:write name="b" property="gemncodg"/>'">
					</td>
				</tr>
				</logic:iterate>
				<tr>
					<th colspan="3">
						<input type="button" class="btn_hot" value="Enviar E-mail" onclick="window.location = 'emailEnviar.do'">
					</th>
				</tr>				
			</tbody>			
			</table>		
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>