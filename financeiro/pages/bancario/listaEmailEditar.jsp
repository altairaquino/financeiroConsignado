	
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
				&nbsp;   Alteração de e-mail da Lista    &nbsp;
			</legend>
			<html:form action="actionListaEmail" focus="ltecdesc" onsubmit="return window.confirm('Confirmar a alteração de e-mail do grupo com os dados fornecidos?')">
			<html:hidden property="m" value="update"/>
			<table style="width: 650px;">
			<tbody>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="2">
						 Alteração de e-mail da Lista
					</th>
				</tr>
				<tr>
					<th>
						Nome
					</th>
					<td>
						<bean:write name="formListaEmail" property="ltecnome"/>
					</td>
				</tr>
				<tr>
					<th>
						E-mail
					</th>
					<td>
						<bean:write name="formListaEmail" property="ltecmail"/>
					</td>
				</tr>
				<tr>
					<th>
						Grupo E-mail
					</th>
					<td>
						<bean:write name="formListaEmail" property="ltecdcgem"/>
					</td>
				</tr>
				<tr>
					<th>
						Status
					</th>
					<td>
						<html:radio property="ltelativ" value="T"> &nbsp;Ativo </html:radio>&nbsp;&nbsp;<html:radio property="ltelativ" value="F">&nbsp; Inativo </html:radio>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'actionListaEmail.do?m=lista&gemncodg=<bean:write name="formListaEmail" property="ltencggem"/>'">
					</td>
					<td style="text-align: right;">						
						<html:submit styleClass="btn_hot">Salvar E-mail</html:submit>
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