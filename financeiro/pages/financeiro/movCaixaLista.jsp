	<%@include file="../topo.jsp" %>	
					

<%@page import="com.grupoexata.financeiro.struts.bean.BeanMovCaixa"%></head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Movimento do caixa > <bean:write name="caixa" property="caxddata"/> &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<td colspan="8">
						<input type="button" class="btn" value="Voltar a Lista de Caixas" onclick="window.location = 'actionCaixa.do?m=lista'">
					</td>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%">
			<tbody>
				<logic:notEmpty name="ls_movcaixa">
				<tr>
					<th colspan="8">
						DADOS DO CAIXA<br>
						Fechamento: <bean:write name="caixa" property="caxdfech"/><br>
						Responsável: <bean:write name="caixa" property="caxcresp"/><br>
						<input type="button" class="btn_hot" value="Relatório" onclick="window.location = 'actionCaixa.do?m=relatorio&caxncodg=<bean:write name="caixa" property="caxncodg"/>'">
						<logic:empty name="caixa" property="caxdfech">
							<input type="button" class="btn_hot" value="Fechar Caixa" onclick=" if (window.confirm('Fechar movimento do caixa para este dia?')){window.location = 'actionCaixa.do?m=fechar&caxncodg=<bean:write name="caixa" property="caxncodg"/>'}">
						</logic:empty>
					</th>
				</tr>
				</logic:notEmpty>
				<logic:notEmpty name="ls_movcaixa">
				<tr>
					<th>
						Código
					</th>
					<th>
						Data
					</th>
					<th>
						Tipo
					</th>
					<th>
						Parc.
					</th>
					<th>
						Descrição
					</th>
					<th>
						Observação
					</th>					
					<th>
						Valor
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				</logic:notEmpty>
				<logic:empty name="ls_movcaixa">
				</logic:empty>
				<logic:iterate id="b" name="ls_movcaixa">
				<% BeanMovCaixa bmc = (BeanMovCaixa)b;
				   String cor = bmc.getMovctpgpc().equals("E")?"#E0D5BA":"#C1ACB6";
				%>
				<tr style="background-color: <%= cor %>;">					
					<td style="font-size: 9px;">
						<bean:write name="b" property="movccodg"/>
					</td>
					<td style="font-size: 9px;">
						<bean:write name="b" property="movddata"/>
					</td>
					<td style="font-size: 9px; text-align: center; font-weight: bold; color: #D00;">
						<bean:write name="b" property="movctpgpc"/>
					</td>
					<td style="font-size: 9px;">
						<bean:write name="b" property="movnnrpc"/>/<bean:write name="b" property="movnqtpc"/>
					</td>
					<td style="font-size: 9px;">
						<bean:write name="b" property="movcdccog"/>
					</td>
					<td style="font-size: 9px;">
						<bean:write name="b" property="movcdesc"/>
					</td>
					<td style="font-size: 9px;">
						R$ <bean:write name="b" property="movyvalr"/>
					</td>
					<td <logic:notEmpty name="b" property="movnforn"> rowspan="2"</logic:notEmpty> style="border-bottom-style: dotted; border-bottom-width: 2px;">
						<logic:empty name="caixa" property="caxdfech">
							<input type="image" src="imagens/fecha.png" title="Cancelar" onclick="if (window.confirm('Cancelar o movimento?')){window.location = 'actionMovCaixa.do?m=cancelar&movncodg=<bean:write name="b" property="movncodg"/>&caxncodg=<bean:write name="caixa" property="caxncodg" />'}">
						</logic:empty>
						<input type="image" src="imagens/detalhe.gif" title="Detalhe" onclick="window.location = 'actionMovCaixa.do?m=dados&movncodg=<bean:write name="b" property="movncodg"/>'">
					</td>
				</tr>
				<logic:notEmpty name="b" property="movnforn">
				<tr style="background-color: <%= cor %>;">
					<td colspan="7"  style="font-size: 9px; font-weight: bold; border-bottom-style: dotted; border-bottom-width: 2px;">
						Benefic: <bean:write name="b" property="movcforn"/>
						<logic:notEmpty name="b" property="movncoco">
							&nbsp;(<bean:write name="b" property="movccoco"/>)
						</logic:notEmpty>
					</td>
				</tr>
				</logic:notEmpty>
				</logic:iterate>		
			</tbody>			
			</table>
			</div>
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>


<%@page import="com.grupoexata.financeiro.struts.bean.BeanMovCaixa"%></html>