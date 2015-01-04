<%@include file="/pages/topo.jsp" %>

	<script type="text/javascript" src="dwr/interface/Mapping.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>

		<script type="text/javascript">
		
			function preencheCidades(elem){			
				Mapping.getCidades(retorno,elem.value,'atincgcd');							
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
		<%@include file="/pages/menu.jsp" %>
		<%@include file="/pages/header.jsp" %>
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp; Atividades > Alteração de Atividade &nbsp;
			</legend>
            <html:form action="actionAtividade" onsubmit="return window.confirm('Confirmar a alteração da Atividade com os dados fornecidos?')">
			<html:hidden property="m" value="update"/>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="4" style="text-align: center; background-color: #DDDDDD;">
						Alterar Atividade
					</th>
				</tr>
				<tr>
					<td style="text-align: left; font-weight: bold;" colspan="4">
						Data:
						<html:select property="atiddata">
							<logic:iterate name="ls_data" id="b">
								<option value="<bean:write name="b"/>"><bean:write name="b"/> </option>
							</logic:iterate>
						</html:select> 
						 - 
						Horário: <html:text property="atithora" size="5" maxlength="5" onkeyup="criaMascara(this, '##:##');"/> 
						 - 
						Local: <html:text property="aticloca" size="50" maxlength="60"/>
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="4">
						Dados do Contato
					</th>
				</tr>
				<tr>
					<td style="text-align: left;" colspan="4">
						<b>Contato:</b> 
						<html:text property="aticcont" size="50" maxlength="50"/> 
						<b>Telefone:</b> 
						<html:text property="aticfone" size="13" maxlength="13" onkeyup="criaMascara(this, '(##)####-####');"/>
					</td>
				</tr>
				<tr>
					<th colspan="1" style="width: 15%;">
						UF
					</th>
					<th colspan="3" style="width: 85%;">
						<html:select property="aticufcd" onclick="preencheCidades(this)" onblur="preencheCidades(this)">
							<html:optionsCollection name="ls_estado" value="cdcuf" label="cdcuf"/>
						</html:select>						
					</th>
				</tr>
				<tr>
					<th colspan="1">
						Cidade
					</th>
					<td colspan="3">
						<div id="cidade">
						<html:select property="atincgcd" style="width: 375px;">
							<html:option value="-1">Escolha o estado</html:option>
							<html:optionsCollection name="ls_cidade" value="cdncodg" label="cdcdesc"/>
						</html:select>
						</div>						
					</td>
				</tr>
				<tr>
					<th colspan="1">
						Endereço
					</th>
					<td colspan="3">
						<html:text property="aticendr" size="40" maxlength="40"/>					
					</td>
				</tr>
				<tr>
					<th colspan="1">
						Bairro
					</th>
					<td colspan="3">
						<html:text property="aticbair" size="40" maxlength="40"/>					
					</td>
				</tr>
				<tr>
					<td style="text-align: left; width: 50%;" colspan="2">
						<b>Descrição da Atividade</b><br>
						<html:textarea property="aticdesc" onkeyup="limitaCaractere(this, 290)" rows="3" cols="55"></html:textarea>
					</td>
					<td style="text-align: left; width: 50%;" colspan="2">
						<b>Observações gerais</b><br>
						<html:textarea property="aticobsv" onkeyup="limitaCaractere(this, 290)" rows="3" cols="55"></html:textarea>
					</td>
				</tr>
				<tr>
					<td style="text-align: left;" colspan="4">
						<b>Perspectivas:</b>
						<html:text property="aticpers" size="101" maxlength="120"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: left; width: 50%;" colspan="2">
						<b>Gasto Extra (R$):</b>&nbsp;
						<html:text property="atiygast" size="10" maxlength="10" style="text-align: right;" onkeydown="Formata(this,10,event,2)"/>
					</td>
					<td style="text-align: left; width: 50%;" colspan="2">
						<b>Odômetro do veículo:</b>&nbsp;
						<html:text property="atinodin" size="7" maxlength="7" onkeyup="criaMascara(this, '#######');"/>&nbsp;até&nbsp;<html:text property="atinodfn" size="7" maxlength="7" onkeyup="criaMascara(this, '#######');"/>
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<input type="button" value="Cancelar" class="btn" onclick="window.location = 'actionAtividade.do?m=lista'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Salvar</html:submit>
					</td>					
				</tr>
			</tbody>
			</table>
            </html:form>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>
