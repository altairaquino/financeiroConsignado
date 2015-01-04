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
			<legend class="red">
				&nbsp;  Relação de Filiais &nbsp;
			</legend>
			<table  style="width: 100%">
			<tbody>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Novo" onclick="window.location = 'actionFilial.do?m=novo'">&nbsp;&nbsp;
						<%-- 
						<input type="button" value="Pesquisa" onclick="window.location = 'fornecedorPesquisa.do'">
						--%>
					</td>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table  style="width: 100%">
			<tbody>
				<tr>
					<th style="background-color: #DDD; font-weight: bold; text-align: center;" colspan="4"> 
						Relação de Filiais
					</th>
				</tr>
				<tr style="background-color: #DDD; font-weight: bold;">
					<th>
						Código
					</th>
					<th>
						Nome
					</th>
					<th>
						Cidade
					</th>
					<th>
						Fone
					</th>
				</tr>
				<logic:iterate name="ls_filial" id="b">
				<tr>
					<td>
						<bean:write name="b" property="flncodg"/>
					</td>
					<td>
						<a href="actionFilial.do?m=editar&flncodg=<bean:write name="b" property="flncodg"/>">
							<bean:write name="b" property="flcnome"/>
						</a>						
					</td>
					<td>
						<bean:write name="b" property="flcdccd"/>/<bean:write name="b" property="flcufcd"/>
					</td>
					<td>
						<bean:write name="b" property="flcfone"/>
					</td>
				</tr>
				</logic:iterate>							
			</tbody>			
			</table>
			</div>
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>