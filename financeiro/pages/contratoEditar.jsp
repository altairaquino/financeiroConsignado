	<%@include file="topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
	<script type="text/javascript" src="dwr/interface/Mapping.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>
	
	<script type="text/javascript">
	<!--
		function validaCPF(frm){
			if (!IsCPF(frm.ctccpf.value)){
				alert('CPF digitado é inválido!');
			}else{
				Mapping.getNomeEntidadePorCPF(retornoNome,frm.ctccpf.value);
			}
		}
		
		function retornoNome(nome){
			if (nome == ''){
				alert('Cliente ainda não cadastrado no sistema!');
			}else{
				DWRUtil.setValue('cliente',nome);
			}			
		}
		
		function retornoTab(tab){
			DWRUtil.setValue('tabela',tab);			
		}
				
		function listaTabelas(frm){
			Mapping.getTabelasDoProduto(retornoTab,frm.ctncgpd.value,'ctncgtp');
		}
		function ajustaDIV(){
			var f = document.getElementById('ctncgfp');
			if (f.value == '2'){
				document.getElementById('banco').style.display = 'block';
			}else{
				document.getElementById('banco').style.display = 'none';
			}
		}
	//-->
	</script>
			
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Alteração do Contrato    &nbsp;
			</legend>
			<html:form action="actionContrato" onsubmit="return window.confirm('Confirmar a alteração do contrato com os dados fornecidos?')">
			<html:hidden property="m" value="update"/>
			<html:hidden property="ctncodg"/>
			<html:hidden property="ctncgen" styleId="ctncgen"/>
			
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> 
						Alteração do Contrato 
					</th>
				</tr>
				<tr>
					<th style="width: 25%">
						Agente de Crédito
					</th>
					<td style="color: red; font-weight: bold; vertical-align: middle;" style="width: 75%">
						<html:text property="ctcnman" styleId="ctcnman" size="45" maxlength="50" readonly="true"/>
						<img src="imagens/lupa.gif" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('angariadorPesquisaWindow.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');"/>					
					</td>
				</tr>				
				<tr>
					<th style="width: 25%">
						Cliente
					</th>
					<td style="width: 75%">
						<html:text property="ctccpf" size="14" maxlength="14" onblur="validaCPF(this.form)" onkeyup="criaMascara(this,'###.###.###-##')"/>&nbsp;
						<html:text property="ctcnmcl" styleId="cliente" size="50" maxlength="50" readonly="true" style="color: red; font-weight: bold;"/>						
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Nº do Contrato
					</th>
					<td style="width: 75%">
						<html:text property="ctnnumr" size="20" maxlength="20" onkeyup="criaMascara(this,'##################')"></html:text>						
					</td>
				</tr>
				<tr>
					<th>
						Forma de Pagamento
					</th>
					<td>
						<html:select property="ctncgfp" styleId="ctncgfp" style="width: 300px;" onchange="ajustaDIV();">
							<html:optionsCollection name="ls_formapagamento" value="fpncodg" label="fpcdesc"/>
						</html:select>
					</td>
				</tr>
			</tbody>
			</table>
			<div id="banco">
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th style="width: 25%">
						Banco da O.P.
					</th>
					<td style="width: 75%">
						<html:select property="ctncgbc" style="width: 180px;">
							<html:option value="-1">Escolha o Banco</html:option>
							<html:option value="1">001 - Banco do Brasil</html:option>
							<html:option value="237">237 - Bradesco S.A.</html:option>
							<html:option value="356">356 - Banco ABN AMRO Real S.A.</html:option>
							<html:option value="104">104 - Caixa Economica Federal</html:option>
						</html:select>
						&nbsp;<b>Agência da O.P.:</b>&nbsp;<html:text property="ctcagen" maxlength="5" size="5"/>
					</td>
				</tr>
			</tbody>
			</table>
			</div>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th style="width: 25%"> 
						Produto
					</th>
					<td style="width: 75%">
						<html:select property="ctncgpd" style="width: 300px;" onclick="listaTabelas(this.form)">
							<html:optionsCollection name="ls_produto" value="pdncodg" label="pdcdesc"/>
						</html:select>						
					</td>
				</tr>			
				<tr>
					<th>
						Tabela Produto
					</th>
					<td>
						<div id="tabela">
							<html:select property="ctncgtp" style="width: 300px;">
								<html:optionsCollection name="ls_tabelaproduto" label="tpcdesc2" value="tpncodg"/>					
							</html:select>
						</div>						
					</td>
				</tr>
				<tr>
					<th>
						Valor do Contrato
					</th>
					<td>
						<html:text property="ctyvalr" size="10" maxlength="10" onkeydown="Formata(this,10,event,2)" style="text-align: right;"></html:text>						
					</td>
				</tr>
				<tr>
					<th>
						Percentual da Exata
					</th>
					<td>
						<html:text property="ctnexat" size="10" maxlength="10" onkeydown="Formata(this,10,event,2)" style="text-align: right;"></html:text>												
					</td>
				</tr>		
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'actionContrato.do?m=dados&ctncodg=<bean:write name="formContrato" property="ctncodg"/>'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Salvar Contrato</html:submit>
					</td>
				</tr>								
				
			</tbody>			
			</table>
			</html:form>
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
	<script type="text/javascript">
		ajustaDIV();
	</script>
</body>
</html>