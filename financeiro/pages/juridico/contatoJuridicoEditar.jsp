	<%@include file="../topo.jsp" %>
	
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
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Contato do Jurídico > Alteração    &nbsp;
			</legend>
			<html:form action="actionEntidade" focus="encnome" onsubmit="return window.confirm('Confirmar a alteração do Contato com os dados fornecidos?')">			
			<html:hidden property="m" value="updateContatoJuridico"/>
			<html:hidden property="enncodg"/>
			<table style="width: 550px;" align="center">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> 
						Alteração de Contato do Jurídico
					</th>
				</tr>
				<tr>
					<td>
						Nome
					</td>
					<td>
						<html:text property="encnome" size="60" maxlength="50"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						C.P.F.
					</td>
					<td>
						<html:text property="encdocm" size="15" maxlength="14" onkeyup="criaMascara(this, '###.###.###-##');"></html:text>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;"> 
						Contato e E-mail 
					</th>
				</tr>
				<tr>
					<td>
						Fone Fixo
					</td>
					<td>
						<html:text property="encfone" size="14" maxlength="13" onkeyup="criaMascara(this, '(##)####-####');"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Celular
					</td>
					<td>
						<html:text property="enccell" size="14" maxlength="13" onkeyup="criaMascara(this, '(##)####-####');"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						E-mail
					</td>
					<td>
						<html:text property="encmail" size="60" maxlength="50"></html:text>
					</td>
				</tr>				
				<tr>
					<th valign="top" colspan="2" style="text-align: center;background-color: #DDDDDD;"> 
						Observações Gerais 
					</th>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<html:textarea property="enmobs" cols="70" rows="3">						
						</html:textarea>						
					</td>
				</tr>		
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'actionEntidade.do?m=opcoesContatoJuridico&enncodg=<bean:write name="formEntidade" property="enncodg"/>'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Salvar Contato</html:submit>
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