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
				<a href="actionFiltro.do?m=lista">Filtro de ajuda de custo</a>&nbsp;>&nbsp;
				<a href="actionFiltro.do?m=opcao&filncodg=<bean:write name="filtro" property="filncodg"/>">
			<bean:write name="filtro" property="filcdesc"/>
				</a>
			&nbsp;>&nbsp;Funcionários
			</legend>
		  <logic:present name="msg">
		  <br><font style="color: red; font-weight: bold;"><bean:write name="msg"/></font>
		  </logic:present>
  			<html:form action="actionFiltroajcust">
  			<html:hidden property="m" value="cadastro"/>
  			<input type="hidden" name="filncodg" value="<bean:write name="filtro" property="filncodg"/>">
			<table style="width: 100%;border-collapse: collapse;" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<th colspan="3" style="text-align: center; background-color: #DDDDDD;">  Pagamento de Ajuda de Custo </th>
				</tr>
				<tr>				
					<td colspan="3">
						<input type="submit" class="btn_hot" value="Enviar">
					</td>
				</tr>
                <tr>
					<th>
						Nome
					</th>
					<th>
						Incluído?
					</th>
					<th style="text-align: right;">
						Valor&nbsp;&nbsp;
					</th>
				</tr>
				<logic:empty name="listaFiltroajcust">
					<th colspan="3">
						 Não há Filtro de Ajuda de Custo Cadastrado.
					</th>
				</logic:empty>
				<% int c = 0; %>
				<logic:iterate id="b001" name="listaFiltroajcust">
				<tr style="padding: 0px; background-color: <%= (c++%2==0?"#f0f0f0":"transparent") %>;">
					<td>
						<bean:write name="b001" property="facccden"/>&nbsp;&nbsp;
					</td>
										
					<td style="padding: 0px;margin: 0px;">
					<select name="faclatv_<bean:write name="b001" property="facncgfil"/>_<bean:write name="b001" property="facncgajc"/>"
						<logic:equal value="T" name="b001" property="faclatv">
						style="background-color: yellow; color: red; font-weight: bold;border: solid 1px black;"
						</logic:equal>
					>
						<option value="F" 
						<logic:equal value="F" name="b001" property="faclatv">
						selected="selected"
						</logic:equal>
						>Não</option>
						<option 
						 value="T"
						<logic:equal value="T" name="b001" property="faclatv">
						selected="selected"
						</logic:equal>
						>Sim</option>
					</select>
					</td>
					<td style="text-align: right;">
						<bean:write name="b001" property="facyvlajc"/>&nbsp;&nbsp;
					</td>
				</tr>
				</logic:iterate>			
				<tr>
					<td colspan="3">
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
