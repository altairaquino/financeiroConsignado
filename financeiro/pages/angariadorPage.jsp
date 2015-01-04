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
				&nbsp;   Opções do Agente de Crédito    &nbsp;
			</legend>
			<h3><bean:write name="angariador" property="encnome"/> (Gráfico de desempenho <input type="image" src="imagens/graf_bar.gif" title="Gráfico de Desempenho" onclick="window.open('actionEntidade.do?m=relatoriosAngariador&enncodg=<bean:write name="angariador" property="enncodg"/>&data1=01/01/2009&data2=01/01/2009&tipo=4')">)</h3>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; text-align: center;">
						Opções do Agente de Crédito 
					</th>
				</tr>
				<tr>
					<td>
						Dados Cadastrais do Agente de Crédito
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionEntidade.do?m=dadosAngariador&enncodg=<bean:write name="angariador" property="enncodg"/>'">
					</td>
				</tr>
				<!-- 
				<tr>
					<td>
						Informa Gerência/Funcionário Agência
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionFuncionarioAgencia.do?m=lista&enncodg=<bean:write name="angariador" property="enncodg"/>'">
					</td>
				</tr>
				 -->
				<tr>
					<td>
						Tabela de Comissões do Agente de Crédito
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionTabelaAngariador.do?m=lista&enncodg=<bean:write name="angariador" property="enncodg"/>'">
					</td>
				</tr>
				<%-- 
				<tr>
					<td>
						Metas/SPREAD/Sinergia/Regional
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionAngariador.do?m=dados&enncodg=<bean:write name="angariador" property="enncodg"/>'">
					</td>
				</tr>
				 --%>
				<tr>
					<td>
						Dados para Pagamento (Conta Corrente)
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionContaCorrente.do?m=lista&enncodg=<bean:write name="angariador" property="enncodg"/>'">
					</td>
				</tr>				
				<html:form action="actionEntidade" method="post">
				<html:hidden property="m" value="relatoriosAngariador"/>
				<input type="hidden" name="enncodg" value="<bean:write name="angariador" property="enncodg"/>">
				<tr>
					<td>
						<select name="tipo" style="width: 300px;">
							<option value="1">Relatório de Comissões por Período</option>
							<option value="2">Relatório de Produção por Período</option>
							<option value="3">Relatório de Extornos de Contratos</option>
						</select>&nbsp;Período:&nbsp;
						<input type="text" id="data1" name="data1" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')" readonly="readonly"> <input type="image" id="b1" src="jscalendar/img.gif"> até
						<input type="text" id="data2" name="data2" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')" readonly="readonly"> <input type="image" id="b2" src="jscalendar/img.gif">
					</td>
					<td>
						<input type="submit" class="btn_hot" value="Visualizar">
					</td>
				</tr>
				</html:form>
				<tr>
					<td>
						Visualizar Contratos pendentes de Físico
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionContrato.do?m=semFisicoDoAngariador&enncodg=<bean:write name="angariador" property="enncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Log de ultimos acessos.
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionLog.do?m=lista&enncodg=<bean:write name="angariador" property="enncodg"/>'">
					</td>
				</tr>
			
			</tbody>			
			</table>	
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
	<script type="text/javascript">
	    Calendar.setup({
	        inputField     :    "data1",     // id of the input field
	        ifFormat       :    "%d/%m/%Y",      // format of the input field
	        button         :    "b1",  // trigger for the calendar (button ID)
	        align          :    "BR",           // alignment (defaults to "Bl")
	        singleClick    :    true
	    });
	    Calendar.setup({
	        inputField     :    "data2",     // id of the input field
	        ifFormat       :    "%d/%m/%Y",      // format of the input field
	        button         :    "b2",  // trigger for the calendar (button ID)
	        align          :    "BR",           // alignment (defaults to "Bl")
	        singleClick    :    true
	    });
	</script>
</body>
</html>