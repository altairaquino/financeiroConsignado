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
				&nbsp;   Opções do Contato Jurídico > <bean:write name="contato" property="encnome"/>   &nbsp;
			</legend>
			<h3><bean:write name="contato" property="encnome"/> </h3>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; text-align: center;">
						Opções do Contato Jurídico 
					</th>
				</tr>
				<tr>
					<td>
						Dados Cadastrais do Contato
					</td>
					<td>
						<input type="button" class="btn_hot" value="Alterar" onclick="window.location = 'actionEntidade.do?m=editarContatoJuridico&enncodg=<bean:write name="contato" property="enncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Últimas Ligações Realizadas
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionLigacaoJuridico.do?m=lista&enncodg=<bean:write name="contato" property="enncodg"/>'">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionEntidade.do?m=listaContatoJuridico'">
					</td>
				</tr>	
			</tbody>			
			</table>
		
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>