	<%@include file="../topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Cadastro de Forma de Pagamento    &nbsp;
			</legend>
			
			<html:form action="/actionFormaLiquidez" focus="flqcdesc">
			<html:hidden property="m" value="cadastro"/>
			<table style="width: 450px;">
			<tbody>
				<tr>
					<th colspan="2">
						Cadastro de Forma de Pagamento
					</th>
				</tr>
				<tr>
					<th>
						Descrição
					</th>
					<td>
						<html:text property="flqcdesc" size="50" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<th>
						Liquidação Imediata?
					</th>
					<td>
						<html:radio property="flqlimed" value="T">Sim</html:radio>&nbsp;
						<html:radio property="flqlimed" value="F">Nao</html:radio>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Cancelar" class="btn_hot" onclick="window.location = 'actionFormaLiquidez.do?m=lista'">
					</td>					
					<td style="text-align: right;">
						<html:submit value="Salvar"/>
					</td>
				</tr>
			</tbody>	
			</table>
			</html:form>
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>