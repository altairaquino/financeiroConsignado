	<%@include file="topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset style="height: 250px;">
			<legend class="red">
				&nbsp;   Definição de Senha para Acesso    &nbsp;
			</legend>
			<html:form action="actionEntidade">
			<html:hidden property="m" value="updateSenha"/>
			<html:hidden property="enncodg"/>
			<table style="width: 500px;" align="center">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; text-align: center;"> 
						Definição de Senha para Acesso
					</th>
				</tr>
				<tr>
					<th style="width: 25%;">
						Usuário
					</th>
					<td style="width: 75%;">
						<bean:write name="formEntidade" property="encnome"/>
					</td>				
				</tr>
				<tr>
					<th>
						Login
					</th>
					<td>
						<bean:write name="formEntidade" property="enclogn"/>
					</td>				
				</tr>				
				<tr>
					<th>
						Nova Senha
					</th>
					<td>
						<html:password property="encsenh" size="15" maxlength="15" value=""/>
					</td>				
				</tr>
				<tr>
					<td style="text-align: right;" colspan="2">
						<html:submit styleClass="btn_hot">Salvar Senha</html:submit>
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