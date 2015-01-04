	<%@include file="../topo.jsp" %>
	
	<script type="text/javascript" src="dwr/interface/Mapping.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>

		<script type="text/javascript">
		
			function preencheCidades(elem){
				Mapping.getCidades(retorno,elem.value,'proncgcd');			
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
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend class="red">
				&nbsp;   Processo > Alteração   &nbsp;
			</legend>
			<html:form action="actionProcesso" onsubmit="return window.confirm('Confirmar a alteração do processo com os dados fornecidos?')">
			<html:hidden property="m" value="update"/>
			<html:hidden property="proncodg"/>
			<input type="hidden" name="proncadt" value="<bean:write name="usuario" property="enncodg"/>">
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDD;">
						Dados Básicos do Cliente
					</th>
				</tr>
				<tr>
					<th>
						C.P.F.
					</th>
					<td>
						<bean:write name="formProcesso" property="proccpf"/>
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Nome
					</th>
					<td style="width: 75%">
						<bean:write name="formProcesso" property="procnmcl"/>
					</td>
				</tr>
				<tr>
					<th>
						Data Nascimento
					</th>
					<td>
						<bean:write name="formProcesso" property="prodnasc"/>
						&nbsp;<b>Sexo</b>&nbsp;
						<logic:equal name="formProcesso" property="procsexo" value="M">
							Masculino
						</logic:equal>
						<logic:equal name="formProcesso" property="procsexo" value="F">
							Feminino
						</logic:equal>	
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;"> 
						Contatos
					</th>
				</tr>
				<tr>
					<th style="width: 25%">
						Angariador
					</th>
					<td style="width: 75%">
						<html:hidden property="pronc2en" styleId="enncodg"/>
						<html:text property="procnmcn" styleId="encnome" size="50" maxlength="50" readonly="true" style="font-weight: bold; color: #D00;"/>
						<img src="imagens/pesquisa.jpg" width="22" height="22" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('contatoJuridicoPesquisaWindow.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');"/>					
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Consultor
					</th>
					<td style="width: 75%">
						<html:hidden property="proncons" styleId="fcancgfc"/>
						<html:text property="proccons" styleId="fcacnmfc" size="50" maxlength="50" readonly="true" style="font-weight: bold; color: #D00;"/>
						<img src="imagens/pesquisa.jpg" width="22" height="22" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('funcionarioAgenciaPesquisaWindow.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');"/>					
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Supervisor
					</th>
					<td style="width: 75%">
						<html:hidden property="pronsupe" styleId="empncodg"/>
						<html:text property="procsupe" styleId="empcdesc" size="50" maxlength="50" readonly="true" style="font-weight: bold; color: #D00;"/>
						<img src="imagens/pesquisa.jpg" width="22" height="22" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('funcionarioPesquisaWindow.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');"/>					
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> 
						Dados do Processo
					</th>
				</tr>
				<tr>
					<th style="width: 25%"> 
						Nº do Processo
					</th>
					<td style="width: 75%">
						<html:text property="procnumr" size="22" maxlength="20" onkeyup="criaMascara(this,'####################')"></html:text>						
					</td>
				</tr>
				<tr>
					<th>
						Tipo de Ação
					</th>
					<td>
						<html:select property="proncgtac" style="width: 290px;">
							<html:optionsCollection name="ls_tipoacaojudicial" value="tacncodg" label="taccdesc"/>
						</html:select>						
					</td>
				</tr>
				<tr>
					<th style="width: 25%"> 
						Localização Interna
					</th>
					<td style="width: 75%">
						<html:text property="procloca" size="65" maxlength="100"/>			
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> 
						Observações do Processo
					</th>
				</tr>
				<tr>
					<td colspan="2" align="center" style="text-align: center;">						
						<html:textarea property="procobs" rows="3" cols="80"></html:textarea>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'actionProcesso.do?m=dados&proncodg=<bean:write name="formProcesso" property="proncodg"/>'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Salvar Processo</html:submit>
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