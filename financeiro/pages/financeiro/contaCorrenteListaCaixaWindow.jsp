<%@include file="/pages/topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
	
	<script type="text/javascript">
			
			function sair(ncodg,desc){
				if(opener){
					var movncoco = opener.document.getElementById('movncoco');
					var movccoco = opener.document.getElementById('movccoco');
					if(movncoco && movccoco)
					{	
						movncoco.value = ncodg;
						movccoco.value = desc;
					}
				}
				window.close();
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
	
	<div id="container" style="width: 500px;">
		<%--include file="/pages/menu.jsp" --%>
		<%@include file="/pages/header.jsp" %>
		<div id="content" >
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;Relação de Contas Correntes&nbsp;
			</legend>
			<div style="width: 100%; height: 200px; overflow: auto;">
			<table style="width: 100%">
				<tbody>
					<tr style="background-color: #DDD;">
						<th>
							Código
						</th>
						<th>
							Conta
						</th>
					</tr>
					<logic:empty name="ls_contacorrente">
					<tr>
						<td colspan="2" style="color: red;">
							Não há conta corrente cadastrada.
						</td>
					</tr>
					</logic:empty>
					<logic:iterate name="ls_contacorrente" id="b">
					<tr>
						<td>
							<bean:write name="b" property="concodg"/>
						</td>
						<td>
							<a href="javascript:sair('<bean:write name="b" property="concodg"/>','<bean:write name="b" property="cocdesc"/>');">
								<bean:write name="b" property="cocdesc"/>
							</a>					
						</td>						
					</tr>
					</logic:iterate>
				</tbody>
			</table>
			</div>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>