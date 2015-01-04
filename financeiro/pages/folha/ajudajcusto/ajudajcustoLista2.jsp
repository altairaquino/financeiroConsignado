<%@include file="/pages/topo.jsp" %>
<%@ page import="com.grupoexata.folha.dao.ModelEmpregado" %>
<%@page import="com.grupoexata.folha.dao.ModelTipoajcust"%>
<script type="text/javascript" src="scripts/jquery.js"></script>
</head>
<body>
	<div id="container">
		<%@include file="/pages/menu.jsp" %>
		<%@include file="/pages/header.jsp" %>
		<%
		request.setAttribute("listaempregado", new ModelEmpregado().listaEntidadeEmpregado());
		request.setAttribute("listatipoajcust", new ModelTipoajcust().list());
		%>
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp; Relação de empregados&nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="4" style="text-align: center; background-color: #DDDDDD;">  Relação de empregados </th>
				</tr>
				<tr>
					<td colspan="4">
						<input type="button" class="btn_hot" value="Novo" onclick="window.location = 'actionEntidade.do?m=novoFuncionario'">
						<input type="button" class="btn_hot" value="Pesquisa" onclick="window.location = 'empregadoPesquisa.do'">
					</td>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%;" cellpadding="0" cellspacing="0" border="0">
			<tbody>
                <tr>
					<th>
						Nome
					</th>
					<th>
						Valor
					</th>	
					<th>
						Desconto
					</th>
					<th>
						Acréscimo
					</th>
					<th>
						Total
					</th>
					<th>&nbsp;
					</th>
				</tr>
				<logic:empty name="listaempregado">
					<th>
						 Não há empregados Cadastrados.
					</th>
				</logic:empty>
				<logic:iterate id="b001" name="listaempregado">
				<tr style="background-color: #DDD">
					<td colspan="6">
						<bean:write name="b001" property="encnome"/>
					</td>
						
					<logic:iterate id="a001" name="listatipoajcust" indexId="d">
					<tr style="background-color: <%=(d++%2==0?"#f0f0f0":"transparent") %>">
					<td>
						<bean:write name="a001" property="tacncodg"/>-
						<bean:write name="a001" property="taccdesc"/>
					</td>
					<td>
						<input name="xxx" value="R$ 145,26" disabled="disabled" maxlength="10" size="10" style="text-align: right;width: 100%;height: 100%;">
					</td>
					<td>
						<input name="xxx" value="R$ -0,26" maxlength="10" size="10" style="text-align: right;width: 100%;height: 100%;">
					</td>
					<td>
						<input name="xxx" value="R$ 0,26" maxlength="10" size="10" style="text-align: right;width: 100%;height: 100%;">
					</td>					
					<td>
						<input name="xxx" value="R$ 0,26" maxlength="10" size="10" style="text-align: right;width: 100%;height: 100%;">
					</td>
					<td>&nbsp;
					</td>
					</tr>
						</logic:iterate>
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