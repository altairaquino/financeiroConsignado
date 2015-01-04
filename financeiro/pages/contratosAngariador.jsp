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
				&nbsp;   (<bean:write name="usuario" property="encnome"/>) > LISTA DE CONTRATOS EM ABERTO   &nbsp;
			</legend>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%;">
			<tbody>
			 	<logic:notEmpty name="ls_contrato">
				<tr>
					<th>
						Data
					</th>
					<th>
						Número
					</th>
					<th>
						Cliente
					</th>
					<th>
						Valor
					</th>
					<th>
						Status
					</th>					
				</tr>
				</logic:notEmpty>	
				<logic:empty name="ls_contrato">
				<tr>
					<td colspan="5" style="color: red;"> 
						NÃO HÁ REGISTRO DE CONTRATOS NO SISTEMA  
					</td>
				</tr>				
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_contrato">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td style="text-align: center;">
						<bean:write name="b" property="ctdcadt"/>
					</td>
					<td> 
						<bean:write name="b" property="ctnnumr"/> 
					</td>
					<td>
						<bean:write name="b" property="ctcnmcl"/>
					</td>
					<th> 
						R$ <bean:write name="b" property="ctyvalr"/> 
					</th>
					<td>
						<bean:write name="b" property="ctcdcsc"/>
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