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
				&nbsp;   P�gina de Administra��o do Sistema    &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="2">
						Administra��o do Sistema
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
						Opera��es
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