	
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
				&nbsp;   Pagamento Agente > Alterar &nbsp;
			</legend>
			<html:form action="actionPagamentoAgente">
			<html:hidden property="m" value="update"/>
			<html:hidden property="pgancodg"/>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="2">
						Editar Dados do Pagamento
					</th>
				</tr>
				<tr>
					<th style="width: 25%;">
						Data Base
					</th>
					<td style="width: 75%; color: green; font-weight: bold;">
						<html:text property="pgadbase" style="color: #00D;" readonly="true" styleId="data1" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/>
						<input type="image" id="b1" src="jscalendar/img.gif">
					</td>
				</tr>
				<tr>
					<th>
						Nome do Agente
					</th>
					<td>
						<bean:write name="formPagamentoAgente" property="pgacnman"/>
					</td>
				</tr>
				<tr>
					<th>
						Deve Físico?
					</th>
					<td>
						<logic:equal name="formPagamentoAgente" property="pgalfisi" value="T">
							Sim
						</logic:equal>
						<logic:equal name="formPagamentoAgente" property="pgalfisi" value="F">
							Não
						</logic:equal>
					</td>
				</tr>
				<tr>
					<th>
						Valor do Pagamento
					</th>
					<td>
						<html:text property="pgayvalr" size="10" maxlength="10" onkeydown="Formata(this,10,event,2)" style="text-align: right;"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Cancelar" onclick="window.location = 'actionPagamentoAgente.do?m=dados&pgancodg=<bean:write name="formPagamentoAgente" property="pgancodg"/>'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Salvar</html:submit>
					</td>					
				</tr>				
				
			</tbody>			
			</table>
			</html:form>
			
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