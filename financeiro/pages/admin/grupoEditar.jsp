	<%@include file="../topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend class="red">
				&nbsp;   Alteração de Grupo    &nbsp;
			</legend>
			<html:form action="actionGrupo" focus="grcdesc">
			<html:hidden property="m" value="update"/>
			<html:hidden property="grncodg"/>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center;">
						Alteração de Grupo
					</th>
				</tr>
				<tr>
					<th>
						Descrição
					</th>
					<td>
						<html:text property="grcdesc" size="50" maxlength="45"/>
					</td>
				</tr>	
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'actionGrupo.do?m=lista'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Salvar Grupo</html:submit>
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