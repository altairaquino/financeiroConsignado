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
				&nbsp;   Movimento Caixa - Dados &nbsp;
			</legend>
			<table style="width: 600px; padding: 5px;">
			<tbody>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionCaixa.do?m=lista'">
					</td>
					<td style="text-align: right;">
						&nbsp;
						<%-- 
						--%>
						<input type="button" value="Editar" class="btn_hot" onclick="window.location = 'actionMovCaixa.do?m=editar&movncodg=<bean:write name="movcaixa" property="movncodg"/>'">
					</td>
				</tr>
				<tr>
					<th colspan="2" style="background-color: #DDD; font-weight: bold; text-align: center; font-size: 14px;">
						Dados do Movimento Caixa
					</th>
				</tr>
				<tr>
					<th style="width: 25%">
						Código Movimento
					</th>
					<td style="width: 75%; color: #00D; font-weight: bold;">
						<bean:write name="movcaixa" property="movccodg"/>
						
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Data Movimento
					</th>
					<td style="width: 75%">
						<bean:write name="movcaixa" property="movddata"/>&nbsp;&nbsp;&nbsp;
						<b>Data Vencimento</b>&nbsp;&nbsp;&nbsp;
						<bean:write name="movcaixa" property="movdvenc"/>
					</td>
				</tr>
				<tr>
					<th style="width: 25%">
						Grupo Conta
					</th>
					<td style="width: 75%">
						<bean:write name="movcaixa" property="movcdcgpc"/>
					</td>
				</tr>
				<logic:notEmpty name="movcaixa" property="movcdcore">
				<tr>
					<th style="width: 25%">
						Origem Entrada
					</th>
					<td style="width: 75%">
						<bean:write name="movcaixa" property="movcdcore"/>
					</td>
				</tr>
				</logic:notEmpty>
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<th style="vertical-align: top;">
						Descrição (Conta)
					</th>
					<td>
						<bean:write  name="movcaixa" property="movcdccog"/>													
					</td>
				</tr>
				<tr>
					<th style="vertical-align: top;">
						Forma Pgto.
					</th>
					<td>
						<bean:write name="movcaixa" property="movcflqu"/>
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
					<td style="color: #00D; font-weight: bold;">
						<logic:iterate id="b" name="ls_centromovcaixa">
							<bean:write name="b" property="crmccent"/><br>
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
						Filial/Loja
					</th>
					<td style="color: #00D;">
						<bean:write name="movcaixa" property="movcfili"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<th>
						Beneficiário
					</th>
					<td style="color: #D00;">
						<bean:write name="movcaixa" property="movcforn"/>
					</td>
				</tr>
				<tr>
					<th>
						Conta Corrente
					</th>
					<td style="color: #D00;">
						<bean:write name="movcaixa" property="movccoco"/>	
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
						<bean:write name="movcaixa" property="movcdesc"/>	
					</td>
				</tr>
				<tr>
					<th>
						Valor
					</th>
					<td style="font-weight: bold;">
						R$ <bean:write name="movcaixa" property="movyvalr" />
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionCaixa.do?m=lista'">
					</td>
				</tr>				
			</tbody>
			</table>						
								
		</fieldset>			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>


<%@page import="com.grupoexata.financeiro.struts.bean.BeanMovCaixa"%>
<%@page import="java.util.List"%></html>