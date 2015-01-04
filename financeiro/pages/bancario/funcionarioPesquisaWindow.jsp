<%@include file="/pages/topo.jsp" %>
	
	<script type="text/javascript">
						
			function sair(ncodg,desc){
				if(opener){
					var empncodg = opener.document.getElementById('empncodg');
					var empcdesc = opener.document.getElementById('empcdesc');
					if(empncodg && empcdesc)
					{	
						empncodg.value = ncodg;
						empcdesc.value = desc;
					}
				}
				window.close();
			}
						
	</script>
	
</head>
<body style="margin: 0px; overflow: hidden;">
		
	<div id="container" style="width: 500px;">
		<%--include file="/pages/menu.jsp" --%>
		<%@include file="/pages/header.jsp" %>
		<div id="content" >
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;Pesquisa Funcionario&nbsp;
			</legend>
			<html:form method="action" action="actionEntidade" focus="encnome">
			<html:hidden property="m" value="pesquisaFuncionarioWindow"/>
			<table style="width: 100%;">
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