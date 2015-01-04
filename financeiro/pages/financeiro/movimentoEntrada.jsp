	<%@include file="../topo.jsp" %>
	<script type="text/javascript" src="dwr/interface/Mapping.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>
  	
<script type="text/javascript">
<!--

	function preencheCentros(elem){			
		Mapping.getCentrosDeCusto(retorno,elem.value,'movncecu');							
	}
	
	function retorno(valor){
		DWRUtil.setValue("centros",valor);
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
	
//-->
</script>
	<style>
		td{
			padding: 4px;
		}
	</style>
				
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend class="red">
				&nbsp;   Movimento Caixa > Entrada &nbsp;
			</legend>
			<html:form action="actionMovCaixa" focus="movddata" styleId="formMovCaixa">
			<html:hidden property="m" value="cadastroEntrada"/>
			<html:hidden property="movltipo" value="M"/>
			<html:hidden property="movnqtpc" value="1"/>
			<html:hidden property="movnnrpc" value="1"/>
			<table style="width: 650px" cellspacing="0" cellpadding="0" align="center">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center; font-size: 13px;">
						Movimento Caixa - Entrada
					</th>
				</tr>
				<% String data = new com.grupoexata.bancario.utils.FormataObj().formataData(new Date()); %>
				<tr>
					<th style="width: 30%">
						Data Movimento
					</th>
					<td style="width: 70%">
						<html:text property="movddata" value="<%= data %>" styleId="data1" readonly="true" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/>
						<input type="image" id="b1" src="jscalendar/img.gif">
						&nbsp;&nbsp;&nbsp;
						<b>Data Vencimento</b>&nbsp;&nbsp;&nbsp;
						<html:text property="movdvenc" value="<%= data %>" styleId="data2" readonly="true" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/>
						<input type="image" id="b2" src="jscalendar/img.gif">
					</td>
				</tr>
				<tr>
					<th style="width: 30%">
						Grupo Conta
					</th>
					<td style="width: 70%">
						<input type="radio" name="movncggpc" value="1" disabled="disabled">&nbsp;<b>Despesa Fixa/Variável</b>&nbsp;&nbsp;
						<input type="radio" name="movncggpc" value="2" disabled="disabled">&nbsp;<b>Investimentos</b>&nbsp;&nbsp;
						<input type="radio" name="movncggpc" value="3" checked="checked">&nbsp;<b>Faturamento</b>&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<th style="width: 30%">
						Fonte Pagadora
					</th>
					<td style="width: 70%">
						<html:select property="movncgore" style="width: 350px;" onclick="preencheCentros(this)">
							<html:option value="0">-----------------------</html:option>
							<html:optionsCollection name="ls_origementrada" label="orecdesc" value="orencodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th style="vertical-align: top;">
						Produto/Cliente/<br>Centro de Custo
					</th>
					<td>
						<div id="centros">
						<html:select property="movncecu" styleId="movncecu" style="width: 375px;">
							<html:optionsCollection name="ls_centrocusto" label="crcdesc" value="crncodg"/>
						</html:select>
						</div>
					</td>
				</tr>		 
				<tr>
					<th style="vertical-align: top;">
						Forma de Recebimento
					</th>
					<td>
						<html:select property="movnflqu" styleId="movnflqu" style="width: 375px;">
							<html:optionsCollection name="ls_formaliquidez" label="flqcdesc" value="flqncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th style="width: 30%">
						Filial
					</th>
					<td style="width: 70%">
						<html:hidden property="movnfili" styleId="movnfili"/>
						<html:text property="movcfili" styleId="movcfili" size="60" readonly="true" style="color: #D00;"/>
						<img src="imagens/lupa.gif" width="17" height="17" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('filialPesquisaWindow.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');"/>
					</td>
				</tr>
				<tr>
					<th style="width: 30%">
						Observações
					</th>
					<td style="width: 70%">
						<html:text property="movcdesc" size="60" maxlength="70"/>
					</td>
				</tr>
				<tr>
					<th>
						Valor
					</th>
					<td>
						<html:text property="movyvalr" size="10" maxlength="10" onkeydown="Formata(this,10,event,2)" style="text-align: right;"/>	
					</td>
				</tr>				
				<tr>
					<td colspan="2" style="text-align: right;">
						<input type="button" class="btn_hot" value="Salvar Movimento" onclick="if (window.confirm('Confirmar o Lançamento do Movimento no caixa?')){this.form.submit();}">
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
	    Calendar.setup({
	        inputField     :    "data2",     // id of the input field
	        ifFormat       :    "%d/%m/%Y",      // format of the input field
	        button         :    "b2",  // trigger for the calendar (button ID)
	        align          :    "BR",           // alignment (defaults to "Bl")
	        singleClick    :    true
	    });
	</script>
	
		
	<%@include file="../footer.jsp" %>
	</div>
</body>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>


<%@page import="com.grupoexata.financeiro.struts.form.FormMovCaixa"%>

<%@page import="java.util.List"%>

</html>