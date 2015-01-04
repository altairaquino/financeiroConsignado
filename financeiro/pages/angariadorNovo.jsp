	<%@include file="topo.jsp" %>
	
	<script type="text/javascript" src="dwr/interface/Mapping.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>

		<script type="text/javascript">

			function ajustaDiv(el){
				if (Number(el.value)==1){
					document.getElementById('nascimento').style.visibility = 'visible';
					document.getElementById('sexo').style.visibility = 'visible';
				}else{
					document.getElementById('nascimento').style.visibility = 'hidden';
					document.getElementById('sexo').style.visibility = 'hidden';
				}
			}
		
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
				&nbsp;   Cadastro de Novo Agente de Crédito    &nbsp;
			</legend>
			<html:form action="actionEntidade" focus="encnome" onsubmit="return window.confirm('Confirmar a inclusão do angariador com os dados fornecidos?')">			
			<html:hidden property="m" value="cadastroAngariador"/>
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> 
						Cadastro de Novo Agente de Crédito
					</th>
				</tr>
				<tr>
					<td>
						Nome/Razão Social
					</td>
					<td>
						<html:text property="encnome" size="60" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<td>
						Tipo Pessoa
					</td>
					<td>
						<html:radio property="enncgpp" styleId="enncgpp" value="1" onclick="ajustaDiv(this)">&nbsp;Física</html:radio>&nbsp;
						<html:radio property="enncgpp" value="2" onclick="ajustaDiv(this)">&nbsp;Jurídica</html:radio>&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						CPF/CNPJ
					</td>
					<td>
						<html:text property="encdocm" size="15" maxlength="14" onkeyup="criaMascara(this, '##############');"/>
					</td>
				</tr>
				<tr id="nascimento">
					<td>
						Data Nascimento
					</td>
					<td>
						<html:text property="endnasc" size="11" maxlength="10" onkeyup="criaMascara(this, '##/##/####');" />
					</td>
				</tr>
				<tr id="sexo">
					<td>
						Sexo
					</td>
					<td>
						<html:radio property="encsexo" value="M">Masculino</html:radio>&nbsp;&nbsp;&nbsp;
						<html:radio property="encsexo" value="F">Feminino</html:radio>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;"> Contato </th>
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
						<html:submit styleClass="btn_hot">Cadastrar Agente de Crédito</html:submit>
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
		ajustaDiv(document.getElementById('enncgpp'));
	</script>
</body>
</html>