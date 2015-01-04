	
	<%@include file="topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset style="text-align: center;">
			<legend>
				&nbsp;   Cadastro de Novo Produto    &nbsp;
			</legend>
			<html:form action="actionProduto" focus="pdncgbc" onsubmit="return window.confirm('Confirmar a inclusão do produto com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>
			<table style="width: 450px;">
			<tbody>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="2"> Cadastro de Novo Produto</th>
				</tr>
				<tr>
					<td>
						Banco
					</td>
					<td>
						<html:select property="pdncgbc" style="width: 350px;">
							<html:optionsCollection name="ls_banco" value="bcncodg" label="bccdesc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Nome do Produto
					</td>
					<td>
						<html:text property="pdcdesc" size="60" maxlength="60"/>
					</td>
				</tr>
				<tr>
					<td>
						Nome Abreviado
					</td>
					<td>
						<html:text property="pdcabrv" size="30" maxlength="30"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'actionProduto.do?m=lista'">
					</td>
					<td style="text-align: right;">						
						<html:submit styleClass="btn_hot">Cadastrar Produto</html:submit>
					</td>
				</tr>				
			
			</tbody>			
			</table>	
			</html:form>
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>