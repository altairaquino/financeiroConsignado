	<%@include file="topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">
		
		<fieldset>
			<legend>
				&nbsp;   Pesquisa Contrato - Anexar Documentos   &nbsp;
			</legend>
			<html:errors/>
			<br><br><br><br><br><br>
			<center>
			<html:form action="actionContrato" focus="ctnnumr">
			<html:hidden property="m" value="pesquisaPorCorretor"/>
			<table style="width: 200px;">
			<tbody>
				<tr>
					<th style="text-align: center; background-color: #DDD;"> 
						Pesquisa Contrato 
					</th>
				</tr>	
				<tr>
					<td>
						Número <html:text property="ctnnumr" size="20" maxlength="15" onkeyup="criaMascara(this,'###############')"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Pesquisa</html:submit>
					</td>
				</tr>			
			</tbody>			
			</table>
			</html:form>
			</center>	
			<br><br><br>
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>