	<%@include file="../topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend class="red">
				&nbsp;  Centro de Custo > Editar    &nbsp;
			</legend>
			
			<html:form action="/actionCentroCusto" focus="crcdesc">
			<html:hidden property="crncodg"/>
			<html:hidden property="m" value="update"/>
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; text-align: center;">
						Alteração de Centro de Custo
					</th>
				</tr>
				<tr>
					<th>
						Nome do Centro
					</th>
					<td>
						<html:text property="crcdesc" size="40" maxlength="35"/>
					</td>
				</tr>
				<%-- 
				<tr>
					<th>
						Fonte Pagadora
					</th>
					<td>
						<html:select property="crncgore" style="width: 300px;">
							<html:optionsCollection name="ls_origementrada" label="orecdesc" value="orencodg"/>
						</html:select>
					</td>
				</tr>
				--%>
				<tr>
					<th>
						Ativo?
					</th>
					<td>
						<html:radio property="crlativ" value="T">&nbsp;Sim</html:radio>&nbsp;&nbsp;
						<html:radio property="crlativ" value="F">&nbsp;Não</html:radio>			 
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Cancelar" class="btn" onclick="window.location = 'actionCentroCusto.do?m=lista'">
					</td>					
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot" value="Salvar"/>
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