<%@include file="/pages/topo.jsp" %>
<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript" src="scripts/loading.js">
criarDivLoading();
</script>
<script type="text/javascript" src="dwr/interface/ItemDescRend.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>
  	<script type="text/javascript">			
			function addRemoverItemDescRend(node, valor){
				var id = node.parentNode.id;
			    var valores = id.split('_');
			    if(valor == '')valor = '_';
			    valor =valores[1] + ';' + valores[2] + ';' + valor + ';'; 
				carregando();
				ItemDescRend.addItemDescRend(valor,"addRemoverItemDescRend(this,'%s');",
				 function(valor){
					DWRUtil.setValue(id,valor);
					carregado();
					}
				);							
			}
	</script>
</head>
<body>
	<div id="loading">
		<img style="cursor: pointer;" title="fechar" onclick="carregado();" alt="" border="0" src="imagens/fecha.png">
	</div>
	<div id="loading-image""> 
		<img alt="Carregando..." src="imagens/load.gif"/>
		<br>
		<br>
		<span>&nbsp;&nbsp;Carregando...</span>
	</div>
	<div id="container">
		<%@include file="/pages/menu.jsp" %>
		<%@include file="/pages/header.jsp" %>
		<a href="#" onclick="carregando();">teste1</a>
		<div id="content">	
		<html:errors/>
		<fieldset id="field">
			<legend>
				&nbsp;<a href="actionDescRend.do?m=lista">Descontos e Rendimentos</a>&nbsp;>&nbsp;<a href="actionDescRend.do?m=opcao&derncodg=<bean:write name="desc_rend" property="derncodg"/>"><bean:write name="desc_rend" property="dercdesc"/></a>&nbsp;> &nbsp;Incidência&nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="6" style="text-align: center; background-color: #DDDDDD;">Incidência</th>
				</tr>
                <tr>
					<th>
						Nome
					</th>
					<th  style="width: 10;">
						Tipo
					</th>
				</tr>
				<logic:empty name="listaitem_desc_rend">
					<th>
						 Não há item_desc_rend Cadastrado.
					</th>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b001" name="listaitem_desc_rend">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
					<bean:write name="b001" property="idrncgtdr.tdrcdesc"/>
					</td>
					<td style="width: 96px;">
					<div style="" id="idr_<bean:write name="desc_rend" property="derncodg"/>_<bean:write name="b001" property="idrncgtdr.tdrncodg"/>">
						<img <logic:notEqual name="b001" property="idrctipo" value="+">style="cursor: pointer;" onclick="addRemoverItemDescRend(this,'+');"</logic:notEqual> title="Positivo" src="imagens/positivo<logic:notEqual name="b001" property="idrctipo" value="+">_</logic:notEqual>.png">
						<img <logic:notEqual name="b001" property="idrctipo" value="-">style="cursor: pointer;" onclick="addRemoverItemDescRend(this,'-');"</logic:notEqual> title="Negativo" src="imagens/negativo<logic:notEqual name="b001" property="idrctipo" value="-">_</logic:notEqual>.png">
						<img <logic:notEqual name="b001" property="idrctipo" value="">style="cursor: pointer;" onclick="addRemoverItemDescRend(this,'');"</logic:notEqual> title="Não inside" src="imagens/ninside<logic:notEqual name="b001" property="idrctipo" value="">_</logic:notEqual>.png">
					</div>
					</td>
				</tr>
				</logic:iterate>
				<tr>					
					<td colspan="6">
						<hr>
						<strong>P</strong> - Positiva | <strong>N</strong> - Negativa | <strong>X</strong> - Não Incide
					</td>
				</tr>				
				<tr>
					<td colspan="6">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionDescRend.do?m=opcao&derncodg=<bean:write name="desc_rend" property="derncodg"/>'">
					</td>
				</tr>
			</tbody>
			</table>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>
