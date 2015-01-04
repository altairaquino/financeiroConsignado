	<%@include file="/pages/topo.jsp" %>
	
	
	 <script type="text/javascript" src="dwr/interface/Questionario.js"></script>
	 <script type="text/javascript" src="dwr/engine.js"></script>
	 <script type="text/javascript" src="dwr/util.js"></script>
  	
	<script type="text/javascript">
			var call_back_onclick = "getQuestaos('%id_div', %ativo, %qstncodg, '%numero', %tab)";
			function getQuestaos(id_div, ativo, qstncodg, numero, tab){
				var ret = function(valor){
					DWRUtil.setValue(id_div, valor);
				};
				if(ativo){
					Questionario.getQuestaos(ret,qstncodg, numero, tab,
						call_back_onclick);
				}else{
					ret('');
				}
			}
			function getQuestionario(qtnncodg){
				var ret = function(valor){
					DWRUtil.setValue("questionario", valor);
				};
				Questionario.getQuestionario(ret, qtnncodg, 
					call_back_onclick);
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
			<legend class="red">
				&nbsp; Questionário da auditoria > Contrato
				<a onclick="window.open('actionContrato.do?m=dadosWindow&ctncodg=<bean:write name="contrato" property="ctncodg"/>', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-400)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-570)/2:0) + ',height = 400, width = 570');">
					[<bean:write name="contrato" property="ctnnumr"/>]
				</a> - 
				<a onclick="window.open('actionEntidade.do?m=dadosEntidadeWindow&enncodg=<bean:write name="contrato" property="ctnc2en"/>', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-430)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-570)/2:0) + ',height = 430, width = 570');">
					[<bean:write name="contrato" property="ctcnmcl"/>]					
				</a>
			</legend>
			<html:form action="actionContratoquestao">
			<html:hidden property="m" value="inserir"/>
			<input type="hidden" name="ctqncgen" value="<bean:write name="usuario" property="enncodg"/>">
			<input type="hidden" name="ctqncgct" value="<bean:write name="contrato" property="ctncodg"/>"/>
			<html:hidden property="ctqncgqtn" value="1"/>
			<div id="questionario" style="z-index: -3;"></div>
			<script type="text/javascript">			
				getQuestionario(1);
			</script>
			
			<hr>
			<input type="button" value="Cancelar" class="btn" onclick="window.location = 'actionContrato.do?m=listaAuditoria'">
			<input type="submit" class="btn_hot" value="Responder">
			</html:form>
		</fieldset>
		</div>
		 <%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>
