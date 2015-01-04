<%@include file="/pages/topo.jsp" %>
<script type="text/javascript" src="scripts/jquery.js"></script>
</head>
<body>
	<div id="container">
		<%@include file="/pages/menu.jsp" %>
		<%@include file="/pages/header.jsp" %>
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend>
				<a href="actionPagtoajcust.do?m=lista">Pagamento de ajuda de custo</a>&nbsp;>&nbsp;
				<a href="actionPagtoajcust.do?m=opcao&pacncodg=<bean:write name="pagtoajcust" property="pacncodg"/>">
			<bean:write name="pagtoajcust" property="pacdsem"/>
				</a>
			&nbsp;>&nbsp;Empregados
			</legend>
  <bean:write name="pagtoajcust" property="paccdctac"/>
  pagto:
  <bean:write name="pagtoajcust" property="pacdpagto"/>
  Semana:
  <bean:write name="pagtoajcust" property="pacdsem"/>
  <logic:present name="msg">
  <br><font style="color: red; font-weight: bold;"><bean:write name="msg"/></font>
  </logic:present>

  			<html:form action="actionPagtoajcustemp">
  			<html:hidden property="m" value="cadastroMassa"/>
			<table style="width: 100%;border-collapse: collapse;" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<th colspan="6" style="text-align: center; background-color: #DDDDDD;">  Pagamento de Ajuda de Custo </th>
				</tr>
				<tr>				
					<td colspan="6">
						<input type="submit" class="btn_hot" value="Enviar">
					</td>
				</tr>
                <tr>
					<th>
						Nome
					</th>
					<th>
						Boqueado?
					</th>
					<th style="width: 60px;">
						Valor
					</th>
					<th style="width: 60px;">
						Acréscimo
					</th>
					<th style="width: 60px;">
						Desconto
					</th>
					<th style="width: 60px;">
						Total
					</th>
				</tr>
				<logic:empty name="listaPagtoajcustemp">
					<th colspan="6">
						 Não há Pagamento de Ajuda de Custo Cadastrado.
					</th>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b001" name="listaPagtoajcustemp">
				<tr style="padding: 0px; background-color: <%= (c++%2==0?"#f0f0f0":"transparent") %>;">
					<td>
						<bean:write name="b001" property="pceccden"/>&nbsp;&nbsp;
					</td>					
					<td style="padding: 0px;margin: 0px;">
					<select name="bloq_cod_<bean:write name="b001" property="pcencodg"/>"
						<logic:equal value="T" name="b001" property="pcelbloq">
						style="background-color: yellow; color: red; font-weight: bold;border: solid 1px black;"
						</logic:equal>
					>
						<option value="F" 
						<logic:equal value="F" name="b001" property="pcelbloq">
						selected="selected"
						</logic:equal>
						>Não</option>
						<option 
						 value="T"
						<logic:equal value="T" name="b001" property="pcelbloq">
						selected="selected"
						</logic:equal>
						>Sim</option>
					</select>
						<input type="hidden" name="a_bloq_cod_<bean:write name="b001" property="pcencodg"/>"
						value="<bean:write name="b001" property="pcelbloq"/>"  
						>
					</td>
					<td style="padding: 0px;margin: 0px;">
					<input type="text" 
					id="valor_cod_<bean:write name="b001" property="pcencodg"/>" 
					name="valor_cod_<bean:write name="b001" property="pcencodg"/>" 
					value="<bean:write name="b001" property="pceyvalor"/>" 
					maxlength="10" size="10" style="text-align: right;height: 100%;margin: 0px;" onkeydown="Formata(this,10,event,2)">
					
					<input type="hidden" name="a_valor_cod_<bean:write name="b001" property="pcencodg"/>"
						value="<bean:write name="b001" property="pceyvalor"/>"  
						>
					</td>
					<td style="padding: 0px;margin: 0px;">
						<input type="text" 
						id="acres_cod_<bean:write name="b001" property="pcencodg"/>"
						name="acres_cod_<bean:write name="b001" property="pcencodg"/>"
						value="<bean:write name="b001" property="pceyacres"/>"
						maxlength="10" size="10" style="text-align: right;height: 100%;margin: 0px;" onkeydown="Formata(this,10,event,2)"
						>
						<input type="hidden" name="a_acres_cod_<bean:write name="b001" property="pcencodg"/>"
						value="<bean:write name="b001" property="pceyacres"/>"  
						>
					</td>
					
					<td style="padding: 0px;margin: 0px;">
						<input type="text" 
						name="desc_cod_<bean:write name="b001" property="pcencodg"/>"
						id="desc_cod_<bean:write name="b001" property="pcencodg"/>"
						value="<bean:write name="b001" property="pceydesc"/>"
						maxlength="10" size="10" style="text-align: right;height: 100%; margin: 0px;" onkeydown="Formata(this,10,event,2)"
						>
						<input type="hidden" name="a_desc_cod_<bean:write name="b001" property="pcencodg"/>"
						value="<bean:write name="b001" property="pceydesc"/>">
					</td>
					<td style="padding: 0px;margin: 0px;">
					<input id="total_cod_<bean:write name="b001" property="pcencodg"/>"  type="text" disabled="disabled" 
					maxlength="10" size="10" style="text-align: right;height: 100%;margin: 0px;">
					</td>
				</tr>
				</logic:iterate>
				
				<tr style="padding: 0px; background-color: <%= (c++%2==0?"#f0f0f0":"transparent") %>;">
					<td colspan="2">
						<b>
						TOTAL
						</b>
					</td>					
					<td style="padding: 0px;margin: 0px;">
					<input type="text" 
					id="valor_total" 
					name="valor_total" 
					disabled="disabled" maxlength="10" size="10" style="text-align: right;height: 100%;margin: 0px;">
					</td>
					<td style="padding: 0px;margin: 0px;">
						<input type="text" 
						id="acres_total"
						name="acres_total"
						disabled="disabled" 
						maxlength="10" size="10" style="text-align: right;height: 100%;margin: 0px;"
						>
					</td>
					
					<td style="padding: 0px;margin: 0px;">
						<input type="text" 
						id="desc_total"
						name="desc_total"
						disabled="disabled" 
						maxlength="10" size="10" style="text-align: right;height: 100%; margin: 0px;"
						>
					</td>
					<td style="padding: 0px;margin: 0px;">
					<input 
					id="total_total"  
					name="total_total"  
					type="text" 
					disabled="disabled" 
					maxlength="10" size="10" style="text-align: right;height: 100%;margin: 0px;">
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<input type="submit" class="btn_hot" value="Enviar">
					</td>
				</tr>
			</tbody>
			</table>
  			</html:form>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>
