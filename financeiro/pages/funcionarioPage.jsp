	<%@include file="topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Opções do Funcionário    &nbsp;
			</legend>
			<h3><bean:write name="funcionario" property="encnome"/> </h3>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; text-align: center;">
						Opções do Funcionário 
					</th>
				</tr>
				<tr>
					<td>
						Dados Básicos
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionEntidade.do?m=dadosFuncionario&enncodg=<bean:write name="funcionario" property="enncodg"/>'">
					</td>
				</tr>
				<logic:notPresent name="empreg">
				<tr>
					<td>
						Dados Funcionais
					</td>
					<td>
						<input type="button" class="btn_hot" value="Cadastrar" onclick="window.location = 'actionEmpregado.do?m=novo&enncodg=<bean:write name="funcionario" property="enncodg"/>'">						
					</td>
				</tr>
				</logic:notPresent>
				<logic:present name="empreg">
				<tr>
					<td>
						Dados Funcionais
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionEmpregado.do?m=dados&empncodg=<bean:write name="empreg" property="empncodg"/>'">						
					</td>
				</tr>
				</logic:present>
				<tr>
					<td>
						Define Senha de Acesso
					</td>
					<td>
						<input type="button" class="btn_hot" value="Definir Senha" onclick="window.location = 'actionEntidade.do?m=defineSenha&enncodg=<bean:write name="funcionario" property="enncodg"/>'">
					</td>
				</tr>
				<logic:present name="empreg">
				<tr>
					<td>
						Centro de Custo Relacionado
					</td>
					<td>
						<logic:notEmpty name="empreg" property="empncodg">
							<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionEmpregadoCentrocusto.do?m=lista&empncodg=<bean:write name="empreg" property="empncodg"/>'">
						</logic:notEmpty>
					</td>
				</tr>
				</logic:present>
				<tr>
					<td>
						Dados bancários 
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionContaCorrente.do?m=listaEmpr&enncodg=<bean:write name="funcionario" property="enncodg"/>'">
					</td>
				</tr>		
				<tr>
					<td>
						Dependentes
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionDependente.do?m=lista&enncodg=<bean:write name="funcionario" property="enncodg"/>'">
					</td>
				</tr>
				<% String data = new com.grupoexata.bancario.utils.FormataObj().formataData(new java.util.Date()); %>
				<html:form action="actionAtividade" method="post">
				<html:hidden property="m" value="relatorios"/>
				<input type="hidden" name="enncodg" value="<bean:write name="funcionario" property="enncodg"/>">
				<tr>
					<td>
						<select name="tipo" style="width: 300px;">
							<option value="1">Relatório de Atividades Diárias</option>
						</select>&nbsp;Período:&nbsp;
						<input type="text" id="data1" value="<%= data %>" name="data1" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')" readonly="readonly"> <input type="image" id="b1" src="jscalendar/img.gif"> até 
						<input type="text" id="data2" value="<%= data %>" name="data2" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')" readonly="readonly"> <input type="image" id="b2" src="jscalendar/img.gif">
					</td>
					<td>
						<input type="submit" class="btn_hot" value="Visualizar">
					</td>
				</tr>
				</html:form>	
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionEmpregado.do?m=lista'">
					</td>
				</tr>	
			</tbody>		
			</table>		
		</fieldset>
			
		</div>
		<script type="text/javascript">
		    Calendar.setup({
		        inputField     :    "data1",     // id of the input field
		        ifFormat       :    "%d/%m/%Y",      // format of the input field
		        button         :    "b1",  // trigger for the calendar (button ID)
		        align          :    "BR",           // alignment (defaults to "Bl")
		        singleClick    :    true
		    });
		    Calendar.setup({
		        inputField     :    "data2",     // id of the input field
		        ifFormat       :    "%d/%m/%Y",      // format of the input field
		        button         :    "b2",  // trigger for the calendar (button ID)
		        align          :    "BR",           // alignment (defaults to "Bl")
		        singleClick    :    true
		    });
		</script>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>