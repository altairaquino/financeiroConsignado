	<%@include file="../topo.jsp" %>	
	
	<script type="text/javascript" src="dwr/interface/Mapping.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>

		<script type="text/javascript">
		
			function preencheCidades(elem){
				Mapping.getCidades(retorno,elem.value,'adcncgcd');						
			}
			
			function retorno(valor){
				DWRUtil.setValue("cidade",valor);
			}
			
			function init(){
				DWRUtil.useLoadingMessage("Carregando Aguarde!!");
			}
			
			if (window.addEventListener) {
				window.addEventListener("load",init,true);
			}else if (window.attachEvent) {
				window.attachEvent("onload", init);
			}else {
				window.onload = init;
			}	
			
		</script>
		
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">	
		<html:errors/>
		<fieldset>
			<legend class="red">
				&nbsp;  Audiência do Processo > Novo &nbsp;
			</legend>
			<html:form action="actionAudiencia" method="post">
			<html:hidden property="m" value="cadastro"/>
			<input type="hidden" name="adcncgpro" value="<bean:write name="processo" property="proncodg"/>">
			<table style="width: 650px;" align="center">
			<tbody>
				<tr>
					<th style="background-color: #DDD; font-weight: bold; text-align: center;" colspan="2">
						Registra Nova Audiência do Processo
					</th>
				</tr>
				<% String d = new com.grupoexata.bancario.utils.FormataObj().formataData(new java.util.Date()); %>
				<tr>
					<th style="width: 25%;">
						Data
					</th>
					<td style="width: 75%;">
						<html:text property="adcddata" styleId="data1" size="11" maxlength="10" readonly="true" value="<%= d %>" style="color: #00D; font-weight: bold;"/>
						<input type="image" id="b1" src="jscalendar/img.gif">
					</td>
				</tr>
				<tr>
					<th>
						Horário
					</th>
					<td>
						<html:text property="adchhora" size="6" maxlength="5" onkeyup="criaMascara(this, '##:##')"/>
					</td>
				</tr>
				<tr>
					<th style="vertical-align: top;">
						Local da Audiência
					</th>
					<td>
						<html:select property="adccufcd" onclick="preencheCidades(this)" onblur="preencheCidades(this)">
							<html:optionsCollection name="ls_estado" value="cdcuf" label="cdcuf"/>
						</html:select>
						<div id="cidade">
						<html:select property="adcncgcd" style="width: 375px;">
							<html:option value="-1">Escolha o estado</html:option>
						</html:select>
						</div>
					</td>
				</tr>
				<tr>
					<th>
						Nome do Advogado
					</th>
					<td>
						<html:text property="adccadvo" size="60" maxlength="40" />
					</td>
				</tr>
				<tr>
					<th style="vertical-align: top;">
						Observações
					</th>
					<td>
						<html:textarea property="adccdesc" cols="60" rows="2"></html:textarea>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Cancelar" class="btn" onclick="window.location = 'actionAudiencia.do?m=lista&proncodg=<bean:write name="processo" property="proncodg"/>'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Salvar Audiência</html:submit>
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