<%@include file="/pages/topo.jsp" %>
<style type="text/css">
	.style1{
		text-align: right;
		width: 120px;
		color:#660000;
	}
</style>
</head>
<body>
	<div id="container">
		<%@include file="/pages/menu.jsp" %>
		<%@include file="/pages/header.jsp" %>
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;Cadastro Pagamento de Ajuda de Custo&nbsp;
			</legend>
            <html:form action="actionPagtoajcust" onsubmit="return window.confirm('Confirmar a inclusão do Pagamento de Ajuda de Custo com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>

			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  
						Cadastro Pagamento de Ajuda de Custo 
					</th>
				</tr>               
				<tr>
					<td class="style1">
						Tipo ajuda de custo
					</td>
					<td>
						<html:select property="pacncgtac">
							<html:option value="-1">---------------</html:option>
							<html:optionsCollection name="listaTipoajcust" label="taccdesc" value="tacncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Filtro
					</td>
					<td>
						<html:select property="pacncgfil">
							<html:option value="">------</html:option>
							<html:optionsCollection name="listaFiltro" label="filcdesc" value="filncodg"/>
						</html:select>
					</td>
				</tr>
				<% String data = new com.grupoexata.bancario.utils.FormataObj().formataData(new java.util.Date()); %>		
				<tr>
					<td class="style1">
						Data de pagamento
					</td>
					<td>
						<html:text property="pacdpagto" styleId="data1" readonly="true" value="<%= data %>" size="11" maxlength="10" onkeyup="criaMascara(this, '##/##/####');"/>
						<input type="image" id="b1" src="jscalendar/img.gif">
					</td>
				</tr>
				<tr>
					<td class="style1">
						Inicio da Semana
					</td>
					<td>
						<html:text property="pacdsem" styleId="data2" readonly="true" value="<%= data %>" size="11" maxlength="10" onkeyup="criaMascara(this, '##/##/####');"/>
						<input type="image" id="b2" src="jscalendar/img.gif">
					</td>
				</tr>
				<tr>
					<td class="style1">
						Qtd de dias com feriado
					</td>
					<td>
						<html:text property="pacnnfer" size="2" maxlength="2" onkeyup="criaMascara(this, '##');"/>
					</td>
				</tr>
				
					<tr>
					<td class="style1">
						Dias referentes
					</td>
					<td>
						<html:text property="pacnrefs" size="2" maxlength="2" onkeyup="criaMascara(this, '##');"/>
					</td>
				</tr>
				
				<tr>
					<td class="style1">
						Dias efetivos
					</td>
					<td>
						<html:text property="pacnefet" size="2" maxlength="2" onkeyup="criaMascara(this, '##');"/>
					</td>
				</tr>				
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Cancelar" onclick="window.location = 'actionPagtoajcust.do?m=lista'">
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
		<%@include file="/pages/footer.jsp" %>
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
</body>
</html>
