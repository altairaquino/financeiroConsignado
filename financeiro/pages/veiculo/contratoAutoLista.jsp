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
				&nbsp;   Listagem de Contratos    &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="6"> Lista de Contratos </th>
				</tr>
				<tr>
					<th colspan="6">
						<input type="button" value="Nova Pesquisa" onclick="window.location = 'contratoAutoPesquisaCPF.do'">
					</th>
				</tr>				
				<tr>
					<th>
						Número
					</th>
					<th>
						Data Base
					</th>
					<th>
						Cliente
					</th>
					<th>
						Valor
					</th>					
					<th>
						Plano
					</th>
					<th>
						Parcela
					</th>
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_contratoauto">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<th>
						<a href="actionContratoAuto.do?m=opcoes&ctancodg=<bean:write name="b" property="ctancodg"/>">
							<bean:write name="b" property="ctacnumr"/>
						</a>						
					</th>
					<td>
						<bean:write name="b" property="ctadbase"/>
					</td>
					<td>
						<bean:write name="b" property="ctacnmen"/>
					</td>
					<th>
						R$ <bean:write name="b" property="ctayvalr"/>
					</th>					
					<td>
						<bean:write name="b" property="ctanplan"/> meses
					</td>
					<th>
						R$ <bean:write name="b" property="ctayvlpc"/>
					</th>
				</tr>
				</logic:iterate>
				<tr>
					<th colspan="6">
						<input type="button" value="Nova Pesquisa" onclick="window.location = 'contratoAutoPesquisaCPF.do'">
					</th>
				</tr>				
			</tbody>			
			</table>		
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>