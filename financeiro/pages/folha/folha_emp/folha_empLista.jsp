<%@include file="/pages/topo.jsp" %>
<script type="text/javascript" src="scripts/jquery.js"></script>
	<script type="text/javascript" src="dwr/interface/FolhaEmpItem.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>
  	
	<script type="text/javascript">
			function getFolhaEmpItems(feincgfoe){
				FolhaEmpItem.getFolhaEmpItems(retorno,feincgfoe,'');							
			}
			function retorno(valor){
				DWRUtil.setValue('resultado',valor);
			}
	</script>
</head>
<body>
	<div id="container">
		<%@include file="/pages/menu.jsp" %>
		<%@include file="/pages/header.jsp" %>
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend>
			<a href="actionFolha.do?m=lista">Folha</a>&nbsp;>&nbsp;
			<a href="actionFolha.do?m=opcao&folncodg=<bean:write name="folha" property="folncodg"/>">
			<bean:write name="folha" property="foldmes"/>&nbsp;|&nbsp;
				Nº <bean:write name="folha" property="folnnum"/>&nbsp;|&nbsp;
				<bean:write name="folha" property="folcdesc"/>&nbsp;|&nbsp;
				<bean:write name="folha" property="epcnome"/>
			</a>&nbsp;>&nbsp;
			Funcionários&nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="4" style="text-align: center; background-color: #DDDDDD;">Funcionários</th>
				</tr>
				<tr>
					<td colspan="4">
						<input type="button" class="btn_hot" value="Novo" onclick="window.location = '#'">
					</td>
				</tr>
				</tbody>
			</table>
			<div style="overflow: auto; height: 300px; border: solid 1px #DDD;">
			<table style="width: 100%;">
			<tbody>
                <tr>
					<%-- 
					<th>
						FOENCODG
					</th>
					<th>
						FOENCGEMP
					</th>
					--%>
					<th>
						Nome
					</th>
					<th style="text-align: right;">
						Sal. Líquido
					</th>
				</tr>
				<logic:empty name="listafolha_emp">
					<th>
						 Não há folha_emp Cadastrado.
					</th>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b001" name="listafolha_emp">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<%-- 
					<td>
						<bean:write name="b001" property="foencodg"/>
					</td>
					<td>
						<bean:write name="b001" property="foencgemp"/>
					</td>
					--%>
					<td>
					<a href="#resultado" onclick="getFolhaEmpItems('<bean:write name="b001" property="foencodg"/>');">
						<bean:write name="b001" property="encnome"/>
					</a>
					</td>
					<th style="text-align: right;color: #006699; padding-right: 10px;">
						<bean:write name="b001" property="foeyliqd"/>
					</th>
				</tr>
				</logic:iterate>
			</tbody>
			</table>
			</div>
			<div id="resultado" style="width: 100%; margin-top: 10px;">&nbsp;</div>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionFolha.do?m=opcao&folncodg=<bean:write name="folha" property="folncodg"/>'">
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
