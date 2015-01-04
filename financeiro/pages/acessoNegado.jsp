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
				&nbsp;   Usuário sem acesso a opção    &nbsp;
			</legend>
			<h2>
				Caro usuário, você não tem acesso a essa opção do sistema. <br>
				Tente efetuar o login novamente para tentar resolver o problema.<br>
				Caso o problema persista entre em contato com o Administrador do Sistema.
			</h2>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th>  </th>
				</tr>		
			
			</tbody>			
			</table>	
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>