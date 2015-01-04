	<%@include file="topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
	
</head>		
<body>	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Alteração de Senha do Usuário    &nbsp;
			</legend>
			
			<html:form action="actionEntidade" focus="encsenh">
			<html:hidden property="m" value="alteraSenha"/>
			<table style="width: 300px;">
			<tbody>
				<tr>
					<td>
						Senha Atual
					</td>
					<td>
						<html:password property="encsenh" size="16" maxlength="15"/>
					</td>
				</tr>
				<tr>
					<td>
						Nova Senha
					</td>
					<td>
						<html:password property="encsen2" size="16" maxlength="15"/>
					</td>
				</tr>
				<tr>
					<td>
						Repetir Nova Senha
					</td>
					<td>
						<html:password property="encsen3" size="16" maxlength="15"/>
					</td>
				</tr>
				<tr>
					<td>
						<html:submit styleClass="btn_hot" value="Enviar"></html:submit>
					</td>
				</tr>			
			</tbody>			
			</table>
			</html:form>
		</fieldset>
					
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>