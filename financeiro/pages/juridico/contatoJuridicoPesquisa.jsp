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
				&nbsp;  Relação de Contatos do Jurídico &nbsp;
			</legend>
			<html:form method="action" action="actionEntidade" focus="encnome">
			<html:hidden property="m" value="pesquisaContatoJuridico"/>
			<table  style="width: 100%">
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
			</tbody>
			</table>
			</html:form>
			<logic:present name="ls_entidade">
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table  style="width: 100%">
			<tbody>
				<tr>
					<th style="background-color: #DDD; font-weight: bold; text-align: center;" colspan="4"> 
						Relação de Contatos do Jurídico
					</th>
				</tr>
				<tr style="background-color: #DDD; font-weight: bold;">
					<th style="width: 10%;">
						Código
					</th>
					<th style="width: 70%;">
						Nome
					</th>
					<th style="width: 20%;">
						CPF
					</th>					
				</tr>
				<% int c = 0; %>
				<logic:iterate name="ls_entidade" id="b">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="enncodg"/>
					</td>
					<td>
						<a href="actionEntidade.do?m=opcoesContatoJuridico&enncodg=<bean:write name="b" property="enncodg"/>">
							<bean:write name="b" property="encnome"/>
						</a>						
					</td>
					<td>
						<bean:write name="b" property="encdocm"/>
					</td>			
				</tr>
				</logic:iterate>							
			</tbody>			
			</table>
			</div>
			</logic:present>
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>