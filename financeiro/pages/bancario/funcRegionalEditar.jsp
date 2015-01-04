	
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
				&nbsp;   Altera��o de Funcion�rio da Regional > <bean:write name="formFuncRegional" property="frencgrg"/>  &nbsp;
			</legend>
			<html:form action="actionFuncRegional" focus="frencgtfa">
			<html:hidden property="frencgrg"/>
			<html:hidden property="frencgen" styleId="fcancgfc"/>
			<html:hidden property="m" value="update"/>
			<table style="width: 550px;">
			<tbody>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="2">
						Altera��o de Funcion�rio da Regional
					</th>
				</tr>
				<tr>
					<td>
						Tipo de Funcion�rio
					</td>
					<td>
						<html:select property="frencgtfa" style="width: 350px;">
							<html:optionsCollection name="ls_tipofuncagencia" value="tfancodg" label="tfacdesc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Funcion�rio
					</td>
					<td style="vertical-align: middle;" valign="middle">
						<html:text property="frecnmen" styleId="fcacnmfc" size="50" readonly="true" style="font-weight: bold; color: red;"/>
						<img src="imagens/pesquisa.jpg" width="22" height="22" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('funcionarioAgenciaPesquisaWindow.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');"/>
					</td>
				</tr>
				<tr>
					<td>
						Status
					</td>
					<td>
						<html:radio property="frelativ" value="T">Ativo</html:radio>&nbsp;&nbsp;&nbsp;<html:radio property="frelativ" value="F">Inativo</html:radio>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'actionFuncRegional.do?m=lista&rgncodg=<bean:write name="formFuncRegional" property="frencgrg"/>'">
					</td>
					<td style="text-align: right;">					
						<html:submit styleClass="btn_hot">Salvar</html:submit>
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