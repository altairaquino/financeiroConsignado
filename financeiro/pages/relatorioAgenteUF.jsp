	<%@include file="topo.jsp" %>	
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">	
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Relatórios Agente de Créditos por UF  &nbsp;
			</legend>
			<html:form action="actionEntidade">
			<table style="width: 650px;" align="center">
			<tbody>
				<tr>
					<td style="text-align: right;" colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2">
						Relatórios Corretor por (UF/Cidade)
					</th>
				</tr>
				<tr>
					<th>
						UF
					</th>
					<th>
						<html:select property="encufcd">
							<html:optionsCollection name="ls_estado" value="cdcuf" label="cdcuf"/>
						</html:select>
					</th>
				</tr>
				<tr>
					<th>
						Cidade
					</th>
					<th>
						<div id="cidades">
							Cidades
						</div>						
					</th>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Visualizar Relatório</html:submit>
					</td>
				</tr>													
			</tbody>
			</table>
			</html:form>
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>