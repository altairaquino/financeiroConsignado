	
	<%@include file="/pages/topo.jsp" %>
	<script type="text/javascript" src="dwr/interface/CentroCusto.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>
  	<script type="text/javascript">
			function getCentroCustos(){
				//carregando();
				CentroCusto.getCentroCustos(retorno,<bean:write name="funcionario" property="empncodg"/>,"getAddCentroCusto(%s);");							
			}
			function getAddCentroCusto(crncodg){
				//carregando();
				DWREngine.beginBatch();
				CentroCusto.addCentroCusto(<bean:write name="funcionario" property="empncodg"/>,crncodg);
				getCentroCustos();
				DWREngine.endBatch();							
			}
			
			function retorno(valor){
				//carregado();
				DWRUtil.setValue('resultado',valor);
			}
			
			function carregando(){
				var l = document.getElementById('loading');
				var li = document.getElementById('loading-image');
				l.style.display="block";
				li.style.display="block";
			}
			function carregado(){
				var l = document.getElementById('loading');
				var li = document.getElementById('loading-image');
				l.style.display="none";
				li.style.display="none";
			}
			function init(){
				DWRUtil.useLoadingMessage("Carregando Aguarde!!");
			}
	</script>
				
</head>
<body>
	
	<div id="container">
		<%@include file="/pages/menu.jsp" %>
		<%@include file="/pages/header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset style="text-align: center;">
			<legend class="red">
				&nbsp;   Lista de centro de custo do empregado > <bean:write name="empregado" property="encnome"/>  &nbsp;
			</legend>
			<table style="width: 650px;">
			<tbody>
				<tr>
					<td colspan="6">&nbsp;</td>
				</tr>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="6">
						Lista de centro de custo do empregado
					</th>
				</tr>				
				<tr style="text-align: center;">
					<td colspan="2">
						<div id="resultado"></div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<input type="button" class="btn" value="Voltar" onclick="window.location = 'actionEmpregadoCentrocusto.do?m=lista&empncodg=<bean:write name="funcionario" property="empncodg"/>'">
					</td>
				</tr>			
			
			</tbody>			
			</table>	
		</fieldset>
			
		</div>
		<script type="text/javascript">
			getCentroCustos();
		</script>
		
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>