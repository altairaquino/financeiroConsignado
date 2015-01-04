	
	<%@include file="../topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset style="text-align: center;">
			<legend class="red">
				&nbsp;   Ouvidoria Exata (Sugestões e Reclamações)    &nbsp;
			</legend>
			<html:form action="actionOuvidoria" focus="ouvncgtis" onsubmit="return window.confirm('Confirmar o envio com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>
			<table style="width: 650px;" align="center">
			<tbody>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="2"> 
						Formulário para envio de sugestões/reclamações
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<b>Deseja identificar a sugestão/reclamação?</b>&nbsp;&nbsp;&nbsp;
						<html:radio property="ouvlidet" value="T">Sim</html:radio>&nbsp;&nbsp;
						<html:radio property="ouvlidet" value="F">Não</html:radio>
					</td>
				</tr>
				<tr>
					<th>
						Ao setor
					</th>
					<td>
						<html:select property="ouvncgset" style="width: 300px;">
							<html:optionsCollection name="ls_setor" label="setcdesc" value="setncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						Tipo de Solicitação
					</th>
					<td>
						<html:select property="ouvncgtis" style="width: 300px;">
							<html:optionsCollection name="ls_tiposolicitacao" label="tiscdesc" value="tisncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						Assunto
					</th>
					<td>
						<html:text property="ouvcassu" size="50" maxlength="48"/>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center;">
						Mensagem
					</th>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<html:textarea property="ouvcdesc" rows="5" cols="80"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'home.do'">
					</td>
					<td style="text-align: right;">						
						<html:submit styleClass="btn_hot">Enviar</html:submit>
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