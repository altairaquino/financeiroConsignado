	<%@include file="topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;  LISTA DE CONTRATOS PARA CONFIRMAÇÃO DE O.P. &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<td colspan="7" style="background-color: #DDD; font-weight: bold;text-align: center;">
						RELAÇÃO DE CONTRATOS PARA CONFIRMAÇÃO DE O.P.
					</td>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%">
			<tbody>
				<logic:notEmpty name="ls_contrato">
				<tr>
					<th>
						Averbação
					</th>
					<th>
						Número
					</th>
					<th>
						Cliente
					</th>
					<th>
						Agente de Crédito
					</th>
					<th>
						&nbsp;
					</th>		
				</tr>
				</logic:notEmpty>				
				<logic:empty name="ls_contrato">
				<tr>
					<td colspan="7" style="color: red;"> 
						NÃO HÁ CONTRATOS PARA CONFIRMAÇÃO DE O.P.
					</td>
				</tr>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_contrato">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="ctdverb"/>
					</td>
					<td>
						<a onclick="window.open('actionContrato.do?m=dadosWindow&ctncodg=<bean:write name="b" property="ctncodg"/>', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-400)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-570)/2:0) + ',height = 400, width = 570');">
							<bean:write name="b" property="ctnnumr"/>						
						</a>
						<!--  
						<img src="imagens/lupa.gif" alt="pesquisar" title="Visualiza Dados" style="cursor: pointer;top:10px;"  onclick="window.open('actionContrato.do?m=dadosWindow&ctncodg=<bean:write name="b" property="ctncodg"/>', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-400)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-570)/2:0) + ',height = 400, width = 570');"/>
						-->
					</td>
					<td style="color: #D00; font-weight: bold;">
						<!-- 
						<img src="imagens/lupa.gif" alt="pesquisar" title="Visualiza Dados" style="cursor: pointer;top:10px;"  onclick="window.open('actionEntidade.do?m=dadosEntidadeWindow&enncodg=<bean:write name="b" property="ctnc2en"/>', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-430)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-570)/2:0) + ',height = 430, width = 570');"/>
						 -->
						<a onclick="window.open('actionEntidade.do?m=dadosEntidadeWindow&enncodg=<bean:write name="b" property="ctnc2en"/>', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-430)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-570)/2:0) + ',height = 430, width = 570');">
							<bean:write name="b" property="ctcnmcl"/>						
						</a>
					</td>
					<th>
						<bean:write name="b" property="ctcnman"/>
					</th>
					<td>
						<html:form action="actionContrato">
							<html:hidden property="m" value="confirmaSaqueOP"/>
							<html:hidden name="b" property="ctncodg"/>
							<input type="button" class="btn_hot" value="Confirmar" onclick="if (window.confirm('Confirmar o saque da OP para este contrato?')){this.form.submit();}">
						</html:form>
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