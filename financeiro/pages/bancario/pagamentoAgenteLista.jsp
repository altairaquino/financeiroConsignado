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
				&nbsp;   Pagamentos Corretor > Lista   &nbsp;
			</legend>
			<% String data = new com.grupoexata.bancario.utils.FormataObj().formataData(new java.util.Date()); %>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="6">
						<html:form action="actionPagamentoAgente" onsubmit="return window.confirm('Fechar comissões para envio ao financeiro com a data informada?');">
							<html:hidden property="m" value="importa"/>
							<html:text property="pgadbase" readonly="true" style="color: #00D;" styleId="data1" value="<%= data %>" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/>
							<input type="image" id="b1" src="jscalendar/img.gif">
							<html:submit styleClass="btn_hot">Fechar Comissões</html:submit>
						</html:form>
					</th>
				</tr>
				<html:form action="actionCaixa">
				<html:hidden property="m" value="relatorioMovimento"/>
				<tr style="background-color: #DDD;">
					<th style="vertical-align: middle;">
						Relatórios
					</th>
					<th style="vertical-align: middle;">
						Data Inicial&nbsp; <input type="text" readonly="readonly" id="data2" name="data1" value="<%= data %>" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')">
						<input type="image" id="b2" src="jscalendar/img.gif">
					</th>
					<th style="vertical-align: middle;">
						Data Final&nbsp; <input type="text" readonly="readonly" id="data3" name="data2" value="<%= data %>" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')">
						<input type="image" id="b3" src="jscalendar/img.gif">
					</th>
					<th style="vertical-align: middle;">
						<select name="tipo" style="width: 200px;">				
							<option value="6">Comissões de Corretores pendente de pagamento</option>
							<option value="7">Comissões de Corretores com Agendamento Marcado</option>
						</select>
					</th>
					<th style="vertical-align: middle;">
						<html:submit styleClass="btn_hot">Enviar</html:submit>
					</th>
				</tr>
				</html:form>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%">
			<tbody>
				<logic:notEmpty name="ls_pagamentoagente">
				<tr>
					<th>
						Data Base
					</th>
					<th>
						Nome do Agente
					</th>
					<th>
						Deve Físico?
					</th>
					<th>
						Tipo
					</th>
					<th>
						Valor
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				</logic:notEmpty>
				<logic:empty name="ls_pagamentoagente">
				<tr>
					<td colspan="6" style="color: red;">
						Não há pagamentos para realizar.
					</td>
				</tr>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_pagamentoagente">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="pgadbase"/>
					</td>
					<td>
						<bean:write name="b" property="pgacnman"/>
					</td>
					<td>
						<logic:equal name="b" property="pgalfisi" value="T">
							Sim
						</logic:equal>
						<logic:equal name="b" property="pgalfisi" value="F">
							Não
						</logic:equal>
					</td>
					<td>
						<bean:write name="b" property="pgactipo"/>
					</td>
					<td>
						R$ <bean:write name="b" property="pgayvalr"/>
					</td>
					<td>
						<input type="image" src="imagens/cifrao.gif" title="Pagar/Detalhes" onclick="window.location = 'actionPagamentoAgente.do?m=dados&pgancodg=<bean:write name="b" property="pgancodg"/>'">
					</td>
				</tr>
				</logic:iterate>
												
			</tbody>			
			</table>
			</div>		
		</fieldset>
			
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
	    Calendar.setup({
	        inputField     :    "data3",     // id of the input field
	        ifFormat       :    "%d/%m/%Y",      // format of the input field
	        button         :    "b3",  // trigger for the calendar (button ID)
	        align          :    "BR",           // alignment (defaults to "Bl")
	        singleClick    :    true
	    });
	    	    
	</script>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>