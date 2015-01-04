<%@include file="/pages/topo.jsp" %>
	
	<script type="text/javascript">
			
			function sair(ncodg,desc){
				if(opener){
					var movncont = opener.document.getElementById('movncont');
					var movccont = opener.document.getElementById('movccont');
					if(movncont && movccont)
					{	
						movncont.value = ncodg;
						movccont.value = desc;
					}
				}
				window.close();
			}
						
	</script>
	
</head>
<body style="margin: 0px; overflow: hidden;">
	
	<div id="container" style="width: 500px;">
		
		<%@include file="/pages/header.jsp" %>
		<div id="content" >
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp; Contas &nbsp;
			</legend>
			
			<logic:present name="ls_planoconta">
			<div style="width: 100%; height: 200px; overflow: auto;">
			<table style="width: 100%">
				<tbody>
					<tr>
						<th>
							Código
						</th>
						<th>
							Descrição
						</th>
						<th>
							Tipo
						</th>
					</tr>
					<logic:iterate name="ls_planoconta" id="b">
					<tr>
						<td>
							<bean:write name="b" property="plcncodg"/>
						</td>
						<td>
							<a href="javascript:sair('<bean:write name="b" property="plcncodg"/>','<bean:write name="b" property="plccdesc"/>');">
								<bean:write name="b" property="plccdesc"/>
							</a>					
						</td>
						<td>
							<logic:equal name="b" property="plcntipo" value="2">
								Saída
							</logic:equal>
							<logic:equal name="b" property="plcntipo" value="1">
								Entrada
							</logic:equal>
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