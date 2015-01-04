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
				&nbsp;   Alteração de Operação    &nbsp;
			</legend>
			<html:form action="actionOperacao" focus="opncodg">
			<html:hidden property="m" value="update"/>
			<html:hidden property="opncodg"/>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center;">
						Alteração de Operação
					</th>
				</tr>
				<tr>
					<th>
						Operação Pai
					</th>
					<td>
						<html:select property="opnpart" style="width: 200px;">
							<html:option value="0">Operação Pai</html:option>
							<html:optionsCollection name="ls_operacao_pai" label="opcdesc" value="opncodg"/>
						</html:select>
					</td>
				</tr>	
				<tr>
					<th>
						Descrição
					</th>
					<td>
						<html:text property="opcdesc" size="50" maxlength="45"/>
					</td>
				</tr>	
				<tr>
					<th>
						Legenda
					</th>
					<td>
						<html:text property="opchint" size="50" maxlength="100"/>
					</td>
				</tr>	
				<tr>
					<th>
						Link
					</th>
					<td>
						<html:text property="opclink" size="50" maxlength="100"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button"  class="btn" value="Voltar" onclick="window.location = 'actionOperacao.do?m=lista'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Salvar Operação</html:submit>
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