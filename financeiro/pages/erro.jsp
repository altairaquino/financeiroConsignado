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
				&nbsp;   ERRO NO SISTEMA    &nbsp;
			</legend>
			<br><br><br><br><br>
			<center>			
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th style="text-align: center; font-size: 15px; color: red;">
						ERRO NO SISTEMA
					</th>
				</tr>
				<tr>
					<td style="text-align: justify; font-size: 15px;">
						CARO USUÁRIO, OCORREU UM ERRO INESPERADO NO SISTEMA.<br>
						CLIQUE NO LINK ABAIXO PARA VOLTAR E, SE O ERRO PERSISTIR
						INFORME O ERRO AO ADMINSTRADOR DO SISTEMA.<br>
						CODIGO DO ERRO: <b> <bean:write name="erro"/> </b>						
					</td>
				</tr>
				<tr>
					<td style="text-align: center;">
						<a href="javascript:history.back()">Voltar</a>
					</td>
				</tr>
			
			</tbody>			
			</table>
			</center>
			<br><br><br><br><br>
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>