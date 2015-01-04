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
				&nbsp;   PAGAMENTOS FUTUROS &nbsp;
			</legend>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center; font-size: 14px;">
						Dados de Pagamentos futuros
					</th>
				</tr>
				<tr>
					<th>
						Data (Mov/Venc)
					</th>
					<td>
						<bean:write name="contapagar" property="cpddata"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<th style="vertical-align: top;">
						Descrição
					</th>
					<td>
						<bean:write name="contapagar" property="cpccont"/>					
					</td>
				</tr>	
				<tr>
					<th style="vertical-align: top;">
						Forma Pgto.
					</th>
					<td>
						<bean:write name="contapagar"  property="cpcflqu"/>
					</td>
				</tr>				
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<th style="vertical-align: top;">
						Centros de Custo
					</th>
					<% List l = (List)request.getAttribute("ls_centrocontapagar"); %>
					<td>
						<logic:iterate id="b" name="ls_centrocontapagar">
							<% if (l.size() > 1){ %> <input type="image" src="imagens/fecha.png" onclick="if (window.confirm('Excluir Centro de Custo desta conta?')){window.location = 'actionContaPagar.do?m=excluir&crcncodg=<bean:write name="b" property="crcncodg"/>'}"> &nbsp;<% } %><bean:write name="b" property="crcccent"/><br>
						</logic:iterate>						
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<th>
						Observação
					</th>
					<td>
						<bean:write name="contapagar" property="cpcdesc"/>
					</td>
				</tr>
				<tr>
					<th>
						Documento
					</th>
					<td>
						<bean:write name="contapagar" property="cpcdocm" />
					</td>
				</tr>
				<tr>
					<th>
						Valor
					</th>
					<td>
						R$ <bean:write name="contapagar" property="cpyvalr"/>	
					</td>
				</tr>
				<html:form action="actionContaPagar" focus="cpdpgto">
				<html:hidden property="m" value="pagar"/>
				<html:hidden property="cpncodg" name="contapagar"/>
				
				<tr>
					<th>
						Data de Pagamento
					</th>
					<td>
						<html:text property="cpdpgto" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/>
					</td>
				</tr>
					
				<tr>
					<td>
						<input type="button" value="Voltar a Lista" onclick="window.location = 'actionContaPagar.do?m=lista'">
					</td>
					<td style="text-align: right;">
						<input type="button" class="btn_hot" value="Efetuar Pagamento" onclick="if (window.confirm('Confirmar o Lançamento do Movimento no caixa?')){this.form.submit();}">
					</td>
				</tr>
				</html:form>
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				
			</tbody>
			</table>
				
										
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>


<%@page import="java.util.List"%></html>