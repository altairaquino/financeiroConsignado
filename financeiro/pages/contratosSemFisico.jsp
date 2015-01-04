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
				&nbsp;   LISTA DE CONTRATOS PARA DAR BAIXA DE F�SICO    &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<td colspan="7">
						<html:form action="actionContrato">				
							<html:hidden property="m" value="pesquisaFisico"/>
							Agente de Cr�dito ou n�mero do contrato&nbsp;<html:text property="ctnnumr" size="25" maxlength="25"></html:text>
							<input type="image" title="Pesquisar Contrato" src="imagens/pesquisa.jpg" onclick="if(this.form.ctnnumr.value == ''){alert('Informe o nome do angariador ou o n�mero do contrato!'); return false;}">
						</html:form>
					</td>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%">
			<tbody>
				<tr>
					<th>
						N�mero
					</th>
					<th>
						Cliente
					</th>
					<th>
						Agente de Cr�dito
					</th>
					<th>
						Valor
					</th>
					<th>
						Status
					</th>
					<th>
						&nbsp;
					</th>
				</tr>				
				<logic:empty name="ls_contrato">
				<tr>
					<td colspan="7" style="color: red;"> 
						N�O H� CONTRATOS PARA BAIXA DE F�SICO 
					</td>
				</tr>				
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_contrato">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td> 
						<bean:write name="b" property="ctnnumr"/> 
					</td>
					<td> 
						<bean:write name="b" property="ctcnmcl"/>
					</td>
					<td> 
						<bean:write name="b" property="ctcnman"/>
					</td>
					<td> 
						R$ <bean:write name="b" property="ctyvalr"/> 
					</td>
					<td>
						<bean:write name="b" property="ctcdcsc"/>
					</td>
					<td style="text-align: center;">
						<input type="image" src="imagens/detalhe.gif" title="Baixar F�sico do Contrato" onclick="if (window.confirm('Confirmar a Baixa do F�sico para este Contrato?\nContrato n�: <bean:write name="b" property="ctnnumr"/>\nCliente: <bean:write name="b" property="ctcnmcl"/>')){window.location = 'actionContrato.do?m=baixaFisico&ctncodg=<bean:write name="b" property="ctncodg"/>'}">
					</td>					
				</tr>
				</logic:iterate>	
			</tbody>			
			</table>	
			</div>
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>