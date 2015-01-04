	<%@include file="../topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Caixas    &nbsp;
			</legend>
			<table style="width: 650px;">
			<tbody>
				<tr>
					<th colspan="5"> 
						Relação de caixas dos últimos 30 dias
					</th>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%">
			<tbody>			
				<logic:empty name="ls_caixa">
				<tr>
					<th colspan="5" style="color: red;"> 
						Não há caixas registrados no sistema.
					</th>
				</tr>
				</logic:empty>
				<logic:notEmpty name="ls_caixa">
				<tr>
					<th>
						Data do Caixa
					</th>
					<th>
						Data do Fechamento
					</th>
					<th>
						Responsável
					</th>
					<th>
						Saldo
					</th>
					<th>
						Opções
					</th>
				</tr>
				</logic:notEmpty>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_caixa">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="caxddata"/>
					</td>
					<td>
						<bean:write name="b" property="caxdfech"/>
					</td>
					<td>
						<bean:write name="b" property="caxcresp"/>
					</td>
					<td>
						<bean:write name="b" property="caxysald"/>
					</td>
					<td>						
						<input type="button" class="btn_hot" value="Movimento" onclick="window.location = 'actionMovCaixa.do?m=lista&caxncodg=<bean:write name="b" property="caxncodg"/>'">
					</td>
				</tr>
				</logic:iterate>
							
			</tbody>			
			</table>	
			
			</div>
		</fieldset>
			
		</div>
				
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>