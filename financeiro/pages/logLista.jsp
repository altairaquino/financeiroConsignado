	<%@include file="topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		
		<div id="content">		
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   LOGS DE ACESSO   &nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<td colspan="3" style="font-size: 13px; font-weight: bold;">
						<bean:write name="entidade" property="encnome"/> [<bean:write name="entidade" property="encdcte"/>]
					</td>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th>
						Data e hora de Entrada
					</th>
					<th>
						Data e hora de Saída
					</th>
					<th>
						IP de Origem
					</th>	
				</tr>
				<logic:empty name="ls_log">
				<tr>
					<td colspan="3" style="color: red;"> 
						NÃO HÁ LOG DE ACESSO. 
					</td>
				</tr>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_log">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="lgdentr"/> 
					</td>
					<td>
						<bean:write name="b" property="lgdsaid"/>
					</td>
					<td>
						<bean:write name="b" property="lgcreqt"/>
					</td>						
				</tr>
				</logic:iterate>	
			</tbody>			
			</table>	
			</div>
		</fieldset>			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>