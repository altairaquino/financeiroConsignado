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
			
			function validaCPF(frm){
				if (!IsCPF(frm.proccpf.value.replace('.','').replace('-',''))){
					alert('CPF digitado é inválido!');
					document.getElementById('cliente').readOnly = false;
					DWRUtil.setValue('cliente','');
				}else{
					Mapping.getNomeEntidadePorCPF(retornoNome,frm.proccpf.value);
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
				&nbsp;   Cadastro de Novo Processo    &nbsp;
			</legend>
			<html:form action="actionProcesso" focus="encdocm" onsubmit="return window.confirm('Confirmar a inclusão do processo com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>
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
						<html:text property="proccpf" size="15" maxlength="14" onkeyup="criaMascara(this, '###.###.###-##');" onblur="validaCPF(this.form)"/>
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Nome
					</th>
					<td style="width: 75%">
						<html:text property="procnmcl" size="60" maxlength="50" styleId="cliente"/>
					</td>
				</tr>
				<tr>
					<th>
						Data Nascimento
					</th>
					<td>
						<html:text property="prodnasc" size="11" maxlength="10" onkeyup="criaMascara(this, '##/##/####');"/>
						&nbsp;<b>Sexo</b>&nbsp;
						<html:radio property="procsexo" value="M">Masculino</html:radio>&nbsp;&nbsp;&nbsp;
						<html:radio property="procsexo" value="F">Feminino</html:radio>
					</td>
				</tr>
				<tr>
					<th>
						Telefones
					</th>
					<td>
						<html:text property="procfone" size="14" maxlength="13" onkeyup="criaMascara(this, '(##)####-####');"/>
						<html:text property="procfone2" size="14" maxlength="13" onkeyup="criaMascara(this, '(##)####-####');"/>
					</td>
				</tr>
				<tr>
					<th>
						Logradouro
					</th>
					<td>
						<html:select property="proncgtl">
							<html:optionsCollection name="ls_tipologradouro" value="tlncodg" label="tlcdesc"/>
						</html:select>
						<html:text property="proclogr" size="42" maxlength="50"/>						
					</td>
				</tr>
				<tr>
					<th>
						Estado
					</th>
					<td>
						<html:select property="procufcd" styleId="procufcd" onclick="preencheCidades(this)" onblur="preencheCidades(this)">
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
						<html:select property="proncgcd" style="width: 375px;">
							<html:option value="-1">Escolha o estado</html:option>
						</html:select>
						</div>
					</td>
				</tr>				
				<tr>
					<th>
						Bairro/CEP
					</th>
					<td>
						<html:text property="procbair" size="47" maxlength="35"/>&nbsp;
						<html:text property="proccep" size="10" maxlength="9" onkeyup="criaMascara(this, '#####-###');"/>
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
				<% String d = new com.grupoexata.bancario.utils.FormataObj().formataData(new java.util.Date()); %>
				<tr>
					<th>
						Fase do processo
					</th>
					<td>
						<html:select property="proncgfap" style="width: 290px;">
							<html:optionsCollection name="ls_faseprocesso" value="fapncodg" label="fapcdesc"/>
						</html:select>
						<html:text property="prodfase" styleId="data1" size="11" value="<%= d %>" style="color: #00D; font-weight: bold;" maxlength="10" readonly="true" onkeyup="criaMascara(this, '##/##/####');"/>
						<input type="image" id="b1" src="jscalendar/img.gif">
					</td>
				</tr>
				<tr>
					<th>
						Observações da Fase
					</th>
					<td>
						<html:text property="procdcfap" size="60" maxlength="100"/>
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
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Salvar Processo</html:submit>
					</td>
				</tr>	
			</tbody>			
			</table>
			</html:form>
		
		</fieldset>
			
		</div>
		
	<script type="text/javascript">
	    Calendar.setup({
	        inputField     :    "data1",     // id of the input field
	        ifFormat       :    "%d/%m/%Y",      // format of the input field
	        button         :    "b1",  // trigger for the calendar (button ID)
	        align          :    "BR",           // alignment (defaults to "Bl")
	        singleClick    :    true
	    });	    
	</script>
		<script type="text/javascript">			
			preencheCidades(document.getElementById('procufcd'));
		</script>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>