	<%@include file="topo.jsp" %>
	
			
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend class="red">
				&nbsp;   Pesquisa de Agente de Crédito - Novo Contrato  &nbsp;
			</legend>
			<html:form method="action" action="actionEntidade" focus="encnome">
			<html:hidden property="m" value="pesquisaAngariadorContrato"/>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="4">Use, no mínimo, 3 caracteres para a pesquisa.</th>
				</tr>
				<tr>
					<td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						Nome, CPF &nbsp;<html:text property="encnome" size="60" maxlength="50"></html:text>&nbsp;
						<html:submit styleClass="btn_hot">Pesquisar</html:submit>
					</td>
				</tr>
				<logic:present name="ls_entidade">
				<tr>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>
				<tr>
					<th>
						Código
					</th>
					<th>
						Nome
					</th>
					<th>
						C.P.F.
					</th>
					<th>
						Data de Nascimento
					</th>
				</tr>
				<logic:iterate name="ls_entidade" id="b">
				<tr>
					<td>
						<bean:write name="b" property="enncodg"/>
					</td>
					<td>
						<a href="actionContrato.do?m=novo&enncodg=<bean:write name="b" property="enncodg"/>">
							<bean:write name="b" property="encnome"/>
						</a>						
					</td>
					<td>
						<bean:write name="b" property="encdocm"/>
					</td>
					<td>
						<bean:write name="b" property="endnasc"/>
					</td>
				</tr>
				</logic:iterate>
				</logic:present>		
			
			</tbody>			
			</table>
			</html:form>
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>