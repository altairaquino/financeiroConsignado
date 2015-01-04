	<%@include file="topo.jsp" %>	
	<%@page import="com.grupoexata.bancario.dao.*" %>
	
	<%
		request.setAttribute("ls_grupoemail", ModelGrupoEmail.getInstance().getGrupoEmails());
	
	%>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">	
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Envio de E-mais &nbsp;
			</legend>
			<html:form action="/actionEnvioEmail">
			<html:hidden property="m" value="enviar"/>
			<center>
			<table style="width: 520px;">
			<tbody>
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Grupos de E-mail" onclick="window.location = 'actionGrupoEmail.do?m=lista'">
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2">
						Envio de E-mails
					</th>
				</tr>
				<tr>
					<td>
						Tipo de E-mail
					</td>
					<td>
						<select name="tipo" style="width: 400px;">
							<option value="1">E-mail de relatório de Gerentes Comercial</option>
							<option value="2">E-mail de Desempenho de Agências do Sinergia</option>													
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" colspan="2">
						&nbsp;
					</td>
				</tr>				
				<tr>
					<td style="text-align: right;" colspan="2">
						<html:submit styleClass="btn_hot">Enviar E-mail</html:submit>						
					</td>
				</tr>
			</tbody>
			</table>
			</html:form>
			<br>
			<html:form action="/actionEnvioEmail">
			<html:hidden property="m" value="enviarPorGrupo"/>
			<table style="width: 520px;">
			<tbody>
			<tr>
				<th colspan="2" style="background-color: #DDD; text-align: center;" >
					Envio de comunicado por e-mail
				</th>
			</tr>
			<tr>
				<th>
					Título
				</th>
				<td>
					<input type="text" name="titulo" size="60" maxlength="60">
				</td>
			</tr>
			<tr>
				<th>
					Grupo
				</th>
				<td>
					<select name="grupo">
						<logic:iterate id="b" name="ls_grupoemail">
							<option value="<bean:write name="b" property="gemncodg"/>"> <bean:write name="b" property="gemcdesc"/></option>							
						</logic:iterate>
					</select>
				</td>
			</tr>
			<tr>
				<th colspan="2" style="background-color: #DDD; text-align: center;" >
					Mensagem
				</th>
			</tr>	
			<tr>
				<td colspan="2">
					<textarea name="conteudo" rows="10" cols="80"></textarea>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;" colspan="2">
					<input type="submit" class="btn_hot" value="Enviar E-mail">
				</td>
			</tr>
			</tbody>
			</table>
			</html:form>
			</center>
									
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
		
</body>
</html>