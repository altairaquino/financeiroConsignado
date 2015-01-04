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
				&nbsp;   Página de Administração do Sistema    &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="2">
						Administração do Sistema
					</th>
				</tr>
				<tr>
					<th>
						Grupos
					</th>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionGrupo.do?m=lista'"> 
					</td>
				</tr>
				<tr>
					<th>
						Operações
					</th>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionOperacao.do?m=lista'"> 
					</td>
				</tr>
			
			</tbody>
			</table>
		
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>