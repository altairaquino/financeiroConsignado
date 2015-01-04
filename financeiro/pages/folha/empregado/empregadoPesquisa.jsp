<%@include file="/pages/topo.jsp" %>
<script type="text/javascript" src="scripts/jquery.js"></script>
</head>
<body>
	<div id="container">
		<%@include file="/pages/menu.jsp" %>
		<%@include file="/pages/header.jsp" %>
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;Pesquisa Empregado&nbsp;
			</legend>
            <html:form action="actionEmpregado">
			<input type="hidden" name="m" value="pesquisa">
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="4">Use, no mínimo, 4 caracteres para a pesquisa.</th>
				</tr>
                <tr>
                    <td style="width: 60%;">
						Nome: <input type="text" size="60" name="param" value="<logic:present name="param"><bean:write name="param"/></logic:present>">
					</td>
                    <td style="width: 40%;">
						<html:submit styleClass="btn_hot">Pesquisar</html:submit>
					</td>
				</tr>
                <logic:present name="listaPesqempregado">
                <tr>
                	<td colspan="2">	
					<table style="width: 100%;">
					<tbody>
						<tr>
							<th colspan="4" style="text-align: center; background-color: #DDDDDD;">  Resultado </th>
						</tr>
						<tr>
							<th>
								Código				
							</th>
							<th>
								CPF
							</th>
							<th>
								Login
							</th>
							<th>
								Nome
							</th>							
						</tr>
						<logic:empty name="listaPesqempregado">
						<tr style="background-color: #ddd;">
							<th colspan="4" style="text-align: center;">
								 consulta sem resultados para "<logic:present name="param"><bean:write name="param"/></logic:present>"
							</th>
						</tr>
						</logic:empty>
						<% int c = 0; %>
						<logic:iterate id="b001" name="listaPesqempregado">
						<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
							<td>
								<bean:write name="b001" property="enncodg"/>
							</td>
							<td>
								<bean:write name="b001" property="encdocm"/>
							</td>
							<td>
								<bean:write name="b001" property="enclogn"/>
							</td>
							<td>
								<a href="actionEntidade.do?m=opcoesFuncionario&enncodg=<bean:write name="b001" property="enncodg"/>">
								<bean:write name="b001" property="encnome"/>
								</a>
							</td>
						</tr>
						</logic:iterate>					
						
					</tbody>
					</table>
					</td>
					</tr>
				</logic:present>
			</tbody>
			</table>
        </html:form>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>