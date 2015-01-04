	<%@include file="topo.jsp" %>
<style type="text/css">
	.style1{
		text-align: right;
		width: 120px;
		color:#660000;
	}
</style>
			
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Dados do funcionário    &nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #ddd;">
						Dados Básicos do funcionário
					</th>
				</tr>
				<tr>
					<td class="style1">
						Nome:
					</td>
					<td>
						<bean:write name="funcionario" property="encnome"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						C.P.F.:
					</td>
					<td>
						<bean:write name="funcionario" property="encdocm"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Data Nascimento:
					</td>
					<td>
						<bean:write name="funcionario" property="endnasc"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Sexo:
					</td>
					<td>
						<logic:equal name="funcionario" property="encsexo" value="M">MASCULINO</logic:equal>
						<logic:equal name="funcionario" property="encsexo" value="F">FEMININO</logic:equal>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						 Filiação
					</th>
				</tr>				
				<tr>
					<td colspan="2">
						<hr/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Nome da mãe:
					</td>
					<td>
						<bean:write name="funcionario" property="encmae"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Nome do pai:
					</td>
					<td>
						<bean:write name="funcionario" property="encpai"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr/>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						 Contato 
					</th>
				</tr>				
				<tr>
					<td colspan="2">
						<hr/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Fone Fixo:
					</td>
					<td>
						<bean:write name="funcionario" property="encfone"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Celular:
					</td>
					<td>
						<bean:write name="funcionario" property="enccell"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						E-mail:
					</td>
					<td>
						<bean:write name="funcionario" property="encmail"/>
					</td>
				</tr>
				<tr>
					<td colspan="2"><hr/></td>
				</tr>
				<tr>
					<th colspan="2">Endereço</th>
				</tr>				
				<tr>
					<td colspan="2"><hr/></td>
				</tr>
				<tr>
					<td class="style1">
						Logradouro:
					</td>
					<td>
						<bean:write name="funcionario" property="encdctl"/>
						<bean:write name="funcionario" property="enlendr"/>		
					</td>
				</tr>
				<tr>
					<td class="style1">
						Estado
					</td>
					<td>
						<bean:write name="funcionario" property="encufcd"/>					
					</td>
				</tr>
				<tr>
					<td class="style1">
						Cidade
					</td>
					<td>
						<bean:write name="funcionario" property="encdccd"/>	
					</td>
				</tr>				
				<tr>
					<td class="style1">
						CEP/Bairro
					</td>
					<td>
						<bean:write name="funcionario" property="enccep"/>
						<bean:write name="funcionario" property="encbair"/>
					</td>
				</tr>
				<tr>
					<td colspan="2"><hr/></td>
				</tr>
				<tr>
					<th colspan="2">Observações Gerais</th>
				</tr>				
				<tr>
					<td colspan="2"><hr/></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<bean:write name="funcionario" property="enmobs"/>						
					</td>
				</tr>		
				<tr>
					<td>					
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionEntidade.do?m=opcoesFuncionario&enncodg=<bean:write name="funcionario" property="enncodg"/>'">
					</td>
					<td style="text-align: right;">
						<input type="button" class="btn_hot" value="Editar funcionário" onclick="window.location = 'actionEntidade.do?m=editarFuncionario&enncodg=<bean:write name="funcionario" property="enncodg"/>'">
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