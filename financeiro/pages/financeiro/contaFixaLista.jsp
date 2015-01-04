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
				&nbsp;  Contas Fixas > Rela��o &nbsp;
			</legend>
			
			<table style="width: 100%">
			<tbody>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Nova Conta" onclick="window.location = 'actionContaFixa.do?m=novo'">&nbsp;&nbsp;
						<input type="button" class="btn_hot" value="Relat�rio de Contas Fixas" onclick="window.location = 'actionContaFixa.do?m=relatorios'">&nbsp;&nbsp;
						<input type="button" class="btn_hot" value="Importar Contas" onclick="if (window.confirm('Importar contas fixas de hoje para Pagamentos Futuros?')){window.location = 'actionContaFixa.do?m=importar'}">
					</td>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%;">
			<tbody>
				<logic:notEmpty name="ls_contafixa">
				<tr>
					<th colspan="6">
						Rela��es de Contas Fixas
					</th>
				</tr>
				<tr>
					<th>
						Descri��o (Conta)
					</th>
					<th>
						Dia Venc.
					</th>
					<th>
						Observa��o
					</th>
					<th>
						Hist�rico
					</th>
					<th>
						Valor
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				</logic:notEmpty>
				<logic:empty name="ls_contafixa">
				<tr>
					<td colspan="5">
						N�o h� contas fixas no sistema.
					</td>
				</tr>
				</logic:empty>
				<% int i = 0; %>
				<logic:iterate id="b" name="ls_contafixa">
				<tr style="background-color: <%= ((i++%2) == 1)?"#FFF":"#DDD" %>;">
					<td style="font-size: 9px;">
						<bean:write name="b" property="cofccont"/>
					</td>
					<td style="font-size: 9px; font-weight: bold; color: #D00;">
						<bean:write name="b" property="cofndia"/>
					</td>
					<td style="font-size: 9px;">
						<bean:write name="b" property="cofcdesc"/>
					</td>
					<td style="font-size: 9px;">
						<bean:write name="b" property="cofcdocm"/>
					</td>
					<td style="font-size: 9px;">
						<bean:write name="b" property="cofyvalr"/>
					</td>
					<td style="font-size: 9px;"<logic:notEmpty name="b" property="cofnforn"> rowspan="2"</logic:notEmpty>>
						<input type="image" src="imagens/detalhe.gif" title="Editar Conta" onclick="window.location = 'actionContaFixa.do?m=editar&cofncodg=<bean:write name="b" property="cofncodg"/>'">
						<input type="image" src="imagens/cancela.jpg" title="Cancelar Conta" onclick="if (window.confirm('Confirmar o cancelamento da conta fixa?')){window.location = 'actionContaFixa.do?m=cancelar&cofncodg=<bean:write name="b" property="cofncodg"/>';}">					
					</td>
				</tr>
				<logic:notEmpty name="b" property="cofnforn">
				<tr>
					<td colspan="5"  style="font-size: 9px; font-weight: bold;">
						Fornec: <bean:write name="b" property="cofcforn"/>
						<logic:notEmpty name="b" property="cofncoco">
							&nbsp;(<bean:write name="b" property="cofccoco"/>)
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


</html>