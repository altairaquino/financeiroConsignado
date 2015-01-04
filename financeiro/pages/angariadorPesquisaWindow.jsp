<%@include file="/pages/topo.jsp" %>
<script type="text/javascript" src="scripts/jquery.js"></script>
	<script type="text/javascript" src="dwr/interface/Ocupacao.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>
  	
	<script type="text/javascript">
			function pesquisarOcupacao(){
				carregando();
				Ocupacao.getFamilia(retorno,DWRUtil.getValue('param'),"getFamilia('%s');");						
			}
			
			function retorno(valor){
				carregado();
				DWRUtil.setValue('resultado',valor);
			}
			function getFamilia(valor){
				carregando();
				Ocupacao.getOcupacoesFamilia(retorno,valor,'sair(%s,%s,%s);');
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
			function sair(ncodg,desc){
				if(opener){
					var ctncgen = opener.document.getElementById('ctncgen');
					var ctcnman = opener.document.getElementById('ctcnman');
					if(ctncgen && ctcnman)
					{	
						ctncgen.value = ncodg;
						ctcnman.value = desc;
					}
				}
				window.close();
			}
			
			function init(){
				DWRUtil.useLoadingMessage("Carregando Aguarde!!");
			}
	</script>
	<style type="text/css">
		#loading{
			opacity: 0.65;
			-moz-opacity: 0.65;
			filter: alpha(opacity=65);
			top:0px;
			left: 0px;
			width: 550px;
			height: 450px;  
			position: absolute; 
			display:none;
			background-position:center;
			background-repeat:no-repeat;
			/*background-color: #4B4B4B;*/
			background-color: #ccc;
			z-index: 1;
		}
		#loading-image{
			top:198px;
			left: 198px;
			position: absolute; 
			display:none;
			background-position:center;
			background-repeat:no-repeat;
			z-index: 2;
			padding: 10px 15px;
			background-color: white;
			text-align: center;
			border: 1px solid black;
			-moz-border-radius-topleft: 7px;
			-moz-border-radius-topright: 7px;
			-moz-border-radius-bottomleft: 7px;
			-moz-border-radius-bottomright: 7px;
		}
	</style>
</head>
<body style="margin: 0px; overflow: hidden;">
	<div id="loading">
	</div>
	<div id="loading-image""> 
		<img alt="Carregando..." src="imagens/load.gif"/>
		<br>
		<br>
		<span>&nbsp;&nbsp;Carregando...</span>
		
	</div>
	<div id="container" style="width: 500px;">
		<%--include file="/pages/menu.jsp" --%>
		<%@include file="/pages/header.jsp" %>
		<div id="content" >
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;Pesquisa Agente de Crédito&nbsp;
			</legend>
			<html:form method="action" action="actionEntidade" focus="encnome">
			<html:hidden property="m" value="pesquisaAngariadorContratoWindow"/>
			<table  style="width: 100%">
			<tbody>
				<tr>
			    	<td colspan="4">
			    		Use, no mínimo, 3 caracteres para a pesquisa.
					</td>
				</tr>
                <tr>
                    <th colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						Nome, CPF &nbsp;<html:text property="encnome" size="40" maxlength="40"></html:text>&nbsp;
						<html:submit styleClass="btn_hot">Pesquisar</html:submit>
					</th>
				</tr>                		
			</tbody>
			</table>
			</html:form>
			<logic:present name="ls_entidade">
			<div style="width: 100%; height: 200px; overflow: auto;">
			<table style="width: 100%;">
				<tbody>
					<tr>
						<th>
							Código
						</th>
						<th>
							Nome
						</th>
						<th>
							C.P.F.
						</th>
					</tr>
					<logic:iterate name="ls_entidade" id="b">
					<tr>
						<td>
							<bean:write name="b" property="enncodg"/>
						</td>
						<td>
							<a href="javascript:sair('<bean:write name="b" property="enncodg"/>','<bean:write name="b" property="encnome"/>');">
								<bean:write name="b" property="encnome"/>
							</a>						
						</td>
						<td>
							<bean:write name="b" property="encdocm"/>
						</td>
					</tr>
					</logic:iterate>
				</tbody>
			</table>
			</div>
			</logic:present>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>