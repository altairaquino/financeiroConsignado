<%@include file="/pages/topo.jsp" %>

</head>
<body>
	<div id="container">
		<%@include file="/pages/menu.jsp" %>
		<%@include file="/pages/header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp; Registro de Atividades &nbsp;
			</legend>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">
						Relatórios
					</th>
				</tr>
				<% String data = new com.grupoexata.bancario.utils.FormataObj().formataData(new java.util.Date()); %>
				<html:form action="actionAtividade" method="post">
				<html:hidden property="m" value="relatorios"/>
				<input type="hidden" name="enncodg" value="<bean:write name="usuario" property="enncodg"/>">
				<tr>
					<td colspan="2">
						<select name="tipo" style="width: 300px;">
							<option value="1">Relatório de Atividades Diárias</option>
						</select>&nbsp;Período:&nbsp;
						<input type="text" id="data1" value="<%= data %>" name="data1" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')" readonly="readonly"> <input type="image" id="b1" src="jscalendar/img.gif"> até 
						<input type="text" id="data2" value="<%= data %>" name="data2" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')" readonly="readonly"> <input type="image" id="b2" src="jscalendar/img.gif">
						<input type="submit" class="btn_hot" value="Visualizar">
					</td>
				</tr>
				</html:form>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">
						Atividades Registradas
					</th>
				</tr>
				<tr>				
					<td colspan="2">
						<input type="button" class="btn_hot" value="Nova Atividade" onclick="window.location = 'actionAtividade.do?m=novo'">
					</td>
				</tr>
                <logic:empty name="ls_atividade">
					<th colspan="2">
						 Não há atividades registradas.
					</th>
				</logic:empty>
				<% 	int c = 0;	%>
				<logic:iterate id="b001" name="ls_atividade">
				<tr id="dia<%=++c%>" style="background-color: <%= (c%2==0?"#FFF":"#DDD") %>">
					<td style="text-align: left; color: #D00; font-weight: bold; border-top-color: #00D; border-top-width: 3px; border-top-style: dotted;" colspan="2">
						Data: [<bean:write name="b001" property="atiddata"/>] - Horário: [<bean:write name="b001" property="atithora"/>] - LOCAL: [<bean:write name="b001" property="aticloca"/>] &nbsp;&nbsp;&nbsp;[<a href="javascript: if (window.confirm('Confirmar o cancelamento da atividade?')){window.location = 'actionAtividade.do?m=cancelar&atincodg=<bean:write name="b001" property="atincodg"/>';}">Cancelar</a>]&nbsp;&nbsp;&nbsp;[<a href="actionAtividade.do?m=editar&atincodg=<bean:write name="b001" property="atincodg"/>" title="Editar Atividade">Editar</a>]
					</td>
				</tr>
				<tr style="background-color: <%= (c%2==0?"#FFF":"#DDD") %>">
					<td style="text-align: left;" colspan="2">
						<b>Contato:</b> <bean:write name="b001" property="aticcont"/> - <b>Telefone:</b> <bean:write name="b001" property="aticfone"/>
					</td>
				</tr>
				<tr style="background-color: <%= (c%2==0?"#FFF":"#DDD") %>">
					<td style="text-align: left;">
						<b>Descrição da Atividade</b><br>
						<textarea rows="3" cols="55" readonly="readonly"><bean:write name="b001" property="aticdesc"/></textarea>
					</td>
					<td style="text-align: left;">
						<b>Observações gerais</b><br>
						<textarea rows="3" cols="55" readonly="readonly"><bean:write name="b001" property="aticobsv"/></textarea>
					</td>
				</tr>
				<tr style="background-color: <%= (c%2==0?"#FFF":"#DDD") %>">
					<td style="text-align: left;" colspan="2">
						<b>Perspectivas:</b> 
						<input type="text" size="100" value="<bean:write name="b001" property="aticpers"/>" readonly="readonly">
					</td>
				</tr>
				<tr style="background-color: <%= (c%2==0?"#FFF":"#DDD") %>">
					<td style="text-align: left;">
						<b>Gasto Extra:</b>&nbsp;
						<input type="text" size="12" value="<bean:write name="b001" property="atiygast"/>" style="text-align: right;" readonly="readonly">
					</td>
					<td style="text-align: left;">
						<b>Odômetro do veículo:</b>&nbsp;
						<input type="text" size="7" value="<bean:write name="b001" property="atinodin"/>" readonly="readonly">&nbsp;até&nbsp;<input type="text" size="7" value="<bean:write name="b001" property="atinodfn"/>" readonly="readonly">
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
		</script>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>

</html>
