	<%@include file="../topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
		
			<legend>
				&nbsp;   Centros de Custo Cadastrados    &nbsp;
			</legend>
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th colspan="2">
						<input type="button" class="btn_hot" value="Novo Centro" onclick="window.location = 'actionCentroCusto.do?m=novo'">
					</th>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%;">
			<tbody>
				<logic:notEmpty name="ls_centrocusto">
				<tr>
					<th>
						Código
					</th>
					<th>
						Fonte Pagadora
					</th>
					<th>
						Centro de Custo
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				</logic:notEmpty>
				<logic:empty name="ls_centrocusto">
				<tr>
					<td>
						Não há centro de custo Cadastrado.
					</td>
				</tr>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_centrocusto">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="crncodg"/>
					</td>
					<td>
						<bean:write name="b" property="crcdcore"/>
					</td>
					<td>
						<bean:write name="b" property="crcdesc"/>
					</td>
					<td>
						<logic:equal value="T" name="b" property="crlativ">
							<input type="image" src="imagens/check.jpg" title="Desativa" onclick="window.location = 'actionCentroCusto.do?m=ativaDesativa&orcncgcr=<bean:write name="b" property="crncodg"/>&orcncgore=<bean:write name="b" property="crncgore"/>'">						
						</logic:equal>
						<logic:equal value="F" name="b" property="crlativ">
							<input type="image" src="imagens/cancela.jpg" title="Ativa" onclick="window.location = 'actionCentroCusto.do?m=ativaDesativa&orcncgcr=<bean:write name="b" property="crncodg"/>&orcncgore=<bean:write name="b" property="crncgore"/>'">						
						</logic:equal>
					</td>
					<td>
					</td>
				</tr>
				</logic:iterate>
							
			</tbody>			
			</table>	
		</div>
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>