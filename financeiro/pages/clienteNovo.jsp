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
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Cadastro de Cliente    &nbsp;
			</legend>
			<html:form action="actionEntidade" focus="encnome" onsubmit="return window.confirm('Confirmar a inclusão do cliente com os dados fornecidos?')">
			<html:hidden property="m" value="cadastroCliente"/>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<td colspan="2"> (*)Dados obrigatórios  </td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> Cadastro de Cliente </th>
				</tr>
				<tr>
					<td>
						Nome
					</td>
					<td>
						<html:text property="encnome" size="60" maxlength="50"></html:text>(*)
					</td>
				</tr>
				<tr>
					<td>
						C.P.F.
					</td>
					<td>
						<html:text property="encdocm" size="15" maxlength="14" readonly="true"/>(*)
					</td>
				</tr>
				<tr>
					<td>
						Data Nascimento
					</td>
					<td>
						<html:text property="endnasc" size="11" maxlength="10" onkeyup="criaMascara(this, '##/##/####');"/>(*)
					</td>
				</tr>
				<tr>
					<td>
						Sexo
					</td>
					<td>
						<html:radio property="encsexo" value="M">Masculino</html:radio>&nbsp;&nbsp;&nbsp;
						<html:radio property="encsexo" value="F">Feminino</html:radio>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;">
						Dados do RG
					</th>
				</tr>
				<tr>
					<td colspan="2">
						Nº RG&nbsp;<html:text property="encrg" size="12" maxlength="11" onkeyup="criaMascara(this, '###########');"/>
						&nbsp;Data de Exp.:&nbsp;<html:text property="endexrg" size="11" maxlength="10" onkeyup="criaMascara(this, '##/##/####');"/>
						&nbsp;Org. Exp.:&nbsp;<html:text property="encoerg" size="5" maxlength="5"/>
						&nbsp;UF:&nbsp;<html:select property="encufrg">
							<html:optionsCollection name="ls_estado" value="cdcuf" label="cdcuf"/>
						</html:select><br>
						Doc. Origem&nbsp;<html:text property="encdorg" size="80" maxlength="80"/>
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
						Contato
					</th>
				</tr>
				<tr>
					<td>
						Fone Fixo
					</td>
					<td>
						<html:text property="encfone" size="14" maxlength="13" onkeyup="criaMascara(this, '(##)####-####');"/>
					</td>
				</tr>
				<tr>
					<td>
						Celular
					</td>
					<td>
						<html:text property="enccell" size="14" maxlength="13" onkeyup="criaMascara(this, '(##)####-####');"/>
					</td>
				</tr>
				<tr>
					<td>
						E-mail
					</td>
					<td>
						<html:text property="encmail" size="60" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;"> Endereço </th>
				</tr>
				<tr>
					<td>
						Logradouro
					</td>
					<td>
						<html:select property="enncgtl">
							<html:optionsCollection name="ls_tipologradouro" value="tlncodg" label="tlcdesc"/>
						</html:select>
						<html:text property="enlendr" size="42" maxlength="50"/>						
					</td>
				</tr>
				<tr>
					<td>
						Estado
					</td>
					<td>
						<html:select property="encufcd" onclick="preencheCidades(this)" onblur="preencheCidades(this)">
							<html:optionsCollection name="ls_estado" value="cdcuf" label="cdcuf"/>
						</html:select>										
					</td>
				</tr>
				<tr>
					<td>
						Cidade
					</td>
					<td>
						<div id="cidade">
						<html:select property="enncgcd" style="width: 375px;">
							<html:option value="-1">Escolha o estado</html:option>
						</html:select>
						</div>
					</td>
				</tr>				
				<tr>
					<td>
						CEP/Bairro
					</td>
					<td>
						<html:text property="enccep" size="10" maxlength="9" onkeyup="criaMascara(this, '#####-###');"/>
						<html:text property="encbair" size="47" maxlength="35"/>
					</td>
				</tr>
				<tr>
					<th valign="top" colspan="2" style="text-align: center;background-color: #DDDDDD;"> Observações Gerais </th>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<html:textarea property="enmobs" cols="70" rows="3">						
						</html:textarea>						
					</td>
				</tr>		
				<tr>
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Cadastrar Cliente</html:submit>
					</td>
				</tr>
				
				
			
			</tbody>			
			</table>
			</html:form>
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>