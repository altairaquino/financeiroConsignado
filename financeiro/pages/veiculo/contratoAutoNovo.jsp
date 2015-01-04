	<%@include file="../topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
	<script type="text/javascript" src="dwr/interface/Mapping.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>
	
	<script type="text/javascript">
	<!--
		function validaCPF(frm){
			if (!IsCPF(frm.ctacdocm.value)){
				alert('CPF digitado é inválido!');
			}else{
				Mapping.getNomeEntidadePorCPF(retornoNome,frm.ctacdocm.value);
			}
		}
		
		function retornoNome(nome){
			if (nome == ''){
				alert('Cliente ainda não cadastrado no sistema!');
			}else{
				DWRUtil.setValue('cliente',nome);
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
				&nbsp;   Cadastro de Novo Contrato - Veículo    &nbsp;
			</legend>
			<html:form action="actionContratoAuto" onsubmit="return window.confirm('Confirmar a inclusão do contrato com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> 
						Cadastro de Novo Contrato - Veículo
					</th>
				</tr>
				<tr>
					<th>
						Cliente
					</th>
					<td>
						<html:text property="ctacdocm" size="14" maxlength="14" onblur="validaCPF(this.form)" onkeyup="criaMascara(this,'###.###.###-##')"/>&nbsp;
						<html:text property="ctacnmen" styleId="cliente" size="50" maxlength="50" style="color: red;" readonly="true"/>
					</td>
				</tr>
				<tr>
					<th> 
						Nº do Contrato
					</th>
					<td>
						<html:text property="ctacnumr" size="13" maxlength="12" onkeyup="criaMascara(this,'##-######-##')"/>						
					</td>
				</tr>
				<tr>
					<th>
						Forma de Pagamento
					</th>
					<td>
						<html:select property="ctanfpto" style="width: 300px;">
							<html:optionsCollection name="ls_pgtocontratoauto" value="pgtncodg" label="pgtcdesc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						Data Base
					</th>
					<td>
						<html:text property="ctadbase" size="10" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/>
					</td>
				</tr>	
				<tr>
					<th>
						Loja Exata
					</th>
					<td>
						<html:select property="ctancgep" style="width: 300px;">
							<html:optionsCollection name="ls_empresa" label="epcnome" value="epncodg"/>
						</html:select>
					</td>
				</tr>	
				<tr>
					<th>
						Operador
					</th>
					<td>
						<html:hidden property="ctancgop" styleId="ctancgop"/>
						<html:text property="ctacnmop" styleId="ctacnmop" size="50" readonly="true" style="color: #D00;"/>
						<img src="imagens/lupa.gif" alt="pesquisar" width="17" height="17" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('operadorContratoPesquisaWindow.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');"/>
					</td>
				</tr>		
				<tr>
					<th>
						Loja Veículo
					</th>
					<td>
						<html:hidden property="ctancglj" styleId="ctancglj"/>
						<html:text property="ctacnmlj" styleId="ctacnmlj" size="50" readonly="true" style="color: #D00;"/>
						<img src="imagens/lupa.gif" alt="pesquisar" width="17" height="17" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('lojaContratoPesquisaWindow.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');"/>
					</td>
				</tr>				
				<tr>
					<th>
						Valor do Contrato (R$)
					</th>
					<td>
						<html:text property="ctayvalr" size="10" maxlength="10" onkeydown="Formata(this,10,event,2)" style="text-align: right;"/>						
					</td>
				</tr>
				<tr>
					<th>
						Plano
					</th>
					<td>
						<html:select property="ctanplan" style="width: 100px;">
							<html:option value="6">06 meses</html:option>
							<html:option value="12">12 meses</html:option>
							<html:option value="18">18 meses</html:option>
							<html:option value="24">24 meses</html:option>
							<html:option value="36">36 meses</html:option>
							<html:option value="48">48 meses</html:option>
							<html:option value="60">60 meses</html:option>
						</html:select>												
					</td>
				</tr>
				<tr>
					<th>
						Valor Parcela (R$)
					</th>
					<td>
						<html:text property="ctayvlpc" size="10" maxlength="10" onkeydown="Formata(this,10,event,2)" style="text-align: right;"/>						
					</td>
				</tr>				
				<tr>
					<th style="vertical-align: top;">
						Observações												
					</th>
					<td>
						<html:textarea property="ctacobsv" cols="65" rows="2"></html:textarea>
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
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>