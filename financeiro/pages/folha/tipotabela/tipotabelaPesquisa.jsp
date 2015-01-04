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
				&nbsp;Pesquisa tipotabela&nbsp;
			</legend>
            <html:form action="actionTipotabela">
			<input type="hidden" name="m" value="pesquisa">
			<table style="width: 550px;">
			<tbody>
                <tr>
                    <td colspan="3" style="text-align: right;">
						<input type="text" name="param" value="">
					</td>
				</tr>
				<tr>
                    <td colspan="3" style="text-align: right;">
						<html:submit styleClass="btn_hot">Pesquisar</html:submit>
					</td>
				</tr>
                				<logic:present name="listaPesqtipotabela">
					<th>
						Resultado.
					</th>
				<tr>
					<th>
						TTBNCODG
					</th>
					<th>
						TTBCDESC
					</th>
					<th>
						TTBCSIGL
					</th>
				</tr>
				<logic:empty name="listaPesqtipotabela">
					<th>
						 Não há tipotabela Cadastrado.
					</th>
				</logic:empty>
				<logic:notEmpty name="listaPesqtipotabela">
				<% int c = 0; %>
				<logic:iterate id="b001" name="listaPesqtipotabela">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
					<a href="actionTipotabela.do?m=editar&ttbncodg=<bean:write name="tipotabela" property="ttbncodg"/>">
						<bean:write name="b001" property="ttbncodg"/>
					</a>
					</td>
					<td>
						<bean:write name="b001" property="ttbcdesc"/>
					</td>
					<td>
						<bean:write name="b001" property="ttbcsigl"/>
					</td>
				</tr>
				</logic:iterate>
				</logic:notEmpty>
				</logic:present>

				<tr>
					<td colspan="3">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = '#adefinir'">
					</td>
				</tr>
			</tbody>
			</table>
        </html:form>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>