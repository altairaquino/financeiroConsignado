	
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
				&nbsp;   Cadastro de Funcion�rios/Ger�ncia > <bean:write name="angariador" property="encnome"/>  &nbsp;
			</legend>
			<html:form action="actionFuncionarioAgencia" focus="fcancgtfa">
			<input type="hidden" name="fcancgag" value="<bean:write name="angariador" property="enncodg"/>">
			<html:hidden property="fcancgfc" styleId="fcancgfc"/>
			<html:hidden property="m" value="cadastro"/>
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="2">
						Cadastro de Funcion�rios/Ger�ncia
					</th>
				</tr>
				<tr>
					<td>
						Tipo de Funcion�rio
					</td>
					<td>
						<html:select property="fcancgtfa" style="width: 350px;">
							<html:optionsCollection name="ls_tipofuncagencia" value="tfancodg" label="tfacdesc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Funcion�rio
					</td>
					<td style="vertical-align: middle;" valign="middle">
						<html:text property="fcacnmfc" styleId="fcacnmfc" size="50" readonly="true" style="font-weight: bold; color: red;"/>
						<img src="imagens/pesquisa.jpg" width="22" height="22" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('funcionarioAgenciaPesquisaWindow.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');"/>
					</td>
				</tr>
				<tr>
					<td>
						Meta(%)
					</td>
					<td>
						<html:text property="fcanmeta" size="10" maxlength="10" onkeydown="Formata(this,10,event,2)" style="text-align: right;"/>
					</td>
				</tr>
				<tr>
					<td>
						Comiss�o(%)
					</td>
					<td>
						<html:text property="fcanperc" size="10" maxlength="10" onkeydown="Formata(this,10,event,2)" style="text-align: right;"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'actionFuncionarioAgencia.do?m=lista&enncodg=<bean:write name="angariador" property="enncodg"/>'">
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