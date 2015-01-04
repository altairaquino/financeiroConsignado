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
				&nbsp;   Lista de Opera��es    &nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center; font-size: 13px;"> 
						Lista de Opera��es
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'adminPage.do'">&nbsp;
						<input type="button" class="btn_hot" value="Nova Opera��o" onclick="window.location = 'actionOperacao.do?m=novo'">
					</td>
				</tr>
			</tbody>			
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th>
						C�digo
					</th>
					<th>
						Pai
					</th>
					<th>
						Descri��o
					</th>
					<th>
						Legenda
					</th>
					<th>
						Link
					</th>
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_operacao">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="opncodg"/>
					</td>
					<td>
						<bean:write name="b" property="opnpart"/>
					</td>
					<td>
						<a title="Editar" href="actionOperacao.do?m=editar&opncodg=<bean:write name="b" property="opncodg"/>">
							<bean:write name="b" property="opcdesc"/>
						</a>
					</td>
					<td>
						<bean:write name="b" property="opchint"/>
					</td>
					<td>
						<bean:write name="b" property="opclink"/>
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