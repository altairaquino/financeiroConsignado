	<%@include file="topo.jsp" %>
	
	<script type="text/javascript" src="dwr/interface/Mapping.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>

		<script type="text/javascript">
		
			function preencheCidades(elem){
				Mapping.getCidades(retorno,elem.value,'enncgcd');							
			}
			
			function retorno(valor){
				DWRUtil.setValue("cidade",valor);
			}
			
			function validaCPF(frm){
				if (!IsCPF(frm.encdocm.value.replace('.','').replace('-',''))){
					alert('CPF digitado é inválido!');
					document.getElementById('cliente').readOnly = false;
					DWRUtil.setValue('cliente','');
				}else{
					Mapping.getNomeEntidadePorCPF(retornoNome,frm.encdocm.value);
				}
			}
			
			function retornoNome(nome){
				if (nome != ''){
					DWRUtil.setValue('cliente',nome);
					document.getElementById('cliente').readOnly = true;
					document.getElementById('cliente').focus();
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
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<% request.setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
		   request.setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
		
		%>
		
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend class="red">
				&nbsp;   Cadastro de Novo Contrato    &nbsp;
			</legend>
			<html:form action="actionContrato" focus="encdocm" onsubmit="return window.confirm('Confirmar a inclusão do contrato com os dados fornecidos?')">
			<html:hidden property="m" value="cadastroCompleto"/>
			<input type="hidden" name="ctncgen" value="<bean:write name="usuario" property="enncodg"/>">
			<input type="hidden" name="ctncadt" value="<bean:write name="usuario" property="enncodg"/>">
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">
						Dados Básicos do Cliente 
					</th>
				</tr>
				<tr>
					<th>
						C.P.F.
					</th>
					<td>
						<html:text property="encdocm" size="15" maxlength="14" onkeyup="criaMascara(this, '###.###.###-##');" onblur="validaCPF(this.form)"/>
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Nome
					</th>
					<td style="width: 75%">
						<html:text property="encnome" size="60" maxlength="50" styleId="cliente"/>
					</td>
				</tr>
				<tr>
					<th>
						Data Nascimento
					</th>
					<td>
						<html:text property="endnasc" size="11" maxlength="10" onkeyup="criaMascara(this, '##/##/####');"/>
						&nbsp;<b>Sexo</b>&nbsp;
						<html:radio property="encsexo" value="M">Masculino</html:radio>&nbsp;&nbsp;&nbsp;
						<html:radio property="encsexo" value="F">Feminino</html:radio>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;">
						Dados do RG do Cliente
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<b>Nº RG</b>&nbsp;<html:text property="encrg" size="12" maxlength="11" onkeyup="criaMascara(this, '###########');"/>
						&nbsp;<b>Data de Exp.:</b>&nbsp;<html:text property="endexrg" size="11" maxlength="10" onkeyup="criaMascara(this, '##/##/####');"/>
						&nbsp;<b>Org. Exp.:</b>&nbsp;<html:text property="encoerg" size="5" maxlength="5"/>
						&nbsp;<b>UF:</b>&nbsp;<html:select property="encufrg">
							<html:optionsCollection name="ls_estado" value="cdcuf" label="cdcuf"/>
						</html:select><br>
						<b>Doc. Origem</b>&nbsp;<html:text property="encdorg" size="80" maxlength="80"/>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;">
						Filiação
					</th>
				</tr>
				<tr>
					<td>
						Nome da Mãe
					</td>
					<td>
						<html:text property="encmae" size="60" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<td>
						Nome do Pai
					</td>
					<td>
						<html:text property="encpai" size="60" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;"> 
						Contato e Endereço  
					</th>
				</tr>
				<tr>
					<th>
						Telefone
					</th>
					<td>
						<html:text property="encfone" size="14" maxlength="13" onkeyup="criaMascara(this, '(##)####-####');"/>
					</td>
				</tr>
				<tr>
					<th>
						Logradouro
					</th>
					<td>
						<html:select property="enncgtl">
							<html:optionsCollection name="ls_tipologradouro" value="tlncodg" label="tlcdesc"/>
						</html:select>
						<html:text property="enlendr" size="42" maxlength="50"/>						
					</td>
				</tr>
				<tr>
					<th>
						Estado
					</th>
					<td>
						<html:select property="encufcd" styleId="encufcd" onclick="preencheCidades(this)" onblur="preencheCidades(this)">
							<html:optionsCollection name="ls_estado" value="cdcuf" label="cdcuf"/>
						</html:select>										
					</td>
				</tr>
				<tr>
					<th>
						Cidade
					</th>
					<td>
						<div id="cidade">
						<html:select property="enncgcd" style="width: 375px;">
							<html:option value="-1">Escolha o estado</html:option>
						</html:select>
						</div>
					</td>
				</tr>			
				<tr>
					<th>
						CEP/Bairro
					</th>
					<td>
						<html:text property="enccep" size="10" maxlength="9" onkeyup="criaMascara(this, '#####-###');"/>
						<html:text property="encbair" size="47" maxlength="35"/>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> 
						Dados do Contrato 
					</th>
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
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<th style="width: 25%">
						Banco da O.P.
					</th>
					<td style="width: 75%">
						<html:select property="ctncgbc" style="width: 180px;">
							<html:option value="-1">Escolha o Banco</html:option>
							<html:option value="1">001 - Banco do Brasil S.A.</html:option>
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
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<th style="width: 25%">
						Produto
					</th>
					<td style="width: 75%">
						<html:select property="ctncgpd" style="width: 300px;" onclick="listaTabelas(this.form)" onblur="listaTabelas(this.form)">
							<html:optionsCollection name="ls_produto" label="pdcdesc" value="pdncodg"/>
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
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Salvar Contrato</html:submit>
					</td>
				</tr>	
							
			</tbody>			
			</table>
			</html:form>
		
		</fieldset>
			
		</div>
		<script type="text/javascript">
			ajustaDIV();
			listaTabelas(document.forms[0]);
			preencheCidades(document.getElementById('encufcd'));
		</script>
		
		<%@include file="footer.jsp" %>
	</div>
</body>

<%@page import="com.grupoexata.bancario.dao.ModelCidade"%>

<%@page import="com.grupoexata.bancario.dao.ModelTipoLogradouro"%></html>