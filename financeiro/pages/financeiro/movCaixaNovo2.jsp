	<%@include file="../topo.jsp" %>
	
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
				&nbsp;   Movimento Caixa > Novo &nbsp;
			</legend>
			<html:form action="actionMovCaixa" focus="movddata" styleId="formMovCaixa">
			<html:hidden property="m" value="cadastro"/>
			<html:hidden property="movltipo" value="M"/>
			<html:hidden property="movnqtpc" value="1"/>
			<html:hidden property="movnnrpc" value="1"/>
			<table style="width: 600px" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center; font-size: 13px;">
						Movimento Caixa
					</th>
				</tr>
				<% String data = new com.grupoexata.bancario.utils.FormataObj().formataData(new Date()); %>
				<tr>
					<th style="width: 25%">
						Data Movimento
					</th>
					<td style="width: 75%">
						<html:text property="movddata" value="<%= data %>" styleId="data1" readonly="true" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/>
						<input type="image" id="b1" src="jscalendar/img.gif">
						&nbsp;&nbsp;&nbsp;
						<b>Data Vencimento</b>&nbsp;&nbsp;&nbsp;
						<html:text property="movdvenc" value="<%= data %>" styleId="data2" readonly="true" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/>
						<input type="image" id="b2" src="jscalendar/img.gif">
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Grupo Conta
					</th>
					<td style="width: 75%">
						<html:radio property="movncggpc" value="1" onclick="ajustaDIV()">&nbsp; <b>Despesa Fixa/Vari�vel</b></html:radio>&nbsp;&nbsp;
						<html:radio property="movncggpc" value="2" onclick="ajustaDIV()">&nbsp; <b>Investimentos</b></html:radio>&nbsp;&nbsp;
						<html:radio property="movncggpc" value="3" onclick="ajustaDIV()">&nbsp; <b>Faturamento</b></html:radio>
						<%-- 
						<logic:iterate id="b1" name="ls_grupoconta">
							<input type="radio" onclick="ajustaDIV()" name="movncggpc" checked="checked" value="<bean:write name="b1" property="gpcncodg"/>">&nbsp;<b><bean:write name="b1" property="gpccdesc"/></b>
						</logic:iterate>
						--%>
					</td>
				</tr>
			</tbody>
			</table>
			<div id="entrada">
			<table style="width: 600px"  cellspacing="0" cellpadding="0">
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
			<table style="width: 600px" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<th style="width: 25%">
						Descri��o (Conta)
					</th>
					<td style="width: 75%">
						<html:hidden property="movncgcog" styleId="movncont"/>
						<html:text property="movcdccog" styleId="movccont" size="50" readonly="true" style="color: #D00;"/>
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
					<td>
						<table cellspacing="0" cellpadding="0">
						<tbody>
						<% List<BeanCentroCusto> l = (List<BeanCentroCusto>)session.getAttribute("ls_centrocusto");
						   Iterator<BeanCentroCusto> it = l.iterator();
						   while (it.hasNext()){
							   BeanCentroCusto centro = it.next();
							   out.print("<tr>");
							   out.print("<td style=\"color: #00D; font-weight: bold;\"><input type=\"checkbox\" name=\"movncecu\" value=\""+centro.getCrncodg()+"\">&nbsp; "+centro.getCrcdesc()+"</td>");
							   if (it.hasNext()){
								   centro = it.next();
								   out.print("<td style=\"color: #00D; font-weight: bold;\"><input type=\"checkbox\" name=\"movncecu\" value=\""+centro.getCrncodg()+"\">&nbsp; "+centro.getCrcdesc()+"</td>");
							   }else{
								   out.print("<td>&nbsp;</td>");
							   }
							   out.print("<tr>");
						   }
						%>
						</tbody>
						</table>	
					</td>
				</tr>			 
			</tbody>
			</table>
			<div id="fornecedor">
			<table style="width: 600px" cellspacing="0" cellpadding="0">
			<tbody>
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
			<table style="width: 600px" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<th style="width: 25%">
						Observa��o
					</th>
					<td style="width: 75%">
						<html:text property="movcdesc" size="60" maxlength="70"/>
					</td>
				</tr>
				<tr>
					<th>
						Documento
					</th>
					<td>
						<html:text property="movcdocm" size="60" maxlength="70"/>	
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
						<input type="button" class="btn_hot" value="Salvar Movimento" onclick="if (window.confirm('Confirmar o Lan�amento do Movimento no caixa?')){this.form.submit();}">
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


<%@page import="com.grupoexata.financeiro.struts.bean.BeanMovCaixa"%>
	<script type="text/javascript">
		ajustaDIV();
	</script>

<%@page import="com.grupoexata.financeiro.struts.form.FormMovCaixa"%>

<%@page import="java.util.List"%>
<%@page import="com.grupoexata.financeiro.struts.bean.BeanCentroCusto"%>
<%@page import="java.util.Iterator"%></html>