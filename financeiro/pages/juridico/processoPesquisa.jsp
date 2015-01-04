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
				&nbsp;   Pesquisa Processo &nbsp;
			</legend>
			<html:form method="action" action="actionProcesso" focus="procnumr">
			<html:hidden property="m" value="pesquisa"/>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="4">Use, no mínimo, 3 caracteres para a pesquisa.</th>
				</tr>
				<tr>
					<td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						Nome, CPF, Número &nbsp;<html:text property="procnumr" size="60" maxlength="50"/>&nbsp;
						<html:submit styleClass="btn_hot">Pesquisar</html:submit>
					</td>
				</tr>
			</tbody>
			</table>
			</html:form>
			<br>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table  style="width: 100%">
			<tbody>
				<logic:present name="ls_processo">
				<tr style="background-color: #DDD; font-weight: bold;">
					<th>
						Nº do Processo
					</th>
					<th>
						Nome do Cliente
					</th>
					<th>
						C.P.F. do Cliente
					</th>
				</tr>
				<% int i = 0; %>
				<logic:iterate name="ls_processo" id="b">
				<tr style="background-color: <%= ((i++%2) == 1)?"#FFF":"#BBB" %>;">
					<td>
						<bean:write name="b" property="procnumr"/>
					</td>
					<td>
						<a href="actionProcesso.do?m=opcoes&proncodg=<bean:write name="b" property="proncodg"/>">
							<bean:write name="b" property="procnmcl"/>
						</a>						
					</td>
					<td>
						<bean:write name="b" property="proccpf"/>
					</td>					
				</tr>
				</logic:iterate>
				</logic:present>		
			
			</tbody>			
			</table>
			</div>
			
		
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>