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
<style type="text/css">
	.style1{
		text-align: right;
		width: 120px;
		color:#660000;
	}
</style>
			
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Cadastro de Funcionário    &nbsp;
			</legend>
			<html:form action="actionEntidade" focus="encnome" onsubmit="return window.confirm('Confirmar a inclusão do funcionário com os dados fornecidos?')">
			<html:hidden property="m" value="cadastroFuncionario"/>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<td colspan="2"> (*)Dados obrigatórios  </td>
				</tr>
				<tr>
					<th colspan="2" style="background-color: #ddd; text-align: center;">
						Dados Básicos do funcionário
					</th>
				</tr>
				<tr>
					<td class="style1">
						Nome
					</td>
					<td>
						<html:text property="encnome" size="60" maxlength="50"></html:text>(*)
					</td>
				</tr>
				<tr>
					<td class="style1">
						C.P.F.
					</td>
					<td>
						<html:text property="encdocm" size="15" maxlength="14"></html:text>(*)
					</td>
				</tr>
				<tr>
					<td class="style1">
						Data Nascimento
					</td>
					<td>
						<html:text property="endnasc" size="11" maxlength="10" onkeyup="criaMascara(this, '##/##/####');" ></html:text>(*)
					</td>
				</tr>
				<tr>
					<td class="style1">
						Sexo
					</td>
					<td>
						<html:radio property="encsexo" value="M">Masculino</html:radio>&nbsp;&nbsp;&nbsp;
						<html:radio property="encsexo" value="F">Feminino</html:radio>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						 Filiação
					</th>
				</tr>				
				<tr>
					<td colspan="2">
						<hr/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Nome da mãe:
					</td>
					<td>
						<html:text property="encmae" maxlength="50" size="50"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Nome do pai:
					</td>
					<td>
						<html:text property="encpai" maxlength="50" size="50"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr/>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						 Contato 
					</th>
				</tr>
				
				<tr>
					<td colspan="2">
						<hr/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Fone Fixo
					</td>
					<td>
						<html:text property="encfone" size="14" maxlength="13" onkeyup="criaMascara(this, '(##)####-####');"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Celular
					</td>
					<td>
						<html:text property="enccell" size="14" maxlength="13" onkeyup="criaMascara(this, '(##)####-####');"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						E-mail
					</td>
					<td>
						<html:text property="encmail" size="60" maxlength="50"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="2"><hr/></td>
				</tr>
				<tr>
					<th colspan="2">Endereço</th>
				</tr>				
				<tr>
					<td colspan="2"><hr/></td>
				</tr>
				<tr>
					<td class="style1">
						Logradouro
					</td>
					<td>
						<html:select property="enncgtl">
							<html:optionsCollection name="ls_tipologradouro" value="tlncodg" label="tlcdesc"/>
						</html:select>
						<html:text property="enlendr" size="42" maxlength="50"></html:text>						
					</td>
				</tr>
				<tr>
					<td class="style1">
						Estado
					</td>
					<td>
						<html:select property="encufcd" onclick="preencheCidades(this)" onblur="preencheCidades(this)">
							<html:optionsCollection name="ls_estado" value="cdcuf" label="cdcuf"/>
						</html:select>										
					</td>
				</tr>
				<tr>
					<td class="style1">
						Cidade
					</td>
					<td>
						<div id="cidade">
						<html:select property="enncgcd" style="width: 375px;">
							<logic:notPresent name="ls_cidade">
							<html:option value="-1">Escolha o estado</html:option>
							</logic:notPresent>
							<logic:present name="ls_cidade">
							<html:optionsCollection name="ls_cidade" value="cdncodg" label="cdcdesc"/>
							</logic:present>
						</html:select>
						</div>
					</td>
				</tr>				
				<tr>
					<td class="style1">
						CEP/Bairro
					</td>
					<td>
						<html:text property="enccep" size="10" maxlength="9" onkeyup="criaMascara(this, '#####-###');" />
						<html:text property="encbair" size="47" maxlength="35"/>
					</td>
				</tr>
				<tr>
					<td colspan="2"><hr/></td>
				</tr>
				<tr>
					<th colspan="2">Observações Gerais</th>
				</tr>				
				<tr>
					<td colspan="2"><hr/></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<html:textarea property="enmobs" cols="60" rows="3" style="width: 100%;">					
						</html:textarea>						
					</td>
				</tr>		
				<tr>
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Cadastrar Funcionário</html:submit>
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