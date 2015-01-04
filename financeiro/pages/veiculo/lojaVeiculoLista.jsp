	<%@include file="../topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
			
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">	
		<html:errors/>
		<fieldset>
			<legend class="red">
				&nbsp;  Relação de Lojas de Veículos &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<td>
						<input type="button" value="Novo" onclick="window.location = 'actionEntidade.do?m=novoLojaVeiculo'">						
					</td>
				</tr>		
				<tr>
					<th>Relação de Lojas de Veículos</th>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%">
			<tbody>
				<tr>
					<th style="background-color: #DDD; font-weight: bold; text-align: center;" colspan="4"> 
						Relação de Lojas de Veículos
					</th>
				</tr>
				<logic:notEmpty name="ls_entidade">
				<tr style="background-color: #DDD; font-weight: bold;">
					<th>
						Código
					</th>
					<th>
						Nome
					</th>
					<th>
						CNPJ
					</th>
					<th>
						TeleFone
					</th>
				</tr>
				</logic:notEmpty>
				<logic:empty name="ls_entidade">
				<tr>
					<th colspan="4" style="color: red;">
						Não há loja cadastrada no sistema.
					</th>
				</tr>
				</logic:empty>
				<logic:iterate name="ls_entidade" id="b">
				<tr>
					<td>
						<bean:write name="b" property="enncodg"/>
					</td>
					<td>
						<a href="actionEntidade.do?m=dadosLojaVeiculo&enncodg=<bean:write name="b" property="enncodg"/>">
							<bean:write name="b" property="encnome"/>
						</a>						
					</td>
					<td>
						<bean:write name="b" property="encdocm"/>
					</td>
					<td>
						<bean:write name="b" property="encfone"/>
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