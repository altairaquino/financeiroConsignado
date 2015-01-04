	
	<%@include file="/pages/topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="/pages/menu.jsp" %>
		<%@include file="/pages/header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset style="text-align: center;">
			<legend class="red">
				&nbsp;   Lista de centro de custo do empregado > <bean:write name="empregado" property="encnome"/>  &nbsp;
			</legend>
			<table style="width: 650px;">
			<tbody>
				<tr>
					<td colspan="6">
						<input type="button" value="Alterar/Incluir" title="Alterar/Incluir" onclick="window.location = 'actionEmpregadoCentrocusto.do?m=editarLista&empncodg=<bean:write name="funcionario" property="empncodg"/>'">
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD;text-align: center" colspan="6">
						Lista de centro de custo do empregado
					</th>
				</tr>
				<logic:notEmpty name="ls_empregadocentrocusto">
				<tr style="background-color: #DDD;text-align: center;">
					<th>
						Descrição do Centro de Custo
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				</logic:notEmpty>
				<logic:empty name="ls_empregadocentrocusto">
				<tr>
					<th colspan="6" style="color: red;">
						Não há Centro de Custo cadastrados para o empregado.
					</th>
				</tr>
				</logic:empty>
				<logic:iterate id="b" name="ls_empregadocentrocusto">
				<tr>
					<td>
						<bean:write name="b" property="crcdesc"/>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				</logic:iterate>
				<tr>
					<td colspan="6">
						<input type="button" class="btn" value="Voltar" onclick="window.location = 'actionEntidade.do?m=opcoesFuncionario&enncodg=<bean:write name="empregado" property="enncodg"/>'">
					</td>
				</tr>			
			
			</tbody>			
			</table>	
		</fieldset>
			
		</div>
		
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>