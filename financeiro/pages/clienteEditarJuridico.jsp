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
				&nbsp;   Dados de Cliente    &nbsp;
			</legend>
			<html:form action="actionEntidade" focus="encnome">
			<html:hidden property="enncodg"/>
			<html:hidden property="m" value="updateClienteJuridico"/>
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> 
						Altera Dados Cadastrais do Cliente 
					</th>
				</tr>
				<tr>
					<td>
						Nome
					</td>
					<td>
						<html:text property="encnome" size="60" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<td>
						C.P.F.
					</td>
					<td>
						<html:text property="encdocm" size="15" maxlength="14" onkeyup="criaMascara(this, '###.###.###-##');"/>(*)
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
						<html:select property="encufcd" onclick="preencheCidades(this)">
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
							<html:optionsCollection name="ls_cidade" label="cdcdesc" value="cdncodg"/>
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
						<html:text property="encbair" size="47" maxlength="35"></html:text>
					</td>
				</tr>
				<tr>
					<th valign="top" colspan="2" style="text-align: center;background-color: #DDDDDD;"> Observações Gerais </th>
				</tr>					
				<tr>
					<td>
						<input type="hidden" value="<bean:write name="processo" property="proncodg"/>" name="proncodg">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionProcesso.do?m=opcoes&proncodg=<bean:write name="processo" property="proncodg"/>'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Salvar Cliente</html:submit>
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