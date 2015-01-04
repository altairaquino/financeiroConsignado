<%@include file="/pages/topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
	
	<script type="text/javascript">
			
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
					var movnfili = opener.document.getElementById('movnfili');
					var movcfili = opener.document.getElementById('movcfili');
					if(movnfili && movcfili)
					{	
						movnfili.value = ncodg;
						movcfili.value = desc;
					}
				}
				window.close();
			}
						
	</script>
	
	</style>
</head>
<% request.setAttribute("ls_filial", ModelFilial.getInstance().getFiliais()); %>
<body style="margin: 0px; overflow: hidden;">
	
	<div id="container" style="width: 500px;">
		<%--include file="/pages/menu.jsp" --%>
		<%@include file="/pages/header.jsp" %>
		<div id="content" >
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;Pesquisa Filial&nbsp;
			</legend>
			<html:form method="action" action="actionFilial" focus="flcnome">
			<html:hidden property="m" value="pesquisaFilialWindow"/>
			<table style="width: 100%">
			<tbody>
				<tr>
			    	<td colspan="4">
			    		Use, no mínimo, 3 caracteres para a pesquisa.
					</td>
				</tr>
                <tr>
                    <th colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						Nome, Cidade <html:text property="flcnome" size="40" maxlength="40"></html:text>&nbsp;
						<html:submit styleClass="btn_hot">Pesquisar</html:submit>
					</th>
				</tr>                		
			</tbody>
			</table>
			</html:form>
			<logic:present name="ls_filial">
			<div style="width: 100%; height: 200px; overflow: auto;">
			<table style="width: 100%">
				<tbody>
					<tr>
						<th>
							Código
						</th>
						<th>
							Nome
						</th>
						<th>
							Cidade
						</th>
					</tr>
					<logic:iterate name="ls_filial" id="b">
					<tr>
						<td>
							<bean:write name="b" property="flncodg"/>
						</td>
						<td>
							<a href="javascript:sair('<bean:write name="b" property="flncodg"/>','<bean:write name="b" property="flcnome"/>');">
								<bean:write name="b" property="flcnome"/>
							</a>						
						</td>
						<td>
							<bean:write name="b" property="flcdccd"/>
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

<%@page import="com.grupoexata.bancario.dao.ModelFilial"%></html>