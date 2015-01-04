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
				&nbsp;Cadastro item_desc_rend (alteração)&nbsp;
			</legend>
            <html:form action="actionItemDescRend" onsubmit="return window.confirm('Confirmar a inclusão do item_desc_rend com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>
            		<html:hidden property="idrncodg"/>

			<table style="width: 550px;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Cadastro item_desc_rend (alteração) </th>
				</tr>
                				<tr>
					<td>
						IDRNCODG
					</td>
					<td>
						<html:text property="idrncodg" disabled="true"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						IDRNCGDER
					</td>
					<td>
						<html:text property="idrncgder" ></html:text>
					</td>
				</tr>
				<tr>
					<td>
						IDRNCGTDR
					</td>
					<td>
						<html:text property="idrncgtdr" ></html:text>
					</td>
				</tr>
				<tr>
					<td>
						IDRCTIPO
					</td>
					<td>
						<html:text property="idrctipo" ></html:text>
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = '#adefinir'">
					</td>
				</tr>
                <tr>
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Cadastrar item_desc_rend</html:submit>
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
