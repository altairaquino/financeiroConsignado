	<%@include file="../topo.jsp" %>
		
				
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">	
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Relatórios Financeiros  &nbsp;
			</legend>
			<html:form action="actionCaixa">
			<html:hidden property="m" value="relatorioMovimento"/>
			<center>
			<table style="width: 550px;">
			<tbody>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2">
						Relatórios Financeiro (Diversos)
					</th>
				</tr>
				<tr>
					<td style="width: 25%;">
						Data Inicial
					</td>
					<td style="width: 75%;">
						<input type="text" id="data1" name="data1" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')">
						<input type="image" id="b1" src="jscalendar/img.gif">			
					</td>
				</tr>
				<tr>
					<td>
						Data Final
					</td>
					<td>
						<input type="text" name="data2" id="data2" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')">
						<input type="image" id="b2" src="jscalendar/img.gif">
					</td>
				</tr>
				<tr>
					<td>
						Tipo
					</td>
					<td>
						<select name="tipo" style="width: 400px;">
							<option value="12">Resultado por Centro de Custo (Analítico)</option>
							<option value="1">Relatório de Pagamentos Futuros (Período)</option>
							<option value="2">Despesas por Centro de Custo/Grupo</option>
							<option value="5">Receitas por Centro de Custo/Data Movimento</option>
							<option value="3">Pagamentos Futuros para Pagamentos em Conta</option>
							<option value="4">Pagamentos Futuros para Pagamentos Sem Conta</option>
							<option value="6">Comissões de Corretores pendente de pagamento</option>
							<option value="7">Comissões de Corretores com Agendamento Marcado</option>
							<option value="8">Faturamento por Origem da Entrada</option>
							<option value="9">Despesas/Investimentos por Filial</option>
							<option value="11">Resultado por Grupo de Contas</option>
							<option value="10">Kilometragem Rodada por Funcionário (Atividades)</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" colspan="2">
						&nbsp;
					</td>
				</tr>				
				<tr>
					<td style="text-align: right;" colspan="2">
						<input type="button" class="btn_hot" value="Visualizar Relatório" onclick="this.form.submit()">						
					</td>
				</tr>
			
			</tbody>
			</table>
			</center>	
			</html:form>
			
			<% request.setAttribute("ls_centrocusto", ModelCentroCusto.getInstance().getCentrosCustoAtivos()); %>
			
			<html:form action="actionCaixa">
			<html:hidden property="m" value="relatorioMovimento2"/>
			<input type="hidden" name="tipo" value="1">
			<center>
			<table style="width: 550px;">
			<tbody>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2">
						Relatórios Financeiro por Conta Grupo
					</th>
				</tr>
				<tr>
					<td style="width: 25%;">
						Data Inicial
					</td>
					<td style="width: 75%;">
						<input type="text" id="data3" name="data1" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')">
						<input type="image" id="b3" src="jscalendar/img.gif">			
					</td>
				</tr>
				<tr>
					<td>
						Data Final
					</td>
					<td>
						<input type="text" name="data2" id="data4" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')">
						<input type="image" id="b4" src="jscalendar/img.gif">
					</td>
				</tr>
				<tr>
					<td>
						Conta Grupo
					</td>
					<td>
						<input type="hidden" name="movncgcog" Id="movncont" value="0">
						<input type="text" name="movcdccog" Id="movccont" value="TODOS" size="50" readonly="readonly" style="color: #D00;">
						<img src="imagens/lupa.gif" alt="pesquisar" width="17" height="17" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('contaGrupoPesquisaWindow.do?gpcncodg=0', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 400, width = 550');"/>
					</td>
				</tr>
				<tr>
					<td>
						Centro de Custo
					</td>
					<td>
						<select name="centrocusto" style="width: 350px;">
							<option value="0">TODOS</option>
							<logic:iterate id="b" name="ls_centrocusto">
								<option value="<bean:write name="b" property="crncodg"/>"><bean:write name="b" property="crcdesc"/></option>
							</logic:iterate>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" colspan="2">
						&nbsp;
					</td>
				</tr>				
				<tr>
					<td style="text-align: right;" colspan="2">
						<input type="button" class="btn_hot" value="Visualizar Relatório" onclick="this.form.submit()">						
					</td>
				</tr>			
			</tbody>
			</table>
			</center>	
			</html:form>
			
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
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
	    Calendar.setup({
	        inputField     :    "data4",     // id of the input field
	        ifFormat       :    "%d/%m/%Y",      // format of the input field
	        button         :    "b4",  // trigger for the calendar (button ID)
	        align          :    "BR",           // alignment (defaults to "Bl")
	        singleClick    :    true
	    });
	</script>
	
</body>

<%@page import="com.grupoexata.financeiro.dao.ModelCentroCusto"%></html>