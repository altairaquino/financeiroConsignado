	
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
				&nbsp;   Rela��o de Funcion�rios da Regional > <bean:write name="regional" property="rgcdesc"/>  &nbsp;
			</legend>
			<table style="width: 650px;">
			<tbody>
				<tr>
					<td colspan="6">
						<input type="button" class="btn_hot" value="Novo" title="Incluir novo Funcion�rios" onclick="window.location = 'actionFuncRegional.do?m=novo&rgncodg=<bean:write name="regional" property="rgncodg"/>'">
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="4">
						Rela��o de Funcion�rios da Regional
					</th>
				</tr>
				<logic:notEmpty name="ls_funcRegional">
				<tr style="background-color: #DDD;text-align: center;">
					<th>
						Tipo do Funcion�rio
					</th>
					<th>
						Funcion�rio
					</th>				
					<th>
						Status
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				</logic:notEmpty>
				<logic:empty name="ls_funcRegional">
				<tr>
					<th colspan="4" style="color: red;">
						N�o h� Funcion�rio cadastrado.
					</th>
				</tr>
				</logic:empty>
				<logic:iterate id="b" name="ls_funcRegional">
				<tr>
					<td>
						<bean:write name="b" property="frecdctfa"/>
					</td>
					<td>
						<bean:write name="b" property="frecnmen"/>
					</td>
					<td>
						<logic:equal name="b" property="frelativ" value="T">
							Ativo
						</logic:equal>					
						<logic:equal name="b" property="frelativ" value="F">
							Inativo
						</logic:equal>
					</td>
					<td>
						<input type="image" src="imagens/editar.gif" title="Alterar" onclick="window.location = 'actionFuncRegional.do?m=editar&frencodg=<bean:write name="b" property="frencodg"/>'">
					</td>
				</tr>
				</logic:iterate>
				<tr>
					<td colspan="4">
						<input type="button" class="btn" value="Voltar" onclick="window.location = 'actionRegional.do?m=lista'">
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