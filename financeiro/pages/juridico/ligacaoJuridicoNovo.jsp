	<%@include file="../topo.jsp" %>
	
	<script type="text/javascript" src="dwr/interface/Mapping.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>
  	
  	<script type="text/javascript">

			function preencheRegistros(){
				e = document.getElementById('enncodg');
				if (e.value != ''){
					Mapping.getLigacoesDoContato(retorno, e.value);
				}
			}
			
			function retorno(valor){
				DWRUtil.setValue("registros",valor);
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
				&nbsp;   Registro de Liga��o para Contatos do Jur�dico  &nbsp;
			</legend>
			<% String d = new com.grupoexata.bancario.utils.FormataObj().formataData(new java.util.Date()); %>
			<table style="width: 600px" align="center">
				<tr>
					<th style="width: 30%;">
						Enviar e-mail de liga��es
					</th>
					<td style="width: 70%;">
						<html:form action="actionLigacaoJuridico">
							<html:hidden property="m" value="enviaEmail"/>
							<html:text property="lgjdprox" styleId="data2" value="<%= d %>" size="11" style="color: #00D; font-weight: bold;" maxlength="10" readonly="true" onkeyup="criaMascara(this, '##/##/####');"/>&nbsp;&nbsp;
							<input type="image" id="b2" src="jscalendar/img.gif">&nbsp;&nbsp;
							<html:submit styleClass="btn_hot" value="Enviar"></html:submit>
						</html:form>
					</td>
				</tr>
			</table>
			<html:form action="actionLigacaoJuridico" method="post" onsubmit="return window.confirm('Confirmar o registro da liga��o com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>
			<input type="hidden" name="lgjncgus" value="<bean:write name="usuario" property="enncodg"/>">
			<input type="hidden" name="lgjcnmus" value="<bean:write name="usuario" property="encnome"/>">
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDD;">
						Registro de Liga��o
					</th>
				</tr>
				<tr>
					<th style="width: 30%;">
						Contato
					</th>
					<td style="width: 70%;">
						<html:hidden property="lgjncgct" styleId="enncodg"/>
						<html:text property="lgjcnmct" styleId="encnome" size="50" maxlength="50" readonly="true" style="font-weight: bold; color: #D00;"/>
						<img src="imagens/pesquisa.jpg" width="22" height="22" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('contatoJuridicoPesquisaWindow.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');"/>
					</td>
				</tr>
				<tr>
					<th>
						Pr�ximo Agendamento
					</th>
					<td>
						<html:text property="lgjdprox" styleId="data1" size="11" style="color: #00D; font-weight: bold;" maxlength="10" readonly="true" onkeyup="criaMascara(this, '##/##/####');"/>
						<input type="image" id="b1" src="jscalendar/img.gif">
						<img src="imagens/help.gif" height="15" width="15" border="0" onmouseover="alert('Caso a data n�o seja informada,\no sistema remarca para 30 dias a pr�xima liga��o.')">
					</td>
				</tr>
				<tr>
					<th>
						E-mail para Ger�ncia?
					</th>
					<td>
						<html:radio property="lgjlmail" value="T">Sim</html:radio>&nbsp;&nbsp;
						<html:radio property="lgjlmail" value="F">N�o</html:radio>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDD;">
						�ltimas liga��es
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<div id="registros" style="text-align: center;">
							<img src="imagens/mais.gif" style="cursor: pointer;" onclick="if (document.getElementById('enncodg').value != ''){preencheRegistros();}else{alert('Escolha o contato!')}">
						</div>
					</td>
				</tr>
				<tr style="background-color: #DDD;">
					<th colspan="2" style="text-align: center;">
						Observa��es Sobre a Liga��o (Ocorr�ncias)
					</th>					
				</tr>
				<tr>
					<td colspan="2"> 
						<html:textarea property="lgjmobsv" onfocus="preencheRegistros();" cols="60" rows="6" style="width: 100%;" value="FALEI COM:
INFORME AS OCORR�NCIAS:">							
						</html:textarea>
					</td>		
				</tr>		
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'home.do'">
					</td>
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Registrar Liga��o</html:submit>
					</td>
				</tr>			
			
			</tbody>
			</table>
			</html:form>
		</fieldset>
			
		</div>
		<logic:present name="msg">
			<input type="hidden" value="<bean:write name="msg"/>" name="msg" id="msg">
			<script>
				var x = document.getElementById('msg').value;
				alert(x);
			</script>
		</logic:present>
	<script type="text/javascript">
	    Calendar.setup({
	        inputField     :    "data1",     // id of the input field
	        ifFormat       :    "%d/%m/%Y",      // format of the input field
	        button         :    "b1",  // trigger for the calendar (button ID)
	        align          :    "BR",           // alignment (defaults to "Bl")
	        singleClick    :    true
	    });	    
	    Calendar.setup({
	        inputField     :    "data2",     // id of the input field
	        ifFormat       :    "%d/%m/%Y",      // format of the input field
	        button         :    "b2",  // trigger for the calendar (button ID)
	        align          :    "BR",           // alignment (defaults to "Bl")
	        singleClick    :    true
	    });	    
	</script>
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>