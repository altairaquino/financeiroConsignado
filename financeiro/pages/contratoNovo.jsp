	<%@include file="topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
	<script type="text/javascript" src="dwr/interface/Mapping.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>
	
	<script type="text/javascript">
	<!--
		function validaCPF(frm){
			if (!IsCPF(frm.ctnc2en.value)){
				alert('CPF digitado é inválido!');
			}else{
				Mapping.getNomeEntidadePorCPF(retornoNome,frm.ctnc2en.value);
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
				&nbsp;   Cadastro de Novo Contrato    &nbsp;
			</legend>
			<html:form action="actionContrato" onsubmit="return window.confirm('Confirmar a inclusão do contrato com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> Cadastro de Novo Contrato </th>
				</tr>
				<tr>
					<td style="width: 25%">
						Agente de Crédito
					</td>
					<td style="color: red; font-weight: bold;"  style="width: 75%">
						<bean:write name="angariador" property="encnome"/>
						<input type="hidden" name="ctncgen" value="<bean:write name="angariador" property="enncodg"/>">
						<input type="hidden" name="ctncadt" value="<bean:write name="usuario" property="enncodg"/>">
					</td>
				</tr>				
				<tr>
					<td style="width: 25%">
						Cliente
					</td>
					<td style="width: 75%">
						<html:text property="ctnc2en" size="14" maxlength="14" onblur="validaCPF(this.form)" onkeyup="criaMascara(this,'###.###.###-##')"/>&nbsp;
						<input type="text" name="cliente" id="cliente" size="50" maxlength="50" disabled="disabled" style="color: red;">
					</td>
				</tr>
				<tr>
					<td> 
						Nº do Contrato
					</td>
					<td>
						<html:text property="ctnnumr" size="20" maxlength="20" onkeyup="criaMascara(this,'##################')"></html:text>						
					</td>
				</tr>
				<tr>
					<td>
						Forma de Pagamento
					</td>
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
					<td style="width: 25%">
						Banco da O.P.
					</td>
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
					<td style="width: 25%">
						Produto
					</td>
					<td style="width: 75%">
						<html:select property="ctncgpd" style="width: 300px;" onclick="listaTabelas(this.form)" onblur="listaTabelas(this.form)">
							<html:optionsCollection name="ls_produto" label="pdcdesc" value="pdncodg"/>
						</html:select>
					</td>
				</tr>				
				<tr>
					<td>
						Tabela Produto
					</td>
					<td>
						<div id="tabela">
							<select name="ctncgtp" style="width: 300px;">
								<option value="-1">Escolha o produto</option>				
							</select>
						</div>						
					</td>
				</tr>
				<tr>
					<td>
						Valor do Contrato
					</td>
					<td>
						<html:text property="ctyvalr" size="10" maxlength="10" onkeydown="Formata(this,10,event,2)" style="text-align: right;"></html:text>						
					</td>
				</tr>				
				<tr>
					<td colspan="2" style="text-align: right;">
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