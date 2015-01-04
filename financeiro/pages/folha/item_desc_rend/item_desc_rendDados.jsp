<%@include file="topo.jsp" %>
<script type="text/javascript" src="scripts/jquery.js"></script>
</head>
<body>
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;Dados Cadastrais item_desc_rend&nbsp;
			</legend>
			<table style="width: 550px;">
			<tbody>
				<tr>
					<td style="text-align: right;" colspan="2">											
						<input type="button" class="btn_hot" value="Editar item_desc_rend" onclick="window.location = 'actionItemDescRend.do?m=dados&idrncodg=<bean:write name="item_desc_rend" property="idrncodg"/>'">
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">  Dados Cadastrais item_desc_rend </th>
				</tr>
                				<tr>
					<td>
						IDRNCODG
					</td>
					<td>
						<bean:write name="item_desc_rend" property="idrncodg"/>
					</td>
				</tr>
				<tr>
					<td>
						IDRNCGDER
					</td>
					<td>
						<bean:write name="item_desc_rend" property="idrncgder"/>
					</td>
				</tr>
				<tr>
					<td>
						IDRNCGTDR
					</td>
					<td>
						<bean:write name="item_desc_rend" property="idrncgtdr"/>
					</td>
				</tr>
				<tr>
					<td>
						IDRCTIPO
					</td>
					<td>
						<bean:write name="item_desc_rend" property="idrctipo"/>
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = '#adefinir'">
					</td>
				</tr>
			</tbody>			
			</table>
		</fieldset>
		</div>
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>
