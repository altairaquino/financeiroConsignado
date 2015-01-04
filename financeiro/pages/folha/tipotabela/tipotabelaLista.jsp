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
				&nbsp;tipotabela&nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="3" style="text-align: center; background-color: #DDDDDD;">  tipotabela </th>
				</tr>
				<tr>				
					<td colspan="3">
						<input type="button" class="btn_hot" value="Novo" onclick="window.location = 'actionTipotabela.do?m=novo'">
					</td>
				</tr>
                <tr>
					<th>
						Código
					</th>
					<th>
						Descrição
					</th>
					<th>
						Sigla
					</th>
				</tr>
				<logic:empty name="listatipotabela">
					<th>
						 Não há tipotabela Cadastrado.
					</th>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b001" name="listatipotabela">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td style="text-align: right;width: 30px;">
						<bean:write name="b001" property="ttbncodg"/>&nbsp;&nbsp;
					</td>
					<td>
					<!-- 
						<a href="actionTipotabela.do?m=editar&ttbncodg=<bean:write name="b001" property="ttbncodg"/>">
					-->
					&nbsp;&nbsp;
						<a href="actionTipotabela.do?m=opcao&ttbncodg=<bean:write name="b001" property="ttbncodg"/>">
							<bean:write name="b001" property="ttbcdesc"/>
						</a>
					</td>
					<td>
						<bean:write name="b001" property="ttbcsigl"/>
					</td>
				</tr>
				</logic:iterate>

				<tr>
					<td colspan="3">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = '#adefinir'">
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
