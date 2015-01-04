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
				&nbsp;   Pagamentos Futuros > Lista &nbsp;
			</legend>
			<% String d = new com.grupoexata.bancario.utils.FormataObj().formataData(new java.util.Date()); %>
			<table style="width: 100%">
			<tbody>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Nova Conta" onclick="window.location = 'actionMovCaixa.do?m=novoFuturo'">
						<input type="button" class="btn_hot" value="Lista Todos" onclick="window.location = 'actionMovCaixa.do?m=listaPagamentoFuturo'">						
					</td>
					<th>
						<html:form action="actionMovCaixa">
							Data&nbsp;
							<html:hidden property="m" value="pesquisaPagamentoFuturo"/>
							<html:text property="movddata" styleId="data1" value="<%= d %>" size="11" style="color: #00D; font-weight: bold;" maxlength="10" readonly="true" onkeyup="criaMascara(this, '##/##/####');"/>&nbsp;
							<input type="image" id="b1" src="jscalendar/img.gif">&nbsp;&nbsp;
							<html:submit value="Pesquisa" styleClass="btn_hot"/>
						</html:form>
					</th>
				</tr>
				<tr>
					<td style="color: #D00; font-weight: bold;" colspan="2">
						Obs: apareceram somente contas entre 30 dias. Para Visualizar demais visualize pelos relatórios.
					</td>					
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%">
			<tbody>			
				<logic:notEmpty name="ls_movcaixa">
				<tr>
					<th colspan="5">
						Pagamentos Futuros
					</th>					
				</tr>
				<tr>
					<th>
						Data
					</th>
					<th>
						Parc.
					</th>
					<th>
						Descrição
					</th>
					<th>
						Observação
					</th>
					<th>
						Histórico
					</th>
					<th>
						Valor
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				</logic:notEmpty>
				<logic:empty name="ls_movcaixa">
				<tr>
					<td colspan="5">
						Não há pagametos futuros no sistema.
					</td>
				</tr>
				</logic:empty>
				<% int i = 0; %>
				<logic:iterate id="b" name="ls_movcaixa">
				<tr style="background-color: <%= ((i++%2) == 1)?"#FFF":"#DDD" %>;">
					<td style="font-size: 9px;">
						<bean:write name="b" property="movdvenc"/>
					</td>
					<td style="font-size: 9px;">
						<bean:write name="b" property="movnnrpc"/>/<bean:write name="b" property="movnqtpc"/>
					</td>
					<td style="font-size: 9px;">
						<bean:write name="b" property="movcdccog"/>
					</td>
					<td style="font-size: 9px;">
						<bean:write name="b" property="movcdesc"/>
					</td>
					<td style="font-size: 9px;">
						<bean:write name="b" property="movcdocm"/>
					</td>
					<td style="font-size: 9px;">
						R$ <bean:write name="b" property="movyvalr"/>
					</td>
					<td <logic:notEmpty name="b" property="movnforn"> rowspan="2"</logic:notEmpty>>
						<input type="image" src="imagens/detalhe.gif" title="Detalhe" onclick="window.location = 'actionMovCaixa.do?m=dadosFuturo&movncodg=<bean:write name="b" property="movncodg"/>'">
					</td>
				</tr>
				<logic:notEmpty name="b" property="movnforn">
				<tr>
					<td colspan="5"  style="font-size: 9px; font-weight: bold;">
						Benefic: <bean:write name="b" property="movcforn"/>
						<logic:notEmpty name="b" property="movncoco">
							&nbsp;(<bean:write name="b" property="movccoco"/>)
						</logic:notEmpty>
					</td>
				</tr>
				</logic:notEmpty>
				</logic:iterate>
			
			</tbody>			
			</table>
			
			</div>
		<logic:present name="msg">
			<input type="hidden" value="<bean:write name="msg"/>" name="msg" id="msg">
			<script>
				var x = document.getElementById('msg').value;
				alert(x);
			</script>
		</logic:present>
					
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
	</script>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>


</html>