	<%@include file="../topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
					
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Alteração de dados do Bem - Veículo    &nbsp;
			</legend>
			<html:form action="/actionVeiculo" onsubmit="return window.confirm('Confirmar a alteração do veículo com os dados fornecidos?')">
			<html:hidden property="m" value="update"/>
			<html:hidden property="veincodg"/>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> 
						Alteração de dados do Bem - Veículo
					</th>
				</tr>
				<tr>
					<th>
						Marca
					</th>
					<td>
						<html:select property="veinmarc" style="width: 300px;">
							<html:optionsCollection name="ls_marcaveiculo" label="mvecdesc" value="mvencodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th> 
						Modelo
					</th>
					<td>
						<html:text property="veicmode" size="47" maxlength="50"/>	
					</td>
				</tr>
				<tr>
					<th>
						Ano de Fabricação
					</th>
					<td>
						<html:text property="veinanof" size="4" maxlength="4" onkeyup="criaMascara(this,'####');"/>
					</td>
				</tr>
				<tr>
					<th>
						Valor de Mercado
					</th>
					<td>
						<html:text property="veiyvalr" size="10" maxlength="10" onkeydown="Formata(this,10,event,2)" style="text-align: right;"/>
					</td>
				</tr>	
				<tr>
					<th>
						Placa
					</th>
					<td>
						<html:text property="veicplac" size="8" maxlength="8"/>
					</td>
				</tr>				
				<tr>
					<th>
						Chassi
					</th>
					<td>
						<html:text property="veicchas" size="35" maxlength="30"/>						
					</td>
				</tr>
				<tr>
					<th>
						Renavan
					</th>
					<td>
						<html:text property="veicrena" size="20" maxlength="20" onkeyup="criaMascara(this,'####################');"/>												
					</td>
				</tr>
				<tr>
					<th>
						UF Licenciamento
					</th>
					<td>
						<html:select property="veicufli" style="width: 300px;">
							<html:option value="null">---</html:option>
							<html:optionsCollection name="ls_estado" value="cdcuf" label="cdcuf"/>
						</html:select>						
					</td>
				</tr>
				<tr>
					<th>
						Combustível												
					</th>
					<td>
						<html:select property="veincomb" style="width: 300px;">
							<html:optionsCollection name="ls_tipocombustivel" value="tconcodg" label="tcocdesc"/>
						</html:select>						
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Cancelar" onclick="window.history.back()">
					</td>
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Salvar Veículo</html:submit>
					</td>
				</tr>				
				
			</tbody>	
			</table>
			</html:form>
		
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
	
	<script type="text/javascript">
		Mapping.getNomeEntidadePorCPF(retornoNome, document.forms[0].ctacdocm.value);
	</script>
</body>
</html>