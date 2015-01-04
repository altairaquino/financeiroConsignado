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

	function marca_desmarcar_centro(){
		var elem = document.getElementsByName('movncecu');
		for (i = 0; i < elem.length; i++){
			elem[i].checked = document.getElementById('marcar').checked?1:0;			
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
			<legend class="red">
				&nbsp;   Movimento Caixa > Nova Saída &nbsp;
			</legend>
			<html:form action="actionMovCaixa" focus="movddata" styleId="formMovCaixa">
			<html:hidden property="m" value="cadastroSaida"/>
			<html:hidden property="movltipo" value="M"/>
			<html:hidden property="movnqtpc" value="1"/>
			<html:hidden property="movnnrpc" value="1"/>
			<table style="width: 650px" cellspacing="0" cellpadding="0" align="center">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center; font-size: 13px;">
						Movimento Caixa - Saída
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
						<input type="radio" name="movncggpc" value="1" checked="checked">&nbsp;<b>Despesa Fixa/Variável</b>&nbsp;&nbsp;
						<input type="radio" name="movncggpc" value="2">&nbsp;<b>Investimentos</b>&nbsp;&nbsp;
						<input type="radio" name="movncggpc" value="3" disabled="disabled">&nbsp;<b>Faturamento</b>&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<th style="width: 30%">
						Descrição (Conta)
					</th>
					<td style="width: 70%">
						<html:hidden property="movncgcog" styleId="movncont"/>
						<html:text property="movcdccog" styleId="movccont" size="50" readonly="true" style="color: #D00;"/>
						<img src="imagens/lupa.gif" alt="pesquisar" width="17" height="17" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="janelaDescricao(document.getElementById('formMovCaixa'))"/>
					</td>
				</tr>				
				<tr>
					<th style="vertical-align: top;">
						Forma Pagamento
					</th>
					<td>
						<html:select property="movnflqu" styleId="movnflqu" style="width: 375px;">
							<html:optionsCollection name="ls_formaliquidez" label="flqcdesc" value="flqncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th style="background-color: #ddd; text-align: center;" colspan="2">
						Produto/Cliente/Centro de Custo
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<table cellspacing="0" cellpadding="2" align="center">
						<tbody>
							<tr>
								<td style="color: #00D; font-weight: bold; padding: 3px;" colspan="2">
									<input type="checkbox" id="marcar" onclick="marca_desmarcar_centro()">&nbsp; Marca/Desmarca Todos
								</td>
							</tr>
						<% List<BeanCentroCusto> l = (List<BeanCentroCusto>)session.getAttribute("ls_centrocusto");
						   Iterator<BeanCentroCusto> it = l.iterator();
						   String style = "style=\"color: #00D; font-weight: bold; padding: 3px;\"";
						   while (it.hasNext()){
							   BeanCentroCusto centro = it.next();
							   out.print("<tr>");
							   out.print("<td "+style+"><input type=\"checkbox\" name=\"movncecu\" value=\""+centro.getCrncodg()+"\">&nbsp; "+centro.getCrcdesc()+"</td>");
							   if (it.hasNext()){
								   centro = it.next();
								   out.print("<td "+style+"><input type=\"checkbox\" name=\"movncecu\" value=\""+centro.getCrncodg()+"\">&nbsp; "+centro.getCrcdesc()+"</td>");
								   if (it.hasNext()){
									   centro = it.next();
									   out.print("<td "+style+"><input type=\"checkbox\" name=\"movncecu\" value=\""+centro.getCrncodg()+"\">&nbsp; "+centro.getCrcdesc()+"</td>");
								   }
								   if (it.hasNext()){
									   centro = it.next();
									   out.print("<td "+style+"><input type=\"checkbox\" name=\"movncecu\" value=\""+centro.getCrncodg()+"\">&nbsp; "+centro.getCrcdesc()+"</td>");
								   }
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
				<tr>
					<th style="width: 30%; color: #D00;">
						Pendência de Comprovante?
					</th>
					<td style="width: 70%">
						<html:radio property="movlpend" value="T">Sim</html:radio>&nbsp;&nbsp;
						<html:radio property="movlpend" value="F">Não</html:radio>
					</td>
				</tr>
				<tr>
					<th style="width: 30%">
						Filial/Loja
					</th>
					<td style="width: 70%">
						<html:hidden property="movnfili" styleId="movnfili"/>
						<html:text property="movcfili" styleId="movcfili" size="60" readonly="true" style="color: #D00;"/>
						<img src="imagens/lupa.gif" width="17" height="17" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('filialPesquisaWindow.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');"/>
					</td>
				</tr>
				<tr>
					<th style="width: 30%">
						Beneficiário
					</th>
					<td style="width: 70%">
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
				<tr>
					<th style="width: 30%">
						Observação
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
		<logic:present name="msg">
			<input type="hidden" value="<bean:write name="msg"/>" name="msg" id="msg">
			<script>
				var x = document.getElementById('msg').value;
				alert(x);
			</script>
		</logic:present>
		
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
	
<%@page import="java.util.List"%>
<%@page import="com.grupoexata.financeiro.struts.bean.BeanCentroCusto"%>
<%@page import="java.util.Iterator"%></html>