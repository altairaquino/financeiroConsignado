	<%@include file="../topo.jsp" %>
	
	<script type="text/javascript" src="dwr/interface/CentroCusto.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>
  	
  	<script type="text/javascript">
			function getCentroCustos(){
				CentroCusto.getCentroCustosMovimento(retorno,<bean:write name="formMovCaixa" property="movncodg"/>,"getAddCentroCusto(%s);");							
			}
			function getAddCentroCusto(crncodg){
				DWREngine.beginBatch();
				CentroCusto.addCentroCustoMovimento(<bean:write name="formMovCaixa" property="movncodg"/>,crncodg);
				getCentroCustos();
				DWREngine.endBatch();
			}
			
			function retorno(valor){
				DWRUtil.setValue('resultado',valor);
			}			
			
			function init(){
				DWRUtil.useLoadingMessage("Carregando Aguarde!!");
			}
	</script>
	
<script type="text/javascript">
<!--
	function ajustaDIV(){
		var f = document.getElementById('formMovCaixa');
		
		if (f.movncggpc[0].checked || f.movncggpc[1].checked){
			document.getElementById('fornecedor').style.display = 'block';
			document.getElementById('entrada').style.display = 'none';
		}
		if (f.movncggpc[2].checked){
			document.getElementById('fornecedor').style.display = 'none';
			document.getElementById('entrada').style.display = 'block';
		}
	}
	
	function janelaContas(form){
		if (form.movnforn.value == ''){
			alert('Escolha antes o fornecedor!');
		}else{
			window.open('actionContaCorrente.do?m=listaCaixaWindow&enncodg='+form.movnforn.value, 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 400, width = 550');
		}
	}
	
	function janelaDescricao(form){
		if (!form.movncggpc[0].checked && !form.movncggpc[1].checked && !form.movncggpc[2].checked){
			alert('Escolha o grupo de Contas!');
		}else{
			if (form.movncggpc[0].checked){
				window.open('contaGrupoPesquisaWindow.do?gpcncodg=1', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 400, width = 550');
			}else{
				if (form.movncggpc[1].checked){
					window.open('contaGrupoPesquisaWindow.do?gpcncodg=2', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 400, width = 550');
				}else{
					window.open('contaGrupoPesquisaWindow.do?gpcncodg=3', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 400, width = 550');
				}
			}
			
		}
	}
//-->
</script>
				
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Pagamentos Futuros > Alteração &nbsp;
			</legend>
			<html:form action="actionMovCaixa" focus="movddata" styleId="formMovCaixa" onsubmit="return window.confirm('Confirma o cadastro do pagamento futuro?');">
			<html:hidden property="m" value="updateFuturo"/>
			<html:hidden property="movltipo"/>
			<table style="width: 600px" cellspacing="0" cellpadding="0" align="center">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center; font-size: 13px;">
						Alteração de Pagamento Futuro
					</th>
				</tr>
				<tr>
					<th style="width: 25%">
						Data 1º Vencimento
					</th>
					<td style="width: 75%">
						<html:text property="movdvenc" styleId="data2" readonly="true" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/>
						<input type="image" id="b2" src="jscalendar/img.gif">
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Grupo Conta
					</th>
					<td style="width: 75%">
						<html:radio property="movncggpc" value="1" onclick="ajustaDIV(); this.form.movncgcog.value = ''; this.form.movcdccog.value = '';">&nbsp; <b>Despesa Fixa/Variável</b></html:radio>&nbsp;&nbsp;
						<html:radio property="movncggpc" value="2" onclick="ajustaDIV(); this.form.movncgcog.value = ''; this.form.movcdccog.value = '';">&nbsp; <b>Investimentos</b></html:radio>&nbsp;&nbsp;
						<html:radio property="movncggpc" value="3" onclick="ajustaDIV(); this.form.movncgcog.value = ''; this.form.movcdccog.value = '';" disabled="true">&nbsp; <b>Faturamento</b></html:radio>						
					</td>
				</tr>
			</tbody>
			</table>
			<div id="entrada">
			<table style="width: 600px"  cellspacing="0" cellpadding="0" align="center">
			<tbody>
				<tr>
					<th style="width: 25%">
						Origem Entrada
					</th>
					<td style="width: 75%">
						<html:select property="movncgore" style="width: 350px;">
							<html:option value="0">-----------------------</html:option>
							<html:optionsCollection name="ls_origementrada" label="orecdesc" value="orencodg"/>
						</html:select>
					</td>
				</tr>
			</tbody>
			</table>
			</div>
			<table style="width: 600px" cellspacing="0" cellpadding="0" align="center">
			<tbody>
				<tr>
					<th style="width: 25%">
						Descrição (Conta)
					</th>
					<td style="width: 75%">
						<html:hidden property="movncgcog" styleId="movncont"/>
						<html:text property="movcdccog" styleId="movccont" size="50" readonly="true" style="color: #D00;"/>
						<img src="imagens/lupa.gif" alt="pesquisar" width="17" height="17" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="janelaDescricao(document.getElementById('formMovCaixa'))"/>
					</td>
				</tr>							 
			</tbody>
			</table>
			<div id="fornecedor">
			<table style="width: 600px" cellspacing="0" cellpadding="0" align="center">
			<tbody>
				<tr>
					<th style="background-color: #ddd; text-align: center;" colspan="2">
						Produto/Cliente/Centro de Custo
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<div id="resultado"></div>
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Filial/Loja
					</th>
					<td style="width: 75%">
						<html:hidden property="movnfili" styleId="movnfili"/>
						<html:text property="movcfili" styleId="movcfili" size="60" readonly="true" style="color: #D00;"/>
						<img src="imagens/lupa.gif" width="17" height="17" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('filialPesquisaWindow.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');"/>
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Beneficiário
					</th>
					<td style="width: 75%">
						<html:hidden property="movnforn" styleId="movnforn"/>
						<html:text property="movcforn" styleId="movcforn" size="60" readonly="true" style="color: #D00;"/>
						<img src="imagens/lupa.gif" width="17" height="17" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('fornecedorPesquisaCaixaWindow.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');"/>
					</td>
				</tr>
				<tr>
					<th>
						Conta Corrente
					</th>
					<td>
						<html:hidden property="movncoco" styleId="movncoco"/>
						<html:text property="movccoco" styleId="movccoco" size="60" readonly="true" style="color: #D00;"/>
						<img src="imagens/lupa.gif" width="17" height="17" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="janelaContas(document.getElementById('formMovCaixa'));"/>
					</td>
				</tr>
			</tbody>
			</table>
			</div>
			<table style="width: 600px" cellspacing="0" cellpadding="0" align="center">
			<tbody>
				<tr>
					<th style="width: 25%">
						Observação
					</th>
					<td style="width: 75%">
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
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'actionMovCaixa.do?m=listaPagamentoFuturo'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Salvar Movimento</html:submit>
					</td>
				</tr>				
			</tbody>
			</table>
			</html:form>			
								
		</fieldset>
			
		</div>
		
	<script type="text/javascript">
	    Calendar.setup({
	        inputField     :    "data2",     // id of the input field
	        ifFormat       :    "%d/%m/%Y",      // format of the input field
	        button         :    "b2",  // trigger for the calendar (button ID)
	        align          :    "BR",           // alignment (defaults to "Bl")
	        singleClick    :    true
	    });
	</script>
	
	<script type="text/javascript">
		getCentroCustos();
	</script>
		
	<%@include file="../footer.jsp" %>
	</div>
</body>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>


<%@page import="com.grupoexata.financeiro.struts.bean.BeanMovCaixa"%>
	<script type="text/javascript">
		ajustaDIV();
	</script>

<%@page import="com.grupoexata.financeiro.struts.form.FormMovCaixa"%>

<%@page import="java.util.List"%>
<%@page import="com.grupoexata.financeiro.struts.bean.BeanCentroCusto"%>
<%@page import="java.util.Iterator"%>
</html>