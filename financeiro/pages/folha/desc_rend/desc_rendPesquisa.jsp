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
				&nbsp;Pesquisa desc_rend&nbsp;
			</legend>
            <html:form action="actionDescRend">
			<input type="hidden" name="m" value="pesquisa">
			<table style="width: 550px;">
			<tbody>
                <tr>
                    <td colspan="7" style="text-align: right;">
						<input type="text" name="param" value="">
					</td>
				</tr>
				<tr>
                    <td colspan="7" style="text-align: right;">
						<html:submit styleClass="btn_hot">Pesquisar</html:submit>
					</td>
				</tr>
                				<logic:present name="listaPesqdesc_rend">
					<th>
						Resultado.
					</th>
				<tr>
					<th>
						DERNCODG
					</th>
					<th>
						DERCCODG
					</th>
					<th>
						DERCDESC
					</th>
					<th>
						DERCTIPO
					</th>
					<th>
						DERLLIQD
					</th>
					<th>
						DERNMULT
					</th>
					<th>
						DERCFORM
					</th>
				</tr>
				<logic:empty name="listaPesqdesc_rend">
					<th>
						 Não há desc_rend Cadastrado.
					</th>
				</logic:empty>
				<logic:notEmpty name="listaPesqdesc_rend">
				<% int c = 0; %>
				<logic:iterate id="b001" name="listaPesqdesc_rend">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
					<a href="actionDescRend.do?m=editar&derncodg=<bean:write name="desc_rend" property="derncodg"/>">
						<bean:write name="b001" property="derncodg"/>
					</a>
					</td>
					<td>
						<bean:write name="b001" property="derccodg"/>
					</td>
					<td>
						<bean:write name="b001" property="dercdesc"/>
					</td>
					<td>
						<bean:write name="b001" property="derctipo"/>
					</td>
					<td>
						<bean:write name="b001" property="derlliqd"/>
					</td>
					<td>
						<bean:write name="b001" property="dernmult"/>
					</td>
					<td>
						<bean:write name="b001" property="dercform"/>
					</td>
				</tr>
				</logic:iterate>
				</logic:notEmpty>
				</logic:present>

				<tr>
					<td colspan="7">
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