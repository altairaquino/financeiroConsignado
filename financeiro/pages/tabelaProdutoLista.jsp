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
				&nbsp;   <bean:write name="produto" property="pdcdesc"/> > Tabela    &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="10"> 
						<bean:write name="produto" property="pdcdcbc"/> - <bean:write name="produto" property="pdcdesc"/> 
					</th>
				</tr>
				<tr>
					<th colspan="10">
						<input type="button" value="Nova Tabela" onclick="window.location = 'actionTabelaProduto.do?m=novo'">
					</th>
				</tr>
				<tr>
					<th>
						Número
					</th>
					<th>
						Nome da Tabela
					</th>
					<th>
						Parc.
					</th>
					<th>
						Car�ncia
					</th>
					<th>
						Prazo
					</th>
					<th>
						% Recebido
					</th>
					<th>
						Imposto
					</th>
					<th>
						Carg. Trib.
					</th>
					<th>
						Líquido
					</th>
					<th>
						Zera?
					</th>		
					<th>
						&nbsp;
					</th>
				</tr>
				<% int c = 0; %>				
				<logic:iterate id="b" name="ls_tabelaproduto">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="tpcnumr"/>
					</td>
					<td>
						<bean:write name="b" property="tpcdesc"/>
					</td>
					<td>
						<bean:write name="b" property="tpnqtpc"/>
					</td>
					<td>
						<bean:write name="b" property="tpncare"/> dias
					</td>
					<td>
						<bean:write name="b" property="tpnpraz"/> meses
					</td>
					<th>
						<bean:write name="b" property="tpnexat"/> %
					</th>					
					<td>
						<bean:write name="b" property="tpnimpt"/> %
					</td>
					<td>
						<bean:write name="b" property="tpncatb"/> %
					</td>
					<th>
						<bean:write name="b" property="tpnliqu"/> %
					</th>
					<td>
						<logic:equal name="b" property="tplzera" value="T">
							SIM
						</logic:equal>
						<logic:equal name="b" property="tplzera" value="F">
							NAO
						</logic:equal>						
					</td>
					<td>
						<input type="image" src="imagens/editar.gif" title="Editar Produto" onclick="window.location = 'actionTabelaProduto.do?m=editar&pdncodg=<bean:write name="b" property="tpncodg"/>'">&nbsp;						
						<input type="image" src="imagens/usuario.gif" title="Tabela dos Agentes de Crédito" onclick="window.location = 'actionTabelaAngariador.do?m=listaAng&tpncodg=<bean:write name="b" property="tpncodg"/>'">
					</td>
				</tr>
				</logic:iterate>
				<tr>
					<th colspan="10">
						<input type="button" value="Nova Tabela" onclick="window.location = 'actionTabelaProduto.do?m=novo'">
					</th>
				</tr>				
				<tr>
					<th colspan="10">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionProduto.do?m=lista'">
					</th>
				</tr>
			</tbody>			
			</table>		
		</fieldset>
					
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>