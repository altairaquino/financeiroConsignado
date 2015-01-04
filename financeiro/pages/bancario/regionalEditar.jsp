	
	<%@include file="../topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset style="text-align: center;">
			<legend>
				&nbsp;   Altera��o de Regional    &nbsp;
			</legend>
			<html:form action="actionRegional" focus="rgcdesc">
			<html:hidden property="rgncodg"/>
			<html:hidden property="m" value="update"/>
			<table style="width: 650px;">
			<tbody>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="2"> 
						Altera��o de Regional
					</th>
				</tr>
				<tr>
					<td>
						Descri��o
					</td>
					<td>
						<html:text property="rgcdesc" size="40" maxlength="40"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'actionRegional.do?m=lista'">
					</td>
					<td style="text-align: right;">				
						<html:submit styleClass="btn_hot">Salvar Regional</html:submit>
					</td>
				</tr>				
			
			</tbody>			
			</table>	
			</html:form>
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>