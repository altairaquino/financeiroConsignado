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
				&nbsp; <a href="actionBordero.do?m=dados&borncodg=<bean:write name="bordero" property="borncodg"/>" title="Ir ao dados do borderô">Borderô nº <bean:write name="bordero" property="borcnumr"/></a> > Relação de Contratos    &nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionBordero.do?m=lista'">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						Nº do Contrato&nbsp;
						<html:form action="actionContratoBordero" focus="ctbnnrct">
							<html:hidden property="m" value="cadastro"/>
							<input type="hidden" name="ctbncgbor" value="<bean:write name="bordero" property="borncodg"/>">
							<html:text property="ctbnnrct" size="10" maxlength="9" onkeyup="criaMascara(this,'#########')"/>
							<html:submit styleClass="btn_hot">Adicionar</html:submit>
						</html:form>
					</td>
				</tr>				
				<tr>
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center; font-size: 13px;"> 
						Relação de Contratos
					</th>
				</tr>
			</tbody>			
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th>
						Nome do Cliente
					</th>
					<th>
						C.P.F.
					</th>
					<th>
						Nº Contrato
					</th>
					<th>
						Data de Pgto.
					</th>
					<th>
						Valor
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_contratobordero">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="ctbcnmcl"/>
					</td>
					<td>
						<B><bean:write name="b" property="ctbccpf"/></B>
					</td>
					<td style="color: #00D;">
						<bean:write name="b" property="ctbnnrct"/>
					</td>
					<td>
						<bean:write name="b" property="ctbdpgto"/>
					</td>
					<td style="color: #D00; text-align: right;">
						R$ <bean:write name="b" property="ctbyvlct"/>
					</td>
					<td style="text-align: center;">
						<logic:equal name="b" property="ctblconf" value="F">
						<input type="image" src="imagens/check.jpg" title="Confirmar Recebimento pelo Banco" onclick="if (window.confirm('Confirmar recebimento pelo Banco?')){window.location = 'actionContratoBordero.do?m=confirma&ctbncodg=<bean:write name="b" property="ctbncodg"/>&borncodg=<bean:write name="bordero" property="borncodg"/>';}">
						<input type="image" src="imagens/delete.gif" title="Excluir" onclick="if (window.confirm('Excluir contrato do borderô?')){window.location = 'actionContratoBordero.do?m=delete&ctbncodg=<bean:write name="b" property="ctbncodg"/>&borncodg=<bean:write name="bordero" property="borncodg"/>';}">
						</logic:equal>
						<logic:equal name="b" property="ctblconf" value="T">
							<b title="Recebimento Confirmado pelo Banco">XX</b>
						</logic:equal>
					</td>					
				</tr>
				</logic:iterate>
			</tbody>
			</table>	
			</div>
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>