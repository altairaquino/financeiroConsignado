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
				&nbsp;   <bean:write name="grupoemail" property="gemcdesc"/> > Lista de E-mail   &nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="4">
						Lista de E-mails
					</th>
				</tr>
				<tr>
					<th>
						Adicionar
					</th>
					<td colspan="3">
						<html:form action="actionListaEmail" onsubmit="if (this.ltecnome.value == ''){alert('Informe o nome da entidade para adicionar!'); return false;} return true;">
						<html:hidden property="m" value="cadastro"/>
						<input type="hidden" name="ltencggem" value="<bean:write name="grupoemail" property="gemncodg"/>">
						<html:hidden property="ltencgen" styleId="enncodg"/>
						<html:text property="ltecnome" styleId="encnome" size="50" readonly="true" style="font-weight: bold; color: red;"/>
						<img src="imagens/lupa.gif" width="18" height="18" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('entidadePesquisaWindow.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');"/>
						<html:submit styleClass="btn_hot">Adicionar</html:submit>						
						</html:form>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionGrupoEmail.do?m=lista'">
					</td>
				</tr>				
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%">
			<tbody>
				<tr>
					<th>
						Nome
					</th>
					<th>
						E-mail
					</th>
					<th>
						Status
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_listaemail">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<a href="actionListaEmail.do?m=editar&ltencodg=<bean:write name="b" property="ltencodg"/>">
							<bean:write name="b" property="ltecnome"/>
						</a>
					</td>
					<td>
						<bean:write name="b" property="ltecmail"/>
					</td>
					<td>
						<logic:equal name="b" property="ltelativ" value="T">
							Ativo
						</logic:equal>
						<logic:equal name="b" property="ltelativ" value="F">
							Inativo
						</logic:equal>
					</td>
					<td>
						<input type="image" src="imagens/delete.gif" title="Excluir da Lista" onclick="if (window.confirm('Confirmar a exclusão da lista?')){window.location = 'actionListaEmail.do?m=delete&ltencodg=<bean:write name="b" property="ltencodg"/>'}">
					</td>
				</tr>				
				</logic:iterate>
			</tbody>			
			</table>
			</div>		
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>