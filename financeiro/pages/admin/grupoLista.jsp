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
				&nbsp;   Lista de Grupos    &nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center; font-size: 13px;"> 
						Lista de Grupos
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'adminPage.do'">&nbsp;
						<input type="button" class="btn_hot" value="Novo Grupo" onclick="window.location = 'actionGrupo.do?m=novo'">
					</td>
				</tr>
			</tbody>			
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th>
						Código
					</th>
					<th>
						Descrição
					</th>
					<th>
						&nbsp;
					</th>	
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_grupo">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="grncodg"/>
					</td>
					<td>
						<a title="Editar" href="actionGrupo.do?m=editar&grncodg=<bean:write name="b" property="grncodg"/>">
							<bean:write name="b" property="grcdesc"/>
						</a>
					</td>
					<td>
						<input type="button" class="btn_hot" value="Usuários" onclick="window.location = 'actionUsuarioGrupo.do?m=lista&grncodg=<bean:write name="b" property="grncodg"/>'">
						<input type="button" class="btn" value="Operações" onclick="window.location = 'actionOperacaoGrupo.do?m=lista&grncodg=<bean:write name="b" property="grncodg"/>'">
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