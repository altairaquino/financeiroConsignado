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
				&nbsp;   Dados Cadastrais do Cliente    &nbsp;
			</legend>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<td colspan="2"> &nbsp;</td>
				</tr>
				<tr>
					<td style="width: 25%;">
						<input type="button" class="btn_hot" value="Nova Pesquisa" onclick="window.location = 'clientePesquisaDados.do'">
					</td>
					<td style="text-align: right; width: 75%;">											
						<input type="button" class="btn_hot" value="Editar Dados" onclick="window.location = 'actionEntidade.do?m=editarCliente&enncodg=<bean:write name="cliente" property="enncodg"/>'">
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
						<bean:write name="cliente" property="encnome"/>
					</td>
				</tr>
				<tr>
					<td>
						C.P.F.
					</td>
					<td>
						<bean:write name="cliente" property="encdocm" />
					</td>
				</tr>
				<tr>
					<td>
						Data Nascimento
					</td>
					<td>
						<bean:write name="cliente" property="endnasc" />
					</td>
				</tr>
				<tr>
					<td>
						Sexo
					</td>
					<td>
						<logic:notEmpty name="cliente" property="encsexo">
							<logic:equal name="cliente" property="encsexo" value="M">
								Masculino
							</logic:equal>
							<logic:equal name="cliente" property="encsexo" value="F">
								Feminino
							</logic:equal>
						</logic:notEmpty>						
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;">
						Dados do RG
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<b>Nº RG:</b>&nbsp;<bean:write name="cliente" property="encrg"/> - 
						&nbsp;<b>Data de Exp.:</b>&nbsp;<bean:write name="cliente" property="endexrg"/> - 
						&nbsp;<b>Org. Exp.:</b>&nbsp;<bean:write name="cliente" property="encoerg"/> - 
						&nbsp;<b>UF:</b>&nbsp;<bean:write name="cliente" property="encufrg"/>
						<br>
						<b>Doc. Origem:</b>&nbsp;<bean:write name="cliente" property="encdorg"/>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;">
						Filiação
					</th>
				</tr>
				<tr>
					<td>
						Nome da Mãe
					</td>
					<td>
						<bean:write name="cliente" property="encmae"/>
					</td>
				</tr>
				<tr>
					<td>
						Nome do Pai
					</td>
					<td>
						<bean:write name="cliente" property="encpai"/>
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
						<bean:write name="cliente" property="encfone"/>
					</td>
				</tr>
				<tr>
					<td>
						Celular
					</td>
					<td>
						<bean:write name="cliente" property="enccell"/>
					</td>
				</tr>
				<tr>
					<td>
						E-mail
					</td>
					<td>
						<bean:write name="cliente" property="encmail"/>
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
						<bean:write name="cliente" property="encdctl"/>&nbsp;<bean:write name="cliente" property="enlendr"/>						
					</td>
				</tr>
				<tr>
					<td>
						Estado
					</td>
					<td>
						<bean:write name="cliente" property="encufcd"/>																
					</td>
				</tr>
				<tr>
					<td>
						Cidade
					</td>
					<td>						
						<bean:write name="cliente" property="encdccd"/>							
					</td>
				</tr>				
				<tr>
					<td>
						CEP/Bairro
					</td>
					<td>
						<bean:write name="cliente" property="enccep"/> - 
						<bean:write name="cliente" property="encbair"/>
					</td>
				</tr>
				<tr>
					<th valign="top" colspan="2" style="text-align: center;background-color: #DDDDDD;"> 
						Observações Gerais 
					</th>
				</tr>
				<tr>
					<td colspan="2">						
						<bean:write name="cliente" property="enmobs"/>											
					</td>
				</tr>		
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Nova Pesquisa" onclick="window.location = 'clientePesquisaDados.do'">
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