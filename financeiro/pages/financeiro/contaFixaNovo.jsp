	<%@include file="../topo.jsp" %>
	
	<script type="text/javascript">
<!--
	function ajustaDIV(){
		var f = document.getElementById('formContaFixa');
		
		if (f.cofncggpc[0].checked || f.cofncggpc[1].checked){
			document.getElementById('fornecedor').style.display = 'block';
			document.getElementById('entrada').style.display = 'none';
		}
		if (f.cofncggpc[2].checked){
			document.getElementById('fornecedor').style.display = 'none';
			document.getElementById('entrada').style.display = 'block';
		}
	}
	
	function janelaContas(form){
		if (form.cofnforn.value == ''){
			alert('Escolha antes o fornecedor!');
		}else{
			window.open('actionContaCorrente.do?m=listaCaixaWindow&enncodg='+form.cofnforn.value, 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 400, width = 550');
		}
	}
	
	function janelaDescricao(form){
		if (!form.cofncggpc[0].checked && !form.cofncggpc[1].checked && !form.cofncggpc[2].checked){
			alert('Escolha o grupo de Contas!');
		}else{
			if (form.cofncggpc[0].checked){
				window.open('contaGrupoPesquisaWindow.do?gpcncodg=1', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 400, width = 550');
			}else{
				if (form.cofncggpc[1].checked){
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
				&nbsp;   CONTAS FIXAS > NOVA &nbsp;
			</legend>
			<html:form action="actionContaFixa" focus="cofncont" styleId="formContaFixa" onsubmit="return window.confirm('Confirmar cadastro de Conta fixa?');">
			<html:hidden property="m" value="cadastro"/>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center; font-size: 14px;">
						Cadastro de Conta Fixa
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Grupo Conta
					</th>
					<td style="width: 75%">
						<html:radio property="cofncggpc" value="1" onclick="ajustaDIV()">&nbsp; <b>Despesa Fixa/Variável</b></html:radio>&nbsp;&nbsp;
						<html:radio property="cofncggpc" value="2" onclick="ajustaDIV()">&nbsp; <b>Investimentos</b></html:radio>&nbsp;&nbsp;
						<html:radio property="cofncggpc" value="3" onclick="ajustaDIV()" disabled="true">&nbsp; <b>Faturamento</b></html:radio>						
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Descrição (Conta)
					</th>
					<td style="width: 75%">
						<html:hidden property="cofncont" styleId="movncont"/>
						<html:text property="cofccont" styleId="movccont" size="50" readonly="true" style="color: #D00;"/>
						<img src="imagens/lupa.gif" alt="pesquisar" width="17" height="17" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="janelaDescricao(document.getElementById('formContaFixa'))"/>
					</td>
				</tr>
				<tr>
					<th>
						Dia Vencimento
					</th>
					<td>
						<html:text property="cofndia" size="2" maxlength="2" onkeyup="criaMascara(this,'##')"></html:text>
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
						<html:hidden property="cofnfili" styleId="movnfili"/>
						<html:text property="cofcfili" styleId="movcfili" size="60" readonly="true" style="color: #D00;"/>
						<img src="imagens/lupa.gif" width="17" height="17" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('filialPesquisaWindow.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');"/>
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Fornecedor
					</th>
					<td style="width: 75%">
						<html:hidden property="cofnforn" styleId="movnforn"/>
						<html:text property="cofcforn" styleId="movcforn" size="60" readonly="true" style="color: #D00;"/>
						<img src="imagens/lupa.gif" width="17" height="17" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('fornecedorPesquisaCaixaWindow.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');"/>
					</td>
				</tr>
				<tr>
					<th>
						Conta Corrente
					</th>
					<td>
						<html:hidden property="cofncoco" styleId="movncoco"/>
						<html:text property="cofccoco" styleId="movccoco" size="60" readonly="true" style="color: #D00;"/>
						<img src="imagens/lupa.gif" width="17" height="17" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="janelaContas(document.getElementById('formContaFixa'));"/>
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
						<html:text property="cofcdesc" size="40" maxlength="40"/>
					</td>
				</tr>
				<tr>
					<th>
						Documento
					</th>
					<td>
						<html:text property="cofcdocm" size="60" maxlength="60"/>
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Valor
					</th>
					<td style="width: 75%">
						<html:text property="cofyvalr" size="10" maxlength="10" onkeydown="Formata(this,10,event,2)" style="text-align: right;"></html:text>	
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'actionContaFixa.do?m=lista'">					
					</td>
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot" value="Salvar Conta Fixa"/>
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
											
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>

</html>