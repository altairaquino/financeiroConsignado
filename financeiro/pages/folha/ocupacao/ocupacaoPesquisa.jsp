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
			function sair(ncodg,desc,ccodg){
				if(opener){
					var ocpncodg = opener.document.getElementById('ocpncodg');
					var ocpcdesc = opener.document.getElementById('ocpcdesc');
					var ocpccodg = opener.document.getElementById('ocpccodg');
					if(ocpncodg && ocpcdesc)
					{	
						ocpncodg.value = ncodg;
						ocpcdesc.value = desc;
						ocpccodg.value = ccodg;
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
				&nbsp;Pesquisa ocupacao&nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
			    	<td colspan="5">
			    		Ocupação/Família/Sinônimo
					</td>
				</tr>
                <tr>
                    <td colspan="5" style="text-align: right;">
                    <table style="width: 100%;">
                    	<tr>
                    		<td>
                    			<input type="text" id="param" name="param" value="" style="width: 100%;">
                    		</td>
                    		<td>
                    			<input type="button" id="btnPesq" class="btn_hot" value="Pesquisar" onclick="pesquisarOcupacao();">
                    		</td>
                    	</tr>
                    </table>						
					</td>
				</tr>
                <tr>
					<td colspan="5">
						<div id="resultado" style="height: 180px;width: 100%; overflow: auto;"></div>
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