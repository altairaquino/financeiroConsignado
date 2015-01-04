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
				&nbsp;   LISTA DE COMISSÕES DO CONTRATO    &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<td colspan="5" style="font-size: 12px;">			
						Contrato: <B><bean:write name="contrato" property="ctnnumr"/></B>						
					</td>
				</tr>
				<tr>
					<td colspan="5" style="font-size: 12px;">
						Cliente: <B><bean:write name="contrato" property="ctcnmcl"/></B>
					</td>
				</tr>				
				<tr>
					<td colspan="5" style="font-size: 12px;">
						Agente de Crédito: <B><bean:write name="contrato" property="ctcnman"/></B>
					</td>
				</tr>
				<tr>
					<td colspan="5" style="font-size: 12px;">
						Valor: <B>R$ <bean:write name="contrato" property="ctyvalr"/></B>					
					</td>
				</tr>
				<tr>
					<td colspan="5" style="font-size: 12px;">
						Forma de Pagamento: <B><bean:write name="contrato" property="ctcdcfp"/></B>
					</td>
				</tr>
				<tr>
					<td colspan="5" style="font-size: 12px;">
						Status do Contrato: <B><bean:write name="contrato" property="ctcdcsc"/></B>
					</td>
				</tr>
				<tr>
					<td colspan="5" style="font-size: 12px;">
						Data da Baixa do Físico: <B><bean:write name="contrato" property="ctdbxfi"/></B>				
					</td>
				</tr>
				<tr>
					<td colspan="5" style="font-size: 12px;">
						Data da Averbação: <B><bean:write name="contrato" property="ctdverb"/></B>
					</td>
				</tr>
				<tr>
					<td colspan="5" style="font-size: 12px;">
						Percentual da Exata: <B><bean:write name="contrato" property="ctnexat"/>%</B>
					</td>
				</tr>
				<logic:notEmpty name="contrato" property="ctdextn">
				<tr>
					<td colspan="5" style="font-size: 12px;">
						Data do Extorno: <b><bean:write name="contrato" property="ctdextn"/></b>
					</td>
				</tr>
				</logic:notEmpty>
				<logic:empty name="contrato" property="ctdverb">
				</logic:empty>
				<tr>
					<td colspan="5">
						<input type="button" class="btn" value="Re-Calcula Comissões" onclick="if (window.confirm('Confirma re-cálculo de comissões do contrato?')){ window.location = 'actionComissaoContrato.do?m=recalculaComissaoContrato&ctncodg=<bean:write name="contrato" property="ctncodg"/>'}">
					</td>
				</tr>
				<tr>
					<th>
						Agente de Crédito
					</th>
					<th>
						(%)
					</th>
					<th>
						Valor
					</th>
					<th>
						Data de Alteração
					</th>
				</tr>			
				<logic:empty name="ls_comissaocontrato">
				<tr>
					<td colspan="5" style="color: red;">
						NÃO HÁ COMISSÕES PARA ESTE CONTRATO
					</td>
				</tr>				
				</logic:empty>
				<html:form action="actionComissaoContrato">
				<html:hidden property="m" value="updateComissoes"/>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_comissaocontrato">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<html:hidden name="b" property="ccncodg"/>
					<td>
						<bean:write name="b" property="cccnmen"/> 
					</td>
					<td>
						<input type="text" name="ccnperc" onkeydown="Formata(this,8,event,2)" style="text-align: right;" size="6" maxlength="6" value="<bean:write name="b" property="ccnperc"/>">&nbsp;					
					</td>
					<td>
						R$ <bean:write name="b" property="ccnvlcm"/>
					</td>
					<td>
						<bean:write name="b" property="ccdalte"/>
					</td>
				</tr>
				</logic:iterate>
				<tr>
					<td colspan="3">
						<input type="button" value="Cancelar" class="btn_hot" onclick="window.location = 'contratoPesquisaComissao.do'">
					</td>
					<td colspan="3" style="text-align: right;">
						<html:submit styleClass="btn_hot">Salva Comissões</html:submit>
					</td>
				</tr>				
				</html:form>
			</tbody>			
			</table>	
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>