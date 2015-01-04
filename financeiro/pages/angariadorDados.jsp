	<%@include file="topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
			
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">	
				
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Dados Cadastrais do Agente de Crédito    &nbsp;
			</legend>
			<table style="width: 550px;">
			<tbody>
				
				<tr>
					<td style="text-align: right;" colspan="2">											
						<input type="button" class="btn_hot" value="Editar Dados" onclick="window.location = 'actionEntidade.do?m=editarAngariador&enncodg=<bean:write name="angariador" property="enncodg"/>'">
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> Dados Cadastrais do Cliente </th>
				</tr>
				<tr>
					<td>
						Nome
					</td>
					<td>
						<bean:write name="angariador" property="encnome"/>
					</td>
				</tr>
				<tr>
					<td>
						C.P.F.
					</td>
					<td>
						<bean:write name="angariador" property="encdocm" />
					</td>
				</tr>
				<tr>
					<td>
						Data Nascimento
					</td>
					<td>
						<bean:write name="angariador" property="endnasc" />
					</td>
				</tr>
				<tr>
					<td>
						Sexo
					</td>
					<td>
						<logic:notEmpty name="angariador" property="encsexo">
							<logic:equal name="angariador" property="encsexo" value="M">
								Masculino
							</logic:equal>
							<logic:equal name="angariador" property="encsexo" value="F">
								Feminino
							</logic:equal>
						</logic:notEmpty>
						
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;"> Contato </th>
				</tr>
				<tr>
					<td>
						Fone Fixo
					</td>
					<td>
						<bean:write name="angariador" property="encfone"/>
					</td>
				</tr>
				<tr>
					<td>
						Celular
					</td>
					<td>
						<bean:write name="angariador" property="enccell"/>
					</td>
				</tr>
				<tr>
					<td>
						E-mail
					</td>
					<td>
						<bean:write name="angariador" property="encmail"/>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;"> Endereço </th>
				</tr>
				<tr>
					<td>
						Logradouro
					</td>
					<td>
						<bean:write name="angariador" property="encdctl"/>&nbsp;<bean:write name="angariador" property="enlendr"/>						
					</td>
				</tr>
				<tr>
					<td>
						Estado
					</td>
					<td>
						<bean:write name="angariador" property="encufcd"/>																
					</td>
				</tr>
				<tr>
					<td>
						Cidade
					</td>
					<td>						
						<bean:write name="angariador" property="encdccd"/>							
					</td>
				</tr>				
				<tr>
					<td>
						CEP/Bairro
					</td>
					<td>
						<bean:write name="angariador" property="enccep"/> - 
						<bean:write name="angariador" property="encbair"/>
					</td>
				</tr>
				<tr>
					<th valign="top" colspan="2" style="text-align: center;background-color: #DDDDDD;"> 
						Observações Gerais 
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<bean:write name="angariador" property="enmobs"/>											
					</td>
				</tr>		
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionEntidade.do?m=opcoesAngariador&enncodg=<bean:write name="angariador" property="enncodg"/>'">
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