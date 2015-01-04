	
	<%@include file="topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset style="text-align: center;">
			<legend class="red">
				&nbsp;   <bean:write name="formAngariador" property="ancnmen"/> > Dados do Angariador (Configuração)  &nbsp;
			</legend>
			<html:form action="actionAngariador" focus="annmeta">
			<html:hidden property="anncgen"/>
			<html:hidden property="m" value="update"/>
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="2">
						Dados do Angariador (Configuração)
					</th>
				</tr>
				<tr>
					<th style="width: 25%;">
						Tipo
					</th>
					<td style="width: 75%;">
						 <html:select property="anncgtn" style="width: 350px;">
						 	<html:optionsCollection name="ls_tipoangariador" value="tnncodg" label="tncdesc"/>
						 </html:select>
					</td>
				</tr>
				<tr>
					<th>
						Regional
					</th>
					<td>
						 <html:select property="anncgre" style="width: 350px;">
						  	<html:option value="-1">Sem Regional</html:option>
						 	<html:optionsCollection name="ls_regional" value="rgncodg" label="rgcdesc"/>
						 </html:select>
					</td>
				</tr>
				<tr>
					<th>
						Meta Mensal
					</th>
					<td>
						 <html:text property="annmeta" size="10" maxlength="10" onkeydown="Formata(this,10,event,2)" style="text-align: right;"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						Spread
					</th>
					<td>
						 <html:text property="annspre" size="10" maxlength="8" onkeydown="Formata(this,8,event,2)" style="text-align: right;"></html:text>
					</td>
				</tr>
				<html:hidden property="anlagen" value="F"/>
				<%--  
				<tr>
					<th>
						É agência SINERGIA?
					</th>
					<td>
						 <html:radio property="anlagen" value="T">Sim</html:radio>
						 <html:radio property="anlagen" value="F">Nao</html:radio>
					</td>
				</tr>
				--%>
				<tr>
					<th>
						Data de Cadastro
					</th>
					<td>
						 <html:text property="andcadt" size="10" maxlength="10" onkeyup="criaMascara(this, '##/##/####')"/>
					</td>
				</tr>
				<tr>
					<th>
						Ativo?
					</th>
					<td>
						 <html:radio property="anlativ" value="T">Sim</html:radio>
						 <html:radio property="anlativ" value="F">Nao</html:radio>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" value="Cancelar" onclick="window.location = 'actionAngariador.do?m=dados&enncodg=<bean:write name="formAngariador" property="anncgen"/>'">
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
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>