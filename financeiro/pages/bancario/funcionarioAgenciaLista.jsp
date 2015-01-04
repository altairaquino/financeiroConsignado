	
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
				&nbsp;   Rela��o de Funcion�rios/Ger�ncia > <bean:write name="angariador" property="encnome"/>  &nbsp;
			</legend>
			<table style="width: 650px;">
			<tbody>
				<tr>
					<td colspan="6">
						<input type="button" class="btn_hot" value="Novo" title="Incluir novo Funcion�rios/Ger�ncia" onclick="window.location = 'actionFuncionarioAgencia.do?m=novo&enncodg=<bean:write name="angariador" property="enncodg"/>'">
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="6">
						Rela��o de Funcion�rios/Ger�ncia
					</th>
				</tr>
				<logic:notEmpty name="ls_funcionarioagencia">
				<tr style="background-color: #DDD;text-align: center;">
					<th>
						Tipo do Funcion�rio
					</th>
					<th>
						Entidade
					</th>				
					<th>
						Meta
					</th>
					<th>
						Comiss�o
					</th>
					<th>
						Status
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				</logic:notEmpty>
				<logic:empty name="ls_funcionarioagencia">
				<tr>
					<th colspan="6" style="color: red;">
						N�o h� Funcion�rios/Ger�ncia cadastrado.
					</th>
				</tr>
				</logic:empty>
				<logic:iterate id="b" name="ls_funcionarioagencia">
				<tr>
					<td>
						<bean:write name="b" property="fcacdctfa"/>
					</td>
					<td>
						<bean:write name="b" property="fcacnmfc"/>
					</td>
					<td>
						<bean:write name="b" property="fcanmeta"/>&nbsp;%
					</td>
					<td>
						<bean:write name="b" property="fcanperc"/>&nbsp;%
					</td>
					<td>
						<logic:equal name="b" property="fcalativ" value="T">
							Ativo
						</logic:equal>					
						<logic:equal name="b" property="fcalativ" value="F">
							Inativo
						</logic:equal>
					</td>
					<td>
						<input type="image" src="imagens/editar.gif" title="Alterar" onclick="window.location = 'actionFuncionarioAgencia.do?m=editar&fcancodg=<bean:write name="b" property="fcancodg"/>'">
						<input type="image" src="imagens/check.jpg" title="Ver Tabelas de Comiss�o" onclick="window.location = 'actionTabelaFuncionario.do?m=lista&fcancodg=<bean:write name="b" property="fcancodg"/>'">
						<input type="image" src="imagens/mail.gif" title="Imprimir Carta de Apresenta��o" onclick="window.location = 'actionFuncionarioAgencia.do?m=imprimeCarta&enncodg=<bean:write name="b" property="fcancgfc"/>'">
					</td>
				</tr>
				</logic:iterate>
				<tr>
					<td colspan="6">
						<input type="button" class="btn" value="Voltar" onclick="window.location = 'actionEntidade.do?m=opcoesAngariador&enncodg=<bean:write name="angariador" property="enncodg"/>'">
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