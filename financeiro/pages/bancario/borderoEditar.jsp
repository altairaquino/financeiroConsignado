	
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
				&nbsp;   Borderôs > Edição &nbsp;
			</legend>
			<html:form action="actionBordero" focus="borcnumr">
			<html:hidden property="m" value="update"/>
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="2">
						Alteração de Borderô
					</th>
				</tr>
				<tr>
					<th style="width: 25%;">
						Número do borderô
					</th>
					<td style="width: 75%;">
						<html:text property="borcnumr" size="16" maxlength="15"/>
					</td>
				</tr>
				<tr>
					<th>
						Número do Protocolo
					</th>
					<td>
						<html:text property="borcnrpt" size="30" maxlength="29"/>
					</td>
				</tr>
				<tr>
					<th>
						Envio ao Banco
					</th>
					<td>
						<html:text property="bordbanc" styleId="data1" readonly="true" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/>
						<input type="image" id="b1" src="jscalendar/img.gif">
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="2">
						Observações
					</th>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;">
						<html:textarea property="borcobsv" rows="3" cols="90"></html:textarea>
					</th>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'actionBordero.do?m=dados&borncodg=<bean:write name="formBordero" property="borncodg"/>'">
					</td>
					<td style="text-align: right;">						
						<html:submit styleClass="btn_hot">Salvar Borderô</html:submit>
					</td>
				</tr>				
			
			</tbody>			
			</table>	
			</html:form>
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