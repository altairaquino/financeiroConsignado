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
				&nbsp;   Grupo Conta > Lista    &nbsp;
			</legend>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%">
			<tbody>			
				<tr>
					<th style="width: 15%">
						Código
					</th>
					<th style="width: 70%">
						Descrição
					</th>
					<th style="width: 15%">
						Contas
					</th>					
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_grupoconta">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="gpcncodg"/>
					</td>
					<td>
						<bean:write name="b" property="gpccdesc"/>
					</td>
					<td>						
						<input type="button" class="btn_hot" value="Contas" onclick="window.location = 'actionContaGrupo.do?m=lista&gpcncodg=<bean:write name="b" property="gpcncodg"/>'">
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