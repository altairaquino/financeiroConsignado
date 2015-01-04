	<%@include file="topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend class="red">
				&nbsp;   Registro de Liga��o do Agente de Cr�dito    &nbsp;
			</legend>
			<table style="width: 500px;">
			<html:form action="actionLigacao" method="post" onsubmit="return window.confirm('Confirmar o registro da liga��o com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>
			<input type="hidden" name="lgncgan" value="<bean:write name="angariador" property="enncodg"/>">
			<input type="hidden" name="lgncgfc" value="<bean:write name="usuario" property="enncodg"/>">
			<tbody>				
				<tr style="background-color: #DDD;">
					<th colspan="2" style="text-align: center;"> Registro de Liga��o </th>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						Agente de Cr�dito
					</td>
					<td style="color: red;">
						<bean:write name="angariador" property="encnome"/>
					</td>
				</tr>
				<tr>
					<td>
						Motivo da Liga��o
					</td>
					<td>
						<html:select property="lgncgml">
							<html:optionsCollection name="ls_motivoligacao" label="mlcdesc" value="mlncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Resultado da Liga��o
					</td>
					<td>
						<html:select property="lgncgrl">
							<html:optionsCollection name="ls_resultadoligacao" label="rlcdesc" value="rlncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Documento Consultado
					</td>
					<td>
						<html:text property="lgcdocm" size="16" maxlength="14" onkeyup="criaMascara(this, '##############');"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Valor Consultado
					</td>
					<td>
						<html:text property="lgyvalr" style="text-align: right;" size="10" maxlength="10" onkeydown="Formata(this,10,event,2)"></html:text>
					</td>
				</tr>
				<tr style="background-color: #DDD;">
					<th colspan="2" style="text-align: center;"> Observa��es Gerais </th>					
				</tr>
				<tr>
					<td colspan="2"> 
						<html:textarea property="lgmobsv" cols="60" rows="3">
						
						</html:textarea>
					</td>					
				</tr>				
				<tr>
					<th colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Registrar Liga��o</html:submit>	
					</th>
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