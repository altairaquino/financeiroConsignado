	
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
				&nbsp;   Edi��o de Conv�nio    &nbsp;
			</legend>
			<html:form action="actionProduto" focus="pdncgbc">
			<html:hidden property="pdncodg"/>
			<html:hidden property="m" value="update"/>
			<table style="width: 450px;">
			<tbody>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="2"> Altera��o de Conv�nio</th>
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
						Nome do Conv�nio
					</td>
					<td>
						<html:text property="pdcdesc" size="60" maxlength="60"/>
					</td>
				</tr>
				<tr>
					<td>
						N�mero
					</td>
					<td>
						<html:text property="pdnnumr" size="3" maxlength="2" onkeyup="criaMascara(this, '##');"/>
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
						<html:submit styleClass="btn_hot">Salvar Conv�nio</html:submit>
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