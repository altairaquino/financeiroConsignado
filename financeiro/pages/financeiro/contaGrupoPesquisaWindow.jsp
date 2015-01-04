<%@include file="/pages/topo.jsp" %>

<%  
    String grupo = request.getParameter("gpcncodg");
    
    if (grupo != null){
    	request.setAttribute("ls_contagrupo", ModelContaGrupo.getInstance().getContasDoGrupoAtivas(Integer.parseInt(grupo)));    	
    }

%>
	
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
			
			<logic:present name="ls_contagrupo">
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
							Grupo
						</th>
					</tr>
					<logic:iterate name="ls_contagrupo" id="b">
					<tr>
						<td>
							<bean:write name="b" property="cogncodg"/>
						</td>
						<td>
							<a href="javascript:sair('<bean:write name="b" property="cogncodg"/>','<bean:write name="b" property="cogcdesc"/>');">
								<bean:write name="b" property="cogcdesc"/>
							</a>					
						</td>
						<td>
							<bean:write name="b" property="cogcdcgpc"/>
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

<%@page import="com.grupoexata.financeiro.dao.ModelContaGrupo"%>
</html>