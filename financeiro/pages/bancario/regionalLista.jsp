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
				&nbsp;   Listagem de Regionais    &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="3"> 
						Lista de Regionais
					</th>
				</tr>
				<tr>
					<th colspan="3">
						<input type="button" class="btn_hot" value="Nova Regional" onclick="window.location = 'actionRegional.do?m=novo'">
					</th>
				</tr>
				<tr>
					<th>
						Código
					</th>
					<th>
						Descrição
					</th>
					<th>
						Funcionários
					</th>										
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_regional">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="rgncodg"/>
					</td>
					<td>
						<a href="actionRegional.do?m=editar&rgncodg=<bean:write name="b" property="rgncodg"/>">
							<bean:write name="b" property="rgcdesc"/>						
						</a>
					</td>
					<td>
						<input type="image" src="imagens/usuario.gif" onclick="window.location = 'actionFuncRegional.do?m=lista&rgncodg=<bean:write name="b" property="rgncodg"/>'">
					</td>									
				</tr>
				</logic:iterate>								
			</tbody>			
			</table>		
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>