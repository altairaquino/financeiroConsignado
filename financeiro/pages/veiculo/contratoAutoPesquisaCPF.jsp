	<%@include file="../topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">
		
		<fieldset>
			<legend>
				&nbsp;   Pesquisa Contrato - Dados do Contrato - Auto  &nbsp;
			</legend>
			<html:errors/>
			<br><br><br><br><br><br>
			<center>
			<html:form action="actionContratoAuto" focus="ctacdocm">
			<html:hidden property="m" value="pesquisaCPF"/>
			<table style="width: 200px;">
			<tbody>
				<tr>
					<th style="text-align: center; background-color: #DDD;">
						Pesquisa Contrato por CPF
					</th>
				</tr>
				<tr>
					<td style="text-align: center;">
						C.P.F. <html:text property="ctacdocm" size="15" maxlength="15" onkeyup="criaMascara(this,'###.###.###-##')"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						<input type="button" class="btn_hot" value="Pesquisa" onclick="this.form.submit()">
					</td>
				</tr>			
			</tbody>			
			</table>
			</html:form>
			</center>	
			<br><br><br>
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>