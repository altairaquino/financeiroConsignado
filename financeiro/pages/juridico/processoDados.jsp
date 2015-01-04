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
				&nbsp;   Processo > Dados (Código: <bean:write name="processo" property="proncodg2"/>)  &nbsp;
			</legend>
			<table style="width: 600px;" align="center" cellspacing="0" cellpadding="4">
			<tbody>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionProcesso.do?m=opcoes&proncodg=<bean:write name="processo" property="proncodg"/>'">						
					</td>
					<td style="text-align: right;">					
						<input type="button" class="btn_hot" value="Editar" onclick="window.location = 'actionProcesso.do?m=editar&proncodg=<bean:write name="processo" property="proncodg"/>'">
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #CCC;">
						Dados Básicos do Cliente
					</th>
				</tr>
				<tr>
					<td colspan="2">
						Processo cadastrado por: <B><bean:write name="processo" property="proccadt"/></B> em <B><bean:write name="processo" property="proddata"/></B>.
					</td>
				</tr>
				<tr>
					<th>
						C.P.F.
					</th>
					<td>
						<bean:write name="processo"  property="proccpf"/>
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Nome
					</th>
					<td style="width: 75%">
						<bean:write name="processo"  property="procnmcl"/>
					</td>
				</tr>
				<tr>
					<th>
						Data Nascimento
					</th>
					<td>					
						<bean:write name="processo"  property="prodnasc"/>
						&nbsp;<b>Sexo:</b>&nbsp;
						<logic:equal name="processo" property="procsexo" value="M">
							Masculino
						</logic:equal>
						<logic:equal name="processo" property="procsexo" value="F">
							Feminino
						</logic:equal>						
					</td>
				</tr>				
				<tr>
					<th colspan="2" style="text-align: center;background-color: #CCC;"> 
						Contatos
					</th>
				</tr>
				<tr>
					<th style="width: 25%">
						Angariador
					</th>
					<td style="width: 75%">
						<bean:write name="processo"  property="procnmcn"/>
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Consultor
					</th>
					<td style="width: 75%">
						<bean:write name="processo" property="proccons"/>											
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Supervisor
					</th>
					<td style="width: 75%">
						<bean:write name="processo" property="procsupe"/>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #CCC;"> 
						Dados do Processo
					</th>
				</tr>
				<tr>
					<th style="width: 25%">
						Nº do Processo
					</th>
					<td style="width: 75%">
						<bean:write name="processo"  property="procnumr"/>						
					</td>
				</tr>
				<tr>
					<th>
						Tipo de Ação
					</th>
					<td>
						<bean:write name="processo" property="procdctac"/>
					</td>
				</tr>
				<tr>
					<th style="width: 25%"> 
						Localização Interna
					</th>
					<td style="width: 75%">
						<bean:write name="processo"  property="procloca"/>			
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #CCC;"> 
						Andamento do Processo
					</th>
				</tr>
				<logic:iterate id="b" name="ls_andamentoprocesso">
				<tr>
					<td style="font-weight: bold;">
						<bean:write name="b" property="anpddata"/>
					</td>
					<td>
						<b><bean:write name="b" property="anpcdcfap"/></b> - <bean:write name="b" property="anpcdesc"/>			
					</td>
				</tr>
				</logic:iterate>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #CCC;">
						Observações do Processo
					</th>
				</tr>
				<tr>
					<td colspan="2" style="height: 60px; vertical-align: top;">						
						<bean:write name="processo" property="procobs"/>
					</td>
				</tr>
			</tbody>			
			</table>
		
		</fieldset>
			
		</div>
			
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>