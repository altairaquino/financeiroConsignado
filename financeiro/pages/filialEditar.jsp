	<%@include file="topo.jsp" %>
	
	<script type="text/javascript" src="dwr/interface/Mapping.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>

		<script type="text/javascript">
		
			function preencheCidades(elem){			
				Mapping.getCidades(retorno,elem.value,'flncgcd');						
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
				&nbsp;  Filial > Alteração de Dados &nbsp;
			</legend>
			<html:form action="actionFilial" focus="flcnome" onsubmit="return window.confirm('Confirmar a inclusão da filial com os dados fornecidos?')">			
			<html:hidden property="m" value="update"/>
			<html:hidden property="flncodg"/>
			<table style="width: 550px;" align="center">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> 
						Alteração de Dados de Filial
					</th>
				</tr>
				<tr>
					<td>
						Nome
					</td>
					<td>
						<html:text property="flcnome" size="60" maxlength="50"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Responsável
					</td>
					<td>
						<html:text property="flcresp" size="60" maxlength="50"></html:text>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;"> Contato </th>
				</tr>
				<tr>
					<td>
						TeleFone
					</td>
					<td>
						<html:text property="flcfone" size="14" maxlength="13" onkeyup="criaMascara(this, '(##)####-####');"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						E-mail
					</td>
					<td>
						<html:text property="flcmail" size="60" maxlength="50"></html:text>
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
						<html:select property="flncgtl">
							<html:optionsCollection name="ls_tipologradouro" value="tlncodg" label="tlcdesc"/>
						</html:select>
						<html:text property="flclogr" size="42" maxlength="50"></html:text>						
					</td>
				</tr>
				<tr>
					<td>
						Estado
					</td>
					<td>
						<html:select property="flcufcd" onclick="preencheCidades(this)" onblur="preencheCidades(this)">
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
						<html:select property="flncgcd" style="width: 375px;">
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
						<html:text property="flccep" size="10" maxlength="9" onkeyup="criaMascara(this, '#####-###');" ></html:text>
						<html:text property="flcbair" size="47" maxlength="35"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Cancelar" class="btn" onclick="window.location = 'actionFilial.do?m=lista'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Salvar Filial</html:submit>
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