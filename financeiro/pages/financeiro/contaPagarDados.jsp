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
				&nbsp; Pagamentos Futuros > Dados &nbsp;
			</legend>
			<table style="width: 650px;" align="center">
			<tbody>
				<tr>
					<td colspan="2" style="text-align: right;">
						<input type="button" class="btn_hot" value="Editar" onclick="window.location = 'actionMovCaixa.do?m=editarFuturo&movncodg=<bean:write name="contapagar" property="movncodg"/>'">
						&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn_hot" value="Cancelar" onclick="if (window.confirm('Confirmar o cancelamento deste lançamento?')) {window.location = 'actionMovCaixa.do?m=cancelarFuturo&movncodg=<bean:write name="contapagar" property="movncodg"/>'}">
					</td>
				</tr>
				<tr>				
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center; font-size: 14px;">
						Dados do Pagamento Futuro
					</th>		
				</tr>
				<tr>
					<th>
						Data Vencimento
					</th>
					<td>
						<bean:write name="contapagar" property="movdvenc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<th style="vertical-align: top;">
						Grupo Conta
					</th>
					<td>
						<bean:write name="contapagar" property="movcdcgpc"/>				
					</td>
				</tr>
				<tr>
					<th style="vertical-align: top;">
						Descrição (Conta)
					</th>
					<td>
						<bean:write name="contapagar" property="movcdccog"/>				
					</td>
				</tr>
				<tr>
					<th style="vertical-align: top;">
						Parcela
					</th>
					<td>
						<bean:write name="contapagar" property="movnnrpc"/>/<bean:write name="contapagar" property="movnqtpc"/>		
					</td>
				</tr>					
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<th style="vertical-align: top;">
						Centros de Custo
					</th>
					<td style="color: #00D; font-weight: bold;">
						<logic:iterate id="b" name="ls_centromovcaixa">
							<bean:write name="b" property="crmccent"/><br>
						</logic:iterate>						
					</td>
				</tr>
				<tr>
					<th>
						Filial/Loja
					</th>
					<td>
						<bean:write name="contapagar" property="movcfili"/>
					</td>
				</tr>
				<tr>
					<th>
						Beneficiário
					</th>
					<td>
						<bean:write name="contapagar" property="movcforn"/>
					</td>
				</tr>
				<tr>
					<th>
						Conta
					</th>
					<td>
						<bean:write name="contapagar" property="movccoco"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<th>
						Observação
					</th>
					<td>
						<bean:write name="contapagar" property="movcdesc"/>
					</td>
				</tr>
				<tr>
					<th>
						Valor
					</th>
					<td>
						R$ <bean:write name="contapagar" property="movyvalr"/>
					</td>
				</tr>
				<html:form action="actionMovCaixa" focus="movddada" onsubmit="return window.confirm('Confirmar o Lançamento do Movimento no caixa?');">
				<html:hidden property="m" value="pagarFuturo"/>
				<html:hidden property="movncodg" name="contapagar"/>
				<tr>
					<th style="width: 30%; color: #D00;">
						Pendência de Comprovante?
					</th>
					<td style="width: 70%">
						<html:radio property="movlpend" value="T">Sim</html:radio>&nbsp;&nbsp;
						<html:radio property="movlpend" value="F">Não</html:radio>
					</td>
				</tr>
				<tr>
					<th style="vertical-align: top;">
						Forma Pgto.
					</th>
					<td>
						<html:select property="movnflqu" style="width: 375px;">
							<html:optionsCollection name="ls_formaliquidez" label="flqcdesc" value="flqncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						Data de Pagamento
					</th>
					<td>
						<html:text property="movddata" readonly="true" styleId="data1" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/>
						<input type="image" id="b1" src="jscalendar/img.gif">
					</td>
				</tr>	
				<tr>
					<td>
						<input type="button" class="btn" value="Voltar a Lista" onclick="window.location = 'actionMovCaixa.do?m=listaPagamentoFuturo'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Confirmar Pagamento</html:submit>
					</td>
				</tr>
				</html:form>
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>				
			</tbody>
			</table>
				
										
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
	</script>
	
</body>


<%@page import="java.util.List"%></html>