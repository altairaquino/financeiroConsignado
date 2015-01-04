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
				&nbsp;   Movimento do caixa do Fornecedor > <bean:write name="fornecedor" property="encnome"/> &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<td colspan="7">
						<input type="button" class="btn" value="Voltar" onclick="window.location = 'actionEntidade.do?m=opcoesFornecedor&enncodg=<bean:write name="fornecedor" property="enncodg"/>'">
					</td>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%">
			<tbody>
				<logic:notEmpty name="ls_movcaixa">
				<tr>
					<th>
						Data
					</th>
					<th>
						Grupo
					</th>
					<th>
						Conta
					</th>
					<th>
						Forma de Pgto.
					</th>
					<th>
						Observação
					</th>
					<th>
						Histórico
					</th>
					<th>
						Valor
					</th>
				</tr>
				</logic:notEmpty>
				<logic:empty name="ls_movcaixa">
				<tr>
					<td colspan="7">
						Não há movimento para este fornecedor.
					</td>
				</tr>
				</logic:empty>
				<logic:notEmpty name="ls_movcaixa">
				<% int c = 0; float soma = 0;%>
				<logic:iterate id="b" name="ls_movcaixa">
				<% soma += Float.parseFloat(((BeanMovCaixa)b).getMovyvalr().replace(".","").replace(",",".")); %>
				<tr style="background-color: <%= (c%2==0?"#DDD":"#FFF") %>;">
					<td style="font-size: 9px; font-weight: bold; color: #D00;">
						<bean:write name="b" property="movddata"/>
					</td>
					<td style="font-size: 9px;">
						<bean:write name="b" property="movcdcgpc"/>
					</td>
					<td style="font-size: 9px;">
						<bean:write name="b" property="movcdccog"/>
					</td>
					<td style="font-size: 9px;">
						<bean:write name="b" property="movcflqu"/>
					</td>
					<td style="font-size: 9px;">
						<bean:write name="b" property="movcdesc"/>
					</td>
					<td style="font-size: 9px;">
						<bean:write name="b" property="movcdocm"/>
					</td>
					<th style="text-align: right; font-size: 9px;" >
						R$ <bean:write name="b" property="movyvalr"/>
					</th>					
				</tr>
				<logic:notEmpty name="b" property="movccoco">
				<tr>
					<td colspan="7" style="background-color: <%= (c%2==0?"#DDD":"#FFF") %>; border-bottom-style: dotted; border-bottom-width: 2px;">
						Conta do Pagamento: <bean:write name="b" property="movccoco"/>
					</td>
				</tr>
				<% c++; %>
				</logic:notEmpty>
				</logic:iterate>
				<tr>
					<th style="text-align: right; color: #00D;" colspan="7">
						Soma: R$ <%= new FormataObj().formataValor(soma) %>
					</th>
				</tr>
				</logic:notEmpty>	
			</tbody>			
			</table>
			</div>
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>


<%@page import="com.grupoexata.financeiro.struts.bean.BeanMovCaixa"%>
<%@page import="com.grupoexata.bancario.utils.FormataObj"%></html>