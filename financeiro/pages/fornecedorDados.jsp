	<%@include file="topo.jsp" %>
	
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Dados do Fornecedor    &nbsp;
			</legend>
			<table style="width: 550px;">
			<tbody>
				<tr>
					<td>
						<input type="button" value="Voltar" onclick="window.location = 'actionEntidade.do?m=opcoesFornecedor&enncodg=<bean:write name="fornecedor" property="enncodg"/>'">
					</td>
					<td style="text-align: right;">
						<input type="button" class="btn_hot" value="Editar Dados" onclick="window.location = 'actionEntidade.do?m=editarFornecedor&enncodg=<bean:write name="fornecedor" property="enncodg"/>'">
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> 
						Dados do Fornecedor
					</th>
				</tr>
				<tr>
					<th style="width: 25%">
						Nome
					</th>
					<td style="width: 75%">
						<bean:write name="fornecedor" property="encnome"/>
					</td>
				</tr>
				<tr>
					<th>
						Tipo
					</th>
					<td>
						<bean:write name="fornecedor" property="encdcpp"/>
					</td>
				</tr>				
				<tr>
					<th>
						CPF/CNPJ
					</th>
					<td>
						<bean:write name="fornecedor" property="encdocm"/>
					</td>
				</tr>
				<tr>
					<th>
						Data Nascimento
					</th>
					<td>
						<bean:write name="fornecedor" property="endnasc"/>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;"> 
						Contatos 
					</th>
				</tr>
				<tr>
					<th>
						Fone Fixo
					</th>
					<td>
						<bean:write name="fornecedor" property="encfone"/>
					</td>
				</tr>
				<tr>
					<th>
						Celular
					</th>
					<td>
						<bean:write name="fornecedor" property="enccell"/>
					</td>
				</tr>
				<tr>
					<th>
						E-mail
					</th>
					<td>
						<bean:write name="fornecedor" property="encmail"/>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;"> 
						Endereço 
					</th>
				</tr>
				<tr>
					<th>
						Logradouro
					</th>
					<td>
						<bean:write name="fornecedor" property="encdctl"/>
						<bean:write name="fornecedor" property="enlendr"/>						
					</td>
				</tr>
				<tr>
					<th>
						Estado
					</th>
					<td>
						<bean:write name="fornecedor" property="encufcd"/>										
					</td>
				</tr>
				<tr>
					<th>
						Cidade
					</th>
					<td>
						<div id="cidade">
						<bean:write name="fornecedor"  property="encdccd"/>
						</div>
					</td>
				</tr>				
				<tr>
					<th>
						CEP/Bairro
					</th>
					<td>
						<bean:write name="fornecedor" property="enccep"/>
						<bean:write name="fornecedor" property="encbair"/>
					</td>
				</tr>
				<tr>
					<th valign="top" colspan="2" style="text-align: center;background-color: #DDDDDD;"> 
						Observações Gerais 
					</th>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<textarea name="enmobs" cols="70" rows="3" readonly="readonly"><bean:write name="fornecedor" property="enmobs"/></textarea>						
					</td>
				</tr>						
			
			</tbody>			
			</table>
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>