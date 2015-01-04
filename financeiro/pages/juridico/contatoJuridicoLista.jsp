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
			<table  style="width: 100%">
			<tbody>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Novo" onclick="window.location = 'actionEntidade.do?m=novoContatoJuridico'">&nbsp;&nbsp;
						<input type="button" class="btn_hot" value="Pesquisa" onclick="window.location = 'contatoJuridicoPesquisa.do'">						
					</td>
				</tr>
			</tbody>
			</table>
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
		
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>