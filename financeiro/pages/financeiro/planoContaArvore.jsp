	<%@include file="../topo.jsp" %>
	<link rel='StyleSheet' href='dtree/dtree.css' type='text/css'/>
	<script type="text/javascript" src="dtree/dtree.js"></script>
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend class="red">
				&nbsp;   Plano de Contas (Árvore)  &nbsp;
			</legend>
			<table style="width: 600px;">
			<tbody>
				<tr>				
					<td>
						<h2>Árvore de Plano de Contas</h2>

						<div class='dtree'>
						
							<p><a href='javascript: d.openAll();'>Abrir Tudo</a> | <a href='javascript: d.closeAll();'>Fechar Tudo</a></p>
						
							
							<script type='text/javascript'>
								<!--
									<%= ModelPlanoConta.getInstance().arvoreDoPlano() %>
								//-->
							</script>
						
						</div>
						
					</td>
				</tr>
				
			</tbody>			
			</table>	
		
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>

<%@page import="com.grupoexata.financeiro.dao.ModelPlanoConta"%></html>