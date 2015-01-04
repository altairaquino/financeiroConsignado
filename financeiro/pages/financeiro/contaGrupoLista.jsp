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
				&nbsp;  Contas do grupo (<bean:write name="grupoconta" property="gpccdesc"/>) > Lista    &nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionGrupoConta.do?m=lista'"> &nbsp;&nbsp;
						<input type="button" class="btn_hot" value="Nova Conta" onclick="window.location = 'actionContaGrupo.do?m=novo&gpcncodg=<bean:write name="grupoconta" property="gpcncodg"/>'">
					</td>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%">
			<tbody>	
				<tr>
					<th style="width: 15%">
						Código
					</th>
					<th style="width: 70%">
						Descrição
					</th>
					<th style="width: 15%">
						Altera Status
					</th>		
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_contagrupo">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="cogncodg"/>
					</td>
					<td>
						<a href="actionContaGrupo.do?m=editar&cogncodg=<bean:write name="b" property="cogncodg"/>">
							<bean:write name="b" property="cogcdesc"/>			
						</a>
					</td>
					<td>
						<logic:equal name="b" property="coglativ" value="T">
							<a href="actionContaGrupo.do?m=ativaDesativa&cogncodg=<bean:write name="b" property="cogncodg"/>">Desativar</a>
						</logic:equal>						
						<logic:equal name="b" property="coglativ" value="F">
							<a href="actionContaGrupo.do?m=ativaDesativa&cogncodg=<bean:write name="b" property="cogncodg"/>">Ativar</a>
						</logic:equal>						
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