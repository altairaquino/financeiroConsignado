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
				&nbsp;Pesquisa item_desc_rend&nbsp;
			</legend>
            <html:form action="actionItemDescRend">
			<input type="hidden" name="m" value="pesquisa">
			<table style="width: 550px;">
			<tbody>
                <tr>
                    <td colspan="4" style="text-align: right;">
						<input type="text" name="param" value="">
					</td>
				</tr>
				<tr>
                    <td colspan="4" style="text-align: right;">
						<html:submit styleClass="btn_hot">Pesquisar</html:submit>
					</td>
				</tr>
                				<logic:present name="listaPesqitem_desc_rend">
					<th>
						Resultado.
					</th>
				<tr>
					<th>
						IDRNCODG
					</th>
					<th>
						IDRNCGDER
					</th>
					<th>
						IDRNCGTDR
					</th>
					<th>
						IDRCTIPO
					</th>
				</tr>
				<logic:empty name="listaPesqitem_desc_rend">
					<th>
						 Não há item_desc_rend Cadastrado.
					</th>
				</logic:empty>
				<logic:notEmpty name="listaPesqitem_desc_rend">
				<% int c = 0; %>
				<logic:iterate id="b001" name="listaPesqitem_desc_rend">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
					<a href="actionItemDescRend.do?m=editar&idrncodg=<bean:write name="item_desc_rend" property="idrncodg"/>">
						<bean:write name="b001" property="idrncodg"/>
					</a>
					</td>
					<td>
						<bean:write name="b001" property="idrncgder"/>
					</td>
					<td>
						<bean:write name="b001" property="idrncgtdr"/>
					</td>
					<td>
						<bean:write name="b001" property="idrctipo"/>
					</td>
				</tr>
				</logic:iterate>
				</logic:notEmpty>
				</logic:present>

				<tr>
					<td colspan="4">
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