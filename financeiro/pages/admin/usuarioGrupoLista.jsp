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
				&nbsp;  <bean:write name="grupo" property="grcdesc"/> > Usuários    &nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center; font-size: 13px;"> 
						Lista de Usuários
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionGrupo.do?m=lista'">
					</td>
				</tr>
			</tbody>			
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th>
						Grupo
					</th>
					<th>
						Usuário
					</th>
					<th>
						Delete
					</th>
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_usuariogrupo">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="ugcdcgr"/>
					</td>
					<td>
						<bean:write name="b" property="ugcnmen"/>
					</td>
					<td>
						<input type="image" src="imagens/delete.gif" onclick="if (window.confirm('Excluir usuário do grupo?')){window.location = 'actionUsuarioGrupo.do?m=delete&ugncodg=<bean:write name="b" property="ugncodg"/>&grncodg=<bean:write name="grupo" property="grncodg"/>';}">
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