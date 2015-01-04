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
				&nbsp;  Andamento do Processo  > Alteração &nbsp;
			</legend>
			<html:form action="actionAndamentoProcesso">
			<html:hidden property="m" value="update"/>
			<html:hidden property="anpncodg"/>
			<html:hidden property="anpncgpro"/>
			<input type="hidden" name="anpncgen" value="<bean:write name="usuario" property="enncodg"/>">
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th style="background-color: #DDD; font-weight: bold; text-align: center;" colspan="2">
						Altera Andamento do Processo
					</th>
				</tr>
				<tr>
					<td>
						Data
					</td>
					<td>
						<html:text property="anpddata" styleId="data1" size="11" maxlength="10" readonly="true" style="color: #00D; font-weight: bold;"/>
						<input type="image" id="b1" src="jscalendar/img.gif">
					</td>
				</tr>
				<tr>
					<td>
						Nova Fase
					</td>
					<td>
						<html:select property="anpncgfap" style="width: 350px;">
							<html:optionsCollection name="ls_faseprocesso" label="fapcdesc" value="fapncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Descrição
					</td>
					<td>
						<html:text property="anpcdesc" size="60" maxlength="80" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Cancelar" class="btn" onclick="window.location = 'actionAndamentoProcesso.do?m=lista&proncodg=<bean:write name="formAndamentoProcesso" property="anpncgpro"/>'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Salvar</html:submit>
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
		        ifFormat       :    "%d/%m/%Y",  // format of the input field
		        button         :    "b1",        // trigger for the calendar (button ID)
		        align          :    "BR",        // alignment (defaults to "Bl")
		        singleClick    :    true
		    });   
		</script>
		
		<%@include file="../footer.jsp" %>
</body>
</html>