	<%@include file="../topo.jsp" %>
	
<script type="text/javascript">
<!--
	function ajustaDIV(){
		var f = document.getElementById('formMovCaixa');
		if (f.movntipo[1].checked){
			document.getElementById('fornecedor').style.display = 'block';
		}
		if (f.movntipo[0].checked){
			document.getElementById('fornecedor').style.display = 'none';
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
		if (!form.movntipo[0].checked && !form.movntipo[1].checked){
			alert('Escolha o tipo de movimento!');
		}else{
			if (form.movntipo[0].checked)
				window.open('actionPlanoConta.do?m=listaCaixaWindow&plcntipo=1', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 400, width = 550');
			else
				window.open('actionPlanoConta.do?m=listaCaixaWindow&plcntipo=2', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 400, width = 550');
			
			//window.open('planoContaPesquisaCaixaWindow.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');
			
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
				&nbsp;   LANÇAMENTOS NO CAIXA &nbsp;
			</legend>
			<html:form action="actionMovCaixa" focus="movddata" styleId="formMovCaixa">
			<html:hidden property="m" value="cadastro"/>
			<table style="width: 600px">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center; font-size: 13px;">
						Movimento Caixa
					</th>
				</tr>
				<tr>
					<th style="width: 25%">
						Data Movimento
					</th>
					<td style="width: 75%">
						<html:text property="movddata" styleId="data1" readonly="true" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/>
						<input type="image" id="b1" src="jscalendar/img.gif">
						&nbsp;&nbsp;&nbsp;
						<b>Data Vencimento</b>&nbsp;&nbsp;&nbsp;
						<html:text property="movdvenc" styleId="data2" readonly="true" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/>
						<input type="image" id="b2" src="jscalendar/img.gif">
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Tipo de Movimento
					</th>
					<td style="width: 75%">
						<html:radio property="movntipo" value="1" onclick="ajustaDIV()">&nbsp;Entrada</html:radio>&nbsp;&nbsp;&nbsp;
						<html:radio property="movntipo" value="2" onclick="ajustaDIV()">&nbsp;Saída</html:radio>
					</td>
				</tr>				
				<tr>
					<th style="width: 25%">
						Descrição (Conta)
					</th>
					<td style="width: 75%">
						<html:hidden property="movncont" styleId="movncont"/>
						<html:text property="movccont" styleId="movccont" size="50" readonly="true" style="color: #D00;"/>
						<img src="imagens/lupa.gif" alt="pesquisar" width="17" height="17" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="janelaDescricao(document.getElementById('formMovCaixa'))"/>
					</td>
				</tr>				
				<tr>
					<th style="vertical-align: top;">
						Forma Pgto.
					</th>
					<td>
						<html:select property="movnflqu" styleId="movnflqu" style="width: 375px;">
							<html:optionsCollection name="ls_formaliquidez" label="flqcdesc" value="flqncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th style="vertical-align: top;">
						Centros de Custo
					</th>
					<td style="color: #00D; font-weight: bold;">
						<logic:iterate id="b" name="ls_centrocusto">
							<input type="checkbox" name="movncecu" value="<bean:write name="b" property="crncodg"/>">&nbsp; <bean:write name="b" property="crcdesc"/><br>
						</logic:iterate>					
					</td>
				</tr>			 
			</tbody>
			</table>
			<div id="fornecedor">
			<table style="width: 600px">
			<tbody>
				<tr>
					<th style="width: 25%">
						Filial/Loja
					</th>
					<td style="width: 75%">
						<html:hidden property="movncgfl" styleId="movnfili"/>
						<html:text property="movcdcfl" styleId="movcfili" size="60" readonly="true" style="color: #D00;"/>
						<img src="imagens/lupa.gif" width="17" height="17" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('filialPesquisaWindow.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');"/>
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Fornecedor
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
			<table style="width: 600px">
			<tbody>
				<tr>
					<th style="width: 25%">
						Observação
					</th>
					<td style="width: 75%">
						<html:text property="movcdesc" size="40" maxlength="40"/>
					</td>
				</tr>
				<tr>
					<th>
						Documento
					</th>
					<td>
						<html:text property="movcdocm" size="60" maxlength="60"/>	
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
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
			</tbody>
			</table>
			</html:form>			
			<table style="width: 100%">
			<tbody>
				<logic:notEmpty name="ls_mov_dia">
				<tr>
					<th colspan="2">
						Lançamentos do dia <b>(Saldo: R$ <bean:write name="saldo_dia"/>)</b>
					</th>
					<th style="background-color: #DFA4F9;">
						Entradas
					</th>
					<th style="background-color: #A0B5EB;">
						Saídas
					</th>
				</tr>
				<tr>
					<th>
						Descrição
					</th>
					<th>
						Observação
					</th>
					<th>
						Documento
					</th>
					<th>
						Valor
					</th>
				</tr>
				</logic:notEmpty>
				<logic:empty name="ls_mov_dia">
				<tr>
					<td colspan="4">
						Não há movimento registrado de hoje.
					</td>
				</tr>
				</logic:empty>
				<logic:iterate id="b" name="ls_mov_dia" indexId="i">
				<tr style="background-color: <%= ((BeanMovCaixa)b).getMovntipo().equals("1")?"#DFA4F9":"#A0B5EB" %>;">
					<td>
						<bean:write name="b" property="movccont"/>
					</td>
					<td>
						<bean:write name="b" property="movcdesc"/>
					</td>
					<td>
						<bean:write name="b" property="movcdocm"/>
					</td>
					<th>
						R$ <bean:write name="b" property="movyvalr"/>
					</th>					
				</tr>
				</logic:iterate>
			
			</tbody>			
			</table>
					
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


<%@page import="com.grupoexata.financeiro.struts.bean.BeanMovCaixa"%>
	<script type="text/javascript">
		ajustaDIV();
	</script>

<%@page import="com.grupoexata.financeiro.struts.form.FormMovCaixa"%>
</html>