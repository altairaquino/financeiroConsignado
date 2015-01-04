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
				&nbsp;   Cadastro de Novo Grupo    &nbsp;
			</legend>
			<html:form action="actionGrupo" focus="grncodg">
			<html:hidden property="m" value="cadastro"/>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center;">
						Cadastro de Novo Grupo
					</th>
				</tr>
				<tr>
					<th>
						Código
					</th>
					<td>
						<html:text property="grncodg" size="4" maxlength="4" onkeyup="criaMascara(this,'####');"/>
					</td>
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
						<input type="button" value="Voltar" onclick="window.location = 'actionGrupo.do?m=lista'">
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