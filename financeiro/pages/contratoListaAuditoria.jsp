	<%@include file="topo.jsp" %>
	
	<script>
		function imprimeRelatorio(){
			f = document.getElementById('form');
			if (f.ctdcadt.value == ''){
				alert('Escolha a data do relatório');
			}else{
				window.location = 'actionContratoquestao.do?m=relatorios&ctdcadt='+f.ctdcadt.value;
			}
		}
	</script>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">		
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;  LISTA DE CONTRATOS PARA AUDITORIA  &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<td colspan="7" style="background-color: #DDD; font-weight: bold;text-align: center;">
						RELAÇÃO DE CONTRATOS PARA AUDITORIA
					</td>
				</tr>
				<tr>
					<td colspan="7">
						<html:form action="actionContrato" styleId="form">
							<html:hidden property="m" value="listaAuditoria"/>
							<html:text property="ctdcadt" styleId="data1" readonly="true"  size="11" maxlength="10" onkeyup="criaMascara(this, '##/##/####')" style="color: #00D;"/>
							<input type="image" id="b1" src="jscalendar/img.gif">
							<html:submit styleClass="btn_hot">Consultar</html:submit>
						</html:form>
						<logic:notEmpty name="ls_contrato">
							<input type="button" value="Imprime Relatório" class="btn_hot" onclick="imprimeRelatorio()">
						</logic:notEmpty>
					</td>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%">
			<tbody>
				<logic:notEmpty name="ls_contrato">
				<tr style="height: 20px;">
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
						Valor
					</th>
					<th>
						Cadastro
					</th>					
				</tr>
				</logic:notEmpty>
				<logic:empty name="ls_contrato">
				<tr>
					<td colspan="7" style="color: red;">
						NÃO HÁ CONTRATOS PARA AUDITORIA PARA A DATA INFORMADA.
					</td>
				</tr>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_contrato">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>; height: 20px;">
					<td style="font-weight: bold; font-size: 9px;">
						<a href="actionContratoquestao.do?m=montar&ctncodg=<bean:write name="b" property="ctncodg"/>" title="Auditar Contrato">
							<bean:write name="b" property="ctnnumr"/>						
						</a>
					</td>
					<td style="font-size: 9px;"> 
						<bean:write name="b" property="ctcnmcl"/>
					</td>
					<th style="font-size: 9px;"> 
						<bean:write name="b" property="ctcnman"/>
					</th>
					<th style="color: #00D;font-size: 9px;"> 
						R$ <bean:write name="b" property="ctyvalr"/>
					</th>
					<td style="font-size: 9px;">
						<bean:write name="b" property="ctdcadt"/>
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
	</script>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>