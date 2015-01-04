	
	<%@include file="../topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset style="text-align: center;">
			<legend class="red">
				&nbsp;   Pagamento Agente > Dados &nbsp;
			</legend>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<td>
						<input type="button" class="btn" value="Voltar" onclick="window.location = 'actionPagamentoAgente.do?m=lista'">
					</td>
					<td style="text-align: right;">
						<input type="button" class="btn_hot" value="Editar" onclick="window.location = 'actionPagamentoAgente.do?m=editar&pgancodg=<bean:write name="pagamentoagente" property="pgancodg"/>'">
					</td>					
				</tr>				
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="2">
						Dados do Pagamento
					</th>
				</tr>
				<tr>
					<th style="width: 25%;">
						Data Base
					</th>
					<td style="width: 75%; color: green; font-weight: bold;">
						<bean:write name="pagamentoagente" property="pgadbase"/>
					</td>
				</tr>
				<tr>
					<th>
						Nome do Agente
					</th>
					<td>
						<bean:write name="pagamentoagente" property="pgacnman"/>
					</td>
				</tr>
				<tr>
					<th>
						Deve Físico?
					</th>
					<td>
						<logic:equal name="pagamentoagente" property="pgalfisi" value="T">
							Sim
						</logic:equal>
						<logic:equal name="pagamentoagente" property="pgalfisi" value="F">
							Não
						</logic:equal>
					</td>
				</tr>
				<tr>
					<th>
						Valor do Pagamento
					</th>
					<td style="color: #D00; font-weight: bold;">
						R$ <bean:write name="pagamentoagente" property="pgayvalr"/>
					</td>
				</tr>
				<% String data = new com.grupoexata.bancario.utils.FormataObj().formataData(new java.util.Date()); %>
				<html:form action="actionPagamentoAgente" onsubmit="return window.confirm('Confirmar o envio ao financeiro da comissão do corretor para a data informada?');">
				<html:hidden property="m" value="confirmaAgendamento"/>
				<html:hidden property="pgancodg" name="pagamentoagente"/>
				<html:hidden property="pgayvalr" name="pagamentoagente"/>
				<tr>
					<th>
						Conta Corrente
					</th>
					<td style="color: #D00; font-weight: bold;">
						<html:select property="pgancoco" style="width: 450px;">
							<html:optionsCollection name="ls_contacorrente" value="concodg" label="cocdesc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						Desconto (R$)
					</th>
					<td style="color: #D00; font-weight: bold;">
						<html:text property="pgaydesc" maxlength="12" size="11" onkeydown="Formata(this,10,event,2)" style="text-align: right;"/>
					</td>
				</tr>
				<tr>
					<th>
						Data do Agendamento
					</th>
					<td>
						<html:text property="pgadpgto" readonly="true" style="color: #00D;" styleId="data1" value="<%= data %>" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/>
						<input type="image" id="b1" src="jscalendar/img.gif">
						<html:submit styleClass="btn_hot">Enviar ao Financeiro</html:submit>
					</td>
				</tr>
				
				</html:form>
			
			</tbody>			
			</table>
			
		</fieldset>
		
	<script type="text/javascript">
	
	    Calendar.setup({
	        inputField     :    "data1",     // id of the input field
	        ifFormat       :    "%d/%m/%Y",      // format of the input field
	        button         :    "b1",  // trigger for the calendar (button ID)
	        align          :    "BR",           // alignment (defaults to "Bl")
	        singleClick    :    true
	    });
	    	    
	</script>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>