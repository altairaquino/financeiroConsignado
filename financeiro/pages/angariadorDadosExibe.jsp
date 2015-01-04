	<%@include file="topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend class="red">
				&nbsp;   <bean:write name="angariador2" property="ancnmen"/> > Dados do Angariador (Configuração)   &nbsp;
			</legend>
			<table style="width: 600px;" align="center">			
			<tbody>
				<tr>
					<th colspan="2" style="text-align: right;">
						<input type="button" class="btn" value="Editar" onclick="window.location = 'actionAngariador.do?m=editar&enncodg=<bean:write name="angariador2" property="anncgen"/>'">
					</th>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDD;">
						Dados do Angariador (Configuração)
					</th>
				</tr>
				<tr>
					<th style="width: 25%;">
						Tipo
					</th>
					<td style="width: 75%;">
						<bean:write name="angariador2" property="ancdctn"/>
					</td>
				</tr>
				<tr>
					<th>
						Regional
					</th>
					<td>
						<bean:write name="angariador2" property="ancdcre"/>
						<logic:empty name="angariador2" property="ancdcre">
							Sem Regional
						</logic:empty>
					</td>
				</tr>
				<tr>
					<th style="width: 150px;">
						Meta Mensal
					</th>
					<td style="width: 350px;">
						R$ <bean:write name="angariador2" property="annmeta"/>
					</td>
				</tr>
				<tr>
					<th>
						Spread
					</th>
					<td>
						<bean:write name="angariador2" property="annspre"/> %
					</td>
				</tr>
				<%-- 
				<tr>
					<th>
						É agência SINERGIA?
					</th>
					<td>
						<logic:equal name="angariador2" property="anlagen" value="T">
							Sim
						</logic:equal>
						<logic:equal name="angariador2" property="anlagen" value="F">
							Não
						</logic:equal>						
					</td>
				</tr>
				 --%>
				<tr>
					<th>
						Data de Cadastro
					</th>
					<td>
						<bean:write name="angariador2" property="andcadt"/>
					</td>
				</tr>	
				<tr>
					<th>
						Status
					</th>
					<td>
						<logic:equal name="angariador2" property="anlativ" value="T">
							Ativo
						</logic:equal>
						<logic:equal name="angariador2" property="anlativ" value="F">
							Inativo
						</logic:equal>						
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionEntidade.do?m=opcoesAngariador&enncodg=<bean:write name="angariador2" property="anncgen"/>'">
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