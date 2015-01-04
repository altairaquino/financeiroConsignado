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
				&nbsp;   Listagem de Contratos com Pendência   &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="6">
						<html:form action="actionContratoAuto" method="post" focus="ctancgep">
							<html:hidden property="m" value="listaPendente"/>
							Loja: &nbsp;<html:select property="ctancgep" onchange="this.form.submit()">
								<html:optionsCollection name="ls_empresa" value="epncodg" label="epcnome"/>
							</html:select>
							<html:submit>Pesquisa</html:submit>
						</html:form>
					</th>
				</tr>
				<tr>
					<th colspan="6" style="background-color: #DDD; text-align: center;"> 
						Lista de Contratos com Pendência 
					</th>
				</tr>
			</tbody>
			</table>
			<div style="height: 350px; width: 100%; overflow: auto;">
			<table style="width: 100%">
			<tbody>	
				<logic:notEmpty name="ls_contratoauto">
				<tr style="background-color: #FF0033; color: white;">
					<th colspan="5" style="text-align: center;">
						Dados do Contrato
					</th>
					<th colspan="2" style="text-align: center;">
						Tipo de Pendência
					</th>
				</tr>
				<tr style="background-color: #FEF5F8;">
					<th>
						Número
					</th>
					<th>
						Cliente
					</th>
					<th>
						Valor
					</th>					
					<th>
						Plano
					</th>
					<th>
						Parcela
					</th>
					<th style="text-align: center;">
						Carnê
					</th>
					<th style="text-align: center;">
						Parcelas
					</th>
				</tr>
				</logic:notEmpty>
				<logic:empty name="ls_contratoauto">
				<tr>
					<th colspan="6" style="color: red; font-weight: bold;">
						Não há contratos com pendências para esta loja.
					</th>
				</tr>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_contratoauto">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<th>
						<a href="actionContratoAuto.do?m=opcoes&ctancodg=<bean:write name="b" property="ctancodg"/>" title="Visualiza Dados do Contrato">
							<bean:write name="b" property="ctacnumr"/>
						</a>			
					</th>					
					<td>
						<bean:write name="b" property="ctacnmen"/>
					</td>
					<th>
						R$ <bean:write name="b" property="ctayvalr"/>
					</th>					
					<td>
						<bean:write name="b" property="ctanplan"/> meses
					</td>
					<th>
						R$ <bean:write name="b" property="ctayvlpc"/>
					</th>
					<th style="text-align: center;">
						<logic:equal name="b" property="ctalpdcn" value="T">
							<a href="actionContratoAuto.do?m=editarQuest&ctancodg=<bean:write name="b" property="ctancodg"/>">
								Pendente
							</a>
						</logic:equal>
						<logic:equal name="b" property="ctalpdcn" value="F">
							&nbsp;
						</logic:equal>
					</th>
					<th style="text-align: center;">
						<logic:equal name="b" property="ctalpdpc" value="T">
							<a href="actionParcelaContratoAuto.do?m=lista&ctancodg=<bean:write name="b" property="ctancodg"/>">
								Pendente
							</a>
						</logic:equal>
						<logic:equal name="b" property="ctalpdpc" value="F">
							&nbsp;
						</logic:equal>
					</th>
				</tr>
				</logic:iterate>
				<tr>
					<th colspan="6">
						
					</th>
				</tr>				
			</tbody>			
			</table>
			</div>	
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>

</html>